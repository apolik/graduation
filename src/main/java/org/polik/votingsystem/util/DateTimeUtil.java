package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Polik on 4/11/2022
 */
@UtilityClass
public class DateTimeUtil {
    public static final LocalTime DEADLINE_FOR_REVOTING = LocalTime.parse("11:00:00");

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.hasLength(str) ? LocalDate.parse(str) : null;
    }

    public static @Nullable
    LocalTime parseLocalTime(@Nullable String str) {
        return StringUtils.hasLength(str) ? LocalTime.parse(str) : null;
    }

    public static @Nullable
    LocalDateTime parseDateTime(@Nullable String str) {
        return StringUtils.hasLength(str) ? LocalDateTime.parse(str) : null;
    }

    public static boolean isBeforeDeadline(LocalTime time) {
        return time.isBefore(DEADLINE_FOR_REVOTING);
    }
}
