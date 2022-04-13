package org.polik.votingsystem.web.restaurant;

import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.polik.votingsystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Polik on 4/5/2022
 */
public abstract class AbstractRestaurantController {
    @Autowired
    protected RestaurantService service;

    public List<Restaurant> getAll() {
        return service.getAll();
    }

    public Restaurant get(int id) {
        return service.getById(id);
    }

    public Restaurant create(Restaurant restaurant) {
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        service.update(restaurant, id);
    }

    public void delete(int id) {
        checkNotFoundWithId(service.deleteById(id), id);
    }
}
