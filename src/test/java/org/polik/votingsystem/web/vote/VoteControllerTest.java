package org.polik.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.mockito.Mockito.mockStatic;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;
import static org.polik.votingsystem.web.user.UserTestData.TEST_USER_EMAIL;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.polik.votingsystem.web.vote.VoteTestData.TO_MATCHER;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class VoteControllerTest extends AbstractControllerTest {

    static final String REST_URL = VoteController.REST_URL + "/";
    static final LocalTime BEFORE_11AM = LocalTime.parse("10:59:59");
    static final LocalTime AFTER_11AM = LocalTime.parse("11:00:01");

    @Test
    @WithUserDetails(TEST_USER_EMAIL)
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + KROGER_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void voteAgain() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + MACDONALDS_ID))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void voteUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + KROGER_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void revoteBefore11AM() throws Exception {
        try (MockedStatic<LocalTime> mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(BEFORE_11AM);

            perform(MockMvcRequestBuilders.put(REST_URL + FRIDAYS_ID))
                    .andExpect(status().isNoContent());
        }
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void revoteAfter11AM() throws Exception {
        try (MockedStatic<LocalTime> mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(AFTER_11AM);

            perform(MockMvcRequestBuilders.put(REST_URL + WINGSTOP_ID))
                    .andExpect(status().isConflict());
        }
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(VoteTestData.VOTES_FOR_CURRENT_DATE));
    }
}