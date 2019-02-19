package controllers;

import models.Envolvente;
import repository.EnvolventeRepository;

import java.util.LinkedList;
import java.util.List;

public class ListEnvolventesController {

    private final EnvolventeRepository repository = new EnvolventeRepository();

    public List<Envolvente> listEnvolventes() {
        List<Envolvente> result = new LinkedList<>();

        for (Envolvente e : repository.findAll()) {
            repository.detach(e);
            result.add(e);
        }

        return result;
    }

}
