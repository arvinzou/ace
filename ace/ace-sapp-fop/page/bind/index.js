var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
var countdown = 30;
var stop = true;
var mobile = "";
var captcha="";
Page({
  data: {
    serverfile: cfg.serverfile,
    stop: true,
    btnName: "获取验证码",
  },
  onReady: function (res) {
    var that = this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });

  },
  onLoad: function (params) {
    var that = this;
    that.setData(params);
  },
  onPullDownRefresh: function () {
    let that = this;
  },
  goback:function(e){
    var that=this;
    wx.navigateTo({ url:that.data.url});
  },
  bindgetuserinfo:function(e){
    console.log(e);
    var that = this;
    util.login(that.getPhoneNumber);
  },
  bind:function(){
    var that=this;
    util.bind(captcha,mobile,that.goback);
  },
  getPhoneNumber: function (e) {
    console.log(e);
    var that = this;
    that.sendCmccByMobile();
    that.settime();
  },
  bindinputMobile: function (e) {
    console.log(e);
    mobile = e.detail.value;
  },
  bindinputCaptcha: function (e) {
    console.log(e);
    captcha = e.detail.value;
  },
  settime: function () {
    var that = this;
    var btnName = "获取验证码";
    if (countdown == 0) {
      btnName = "获取验证码";
      countdown = 30;
      stop = true;
    } else {
      stop = false;
      btnName = "重新发送 " + countdown + "";
      countdown--;
    }
    that.setData({
      countdown: countdown,
      btnName: btnName,
      stop: stop
    })
    if (!stop) {
      setTimeout(function () {
        that.settime()
      }, 1000)
    }

  },
  sendCmccByMobile: function () {
    util.request(cfg.sendCmccByMobile, { mobile: mobile },
      function (data) {
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          success: function (res) {

          }
        })
      }
    );
  }
});
