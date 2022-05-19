package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 2/1/2022
 */
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Query("from Dish d where (:date is null or d.entryDate=:date) and (:restaurantId is null or d.restaurant.id=:restaurantId)")
    List<Dish> findAll(@Nullable Integer restaurantId, @Nullable LocalDate date);
}