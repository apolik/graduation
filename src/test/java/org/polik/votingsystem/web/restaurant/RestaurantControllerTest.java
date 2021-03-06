package org.polik.votingsystem.web.restaurant;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class RestaurantControllerTest extends AbstractControllerTest {
    static final String REST_URL = RestaurantController.REST_URL + '/';

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }

    @Test
    void getAllUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MACDONALDS_ID))
                .andExpect(status().isOk())
                .andExpect(RESTAURANT_MATCHER.contentJson(macdonalds));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND_ID))
                .andExpect(status().isNotFound());
    }
}
