package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@Getter @Setter
public class Restaurant extends NamedEntity {
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Dish> dishes;

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
