var appRouter = function(app, soap_client) {
	app.get("/", function(req, res) {
		res.send("Hello World");
	});
	
	app.post("/account", function(req, res) {
		var body = JSON.stringify(req.body);
		soap_client.get
		return res.send(body);
		/* if(!req.body.username || !req.body.password || !req.body.twitter) {
			return res.send({"status": "error", "message": "missing a parameter"});
		} else {
			return res.send(req.body);
		} */
	});
}

module.exports = appRouter;