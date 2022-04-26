package org.polik.votingsystem.web.dish;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.polik.votingsystem.util.DishUtil.createTo;
import static org.polik.votingsystem.web.dish.DishTestData.*;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.FRIDAYS_ID;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.wingstop;
import static org.polik.votingsystem.web.user.UserTestData.ADMIN_EMAIL;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/26/2022
 */
public class AdminDishControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminDishController.REST_URL + '/';

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(vote -> vote.getDate().equals(TODAY))));
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createWithLocation() throws Exception {
        Dish dish = getNew();

        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createTo(dish))))
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(actions);
        dish.setId(created.id());
        DISH_MATCHER.assertMatch(dish, created);
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void update() throws Exception {
        Dish dish = getUpdated();

        perform(MockMvcRequestBuilders.put(REST_URL + dish.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createTo(dish))))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void updateInvalid() throws Exception {
        DishTo dish = new DishTo(1, null, null, null);

        perform(MockMvcRequestBuilders.put(REST_URL + dish.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(dish)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void delete() throws Exception {
        int id = fridaysDish1.id();
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
    void getAllByDate() throws Exception {
        LocalDate date = LocalDate.parse("2022-03-10");

        perform(MockMvcRequestBuilders.get(REST_URL + "history?date=" + date))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(
                        filteredByPredicate(vote -> vote.getDate().equals(date))
                ));
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllByDateAndRestaurantId() throws Exception {
        LocalDate date = LocalDate.parse("2022-03-10");
        String url = REST_URL + FRIDAYS_ID + "/history?date=" + date;

        perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(
                                vote -> vote.getDate().equals(date) && vote.getRestaurant().id() == FRIDAYS_ID
                        )
                ));
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void createInvalid() throws Exception {
        DishTo newTo = new DishTo(null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
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
