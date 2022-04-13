package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/22/2022
 */
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    <S extends Vote> S save(S entity);

    @Transactional
    @Modifying
    @Query("update Vote v set v.restaurant.id=?1 where v.user.id=?2 and v.date=current_date")
    int revote(int restaurantId, int userId);

    List<Vote> findAllByDate(LocalDate date);
}
