package org.polik.votingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.Set;

/**
 * Created by Polik on 4/21/2022
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {
    Set<DishTo> dishes;

    public RestaurantTo(Integer id, String name, Set<DishTo> dishes) {
        super(id, name);
        this.dishes = dishes;
    }
}
