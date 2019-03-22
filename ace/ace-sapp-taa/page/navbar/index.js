var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // yhRole: false,    //隐患权限
    // dataRole: false,   // 事故分析
    yhRole: true,    //隐患权限
    dataRole: true,    // 事故分析
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    this.initUserData();     //初始化用户信息
    this.initAuthority();     //初始化权限信息
  },
  /**
   *   链接事故快报
   */
  linkSGKB: function (e) {
    wx.navigateTo({
      url: '../accidentKb/index',
    });
  },
  /**
   * 链接隐患上报
   */
  linkYHSB: function (e) {
    var that = this;
    var yhRole = that.data.yhRole;
    if (yhRole) {
      wx.navigateTo({
        url: '../roadHiddenDanger/index',
      });
    } else {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '无权限进入，请联系管理员'
      });
    }
  },
  /**
   * 链接路段采集
   */
  linkLDCJ: function (e) {
    wx.navigateTo({
      url: '../sectionCj/index',
    });
  },
  /**
   * 链接事故列表
   */
  linkSGLB: function (e) {
    wx.navigateTo({
      url: '../accidentList/index',
    });
  },
  /**
  * 链接隐患列表
  */
  linkYHLB: function (e) {
    var that = this;
    var yhRole = that.data.yhRole;
    if (yhRole) {
      wx.navigateTo({
        url: '../roadHiddenDangerList/index',
      });
    } else {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '无权限进入，请联系管理员',
      });
    }

  },
  /**
  * 链接事故分析
  */
  linkSGFX: function (e) {
    var that = this;
    var dataRole = that.data.dataRole;   //权限掌上驾驶舱
    if (dataRole) {
      wx.navigateTo({
        url: '../datas/index',
      });
    } else {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '无权限进入，请联系管理员',
      })
    }
  },
  /**
   * 初始化用户信息
   */
  initUserData: function () {
    var that = this;
    util.request(cfg.server + '/taa/www/register/findCustomerVo', {},
      function (res) {
        console.log(res);
        if (res.status == 0) {
          that.setData({
            userData: res.data
          });
        } else {
          if (res.info == '用户尚未注册') {
            wx.navigateTo({
              url: '../regist/index',
            });
          } else {
            wx.showModal({
              title: '提示',
              content: res.info,
              success: function (res) { }
            });
          }
        }

      }
    );
  },
  /**
  * 初始化权限
  */
  initAuthority: function (e) {
    var that = this;
    var yhRoleStr = '0763f1b1-437a-4d6f-af3f-ab46a0e0b60e';  //隐患管理员
    var dataRoleStr = '764a02db-4c20-4c31-8040-07a3d8dc01b9'; //掌上驾驶舱
    // 测试查询隐患列表 
     util.request(cfg.server + '/taa/www/roadDangerReport/findUserRole', {
    },
      function (res) {
        console.log(res);
        // 比较权限值
        if (res.length > 0) {
          for (var i = 0; i < res.length; i++) {
            if (yhRoleStr == res[i].role_id) {
              that.setData({
                yhRole: true
              });
            }
            if (dataRoleStr == res[i].role_id) {
              that.setData({
                dataRole: true
              });
            }
          }
          console.log(that.data.yhRole);
          console.log(that.data.dataRole);
        }

      },
      function (res) {
        console.log(res);
      }
    );
  },
  /**
   * 编辑警号信息
   */
  edit: function () {
    wx.navigateTo({
      url: '../info/index',
    });
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