package org.polik.votingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

/**
 * Created by Polik on 4/10/2022
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo {

    @NotNull
    @Range(min = 100, max = 10000, message = "Price must be between 100 and 10000")
    Integer price;

    @NotNull
    Integer restaurantId;

    @ConstructorProperties({"name", "price", "restaurantId"})
    public DishTo(String name, Integer price, Integer restaurantId) {
        super(null, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public DishTo(Integer id, String name, Integer price, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }
}
