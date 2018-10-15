Page({
    data: {
        navbarArray: [{
            text: '活动精选',
            type: 'navbar-item-active'
         }, 
        {
            text: '党建活动',
            type: ''
        },
        {
            text: '公益活动',
            type: ''
        }, {
            text: '普及活动',
            type: ''
        }, {
            text: '创意活动',
            type: ''
        }],
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
            item.type = '';
            if (index === targetChannelIndex) {
                item.type = 'navbar-item-active';
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
});