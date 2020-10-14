'use strict';
var path = require('path');
var express = require('express');

var app = express();


app.use(express.static(path.join(__dirname, './src/main/resources/static/built')));
app.use(express.static(path.join(__dirname, './src/main/resources/static/css')));
app.use(express.static(path.join(__dirname, './src/main/resources/html')));

// Allows you to set port in the project properties.
app.set('port', process.env.PORT || 3000);

var server = app.listen(app.get('port'), function() {
    console.log('listening');
});