package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.polik.votingsystem.util.validation.ValidationUtil.checkNew;

/**
 * Created by Polik on 4/9/2022
 */
@RestController
@CacheConfig(cacheNames = "dishes")
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController extends AbstractDishController {
    public static final String REST_URL = "/api/admin/dishes";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo) {
        checkNew(dishTo);
        Dish created = super.create(dishTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DishController.REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
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
}
