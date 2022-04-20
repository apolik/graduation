package org.polik.votingsystem.util.exception;

/**
 * Created by Polik on 4/15/2022
 */
public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException() {
    }

    public IllegalRequestDataException(String message) {
        super(message);
    }
}
