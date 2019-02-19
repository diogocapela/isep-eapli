package models.avaliacao;

import modelsDTO.CasoDTO;
import modelsDTO.ObjetoSeguroDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Refere-se a um pedido de avaliação de risco concreto;
 * (i.e. para um ou mais objetos seguros específicos e respetivos conjunto de coberturas) bem como o respetivo resultado
 * produzido desse caso.
 */
@Entity
@Table
public class Caso {
    /* Processar pedido único de análise de risco: cada pedido é efetuado por um sistema externo
       indicando o tipo e código/identificador do processo que enquadra o pedido (e.g. simulação
       2019.00027), o local ou locais de risco a avaliar e o conjunto de coberturas pretendidas. Cada
       pedido de análise é tratado como um Caso.
    */
    // Variáveis de Instância
    //================================================================
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ObjetoSeguro> objetosSegurados;
    private EstadoCaso estado;

    // Construtores
    //================================================================
    public Caso() {
        estado = EstadoCaso.ESPERA;
        objetosSegurados = new LinkedList<>();
    }

    public Caso(List<ObjetoSeguro> objetosSegurados, EstadoCaso estado) {
        this.objetosSegurados = objetosSegurados;
        if (this.estado == null) {
            this.estado = estado;
        } else {
            this.estado = EstadoCaso.ESPERA;
        }
    }

    public Caso(String id, Long version, List<ObjetoSeguro> objetosSegurados, EstadoCaso estado) {
        this.objetosSegurados = objetosSegurados;
        if (estado == null) {
            this.estado = estado;
        } else {
            this.estado = EstadoCaso.ESPERA;
        }
    }

    public boolean isEmEspera() {
        return this.estado == EstadoCaso.ESPERA;
    }

    public boolean isEmProcessamento() {
        return this.estado == EstadoCaso.PROCESSAMENTO;
    }

    public boolean isProcessado() {
        return this.estado == EstadoCaso.PROCESSADO;
    }

    public void emProcessamento() {
        this.estado = EstadoCaso.PROCESSAMENTO;
    }

    public void processado() {
        this.estado = EstadoCaso.PROCESSADO;
    }

    public Long getID() {
        return id;
    }

    // Métodos
    //================================================================
    public void copyAttributes(Caso c2) {
        this.estado = c2.estado;
        this.objetosSegurados = c2.objetosSegurados;
    }

    public List<ObjetoSeguro> objetosSegurados() {
        return new ArrayList<>(this.objetosSegurados);
    }

    public void atribuirAvaliacao(ObjetoSeguro objSeguro, Avaliacao avaliacao) {
        for (ObjetoSeguro obj : this.objetosSegurados) {
            if (obj.equals(objSeguro)) {
                obj.atribuirAvaliacao(avaliacao);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringCaso = new StringBuilder("CASO:" + this.id + "\n");

        for (ObjetoSeguro obj : objetosSegurados) {
            stringCaso.append(obj).append("\n");
        }

        return stringCaso.toString();
    }

    public CasoDTO toDTO() {
        List<ObjetoSeguroDTO> objetosSegurosDTO = new LinkedList<>();

        for (ObjetoSeguro os : objetosSegurados) {
            objetosSegurosDTO.add(os.toDTO());
        }

        return new CasoDTO(objetosSegurosDTO, this.estado.toString());

    }

}
