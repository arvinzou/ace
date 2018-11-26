var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        limit: 10,
        category: 1,
        navbarArray: [
            {
                text: '党建活动',
                active: 'navbar-item-active',
                type: '4'
            },
            {
                text: '公益活动',
                active: '',
                type: '1'
            }, {
                text: '普及活动',
                active: '',
                type: '2'
            }, {
                text: '创意活动',
                active: '',
                type: '3'
            }
        ],
        lists: [{
                list: [],
                start: 0,
                isfrist: true,
                category: '1',
                LoadOver: false,
                Loadingstatus: false,
            },
            {
                list: [],
                start: 0,
                isfrist: true,
                category: '2',
                LoadOver: false,
                Loadingstatus: false,
            },
            {
                list: [],
                start: 0,
                isfrist: true,
                category: '3',
                LoadOver: false,
                Loadingstatus: false,
            },
            {
                list: [],
                start: 0,
                isfrist: true,
                category: '4',
                LoadOver: false,
                Loadingstatus: false,
            }
        ]

    },

    onLoad: function() {
        let that = this;
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../activityIndex/index&type=switchTab"
            });
        }
        that.initdata();
    },
    loadMore: function () {
        var self = this;
        // 当前页是最后一页
        if (self.data.currentPage == self.data.allPages) {
            self.onReachBottom();
            return;
        }
    },

    changeActivity:function(e){
        let that=this;
        let index = e.detail.current;
        that.switchChannel(index);
        let temp=that.getTarget();
        if (temp.isfrist) {
            that.initdata();
            return;
        }
    },

    initdata: function() {
        let that = this;
        let temp = that.getTarget();
        if (temp.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(cfg.publicActivityReports, {
            category: that.data.category == 1 ? '4' : that.data.category-1,
                start: temp.start,
                limit: that.data.limit,
            },
            function(rst) {
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                if (rst.status == 0) {
                    that.viewData(rst.data);
                }
            }
        );
    },

    getTarget:function(){
        let that = this;
        return  that.data.lists[that.data.category - 1];
    },

    showLoading: function() {
        let that = this;
        let temp = that.getTarget();
        temp.Loadingstatus = true;
        that.setDataTarget();
    },
    setDataTarget:function(){
        let that = this;
        var tempDate = 'lists[' + (that.data.category - 1) + ']';
        console.log(tempDate);
        console.log(that.data);
        that.setData({
            category:that.data.category,
            [tempDate]: that.getTarget(),
        });
    },


    viewData: function(data) {
        let that = this;
        let temp = that.getTarget();
        temp.isfrist = false;
        temp.Loadingstatus = false;
        if (data) {
            temp.list = temp.list.concat(data);
            if (data.length < that.data.limit) {
                temp.LoadOver = true;
            }
        }
        that.setDataTarget();
    },

    // 下拉刷新
    onPullDownRefresh: function() {
        let that = this;
        console.log('-------------下拉加载-------------');
        let temp = that.getTarget();
        temp.list = [];
        temp.start = 0;
        temp.LoadOver = false;
        that.initdata();
    },

    // // 上拉加载
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        let temp = that.getTarget();
        temp.start += that.data.limit;
        that.initdata();
    },

    onTapNavbar: function(e) {
        this.switchChannel(parseInt(e.currentTarget.id));
    },
    switchChannel: function(targetChannelIndex) {
        let that = this;
        console.log(targetChannelIndex);
        that.getArticles(targetChannelIndex);
        let navbarArray = that.data.navbarArray;
        navbarArray.forEach((item, index, array) => {
            item.active = '';
            if (index === targetChannelIndex) {
                item.active = 'navbar-item-active';
            }
        });
        that.setData({
            category: targetChannelIndex+1,
            navbarArray: navbarArray,
        });
    },
    getArticles: function(index) {
        this.setData({
            loadingModalHide: false,
            articleContent: ''
        });
        setTimeout(() => {
            this.setData({
                loadingModalHide: true,
                articleContent: this.data.navbarArray[index].text
            });
        }, 500);
    },
    viewInfo: function(e) {
        let that = this;
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({
            url: '../reportInfo/index?id=' + p + "&title=" + title
        })
    }
});