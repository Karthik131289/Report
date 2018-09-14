package com.wegot.venaqua.report.ws;

import org.apache.commons.io.IOUtils;

import javax.jws.WebService;
import java.io.IOException;
import java.io.InputStream;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {

    @Override
    public String getSiteUsageByWaterSource(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteUsageByWaterSource.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getSiteUsageByBlockLevel(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteUsageByBlockLevel.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getHighUsers(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("HighUsers.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getSiteDemandByWaterType(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteDemandByWaterType.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getPumpYield(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("PumpYield.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getSiteTrendByWaterSource(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteTrendByWaterSource.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }

    @Override
    public String getSiteWaterMap(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
            /*RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);
            System.out.println("**** Parsed Request Info ****");
            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());*/

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteWaterMap.json");
            if (resourceAsStream != null) {
                response = IOUtils.toString(resourceAsStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        System.out.println("**** Response Info ****");
        System.out.println(response);
        return response;
    }
}
