var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rIndex: 0, //区域筛选索引
    region: [],
    regionArray: [], // 行政区下拉数据
    areaCode: '4307',
    yhList: [] //隐患列表
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.initDict(); //初始化字典
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
    this.initRoadHiddenDangerList(); // 初始化隐患列表
  },
  /**
   *  初始化字典
   */
  initDict: function() {
    var that = this;
    util.request(cfg.server + '/portal/content/common/js/dict_taa.js', {},
      function(res) {
        var oIndex = res.indexOf("=");
        var retData = res.substring(oIndex + 1, res.length);
        var retObj = JSON.parse(retData);
        var lkyhObj = retObj['175'];
        var yhType = [];
        var yhTypeArray = [];
        if (lkyhObj) {
          // 移除code=='',name==''的那条数据
          for (var i = 0; i < lkyhObj.length; i++) {
            if (lkyhObj[i].CODE == '' && lkyhObj[i].NAME == '') {
              lkyhObj.splice(i, 1);
            }
          }
          for (var i = 0; i < lkyhObj.length; i++) {
            yhType.push(lkyhObj[i].NAME);
            yhTypeArray.push(lkyhObj[i]);
          }
          app.globalData.yhTypeArray = yhTypeArray;
        }
      },
      function(res) {
        console.log(res);
      }
    );
  },
  /**
   * 初始化上报路况隐患列表
   */
  initRoadHiddenDangerList: function(e) {
    var that = this;
    // 测试查询隐患列表 
    util.request(cfg.server + '/taa/www/roadDangerReport/findReportList', {},
      function(res) {
        if (res.status == 0) {
          var yhList = res.data.rows;
          if (yhList.length > 0) {
            that.setData({
              yhList: res.data.rows
            });
          } else {
            that.setData({
              yhList: []
            })
          }
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },
  /**
   * input查询上报路况隐患列表
   */
  searchRoad: function(e) {
    var that = this;
    var name = e.detail.value;
  },
  /**
   * 链接路况隐患详情
   */
  linkRoadHiddenDangerDetails: function(e) {
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../roadHiddenDangerDetails/index?id=' + id + '',
    });
  },
  /**
   * 撤销事件
   */
  revoke: function(e) {
    console.log(e);
    var that = this;
    var reportId = e.currentTarget.dataset.id;
    wx.showModal({
      title: '提示',
      content: '确认撤销该条隐患信息？',
      success: function(res) {
        if (res.confirm) {
          // 测试查询隐患列表 
          util.request(cfg.server + '/taa/www/roadDangerReport/repealReport', {
              reportId: reportId
            },
            function(res) {
              console.log(res);
              if (res.status == 0) {
                wx.showModal({
                  title: '提示',
                  content: '撤销' + res.errorMessage,
                  showCancel: false
                });

                that.onShow(); //刷新页面
              } else {
                wx.showModal({
                  title: '提示',
                  content: '撤销' + res.errorMessage,
                  showCancel: false
                })
              }
            }
          );
        }
      }
    })

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