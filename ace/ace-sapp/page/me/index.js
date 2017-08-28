var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()
Page({
  data: {
    flag: false,
    latitude: 29.031673,
    longitude: 111.698497
  },
  a: function () {
    this.setData({ flag: false })
  },
  b: function () {
    this.setData({ flag: true })
  }
})