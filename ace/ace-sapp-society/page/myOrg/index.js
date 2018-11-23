var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userinfoData: null,
    },

    /**
     * 生命周期函数--监听页面加载;
     */
    onLoad: function(options) {
        var that = this;
        that.setData({ userinfoData: wx.getStorageSync("sysUserInfo")});
    },
    initUserData: function () {
        var that = this;
        let userInfo = util.getSysUser();
        if (!userInfo) {
            wx.navigateTo({
                url: "../regist/index"
            });
        }
        that.setData({
            userinfoData: userInfo,
            isRegist: true,
        });

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
       that.initUserData();
        that.setData({ userinfoData: wx.getStorageSync("sysUserInfo") });
        wx.stopPullDownRefresh();
        return;
    },
    phoneCall: function(){
        wx.makePhoneCall({
            phoneNumber: '0736-7111361' 
        });
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