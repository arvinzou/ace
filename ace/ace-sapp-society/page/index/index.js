var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        activity: null,
        reportData: null,
        days: '00',
        hours: '00',
        minutes: '00',
        isEnd: false,
        stopwatch: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        // 判断有没有鉴权
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../index/index&type=switchTab"
            });
        }
        // 判断有没有登陆
        if (util.isLogin()){
            if (!util.getSysUser()){
                that.initUserData();
            }
        }
    },

    /**
     * 正在进行中的活动
     */
    activityIng: function(limit) {
        var that = this;
        util.request(cfg.findActivitying, {
                "start": 0,
                "limit": limit,
                "orderBy": "status asc,dendline",
                "sord": "desc"
            },
            function(ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    if (ret.data[0]) {
                        //剩余报名天数
                        ret.data[0].leastDays = that.diy_time(new Date(), ret.data[0].startDate);
                        console.log("剩余报名天数==========================" + ret.data[0].leastDays);
                        ret.data[0].dendline = ret.data[0].dendline.substring(0, 16);
                        ret.data[0].startDate = ret.data[0].startDate.substring(0, 16);
                        ret.data[0].endDate = ret.data[0].endDate.substring(0, 16);

                        ret.data[0].range = util.formateStringToDate(ret.data[0].dendline).getTime() - new Date().getTime();
                        that.setData({
                            activity: ret.data[0]
                        });
                    }

                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.errorMessage,
                        success: function(res) {}
                    });
                }

            }
        );
    },
    diy_time: function(startTime, endTime) {
        console.log("startTime=,endTime=" + startTime + ":" + endTime);
        var that = this;
        var date3 = util.formateStringToDate(endTime).getTime() - startTime.getTime(); //时间差的毫秒数  
        //计算出相差天数
        var seconds = Math.floor(date3 / 1000);
        //计算出小时数
        if (seconds < 0) {
            that.setData({
                isEnd: true
            });
            return;
        }
        that.data.seconds = seconds;
        that.formatTime();
        that.actionClock();

    },

    formatTime: function() {
        var that = this;
        var seconds = that.data.seconds;
        var hours = Math.floor(seconds / 3600);
        var minutes = Math.floor((seconds % 3600) / 60);
        var second = Math.floor(seconds % 3600 % 60);
        that.setData({
            second: second < 10 ? '0' + second : second,
            hours: hours < 10 ? '0' + hours : hours,
            minutes: minutes < 10 ? '0' + minutes : minutes
        });
        if (seconds < 0) {
            that.setData({
                isEnd: true
            });
        }
    },
    actionClock: function() {
        var that = this;
        clearInterval(that.data.stopwatch);
        that.data.stopwatch = setInterval(function() {
            that.data.seconds = (that.data.seconds) - 1;
            that.formatTime();
        }, 1000);
    },
    /**
     * 精选往事
     */
    initReport: function() {
        var that = this;
        util.request(cfg.findPublicActivityReportList, {
                "start": 0,
                "limit": 2,
                "top": "1"
            },
            function(ret) {
                if (ret.status == 0) {
                    that.setData({
                        reportData: ret.data
                    })
                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.errorMessage,
                        success: function(res) {}
                    });
                }

            }
        );
    },
    behavior: function() {
        wx.navigateTo({
            url: '../behavior/index'
        })
    },
    idea: function() {
        wx.navigateTo({
            url: '../idea/index'
        })
    },
    showMoreActivity: function() {
        wx.navigateTo({
            url: '../indexMore/index'
        })
    },
    showActivityDetail: function(e) {
        var that = this;
        wx.navigateTo({
            url: '../activityInfo/index?id=' + e.currentTarget.dataset.id,
        })
    },
    viewInfo: function(e) {
        let that = this;
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({
            url: '../reportInfo/index?id=' + p + "&title=" + title
        })
    },
    initUserData: function() {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function(ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    util.setSysUser(ret.data);
                }
            }
        );
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
        var that = this;
        that.activityIng(1);
        that.initReport();
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
        var that = this;
        that.activityIng(1);
        that.initReport();
        wx.stopPullDownRefresh();
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