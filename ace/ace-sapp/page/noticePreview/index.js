var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    serverfile: cfg.serverfile,
    o: {},
    userList:[]

  },
  onReady: function (res) {
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  onLoad: function (options) {
    var that = this;
    this.setData({
      id: options.id
    }),
      util.request(cfg.selectTaskCmccById, { id: options.id },
        function (data) {
          var obj=data.value.tel;
          var list = obj.split(';');
          var userList=[];
          for(var i in list){
            var e=list[i];
            var items = e.split(',');
            userList.push({ name: items[1], tel: items[0]});
          }
          that.setData({
            o: data.value,
            userList: userList,
            config: cfg
          });
          wx.setNavigationBarTitle({ title: that.data.o.taskName });
        }
      );
  }
})