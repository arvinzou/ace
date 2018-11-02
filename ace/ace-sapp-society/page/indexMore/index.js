var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      activity: null,
      days: '00',
      hours: '00',
      minutes: '00',
      isEnd: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.activityIng(999);
  },
  activityIng: function (limit) {
        var that = this;
        util.request(cfg.findActivitying, {"start":0, "limit":limit},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    for (var i = 0; i < ret.data.length; i++ ){
                        //剩余报名天数
                        ret.data[i].leastDays = that.diy_time(new Date(), ret.data[i].dendline);
                        ret.data[i].dendline = ret.data[i].dendline.substring(0, 16);
                        ret.data[i].days = that.data.days;
                        ret.data[i].hours = that.data.hours;
                        ret.data[i].minutes = that.data.minutes;
                        ret.data[i].isEnd = that.data.isEnd;
                    }
                    that.setData({ activity: ret.data });

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
    diy_time: function (startTime, endTime) {
        var that = this;
        //time1 = Date.parse(time1);
        //time2 = Date.parse(new Date(time2));
        //return Math.abs(parseInt((time2 - time1) / 1000 / 3600 / 24));

        var date3 = util.formateStringToDate(endTime).getTime() - startTime.getTime();   //时间差的毫秒数      
        //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000))
        //计算出小时数
        var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000))
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000))
        //计算相差秒数
        var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
        var seconds = Math.round(leave3 / 1000)
        console.log(" 相差 " + days + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒");
        that.setData({
            days: days,
            hours: hours,
            minutes: minutes
        });
        if (date3 < 0) {
            that.setData({
                isEnd: true
            });
        }
    },
    showActivityDetail: function (e) {
        var that = this;
        wx.navigateTo({
            url: '../activityInfo/index?id=' + e.currentTarget.dataset.id,
        })
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