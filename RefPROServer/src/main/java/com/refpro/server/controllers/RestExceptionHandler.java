package com.refpro.server.controllers;


import com.refpro.server.dtos.ErrorDto;
import com.refpro.server.exception.AbstractRestException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages={"com.refpro.server.controllers"})
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractRestException.class)
    protected ResponseEntity<Object> handleAbstractRestException(AbstractRestException exception) {
        return buildResponseEntity(exception);
    }

    private ResponseEntity buildResponseEntity(AbstractRestException exception) {
        ErrorDto errorDto = new ErrorDto(exception);
        return new ResponseEntity(errorDto, exception.getStatus()==null? HttpStatus.INTERNAL_SERVER_ERROR:exception.getStatus());
    }


}