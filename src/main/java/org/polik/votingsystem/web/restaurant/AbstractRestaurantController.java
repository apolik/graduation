package org.polik.votingsystem.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.RestaurantTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.polik.votingsystem.util.RestaurantUtil.getTos;
import static org.polik.votingsystem.util.Util.safeGet;
import static org.polik.votingsystem.util.validation.ValidationUtil.assureIdConsistent;

/**
 * Created by Polik on 4/5/2022
 */
@Slf4j
public abstract class AbstractRestaurantController {
    @Autowired
    protected RestaurantRepository repository;

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return getTos(repository.findAll());
    }

    public Restaurant getWithDishes(int id, @Nullable LocalDate date) {
        log.info("getAllWithDishes {} for date {}", id, date);
        Optional<Restaurant> result = date == null ?
                repository.getWithDishesForToday(id) :
                repository.getWithDishes(id, date);
        return safeGet(result, id);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return safeGet(repository.findById(id), id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
