var appRouter = function (app, soap, prop) {

    var url = prop.get('soap-server.url');

    app.get("/", function (req, res) {
        res.status(200);
        res.send("welcome to WeGot Data Visuals REST API");
    });

    app.get("/api", function (req, res) {
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
        res.header("content-type", "text/html; charset=utf-8");
        res.status(200);
        res.send(message);
    });

    app.post("/site/usage/watersource", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByWaterSource(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/usage/blocklevel", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteUsageByBlockLevel(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/highusers", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getHighUsers(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/demand/watertype", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteDemandByWaterType(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/pump/yield", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getPumpYield(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/trend/watersource", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteTrendByWaterSource(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });

    app.post("/site/watermap", function (req, res) {
        console.log("request URL : " + req.method + " " + req.url);
        var body = JSON.stringify(req.body);
        var args = {RequestInfo: body};
        soap.createClient(url, function (err, client) {
            if (err) {
                console.error(err);
            }
            else {
                console.log("soap client initiated..");
                client.VenAquaReport.VenAquaReportImplPort.getSiteWaterMap(args, function (err, response) {
                    if (err)
                        console.error(err);
                    else {
                        var resp = JSON.parse(JSON.stringify(response))['return'];
                        console.log("response: " + resp);
                        res.header("content-type", "application/json; charset=utf-8");
                        return res.send(resp);
                    }
                })
            }
        });
    });
}

module.exports = appRouter;