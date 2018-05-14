package com.refpro.server.exception;


import org.springframework.http.HttpStatus;

public class InvalidInputException  extends AbstractRestException{
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidInputException(String message, String endUserVisibleMessage) {super(message,endUserVisibleMessage); this.status= HttpStatus.BAD_REQUEST;};
}
