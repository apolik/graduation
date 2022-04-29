package org.polik.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.restaurant.RestaurantTestData;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.mockStatic;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.ADMIN_EMAIL;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.polik.votingsystem.web.vote.VoteTestData.TO_MATCHER;
import static org.polik.votingsystem.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class AdminVoteControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminVoteController.REST_URL + '/';
    static final LocalTime BEFORE_11AM = LocalTime.parse("10:59:59");
    static final LocalTime AFTER_11AM = LocalTime.parse("11:00:01");

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TO_MATCHER.contentJson(VOTES_FOR_CURRENT_DATE));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllByDate() throws Exception {
        LocalDate date = userVote2.getDate();

        perform(MockMvcRequestBuilders.get(REST_URL + "history?date=" + date))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TO_MATCHER.contentJson(filteredByPredicate(vote -> vote.getDate().equals(date))));
    }

    @Test
    void voteUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + MACDONALDS_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void revoteBefore11AM() throws Exception {
        try (MockedStatic<LocalTime> mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(BEFORE_11AM);

            perform(MockMvcRequestBuilders.put(REST_URL + FRIDAYS_ID))
                    .andExpect(status().isNoContent());
        }
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void revoteAfter11AM() throws Exception {
        try (MockedStatic<LocalTime> mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(AFTER_11AM);

            perform(MockMvcRequestBuilders.put(REST_URL + WINGSTOP_ID))
                    .andExpect(status().isConflict());
        }
    }

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void getAllForTodayByRestaurantId() throws Exception {
        int restaurantId = RestaurantTestData.FRIDAYS_ID;

        perform(MockMvcRequestBuilders.get(REST_URL + restaurantId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TO_MATCHER.contentJson(
                        filteredByPredicate(vote -> vote.getRestaurantId() == restaurantId && vote.getDate().equals(TODAY))
                ));
    }
}
