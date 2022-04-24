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

//    @Query("select r from Restaurant r join r.votes v where v.date=current_date")
//    @Query("from Restaurant r left join  r.votes v where v.date=current_date")
//    @Query("select r from Restaurant r join fetch Vote v on v.date=current_date")
//    Set<Restaurant> findAllForCurrentDate();

//    @Query("from Restaurant r left join fetch r.votes as v on v.date=current_date")

    @Query("from Restaurant r left outer join fetch r.votes as v where v.date=current_date")
    Set<Restaurant> findAllForCurrentDate();
}
