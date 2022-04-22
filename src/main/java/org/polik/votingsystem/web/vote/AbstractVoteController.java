package org.polik.votingsystem.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.repository.VoteRepository;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 4/11/2022
 */
@Slf4j
public abstract class AbstractVoteController {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<VoteTo> getAll() {
        log.info("getAll");
        return VoteUtil.getTos(repository.findAllByDate(LocalDate.now()));
    }

    public List<VoteTo> getAllByDate(LocalDate date) {
        log.info("getAll");
        return VoteUtil.getTos(repository.findAllByDate(date));
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

        return repository.save(vote);
    }

    public void revote(int restaurantId, int userId) {
        log.info("revote {} {}", restaurantId, userId);
        repository.revote(restaurantId, userId);
    }
}
