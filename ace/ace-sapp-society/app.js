const openIdUrl = require('./config').openIdUrl;
// var util = require("../../util/util.js");
App({
    onLaunch: function () {
        // wx.checkSession({
        //     success: function (res) {
        //         console.log("处于登录态");
        //     },
        //     fail: function (res) {
        //         console.log("需要重新登陆");
        //         wx.clearStorage();
        //     }
        // })
    },
    onShow: function () {
        console.log('App Show')
    },

    onHide: function () {

        console.log('App Hide')
    },
    globalData: {
        hasLogin: false,
        socketConnectFail: false,
        openid: null
    },
    // lazy loading openid
    getUserOpenId: function (callback) {
        var self = this
        console.log("33333333333333333333333333333333333333333333");
        if (self.globalData.openid) {
            callback(null, self.globalData.openid)
        } else {
            wx.login({
                success: function (data) {
                    wx.request({
                        url: openIdUrl,
                        data: {
                            code: data.code
                        },
                        success: function (res) {
                            console.log('拉取openid成功', res)
                            self.globalData.openid = res.data.openid
                            callback(null, self.globalData.openid)
                        },
                        fail: function (res) {
                            console.log('拉取用户openid失败，将无法正常使用开放接口等服务', res)
                            callback(res)
                        }
                    })
                },
                fail: function (err) {
                    console.log('wx.login 接口调用失败，将无法正常使用开放接口等服务', err)
                    callback(err)
                }
            })
        }
    }
})
