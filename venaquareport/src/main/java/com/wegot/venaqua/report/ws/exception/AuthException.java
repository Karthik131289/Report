package com.wegot.venaqua.report.ws.exception;

public class AuthException extends Exception {
    private int errorCode = -1;

    public AuthException() {
    }

    public AuthException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AuthException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
