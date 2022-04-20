package org.polik.votingsystem.web;

import lombok.Getter;
import lombok.NonNull;
import org.polik.votingsystem.model.User;

/**
 * Created by Polik on 4/6/2022
 */
@Getter
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    public AuthorizedUser(@NonNull User user) {
        super(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );

        this.user = user;
    }

    public int getId() {
        return user.id();
    }
}
