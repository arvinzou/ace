var util = require("../../util/util.js");
var cfg = require("../../config.js");

Page({
    data: {
        limit: 10,
        start: 0,
        LoadOver: false,
        LoadIsFrist: true,
        list: [],
        /*文件服务器*/
        serverfile: cfg.serverfile,
    },

    /**
     * 清除状态
     */
    clearStatus: function () {
        let that = this;
        that.data.start = 0;
        that.setData({
            LoadOver: false,
            LoadIsFrist: true,
            list: [],
        })


    },
    /**
     * 准备
     */
    onReady: function (res) {
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
     * 页面转发分享按钮。
     */
    onShareAppMessage: function (res) {
        if (res.from === 'button') {
            // 来自页面内转发按钮
            console.log(res.target)
        }
        return {
            title: '我发现了掌上统战小程序，一起看看吧',
            path: '/',
            success: function (res) {
                // 转发成功
            },
            fail: function (res) {
                // 转发失败
            }
        }
    },
    // 显示输入框
    showInput: function () {
        this.setData({
            inputShowed: true
        });
    },
    //隐藏输入框
    hideInput: function () {
        this.setData({
            inputVal: "",
            inputShowed: false
        });
    },
    //清除输入框内的数据
    clearInput: function () {

        this.setData({
            inputVal: ""
        });
        this.clearStatus();
        this.initData('');

    },
    //实时根据输入框内容进行搜索
    inputTyping: function (e) {
        var that = this;
        that.clearStatus();
        this.setData({
            inputVal: e.detail.value
        });
        if (that.data.inputVal.length >= 1) {
            that.initData(that.data.inputVal);
        }
        if (that.data.inputVal == '') {
            that.initData(that.data.inputVal);
        }
    },
    kindToggle: function (e) {
        var id = e.currentTarget.id, list = this.data.list;
        for (var i = 0, len = list.length; i < len; ++i) {
            if (list[i].id == id) {
                list[i].open = !list[i].open
            } else {
                list[i].open = false
            }
        }
        this.setData({
            list: list
        });
    },
    //下拉刷新
    onPullDownRefresh: function () {
        console.log('--------下拉刷新-------')
        var that = this;
        that.clearStatus();
        that.initData('');
    },
    //下载
    onLoad: function () {
        var that = this;
        that.initData('');
    },
    //初始化数据
    initData: function (value) {
        let that = this;
        let dataList = [];
        /*判读有没有下载完毕*/
        if (that.data.LoadOver) {
            return;
        }
        //请求数据。
        util.request(cfg.findResearchList,
            {
                name: value,
                start: that.data.start * 10,
                limit: that.data.limit
            },
            /*请求数据成功 */
            function (data) {
                wx.hideNavigationBarLoading(); //完成停止加载
                wx.stopPullDownRefresh(); //停止下拉刷新
                //获取list容器
                //截取时间
                for (let i = 0; i < data.rows.length; i++) {
                    let date = data.rows[i].published;
                    data.rows[i].published = date.substring(0, 10);;
                }

                if (that.data.LoadIsFrist) {
                    dataList = data.rows;
                    that.setData({
                        LoadIsFrist: false, //把第一次加载数据设为为false,已经成了过去时了。
                    });
                } else {
                    dataList = that.data.list.concat(data.rows);
                }
                that.setData({
                    list: dataList,
                    Loadingstatus: true,
                });
                //判断有没有获取到数据
                if (data.rows.length < that.data.limit) {
                    that.setData({
                        LoadOver: true, //把第一次加载数据设为为false,已经成了过去时了。
                        Loadingstatus: false,//把"上拉加载"的变量设为false，隐藏 
                    });
                }
            });
    },

    /**
     * 上拉加载
     */
    onReachBottom: function () {
        var that = this;
        var value;
        console.log('-------------上拉加载-------------');
        that.data.start++;
        value = that.data.inputVal;
        that.initData(value ? value : "");
    },
})


