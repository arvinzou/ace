var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        currentTab: 0, //当前选中的Tab项
        navbar:['文明行为','不文明行为'],
        behaviorList: [],
        start: 0,
        limit: 3,
        LoadOver: false,
        Loadingstatus: false,
        behaviorType: "1"
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
            if (e.target.dataset.idx == 0){
                that.setData({ behaviorType: "1"});
                that.initBehaviorList('1');
            }else{
                that.initBehaviorList('0');
                that.setData({ behaviorType: "0" });
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
        that.initBehaviorList(that.data.behaviorType);
    },
    previewImage: function (e) {
        console.log(e);
        wx.previewImage({
            current: e.currentTarget.id,
            urls: [e.currentTarget.id]
        })
    },
    initBehaviorList: function(type){
        var that = this;
        if (that.data.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(cfg.behaviorList, { "start": that.data.start, "limit": that.data.limit, "type": type, "status":"3"},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    wx.hideNavigationBarLoading() //完成停止加载
                    wx.stopPullDownRefresh() //停止下拉刷新
                    that.setData(
                        { 
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
        that.initBehaviorList(that.data.behaviorType);
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start = that.data.start+that.data.limit+1;
        that.initBehaviorList(that.data.behaviorType);
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