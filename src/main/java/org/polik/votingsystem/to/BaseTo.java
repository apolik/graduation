package org.polik.votingsystem.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.polik.votingsystem.HasId;

/**
 * Created by Polik on 4/15/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseTo implements HasId {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    protected Integer id;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
