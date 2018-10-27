package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.ws.db.DBConnection;
import com.wegot.venaqua.report.ws.db.DBManager;
import com.wegot.venaqua.report.ws.db.query.HouseUsageEnum;
import com.wegot.venaqua.report.ws.db.query.HouseUsageQuery;
import com.wegot.venaqua.report.ws.db.query.SiteUsageByWaterSourceQuery;
import com.wegot.venaqua.report.ws.db.query.SiteWaterMapQuery;
import com.wegot.venaqua.report.ws.exception.*;
import com.wegot.venaqua.report.ws.handler.auth.AuthenticationHandler;
import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.pie.WaterSourceUsageResponse;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
import com.wegot.venaqua.report.ws.response.waterMap.WaterMapResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.io.InputStream;
import java.sql.Connection;

@WebService(endpointInterface = "com.wegot.venaqua.report.ws.VenAquaReport", serviceName = VenAquaReport.SERVICE_NAME,
        wsdlLocation = "localhost")
public class VenAquaReportImpl implements VenAquaReport {
    private final Logger log = LoggerFactory.getLogger(VenAquaReportImpl.class);
    private AuthenticationHandler authHandler = null;
    private DBManager dbManager;

    public VenAquaReportImpl() throws ReportException {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        this.authHandler = VenAquaReportHelper.getAuthHandler();
        this.dbManager = DBManager.getInstance();
    }

    @Override
    public String getSiteUsageByWaterSource(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                SiteUsageByWaterSourceQuery query = new SiteUsageByWaterSourceQuery();
                Connection connection = dbConnection.getConnection();
                WaterSourceUsageResponse responseObj = query.execute(connection, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj.getWaterSourceList());
            }
        }  catch (AuthException | RequestException| ProcessException | ResponseException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteUsageByBlockLevel(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                HouseUsageQuery<BlockLevelUsageResponse> query = new HouseUsageQuery<>();
                Connection connection = dbConnection.getConnection();
                BlockLevelUsageResponse responseObj = query.execute(connection, HouseUsageEnum.BLOCKLEVEL, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(requestInfoObj.getUid());
                response = VenAquaReportHelper.convertResponseObjToString(responseObj);
            }
        }  catch (AuthException | RequestException | ProcessException | ResponseException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getHighUsers(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                HouseUsageQuery<HighUsersResponse> query = new HouseUsageQuery<>();
                Connection connection = dbConnection.getConnection();
                HighUsersResponse responseObj = query.execute(connection, HouseUsageEnum.HIGHUSERS, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(requestInfoObj.getUid());
                response = VenAquaReportHelper.convertResponseObjToString(responseObj);
            }
        }  catch (AuthException | RequestException | ProcessException | ResponseException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteDemandByWaterType(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/resources/sample/SiteDemandByWaterType.json");
                if (resourceAsStream != null) {
                    response = IOUtils.toString(resourceAsStream);
                }
            }

        } catch (AuthException | RequestException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getPumpYield(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/resources/sample/PumpYield.json");
                if (resourceAsStream != null) {
                    response = IOUtils.toString(resourceAsStream);
                }
            }
        } catch (AuthException | RequestException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteTrendByWaterSource(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/resources/sample/SiteTrendByWaterSource.json");
                if (resourceAsStream != null) {
                    response = IOUtils.toString(resourceAsStream);
                }
            }
        } catch (AuthException | RequestException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }

    @Override
    public String getSiteWaterMap(String requestInfo) throws VenaquaException {
        String response = null;
        log.debug("**** Request Info ****");
        log.debug(requestInfo);
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                SiteWaterMapQuery query = new SiteWaterMapQuery();
                Connection connection = dbConnection.getConnection();
                WaterMapResponse responseObj = query.execute(connection, requestInfoObj.getUid(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj.getSiteDayUsageList());
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            VenAquaReportHelper.throwVenaquaException(e);
        }
        log.debug("**** Response Info ****");
        log.debug(response);
        return response;
    }
}
