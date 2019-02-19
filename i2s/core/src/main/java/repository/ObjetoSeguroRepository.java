package repository;

import models.avaliacao.ObjetoSeguro;

import java.io.Serializable;

public class ObjetoSeguroRepository extends JPARepository<ObjetoSeguro, Long> implements Serializable {

    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }

}
