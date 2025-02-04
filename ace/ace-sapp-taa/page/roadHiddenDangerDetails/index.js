var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    reportId:'',   //隐患id
    detailsInfo:{},   //详情信息
    yhTypeArray:[],   //隐患类型
    headerHeight:0,    // id = 'header'高度  
    boxHeight: 0,    // id = 'box'高度 
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var reportId = options.id;
    // 获取隐患id
    that.setData({
      reportId: reportId
    });
    //创建节点选择器
    var query = wx.createSelectorQuery();
    query.select('#header').boundingClientRect();
    query.selectViewport().scrollOffset();
    query.exec(function (res) {
      that.setData({
        headerHeight: res[0].height
      })
    });
    //创建节点选择器
      var query2 = wx.createSelectorQuery();
    query2.select('#box').boundingClientRect();
    query2.selectViewport().scrollOffset();
    query2.exec(function (res) {
      that.setData({
        boxHeight: res[0].height
      });
    });
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
    var that = this;
    that.initRoadHiddenDangerDetails();  //详情信息
  },
  
  /**
   * 初始化隐患详情
   */
  initRoadHiddenDangerDetails:function(e){
    var that = this;
    var reportId = that.data.reportId;
    var yhTypeArray = app.globalData.yhTypeArray;
    console.log(yhTypeArray)
    // 测试查询隐患列表 
     util.request(cfg.server + '/taa/www/roadDangerReport/selectRoadDangerReportByPrimaryKey', {
      reportId: reportId,
    },
      function (res) {
        console.log(res);
        if (res.status == 0) {
          //处理隐患类型
          var type = res.value.type;
          if ( yhTypeArray && yhTypeArray.length > 0 ){
            for (var i = 0; i < yhTypeArray.length; i++) {
              if (type == yhTypeArray[i].CODE){
                res.value.type = yhTypeArray[i].NAME;
              }
            }
            that.setData({
              detailsInfo: res.value
            });
          }
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function (res) { }
          });
        }
      }
    );
  },
  /**
   * 滚动指定区域
   */
  scrollToViewFn: function(e){
    var that = this;
    var headerHeight = that.data.headerHeight;
    var boxHeight = that.data.boxHeight;
    // 将页面滚动到目标位置
    wx.pageScrollTo({
      scrollTop: headerHeight + boxHeight ,
      duration: 300
    })
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

  },
})