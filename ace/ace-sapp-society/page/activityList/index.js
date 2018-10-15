Page({
    data: {
        list:[],
        category: '',
        start:0,
        limit:10,
        LoadOver:false,
        Loadingstatus:false,
    },
    onLoad: function(options) {
        let that = this;
        let category = options.category;
        if (!category) {
            wx.navigateBack({})
            return;
        }
        that.data.category = category;
        that.setBarTitleText(category);
        initdata();
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
        util.request(cfg.selectActivityPageList, {
                category: that.data.category,
                start: that.data.start,
                limit: that.data.limit
            },
            function(data) {
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    list: that.data.list.concat(data)
                });
                hideLoading();
                if (data.rows.length < that.data.limit) {
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
        that.initData('');
    },

    // 上拉加载
    onReachBottom: function () {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start+=10;
        Loadingstatus: true,
        showLoading();
        that.initData();
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
        that.setData({
            Loadingstatus: false,
        });
    },
    // 隐藏加载
    hideLoading:function(){
        that.setData({
            Loadingstatus: true,
        });
    }
})