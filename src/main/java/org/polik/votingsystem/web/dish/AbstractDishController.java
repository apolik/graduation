package org.polik.votingsystem.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.NotFoundException;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.DishRepository;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.util.DishUtil;
import org.polik.votingsystem.util.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.polik.votingsystem.util.DishUtil.fromTo;
import static org.polik.votingsystem.util.DishUtil.setRestaurant;

/**
 * Created by Polik on 4/11/2022
 */
@Slf4j
public abstract class AbstractDishController {
    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<DishTo> getAllForToday() {
        log.info("getAllForToday");
        return DishUtil.getTos(repository.findAll());
    }

    public List<DishTo> getAllByDate(LocalDate date) {
        log.info("getAllByDate {}", date);
        return DishUtil.getTos(repository.findAllByDate(date));
    }

    public List<DishTo> getAllByDateAndRestaurantId(@Nullable LocalDate date, int restaurantId) {
        log.info("getAllByDateAndRestaurantId {} {}", date, restaurantId);
        return DishUtil.getTos(repository.findAllByDateAndRestaurantId(date, restaurantId));
    }

    public List<DishTo> getAllForTodayByRestaurantId(int restaurantId) {
        log.info("getAllForTodayByRestaurantId {}", restaurantId);
        return DishUtil.getTos(repository.findAllByRestaurantId(restaurantId));
    }

    @Transactional
    public Dish create(DishTo dishTo) {
        log.info("create {}", dishTo);
        Dish dish = fromTo(dishTo);

        dish.setRestaurant(
                getRestaurant(dishTo.getRestaurantId())
        );

        return repository.save(dish);
    }

    @Transactional
    public void update(DishTo dishTo, int restaurantId) {
        log.info("update {} {}", dishTo, restaurantId);
        ValidationUtil.assureIdConsistent(dishTo, restaurantId);

        repository.save(fromTo(
                dishTo.getId(),
                dishTo,
                getRestaurant(dishTo.getRestaurantId())
        ));
    }

    @Transactional
    public List<Dish> createDishes(int restaurantId, List<Dish> dishes) {
        log.info("createDishes {}", restaurantId);
        Restaurant restaurant = getRestaurant(restaurantId);

        return repository.saveAll(setRestaurant(dishes, restaurant));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    private Restaurant getRestaurant(int id) {
        Optional<Restaurant> result = restaurantRepository.findById(id);
        return result.orElseThrow(
                () -> new NotFoundException("No such restaurant with id: " + id)
        );
    }
}
