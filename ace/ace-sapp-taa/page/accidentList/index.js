var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [],
    rIndex: 0, //区域筛选索引
    region: [],
    regionArray: [], // 行政区下拉数据
    areaCode: '4307'

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.initAccidentList(''); //初始化事故列表
    that.initRegionList(); //初始化地区列表
  },
  /**
   * 编辑事故
   */
  editAccident: function() {
    wx.navigateTo({
      url: '../accidentDetail/index',
    });
  },
  /**
   * 选择行政区
   */
  bindRegionChange:function(e){
    var that = this;
    var tempIndex = e.detail.value;
    var areaArr = that.data.regionArray;
    that.setData({
      rIndex: tempIndex,
      areaCode: areaArr[tempIndex].code
    });
    that.initAccidentList('');
    console.log("areaCode===========================" + that.data.areaCode);
  },
  /**
   * 获取行政区划列表
   */
  initRegionList: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/report/findDistrictList', {},
      function(res) {
        if (res.status == 0) {
          var data = res.data;
          var tempArr = [];
          for (var i = 0; i < data.length; i++) {
            tempArr.push(data[i].name);
          }
          that.setData({
            region: tempArr,
            regionArray: data
          });
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
   * 初始化事故列表
   */
  initAccidentList: function(name) {
    var that = this;
    util.request(cfg.server + '/taa/www/traAcc/findList', {
        start: 0,
        limit: 999,
        keyword: name,
        areaCode: that.data.areaCode
      },
      function(res) {
        if (res.status == 0) {
          var dataRows = res.data.rows;
          console.log(res.data.rows)
          for (var i = 0; i < dataRows.length; i++) {
            if (dataRows[i].lastModifyDate) {
              dataRows[i].lastModifyDate = dataRows[i].lastModifyDate.substring(5, dataRows[i].lastModifyDate.length)
            }
          }
          that.setData({
            list: dataRows
          });
          console.log(dataRows);
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
   * 链接事故详情 
   */
  linkAccidentDetails:function(e){
    var traAccId = e.currentTarget.dataset.id;  //查询事故详情id
    var linkid = e.currentTarget.dataset.linkid;    //链接id
    wx.navigateTo({
      url: '../accidentDetail/index?id=' + traAccId + '&linkid=' + linkid + '',
    });
  },

  /**
   * 续报事件 */
  editAccident: function(e) {
    var traAccId = e.target.dataset.id;
    var linkid = e.currentTarget.dataset.linkid;
    wx.navigateTo({
      url: '../accidentDetail/index?id=' + traAccId + '&linkid=' + linkid +'',
    });
  },
  /**
   * 撤销事件 */
  revoke: function(e) {
    var traAccId = e.target.dataset.id;
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确认撤销该事故？',
      success: function(res) {
        if (res.confirm) {
          util.request(cfg.server + '/taa/www/traAcc/repealReport', {
              traAccId: traAccId
            },
            function(resp) {
              if (resp.status == 0) {
                wx.showModal({
                  title: '提示',
                  content: '撤销' + resp.info,
                  showCancel:false
                })
                that.initAccidentList('');
              } else {
                wx.showModal({
                  title: '提示',
                  content: '撤销' + resp.info,
                  showCancel: false
                })
              }
            }
          );
        }
      }
    });

  },
  searchRoad: function(e) {
    var that = this;
    var name = e.detail.value;
    that.initAccidentList(name);
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