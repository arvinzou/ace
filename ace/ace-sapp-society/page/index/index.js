var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        activity: null,
        reportData: null,
        stopwatch: '',
        autoplay: true,
        interval: 5000,
        duration: 500,
        timer: {
            0: {
                hour: "00",
                minute: "00",
                second: "00",
                seconds: 0,
                isEnd: false
            },
            1: {
                hour: "00",
                minute: "00",
                second: "00",
                seconds: 0,
                isEnd: false
            },
            2: {
                hour: "00",
                minute: "00",
                second: "00",
                seconds: 0,
                isEnd: false
            },
            3: {
                hour: "00",
                minute: "00",
                second: "00",
                seconds: 0,
                isEnd: false,
            },
            4: {
                hour: "00",
                minute: "00",
                second: "00",
                seconds: 0,
                isEnd: false
            }
            
        },
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        // 判断有没有鉴权
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../index/index&type=switchTab"
            });
        }
        // 判断有没有登陆
        if (util.isLogin()) {
            if (!util.getSysUser()) {
                that.initUserData();
            }
        }
    },

    /**
     * 正在进行中的活动
     */
    activityIng: function (limit) {
        var that = this;
        util.request(cfg.findActivitying, {
            "start": 0,
            "limit": limit,
            "orderBy": "status asc,dendline",
            "sord": "desc"
        },
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    if (ret.data.length > 0) {
                        for (var i = 0; i < ret.data.length; i++) {
                            ret.data[i].leastDays = that.diy_time(new Date(), ret.data[i].startDate, i);
                            ret.data[i].dendline = ret.data[i].dendline.substring(0, 16);
                            ret.data[i].startDate = ret.data[i].startDate.substring(0, 16);
                            ret.data[i].endDate = ret.data[i].endDate.substring(0, 16);
                            ret.data[i].range = util.formateStringToDate(ret.data[0].dendline).getTime() - new Date().getTime();
                        }
                        that.clockfuntion();
                        that.actionClock();
                        that.setData({
                            activity: ret.data
                        });
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


    clockfuntion:function() {
        var that=this;
        var time = that.data.timer;
        for (var item in time) {
            time[item].seconds = time[item].seconds-1;
            that.formatTime(time[item]);
        }
        that.setData({
            timer: time,
        })
    },

    diy_time: function (startTime, endTime, i) {
        console.log("startTime=,endTime=" + startTime + ":" + endTime);
        var that = this;
        var date3 = util.formateStringToDate(endTime).getTime() - startTime.getTime(); //时间差的毫秒数  
        var time = that.data.timer[i];
        time.seconds = Math.floor(date3 / 1000);
    },

    formatTime: function (time) {
        var that = this;
        var seconds = time.seconds;
        if (seconds < 0) {
            time.isEnd = true;
            return;

        }
        var hours = Math.floor(seconds / 3600);
        var minutes = Math.floor((seconds % 3600) / 60);
        var second = Math.floor(seconds % 3600 % 60);
        time.second = second < 10 ? '0' + second : second;
        time.hour = hours < 10 ? '0' + hours : hours;
        time.minute = minutes < 10 ? '0' + minutes : minutes;
    },
    actionClock: function () {
        var that = this;
        clearInterval(that.data.stopwatch);
        that.data.stopwatch = setInterval(function () {
            console.log(11111111111);
            that.clockfuntion();
        }, 1000);
    },
    /**
     * 精选往事
     */
    initReport: function () {
        var that = this;
        util.request(cfg.findPublicActivityReportList, {
            "start": 0,
            "limit": 2,
            "top": "1"
        },
            function (ret) {
                if (ret.status == 0) {
                    that.setData({
                        reportData: ret.data
                    })
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
    behavior: function () {
        wx.navigateTo({
            url: '../behavior/index'
        })
    },
    idea: function () {
        wx.navigateTo({
            url: '../idea/index'
        })
    },
    showMoreActivity: function () {
        wx.navigateTo({
            url: '../indexMore/index'
        })
    },
    showActivityDetail: function (e) {
        var that = this;
        wx.navigateTo({
            url: '../activityInfo/index?id=' + e.currentTarget.dataset.id,
        })
    },
    viewInfo: function (e) {
        let that = this;
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({
            url: '../reportInfo/index?id=' + p + "&title=" + title
        })
    },
    initUserData: function () {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
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
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        var that = this;
        var that = this;
        that.activityIng(5);
        that.initReport();
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
        that.activityIng(5);
        that.initReport();
        wx.stopPullDownRefresh();
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
});