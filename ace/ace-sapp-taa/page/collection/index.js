var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tab: 0,
    nList: [],
    yList: [],
    nNum:0,
    yNum:0,
    pageType: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      console.log(2);
      var that = this;
      var tabIndex = options.tab;
      if(tabIndex){
          tab: tabIndex
      }
      if (options.type){
          that.setData({
              pageType: options.type
          });
      }
     
      that.initNList('');
      that.initYList('');
  },
  changeTab: function(e){
      var that = this;
      that.setData({
          tab: e.target.dataset.index
      });
  },

searchSomeThing:function(e){
    var that=this;
    var name = e.detail.value
    if(that.data.tab==0){
        that.initNList(name);
        return;
    }
    that.initYList(name);
    return;
},
  /**
   * 获取已采集路段信息
   */
  initYList: function(rname){
      var that = this;
      util.request(cfg.server + '/taa/www/road/findSectionList', { category: "1", name:rname},
          function (res) {
              if (res.status == 0) {
                 that.setData({
                     yList: res.data.rows,
                     yNum: res.data.total
                 });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
              }

          }
      );
  },
  /**
   * 获取未采集路段信息
   */
    initNList: function (rname){
      var that = this;
      util.request(cfg.server + '/taa/www/road/findSectionList', { category: "0", name: rname },
          function (res) {
              if (res.status == 0) {
                  that.setData({
                      nList: res.data.rows,
                      nNum: res.data.total
                  });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
              }

          }
      );
  },
  selectRoadSection: function (e) {
        var that = this;

      var roadSectionId = e.currentTarget.dataset.id;
      var roadSectionName = e.currentTarget.dataset.name;
      var skipType = that.data.pageType;
        if (skipType == 'kb') {
            //快报选择路段
            app.globalData.sectionId = roadSectionId;
            app.globalData.sectionName = roadSectionName;
            app.globalData.tab = 0,
            wx.switchTab({
                url: '../index/index',
            });
        } else if (skipType == 'cj'){
            //路段采集
            var startName = e.currentTarget.dataset.startname;
            var endName = e.currentTarget.dataset.endname;

            app.globalData.startName = startName;
            app.globalData.endName = endName;
            app.globalData.cjSectionId = roadSectionId;
            app.globalData.tab = 1
            wx.navigateBack({ changed: true });
        } 
    },

    resetSection: function(e){
        var that = this;
        var sectionId = e.currentTarget.dataset.id;
        util.request(cfg.server + '/taa/www/road/resetSectionGPS', { sectionId: sectionId },
            function (res) {
                if (res.status == 0) {
                    wx.showModal({
                        title: '提示',
                        content: res.info,
                        success: function (res) { }
                    });
                    that.initYList('');
                } else {
                    wx.showModal({
                        title: '提示',
                        content: res.info,
                        success: function (res) { }
                    });
                }

            }
        );
    },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      console.log(1);
      app.globalData.startName = null;
      app.globalData.endName = null;
      app.globalData.cjSectionId = null;
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})