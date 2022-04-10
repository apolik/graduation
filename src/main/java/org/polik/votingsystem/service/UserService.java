package org.polik.votingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Role;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Polik on 3/22/2022
 */
@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {
        user.getRoles().add(Role.USER);

        user = repository.save(user);
        log.info("register {}", user);

        return user;
    }

    public User getByUserName(String name) {
        log.info("getByName {}", name);
        return repository.getByUsername(name);
    }

    public User getById(int id) {
        log.info("getById {}", id);
        return repository.getById(id);
    }
}
