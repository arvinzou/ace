var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        id: '',
        activityInfo: {},
        btnFlag: 1,
        nowDate: new Date().Format("yyyy-MM-dd HH:mm:ss"),
        method: false
    },
    onLoad: function(options) {
        let that = this;

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
    initdata: function() {
        let that = this;
        util.request(cfg.findActivity, {
                id: that.data.id,
            },
            function(rst) {
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.data.activityInfo = rst.data;
                that.btnControl();
                that.setData({
                    activityInfo: rst.data
                });

            }
        );
    },

    btnControl: function() {
        let that = this;
        if (that.data.activityInfo.dendline > that.data.nowDate) {
            util.request(cfg.applyStatus, {
                    activityId: that.data.activityInfo.id,
                },
                function(rst) {
                    //        code:1、未注册和鉴权2、账户类型为组织，3、未注册，4、已注册
                    let code = rst.data.code;
                    that.setData({
                        btnFlag: code,
                    })
                }
            );
        } else {
            that.setData({
                btnFlag: 0,
            })
        }
    },

    setBarTitleText: function(tit) {
        wx.setNavigationBarTitle({
            title: tit
        })
    },

    viewParticipants: function(e) {
        let data = e.currentTarget.dataset
        let p = data.id;
        wx.navigateTo({
            url: '../participants/index?id=' + p
        })
    },
    apply:function(){
        let that=this;
        wx.navigateTo({
            url: '../enterActivity/index?id='+ that.data.activityInfo.id
        })
    }
})