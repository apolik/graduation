package org.polik.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.polik.votingsystem.model.Restaurant;

/**
 * Created by Polik on 4/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteTo {
    private Restaurant restaurant;
    private Integer votes;
}
