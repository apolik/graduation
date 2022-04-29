package org.polik.votingsystem.util.validation;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.HasId;
import org.polik.votingsystem.error.IllegalRequestDataException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Created by Polik on 4/9/2022
 */
@UtilityClass
public class ValidationUtil {
    public static final int LAST_HOUR_FOR_REVOTING = 11;

    public static void assureIdConsistent(HasId entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalRequestDataException(entity + " must be with id=" + id);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void checkNew(List<? extends HasId> beans) {
        beans.forEach(el -> {
            if (!el.isNew()) {
                throw new IllegalRequestDataException(el.getClass().getSimpleName() + " must be new (id=null)");
            }
        });
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
