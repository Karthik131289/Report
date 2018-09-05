package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;

import javax.jws.WebService;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {

    @Override
    public SiteConsumptionByWaterSourceResponse getSiteConsumptionByWaterSource(RequestInfo requestInfo) {
        System.out.println("Processing...");
        System.out.println("Uid : " + requestInfo.getUid());
        System.out.println("ChartType : " + requestInfo.getChartType());
        System.out.println("FromDate : " + requestInfo.getFromDate());
        System.out.println("ToDate : " + requestInfo.getToDate());
        try {
            List waterSource = JSONConverter.CovertJSONToObject(new File("SiteConsumptionByWaterSource.json"), List<WaterSource>.class);
            SiteConsumptionByWaterSourceResponse response = new SiteConsumptionByWaterSourceResponse();
            response.setWaterSourceList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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
