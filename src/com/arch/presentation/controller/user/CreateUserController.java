package com.arch.presentation.controller.user;

import com.arch.application.service.user.CreateUserService;
import com.arch.domain.entities.user.User;

public class CreateUserController {
    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    public void handle(String firstName, String lastName, String email) {
        User user = new User(firstName, lastName, email);
        createUserService.addUser(user);
    }

    public String getRoute() {
        return "/api/v1/users";
    }
}
