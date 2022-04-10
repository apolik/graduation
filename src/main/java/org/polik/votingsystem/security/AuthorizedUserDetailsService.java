package org.polik.votingsystem.security;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by Polik on 3/21/2022
 */
@Slf4j
@Component
public class AuthorizedUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public AuthorizedUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByUsername(username);
        Assert.notNull(user, "No such user with username: " + username);
        log.info("loadUserByUsername {}", username);

        return AuthorizedUserFactory.create(user);
    }
}
