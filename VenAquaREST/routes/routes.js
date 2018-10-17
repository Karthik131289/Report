var appRouter = function (app, soap, prop) {

    var url = prop.get('soap-server.url');

    app.get("/", function (req, res) {
        res.status(200);
        res.header('content-type', 'text/plain; charset=utf-8');
        res.send('welcome to WeGot Data Visuals REST API');
    });

    app.get("/api", function (req, res) {
        logRequestDetails(req);
        const message = getHomePageBody();
        res.status(200);
        res.header('content-type', 'text/html; charset=utf-8');
        res.send(message);
    });

    app.post("/site/usage/watersource", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByWaterSource(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        showResonseHeaders(response);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/usage/blocklevel", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByBlockLevel(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/highusers", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getHighUsers(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/demand/watertype", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteDemandByWaterType(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/pump/yield", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getPumpYield(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/trend/watersource", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteTrendByWaterSource(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/watermap", function (req, res) {
        logRequestDetails(req);
        var args = getSOAPBody(req);
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.info("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteWaterMap(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.debug("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });
}

function getHomePageBody() {
    var message = "<html><body>";
    message = message + "<b>WeGot Data Visuals REST API</b>";
    message = message + "<br><br><strong>&nbsp; EndPoints : </strong>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/usage/watersource </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/usage/blocklevel </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/highusers </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/demand/watertype </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/pump/yield </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/trend/watersource </em>";
    message = message + "<br>&nbsp;&nbsp;&nbsp; POST &nbsp;<em> /site/watermap </em>";
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

function showResonseHeaders(soapRes) {
    console.debug(soapRes);
}

module.exports = appRouter;