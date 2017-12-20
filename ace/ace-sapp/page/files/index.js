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
    /**
     * 页面的初始数据
     */
    data: {
        limit: 10,
        type: 1,
        navbar: ['统战文件', '统战资料'],
        currentTab: 0,
        serverfile: cfg.serverfile,
        tongZhanWj: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },
        tongZhanZl: {
            List: [],
            LoadOver: false,
            LoadIsFrist: true,
            Loadingstatus: false,
            start: 0,
        },
    },
    /**切换按钮 */
    navbarTap: function (e) {
        let that = this;
        if (that.data.currentTab === e.target.dataset.idx) {
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.idx
            })
        }
        this.setData({
            inputVal: "",
            inputShowed: false
        });
        that.data.type = parseInt(that.data.currentTab) + 1;
        that.changePage(that.data.type);
        if (objectType.LoadIsFrist) {
            that.contentView('');
        }

    },
    /**
     * 切换页面
     */
    changePage: function (type) {
        let that = this;
        let key = "";
        if (type == 1) {
            key = "Wj";
            objectType = that.data.tongZhanWj;
        } else if (type == 2) {
            key = "Zl";
            objectType = that.data.tongZhanZl;
        }
        listStr = "tongZhan" + key + ".List";
        LoadOverStr = "tongZhan" + key + ".LoadOver";
        LoadIsFristStr = "tongZhan" + key + ".LoadIsFrist";
        LoadingstatusStr = "tongZhan" + key + ".Loadingstatus";
        startStr = "tongZhan" + key + ".start";

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
        that.contentView('');
    },

    // 显示输入框
    showInput: function () {
        let that = this;
        that.setData({
            inputShowed: true
        });
    },

    clearInputStatus: function () {
        let that = this;
        that.setData({
            [listStr]: null,
            [startStr]: 0,
            [LoadIsFristStr]: true,
            [LoadOverStr]: false,
        });
    },

    /**
     * 清除状态
     */
    clearStatus: function () {
        let that = this;
        that.setData({
            inputVal: "",
            [listStr]: null,  //触发到上拉事件，把isFromFile设为为false  
            [startStr]: 0,
            [LoadIsFristStr]: true,
            [LoadOverStr]: false,
        });
    },
    //隐藏输入框
    hideInput: function () {
        this.setData({
            inputVal: "",
            inputShowed: false
        });
        this.clearStatus();
        this.contentView('');
    },
    //清除输入框内的数据
    clearInput: function () {
        this.setData({
            inputVal: ""
        });
        this.clearStatus();
        this.contentView('');
    },
    //实时根据输入框内容进行搜索
    inputTyping: function (e) {
        var that = this;
        this.setData({
            [listStr]: null,
            [startStr]: 0,
            [LoadIsFristStr]: true,
            [LoadOverStr]: false,
            inputVal: e.detail.value
        });  
        if (that.data.inputVal.length >= 2) {
            that.contentView(that.data.inputVal);
        }
        if (that.data.inputVal == '') {
            that.contentView(that.data.inputVal);
        }
    },


    /*加载页面*/
    contentView: function (value) {
        let that = this;
        /*判读有没有下载完毕*/
        if (objectType.LoadOver) {
            return;
        }
        //请求数据。
        util.request(cfg.findFilesList,
            {
                category: that.data.type,
                name: value,
                start: objectType.start * 10,
                limit: that.data.limit
            },
            /*请求数据成功 */
            function (data) {
                let dataList = [];
                wx.hideNavigationBarLoading(); //完成停止加载
                wx.stopPullDownRefresh(); //停止下拉刷新
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
        that.contentView('');
    },

    /**
    * 下拉刷新
    */
    onPullDownRefresh: function () {
        console.log('--------下拉刷新-------');
        var that = this;
        that.setData({
            inputVal: "",
            inputShowed: false
        });
        that.clearStatus();
        that.contentView('');
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

    navbarTap_file: function (e) {
        let that = this;
        console.log(e);
        let urls = that.data.serverfile + e.currentTarget.dataset.fileaddr;
        const downloadTask = wx.downloadFile({
            url: urls, //开启tomcat后的本机ip地址
            success: function (res) {
                console.log("res.tempFilePath");
                console.log(res.tempFilePath);
                var filePath = res.tempFilePath
                wx.openDocument({
                    filePath: filePath,
                    success: function (res) {
                        console.log('打开文档成功')
                        console.log(res)
                    },
                    fail: function (res) {
                        console.log('fail')
                        console.log(res)
                    },
                    complete: function (res) {
                        console.log('complete')
                        console.log(res)
                    }
                })
            }
        })
    }
})
