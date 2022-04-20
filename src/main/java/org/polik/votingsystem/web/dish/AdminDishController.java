package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.to.DishTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 4/9/2022
 */
@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController extends AbstractDishController {
    public static final String REST_URL = "/api/admin/dishes";

    @GetMapping
    public List<DishTo> getAllByDate(@RequestParam @Nullable LocalDate date) {
        return date == null ?
                super.getAll() :
                super.getAllByDate(date);
    }

    @GetMapping("/{restaurantId}")
    public List<DishTo> getAllByDateAndRestaurantId(@RequestParam @Nullable LocalDate date,
                                                    @PathVariable int restaurantId) {
        return date == null ?
                super.getAllByRestaurantId(restaurantId) :
                super.getAllByDateAndRestaurantId(date, restaurantId);
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
