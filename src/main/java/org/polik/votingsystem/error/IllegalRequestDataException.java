package org.polik.votingsystem.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

/**
 * Created by Polik on 4/15/2022
 */
public class IllegalRequestDataException extends AppException {
    public IllegalRequestDataException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
