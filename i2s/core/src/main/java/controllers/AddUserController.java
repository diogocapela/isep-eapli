package controllers;

import models.User;
import repository.UserRepository;

public class AddUserController {

    private final UserRepository repository = new UserRepository();

    public User addUser(String email, String name) {
        return repository.add(new User(email, name));
    }

}
