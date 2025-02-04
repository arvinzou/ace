var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tab: 0,
    nList: [], //未采集
    yList: [], //已采集
    nNum: 0,
    yNum: 0,
    pageType: null,
    isCJ: false, // 是否采集
    tabIsShow: true, //选显卡是否显示
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log('选项卡数字：' + options.tab);
    var that = this;
    var tabIndex = options.tab;
    if (tabIndex) {
      if (tabIndex == 1) {
        that.setData({
          tabIsShow: false,
        });
        wx.setNavigationBarTitle({
          title: '已采集路段',
        });
      } else {
        wx.setNavigationBarTitle({
          title: '待采集路段',
        });
      }
      that.setData({
        tab: tabIndex
      });
    }
    if (options.type) {
      that.setData({
        pageType: options.type
      });
    }

    that.initNList('');
    that.initYList('');
  },
  /**
   * 选项卡切换
   */
  changeTab: function(e) {
    var that = this;
    var index = e.target.dataset.index;
    that.setData({
      tab: e.target.dataset.index,
      pageType: 'cj'
    });
    if (index == 0) {
      wx.setNavigationBarTitle({
        title: '待采集路段',
      });
    } else {
      wx.setNavigationBarTitle({
        title: '已采集路段',
      });
    }
  },

  searchSomeThing: function(e) {
    var that = this;
    var name = e.detail.value
    if (that.data.tab == 0) {
      that.initNList(name);
      return;
    }
    that.initYList(name);
    return;
  },
  /**
   * 获取已采集路段信息
   */
  initYList: function(rname) {
    var that = this;
    util.request(cfg.server + '/taa/www/road/findSectionList', {
        category: "1",
        keyWord: rname
      },
      function(res) {
        if (res.status == 0) {
          that.setData({
            yList: res.data.rows,
            yNum: res.data.total
          });
        } else {
          wx.showModal({
            title: '提示',
            content: res.info,
            success: function(res) {}
          });
        }

      }
    );
  },
  /**
   * 获取未采集路段信息
   */
  initNList: function(rname) {
    var that = this;
    util.request(cfg.server + '/taa/www/road/findSectionList', {
        category: "0",
        keyWord: rname
      },
      function(res) {
        if (res.status == 0) {
          that.setData({
            nList: res.data.rows,
            nNum: res.data.total
          });
        } else {
          wx.showModal({
            title: '提示',
            content: res.info,
            success: function(res) {}
          });
        }

      }
    );
  },
  /**
   * 首页公用浮窗。显示与隐藏
   */
  selectRoadSection: function(e) {
    var that = this;
    console.log(e);
    var roadSectionId = app.globalData.collectionId = e.currentTarget.dataset.id;
    var roadSectionName = e.currentTarget.dataset.name; //路段起点和结尾
    var startName = e.currentTarget.dataset.startname;
    var endName = e.currentTarget.dataset.endname;
    var skipType = that.data.pageType;
    console.log(skipType)
    if (skipType == 'kb') {
      //快报选择路段
      app.globalData.sectionId = roadSectionId;
      app.globalData.sectionName = roadSectionName;
      app.globalData.tab = 0;
      app.globalData.roadManId = e.currentTarget.dataset.roadmanid;
      app.globalData.roadManName = e.currentTarget.dataset.roadmanname;
      app.globalData.startName = startName;
      app.globalData.endName = endName;
      wx.navigateBack({
        changed: true
      });
    } else if (skipType == 'cj') {
      console.log(app.globalData.collectionId)
      //路段采集
      app.globalData.startName = startName;
      app.globalData.endName = endName;
      app.globalData.cjSectionId = roadSectionId;
      app.globalData.isCJ = false; //是否是采集信息
      app.globalData.tab = 1;
        wx.navigateBack({
            changed: true
        });
    } else if (skipType == 'xb') {
      //续报选择路段
      console.log(e);
      app.globalData.sectionId = roadSectionId; //路段id
      app.globalData.sectionName = roadSectionName; // 路段名
      app.globalData.roadManId = e.currentTarget.dataset.roadmanid; // 路长id
      app.globalData.roadManName = e.currentTarget.dataset.roadmanname; // 路长姓名
      wx.navigateBack({
        changed: true
      });

    } else if (skipType == 'lh') {
      // 路患上报
      console.log(e);
      app.globalData.sectionId = roadSectionId;
      app.globalData.sectionName = roadSectionName;
      app.globalData.roadManId = e.currentTarget.dataset.roadmanid;
      app.globalData.roadManName = e.currentTarget.dataset.roadmanname;
      wx.navigateBack({
        changed: true
      });
    }
  },
  /**
   * 查看已采集详情
   */
  showRoadSection: function(e) {
    app.globalData.startName = e.currentTarget.dataset.startname;
    app.globalData.endName = e.currentTarget.dataset.endname;
    app.globalData.collectionId = e.currentTarget.dataset.id;
    app.globalData.isCJ = true; // 是否采集
    console.log("选择================" + app.globalData.startName);
      wx.navigateBack({
          changed: true
      });
  },
  /**
   * 重置
   */
  resetSection: function(e) {
    var that = this;
    var sectionId = e.currentTarget.dataset.id;
    wx.showModal({
      title: '提示',
      content: '确定重置？',
      success: function(res) {
        if (res.confirm) {
          util.request(cfg.server + '/taa/www/road/resetSectionGPS', {
              sectionId: sectionId
            },
            function(res) {
              if (res.status == 0) {
                wx.showModal({
                  title: '提示',
                  content: res.info,
                  showCancel: false,
                  success: function(res) {}
                });
                //设置已采集信息（浮窗）
                that.initYList('');
                that.data.pageType = 'cj';
                that.selectRoadSection(e);
              } else {
                wx.showModal({
                  title: '提示',
                  content: res.info,
                  success: function(res) {}
                });
              }

            }
          );
        } else if (res.cancel) {

        }
      }
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    console.log(1);
    app.globalData.startName = null;
    app.globalData.endName = null;
    app.globalData.cjSectionId = null;
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})