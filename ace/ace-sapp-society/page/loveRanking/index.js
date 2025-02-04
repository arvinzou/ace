var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        rankList: [],
        currentTab: 'month',
        personTab: 0,
        userType: "1"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        that.initRank(that.data.currentTab, that.data.userType);
    },
    detail: function(e) {
        wx.navigateTo({
            url: '../coinDetail/index?userId=' + e.currentTarget.dataset.id
        })
    },
    navbarTap: function(e) {
        var that = this;
        var index = e.currentTarget.dataset.index;
        if(index == 0){
            that.setData({ userType: "1" });
        }else{
            that.setData({ userType: "2" });
        }
        that.setData({ personTab: index});
        that.initRank(that.data.currentTab, that.data.userType);
    },
    initRank: function(rankType, userType) {
        var that = this;
        util.request(cfg.pointRank, {
                "rankType": rankType,
                "userType": userType
            },
            function(ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({
                        rankList: ret.data.list
                    });
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
    changeType: function(e) {
        var that = this;
        var temp = e.currentTarget.dataset.value;
        that.setData({
            currentTab: temp
        });
        that.initRank(that.data.currentTab, that.data.userType);
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
        that.initRank(that.data.currentTab, that.data.userType);
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