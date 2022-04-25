package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Polik on 3/11/2022
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("from Restaurant r left join r.votes v on v.date=current_date")
    @EntityGraph(value = "graph.Parent.children")
    Set<Restaurant> findAllForCurrentDate();

    @Query("from Restaurant r left join r.votes v on v.date=?1")
    @EntityGraph(value = "graph.Parent.children")
    Set<Restaurant> findAllByDate(LocalDate date);
}
