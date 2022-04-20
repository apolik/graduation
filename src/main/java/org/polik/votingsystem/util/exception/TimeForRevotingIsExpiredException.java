package org.polik.votingsystem.util.exception;

/**
 * Created by Polik on 4/14/2022
 */
public class TimeForRevotingIsExpiredException extends RuntimeException {
    public TimeForRevotingIsExpiredException() {
    }

    public TimeForRevotingIsExpiredException(String message) {
        super(message);
    }
}
