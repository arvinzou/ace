var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        list:[],
        LoadOver:false,
        Loadingstatus:false,
        nowDate: util.formatTime(new Date(), 'Y-M-D h:m:s'),
        showBtn:false,
        hiddenBtn: true,
    },
    store:{
        category: '',
        start: 0,
        limit: 10,
    },
    onLoad: function(options) {
        let that = this;
        that.ifCreatBtn();
        let category = options.category;
        console.log(category);
        if (!category) {
            wx.navigateBack({})
            return;
        }
        that.store.category = category;
        that.setBarTitleText(category);
        that.initdata();
    },

    ifCreatBtn:function(){
        let that = this;
        let sysUserInfo = util.getSysUser();
        if (!sysUserInfo){
            util.request(cfg.findUserInfo, {},
                function (rst) {
                    if (rst.status == 0) {
                        util.setSysUser(rst.data);
                        if (rst.data.societyOrg&&rst.data.societyOrg.orgType == 2) {
                            that.setData({
                                hiddenBtn: false,
                            });
                        }
                        return;
                    }
                }
            );
        }
        if (sysUserInfo.societyOrg &&sysUserInfo.societyOrg.orgType == 2) {
            that.setData({
                hiddenBtn: false,
            });
        }
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
        util.request(cfg.findActivitys, {
            category: that.store.category,
            start: that.store.start,
            limit: that.store.limit
            },
            function(data) {
                console.log(data.data);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                if (data.data.length < that.store.limit) {
                    that.data.LoadOver=true;
                }
                that.setData({
                    list: that.data.list.concat(data.data),
                    Loadingstatus: false,
                    LoadOver: that.data.LoadOver,
                });
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
        that.store.start += that.store.limit;
        that.initdata();
    },
    // 清空状态
    clearStatus: function () {
        let that = this;
        that.store.start = 0;
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

    createActivity: function () {
        let that =this; 
        let category = that.store.category;
        wx.navigateTo({ url: '../activityApply/index?category=' + category })
    },
    

    onPageScroll: function (e) {
        if (this.data.hiddenBtn){
            return;
        }
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