package org.polik.votingsystem.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

/**
 * Created by Polik on 5/20/2022
 */
public class DuplicateException extends AppException {
    public DuplicateException(String message) {
        super(HttpStatus.CONFLICT, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
