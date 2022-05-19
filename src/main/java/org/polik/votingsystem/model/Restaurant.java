package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@ToString(callSuper = true, exclude = {"dishes"})
@NamedEntityGraph(name = "dishes", attributeNodes = @NamedAttributeNode("dishes"))
public class Restaurant extends NamedEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "restaurant",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Dish> dishes = new HashSet<>();

    public Restaurant(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
