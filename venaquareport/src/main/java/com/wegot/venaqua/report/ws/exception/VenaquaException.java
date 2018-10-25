package com.wegot.venaqua.report.ws.exception;

import javax.xml.ws.WebFault;

@WebFault(name="VenaquaException", faultBean = "com.wegot.venaqua.report.ws.exception.ErrorInfo")
public class VenaquaException extends Exception {
    private ErrorInfo errorInfo;

    public VenaquaException() {
        super();
    }

    public VenaquaException(ErrorInfo errorInfo) {
        super(errorInfo.getErrorMessage());
        this.errorInfo = errorInfo;
    }

    public VenaquaException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public VenaquaException(String message, Throwable cause, ErrorInfo errorInfo) {
        super(message, cause);
        this.errorInfo = errorInfo;
    }

    public VenaquaException(Throwable cause, ErrorInfo errorInfo) {
        super(cause);
        this.errorInfo = errorInfo;
    }

    public VenaquaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorInfo errorInfo) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public int getErrorCode() {
        return errorInfo.getErrorCode();
    }

    public String getErrorMessage() {
        return errorInfo.getErrorMessage();
    }
}
