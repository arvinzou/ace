var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    id: '',
    activityInfo: {},
    btnFlag: 1,
    nowDate: new Date().Format("yyyy-MM-dd HH:mm:ss"),
    method: false,
    hiddenBtn: true,
    showBtn: false,
  },
  onLoad: function(options) {
    var that = this;
    var id = options.id;
    that.setData({
      id: id
    });
    //判断有没有鉴权
    if (!util.is_login()) {
      var u = '../activityInfo/index?id=' + id;
      wx.navigateTo({
        url: '../userinfo/index?url=' + encodeURIComponent(u) + '&type=navigateTo'
      });
      return;
    }
   
  },

  ifCreatBtn: function() {
    var that = this;
    // 已经登陆过了。获取用户信息
    var sysUserInfo = util.getSysUser();
    // 如果没有用户信息
    if (sysUserInfo) {
      if ((that.data.activityInfo.category == 4 && sysUserInfo.person.politicalStatus == 1) || (that.data.activityInfo.initiatorId == sysUserInfo.person.id)) {
        return;
      }
    }
    that.setData({
      hiddenBtn: false,
    });
    that.btnControl();
  },

  // 获取列表
  initdata: function() {
    var that = this;
    util.request(cfg.findActivity, {
        id: that.data.id,
      },
      function(rst) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
        that.data.activityInfo = rst.data;

        that.setBarTitleText(rst.data.title);
        that.ifCreatBtn();
        that.setData({
          activityInfo: rst.data
        });
        console.log(that.data.activityInfo);
      }
    );
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var id = this.data.id;
    if (!id) {
      wx.navigateBack({})
      return;
    }
    this.initdata(); //初始化数据
  },

  btnControl: function() {
    var that = this;
    if (that.data.activityInfo.dendline > that.data.nowDate) {
      util.request(cfg.applyStatus, {
          activityId: that.data.activityInfo.id,
        },
        function(rst) {
          //        code:1、未注册和鉴权2、账户类型为组织，3、未报名，4、已报名
          var code = rst.data.code;
          that.setData({
            btnFlag: code,
          })
        }
      );
    } else {
      that.setData({
        btnFlag: 0,
      })
    }
  },

  setBarTitleText: function(tit) {
    wx.setNavigationBarTitle({
      title: tit
    })
  },
  // 查看更多参与人员
  viewParticipants: function(e) {
    var p = e.currentTarget.dataset.id;
    var that = this;
    var flag = false;
    var sysUserInfo = util.getSysUser();
    //如果没有注册，尝试重新申请获取一次。
    if (sysUserInfo) {
      console.log(sysUserInfo);
      flag = that.data.activityInfo.sId == sysUserInfo.person.id;
    }
    wx.navigateTo({
      url: '../participants/index?id=' + p + "&flag=" + flag,
    })
  },
  apply: function() {
    var that = this;
    var coin = that.data.activityInfo.participant;
    if (coin < 0) {
      wx.showModal({
        title: '提示',
        content: "参加活动需要" + coin + "爱心币",
        success: function(res) {
          console.log(res)
          if (res.confirm) {
            var user = util.getSysUser();
            if (user.person.validPoints > -coin) {
              wx.navigateTo({
                url: '../enterActivity/index?id=' + that.data.activityInfo.id + '&category=' + that.data.activityInfo.category
              })
            } else {
              wx.showToast({
                title: '爱心币不足',
                icon: 'success',
                duration: 2000,
                complete: function() {
                  return;
                }
              });
            }
          } else {
            return;
          }
        }
      })
    } else {
      wx.navigateTo({
        url: '../enterActivity/index?id=' + that.data.activityInfo.id + '&category=' + that.data.activityInfo.category
      })
    }
  },
  onPageScroll: function(e) {
    if (this.data.hiddenBtn) {
      return;
    }
    if (e.scrollTop <= 0) {
      // 滚动到最顶部
      e.scrollTop = 0;
    } else if (e.scrollTop > this.data.scrollHeight) {
      // 滚动到最底部
      e.scrollTop = this.data.scrollHeight;
    }
    if (e.scrollTop > this.data.scrollTop || e.scrollTop >= this.data.scrollHeight) {
      this.setData({
        showBtn: true,
      });
    } else {
      this.setData({
        showBtn: false,
      });
    }
    //给scrollTop重新赋值 
    this.setData({
      scrollTop: e.scrollTop
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh();
    return;
  },
  signIn: function() {
    wx.navigateTo({
      url: '../regist/index',
    })
  },
  onShareAppMessage: function(res) {
    var that = this;
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: that.data.tit,
      id: that.data.id,
      path: '/page/activityInfo/index?id=' + id,
      success: function(res) {
        // 转发成功
      },
      fail: function(res) {
        // 转发失败
      }
    }
  }
})