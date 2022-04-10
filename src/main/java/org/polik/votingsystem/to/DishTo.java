package org.polik.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Polik on 4/10/2022
 */
@Data
@AllArgsConstructor
public class DishTo {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 50, message = "length must be between 4 and 50 characters")
    private String name;

    @NotNull
    @Range(min = 100, max = 10000, message = "Price should be between 100 and 10000")
    private Integer price;

    @NotNull
    private Integer restaurantId;
}
