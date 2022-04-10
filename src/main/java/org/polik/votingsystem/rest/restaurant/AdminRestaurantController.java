package org.polik.votingsystem.rest.restaurant;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Polik on 4/7/2022
 */
@RestController
@RequestMapping(AdminRestaurantController.REST_URL)
public class AdminRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping({"", "/"})
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping({"", "/"})
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    @PutMapping({"{id}", "{id}/"})
    public void update(Restaurant restaurant) {
        super.update(restaurant);
    }

    @Override
    @DeleteMapping({"{id}", "{id}/"})
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping({"{id}", "{id}/"})
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
