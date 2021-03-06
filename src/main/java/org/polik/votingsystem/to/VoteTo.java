package org.polik.votingsystem.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;

/**
 * Created by Polik on 4/11/2022
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class VoteTo extends BaseTo {
    @NotNull
    Integer restaurantId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    LocalDate voteDate;

    @Builder
    @ConstructorProperties({"id", "restaurantId", "voteDate"})
    public VoteTo(Integer id, Integer restaurantId, LocalDate voteDate) {
        super(id);
        this.restaurantId = restaurantId;
        this.voteDate = voteDate;
    }
}
