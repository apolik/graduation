package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.to.DishTo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 4/9/2022
 */
@RestController
@CacheConfig(cacheNames = "dishes")
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController extends AbstractDishController {
    public static final String REST_URL = "/api/admin/dishes";

    @Override
    @GetMapping
    @Cacheable
    public List<DishTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{restaurantId}")
    public List<DishTo> getAllByRestaurantId(@PathVariable int restaurantId) {
        return super.getAllByRestaurantId(restaurantId);
    }

    @Override
    @PostMapping
    @CacheEvict(allEntries = true)
    public DishTo create(@RequestBody @Valid DishTo dishTo) {
        return super.create(dishTo);
    }

    @Override
    @PutMapping("/{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DishTo dishTo,
                       @PathVariable int id) {
        super.update(dishTo, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/history")
    public List<DishTo> getAllByDate(@RequestParam LocalDate date) {
        return super.getAllByDate(date);
    }

    @Override
    @GetMapping("/{restaurantId}/history")
    public List<DishTo> getAllByDateAndRestaurantId(@RequestParam LocalDate date,
                                                    @PathVariable int restaurantId) {
        return super.getAllByDateAndRestaurantId(date, restaurantId);
    }
}
