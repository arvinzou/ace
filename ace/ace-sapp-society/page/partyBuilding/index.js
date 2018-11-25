var util = require("../../util/util.js");
var cfg = require("../../config.js");

Page({

    /**
     * 页面的初始数据
     */
    data: {
        list: [],
        category: 4,
        start: 0,
        limit: 10,
        LoadOver: false,
        Loadingstatus: false,
        showBtn: false,
        hiddenBtn: true,
        orgs: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        that.ifCreatBtn();
    },

    ifCreatBtn: function () {
        let that = this;
        let sysUserInfo = util.getSysUser();
        if (!sysUserInfo) {
            util.request(cfg.findUserInfo, {},
                function (rst) {
                    if (rst.status == 0) {
                        util.setSysUser(rst.data);
                        if (rst.data.regType == 2 && rst.data.societyOrg.orgType == 1 && rst.data.societyOrg.status=='3') {
                            that.setData({
                                hiddenBtn: false,
                            });
                        }
                        return;
                    }
                }
            );
        }
        if (sysUserInfo.regType == 2 && sysUserInfo.societyOrg.orgType == 1 && sysUserInfo.societyOrg.status == "3") {
            that.setData({
                hiddenBtn: false,
            });
        }
    },

    initOrgData: function() {
        let that = this;
        util.request(cfg.findSocietyOrgInfos, {
                orgType: '1',
                start: that.data.start,
                limit: 100,
                status:'3',
            },
            function(rst) {
                console.log(rst.rows);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    orgs: rst.rows,
                });
            }
        );
    },
    // 显示加载
    showLoading: function() {
        let that = this;
        that.setData({
            Loadingstatus: true,
        });
    },

    initdata: function() {
        console.log("initData");
        let that = this;
        if (that.data.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(cfg.findActivitys, {
                category: that.data.category,
                start: that.data.start,
                limit: that.data.limit
            },
            function(data) {
                console.log(data.data);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    list: that.data.list.concat(data.data),
                    Loadingstatus: false,
                });
                if (data.data.length < that.data.limit) {
                    that.setData({
                        LoadOver: true,
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
        let that = this;
        that.initOrgData();
        that.initdata();
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
        that.initdata();
        wx.stopPullDownRefresh();
        return;
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start += that.data.limit;
        that.initdata();
    },
    // 清空状态
    clearStatus: function() {
        let that = this;
        that.data.start = 0;
        that.setData({
            LoadOver: false,
            list: [],
        })
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
    onPageScroll: function(e) {
        if (e.scrollTop <= 0) {
            // 滚动到最顶部
            e.scrollTop = 0;
        } else if (e.scrollTop > this.data.scrollHeight) {
            // 滚动到最底部
            e.scrollTop = this.data.scrollHeight;
        }
        if (e.scrollTop > this.data.scrollTop || e.scrollTop >= this.data.scrollHeight) {
            this.setData({
                showBtn: true,
            });
        } else {
            this.setData({
                showBtn: false,
            });
        }
        //给scrollTop重新赋值 
        this.setData({
            scrollTop: e.scrollTop
        })
    },
    viewContent: function(e) {
        console.log(e);
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({
            url: '../activityInfo/index?id=' + p + "&title=" + title
        })
    },
    createActivity: function(e) {
        let data = e.currentTarget.dataset
        let p = data.category;
        wx.navigateTo({
            url: '../activityApply/index?category=' + p
        })
    },

})