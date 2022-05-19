package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by Polik on 3/11/2022
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @EntityGraph("dishes")
    @Query("from Restaurant r left join r.dishes d on (?1 is null or d.entryDate=?1) order by r.name")
    List<Restaurant> findAll(@Nullable LocalDate date);

    @EntityGraph("dishes")
    Optional<Restaurant> findById(int id);
}
