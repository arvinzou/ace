// page/reportInfo/index.js
var util = require("../../util/util.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id:'',
        list:{},
        actionComment: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        console.log(options);
        let id = options.id;
        let tit = options.title;
        if (!id) {
            wx.navigateBack({})
            return;
        }
        that.data.id = id;
        that.setBarTitleText(tit);
        that.initdata();
    },

    // 获取列表
    initdata: function () {
        let that = this;
        util.request('http://192.168.2.189/society/www/activity/selectActivityReportByPrimaryKey', {
            id: that.data.id,
        },
            function (rst) {
                console.log(rst);
                console.log(rst.data);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    list: rst.data
                });
            }
        );
    },
    // 设置头标题
    setBarTitleText: function (tit) {
        wx.setNavigationBarTitle({
            title: tit
        })
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

    },
    actionComment: function () {
        this.setData({
            actionComment: true,
        })
    },
    hiddenComment:function(e){
        console.log(e);
        this.setData({
            actionComment: false,
        })
    }
})