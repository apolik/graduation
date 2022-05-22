package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;

import java.util.Collection;
import java.util.List;

/**
 * Created by Polik on 5/22/2022
 */
@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.id(), restaurant.getName());
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .toList();
    }
}
