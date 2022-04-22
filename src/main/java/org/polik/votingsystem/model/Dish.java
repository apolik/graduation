package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Table(name = "dishes")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dish extends NamedEntity {

    @Column(name = "price")
    @NotNull
    private Integer price;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "date", updatable = false)
    @CreationTimestamp
    private LocalDate date;

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Integer price, Restaurant restaurant) {
        super.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
