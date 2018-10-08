package com.wegot.venaqua.report.ws.exception;

//@WebFault(name="ReportException", faultBean = "ErrorInfo")
public class ReportException extends Exception {
    private ErrorInfo errorInfo;

    public ReportException() {
        super();
    }

    public ReportException(ErrorInfo errorInfo) {
        super(errorInfo.getErrorMessage());
        this.errorInfo = errorInfo;
    }

    public ReportException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public ReportException(String message, ErrorInfo errorInfo, Throwable cause) {
        super(message, cause);
        this.errorInfo = errorInfo;
    }

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportException(Throwable cause) {
        super(cause);
    }

    public ReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
