var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        list: [],
        start: 0,
        limit: 10,
        LoadOver: false,
        Loadingstatus: false,
        nowDate: new Date().Format("yyyy-MM-dd HH:mm:ss"),
        url:'',
        type:2,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that=this;
        that.optionUrl();
        that.initdata();
    },
    optionUrl:function(){
        let that = this;
        let user = util.getSysUser();
        if (user.regType == 2) {

            that.data.url = cfg.adminActivityList;
        } else if (user.regType == 1){
            that.data.url = cfg.userActivityList;
        }
    },


    // 获取列表
    initdata: function() {
        console.log("initData");
        let that = this;
        if (that.data.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(that.data.url, {
                category: 1,
                start: that.data.start,
                limit: that.data.limit
            },
            function(data) {
                console.log(data.data);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    type:that.data.type,
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
    // 显示加载
    showLoading: function () {
        let that = this;
        that.setData({
            Loadingstatus: true,
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {
        console.log('--------下拉刷新-------')
        var that = this;
        that.clearStatus();
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

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start += that.data.limit;
        that.initdata();
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
    activitySign:function(e){
        let that = this;
        let typ=e.currentTarget.dataset.type;
        let id = e.currentTarget.dataset.id;
        wx.chooseImage({
            sourceType: ['camera'],
            success(res) {
                that.uploadFileFun(res.tempFilePaths[0], typ,id);
            }
        })
    },

    delActivity: function (e) {
        let that = this;
        let id = e.currentTarget.dataset.id;
        util.request(cfg, {
            id: id,
        },
        function (data) {
                console.log(data.data);
                wx.hideNavigationBarLoading(); //完成停止加载
                wx.stopPullDownRefresh(); //停止下拉刷新
                that.onPullDownRefresh();
            }
        );
    },
    postReport:function(e){
        let that=this;
        let id = e.currentTarget.dataset.id;
        if(id){
            wx.navigateTo({
                url: '../postReport/index?id=' + id,
            })
        }
        return;
    },

    // 上传文件方法
    uploadFileFun: function (tempFilePaths, type,id) {
        let that = this;
        wx.uploadFile({
            url: cfg.server+'/portal/www/uploadFile.do',
            filePath: tempFilePaths,
            name: 'file',

            formData: {
                collectionName: 'ceshi',
                id: '111'
            },
            success: function (res) {
                var data = JSON.parse(res.data);
                var url = cfg.server + data.value[0].fileUrl;
                util.request(cfg.activitySign, {
                    filePath: url,
                    type: type,
                    id: id,
                },
                    function (data) {
                        console.log(data.data);
                        wx.hideNavigationBarLoading() //完成停止加载
                        wx.stopPullDownRefresh() //停止下拉刷新
                        that.setData({
                            type: that.data.type,
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
            fail: function (res) {
                wx.showModal({
                    title: '提示',
                    content: '图爿上传失败',
                })
            },
        })
    }
})