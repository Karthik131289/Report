package com.wegot.venaqua.report.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface VenAquaReport {
    public static final String SERVICE_NAME = "VenAquaReport";

    @WebMethod()
    public String getSiteConsumptionByWaterSource(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getResidenceConsumption(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getResidenceConsumption2(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getSiteDemandByWaterType(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getPumpStateAndYield(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getSitePerformanceByWaterSource(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getSiteConsumptionForRange(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);

    @WebMethod
    public String getSiteConsumptionForDay(@WebParam(name = "RequestInfo", mode = WebParam.Mode.IN) String requestInfo);
}
