package org.polik.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.polik.votingsystem.web.json.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.mockito.Mockito.mockStatic;
import static org.polik.votingsystem.util.DateTimeUtil.DEADLINE_FOR_REVOTING;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.WINGSTOP_ID;
import static org.polik.votingsystem.web.user.UserTestData.TEST_USER_EMAIL;
import static org.polik.votingsystem.web.user.UserTestData.USER_EMAIL;
import static org.polik.votingsystem.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Polik on 4/23/2022
 */
class VoteControllerTest extends AbstractControllerTest {

    static final String REST_URL = VoteController.REST_URL + "/";
    static final LocalTime BEFORE_DEADLINE = DEADLINE_FOR_REVOTING.minusSeconds(1);
    static final LocalTime AFTER_DEADLINE = DEADLINE_FOR_REVOTING.plusSeconds(1);

    @Test
    @WithUserDetails(TEST_USER_EMAIL)
    void create() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VoteTestData.getNew())))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails(TEST_USER_EMAIL)
    void createInvalid() throws Exception {
        var invalidVote = new VoteTo(null, null, null);

        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalidVote)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + userVote2.id()))
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHER.contentJson(userVote2));
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void createAgain() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VoteTestData.getNew())))
                .andExpect(status().isConflict());
    }

    @Test
    void createUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(VoteTestData.getNew())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void revoteBeforeDeadline() throws Exception {
        try (var mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(BEFORE_DEADLINE);
            var url = String.format("%s?restaurantId=%d", REST_URL, WINGSTOP_ID);

            perform(MockMvcRequestBuilders.patch(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(VoteTestData.getUpdated())))
                    .andExpect(status().isNoContent());
        }
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void revoteAfterDeadline() throws Exception {
        try (var mockedTime = mockStatic(LocalTime.class)) {
            mockedTime.when(LocalTime::now).thenReturn(AFTER_DEADLINE);
            var url = String.format("%s?restaurantId=%d", REST_URL, WINGSTOP_ID);

            perform(MockMvcRequestBuilders.patch(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(VoteTestData.getUpdated())))
                    .andExpect(status().isConflict());
        }
    }

    @Test
    @WithUserDetails(USER_EMAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(USER_VOTES));
    }
}