package org.polik.votingsystem.web.restaurant;

import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.polik.votingsystem.util.validation.ValidationUtil.checkNew;

/**
 * Created by Polik on 4/7/2022
 */
@RestController
@CacheConfig(cacheNames = "restaurants")
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/admin/restaurants";

    @Override
    @Cacheable
    @GetMapping
    public List<RestaurantTo> getAllForToday() {
        return super.getAllForToday();
    }

    @CacheEvict(allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody @Valid Restaurant restaurant) {
        checkNew(restaurant);
        super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Restaurant restaurant,
                       @PathVariable int id) {
        super.update(restaurant, id);
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
    public List<RestaurantTo> getAllByDate(@RequestParam LocalDate date) {
        return super.getAllByDate(date);
    }

    @Override
    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return super.get(id);
    }
}
