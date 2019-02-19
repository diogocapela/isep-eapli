package modelsDTO;

public class EnvolventeDTO {
    private final String titulo;
    private final String descricao;

    public EnvolventeDTO(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof EnvolventeDTO))
            return false;

        EnvolventeDTO other = (EnvolventeDTO) obj;

        return other.titulo.equals(this.titulo) && other.descricao.equals(this.descricao);
    }
}
