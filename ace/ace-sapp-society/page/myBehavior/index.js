var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        currentTab: 0, //当前选中的Tab项
        navbar: ['文明行为', '不文明行为'],
        behaviorList: [],
        userId: null,
        start: 0,
        limit: 3,
        LoadOver: false,
        Loadingstatus: false,
        behaviorType : "1"
    },

    /**
* 点击选项卡
*/
    navbarTap: function (e) {
        let that = this;
        that.setData({
            LoadOver: false,
            start: 0,
            limit: 3,
            behaviorList: []
        });
        if (that.data.currentTab === e.target.dataset.idx) {
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.idx
            });
            if (e.target.dataset.idx == 0) {
                that.setData({ behaviorType: "1"});
                that.initBehaviorList(that.data.userId, '1');
            } else {
                that.setData({ behaviorType: "0"});
                that.initBehaviorList(that.data.userId, '0');
            }

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
        that.initBehaviorList(options.userId, that.data.behaviorType);
    },
    initBehaviorList: function(userId,type){
        var that = this;
        if (that.data.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(cfg.behaviorList, { "userId":userId, "start": that.data.start, "limit": that.data.limit, "type": type},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    wx.hideNavigationBarLoading() //完成停止加载
                    wx.stopPullDownRefresh() //停止下拉刷新
                    that.setData({ 
                        behaviorList: that.data.behaviorList.concat(ret.data.rows),
                        Loadingstatus: false
                    });
                    if (ret.data.rows.length < that.data.limit) {
                        that.setData({
                            LoadOver: true,
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
        console.log('--------下拉刷新-------')
        var that = this;
        that.clearStatus();
        that.initBehaviorList(that.data.userId, that.data.behaviorType);
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start = that.data.start + that.data.limit + 1;
        that.initBehaviorList(that.data.userId, that.data.behaviorType);
    },
    // 清空状态
    clearStatus: function () {
        let that = this;
        that.data.start = 0;
        that.setData({
            LoadOver: false,
            behaviorList: [],
        })
    },
    // 显示加载
    showLoading: function () {
        let that = this;
        that.setData({
            Loadingstatus: true,
        });
    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})