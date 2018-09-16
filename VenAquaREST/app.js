/* var soap = require("soap");
var express = require("express");
var bodyParser = require("body-parser");

var url = 'http://venaqua-report.herokuapp.com/report?wsdl';
var soap_client;
soap.createClient(url, function(err, client) {
	if(err) {
		console.error(err);
	}
	else {
		console.log("soap client initiated..");
		soap_client = client;
	}
});

var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var routes = require("./routes/routes.js")(app, soap_client);

var server = app.listen(3000, function () {
    console.log("Listening on port %s...", server.address().port);
});
 */
 
var soap = require("soap");
var express = require("express");
var bodyParser = require("body-parser");

var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var routes = require("./routes/routes.js")(app, soap);

var server = app.listen(process.env.PORT || 3000, function () {
    console.log("Listening on port %s...", server.address().port);
});

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", '*');
    res.header("Access-Control-Allow-Credentials", true);
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
    res.header("Access-Control-Allow-Headers", 'Origin,X-Requested-With,Content-Type,Accept,content-type,application/json');
    next();
});