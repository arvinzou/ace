Page({

  /**
   * 页面的初始数据
   */
  data: {
      array: ['个人', '社会组织', '党员', '党组织'],
      objectArray: [
          {
              id: 0,
              name: '个人'
          },
          {
              id: 1,
              name: '社会组织'
          },
          {
              id: 2,
              name: '党员'
          },
          {
              id: 3,
              name: '党组织'
          }
      ],
      index: 0,

      groupArray: ['心阳光联盟', '华彩伟业'],
      objectGroupArray: [
          {
              id: 0,
              name: '心阳光联盟'
          },
          {
              id: 1,
              name: '华彩伟业'
          }
      ],
      group_index: 0,

      stepNum: 1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  bindPickerChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            index: e.detail.value
        })
    },

    bindPickerChange1: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            group_index: e.detail.value
        })
    },
    nextOne: function () {
        var that = this;
        this.setData({
            stepNum: ++that.data.stepNum,
            topNum: 0,
        });
    },

    previousOne: function () {
        var that = this;
        this.setData({ stepNum: --that.data.stepNum, topNum: 0, });
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