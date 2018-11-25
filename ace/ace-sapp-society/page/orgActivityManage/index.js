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
        showBtn: false,
        hiddenBtn: true,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that=this;
        that.optionUrl();
    },
    optionUrl:function(){
        let that = this;
        let user = util.getSysUser();
        that.data.url = cfg.adminActivityList;  
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
    //时间没到点击签到
    notTimeSign:function(){
        wx.showToast({
            title: '活动没有开始',
            icon: 'info',
            duration: 2000
        })
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
        let that=this;
        let sysUserInfo = util.getSysUser();
        //如果没有注册，尝试重新申请获取一次。
        if (sysUserInfo.societyOrg.status == '3') {
            that.setData({
                hiddenBtn: false,
            });
        }
        that.clearStatus();
        that.initdata();
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
            sizeType: ['compressed'],
            sourceType: ['camera'],
            success(res) {
                that.uploadFileFun(res.tempFilePaths[0], typ,id);
            }
        })
    },
    // 删除活动
    delActivity: function (e) {
        let that = this;
        let id = e.currentTarget.dataset.id;
        wx.showModal({
            title: '提示',
            content: '确定删除活动吗？',
            success: function (res) {
                if (res.confirm) {
                    util.request(cfg.delActivity, {
                        id: id,
                    },
                        function (data) {
                            console.log(data.data);
                            wx.hideNavigationBarLoading(); //完成停止加载
                            wx.stopPullDownRefresh(); //停止下拉刷新
                            that.onPullDownRefresh();
                        }
                    );
                } 
            }
        });
    },
    //修改活动
    editActivity: function (e) {
        let that = this;
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '../activityEdit/index?id='+id,
        })
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
            url: cfg.uploadUrl,
            filePath: tempFilePaths,
            name: 'file',
            success: function (res) {
                var data = JSON.parse(res.data);
                var url = data.file_path;
                util.request(cfg.activitySign, {
                    filePath: url,
                    type: type,
                    id: id,
                },
                    function (rst) {
                        console.log(rst);
                        if (rst.status==0){
                            wx.showToast({
                                title: '成功',
                                icon: 'success',
                                duration: 2000,
                                complete: function () {
                                    that.onPullDownRefresh();
                                }
                            })
                        }else{
                            wx.showToast({
                                title: '失败!',
                                icon: 'none',
                                duration: 2000
                            })
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
    },
    viewContent: function (e) {
        console.log(e);
        let data = e.currentTarget.dataset
        let p = data.id;
        let title = data.title;
        wx.navigateTo({ url: '../activityInfo/index?id=' + p + "&title=" + title })
    },
    //时间没到点击签到
    notTimeStart: function () {
        wx.showToast({
            title: '没有到开始时间',
            icon: 'info',
            duration: 2000
        })
    },
    onPageScroll: function (e) {
        if (this.data.hiddenBtn) {
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
    createActivity: function () {
        let that = this;
        wx.navigateTo({ url: '../activityApply/index?category=1'})
    },
})