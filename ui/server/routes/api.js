var express = require('express');
var router = express.Router();
var request = require('request');
var bodyParser = require("body-parser");

var apiHost = process.env.API_SERVICE_HOST || '127.0.0.1';
var apiPort = process.env.API_SERVICE_PORT || '8080';
const APIURL = "http://" + apiHost + ":" + apiPort + "/v1";
console.log("API SERVER: " +APIURL);

router.use(bodyParser.urlencoded({ extended: false }));
router.use(bodyParser.json());

var options = {
  url: APIURL,
  headers: {
    'Accept': 'application/json',
    'Content-type': 'application/json'
  },
  json: true,
};

router.use(function (req, res, next) {
  options.url = APIURL + req.originalUrl.slice(4);
  options.method = req.method;
  options.body = req.body;
  next();
})
router.use(function (req, res, next) {
  // console.log(req.headers);
  // console.log(req.body);
  next();
})

router.get('/**/*', function(req, res, next) {
  request.get(options, function (error, response, body) {
    console.log('error:', error);
    console.log('statusCode:', response && response.statusCode);
    console.log('body:', body);
  }).pipe(res);
});

router.post('/**/*', function(req, res, next) {
  request.post(options, function (error, response, body) {
    console.log('error:', error);
    console.log('statusCode:', response && response.statusCode);
    console.log('body:', body);
  }).pipe(res);
});

router.put('/**/*', function(req, res, next) {
  request.put(options, function (error, response, body) {
    console.log('error:', error);
    console.log('statusCode:', response && response.statusCode);
    console.log('body:', body);
  }).pipe(res);
});

router.delete('/**/*', function(req, res, next) {
  request.delete(options, function (error, response, body) {
    console.log('error:', error);
    console.log('statusCode:', response && response.statusCode);
    console.log('body:', body);
  }).pipe(res);
});

module.exports = router;
