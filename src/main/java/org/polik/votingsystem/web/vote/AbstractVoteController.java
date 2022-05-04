package org.polik.votingsystem.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.NotFoundException;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.repository.VoteRepository;
import org.polik.votingsystem.to.VoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.polik.votingsystem.util.VoteUtil.createTo;
import static org.polik.votingsystem.util.VoteUtil.getTos;

/**
 * Created by Polik on 4/11/2022
 */
@Slf4j
public abstract class AbstractVoteController {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<VoteTo> getAllForToday() {
        log.info("getAllForToday");
        return getTos(repository.findAllForToday());
    }

    public List<VoteTo> getAllForTodayByRestaurantId(int restaurantId) {
        log.info("getAllForTodayByRestaurantId {}", restaurantId);
        return getTos(repository.findAllByVoteDateAndRestaurant_Id(LocalDate.now(), restaurantId));
    }

    public List<VoteTo> getAllByDate(LocalDate date) {
        log.info("getAllByDate {}", date);
        return getTos(repository.findAllByVoteDate(date));
    }

    @Transactional
    public VoteTo vote(int restaurantId, User user) {
        log.info("vote {}", restaurantId);
        Vote vote = new Vote(user, getRestaurant(restaurantId));
        return createTo(repository.vote(vote));
    }

    public void revote(int restaurantId, int userId) {
        log.info("revote {} {}", restaurantId, userId);
        repository.revote(restaurantId, userId);
    }

    private Restaurant getRestaurant(int restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NotFoundException("No such restaurant with id: " + restaurantId)
                );
    }
}
