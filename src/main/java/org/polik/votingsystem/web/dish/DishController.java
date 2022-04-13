package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.to.DishTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController extends AbstractDishController {
    public static final String REST_URL = "/rest/dishes";

    @GetMapping("/{restaurantId}")
    public List<DishTo> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping
    public List<DishTo> getAll() {
        return super.getAll();
    }
}
