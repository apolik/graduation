package org.polik.votingsystem.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
public class Restaurant extends NamedEntity {
    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
