Page({

  /**
   * 页面的初始数据
   */
  data: {
      tab: 0,
      isNull: 0,
      wIndex: 0,
      cIndex: 0,
      latitude: null,
      longitude: null,
      current: [],
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
      }],

      weather: ['晴', '多云', '小雨', '中雨','大雨'],
      weatherArray: [
          {
              id: 0,
              name: '晴'
          },
          {
              id: 1,
              name: '多云'
          },
          {
              id: 2,
              name: '小雨'
          },
          {
              id: 3,
              name: '中雨'
          },
          {
              id: 4,
              name: '大雨'
          }
      ],

      carType: ['suv', '皮卡', '小轿车', '跑车', '面包车'],
      carTypeArray: [
          {
              id: 0,
              name: 'suv'
          },
          {
              id: 1,
              name: '皮卡'
          },
          {
              id: 2,
              name: '小轿车'
          },
          {
              id: 3,
              name: '跑车'
          },
          {
              id: 4,
              name: '面包车'
          }
      ],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     var that = this;
     that.getLocation();
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
  getLocation: function(e){
      var that = this;
      wx.getLocation({
          success: function(res) {
              var latitude = res.latitude;
              var longitude = res.longitude;
              var o = {
                iconPath: '../../image/icon-locate.png',
                longitude: res.longitude,
                latitude: res.latitude,
                width: 25,
                height: 25   
              }
              var arr = [];
              arr.push(o);
              that.setData({
                  latitude: latitude,
                  longitude: longitude,
                  current: arr
              });
          },
      })
  },
  bindWeatherChange: function(e){
      this.setData({
          wIndex: e.detail.value
      })
  },
  bindCarChange: function(e){
      this.setData({
          cIndex: e.detail.value
      })
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