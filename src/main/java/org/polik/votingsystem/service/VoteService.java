package org.polik.votingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.repository.VoteRepository;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.util.DateTimeUtil;
import org.polik.votingsystem.util.VoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

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

    public List<VoteTo> getAll() {
        log.info("getAll");
        return VoteUtil.getTos(voteRepository.findAllByDate(LocalDate.now()));
    }

    public List<VoteTo> getAll(LocalDate date) {
        log.info("getAll {}", date);
        return VoteUtil.getTos(voteRepository.findAllByDate(date));
    }

    @Transactional
    public Vote vote(int restaurantId, User user) {
        log.info("vote {} {}", restaurantId, user);
        Assert.notNull(user, "User cannot be null");
        Vote vote = new Vote();

        vote.setUser(user);
        vote.setRestaurant(
                restaurantRepository.getById(restaurantId)
        );

        return voteRepository.save(vote);
    }

    public void revote(int restaurantId, int userId) {
        log.info("revote {} {}", restaurantId, userId);
        DateTimeUtil.isTimeForRevoteExpired();
        voteRepository.revote(restaurantId, userId);
    }
}
