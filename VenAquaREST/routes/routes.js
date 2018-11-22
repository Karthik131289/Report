var appRouter = function (app, soap, prop, xmlConverter) {

    var url = prop.get('soap-server.url');
    const basePath = prop.get('base-path');

    app.get( basePath + "/", function (req, res) {
        res.status(200);
        res.header('content-type', 'text/plain; charset=utf-8');
        res.send('welcome to WeGot Data Visuals REST API');
    });

    app.get( basePath + "/api", function (req, res) {
        logRequestDetails(req);
        const message = getHomePageBody(basePath);
        res.status(200);
        res.header('content-type', 'text/html; charset=utf-8');
        res.send(message);
    });

    app.get( basePath + "/health", function (req, res) {
        logRequestDetails(req);
        var message = "Health Check : api not running healthily...";
		console.info("Connecting soap-server : " + url);
        soap.createClient(url, function (err, client) {
            if (err) {
                message = "Health Check : could not init soap clent...";
                console.error(message);
                res.status(200);
                res.header('content-type', 'text/plain; charset=utf-8');
                res.send(message);
            }
            else {
                console.info("soap client initiated..");
                var body = "{\"siteId\": 4,\"chartType\": \"pie chart\",\"fromDate\": \"2018-04-01\",\"toDate\": \"2018-04-05\"}";
                var args = {RequestInfo: body};
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByWaterSource(args, function (err, response) {
                    if (err) {
                        message = "Health Check : error occured in soap request...";
                        console.error(message);
                        res.status(200);
                        res.header('content-type', 'text/plain; charset=utf-8');
                        res.send(message);
                    } else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        message = "Health Check : success...";
                        console.error(message);
                        res.status(200);
                        res.header('content-type', 'text/plain; charset=utf-8');
                        res.send(message);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/usage/watersource", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByWaterSource(args, function (err, response) {
                    if (err) {
                        return sendErrorResponse(err, response, res, xmlConverter);
                    } else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        showResponseHeaders(response);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/usage/blocklevel", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByBlockLevel(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/highusers", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getHighUsers(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/demand/watertype", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteDemandByWaterType(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/pump/yield", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getPumpYield(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/trend/watersource", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteTrendByWaterSource(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post( basePath + "/site/watermap", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                return sendServerInternalError(err, res);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteWaterMap(args, function (err, response) {
                    if (err)
                        return sendErrorResponse(err, response, res, xmlConverter);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        res.status(200);
                        return res.send(resp);
                    }
                })
            }
        });
    });
}

function getHomePageBody(basePath) {
    var message = "<html><body>";
    message = message + "<b>WeGot Data Visuals REST API</b>";
    message = message + "<br><br><strong>&nbsp; EndPoints : </strong>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/usage/watersource </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/usage/blocklevel </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/highusers </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/demand/watertype </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/pump/yield </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/trend/watersource </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> " + basePath + "/site/watermap </em>";
    message = message + "</body></html>";
    return message;
}

function getSOAPBody(httpReq) {
    var body = JSON.stringify(httpReq.body);
    return {RequestInfo: body};
}

function logRequestDetails(httpReq) {
    console.debug("request URL : %s %s %s/%s", httpReq.method, httpReq.url, httpReq.protocol.toUpperCase(), httpReq.httpVersion);
    console.debug("Headers : %s", JSON.stringify(httpReq.headers));
    console.debug("Client Address : %s", httpReq.connection.remoteAddress);
}

function showResponseHeaders(soapRes) {
    console.debug(soapRes);
}

function sendErrorResponse(err, response, res, xmlConverter) {
    console.error(err);
    const soapBody = response.body;
    const options = {compact: true, spaces: 4};
    var bodyObj = xmlConverter.xml2js(soapBody, options);
    var errorInfo = bodyObj['S:Envelope']['S:Body']['S:Fault']['detail']['ns2:VenaquaException'];
    const errorMsg = errorInfo.errorMessage._text;
    const errorCode = errorInfo.errorCode._text;
    console.error("errorMessage : " + errorMsg);
    console.error("errorCode : " + errorCode);

    var errResp = createErrorObj(errorMsg)
    console.debug("response: " + errResp);
    res.header("content-type", "application/json; charset=utf-8");
    res.status(errorCode);
    return res.send(errResp);
}

function createErrorObj(errMsg) {
    return { "message" : errMsg};
}

function sendServerInternalError(err, res) {
    res.status(500);
    console.error(err);
    res.send();
}

module.exports = appRouter;