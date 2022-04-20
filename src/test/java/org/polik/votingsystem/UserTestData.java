package org.polik.votingsystem;

import org.polik.votingsystem.model.Role;
import org.polik.votingsystem.model.User;

import java.util.Set;

/**
 * Created by Polik on 4/14/2022
 */
public class UserTestData {
    public static final User admin = new User(1, "admin", "{noop}admin", "admin@gmail.com", Set.of(Role.ADMIN));
    public static final User kanye = new User(2, "ye", "kanye", "ye@gmail.com", Set.of(Role.USER));

}
