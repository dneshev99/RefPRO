package com.refpro.server.exception;


import org.springframework.http.HttpStatus;

public class MatchNotFoundException extends AbstractRestException{
    public MatchNotFoundException(String errorMessage) {
        super(errorMessage,errorMessage, HttpStatus.NOT_FOUND);
    }
}
