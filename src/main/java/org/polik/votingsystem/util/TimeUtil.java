package org.polik.votingsystem.util;

import java.time.LocalTime;

/**
 * Created by Polik on 4/9/2022
 */
public class TimeUtil {
    public static void isTimeForRevoteExpired() {
        if (LocalTime.now().getHour() >= 11) {
            throw new IllegalArgumentException("You cannot revote after 11 PM");
        }
    }
}
