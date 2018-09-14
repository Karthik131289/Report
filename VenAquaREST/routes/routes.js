/* var appRouter = function(app, soap_client) {
	console.log("Test...");
	
	app.get("/", function(req, res) {
		res.send("Hello World");
	});
	
	app.post("/account", function(req, res) {
		var body = JSON.stringify(req.body);
		var args = {RequestInfo:body};
		console.log(soap_client);
		soap_client.VenAquaReport.VenAquaReportImplPort.getSiteDemandByWaterType(args,function(err,response){
			if(err)
				console.error(err);
			else {
				console.log(response);
				return res.send(response);
			}
		})
	});
}

module.exports = appRouter; */

var appRouter = function(app, soap) {
	var url = 'http://venaqua-report.herokuapp.com/report?wsdl';
	
	app.get("/", function(req, res) {
		res.send("Hello World");
	});
	
	app.post("/account", function(req, res) {
		var body = JSON.stringify(req.body);
		var args = {RequestInfo:body};
		soap.createClient(url, function(err, client) {
			if(err) {
				console.error(err);
			}
			else {
				console.log("soap client initiated..");
				client.VenAquaReport.VenAquaReportImplPort.getSiteDemandByWaterType(args,function(err,response){
					if(err)
						console.error(err);
					else {
						console.log(response);
						return res.send(response);
					}
				})
			}
		});
	});
}

module.exports = appRouter;