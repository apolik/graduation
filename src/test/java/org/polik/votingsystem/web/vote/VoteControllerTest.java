package org.polik.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.polik.votingsystem.TestUtil;
import org.polik.votingsystem.util.DateTimeUtil;
import org.polik.votingsystem.web.AbstractControllerTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.polik.votingsystem.RestaurantTestData.restaurant2;
import static org.polik.votingsystem.web.user.UserTestData.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + "/";

    @Test
    public void getConflict() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + restaurant2)
                .with(TestUtil.userHttpBasic(user)))
                .andExpect(status().isConflict());
    }

    @Test
    public void revote() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL + restaurant2)
                .with(TestUtil.userHttpBasic(user)))
                .andExpect(DateTimeUtil.isBefore(LocalTime.now(), 11) ?
                        status().isOk() :
                        status().isConflict()
                );
    }
}