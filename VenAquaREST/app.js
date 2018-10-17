var soap = require("soap");
var express = require("express");
var cors = require("cors");
var bodyParser = require("body-parser");
var timeout = require('connect-timeout');
const PropertiesReader = require('properties-reader');

/*** Read application.properties ***/
const prop = PropertiesReader('./resources/application.properties');

var app = express();
app.use(cors());
app.use(timeout(60000));
app.use(haltOnTimedout);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var routes = require("./routes/routes.js")(app, soap, prop);

var server = app.listen(process.env.PORT || 3000, function () {
    console.log("Listening on port %s...", server.address().port);
});

function haltOnTimedout(req, res, next){
  if (!req.timedout) next();
}