var util = require("../../util/util.js");
Page({
    data: {
        id: '',
        activityInfo: {}
    },
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
    initdata: function() {
        let that = this;
        util.request('http://192.168.2.189/society/www/activity/selectActivityByPrimaryKey', {
                id: that.data.id,
            },
            function(rst) {
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

    setBarTitleText: function(tit) {
        wx.setNavigationBarTitle({
            title: tit
        })
    },

    viewParticipants: function (e) {
        console.log(e);
        let data = e.currentTarget.dataset
        let p = data.id;
        wx.navigateTo({ url: '../participants/index?id=' + p })
    },

})