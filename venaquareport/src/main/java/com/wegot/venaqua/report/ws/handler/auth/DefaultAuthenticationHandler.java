package com.wegot.venaqua.report.ws.handler.auth;

import com.wegot.venaqua.report.ws.InvocationInfo;
import com.wegot.venaqua.report.ws.exception.AuthException;
import com.wegot.venaqua.report.ws.exception.ReportException;

public class DefaultAuthenticationHandler implements AuthenticationHandler {
    @Override
    public boolean authenticate(InvocationInfo invocationInfo) throws AuthException {
        return true;
    }
}
