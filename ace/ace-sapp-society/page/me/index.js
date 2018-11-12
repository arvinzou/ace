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
        userId: null,
        wxUser:null

    },

    /**
     * 生命周期函数--监听页面加载;
     */
    onLoad: function(options) {
        var that = this;
        console.log(options);
        console.log("util.is_login()=============================================" + util.is_login());
        if (!util.isLogin()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../me/index&type=switchTab"
            });
        } else {
            if (wx.getStorageSync('userinfo')) {
                that.setData({ userId: wx.getStorageSync('WX-SESSION-ID')});
                that.initUserData();
                that.initWxUserData();
            };
        }
    },
    initUserData: function() {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function(ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    util.setSysUser(ret.data);
                    that.setData({
                        userinfoData: ret.data,
                        isRegist: true,
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
    initWxUserData: function(){
        var that = this;
        util.request(cfg.server + '/portal/www/selectWxUserByPrimaryKey.do', { "id": wx.getStorageSync('WX-SESSION-ID')},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({ wxUser: ret.value});
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
            that.initWxUserData();
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
        that.initWxUserData();
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