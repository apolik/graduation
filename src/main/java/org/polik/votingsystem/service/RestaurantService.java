package org.polik.votingsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.util.ValidationUtil;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@Slf4j
@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    public Restaurant getById(int id) {
        log.info("getById {}", id);
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("No such restaurant with id: " + id)
        );
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} {}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);

        repository.save(restaurant);
    }

    public boolean deleteById(int id) {
        log.info("deleteById {}", id);
        return repository.deleteById(id) != 0;
    }
}
