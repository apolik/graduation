package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 2/1/2022
 */
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("from Dish d where d.creationDate=current_date")
    List<Dish> findAllForToday();

    @Query("from Dish d where d.creationDate=current_date and d.restaurant.id=?1")
    List<Dish> findAllForTodayByRestaurantId(int restaurantId);

    List<Dish> findAllByCreationDate(LocalDate date);

    List<Dish> findAllByCreationDateAndRestaurantId(LocalDate date, int restaurantId);
}