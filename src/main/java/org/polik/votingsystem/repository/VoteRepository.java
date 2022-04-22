package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.util.DateTimeUtil;
import org.polik.votingsystem.util.exception.TimeForRevotingIsExpiredException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Polik on 3/22/2022
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    List<Vote> findAllByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("update Vote v set v.restaurant.id=?1 where v.user.id=?2 and v.date=current_date")
    void update(int restaurantId, int userId);

    default void revote(int restaurantId, int userId) {
        if (DateTimeUtil.isBefore(LocalTime.now(), 11)) {
            update(restaurantId, userId);
        } else {
            throw new TimeForRevotingIsExpiredException("You cannot change your mind after 11 PM");
        }
    }
}
