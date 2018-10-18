var util = require("../../util/util.js");
Page({
    data: {
        navbarArray: [
            //     {
            //     text: '活动精选',
            //     active: 'navbar-item-active'
            //  }, 
            {
                text: '党建活动',
                active: 'navbar-item-active',
                type: 'dj'
            },
            {
                text: '公益活动',
                active: '',
                type: 'gy'
            }, {
                text: '普及活动',
                active: '',
                type: 'pj'
            }, {
                text: '创意活动',
                active: '',
                type: 'cy'
            }
        ],
        lists: {
            jx: {
                list: [],
                start: 0,
                scroll: 0,
                isfrist: true,
                LoadOver: false,
                Loadingstatus: false,
            },
            gy: {
                list: [],
                start: 0,
                scroll: 0,
                isfrist: true,
                category: '1',
                LoadOver: false,
                Loadingstatus: false,
            },
            pj: {
                list: [],
                start: 0,
                scroll: 0,
                isfrist: true,
                category: '2',
                LoadOver: false,
                Loadingstatus: false,
            },
            dj: {
                list: [],
                start: 0,
                scroll: 0,
                isfrist: true,
                category: '4',
                LoadOver: false,
                Loadingstatus: false,
            },
            cy: {
                list: [],
                start: 0,
                scroll: 0,
                isfrist: true,
                category: '3',
                LoadOver: false,
                Loadingstatus: false,
            },
        },
        vdata: {},
        nowType: 'dj',
        limit: 10,
        loadingModalHide: false
    },

    onLoad: function() {
        let that = this;
        that.setVdata();
        that.initdata();
    },

    setVdata: function() {
        let that = this;
        let temp = that.data.lists[that.data.nowType];
        that.data.vdata = temp;
    },
    getVdata: function() {
        let that = this;
        that.data.lists[that.data.nowType] = that.data.vdata;
    },

    initdata: function() {
        let that = this;
        let temp = that.data.vdata;
        if (temp.LoadOver) {
            return;
        }
        that.showLoading();
        util.request('http://192.168.2.189/society/www/activity/findPublicActivityReportList', {
                category: temp.category,
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
    showLoading: function() {
        let that = this;
        let temp = that.data.vdata;
        temp.Loadingstatus=true;
        that.setData({
            vdata: temp
        });
    },


    viewData: function(data) {
        let that = this;
        let temp = that.data.vdata;
        temp.isfrist = false;
        temp.Loadingstatus = false;
        if (data) {
            temp.list = temp.list.concat(data);
            if (data.length < that.data.limit) {
                temp.LoadOver = true;
            }
        }
        that.setData({
            vdata: temp
        })
    },

    // 下拉刷新
    onPullDownRefresh: function() {
        let that=this;
        let temp = that.data.vdata;
        temp.list=[];
        temp.start=0;
        temp.LoadOver=false;
        that.getVdata();
        that.initdata();
    },

    // // 上拉加载
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        let temp = that.data.vdata;
        temp.start += that.data.limit;
        that.initdata();
    },

    onTapNavbar: function(e) {
        this.switchChannel(parseInt(e.currentTarget.id));
    },
    switchChannel: function(targetChannelIndex) {
        let that = this;
        that.getArticles(targetChannelIndex);
        let navbarArray = that.data.navbarArray;
        that.getVdata();
        navbarArray.forEach((item, index, array) => {
            item.active = '';
            if (index === targetChannelIndex) {
                item.active = 'navbar-item-active';
                that.data.nowType = item.type;
            }
        });
        that.setData({
            navbarArray: navbarArray,
        });
        that.setVdata();
        let temp=that.data.vdata;
        if (temp.isfrist) {
            that.initdata();
            return;
        }
        that.viewData();
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
    recordY: function(e) {
        let that = this;
        let temp=that.data.vdata;
        temp.scroll = e.detail.scrollTop;
    },
    viewInfo:function(e){
        let that=this;
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({ url: '../reportInfo/index?id=' + p + "&title=" + title })
    }
});