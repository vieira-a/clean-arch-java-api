package com.arch.infrastructure.repository.user;

import com.arch.application.service.user.IUserService;
import com.arch.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserService {
    private final List<User> users = new ArrayList<>();

    @Override
    public void createUser(User user) {
        users.add(user);
    }
}
