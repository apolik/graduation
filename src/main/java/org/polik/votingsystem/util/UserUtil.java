package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.error.IllegalRequestDataException;
import org.polik.votingsystem.model.Role;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.to.UserTo;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Polik on 4/12/2022
 */
@UtilityClass
public class UserUtil {
    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getPassword(), userTo.getEmail().toLowerCase(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static void checkModification(int count, String email) {
        if (count == 0) {
            throw new IllegalRequestDataException("User with email=" + email + " not found");
        }
    }
}
