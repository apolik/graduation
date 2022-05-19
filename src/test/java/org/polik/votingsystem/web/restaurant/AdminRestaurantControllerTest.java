package org.polik.votingsystem.web.restaurant;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.ADMIN_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class AdminRestaurantControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createWithLocation() throws Exception {
        var restaurant = getNew();

        var resultActions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant)))
                .andExpect(status().isCreated());

        var created = RESTAURANT_MATCHER.readFromJson(resultActions);
        restaurant.setId(created.id());
        RESTAURANT_MATCHER.assertMatch(restaurant, created);
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void update() throws Exception {
        var updated = getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL + updated.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createInvalid() throws Exception {
        var restaurant = new Restaurant(null, null);

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND_ID))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MACDONALDS_ID))
                .andExpect(status().isNoContent());
    }
}
