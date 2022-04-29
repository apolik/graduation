package org.polik.votingsystem.web.dish;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.polik.votingsystem.web.dish.DishTestData.TO_MATCHER;
import static org.polik.votingsystem.web.dish.DishTestData.filteredByPredicate;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.wingstop;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/26/2022
 */
class DishControllerTest extends AbstractControllerTest {
    static final String REST_URL = DishController.REST_URL + '/';

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(vote -> vote.getDate().equals(TODAY))));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAllForTodayByRestaurantId() throws Exception {
        int id = wingstop.id();

        perform(MockMvcRequestBuilders.get(REST_URL + id))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(
                                vote -> vote.getDate().equals(TODAY) && vote.getRestaurant().id() == id
                        ))
                );
    }
}
