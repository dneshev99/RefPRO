package com.refpro.server.dtos;

import com.refpro.server.exception.AbstractRestException;
import org.springframework.http.HttpStatus;

public class ErrorDto {
    private String errorMessage;
    protected Integer errorCode;
    protected String status;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorDto(AbstractRestException e){
        this.errorCode = e.getErrorCode();
        this.errorMessage = e.getDisplayErrorMessage();
        this.status = e.getStatus().getReasonPhrase();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
