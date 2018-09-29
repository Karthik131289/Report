package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class VenAquaReportHelper {

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

    protected static String dummyResponse() throws IOException {
        String response = null;
        InputStream resourceAsStream = VenAquaReportHelper.class.getClassLoader().getResourceAsStream("SiteUsageByWaterSource.json");
        if (resourceAsStream != null) {
            response = IOUtils.toString(resourceAsStream);
        }
        return response;
    }
}
