var util = require("../../util/util.js");
Page({
    data: {
        list:[],
        category: '',
        start:0,
        limit:6,
        LoadOver:false,
        Loadingstatus:false,
        nowDate: new Date().Format("yyyy-MM-dd HH:mm:ss"),
        showBtn:false,
        hiddenBtn: true,
    },
    onLoad: function(options) {
        let that = this;
        if (util.isLogin()) {
            util.request('http://192.168.2.189/society/www/activity/getUserType', {},
                function (rst) {
                    if (rst.data.type == 2) {
                        that.setData({
                            hiddenBtn: false,
                        });
                    }
                }
            );
        };
        let category = options.category;
        if (!category) {
            wx.navigateBack({})
            return;
        }
        that.data.category = category;
        that.setBarTitleText(category);
        that.initdata();
    },

    setBarTitleText: function(category) {
        let barText = '';
        switch (category) {
            case '1':
                barText = '公益活动';
                break;
            case '2':
                barText = '普及活动';
                break;
            case '3':
                barText = '创意活动';
                break;
        }
        wx.setNavigationBarTitle({
            title: barText
        })
    },
    // 获取列表
    initdata: function() {
        console.log("initData");
        let that = this;
        if(that.data.LoadOver){
            return;
        }
        that.showLoading();
        util.request('http://192.168.2.189/society/www/activity/findActivityList', {
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
    //下拉刷新
    onPullDownRefresh: function () {
        console.log('--------下拉刷新-------')
        var that = this;
        that.clearStatus();
        that.initdata();
    },

    // // 上拉加载
    onReachBottom: function () {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start += that.data.limit;
        that.initdata();
    },
    // 清空状态
    clearStatus: function () {
        let that = this;
        that.data.start = 0;
        that.setData({
            LoadOver: false,
            list: [],
        })
    },
    //页面转发分享按钮。
    onShareAppMessage: function (res) {
        if (res.from === 'button') {
            // 来自页面内转发按钮
            console.log(res.target)
        }
        return {
            title: '活动',
            path: '/',
            success: function (res) {
                // 转发成功
            },
            fail: function (res) {
                // 转发失败
            }
        }
    },
    // 显示加载
    showLoading:function(){
        let that=this;
        that.setData({
            Loadingstatus: true,
        });
    },
    viewContent: function (e) {
        console.log(e);
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({ url: '../activityInfo/index?id=' + p + "&title=" + title})
    },

    createActivity: function (e) {
        let data = e.currentTarget.dataset
        let p = data.category;
        wx.navigateTo({ url: '../activityApply/index?category=' + p })
    },
    

    onPageScroll: function (e) {

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
    
})