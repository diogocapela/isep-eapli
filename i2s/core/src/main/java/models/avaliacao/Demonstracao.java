package models.avaliacao;

import javax.persistence.Embeddable;

@Embeddable
public class Demonstracao {

    // Variáveis de Instância
    //================================================================
    private String demonstracao;

    // Construtores
    //================================================================
    protected Demonstracao() {
    }

    public Demonstracao(String demonstracao) {
        this.demonstracao = demonstracao;
    }

    // Métodos
    //================================================================

    @Override
    public String toString() {
        return this.demonstracao;
    }


}
