package repository;

import models.User;

import java.io.Serializable;

public class UserRepository extends JPARepository<User, Long> implements Serializable {

    @Override
    protected String persistenceUnitName() {
        return "DATABASE_01";
    }

}
