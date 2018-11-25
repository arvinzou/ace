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
        wxUser: null

    },

    /**
     * 生命周期函数--监听页面加载;
     */
    onLoad: function(options) {

    },
    initUserData: function() {
        var that = this;
        let userInfo = util.getSysUser();
        if(!userInfo){
            wx.navigateTo({
                url: "../regist/index"
            });
        }
        that.setData({
            userinfoData: userInfo,
            isRegist: true,
        });
    },
    initWxUserData: function() {
        var that = this;
        util.request(cfg.server + '/portal/www/selectWxUserByPrimaryKey.do', {
                "id": wx.getStorageSync('WX-SESSION-ID')
            },
            function(ret) {
                if (ret.status == 0) {
                    that.setData({
                        wxUser: ret.value
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

        // 判断有没有鉴权
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../me/index&type=switchTab"
            });
            return;
        }
        // 判断有没有注册
        var userInfo = util.getSysUser();
        if (userInfo) {
            that.initUserData();
        } else {
            that.getSysUserInfo();
        }
        that.setData({
            userId: wx.getStorageSync('WX-SESSION-ID')
        })
        that.initWxUserData();
    },

    getSysUserInfo:function(){
        let that=this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
                util.setSysUser(ret.data);
                that.initUserData();
            }
        );
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
        let that = this;
        that.setData({
            num1: parseInt(Math.random() * 100),
            num2: parseInt(Math.random() * 100),
            num3: parseInt(Math.random() * 100),
        });
        util.delSysUser();
        that.onShow();
        wx.stopPullDownRefresh();
        return;
    },
    call: function() {
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