package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;
import com.wegot.venaqua.report.ws.exception.*;
import com.wegot.venaqua.report.ws.handler.auth.AuthenticationHandler;
import com.wegot.venaqua.report.ws.handler.auth.DefaultAuthenticationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.ServiceLoader;

public class VenAquaReportHelper {
    private final static Logger log = LoggerFactory.getLogger(VenAquaReportHelper.class);

    protected static RequestInfo prepareRequestInfoObj(String requestBody) throws RequestException {
        try {
            RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestBody, RequestInfo.class);
            log.debug("**** Parsed Request Info ****");
            log.debug("Uid : " + requestInfoObj.getUid());
            log.debug("ChartType : " + requestInfoObj.getChartType());
            log.debug("FromDate : " + requestInfoObj.getFromDate());
            log.debug("ToDate : " + requestInfoObj.getToDate());
            return requestInfoObj;
        } catch (Exception e) {
            throw new RequestException("Error occurred while parsing request body.", e);
        }
    }

    protected static void validateRequestInfo(RequestInfo requestInfo) throws RequestException {
        String uid = requestInfo.getUid();
        if (uid == null || uid.isEmpty())
            throw new RequestException("Invalid uid parameter value.");

        String chartType = requestInfo.getChartType();
        if (chartType == null || chartType.isEmpty())
            throw new RequestException("Invalid chartType parameter value.");

        Date fromDate = requestInfo.getFromDate();
        Date toDate = requestInfo.getToDate();
        if (fromDate!=null) {
            if (toDate==null)
                throw new RequestException("Missing parameter toDate.");
            else if (fromDate.equals(toDate))
                    throw new RequestException("Invalid values. Values of fromDate and toDate parameter cannot be equal.");
            else if(fromDate.after(toDate))
                throw new RequestException("Invalid values. Value of fromDate cannot be greater than toDate parameter.");
        } else if (toDate!=null)
                throw new RequestException("Missing parameter fromDate.");
    }

    protected static<T> String convertResponseObjToString(T responseObj) throws ResponseException {
        try {
            return JSONConverter.CovertToJsonString(responseObj);
        } catch (Exception e) {
            throw new ResponseException(e);
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

    protected static void throwVenaquaException(Exception e) throws VenaquaException {
        if(e instanceof AuthException) {
            AuthException ae = (AuthException)e;
            int errCode = ae.getErrorCode() == -1 ? 401 : ae.getErrorCode();
            ErrorInfo errorInfo = new ErrorInfo(errCode, ae.getMessage());
            throw new VenaquaException(errorInfo.getErrorMessage(), ae, errorInfo);
        } else if (e instanceof RequestException) {
            RequestException re = (RequestException)e;
            ErrorInfo errorInfo = new ErrorInfo(400, re.getMessage());
            throw new VenaquaException(re.getMessage(), re, errorInfo);
        } else if(e instanceof ReportException | e instanceof ProcessException | e instanceof ResponseException) {
            ErrorInfo errorInfo = new ErrorInfo(500, e.getMessage());
            throw new VenaquaException(errorInfo.getErrorMessage(), e, errorInfo);
        } else if(e instanceof Exception) {
            ErrorInfo errorInfo = new ErrorInfo(500, e.getMessage());
            throw new VenaquaException(errorInfo.getErrorMessage(), e, errorInfo);
        }
    }
}
