package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Polik on 2/1/2022
 */
@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("from Dish d where d.restaurant.id=?1 and d.date=current_date")
    List<Dish> findAllByRestaurantId(int restaurantId);

    @Override
    @Query("from Dish d where d.date=current_date")
    List<Dish> findAll();

    @Query("from Dish d where d.id=?1 and d.restaurant.id=?2")
    Dish findByIdAndRestaurantId(int id, int restaurantId);

    @Override
    @Transactional
    <S extends Dish> S save(S entity);



    @Transactional
    void deleteById(int id);
}
