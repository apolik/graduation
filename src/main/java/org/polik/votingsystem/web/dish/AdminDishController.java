package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.to.DishTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Polik on 4/9/2022
 */
@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController extends AbstractDishController {
    public static final String REST_URL = "/rest/admin/dishes";

    @Override
    @GetMapping("/{restaurantId}")
    public List<DishTo> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @GetMapping
    public List<DishTo> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping
    public DishTo create(@RequestBody @Valid DishTo dishTo) {
        return super.create(dishTo);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DishTo dishTo,
                       @PathVariable int id) {
        super.update(dishTo, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
