var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()


var listStr = "";
var LoadOverStr = "";
var LoadIsFristStr = "";
var LoadingstatusStr = "";
var startStr = "";
var objectType = null;

Page({
    data: {
        type: 1,
        limit: 10,
        navbar: ['进行中', '已结束'],
        currentTab: 0,
        /*文件服务器*/
        serverfile: cfg.serverfile,
        povertyAlleviationIng: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },

        povertyAlleviationEnd: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },
    },
    /**
    * 点击选项卡
    */
    navbarTap: function (e) {
        let that = this;      
        if (that.data.currentTab === e.target.dataset.idx) { 
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.idx
            })
        }

        that.data.type = parseInt(that.data.currentTab) + 1;
        that.changePage(that.data.type);
        if (objectType.LoadIsFrist) {
            that.contentView(that.data.type);
        }
    },

    /**
     * 切换页面
     */
    changePage: function (type) {
        let that = this;
        let key = "";
        if (type == 1) {
            key = "Ing";
            objectType = that.data.povertyAlleviationIng;
        } else if (type == 2) {
            key = "End";
            objectType = that.data.povertyAlleviationEnd;
        }
        listStr = "povertyAlleviation" + key + ".List";
        LoadOverStr = "povertyAlleviation" + key + ".LoadOver";
        LoadIsFristStr = "povertyAlleviation" + key + ".LoadIsFrist";
        LoadingstatusStr = "povertyAlleviation" + key + ".Loadingstatus";
        startStr = "povertyAlleviation" + key + ".start";

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function () {
        let that = this;
        //下载数据函数。
        let val = that.data.type;
        //更改页面。
        that.changePage(val);
        that.contentView(val);
    },

    /*加载页面*/
    contentView: function (value) {
        let that = this;
        /*判读有没有下载完毕*/
        if (objectType.LoadOver) {
            return;
        }
        //请求数据。
        util.request(cfg.findPovertyAlleviationList,
            {
                category: value,
                start: objectType.start * 10,
                limit: that.data.limit
            },
            /*请求数据成功 */
            function (data) {
                let dataList = [];
                wx.hideNavigationBarLoading(); //完成停止加载
                wx.stopPullDownRefresh(); //停止下拉刷新
                for (let i = 0; i < data.rows.length; i++) {
                    let date = data.rows[i].time;
                    data.rows[i].time = date.substring(0, 10);;
                }
                //获取list容器
                //判断有没有获取到数据

                //有获得数据
                //判读是不是第一次加载数据
                if (objectType.LoadIsFrist) {
                    dataList = data.rows;
                    that.setData({
                        [LoadIsFristStr]: false, //把第一次加载数据设为为false,已经成了过去时了。
                    });
                } else {
                    dataList = objectType.List.concat(data.rows);
                }

                that.setData({
                    [listStr]: dataList,
                    [LoadingstatusStr]: true,
                });
                //判断有没有获取到数据
                if (data.rows.length < that.data.limit) {
                    that.setData({
                        [LoadingstatusStr]: false,//把"上拉加载"的变量设为false，隐藏 
                        [LoadOverStr]: true,//把“没有数据”设为true，显示 
                    });
                }
            });
    },
    /**
     * 上拉加载
     */

    onReachBottom: function () {
        var that = this;
        console.log('-------------上拉加载-------------');
        let num = parseInt(objectType.start) + 1;
        that.setData({
            [startStr]: num,
        });
        that.contentView(that.data.type);
    },

    /**
    * 下拉刷新
    */
    onPullDownRefresh: function () {
        console.log('--------下拉刷新-------');
        var that = this;
        that.setData({
            [listStr]: null,  //触发到上拉事件，把isFromFile设为为false  
            [startStr]: 0,
            [LoadIsFristStr]: true,
            [LoadOverStr]: false,
        });
        that.contentView(that.data.type);
    },

    onShareAppMessage: function (res) {
        return {
            title: '我发现了掌上统战小程序，一起看看吧',
            path: '/page/files/index'
        }
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
        var that = this;
        //设施顶部样式
        wx.setNavigationBarColor({
            frontColor: cfg.frontColor,
            backgroundColor: cfg.backgroundColor,
            animation: {
                duration: 400,
                timingFunc: 'easeIn'
            }
        });
    },

    /**  
    * 滑动切换tab  
    */
    bindChange: function (e) {
        var that = this;
        that.setData({ currentTab: e.detail.current });
    },
    /**
     * 点击查看详情
     */
    listClick: function (e) {
        console.log(e);
        let p = e.currentTarget.dataset.id;
        let module = '精准扶贫'
        wx.navigateTo({ url: '../selectMessage/index?id=' + p + '&module=' + module })
    },
})

