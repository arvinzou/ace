var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    timeActive: 'time-active',
    dateArray: [],
    siteId: null,
    siteInfo: null,
    userinfoData: null,
    index:0,
    id: 0,
    siteDate: null,
    timeInterval:'9:00-11:00',
    timeIndex: 0,
    occupy:[]
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({ siteId: wx.getStorageSync('siteId')});
      that.initSpaceOccupy();
      if (!util.is_login()) {
          wx.navigateTo({
              url: "../userinfo/index?url=../siteChoose/index&type=navigateTo"
          });
      }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
      
  },
  
  dateLater: function(dates, later){
      let dateObj = {};
      let show_day_chise = new Array('周日','周一','周二','周三','周四','周五','周六');
      let show_day = new Array('Sun', 'Mon', 'Tues', 'Wed', 'Thur', 'Fri', 'Sat');
      let date = new Date(dates);
      date.setDate(date.getDate() + later);
      let day = date.getDay();
      dateObj.year = date.getFullYear();
      dateObj.month = ((date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : date.getMonth() + 1);
      dateObj.day = (date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate());
      dateObj.week_chise = show_day_chise[day];
      dateObj.week = show_day[day];
      dateObj.date = dateObj.year + "-" + dateObj.month + "-" + dateObj.day;
      return dateObj;
  },

  getCurrentMonthFirst: function(){
      var date = new Date();
      var todate = date.getFullYear() + "-" + ((date.getMonth() + 1) < 10 ? ("0" + (date.getMonth() + 1)) : date.getMonth() + 1) + "-" + (date.getDate() < 10 ? ("0" + date.getDate()) : date.getDate());
      return todate;
  },

  getDates: function (days, todate){
      var that = this;
      todate = that.getCurrentMonthFirst();
      var dateArry = [];
      for(var i=0; i<days; i++){
          var dateObj = that.dateLater(todate, i);
          dateArry.push(dateObj);
      }
      console.log("==========================================" + dateArry);
      that.setData({ dateArray: dateArry});
      that.setData({ siteDate: dateArry[0]});
  },
  initsiteInfo: function () {
        var that = this;
      util.request(cfg.siteDetail, { "commodityId": that.data.siteId },
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({ siteInfo: ret.data });
                } else {

                }

            }
        );
    },
    initUserData: function () {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
                if (ret.status == 0) {
                    that.setData({ userinfoData: ret.data });
                    return true;
                } else {
                    return false;
                }

            }
        );
    },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      var that = this;
      if (wx.getStorageSync('userinfo')) {
          if (that.initUserData() == false) {
              wx.navigateTo({ url: "../regist/index" });
          }
      }
      that.initsiteInfo();
  },
  formSubmit: function(){
      var that = this;
      var spaceOccupyInfo = { "spaceId": that.data.siteId, "reserveDate": that.data.siteDate.date, "reserveInterval": that.data.timeInterval, "year": that.data.siteDate.year, "month": that.data.siteDate.month, "day": that.data.siteDate.day, "whatDay": that.data.siteDate.week, "createUserId": wx.getStorageSync("WX-SESSION-ID"), "createUserName": that.data.userinfoData.nickName, "createDate": new Date()};
      var orderDetail = { "commodityId": that.data.siteId, "commodityName": that.data.siteInfo.commodityName, "commodityCover": that.data.siteInfo.commodityCover, "salePrice": that.data.siteInfo.costPoints, "purchaseQty": 1 };
      var orderDetailList = [];
      orderDetailList.push(orderDetail);
      var receiveName = null;
      var receivePhone = null;
      if (that.data.userinfoData.regType == '2') {
          receiveName = that.data.userinfoData.societyOrg.contactPerson;
          receivePhone = that.data.userinfoData.societyOrg.contactPhone;
      } else {

      }
      console.log("==============================="+spaceOccupyInfo.toString());
      util.request(cfg.createOrder, { "params": JSON.stringify({ "nickname": that.data.userinfoData.nickName, "headimgurl": that.data.userinfoData.avatarUrl, "payType": '1', 'payAmount': that.data.siteInfo.costPoints, "receiveType": '2', "receiveName": receiveName, "receivePhone": receivePhone, "spaceOccupyInfo": spaceOccupyInfo, "detailList": orderDetailList})},
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  wx.showModal({
                      title: '提示',
                      content: ret.info,
                      success: function (res) { }
                  });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },
  chooseDate: function(e){
      var that = this;
      var id = e.currentTarget.dataset.id;
      var tempArr = that.data.dateArray;
      that.setData({
          id: id,
          index: id,
          siteDate: tempArr[id]
      });
  },
 timeSelect: function(e){
     var that = this;
     var value = e.currentTarget.dataset.value;
     if(value == 0){
         that.setData({
             timeInterval: '9:00-11:00',
             timeIndex: 0
         });
     }else{
         that.setData({
             timeInterval: '15:00-17:00',
             timeIndex: 1
         });
     }
     
 },
 initSpaceOccupy: function(e){
     var that = this;
     that.getDates(8);
     util.request(cfg.server + '/society/www/commodity/spaceOccupyInfo', { "spaceId": that.data.siteId },
         function (ret) {
             if (ret.status == 0) {
                 var datas = ret.data;
                 var dates = that.data.dateArray;
                 if (datas.length > 0){
                     for (var i = 0; i < datas.length; i++){
                         for(var j=0; j< dates.length; j++){
                             if (datas[i].reserveDate == dates[j].date && datas[i].reserveInterval == '9:00-11:00'){
                                 dates[j].occupyUp = datas[i].reserveInterval;
                             } else if (datas[i].reserveDate == dates[j].date && datas[i].reserveInterval == '15:00-17:00'){
                                 dates[j].occupyDown = datas[i].reserveInterval;
                             }
                         }
                     }
                     that.setData({ dateArray: dates});
                 }
             } else {
                 wx.showModal({
                     title: '提示',
                     content: ret.errorMessage,
                     success: function (res) { }
                 });
             }

         }
     );
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
      var that = this;
      that.initSpaceOccupy();
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