package models.avaliacao;

public class AvaliacaoCelular {

    private final Integer scoreObtido;
    private final Integer scoreMaximo;
    private final String demonstracaoObtida;
    private final String demonstracaoMaxima;

    public AvaliacaoCelular(Integer peso, Integer valorEscalaObtido, Integer valorEscalaMaxima) {
        scoreObtido = peso * valorEscalaObtido;
        scoreMaximo = peso * valorEscalaMaxima;
        demonstracaoObtida = String.format("(%d x %d)", peso, valorEscalaObtido);
        demonstracaoMaxima = String.format("(%d x %d)", peso, valorEscalaMaxima);
    }

    public Integer getScoreObtido() {
        return scoreObtido;
    }

    public Integer getScoreMaximo() {
        return scoreMaximo;
    }

    public String getDemonstracaoObtida() {
        return demonstracaoObtida;
    }

    public String getDemonstracaoMaxima() {
        return demonstracaoMaxima;
    }


}
