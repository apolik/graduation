package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by Polik on 3/11/2022
 */
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "restaurants")
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @EntityGraph(value = "dishes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select distinct r from Restaurant r left join r.dishes d on (?2 is null or d.entryDate=?2) where r.id=?1")
    Optional<Restaurant> getWithDishes(int id, @Nullable LocalDate date);

    @Cacheable
    @EntityGraph(value = "dishes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select distinct r from Restaurant r left join r.dishes d on d.entryDate=current_date where r.id=?1")
    Optional<Restaurant> getWithDishesForToday(int id);

    Optional<Restaurant> findById(int id);

    @Override
    @CacheEvict(allEntries = true)
    <S extends Restaurant> S save(S entity);

    @CacheEvict(allEntries = true)
    default void deleteExisted(int id) {
        BaseRepository.super.deleteExisted(id);
    }
}
