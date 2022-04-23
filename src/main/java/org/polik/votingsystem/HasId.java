package org.polik.votingsystem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

/**
 * Created by Polik on 2/1/2022
 */
public interface HasId {
    Integer getId();

    void setId(Integer id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
