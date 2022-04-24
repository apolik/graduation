package org.polik.votingsystem.to;

import lombok.Value;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

/**
 * Created by Polik on 4/11/2022
 */
@Value
public class VoteTo extends BaseTo {
    String restaurant;
    int restaurantId;
    LocalDate date;

    @ConstructorProperties({"id", "restaurant", "restaurantId", "date"})
    public VoteTo(int id, String restaurant, int restaurantId, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
