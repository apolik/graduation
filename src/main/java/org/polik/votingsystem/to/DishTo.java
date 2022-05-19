package org.polik.votingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Polik on 4/10/2022
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo {
    @NotNull
    @Range(min = 0, message = "Price must be more than 0")
    BigDecimal price;

    @NotNull
    Integer restaurantId;

    @NotNull
    LocalDate entryDate;

    @ConstructorProperties({"name", "price", "restaurantId", "entryDate"})
    public DishTo(String name, BigDecimal price, Integer restaurantId, LocalDate entryDate) {
        this(null, name, price, restaurantId, entryDate);
    }

    public DishTo(Integer id, String name, BigDecimal price, Integer restaurantId, LocalDate entryDate) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
        this.entryDate = entryDate;
    }
}
