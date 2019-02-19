package modelsDTO;

import java.util.List;

public class ObjetoSeguroDTO {
    private final String descricao;
    private final Double latitude;
    private final Double longitude;
    private final List<CoberturaDTO> coberturas;
    private final AvaliacaoDTO avaliacao;

    public ObjetoSeguroDTO(String descricao, Double latitude, Double longitude, List<CoberturaDTO> coberturas, AvaliacaoDTO avaliacao) {
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.coberturas = coberturas;
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<CoberturaDTO> getCoberturas() {
        return coberturas;
    }

    public AvaliacaoDTO getAvaliacao() {
        return avaliacao;
    }
}
