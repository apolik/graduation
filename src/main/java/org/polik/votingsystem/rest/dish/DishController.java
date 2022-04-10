package org.polik.votingsystem.rest.dish;

import org.polik.votingsystem.service.DishService;
import org.polik.votingsystem.to.DishTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {
    public static final String REST_URL = "/rest/dishes";
    private final DishService service;

    public DishController(DishService service) {
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
}
