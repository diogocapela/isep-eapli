package controllers;

import models.Cobertura;
import models.avaliacao.Caso;
import models.avaliacao.ObjetoSeguro;
import models.matriz.Matriz;
import modelsDTO.CoberturaDTO;
import modelsDTO.ObjetoSeguroDTO;
import repository.CasoRepository;
import repository.MatrizRepository;
import repository.ObjetoSeguroRepository;
import services.AvaliadorService;
import services.GeoReferenceService;
import utils.JSONExporter;
import utils.JSONImporter;

import java.util.LinkedList;
import java.util.List;


public class ProcessCasoFromJSONController {
    private final JSONImporter jsonImporter = new JSONImporter();
    private final CasoRepository casoRepository = new CasoRepository();
    private final ObjetoSeguroRepository objetoSeguroRepository = new ObjetoSeguroRepository();
    private final JSONExporter jsonExporter = new JSONExporter();
    private MatrizRepository matrizRepository = new MatrizRepository();
    private GeoReferenceService geoReferenceService;
    private AvaliadorService avaliadorService;

    public Long processCasoFromJSON(String filePathInput, String filePathOutput) {
        // IMPORTAR
        Caso caso = null;
        try {
            caso = jsonImporter.importCaso(filePathInput);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        Caso casoTmp = new Caso();
        if (caso == null) {
            return null;
        }
        for (ObjetoSeguroDTO os : caso.toDTO().getObjetosSegurados()) {
            ObjetoSeguro osTmp = new ObjetoSeguro();
            objetoSeguroRepository.add(osTmp);
            List<Cobertura> coberturas = new LinkedList<>();
            for (CoberturaDTO cDTO : os.getCoberturas()) {
                coberturas.add(new Cobertura(cDTO.getTitulo(), cDTO.getDescricao()));
            }
            ObjetoSeguro objetoSeguro = new ObjetoSeguro(os.getDescricao(), os.getLatitude(), os.getLatitude(), coberturas);
            osTmp.copyAttributes(objetoSeguro);
            objetoSeguroRepository.update(osTmp);
        }
        casoRepository.add(casoTmp);
        casoTmp.copyAttributes(caso);
        casoRepository.update(casoTmp);

        // AVALIAR
        Matriz publicada = matrizRepository.getMatrizPublicada();
        if (publicada == null) {
            return null;
        }
        matrizRepository.detach(publicada);

        geoReferenceService = new GeoReferenceService();

        caso.emProcessamento();
        casoRepository.update(caso);
        avaliadorService = new AvaliadorService(casoTmp, publicada.toDTO(), geoReferenceService);
        avaliadorService.run();
        caso.processado();
        casoRepository.update(caso);

        // EXPORTAR
        try {
            jsonExporter.exportCaso(caso, filePathOutput);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        return casoTmp.getID();
    }
}
