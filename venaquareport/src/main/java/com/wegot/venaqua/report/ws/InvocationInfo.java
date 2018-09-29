package com.wegot.venaqua.report.ws;

public class InvocationInfo {
    private RequestInfo requestInfo;

    public InvocationInfo() {
    }

    public InvocationInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
}
