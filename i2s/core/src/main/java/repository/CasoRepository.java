package repository;

import models.avaliacao.Caso;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class CasoRepository extends JPARepository<Caso, Long> implements Serializable {

    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }

    public List<Caso> fetchCasesNotProcessing() {
        List<Caso> result = new LinkedList<>();

        for (Caso cTmp : findAll()) {
            if (!cTmp.isEmProcessamento()) {
                result.add(cTmp);
            }
        }

        return result;
    }

}
