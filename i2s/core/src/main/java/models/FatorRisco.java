package models;

import modelsDTO.FatorRiscoDTO;
import services.FatorRiscoService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class FatorRisco implements Serializable {

    // Variáveis de Instância
    //================================================================

    private final FatorRiscoService fs = new FatorRiscoService();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    @Column(nullable = false, unique = true)
    private String titulo;
    @Column(nullable = false)
    private String descricao;

    // Construtores
    //================================================================

    public FatorRisco() {
    }

    public FatorRisco(String titulo, String descricao) {
        if (titulo == null || descricao == null) {
            throw new IllegalArgumentException();
        }
        if (fs.existsOnDbByTitle(titulo)) {
            FatorRisco t = fs.getFatorRiscoByTitle(titulo);
            this.titulo = t.titulo;
            this.descricao = t.descricao;
            this.id = t.id;
            this.version = t.version;
        } else {
            this.titulo = titulo;
            this.descricao = descricao;
        }
    }

    // Métodos
    //================================================================

    @Override
    public String toString() {
        return "FatorRisco{" +
            "id=" + id +
            ", titulo='" + titulo + '\'' +
            ", descricao='" + descricao + '\'' +
            ", version=" + version +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FatorRisco other = (FatorRisco) obj;
        return Objects.equals(this.titulo, other.titulo);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.titulo);
        return hash;
    }


    public FatorRiscoDTO toDTO() {
        return new FatorRiscoDTO(this.titulo, this.descricao);
    }
}
