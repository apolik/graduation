package org.polik.votingsystem.web.dish;

import org.polik.votingsystem.service.DishService;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Polik on 4/11/2022
 */
public abstract class AbstractDishController {
    @Autowired
    private DishService service;

    public List<DishTo> getAll() {
        return service.getAll();
    }

    public List<DishTo> getAll(int restaurantId) {
        return service.getAll(restaurantId);
    }

    public DishTo create(DishTo dishTo) {
        return service.create(dishTo, dishTo.getRestaurantId());
    }

    public void update(DishTo dishTo, int restaurantId) {
        service.update(dishTo, restaurantId);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(service.delete(id), id);
    }
}
