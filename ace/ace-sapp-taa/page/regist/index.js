var util = require("../../util/util.js");
var cfg = require("../../config.js");
var countdown = 60;
var stop = true;
Page({

  /**
   * 页面的初始数据
   */
  data: {
      array: [],
      objectArray: [
         
      ],
      index: 0,
      stop: true,
      btnName: "获取验证码",
      phoneNum: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     
  },
  bindPickerChange(e) {
    var that = this;
    that.setData({
        index: e.detail.value
    });
    console.log("index================================="+that.data.index);
  }, 
  initDeptList: function(e){
      var that = this;
      util.request(cfg.server + '/taa/www/register/findDeptList', {},
          function (res) {
              if (res.status == 0) {
                  var tempArr = [];
                  var objArr = [];
                  var retData = res.rows;
                  for (var i = 0; i < retData.length; i++) {
                      tempArr.push(retData[i].departmentName);
                      var o = {};
                      o.index = i;
                      o.id = retData[i].departmentId;
                      o.name = retData[i].departmentName;
                      objArr.push(o);
                  }
                  that.setData({
                      array: tempArr,
                      objectArray: objArr
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
  formSubmit: function(e){
      var name = e.detail.value.name;
      var mobile = e.detail.value.mobile;
      var copNo = e.detail.value.copNo;
      var dept = e.detail.value.dept;
      var code = e.detail.value.code;
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
              content: '请输入手机号码！',
              success: function (res) { }
          });
          return;
      }
      if (code == undefined || code == '' || code == null){
          wx.showModal({
              title: '提示',
              content: '请输入验证码！',
              success: function (res) { }
          });
          return;
      }
      if (copNo == undefined || copNo == '' || copNo == null) {
          wx.showModal({
              title: '提示',
              content: '请输入警号！',
              success: function (res) { }
          });
          return;
      }
      if (dept == undefined || dept == '' || dept == null){
          wx.showModal({
              title: '提示',
              content: '请选择所属单位！',
              success: function (res) { }
          });
          return;
      }
      util.request(cfg.server + '/taa/www/register/register', { name: name, mobile: mobile, copNo: copNo, deptId: dept, code: code},
          function (ret) {
              if (ret.status == 0) {
                  wx.showModal({
                      title: '提示',
                      content: ret.info,
                      success: function (res) {
                          wx.navigateTo({
                              url: '../index/index',
                          });
                      }

                  });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.info,
                      success: function (res) { }
                  });
              }

          }
      );
  },
    phoneInput: function (e) {
        var that = this;
        that.setData({
            phoneNum: e.detail.value
        });
        console.log("phoneNum启动！");
    },
    sendCode: function (e) {
        var that = this;
        var phone = that.data.phoneNum;
        console.log("========================================phone" + phone);
        if (phone == null || phone == undefined) {
            wx.showModal({
                title: '提示',
                content: '请输入手机号码！',
                success: function (res) { }
            });
            return;
        }
        util.post(cfg.server +'/taa/www/register/sendSmsCode', {
            mobile: phone
        },
            function (ret) {
                console.log(ret);
                wx.showModal({
                    title: '提示',
                    content: ret.info,
                    success: function (res) { }
                })
            }
        );
        that.settime();
    },
    settime: function () {
        var that = this;
        var btnName = "获取验证码";
        if (countdown == 0) {
            btnName = "获取验证码";
            countdown = 30;
            stop = true;
        } else {
            stop = false;
            btnName = "重新发送 " + countdown + "";
            countdown--;
        }
        that.setData({
            countdown: countdown,
            btnName: btnName,
            stop: stop
        })
        if (!stop) {
            setTimeout(function () {
                that.settime()
            }, 1000)
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