var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userinfoData: null,
        isRegist: false,
        num1: parseInt(Math.random() * 100),
        num2: parseInt(Math.random() * 100),
        num3: parseInt(Math.random() * 100),
        userId: null
    },

    /**
     * 生命周期函数--监听页面加载;
     */
    onLoad: function(options) {
        var that = this;
        console.log("util.is_login()=============================================" + util.is_login());
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../me/index&type=switchTab"
            });
        } else {
            if (wx.getStorageSync('userinfo')) {
                that.setData({ userId: wx.getStorageSync('WX-SESSION-ID')});
                that.initUserData();
            }

        }
    },
    initUserData: function() {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function(ret) {
                if (ret.status == 0) {
                    that.setData({
                        userinfoData: ret.data,
                        isRegist: true
                    });
                } else {
                    that.setData({
                        isRegist: false
                    });
                    wx.navigateTo({ url: "../regist/index" });
                }

            }
        );
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },
    loveRanking: function() {
        wx.navigateTo({
            url: '../loveRanking/index'
        })
    },
    myActivity: function() {
        wx.navigateTo({
            url: '../activityManage/index'
        })
    },
    historyOrder: function() {
        wx.navigateTo({
            url: '../historyOrder/index'
        })
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {
        var that = this;
        if (wx.getStorageSync('userinfo')) {
            that.setData({userId: wx.getStorageSync('WX-SESSION-ID')})
            that.initUserData();
        }
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
        let that=this;
        that.setData({
            num1: parseInt(Math.random() * 100),
            num2: parseInt(Math.random() * 100),
            num3: parseInt(Math.random() * 100),
        });
        util.delSysUser();
        that.initUserData();
        wx.stopPullDownRefresh();
        return;
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