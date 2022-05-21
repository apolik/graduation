package org.polik.votingsystem.web;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Created by Polik on 4/15/2022
 */
@UtilityClass
public class SecurityUtil {
    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authId() {
        return get().id();
    }
}
