package org.polik.votingsystem.util;

import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;

import java.util.List;

/**
 * Created by Polik on 4/10/2022
 */
public class DishUtil {
    public static List<DishTo> getTos(List<Dish> dishes) {
        return dishes.stream()
                .map(DishUtil::getTo)
                .toList();
    }

    public static DishTo getTo(Dish dish) {
        return new DishTo(
                dish.getId(),
                dish.getName(),
                dish.getPrice(),
                dish.getRestaurant().getId()
        );
    }

    public static Dish fromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(),
                dishTo.getName(),
                dishTo.getPrice());
    }

    public static void updateNotNullFields(Dish old, DishTo newDish) {
        if (newDish.getName() != null)
            old.setName(newDish.getName());

        if (newDish.getPrice() != null)
            old.setPrice(newDish.getPrice());
    }
}
