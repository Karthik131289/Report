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
    public String getSiteConsumptionByWaterSource(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getResidenceConsumption(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getSiteDemandByWaterType(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getPumpStateAndYield(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getYieldByWaterSource(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);
}
