package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import javax.jws.WebService;
import java.io.IOException;
import java.io.InputStream;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {

    @Override
    public String getSiteConsumptionByWaterSource(String requestInfo) {
        String response = null;
        System.out.println("Processing...");
        try {
            RequestInfo requestInfoObj = JSONConverter.CovertToObject(requestInfo, RequestInfo.class);

            System.out.println("Uid : " + requestInfoObj.getUid());
            System.out.println("ChartType : " + requestInfoObj.getChartType());
            System.out.println("FromDate : " + requestInfoObj.getFromDate());
            System.out.println("ToDate : " + requestInfoObj.getToDate());

            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("SiteConsumptionByWaterSource.json");
            if(resourceAsStream!=null) {

            }

        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        return response;
    }

    @Override
    public String getResidenceConsumption(String requestInfo) {
        return "ResidenceConsumption";
    }

    @Override
    public String getSiteDemandByWaterType(String requestInfo) {
        return "SiteDemandByWaterType(";
    }

    @Override
    public String getPumpStateAndYield(String requestInfo) {
        return "PumpStateAndYield";
    }

    @Override
    public String getYieldByWaterSource(String requestInfo) {
        return "YieldByWaterSource";
    }
}
