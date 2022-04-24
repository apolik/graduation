package org.polik.votingsystem.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

/**
 * Created by Polik on 4/14/2022
 */
public class ExpiredTimeException extends AppException {
    public ExpiredTimeException(String message) {
        super(HttpStatus.CONFLICT, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
