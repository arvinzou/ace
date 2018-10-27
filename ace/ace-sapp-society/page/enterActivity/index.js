var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        category: '',
        user: {},
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        let category = options.category;
        let id = options.id;
        if (!id) {
            wx.navigateBack({})
            return;
        }
        that.setData({
            category: category,
            id: id,
        })
        that.initdata();
    },

    initdata: function() {
        let that = this;
        let sysuser = util.getSysUser();
        if (sysuser) {
            that.setData({
                user: sysuser.person
            })
        }
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
    formSubmit: function(e) {
        let that = this;
        let vals = e.detail.value;
        if (!vals.contactName || !vals.mobileNumber) {
            wx.showModal({
                title: '提示',
                content: '报名资料不能为空',
            })
        }
        vals.activityId = that.data.id;
        vals.identity='1';
        util.request(cfg.insertActivityDetail, {
                jsons: JSON.stringify(vals),
            },
            function(data) {
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                if (data.status == 0) {
                    wx.showToast({
                        title: '报名成功',
                        icon: 'success',
                        duration: 1000
                    });
                    wx.navigateBack({});
                    return;
                }
                wx.showModal({
                    title: '提示',
                    content: data.errorMessage,
                })

            }
        );
    },

})