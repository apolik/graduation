package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.DishTo;

import java.util.Collection;
import java.util.List;

/**
 * Created by Polik on 4/10/2022
 */
@UtilityClass
public class DishUtil {
    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream()
                .map(DishUtil::createTo)
                .toList();
    }

    public static DishTo createTo(Dish dish) {
        return new DishTo(
                dish.getId(),
                dish.getName(),
                dish.getPrice(),
                dish.getRestaurant().getId()
        );
    }

    public static List<Dish> setRestaurant(List<Dish> dishes, Restaurant restaurant) {
        return dishes.stream()
                .peek(el -> el.setRestaurant(restaurant))
                .toList();
    }

    public static Dish fromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(),
                dishTo.getName(),
                dishTo.getPrice());
    }

    public static Dish fromTo(int id, DishTo dishTo, Restaurant restaurant) {
        return new Dish(
                id,
                dishTo.getName(),
                dishTo.getPrice(),
                restaurant
        );
    }
}
