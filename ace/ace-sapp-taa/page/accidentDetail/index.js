Page({

  /**
   * 页面的初始数据
   */
  data: {
      items: [
          { name: '1', value: '未按规定让行' },
          { name: '2', value: '违反交通信号', checked: 'true' },
          { name: '3', value: '酒后驾驶' },
          { name: '4', value: '无证驾驶' },
          { name: '5', value: '超速行驶' },
          { name: '6', value: '违法倒车' },
          { name: '7', value: '违法上道路行驶' },
          { name: '8', value: '违法变道' },
      ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
 
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },
  checkboxChange(e) {
    console.log('checkbox发生change事件，携带value值为：', e.detail.value)
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