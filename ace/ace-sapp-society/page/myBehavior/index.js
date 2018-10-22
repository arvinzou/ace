var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        currentTab: 0, //当前选中的Tab项
        behaviorList: [],
        userId: null
    },

    swichNav: function(e) {
        console.log(e);
        var that = this;
        if (this.data.currentTab === e.target.dataset.current) {
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.current,
            })
        }
    },
    swiperChange: function(e) {
        console.log(e);
        var that = this;
        this.setData({
            currentTab: e.detail.current,
        });
        if (e.detail.current == '0'){
            that.initBehaviorList(that.data.userId, "1");
        }else{
            that.initBehaviorList(that.data.userId, "0");
        }
    },
    releaseBehavior: function(){
        wx.navigateTo({
            url: '../releaseBehavior/index'
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        that.setData({userId: options.userId});
        that.initBehaviorList(options.userId,"1");
    },
    initBehaviorList: function(userId,type){
        var that = this;
        util.request(cfg.behaviorList, { "userId":userId, "start": 0, "limit": 999, "type": type},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({ behaviorList: ret.data.rows });
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