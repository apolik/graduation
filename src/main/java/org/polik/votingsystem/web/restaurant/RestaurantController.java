package org.polik.votingsystem.web.restaurant;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/12/2022
 */
@RestController
@CacheConfig(cacheNames = "restaurants")
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/restaurants";

    @GetMapping
    @Cacheable
    public List<Restaurant> getAll(@RequestParam(required = false) LocalDate date) {
        return super.getAll(date);
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
