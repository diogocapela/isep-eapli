package repository;

import models.Cobertura;

import java.io.Serializable;
import java.util.List;

public class CoberturaRepository extends JPARepository<Cobertura, Long> implements Serializable {
    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }


    public Cobertura findByTitle(String title) {
        List<Cobertura> lst = super.findAll();
        for (Cobertura c : lst) {
            if (c.toDTO().getTitulo().equals(title)) {
                return c;
            }
        }
        return null;
    }
}
