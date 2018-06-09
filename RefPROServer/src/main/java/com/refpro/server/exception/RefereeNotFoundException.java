package com.refpro.server.exception;

import org.springframework.http.HttpStatus;

public class RefereeNotFoundException extends AbstractRestException{
    public RefereeNotFoundException(String errorMessage) {
        super(errorMessage,errorMessage,HttpStatus.NOT_FOUND);
    }
    public RefereeNotFoundException(String message, String endUserVisibleMessage) {super(message,endUserVisibleMessage,HttpStatus.NOT_FOUND);};
}
