package org.polik.votingsystem.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.DishRepository;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.DishTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.polik.votingsystem.util.DishUtil.fromTo;
import static org.polik.votingsystem.util.DishUtil.getTos;
import static org.polik.votingsystem.util.Util.safeGet;
import static org.polik.votingsystem.util.validation.ValidationUtil.assureIdConsistent;

/**
 * Created by Polik on 4/11/2022
 */
@Slf4j
public abstract class AbstractDishController {
    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<DishTo> getAll(@Nullable Integer restaurantId, @Nullable LocalDate date) {
        log.info("getAll {} {}", restaurantId, date);
        return getTos(repository.findAll(restaurantId, date));
    }

    @Transactional
    public Dish create(DishTo dishTo) {
        log.info("create {}", dishTo);
        Dish dish = fromTo(dishTo, getRestaurant(dishTo.getRestaurantId()));
        return repository.save(dish);
    }

    @Transactional
    public void update(DishTo dishTo, int id) {
        log.info("update {} {}", dishTo, id);
        assureIdConsistent(dishTo, id);
        repository.save(fromTo(dishTo, getRestaurant(dishTo.getRestaurantId())));
    }

    public Dish get(int id) {
        log.info("get {}", id);
        return safeGet(repository.findById(id), id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    private Restaurant getRestaurant(int restaurantId) {
        return safeGet(restaurantRepository.findById(restaurantId), restaurantId);
    }
}
