package com.wegot.venaqua.report.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface VenAquaReport {
    public static final String SERVICE_NAME = "VenAqua Report";

    @WebMethod
    /**
     * Get Site Consumption for water sources
     */
    public SiteConsumptionByWaterSourceResponse getSiteConsumptionByWaterSource(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) RequestInfo requestInfo);

    @WebMethod
    public String getResidenceConsumption(RequestInfo requestInfo);

    @WebMethod
    public String getSiteDemandByWaterType(RequestInfo requestInfo);

    @WebMethod
    public String getPumpStateAndYield(RequestInfo requestInfo);

    @WebMethod
    public String getYieldByWaterSource(RequestInfo requestInfo);
}
