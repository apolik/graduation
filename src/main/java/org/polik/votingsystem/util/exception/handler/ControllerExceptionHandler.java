package org.polik.votingsystem.util.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by Polik on 3/27/2022
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<IncorrectData> handleNotFound(NotFoundException ex) {
//        return notFound(ex);
//    }

    private ResponseEntity<IncorrectData> notFound(RuntimeException ex) {
        return new ResponseEntity<>(new IncorrectData(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}

