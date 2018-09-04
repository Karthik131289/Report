package com.wegot.venaqua.report.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {

    @Override
    public String getSiteConsumptionByWaterSource(RequestInfo requestInfo) {
        System.out.println("Processing");
        System.out.println("Uid : " + requestInfo.getUid());
        System.out.println("ChartType : " + requestInfo.getChartType());
        System.out.println("FromDate : " + requestInfo.getFromDate());
        System.out.println("ToDate : " + requestInfo.getToDate());
        return "SiteConsumptionByWaterSource";
    }

    @Override
    public String getResidenceConsumption(RequestInfo requestInfo) {
        return "ResidenceConsumption";
    }

    @Override
    public String getSiteDemandByWaterType(RequestInfo requestInfo) {
        return "SiteDemandByWaterType(";
    }

    @Override
    public String getPumpStateAndYield(RequestInfo requestInfo) {
        return "PumpStateAndYield";
    }

    @Override
    public String getYieldByWaterSource(RequestInfo requestInfo) {
        return "YieldByWaterSource";
    }
}
