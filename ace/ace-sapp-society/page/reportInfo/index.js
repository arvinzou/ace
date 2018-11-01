// page/reportInfo/index.js
var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '',
        list: {},
        actionComment: false,
        commentVal: '',
        commentList:[],
        CTotal:0,
        likeNum:0,
        like:false,
        likeUrl:'',
        LoadOver: false,
        Loadingstatus: false,
        start: 0,
        limit: 10,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        let id = options.id;
        let tit = options.title;
        if (!id) {
            wx.navigateBack({})
            return;
        }
        that.data.id = id;
        that.setBarTitleText(tit);
        that.initdata();
        that.getLikeNum();
        that.getCommentList();
    },


    getLikeNum(){
        let that = this;
        util.request(cfg.findAdmires, {
            bisId: that.data.id,
            bisType: "reportLike",
        },
            function (rst) {
                that.setData({
                    likeNum: rst.data.allLike,
                    like: rst.data.iLiet,
                    initNum: rst.data.allLike
                })
            });
    },

    getCommentList:function(){
        let that = this;
        if (that.data.LoadOver) {
            return;
        }
        that.showLoading();
        util.request(cfg.findComments, {
            bisId: that.data.id,
            bisType: "reportComment",
            start: that.data.start,
            limit: that.data.limit,
        },
            function (rst) {
                if(rst.status==0){
                    if(that.data.start==0){
                        that.setData({
                            CTotal:rst.data.total,
                        })
                    }
                    that.setData({
                        commentList: that.data.commentList.concat(rst.data.rows),
                        Loadingstatus: false,
                    });
                    if (rst.data.rows.length < that.data.limit) {
                        that.setData({
                            LoadOver: true,
                        });
                    }
                }
            });
    },

    // 显示加载
    showLoading: function () {
        let that = this;
        that.setData({
            Loadingstatus: true,
        });
    },


    // 获取列表
    initdata: function() {
        let that = this;
        util.request(cfg.findActivityReport, {
                id: that.data.id,
            },
            function(rst) {
                let temp = rst.data;
                temp.content=JSON.parse(temp.content);
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    list: temp
                });
            }
        );
    },
    // 设置头标题
    setBarTitleText: function(tit) {
        wx.setNavigationBarTitle({
            title: tit
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
        let that =this;
        if (that.data.initNum == that.data.likeNum){
            return;
        }
        util.request(that.data.likeUrl, {
            bisId: that.data.id,
            bisType: "reportLike",
        },
            function (rst) {
                if (rst.status == 0) {
                    that.setData({
                        likeNum: that.data.likeNum,
                        like: that.data.iLike,
                    });
                    return;
                }
                wx.showModal({
                    title: '提示',
                    content: '点赞失败，请重试',
                })
            });
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {
        return;
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        var that = this;
        console.log('-------------上拉加载-------------');
        that.data.start += that.data.limit;
        that.getCommentList();
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
    actionComment: function() {
        this.setData({
            actionComment: true,
        })
    },
    hiddenComment: function(e) {
        let that = this;
        if (that.data.commentVal) {
            wx.showModal({
                title: '提示',
                content: '确定放弃评论？',
                success: function(res) {
                    if (!res.confirm) {
                        return;
                    }
                }
            })
        }
        that.setData({
            actionComment: false,
        })
    },
    getValue: function(e) {
        let that = this;
        that.data.commentVal = e.detail.value;
    },
    formSubmit: function (e) {
        let that=this;
        let vals=e.detail.value;
        if (!vals.content){
            wx.showModal({
                title: '提示',
                content: '没有输入任何内容',
            })
            return;
        }
        vals.bisType="reportComment";
        vals.bisId=that.data.id;
        util.request(cfg.submitComment, vals,
            function (rst) {
                if(rst.status==0){
                    that.setData({
                        actionComment: false,
                    })
                    that.clearStatus();
                    that.getCommentList();
                    return;
                }
                wx.showModal({
                    title: '提示',
                    content: '评论失败，稍后重试',
                })
        });
    },
    // 清空状态
    clearStatus: function () {
        let that = this;
        that.data.start = 0;
        that.setData({
            LoadOver: false,
            commentList: [],
        })
    },
    actionLike:function(){
        let that=this;
        let url,num,iLike;
        if (that.data.like) {
            url = cfg.cancelAdmire
            num = -1;
            iLike = false;
        } else {
            url = cfg.admire
            num = 1;
            iLike = true;
        }
        that.setData({
            likeNum: that.data.likeNum + num,
            like: iLike,
            likeUrl:url,
        });
    }
})