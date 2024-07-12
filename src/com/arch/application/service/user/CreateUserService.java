package com.arch.application.service.user;

import com.arch.domain.entities.user.User;
import com.arch.domain.usecase.user.ICreateUserUsecase;
import com.arch.infrastructure.repository.user.UserRepository;

public class CreateUserService implements ICreateUserUsecase {
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.createUser(user);
    }
}
