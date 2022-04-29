package org.polik.votingsystem.repository;

import org.polik.votingsystem.error.ExpiredTimeException;
import org.polik.votingsystem.error.IllegalRequestDataException;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.util.DateTimeUtil;
import org.polik.votingsystem.web.GlobalExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.polik.votingsystem.util.DateTimeUtil.LAST_HOUR_FOR_REVOTING;
import static org.polik.votingsystem.util.validation.ValidationUtil.checkModification;

/**
 * Created by Polik on 3/22/2022
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    List<Vote> findAllByDate(LocalDate date);

    List<Vote> findAllByDateAndRestaurant_Id(LocalDate date, int id);

    @Transactional
    @Modifying
    @Query("update Vote v set v.restaurant.id=?1 where v.user.id=?2 and v.date=current_date")
    int update(int restaurantId, int userId);

    @Transactional
    default Vote vote(Vote vote) {
        try {
            return save(vote);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalRequestDataException(GlobalExceptionHandler.EXCEPTION_DUPLICATE_VOTE);
        }
    }

    @Transactional
    default void revote(int restaurantId, int userId) {
        if (DateTimeUtil.isBefore(LocalTime.now(), LAST_HOUR_FOR_REVOTING)) {
            checkModification(update(restaurantId, userId), restaurantId);
        } else {
            throw new ExpiredTimeException(GlobalExceptionHandler.EXCEPTION_EXPIRED_TIME);
        }
    }
}
