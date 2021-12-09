package br.com.ignidigital.dscatalog.controller.exceptions;

import br.com.ignidigital.dscatalog.services.exceptions.DatabaseException;
import br.com.ignidigital.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound (ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError ();
        err.setTimestamp(Instant.now());
        err.setStatus(httpStatus.value());
        err.setError("Resource not found!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database (DatabaseException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError ();
        err.setTimestamp(Instant.now());
        err.setStatus(httpStatus.value());
        err.setError("Database exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(err);
    }


}
