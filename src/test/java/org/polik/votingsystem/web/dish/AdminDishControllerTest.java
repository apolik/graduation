package org.polik.votingsystem.web.dish;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.polik.votingsystem.util.DishUtil.createTo;
import static org.polik.votingsystem.web.dish.DishTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.ADMIN_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/26/2022
 */
class AdminDishControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminDishController.REST_URL + '/';

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createWithLocation() throws Exception {
        var dish = getNew();
        var resultActions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createTo(dish))))
                .andExpect(status().isCreated());

        var created = DISH_MATCHER.readFromJson(resultActions);
        dish.setId(created.id());
        DISH_MATCHER.assertMatch(dish, created);
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void update() throws Exception {
        var dish = getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL + dish.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createTo(dish))))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void updateInvalid() throws Exception {
        var dish = new DishTo(1, null, null, null, null);

        perform(MockMvcRequestBuilders.put(REST_URL + dish.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(dish)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void delete() throws Exception {
        var id = fridaysDish1.id();
        perform(MockMvcRequestBuilders.delete(REST_URL + id))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND_ID))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createInvalid() throws Exception {
        var newTo = new DishTo(null, null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
