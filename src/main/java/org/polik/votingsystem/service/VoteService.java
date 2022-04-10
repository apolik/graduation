package org.polik.votingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.repository.VoteRepository;
import org.polik.votingsystem.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by Polik on 4/5/2022
 */
@Slf4j
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Vote vote(int restaurantId, User user) {
        Assert.notNull(user, "User cannot be null");
        Vote vote = new Vote();

        vote.setUser(user);
        vote.setRestaurant(
                restaurantRepository.getById(restaurantId)
        );
        log.info("vote {} {}", restaurantId, user);

        return voteRepository.save(vote);
    }

    public void revote(int restaurantId, int userId) {
        TimeUtil.isTimeForRevoteExpired();
        voteRepository.revote(restaurantId, userId);
    }
}
