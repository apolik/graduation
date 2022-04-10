package org.polik.votingsystem.rest.dish;

import org.polik.votingsystem.service.DishService;
import org.polik.votingsystem.to.DishTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Polik on 4/9/2022
 */
@RestController
@RequestMapping(AdminDishController.REST_URL)
public class AdminDishController {
    public static final String REST_URL = "/rest/admin/dishes";
    private final DishService service;

    public AdminDishController(DishService service) {
        this.service = service;
    }

    @GetMapping({"{restaurantId}", "{restaurantId}/"})
    public List<DishTo> getAll(@PathVariable int restaurantId) {
        return service.getAll(restaurantId);
    }

    @GetMapping()
    public List<DishTo> getAll() {
        return service.getAll();
    }

    @PostMapping({"", "/"})
    public DishTo create(@RequestBody @Valid DishTo dishTo) {
        return service.create(dishTo, dishTo.getRestaurantId());
    }

    @PutMapping({"{id}", "{id}/"})
    public void update(@RequestBody @Valid DishTo dishTo, @PathVariable int id) {
        service.update(dishTo, id);
    }

    @DeleteMapping({"{id}", "{id}/"})
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
