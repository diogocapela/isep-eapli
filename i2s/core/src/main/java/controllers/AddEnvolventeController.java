package controllers;


import services.EnvolventeService;

public class AddEnvolventeController {

    private final EnvolventeService es = new EnvolventeService();

    public void addEnvolvente(String tituloEnvolvente, String descricao) {
        es.createEnvolvente(tituloEnvolvente, descricao);
    }
}
