package services;

import models.Envolvente;
import repository.EnvolventeRepository;

import java.io.Serializable;

public class EnvolventeService implements Serializable {
    private final EnvolventeRepository er = new EnvolventeRepository();

    public EnvolventeService() {
        /**/
    }

    public Envolvente createEnvolvente(String titulo, String descricao) {
        Envolvente envolvente = er.findByTitle(titulo);

        if (envolvente == null) {
            //não existe, criar
            envolvente = new Envolvente(titulo, descricao);
            er.add(envolvente);
        }

        return envolvente;
    }

    public boolean existsOnDbById(Long id) {
        return er.findById(id) != null;
    }

    public boolean existsOnDbByTitle(String title) {
        return er.findByTitle(title) != null;
    }

    public Envolvente getEnvolventeByTitle(String title) {
        return er.findByTitle(title);
    }

    public Envolvente getEnvolventeById(Long id) {
        return er.findById(id);
    }
}
