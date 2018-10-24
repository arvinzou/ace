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
    },
    /**
  * 点击选项卡
  */
    navbarTap: function (e) {
        let that = this;
        if (that.data.currentTab === e.target.dataset.idx) {
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.idx
            });
            if (e.target.dataset.idx == 0){
                that.initBehaviorList('1');
            }else{
                that.initBehaviorList('0');
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
        that.initBehaviorList("1");
    },
    initBehaviorList: function(type){
        var that = this;
        util.request(cfg.behaviorList, { "start": 0, "limit": 999, "type": type, "status":"3"},
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
        var that = this;
        that.initBehaviorList('1');
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