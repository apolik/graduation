package org.polik.votingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.beans.ConstructorProperties;

/**
 * Created by Polik on 4/21/2022
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {
    int votes;

    @ConstructorProperties({"id", "name", "votes"})
    public RestaurantTo(Integer id, String name, int votes) {
        super(id, name);
        this.votes = votes;
    }
}
