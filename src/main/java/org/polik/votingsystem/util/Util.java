package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.HasId;
import org.polik.votingsystem.error.NotFoundException;

import java.util.Optional;

/**
 * Created by Polik on 5/14/2022
 */
@UtilityClass
public class Util {
    public static <T extends HasId> T safeGet(Optional<T> entity, int id) {
        return entity.orElseThrow(
                () -> new NotFoundException("Entity with id=" + id + " not found")
        );
    }
}
