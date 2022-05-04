package org.polik.votingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

/**
 * Created by Polik on 4/11/2022
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class VoteTo extends BaseTo {
    String restaurant;
    int restaurantId;
    LocalDate voteDate;

    @ConstructorProperties({"id", "restaurant", "restaurantId", "voteDate"})
    public VoteTo(int id, String restaurant, int restaurantId, LocalDate voteDate) {
        super(id);
        this.restaurant = restaurant;
        this.restaurantId = restaurantId;
        this.voteDate = voteDate;
    }
}
