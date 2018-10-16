Page({
    data: {
        navbarArray: [{
            text: '活动精选',
            active: 'navbar-item-active'
         }, 
        {
            text: '党建活动',
            active: ''
        },
        {
            text: '公益活动',
            active: ''
        }, {
            text: '普及活动',
            active: ''
        }, {
            text: '创意活动',
            active: ''
        }],
        lists:{
            jx: {
                list:[],
                scroll:''
            },
            gy: {
                list: [],
                scroll: ''
            },
            pj: {
                list: [],
                scroll: ''
            },
            dj: {
                list: [],
                scroll: ''
            },
            cy: {
                list: [],
                scroll: ''
            },
        },
        nowType:'jx',
        loadingModalHide: false
    },


   

    // 下拉刷新
    onPullDownRefresh: function () {
        wx.stopPullDownRefresh();
    },
    onTapNavbar: function (e) {
        this.switchChannel(parseInt(e.currentTarget.id));
    },
    switchChannel: function (targetChannelIndex) {
        this.getArticles(targetChannelIndex);

        let navbarArray = this.data.navbarArray;
        navbarArray.forEach((item, index, array) => {
            item.active = '';
            if (index === targetChannelIndex) {
                item.active = 'navbar-item-active';
            }
        });
        this.setData({
            navbarArray: navbarArray,
            currentChannelIndex: targetChannelIndex
        });
    },
    getArticles: function (index) {
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
    recordY:function(e){
        let that=this;
        that.data.lists[that.data.nowType].scroll = e.detail.scrollTop;
    }
});