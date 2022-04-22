package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Polik on 4/21/2022
 */
@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .toList();
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), Set.copyOf(DishUtil.createTos(restaurant.getDishes())));
    }
}
