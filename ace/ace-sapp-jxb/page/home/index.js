var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
var page = 1;
Page({
  data: {
    serverfile: cfg.serverfile,
    currentTab: 0,
    navbar: ['直播', '课程'],
    items: [],
    startX: 0, //开始坐标
    startY: 0
  },

  onReady: function (res) {
    console.log('index.js.onReady');
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
  onLoad: function () {
    var that = this;
    that.initData();
  },
  onPullDownRefresh: function () {
    let that = this;
    that.initData();

  },
  add: function () {
    let that = this;
    wx.showActionSheet({
      itemList: ['创建直播', '创建课程'],
      success: function (res) {
        if (!res.cancel) {
          if (res.tapIndex == 0) {
            wx.navigateTo({
              url: '../liveCsl/index?act=add',
            });
          } else if (res.tapIndex == 1) {
            wx.navigateTo({
              url: '../courseCsl/index?act=add',
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
  initData: function () {
    var that = this;
    util.request(cfg.getLiveListByUserId, { page: page },
      function (data) {
        data.data.forEach(function (v, i) {
          v.isTouchMove = false;
        });
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
  },
  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    var that=this;
    if (that.data.currentTab==0){
      that.data.listLive.forEach(function (v, i) {
        if (v.isTouchMove)//只操作为true的
          v.isTouchMove = false;
      })
      that.setData({
        startX: e.changedTouches[0].clientX,
        startY: e.changedTouches[0].clientY,
        listLive: that.data.listLive
      })
    }else{
      that.data.listCourse.forEach(function (v, i) {
        if (v.isTouchMove)//只操作为true的
          v.isTouchMove = false;
      })
      that.setData({
        startX: e.changedTouches[0].clientX,
        startY: e.changedTouches[0].clientY,
        listCourse: that.data.listCourse
      })
    }
    
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this;
    var index = e.currentTarget.dataset.index;//当前索引
    var startX = that.data.startX;//开始X坐标
    var startY = that.data.startY;//开始Y坐标
    var touchMoveX = e.changedTouches[0].clientX;//滑动变化坐标
    var touchMoveY = e.changedTouches[0].clientY;//滑动变化坐标
    //获取滑动角度
    var angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    if (that.data.currentTab == 0) {
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
    }else{
      that.data.listCourse.forEach(function (v, i) {
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
        listCourse: that.data.listCourse
      })
    }
    
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
    if (that.data.currentTab == 0) {
      that.data.listLive.splice(e.currentTarget.dataset.index, 1)
      that.setData({
        listLive: that.data.listLive
      })
    }else{
      that.data.listCourse.splice(e.currentTarget.dataset.index, 1)
      that.setData({
        listCourse: that.data.listCourse
      })
    }
  },
  edit:function(e){
    console.log(e);
    var that = this;
    if (that.data.currentTab == 0) {
      wx.navigateTo({
        url: '../liveCsl/index?act=edit&id=' + e.currentTarget.dataset.id
      });
    }else{
      wx.navigateTo({
        url: '../courseCsl/index?act=edit&id=' + e.currentTarget.dataset.id
      });
    }
  },
  preview: function (e) {
    console.log(e);
    var that = this;
    if (that.data.currentTab == 0) {
      wx.navigateTo({
        url: '../live/index?id=' + e.currentTarget.dataset.id
      });
    } else {
      wx.navigateTo({
        url: '../course/index?id=' + e.currentTarget.dataset.id
      });
    }
  }
});
