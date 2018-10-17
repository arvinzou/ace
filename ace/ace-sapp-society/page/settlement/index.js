var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
     commodityInfo: null,
     commodityId: null,
     userinfoData: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({ commodityId: options.commodityId})
      console.log("+====================================="+util.isLogin());
      if (!util.is_login()) {
          wx.navigateTo({
              url: "../userinfo/index?url=../settlement/index"
          });
      }
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
    initCommodityInfo: function (){
      var that = this;
        util.request(cfg.siteDetail, { "commodityId": that.data.commodityId},
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  that.setData({ commodityInfo: ret.data});
              } else {

              }

          }
      ); 
  },
  formSubmit: function(e){
      var that = this;
      var detailState = e.detail.value.detailState;
      var orderDetail = { "commodityId": that.data.commodityId, "commodityInfo": that.data.commodityInfo.commodityName, "commodityCover": that.data.commodityInfo.commodityCover, "salePrice": that.data.commodityInfo.costPoints, "purchaseQty":1};
      var orderDetailList = [];
      orderDetailList.push(orderDetail);
      if (detailState == '' || detailState == undefined || detailState == null){
          wx.showModal({
              title: '提示',
              content: '收货地址不能为空！',
              success: function (res) { }
          });
          return;
      }
      var receiveName = null;
      var receivePhone = null;
      if (that.data.userinfoData.regType == '2'){
          receiveName = that.data.userinfoData.societyOrg.contactPerson;
          receivePhone = that.data.userinfoData.societyOrg.contactPhone;
      }else{

      }
      util.request(cfg.createOrder, { "params": JSON.stringify({ "nickname": that.data.userinfoData.nickName, "headimgurl": that.data.userinfoData.avatarUrl, "payType": '1', 'payAmount': that.data.commodityInfo.costPoints, "receiveType": '2', "address": detailState, "receiveName": receiveName, "receivePhone": receivePhone, "detailList": orderDetail }) },
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
      if (wx.getStorageSync('userinfo')) {
          if (that.initUserData() == false) {
              wx.navigateTo({ url: "../regist/index" });
          }
      }
      that.initCommodityInfo();
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