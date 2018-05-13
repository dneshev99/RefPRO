package com.refpro.server.exception;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends AbstractRestException{
    public PlayerNotFoundException(String message) {super(message);}
    public PlayerNotFoundException(String message, String endUserVisibleMessage) {super(message,endUserVisibleMessage); this.status= HttpStatus.NOT_FOUND;};
}
