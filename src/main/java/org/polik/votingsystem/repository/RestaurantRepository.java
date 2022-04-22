package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Polik on 3/11/2022
 */
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

//    @Query("select distinct r from Restaurant r left join fetch r.dishes d where d.date=current_date")
    @Query("from Restaurant r join fetch r.dishes d where d.date=current_date order by r.name")
    Set<Restaurant> findAllForCurrentDate();
}
