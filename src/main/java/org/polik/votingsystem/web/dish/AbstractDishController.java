package org.polik.votingsystem.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.DishRepository;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.util.DishUtil;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.polik.votingsystem.util.DishUtil.createTo;
import static org.polik.votingsystem.util.DishUtil.fromTo;

/**
 * Created by Polik on 4/11/2022
 */
@Slf4j
public abstract class AbstractDishController {
    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<DishTo> getAll() {
        log.info("getAll");
        return DishUtil.createTos(repository.findAll());
    }

    public List<DishTo> getAllByDate(LocalDate date) {
        log.info("getAllByDate {}", date);
        return DishUtil.createTos(repository.findAllByDate(date));
    }

    public List<DishTo> getAllByDateAndRestaurantId(@Nullable LocalDate date, int restaurantId) {
        log.info("getAllByDateAndRestaurantId {} {}", date, restaurantId);
        return DishUtil.createTos(repository.findAllByDateAndRestaurantId(date, restaurantId));
    }

    public List<DishTo> getAllByRestaurantId(int restaurantId) {
        log.info("getAllByRestaurantId {}", restaurantId);
        return DishUtil.createTos(repository.findAllByRestaurantId(restaurantId));
    }

    @Transactional
    public DishTo create(DishTo dishTo) {
        log.info("create {}", dishTo);
        Dish dish = fromTo(dishTo);

        dish.setRestaurant(
                getRestaurant(dishTo.getRestaurantId())
        );

        return createTo(repository.save(dish));
    }

    @Transactional
    public void update(DishTo dishTo, int restaurantId) {
        log.info("update {} {}", dishTo, restaurantId);

        repository.save(fromTo(
                dishTo.getId(),
                dishTo,
                getRestaurant(dishTo.getRestaurantId())
        ));
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
