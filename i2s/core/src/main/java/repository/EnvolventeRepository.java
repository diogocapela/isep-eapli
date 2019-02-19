package repository;

import models.Envolvente;

import java.io.Serializable;
import java.util.List;

public class EnvolventeRepository extends JPARepository<Envolvente, Long> implements Serializable {

    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }

    public Envolvente findByTitle(String title) {
        List<Envolvente> lst = super.findAll();
        for (Envolvente c : lst) {
            if (c.toDTO().getTitulo().equals(title)) {
                return c;
            }
        }
        return null;
    }

}
