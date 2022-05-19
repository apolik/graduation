package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Polik on 4/21/2022
 */
@UtilityClass
public class RestaurantUtil {
    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .sorted(Comparator.comparingInt(RestaurantTo::getVotes).reversed())
                .toList();
    }

    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDishes().size()
        );
    }
}
