package com.wegot.venaqua.report.ws.handler.auth;

import com.wegot.venaqua.report.ws.InvocationInfo;
import com.wegot.venaqua.report.ws.exception.ReportException;

public interface AuthenticationHandler {
    public boolean authenticate(InvocationInfo invocationInfo) throws ReportException;
}
