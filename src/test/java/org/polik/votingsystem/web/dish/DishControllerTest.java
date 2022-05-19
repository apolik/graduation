package org.polik.votingsystem.web.dish;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.polik.votingsystem.util.DishUtil.getTos;
import static org.polik.votingsystem.web.dish.DishTestData.*;
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
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(getTos(ALL_DISHES)));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAllForTodayByRestaurantId() throws Exception {
        var id = wingstop.id();
        var url = String.format("%s?date=%s&restaurantId=%d", REST_URL, TODAY, id);

        perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(
                                vote -> vote.getEntryDate().equals(TODAY) && vote.getRestaurant().id() == id
                        ))
                );
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void get() throws Exception {
        var id = krogerDish4.id();

        perform(MockMvcRequestBuilders.get(REST_URL + id))
                .andExpect(status().isOk())
                .andExpect(DISH_MATCHER.contentJson(getById(id)));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getNotFound() throws Exception {
        var id = Objects.hash(krogerDish4.id());

        perform(MockMvcRequestBuilders.get(REST_URL + id))
                .andExpect(status().isNotFound());
    }
}
