package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by Polik on 3/11/2022
 */
// fixme: if delete/update/create a dish, cache won't get updated
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @EntityGraph(value = "dishes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select distinct r from Restaurant r left join r.dishes d on d.entryDate=?2 where r.id=?1")
    Optional<Restaurant> getWithDishes(int id, LocalDate date);

    @Cacheable(cacheNames = "restaurants-with-dishes", key = "#id")
    @EntityGraph(value = "dishes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select distinct r from Restaurant r left join r.dishes d on d.entryDate=current_date where r.id=?1")
    Optional<Restaurant> getWithDishesForToday(int id);

    Optional<Restaurant> findById(int id);

    @Override
    @Cacheable(cacheNames = "all-restaurants")
    List<Restaurant> findAll();

    @Override
    @Caching(evict = {
            @CacheEvict(allEntries = true, cacheNames = "all-restaurants"),
            @CacheEvict(allEntries = true, cacheNames = "restaurants-with-dishes")
    })
    <S extends Restaurant> S save(S entity);

    @Caching(evict = {
            @CacheEvict(allEntries = true, cacheNames = "all-restaurants"),
            @CacheEvict(key = "#id", cacheNames = "restaurants-with-dishes")
    })
    default void deleteExisted(int id) {
        BaseRepository.super.deleteExisted(id);
    }
}
