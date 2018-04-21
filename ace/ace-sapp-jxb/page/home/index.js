var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
var page=1;
Page({
    data: {
        serverfile: cfg.serverfile,
        currentTab:0,
        navbar: ['直播', '课程']
    },
    onReady: function (res) {
        console.log('index.js.onReady');
        var that=this;
        wx.setNavigationBarColor({
            frontColor: cfg.frontColor,
            backgroundColor: cfg.backgroundColor,
            animation: {
                duration: 400,
                timingFunc: 'easeIn'
            }
        });
       
    },
    onLoad: function () {
        var that = this; 
        that.initData();
    },
    onPullDownRefresh:function(){
      let that = this;
      that.initData();
     
    },
    add:function(){
      let that = this;
      wx.showActionSheet({
        itemList: ['创建直播', '创建课程'],
        success: function (res) {
          if (!res.cancel) {
            if (res.tapIndex == 0) {
              wx.navigateTo({
                url: '../liveAdd/index',
              });
            } else if (res.tapIndex == 1) {
              wx.navigateTo({
                url: '../courseAdd/index',
              });
            }
          }
        }
      })
    },
     navbarTap: function (e) {
      console.log(e);
      let that = this;
      if (that.data.currentTab == e.target.dataset.idx) {
        return false;

      } else {
        that.setData({
          currentTab: e.target.dataset.idx
        })
      }
    },
    initData:function(){
      var that=this;
      util.request(cfg.getLiveListByUserId, {page:page},
        function (data) {
          that.setData({
            listLive: data.data
          });
          wx.stopPullDownRefresh();
        }
      );
      util.request(cfg.getCourseListByUserId, { page: page },
        function (data) {
          that.setData({
            listCourse: data.data
          });
          wx.stopPullDownRefresh();
        }
      );
    }
});
