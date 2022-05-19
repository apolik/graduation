package org.polik.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Polik on 3/11/2022
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "unique_user_id_per_date_idx")})
@ToString(callSuper = true, exclude = {"user", "restaurant"})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "user", attributeNodes = @NamedAttributeNode(value = "user")),
        @NamedEntityGraph(name = "restaurant", attributeNodes = @NamedAttributeNode("restaurant"))
})
public class Vote extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "vote_date")
    @CreationTimestamp
    private LocalDate voteDate;

    @Builder
    public Vote(Integer id, LocalDate voteDate, User user, Restaurant restaurant) {
        super(id);
        this.voteDate = voteDate;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(User user, Restaurant restaurant) {
        this(null, null, user, restaurant);
    }
}
