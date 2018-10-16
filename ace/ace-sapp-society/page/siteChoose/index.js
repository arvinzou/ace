Page({

  /**
   * 页面的初始数据
   */
  data: {
    daysArr: ["一","二","三","四","五","六","日"]
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     
  },

  initCalendar: function(){
      var d = new Date();
      var y = d.getFullYear();
      var m = d.getMonth() + 1;
      var ds = d.getDate();
      var days = d.getDay();
      switch (days) {
          case 1:
              days = daysArr[0];
              break;
          case 2:
              days = daysArr[1];
              break;
          case 3:
              days = daysArr[2];
              break;
          case 4:
              days = daysArr[3];
              break;
          case 5:
              days = daysArr[4];
              break;
          case 6:
              days = daysArr[5];
              break;
          case 7:
              days = daysArr[6];
              break;
      }
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