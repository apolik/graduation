package org.polik.votingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.DishRepository;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.util.ValidationUtil;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.polik.votingsystem.util.DishUtil.*;

/**
 * Created by Polik on 4/9/2022
 */
@Slf4j
@Service
public class DishService {
    private final DishRepository repository;
    private final RestaurantRepository restaurantRepository;

    public DishService(DishRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<DishTo> getAll(int restaurantId) {
        log.info("getAll {}", restaurantId);
        return getTos(repository.findAllByRestaurantId(restaurantId));
    }

    public List<DishTo> getAll() {
        log.info("getAll");
        return getTos(repository.findAll());
    }

    @Transactional
    public DishTo create(DishTo dishTo, int restaurantId) {
        Dish dish = fromTo(dishTo);

        dish.setRestaurant(getRestaurantById(restaurantId));
        log.info("create {} {}", dish, restaurantId);

        return getTo(repository.save(dish));
    }

    @Transactional
    public void update(DishTo dishTo, int id) {
        Dish dish = new Dish();
        ValidationUtil.assureIdConsistent(dish, id);

        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setRestaurant(
                getRestaurantById(dishTo.getRestaurantId())
        );

        log.info("update {} {}", dishTo, id);

        repository.save(dish);
    }

    private Restaurant getRestaurantById(int id) {
        Optional<Restaurant> result = restaurantRepository.findById(id);
        return result.orElseThrow(
                () -> new NotFoundException("No such restaurant with id: " + id)
        );
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteById(id);
    }
}
