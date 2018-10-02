package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.handler.auth.AuthenticationHandler;
import com.wegot.venaqua.report.ws.handler.auth.DefaultAuthenticationHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

public class VenAquaReportHelper {
    private final static Logger log = LoggerFactory.getLogger(VenAquaReportHelper.class);

    protected static RequestInfo prepareRequestInfoObj(String requestBody) throws ReportException {
        try {
            RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestBody, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());
            return requestInfoObj;
        } catch (Exception e) {
            throw new ReportException(e);
        }
    }

    protected static InvocationInfo prepareInvocationInfo(RequestInfo requestInfo) {
        InvocationInfo invocationInfo = new InvocationInfo();
        invocationInfo.setRequestInfo(requestInfo);
        return invocationInfo;
    }

    protected static AuthenticationHandler getAuthHandler() {
        AuthenticationHandler authHandler = null;
        ServiceLoader<AuthenticationHandler> serviceLoader = ServiceLoader.load(AuthenticationHandler.class);
        Iterator<AuthenticationHandler> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            AuthenticationHandler handler = iterator.next();
            if (handler instanceof DefaultAuthenticationHandler) {
                if (!iterator.hasNext()) {
                    authHandler = handler;
                    break;
                }
            } else {
                authHandler = handler;
                break;
            }
        }
        if (authHandler == null)
            log.debug("Could not initialize authentication handler.");
        return authHandler;
    }

    protected static String dummyResponse() throws IOException {
        String response = null;
        InputStream resourceAsStream = VenAquaReportHelper.class.getClassLoader().getResourceAsStream("SiteUsageByWaterSource.json");
        if (resourceAsStream != null) {
            response = IOUtils.toString(resourceAsStream);
        }
        return response;
    }
}
