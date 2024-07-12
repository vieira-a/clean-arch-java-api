package com.arch.presentation.module.user;

import com.arch.application.service.user.CreateUserService;
import com.arch.infrastructure.repository.user.UserRepository;
import com.arch.presentation.controller.user.CreateUserController;

public class UserModule {
    private final CreateUserController createUserController;

    public UserModule() {
        UserRepository userRepository = new UserRepository();
        CreateUserService createUserService = new CreateUserService(userRepository);
        this.createUserController = new CreateUserController(createUserService);
    }

    public CreateUserController getCreateUserController() {
        return createUserController;
    }
}
