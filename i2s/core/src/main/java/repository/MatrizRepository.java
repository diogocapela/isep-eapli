package repository;

import models.matriz.Matriz;

import java.io.Serializable;

public class MatrizRepository extends JPARepository<Matriz, Long> implements Serializable {

    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }

    public Matriz getMatrizPublicada() {
        Matriz matriz = null;

        for (Matriz m : findAll()) {
            if (m.isPublicada()) {
                return m;
            }
        }
        return matriz;
    }
}
