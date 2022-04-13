package org.polik.votingsystem.util.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Polik on 3/27/2022
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectData> handleNotFound(NotFoundException ex) {
        return notFound(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<IncorrectData> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
        return notFound(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<IncorrectData> notFound(RuntimeException ex, HttpStatus status) {
        return new ResponseEntity<>(new IncorrectData(ex.getMessage()), status);
    }
}

