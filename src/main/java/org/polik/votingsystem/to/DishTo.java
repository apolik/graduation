package org.polik.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Polik on 4/10/2022
 */
@Value
@AllArgsConstructor
@NotBlank
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "length must be between 4 and 50 characters")
    String name;

    @NotNull
    @Range(min = 100, max = 10000, message = "Price should be between 100 and 10000")
    Integer price;

    @NotNull
    Integer restaurantId;

    public DishTo(Integer id, String name, Integer price, Integer restaurantId) {
        super(id);
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }
}
