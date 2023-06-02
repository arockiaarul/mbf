package com.youcloud.mbf.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MbfDataLibException extends RuntimeException {

    private String message;
    private Throwable cause;
    @Getter
    private String code;
    @Getter
    private HttpStatus status;

    public MbfDataLibException(String message, Throwable cause, HttpStatus status, String code) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
        this.status = status;
        this.code = code;
    }

    public MbfDataLibException(String message, HttpStatus status, String code) {
        super(message);
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public MbfDataLibException(Throwable cause, HttpStatus status, String code) {
        super(cause);
        this.cause = cause;
        this.status = status;
        this.code = code;
    }
}
