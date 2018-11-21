var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
Page({
  data: {
    serverfile: cfg.serverfile,
    items: [{ id: '1', name: "" }],
    startX: 0, //开始坐标
    startY: 0,
    hidden: true,
    nocancel: false,
    answerDept: "非公经济服务中心",
    operator: ""
  },
  onReady: function (res) {

  },
  onLoad: function (params) {
    var that = this;
    that.setData(params);
    that.initData(that.data.id);
  },
  onPullDownRefresh: function () {
    let that = this;
    that.initData(that.data.id);
  },
  initData: function (id) {
    var that = this;
    util.request(cfg.selectAppealCaseByPrimaryKey, { id: id },
      function (data) {
        that.setData({
          o: data.value
        });

        console.log(data.value);
        if (data.status != 0) {
          wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        } else {
          wx.setNavigationBarTitle({ title: that.data.o.title });
        }
        wx.stopPullDownRefresh();
      }
    );
  },
  previewImage: function (e) {
    console.log(e);
    var urls=[];
    urls.push(e.target.dataset.url);
    wx.previewImage({
      current: e.target.dataset.url, 
      urls: urls
    })
  },
  previewFile: function (e) {
    console.log(e);
    var that = this;
    wx.showNavigationBarLoading();
    wx.downloadFile({
      url: e.currentTarget.dataset.url,
      success: function (res) {
        let path = res.tempFilePath
        console.log(path)
        wx.hideNavigationBarLoading(); //完成停止加载
        wx.openDocument({
          filePath: path,
          success: function (res) {
          },
          fail: function (err) {
            console.log(err);
            wx.previewImage({
              current: e.currentTarget.dataset.url, 
              urls: [e.currentTarget.dataset.url] 
            })
          }
        })
      },
      fail(err) {
        typeof op.fail === 'function' && op.fail(err)
      }
    })
  },
  //手指触摸动作开始 记录起点X坐标
  touchstart: function (e) {
    //开始触摸时 重置所有删除
    this.data.items.forEach(function (v, i) {
      if (v.isTouchMove)//只操作为true的
        v.isTouchMove = false;
    })
    this.setData({
      startX: e.changedTouches[0].clientX,
      startY: e.changedTouches[0].clientY,
      items: this.data.items
    })
  },
  //滑动事件处理
  touchmove: function (e) {
    var that = this,
      index = e.currentTarget.dataset.index,//当前索引
      startX = that.data.startX,//开始X坐标
      startY = that.data.startY,//开始Y坐标
      touchMoveX = e.changedTouches[0].clientX,//滑动变化坐标
      touchMoveY = e.changedTouches[0].clientY,//滑动变化坐标
      //获取滑动角度
      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });
    that.data.items.forEach(function (v, i) {
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
      items: that.data.items
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
    this.data.items.splice(e.currentTarget.dataset.index, 1)
    this.setData({
      items: this.data.items
    })
  },
  updateAccept: function (e) {
    this.setData({
      hidden: false
    });
  },
  cancel: function () {
    this.setData({
      hidden: true
    });
  },
  confirm: function () {
    var that = this;
    util.request(cfg.updateAccept, { id: that.data.o.id, answerDept: that.data.answerDept, operator: that.data.operator, client: 'app' },
      function (data) {
        console.log(data.value);
        wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        if (data.status == 0) {
          that.setData({
            hidden: true
          });
        }
      }
    );
  },
  bindinputAnswerDept: function (e) {
    console.log(e);
    this.setData({ answerDept: e.detail.value });
  },
  bindinputOperator: function (e) {
    console.log(e);
    this.setData({ operator: e.detail.value });
  },
  updateDetailsOfProgress: function (e) {
    var that = this;
    wx.navigateTo({
      url: '../detailsOfProgress/index?id=' + that.data.id,
    })
  },
  answer: function (e) {
    var that = this;
    wx.navigateTo({
      url: '../answer/index?id=' + that.data.id,
    })
  }
});
