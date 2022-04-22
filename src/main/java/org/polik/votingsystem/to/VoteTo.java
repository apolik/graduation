package org.polik.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by Polik on 4/11/2022
 */
@Value
@AllArgsConstructor
public class VoteTo {
    Integer restaurantId;
    Integer votes;
}
