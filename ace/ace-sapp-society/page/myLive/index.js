var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
var start =0;
var limit =5;
Page({
  data: {
    serverfile: cfg.serverfile,
    items: [],
    listLive:[],
    startX: 0, //开始坐标
    startY: 0
  },
  onReady: function (res) {
    that.initData();
  },
  onShow: function () {
      var that=this;
      that.initData();
  },
  onLoad: function () {
    var that = this;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../myLive/index" });
    }else{
      that.setData({
        userinfo: wx.getStorageSync('userinfo')
      });
    }
  },
  onPullDownRefresh: function () {
    let that = this;
    start+=5;
    that.initData();

  },
  add: function () {
    let that = this;
    wx.navigateTo({
      url: '../liveCsl/index?act=add',
    });
  },
  initData: function () {
    var that = this;
    util.request(cfg.findLiveList, {start:0,limit:9999 },
      function (data) {
        console.log(data);
        data.rows.forEach(function (o, i) {
          o.isTouchMove = false;
          //that.data.listLive.push(o);
        });
        that.setData({
          listLive: data.rows
        });
        wx.stopPullDownRefresh();
      }
    );
  },

  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    var that=this;
    that.data.listLive.forEach(function (v, i) {
      if (v.isTouchMove)//只操作为true的
        v.isTouchMove = false;
    })
    that.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      listLive: that.data.listLive
    })
    
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;//当前索
    var startX = that.data.startX;//开始X坐标
    var startY = that.data.startY;//开始Y坐标
    var touchMoveX = e.changedTouches[0].clientX;//滑动变化坐标
    var touchMoveY = e.changedTouches[0].clientY;//滑动变化坐标

    //获取滑动角度
    var angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    that.data.listLive.forEach(function (v, i) {
      v.isTouchMove = false
      //滑动超过30度角 return
      if (Math.abs(angle) > 30) return;
      if (i == index) {
        if (touchMoveX > startX) //右滑
          v.isTouchMove = false
        else //左滑
          v.isTouchMove = true
      }
    })
    //更新数据
    that.setData({
      listLive: that.data.listLive
    })
    
  },
  /**
   * 计算滑动角度
   * @param {Object} start 起点坐标
   * @param {Object} end 终点坐标
   */
  angle: function (start, end) {
    var _X = end.X - start.X,
      _Y = end.Y - start.Y
    //返回角度 /Math.atan()返回数字的反正切值
    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);
  },
  //删除事件

  del: function (e) {
    var that=this;
    var url = cfg.deleteLiveByLiveId;
    console.log();
    util.request(url, { id: e.currentTarget.dataset.id},
      function (data) {
        if(data.status==1){
          wx.showModal({
            title: '提示',
            content: data.errorMessage,
            showCancel: false
          })
        }else{
          that.data.listLive.splice(e.currentTarget.dataset.index, 1)
          that.setData({
            listLive: that.data.listLive
          })
        }
      }
    );
    
  },
  edit:function(e){
    console.log(e);
    var that = this;
    wx.navigateTo({
      url: '../liveCsl/index?act=edit&id=' + e.currentTarget.dataset.id
    });
  },
  preview: function (e) {
    console.log(e);
    var that = this;
    wx.navigateTo({
      url: '../live/index?id=' + e.currentTarget.dataset.id
    });
  },
  submit: function (e) {
    var that = this;
    var url = cfg.updateAuditStatus;
    console.log();
    util.request(url, { id: e.currentTarget.dataset.id, status:'1' },
      function (data) {
        if (data.status == 1) {
          wx.showModal({
            title: '提示',
            content: data.errorMessage,
            showCancel: false
          })
        } else {
          that.data.listLive=[];
          that.initData();
        }
      }
    );
  },
  rpt: function (e) {
    console.log(e);
    var that = this;
    wx.navigateTo({
      url: '../rpt/index?id=' + e.currentTarget.dataset.id
    });
  },
});
