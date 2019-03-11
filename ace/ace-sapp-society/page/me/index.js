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
        actionComment:false,
        num1: parseInt(Math.random() * 100),
        num2: parseInt(Math.random() * 100),
        num3: parseInt(Math.random() * 100),
        userId: null,
        loginUser: null

    },

    /**
     * 生命周期函数--监听页面加载;
     */
    onLoad: function(options) {
    

    },
    /**
     * 初始用户信息
     */
    initUserData: function() {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
                util.setSysUser(ret.data);
                if (!ret.data) {
                    wx.navigateTo({
                        url: "../regist/index"
                    });
                }else{
                    that.setData({
                        userinfoData: ret.data,
                        isRegist: true,
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
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../index/index&type=switchTab"
            });
            return;
        }
        that.initUserAdmin();
        // 判断有没有注册
        that.initUserData();
        that.setData({
            userId: wx.getStorageSync('WX-SESSION-ID')
        })
    },

    initUserAdmin: function(){
        var that = this;
        util.request(cfg.server+'/portal/www/isAdmin.do', {},
            function (ret) {
                console.log(ret);
                that.setData({ loginUser: ret.status});
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
        var that = this;
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

    },
    actionComment: function () {
        this.setData({
            actionComment: true,
        })
    }, 
    formSubmit: function (e) {
        var that = this;
        var vals = e.detail.value;
        if (!vals.content) {
            wx.showModal({
                title: '提示',
                content: '没有输入任何内容',
            })
            return;
        }
        util.request(cfg.feedBack, vals,
            function (rst) {
                if (rst.status == 0) {
                    that.setData({
                        actionComment: false,
                    })
                    wx.showModal({
                        title: '提交成功',
                        content: '感谢您的反馈，我们会尽快处理',
                    })
                    return;
                }
                wx.showModal({
                    title: '提交失败',
                    content: '请稍后重试',
                })
            });
    },
    hiddenComment: function (e) {
        var that = this;
        if (that.data.commentVal) {
            wx.showModal({
                title: '提示',
                content: '确定放弃输入？',
                success: function (res) {
                    if (!res.confirm) {
                        return;
                    }
                }
            })
        } else {
            that.setData({
                actionComment: false,
            })
        }
    },
})