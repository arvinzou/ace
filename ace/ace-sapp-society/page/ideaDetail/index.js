var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        detail: null,
        actionComment: false,
        commentVal: '',
        commentList: [],
        CTotal: 0,
        likeNum: 0,
        like: false,
        likeUrl: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        if (!util.is_login()) {
            wx.navigateTo({
                url: "../userinfo/index?url=../ideaDetail/index"
            });
        }
        if (util.is_login()) {
            that.setData({
                userinfo: wx.getStorageSync('userinfo')
            });
            if (options.belongTo == 0) {
                that.setData({
                    isComment: 1
                });
            }
            that.initData();
            that.getLikeNum();
            that.getCommentList();
        }
    },
    initData: function() {
        var that = this;
        util.request(cfg.ideaDetail, {
                "ideaId": wx.getStorageSync('ideaId'),
                "unionId": wx.getStorageSync('WX-SESSION-ID')
            },
            function(ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({
                        detail: ret.data
                    });
                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.errorMessage,
                        success: function(res) {}
                    });
                }

            }
        );
    },

    getLikeNum() {
        let that = this;
        util.request(cfg.findAdmires, {
                bisId: wx.getStorageSync('ideaId'),
                bisType: "idea",
            },
            function(rst) {
                that.setData({
                    likeNum: rst.data.allLike,
                    like: rst.data.iLiet,
                    initNum: rst.data.allLike
                })
            });
    },

    getCommentList: function() {
        let that = this;
        if (that.data.LoadOver) {
            return;
        }
        util.request(cfg.findComments, {
                bisId: wx.getStorageSync('ideaId'),
                bisType: "idea",
            },
            function(rst) {
                if (rst.status == 0) {
                    that.setData({
                        commentList: rst.data.rows,
                        Loadingstatus: false,
                        CTotal: rst.data.total,
                    });
                }
            });
    },
    onUnload: function () {
        let that = this;
        if (that.data.initNum == that.data.likeNum) {
            return;
        }
        util.request(that.data.likeUrl, {
            bisId: wx.getStorageSync('ideaId'),
            bisType: "idea",
        },
            function (rst) {
                if (rst.status == 0) {
                    return;
                }
                wx.showModal({
                    title: '提示',
                    content: '点赞失败，请重试',
                })
            });
    },

    downloadFile: function(e) {
        var that = this;
        var fileObjList = e.currentTarget.dataset.value;
        var fileList = [];
        for (var i = 0; i < fileObjList.length; i++) {
            fileList.push(fileObjList[i].fileUrl);
        }
        wx.previewImage({
            current: fileList[0], // 当前显示图片的http链接
            urls: fileList // 需要预览的图片http链接列表
        });
    },
    /**
     * 发布评论
     */
    releaseComment: function() {

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
        var that = this;
        that.setData({
            userinfo: wx.getStorageSync('userinfo')
        });
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {
        var that = this;
        that.initData();
        that.initCommentList();
        that.initAdmireTotal();
    },
    formSubmit: function(e) {
        let that = this;
        let vals = e.detail.value;
        if (!vals.content) {
            wx.showModal({
                title: '提示',
                content: '没有输入任何内容',
            })
            return;
        }
        vals.bisType = "idea";
        vals.bisId = wx.getStorageSync('ideaId');
        util.request(cfg.submitComment, vals,
            function(rst) {
                if (rst.status == 0) {
                    that.setData({
                        actionComment: false,
                    })
                    // that.clearStatus();
                    that.getCommentList();
                    return;
                }
                wx.showModal({
                    title: '提示',
                    content: '评论失败，稍后重试',
                })
            });
    },
    addlike: function(e) {
        console.log(e.currentTarget.dataset.id);
        var that = this;
        util.request(cfg.server + "/society/www/comment/admire", {
                "bisId": e.currentTarget.dataset.id,
                "bisType": 'idea',
                "unionId": wx.getStorageSync('WX-SESSION-ID')
            },
            function(data) {
                that.initCommentList();
            }
        );
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
                    that.setData({
                        actionComment: false,
                    })
                }
            })
        }else{
            that.setData({
                actionComment: false,
            })
        }
    },
    getValue: function(e) {
        let that = this;
        that.data.commentVal = e.detail.value;
    },
    actionLike: function() {
        let that = this;
        let url, num, iLike;
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
            likeUrl: url,
        });
    },
    actionComment: function() {
        this.setData({
            actionComment: true,
        })
    },
})