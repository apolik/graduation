package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Table(name = "dishes")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"restaurant"})
@NoArgsConstructor
public class Dish extends NamedEntity {

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    public Dish(Integer id, String name, BigDecimal price, LocalDate entryDate, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.entryDate = entryDate;
        this.restaurant = restaurant;
    }
}
