package org.polik.votingsystem.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

import static org.polik.votingsystem.util.Util.safeGet;
import static org.polik.votingsystem.util.validation.ValidationUtil.assureIdConsistent;

/**
 * Created by Polik on 4/5/2022
 */
@Slf4j
@CacheConfig(cacheNames = "restaurants")
public abstract class AbstractRestaurantController {
    @Autowired
    protected RestaurantRepository repository;

    public List<Restaurant> getAll(@Nullable LocalDate date) {
        log.info("getAll {}", date);
        return repository.findAll(date);
    }

    public Restaurant get(int id) {
        log.info("getById {}", id);
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

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
