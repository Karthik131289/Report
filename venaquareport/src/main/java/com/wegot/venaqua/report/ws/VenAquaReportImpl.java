package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.json.JSONConverter;
import com.wegot.venaqua.report.ws.db.DBConnection;
import com.wegot.venaqua.report.ws.db.DBManager;
import com.wegot.venaqua.report.ws.db.query.HouseUsageEnum;
import com.wegot.venaqua.report.ws.db.query.HouseUsageQuery;
import com.wegot.venaqua.report.ws.db.query.SiteUsageByWaterSourceQuery;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.handler.auth.AuthenticationHandler;
import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.pie.WaterSourceUsageResponse;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {
    private final Logger log = LoggerFactory.getLogger(VenAquaReportImpl.class);
    private AuthenticationHandler authHandler = null;
    private DBManager dbManager;

    public VenAquaReportImpl() throws ReportException {
        this.authHandler = VenAquaReportHelper.getAuthHandler();
        this.dbManager = DBManager.getInstance();
    }

    @Override
    public String getSiteUsageByWaterSource(String requestInfo) {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                SiteUsageByWaterSourceQuery query = new SiteUsageByWaterSourceQuery();
                Connection connection = dbConnection.getConnection();
                WaterSourceUsageResponse responseObj = query.execute(connection, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = JSONConverter.CovertToJsonAsString(responseObj.getWaterSourceList());
            }
        } catch (ReportException e) {
            //throw e;
            e.printStackTrace();
            response = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteUsageByBlockLevel(String requestInfo) {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {

            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                HouseUsageQuery<BlockLevelUsageResponse> query = new HouseUsageQuery<>();
                Connection connection = dbConnection.getConnection();
                BlockLevelUsageResponse responseObj = query.execute(connection, HouseUsageEnum.BLOCKLEVEL, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(requestInfoObj.getUid());
                response = JSONConverter.CovertToJsonAsString(responseObj);
            }
        }  catch (ReportException e) {
            //throw e;
            e.printStackTrace();
            response = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getHighUsers(String requestInfo) {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                HouseUsageQuery<HighUsersResponse> query = new HouseUsageQuery<>();
                Connection connection = dbConnection.getConnection();
                HighUsersResponse responseObj = query.execute(connection, HouseUsageEnum.HIGHUSERS, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(requestInfoObj.getUid());
                response = JSONConverter.CovertToJsonAsString(responseObj);
            }
        }  catch (ReportException e) {
            //throw e;
            e.printStackTrace();
            response = e.getMessage();
        }  catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteDemandByWaterType(String requestInfo) {
        String response = null;
        System.out.println("**** Request Info ****");
        System.out.println(requestInfo);
        try {
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
