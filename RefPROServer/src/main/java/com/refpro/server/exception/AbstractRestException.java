package com.refpro.server.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractRestException extends Exception{
    public AbstractRestException(String errorMessage){
        super(errorMessage);
    }
    public AbstractRestException(String message, String endUserVisibleMessage) {
        super(message);
        this.displayErrorMessage = endUserVisibleMessage;
    }
    protected Integer errorCode;
    protected String displayErrorMessage;
    protected HttpStatus status;
    public AbstractRestException(){
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getDisplayErrorMessage() {
        return displayErrorMessage;
    }

    public void setDisplayErrorMessage(String displayErrorMessage) {
        this.displayErrorMessage = displayErrorMessage;
    }
}
