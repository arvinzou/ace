var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp()
const TITLE_HEIGHT = 30
const ANCHOR_HEIGHT = 18
Page({
    data: {
        toView: '',
        logs: [],
        scrollTop: 0,
        listHeight: [],
        currentIndex: 0,
        fixedTitle: '',
        fixedTop: 0,
        list: [
        
        ],
        pageType: null
    },
    onLoad: function (options) {
        var that = this;
        that.setData({
            pageType : options.type
        })
        that.initRoadList();
    },
    
    initRoadList: function(){
        var that = this;
        util.request(cfg.server + '/taa//www/road/roster', { },
            function (res) {
                if (res.status == 0) {
                    that.setData({
                        list: res.data
                    });
                    var list = res.data;
                    wx.hideLoading();
                    that.setData({
                        logs: that._normalizeSinger(list)
                    });

                    that._calculateHeight()
                } else {
                    wx.showModal({
                        title: '提示',
                        content: res.info,
                        success: function (res) { }
                    });
                }

            }
        );
    },
    _normalizeSinger(list) {
        //歌手列表渲染
        let map = {
           
        }
        list.forEach((item, index) => {
            const key = item.index
            if (!map[key]) {
                map[key] = {
                    title: key,
                    items: []
                }
            }
            map[key].items.push({
                name: item.name,
                id: item.id
            })
        })
        // 为了得到有序列表，我们需要处理 map
        let ret = [];
        for (let key in map) {
            let val = map[key]
            if (val.title.match(/[a-zA-Z]/)) {
                ret.push(val)
            } 
        }
        ret.sort((a, b) => {
            return a.title.charCodeAt(0) - b.title.charCodeAt(0)
        });
        return ret;
    },
    scroll: function (e) {
        var newY = e.detail.scrollTop;
        this.scrollY(newY);
    },
    scrollY(newY) {
        const listHeight = this.data.listHeight
        // 当滚动到顶部，newY>0
        if (newY == 0 || newY < 0) {
            this.setData({
                currentIndex: 0,
                fixedTitle: ''
            })
            return
        }
        // 在中间部分滚动
        for (let i = 0; i < listHeight.length - 1; i++) {
            let height1 = listHeight[i]
            let height2 = listHeight[i + 1]
            if (newY >= height1 && newY < height2) {
                this.setData({
                    currentIndex: i,
                    fixedTitle: this.data.logs[i].title
                })
                this.fixedTt(height2 - newY);
                return
            }
        }
        // 当滚动到底部，且-newY大于最后一个元素的上限
        this.setData({
            currentIndex: listHeight.length - 2,
            fixedTitle: this.data.logs[listHeight.length - 2].title
        })
    },
    fixedTt(newVal) {
        let fixedTop = (newVal > 0 && newVal < TITLE_HEIGHT) ? newVal - TITLE_HEIGHT : 0
        if (this.data.fixedTop === fixedTop) {
            return
        }
        this.setData({
            fixedTop: fixedTop
        })
    },
    _calculateHeight() {
        var lHeight = [],
            that = this;
        let height = 0;
        lHeight.push(height);
        var query = wx.createSelectorQuery();
        query.selectAll('.list-group').boundingClientRect(function (rects) {
            var rect = rects,
                len = rect.length;
            for (let i = 0; i < len; i++) {
                height += rect[i].height;
                lHeight.push(height)
            }

        }).exec();
        var calHeight = setInterval(function () {
            if (lHeight != [0]) {
                that.setData({
                    listHeight: lHeight
                });
                clearInterval(calHeight);
            }
        }, 1000)
    },
    scrollToview(e) {
        var id = e.target.dataset.id
        this.setData({
            toView: id
        });

    },
    selectRoadMan: function(e){
        var that = this;
        var roadManId = e.currentTarget.dataset.id;
        var roadManName = e.currentTarget.dataset.name;
        var skipType = that.data.pageType;
        wx.setStorageSync('roadManId', roadManId);
        wx.setStorageSync('roadManName', roadManName);
        if (skipType == 'kb'){
            //快报选择路长
            wx.switchTab({
                url: '../index/index',
            });
        }else if(skipType == 'xb'){
            //续报选择路长
            wx.navigateTo({
                url: '../accidentDetail/index',
            });
        }
    }
})
