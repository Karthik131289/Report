package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.ws.exception.ReportException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface VenAquaReport {
    public static final String SERVICE_NAME = "VenAquaReport";

    @WebMethod()
    public String getSiteUsageByWaterSource(@WebParam(name = "RequestInfo") String requestInfo) throws ReportException;

    @WebMethod
    public String getSiteUsageByBlockLevel(@WebParam(name = "RequestInfo") String requestInfo);

    @WebMethod
    public String getHighUsers(@WebParam(name = "RequestInfo") String requestInfo);

    @WebMethod
    public String getSiteDemandByWaterType(@WebParam(name = "RequestInfo") String requestInfo);

    @WebMethod
    public String getPumpYield(@WebParam(name = "RequestInfo") String requestInfo);

    @WebMethod
    public String getSiteTrendByWaterSource(@WebParam(name = "RequestInfo") String requestInfo);

    @WebMethod
    public String getSiteWaterMap(@WebParam(name = "RequestInfo") String requestInfo);
}
