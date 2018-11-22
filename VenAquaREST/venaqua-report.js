var soap = require("soap");
var express = require("express");
var cors = require("cors");
var bodyParser = require("body-parser");
var timeout = require('connect-timeout');
const PropertiesReader = require('properties-reader');
const xmlConverter = require('xml-js');

/*** Read application.properties ***/
const prop = PropertiesReader('application.properties');

var app = express();
app.use(cors());
app.use(timeout(180000));
app.use(haltOnTimedout);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.disable('etag');

var routes = require("./routes/routes.js")(app, soap, prop, xmlConverter);

var server = app.listen(process.env.PORT || 3000, function () {
    console.log("***** WeGot Data Visuals REST API *****");
    console.log("Server running on port : %s...", server.address().port);
});

function haltOnTimedout(req, res, next){
  if (!req.timedout) next();
}