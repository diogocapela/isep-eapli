package modelsDTO;

import java.util.List;

public class CasoDTO {
    private List<ObjetoSeguroDTO> objetosSegurados;
    private String estado;

    public CasoDTO(List<ObjetoSeguroDTO> objetosSegurados, String estado) {
        this.objetosSegurados = objetosSegurados;
        this.estado = estado;
    }

    public List<ObjetoSeguroDTO> getObjetosSegurados() {
        return objetosSegurados;
    }

    public String getEstado() {
        return estado;
    }
}
