package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "restaurant", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@NamedEntityGraph(name = "restaurant", attributeNodes = @NamedAttributeNode("votes"))
public class Restaurant extends NamedEntity {

    @OneToMany(mappedBy = "restaurant",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Vote> votes = new HashSet<>();

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
