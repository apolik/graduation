package org.polik.votingsystem.rest.restaurant;

import org.polik.votingsystem.model.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Polik on 3/12/2022
 */
@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping({"", "/"})
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping({"{id}", "{id}/"})
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
