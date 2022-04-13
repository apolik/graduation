package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Polik on 3/11/2022
 */
@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    List<Restaurant> findAll();

    @Transactional
    @Modifying
    @Query("delete from Restaurant r where r.id=?1")
    int deleteById(int id);

    @Override
    @Transactional
    <S extends Restaurant> S save(S entity);
}
