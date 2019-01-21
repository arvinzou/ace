var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      array: ['常德市公安交警支队', '武陵区公安交警支队', '鼎城区公安交警支队','经开区公安交警支队'],
      objectArray: [
          {
              id: 0,
              name: '常德市公安交警支队'
          },
          {
              id: 1,
              name: '武陵区公安交警支队'
          },
          {
              id: 2,
              name: '鼎城区公安交警支队'
          },
          {
              id: 3,
              name: '经开区公安交警支队'
          }
      ],
      index: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     
  },
  bindPickerChange(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
        index: e.detail.value
    });
  }, 
  initDeptList: function(e){
      util.request('http://127.0.0.1' +'/taa/www/register/findDeptList', {
         
      },
      function (ret) {
           console.log(ret);
            wx.showModal({
                title: '提示',
                content: ret.info,
                success: function (res) { }
            });
        }
      );
  },
  formSubmit: function(e){
      var name = e.detail.value.name;
      var mobile = e.detail.value.mobile;
      var copNo = e.detail.value.copNo;
      var dept = e.detail.value.copNo;
      if (name == undefined || name == '' || name == null) {
          wx.showModal({
              title: '提示',
              content: '请输入姓名！',
              success: function (res) { }
          });
          return;
      }
      if (mobile == undefined || mobile == '' || mobile == null) {
          wx.showModal({
              title: '提示',
              content: '请输入手机号码',
              success: function (res) { }
          });
          return;
      }
      if (mobile == undefined || mobile == '' || mobile == null) {
          wx.showModal({
              title: '提示',
              content: '请输入手机号码',
              success: function (res) { }
          });
          return;
      }
      if (dept == undefined || dept == '' || dept == null){
          wx.showModal({
              title: '提示',
              content: '请选择所属单位',
              success: function (res) { }
          });
          return;
      }
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
      if (!util.is_login()) {
          wx.navigateTo({
              url: "../userinfo/index?url=../regist/index&type=switchTab"
          });
          return;
      }else{
          that.initDeptList();
      }
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