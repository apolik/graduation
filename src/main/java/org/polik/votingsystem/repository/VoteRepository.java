package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Polik on 3/22/2022
 */
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Transactional
    @Modifying
    @Query("update Vote v set v.restaurant.id=?1 where v.user.id=?2 and v.voteDate=current_date")
    int revote(int restaurantId, int userId);

    @Query("from Vote v where v.id=?1 and v.user.id=?2")
    Optional<Vote> findById(int id, int userId);

    @Query("from Vote v where v.user.id=?1")
    @EntityGraph("restaurant")
    Set<Vote> findAll(int userId);
}
