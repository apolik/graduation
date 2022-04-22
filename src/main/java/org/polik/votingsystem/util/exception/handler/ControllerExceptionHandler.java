package org.polik.votingsystem.util.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.util.exception.ErrorInfo;
import org.polik.votingsystem.util.exception.ErrorType;
import org.polik.votingsystem.util.exception.NotFoundException;
import org.polik.votingsystem.util.exception.TimeForRevotingIsExpiredException;
import org.polik.votingsystem.util.validation.ValidationUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.polik.votingsystem.util.exception.ErrorType.DATA_ERROR;
import static org.polik.votingsystem.util.exception.ErrorType.DATA_NOT_FOUND;

/**
 * Created by Polik on 3/27/2022
 */
@Slf4j
//@ControllerAdvice todo:
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleNotFound(HttpServletRequest req, NotFoundException ex) {
        return logAndGetErrorInfo(req, ex, false, DATA_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TimeForRevotingIsExpiredException.class)
    public ErrorInfo abc(HttpServletRequest req, TimeForRevotingIsExpiredException ex) {
        return logAndGetErrorInfo(req, ex, false, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ErrorInfo conflict(HttpServletRequest req, Exception ex) {
        return logAndGetErrorInfo(req, ex, true, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorInfo handleEmptyResultDataAccess(HttpServletRequest req, EmptyResultDataAccessException ex) {
        return logAndGetErrorInfo(req, ex, false, DATA_NOT_FOUND);
    }

//    private ResponseEntity<ErrorInfo> notFound(RuntimeException ex, HttpStatus status) {
//        return new ResponseEntity<>(new ErrorInfo(ex.getMessage()), status);
//    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return new ErrorInfo(req.getRequestURL().toString(), errorType, rootCause.toString());
    }
}

