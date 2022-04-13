package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.BaseEntity;
import org.polik.votingsystem.util.exception.NotFoundException;

/**
 * Created by Polik on 4/9/2022
 */
@UtilityClass
public class ValidationUtil {
    public static void assureIdConsistent(BaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static <T> T checkNotFound(T obj, String message) {
        if (obj == null)
            throw new NotFoundException("Not found entity with " + message);
        else
            return obj;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }
}
