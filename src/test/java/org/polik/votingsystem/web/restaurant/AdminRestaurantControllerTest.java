package org.polik.votingsystem.web.restaurant;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.polik.votingsystem.util.RestaurantUtil.createTo;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.ADMIN_EMAIL;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class AdminRestaurantControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(RESTAURANTS_FOR_CURRENT_DATE));
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllByDate() throws Exception {
        LocalDate date = LocalDate.now();

        perform(MockMvcRequestBuilders.get(REST_URL + "history?date=" + date))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(RESTAURANTS_FOR_CURRENT_DATE));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createWithLocation() throws Exception {
        Restaurant restaurant = getNew();

        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant)))
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANT_MATCHER.readFromJson(actions);
        restaurant.setId(created.id());
        RESTAURANT_MATCHER.assertMatch(restaurant, created);
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

    @Test
    void getAllUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MACDONALDS_ID))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(createTo(macdonalds)));
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getUnprocessableEntity() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND_ID))
                .andExpect(status().isUnprocessableEntity());
    }
}
