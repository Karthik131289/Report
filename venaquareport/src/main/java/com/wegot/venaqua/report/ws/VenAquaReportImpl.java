package com.wegot.venaqua.report.ws;

import com.wegot.venaqua.report.ws.db.DBConnection;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.db.DBManager;
import com.wegot.venaqua.report.ws.db.query.*;
import com.wegot.venaqua.report.ws.exception.*;
import com.wegot.venaqua.report.ws.handler.auth.AuthenticationHandler;
import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.gantt.PumpYieldResponse;
import com.wegot.venaqua.report.ws.response.pie.WaterSourceUsageResponse;
import com.wegot.venaqua.report.ws.response.sparkline.WaterSourceTrendResponse;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
import com.wegot.venaqua.report.ws.response.usage.WaterTypeDemandResponse;
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                SiteUsageByWaterSourceQuery query = new SiteUsageByWaterSourceQuery();
                connection = dbConnection.getConnection();
                WaterSourceUsageResponse responseObj = query.execute(connection, requestInfoObj.getSiteId(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj.getWaterSourceList());
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                HouseUsageQuery<BlockLevelUsageResponse> query = new HouseUsageQuery<>();
                connection = dbConnection.getConnection();
                String siteName = DBHelper.getSiteName(connection, requestInfoObj.getSiteId());
                BlockLevelUsageResponse responseObj = query.execute(connection, HouseUsageEnum.BLOCKLEVEL, requestInfoObj.getSiteId(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(siteName);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj);
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                HouseUsageQuery<HighUsersResponse> query = new HouseUsageQuery<>();
                connection = dbConnection.getConnection();
                String siteName = DBHelper.getSiteName(connection, requestInfoObj.getSiteId());
                HighUsersResponse responseObj = query.execute(connection, HouseUsageEnum.HIGHUSERS, requestInfoObj.getSiteId(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                responseObj.setName(siteName);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj);
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                connection = dbConnection.getConnection();
                WaterTypeDemandQuery query = new WaterTypeDemandQuery();
                WaterTypeDemandResponse responseObj = query.execute(connection, requestInfoObj.getSiteId(),
                        requestInfoObj.getFromDate(), requestInfoObj.getToDate(), requestInfoObj.getFromTime(),
                        requestInfoObj.getToTime());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj);

                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/resources/sample/SiteDemandByWaterType.json");
                if (resourceAsStream != null) {
                    response = IOUtils.toString(resourceAsStream);
                }
            }

        } catch (AuthException | RequestException | ProcessException /*| ResponseException*/ e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
                DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
                //Connection connection = dbConnection.getConnection();
                PumpYieldQuery query = new PumpYieldQuery();
                //PumpYieldResponse responseObj = query.execute(connection, requestInfoObj.getSiteId(), requestInfoObj.getDate());
                //dbConnection.releaseConnection(connection);
                //response = VenAquaReportHelper.convertResponseObjToString(responseObj);
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                connection = dbConnection.getConnection();
                WaterSourceTrendQuery query = new WaterSourceTrendQuery();
                WaterSourceTrendResponse responseObj = query.execute(connection, requestInfoObj.getSiteId(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj.getWaterSources());
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = null;
        try {
            RequestInfo requestInfoObj = VenAquaReportHelper.prepareRequestInfoObj(requestInfo);
            VenAquaReportHelper.validateRequestInfo(requestInfoObj);
            InvocationInfo invocationInfo = VenAquaReportHelper.prepareInvocationInfo(requestInfoObj);
            boolean authenticate = this.authHandler.authenticate(invocationInfo);
            if (authenticate) {
                SiteWaterMapQuery query = new SiteWaterMapQuery();
                connection = dbConnection.getConnection();
                WaterMapResponse responseObj = query.execute(connection, requestInfoObj.getSiteId(), requestInfoObj.getFromDate(), requestInfoObj.getToDate());
                dbConnection.releaseConnection(connection);
                response = VenAquaReportHelper.convertResponseObjToString(responseObj.getSiteDayUsageList());
            }
        } catch (AuthException | RequestException | ProcessException | ResponseException e) {
            if (e instanceof ProcessException && connection != null) {
                dbConnection.releaseConnection(connection, true);
            }
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
