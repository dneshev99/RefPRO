package com.refpro.server.exception;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends AbstractRestException{
    public PlayerNotFoundException(String message) {super(message,message,HttpStatus.NOT_FOUND);}
    public PlayerNotFoundException(String message, String endUserVisibleMessage) {super(message,endUserVisibleMessage,HttpStatus.NOT_FOUND);};
}
