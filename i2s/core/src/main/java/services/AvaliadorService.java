package services;

import javafx.util.Pair;
import models.Cobertura;
import models.Envolvente;
import models.FatorRisco;
import models.Rating;
import models.avaliacao.*;
import modelsDTO.CelulaBaseDTO;
import modelsDTO.CelulaDetalhadaDTO;
import modelsDTO.MatrizDTO;
import settings.Settings;

import java.util.LinkedList;
import java.util.List;

public class AvaliadorService implements AvaliadorServiceInterface, Runnable {

    private Caso caso;
    private MatrizDTO matriz;
    private GeoReferenceService geoReferenceService;

    public AvaliadorService(Caso caso, MatrizDTO matriz, GeoReferenceService geoReferenceService) {
        this.caso = caso;
        this.matriz = matriz;
        this.geoReferenceService = geoReferenceService;
    }

    @Override
    public void run() {
        avaliaCaso(this.caso, this.matriz, this.geoReferenceService);
        //System.out.println(caso);
    }

    @Override
    public Boolean avaliaCaso(Caso caso, MatrizDTO matriz, GeoReferenceService geoReferenceService) {
        Boolean result = null;

        caso.emProcessamento();

        List<ObjetoSeguro> objetoSeguros = caso.objetosSegurados();

        for (ObjetoSeguro obj : objetoSeguros) {
            caso.atribuirAvaliacao(obj, avaliaObjeto(obj, matriz, geoReferenceService));
        }

        caso.processado();

        return result;
    }

    private Avaliacao avaliaObjeto(ObjetoSeguro obj, MatrizDTO matriz, GeoReferenceService geoReferenceService) {
        Avaliacao avaliacao;
        List<Cobertura> coberturasDoObj = obj.coberturasDoObjeto();
        Pair<Double, Double> coordenadas = obj.coordenadas();
        List<CelulaBaseDTO> celulasDetalhadasByCobertura;
        List<AvaliacaoCelular> avaliacoesCelulares = new LinkedList<>();
        Integer valorGeo;
        Rating escalaGeo;
        AvaliacaoCelular avaliacaoCelular;

        for (Cobertura cob : coberturasDoObj) {
            celulasDetalhadasByCobertura = matriz.getCelulasByCoberturaToDTO(cob);
            for (CelulaBaseDTO celulaDTO : celulasDetalhadasByCobertura) {
                CelulaDetalhadaDTO celulaTmpDTO = (CelulaDetalhadaDTO) celulaDTO;
                escalaGeo = geoReferenceService.getGeoRating(
                    new Envolvente(celulaTmpDTO.getEnvolventeDTO().getTitulo(), celulaTmpDTO.getEnvolventeDTO().getDescricao()),
                    new FatorRisco(celulaTmpDTO.getFatorRiscoDTO().getTitulo(), celulaTmpDTO.getFatorRiscoDTO().getDescricao()),
                    coordenadas.getKey(),
                    coordenadas.getValue()
                );
                valorGeo = getValorEscala(escalaGeo, celulaTmpDTO);
                avaliacaoCelular = getAvaliacaoCelular(celulaDTO, valorGeo);
                avaliacoesCelulares.add(avaliacaoCelular);
            }
        }

        avaliacao = getAvaliacaoObjeto(avaliacoesCelulares);

        return avaliacao;
    }


    private AvaliacaoCelular getAvaliacaoCelular(CelulaBaseDTO celulaDTO, Integer valorGeo) {
        CelulaDetalhadaDTO celulaTmpDTO = (CelulaDetalhadaDTO) celulaDTO;
        Integer peso = celulaTmpDTO.getPeso().getPeso();
        Integer scoreMaximo = celulaTmpDTO.getEscala().getEscalaElevada();

        return new AvaliacaoCelular(peso, valorGeo, scoreMaximo);
    }

    /**
     * Método que devolve o valor da escala referente à classificação obtida
     * pelo GeoReferenceService
     */
    private Integer getValorEscala(Rating geoRating, CelulaDetalhadaDTO celulaDTO) {
        if (geoRating == null) {
            if (celulaDTO.getNecessidade().getNecessidade().equalsIgnoreCase(Settings.FACULTATIVO)) {
                return 0;
            } else if (celulaDTO.getContribuicao().getContribuicao().equalsIgnoreCase(Settings.POSITIVA)) {
                return celulaDTO.getEscala().getEscalaBaixa();
            } else if (celulaDTO.getContribuicao().getContribuicao().equalsIgnoreCase(Settings.NEGATIVA)) {
                return celulaDTO.getEscala().getEscalaElevada();
            }
        } else {
            if (geoRating.equals(Rating.LOW)) {
                return celulaDTO.getEscala().getEscalaBaixa();
            } else if (geoRating.equals(Rating.MEDIUM)) {
                return celulaDTO.getEscala().getEscalaMedia();
            } else {
                return celulaDTO.getEscala().getEscalaElevada();
            }
        }

        return celulaDTO.getEscala().getEscalaElevada();
    }

    private Avaliacao getAvaliacaoObjeto(List<AvaliacaoCelular> avaliacoesCelulares) {
        Integer scoreMaximo = 0;
        Integer scoreObtido = 0;
        StringBuilder demontracaoObtida = new StringBuilder("(");
        StringBuilder demontracaoMaxima = new StringBuilder("(");

        for (AvaliacaoCelular avCel : avaliacoesCelulares) {
            scoreMaximo += avCel.getScoreMaximo();
            scoreObtido += avCel.getScoreObtido();
            demontracaoObtida.append(avCel.getDemonstracaoObtida()).append("+");
            demontracaoMaxima.append(avCel.getDemonstracaoMaxima()).append("+");
        }
        demontracaoObtida.deleteCharAt(demontracaoObtida.length() - 1).append(")");
        demontracaoMaxima.deleteCharAt(demontracaoObtida.length() - 1).append(")");

        Integer indiceRisco = null;
        if (scoreMaximo != 0) {
            indiceRisco = Math.round(((float) scoreObtido / (float) scoreMaximo) * 100);
        }

        return new Avaliacao(
            new IndiceRisco(indiceRisco),
            new Demonstracao(demontracaoObtida + " / " + demontracaoMaxima)
        );
    }

}
