package org.polik.votingsystem.util.validation;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.HasId;
import org.polik.votingsystem.util.exception.IllegalRequestDataException;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

/**
 * Created by Polik on 4/9/2022
 */
@UtilityClass
public class ValidationUtil {

    public static void assureIdConsistent(HasId entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalRequestDataException(entity + " must be with id=" + id);
        }
    }

    public static <T> T checkNotFound(T obj, String message) {
        if (obj == null)
            throw new NotFoundException("Not found entity with " + message);
        else
            return obj;
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }
}
