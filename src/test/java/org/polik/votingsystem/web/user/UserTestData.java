package org.polik.votingsystem.web.user;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.MatcherFactory;
import org.polik.votingsystem.model.Role;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.util.JsonUtil;

import java.util.Collections;

/**
 * Created by Polik on 4/14/2022
 */
@UtilityClass
public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password");

    public static final User admin = new User(1, "admin", "admin", "admin@gmail.com", Collections.singleton(Role.ADMIN));
    public static final User user = new User(2, "ye", "kanye", "ye@gmail.com", Collections.singleton(Role.USER));
    public static final User superUser = new User(3, "superuser", "super", "super@gmail.com", Collections.singleton(Role.USER));
    public static final User guest = new User(4, "guest", "guest", "guest@gmail.com", Collections.singleton(Role.USER));
    public static final User test = new User(5, "test", "testuser", "test@gmail.com", Collections.singleton(Role.USER));

    public static final String ADMIN_EMAIL = "admin@gmail.com";
    public static final String USER_EMAIL = "ye@gmail.com";
    public static final String SUPERUSER_EMAIL = "super@gmail.com";
    public static final String GUEST_EMAIL = "guest@gmail.com";
    public static final String TEST_USER_EMAIL = "test@gmail.com";

    public static final int ADMIN_ID = 1;
    public static final int USER_ID = 2;
    public static final int SUPERUSER_ID = 3;
    public static final int TEST_USER_ID = 4;
    public static final int GUEST_ID = 5;
    public static final int NOT_FOUND = 1017;

    public static User getNew() {
        return new User(null, "New", "newPass", "new@gmail.com", Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(2, "updated", "updatedpass", "updated@gmail.com", Collections.singleton(Role.USER));
    }

    public static String jsonWithPassword(User user, String pass) {
        return JsonUtil.writeAdditionProps(user, "password", pass);
    }
}
