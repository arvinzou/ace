Page({

  /**
   * 页面的初始数据
   */
  data: {
      tab: 1,
      latitude: 29.027830,
      longitude: 111.664810,
      markers: [
          {
            iconPath: '../../image/icon-start.png',
            id: 1,
            longitude: 111.664757,
            latitude: 29.026934,
            name: '新外滩',
            width: 28,
            height: 35
        },
          {
              iconPath: '../../image/icon-end.png',
              id: 2,
              longitude: 111.671200,
              latitude: 29.036150,
              name: '天源邻里坊',
              width: 28,
              height: 35
          }
      ],
      polyline: [{
            points: [{
            longitude: 111.664757,
            latitude: 29.026934
      }, {
            longitude: 111.671200,
            latitude: 29.036150
      }],
            color: '#4350FC',
            width: 6,
            dottedLine: false
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  selectRoad: function(){
      wx.navigateTo({
          url: '../collection/index',
      });
  },
  changeTab: function(e){
     var that = this;
     that.setData({
         tab: e.target.dataset.index
     });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})