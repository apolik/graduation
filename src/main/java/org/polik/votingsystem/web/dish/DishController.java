package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@CacheConfig(cacheNames = "dishes")
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController extends AbstractDishController {
    public static final String REST_URL = "/api/dishes";

    @GetMapping
    @Cacheable
    public List<DishTo> getAll(@RequestParam(required = false) Integer restaurantId,
                               @RequestParam(required = false) LocalDate date) {
        return super.getAll(restaurantId, date);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }
}
