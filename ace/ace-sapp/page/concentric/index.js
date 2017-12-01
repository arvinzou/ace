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
        limit: 10,
        type: 1,
        navbar: ['同心社区', '同心乡村', '同心项目', '同心园区'],
        currentTab: 0,
        /*文件服务器*/
        serverfile: cfg.serverfile,
        concentricSq: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },

        concentricXc: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },
        concentricXm: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },
        concentricYq: {
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
            key = "Sq";
            objectType = that.data.concentricSq;
        } else if (type == 2) {
            key = "Xc";
            objectType = that.data.concentricXc;
        } else if (type == 3) {
            key = "Xm";
            objectType = that.data.concentricXm;
        } else if (type == 4) {
            key = "Yq";
            objectType = that.data.concentricYq;
        }
        listStr = "concentric" + key + ".List";
        LoadOverStr = "concentric" + key + ".LoadOver";
        LoadIsFristStr = "concentric" + key + ".LoadIsFrist";
        LoadingstatusStr = "concentric" + key + ".Loadingstatus";
        startStr = "concentric" + key + ".start";

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
        util.request(cfg.findConcentricList,
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
                /**处理数据列表。和显示状态 */
                that.setData({
                    [listStr]: dataList,
                    [LoadingstatusStr]: true,
                });
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

    /**
     * 点击文件事件
     */
    ViewFile: function () {
        console.log("hehe");
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
        let module = '同心工程'
        wx.navigateTo({ url: '../selectMessage/index?id=' + p + '&module=' + module })
    },
})

