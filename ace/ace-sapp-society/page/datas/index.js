var util = require("../../util/util.js");
var cfg = require("../../config.js");
var Charts = require('../../util/wxcharts.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      societyData: null,
      access_tocken: null,
      statistics: null,
      usersList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.getConfig();
      that.initSocietyData();
  },
  /**
   * 获取acccess_tocken参数
   */
  getConfig: function(e){
      var that = this;
      util.request(cfg.server + '/portal/www/wx/jsapi/getWxCfg', { "sysId": "societyMiniApp", },
          function (ret) {
              if (ret.status == 0) {
                  that.initData(ret.data.accessToken);
                  //that.initVisitData(ret.data.accessToken);
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },
    initData: function (access_tocken){
      var that = this;
        util.post('https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend?access_token=' + access_tocken, 
            {
                "begin_date": "20181001",
                "end_date": "20181031"
            },
          function (ret) {
              that.setData({ statistics: ret.list[0]})
          }
      );
  },

    /*initVisitData: function (access_tocken){
        var that = this;
        util.post('https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token=' + access_tocken,
            {
                "begin_date": "2018-11-04",
                "end_date": "2018-11-04"
            },
            function (ret) {
                console.log(ret);
            }
        );
    },*/

  /**
   * 统计芙蓉街道后台发布的数据
   */
  initSocietyData: function(e){
      var that = this;
      util.request(cfg.server+'/society/www/anslysis/query', { "reportId": "portal", },
          function (ret) {
              if (ret.status == 0) {
                  var retData = ret.value;
                  var users = 0;
                  var data = {};
                  for (var i = 0; i < retData.length; i++){
                      if (retData[i].id == 'person' || retData[i].id == 'org'){
                          users = users + parseInt(retData[i].value);
                      }
                      data[retData[i].id] = retData[i].value;
                  } 
                  data['users'] = users;
                  that.setData({ societyData: data});
                  that.initUsers(data);
                  that.initActivitycolumn(data);
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },

  /**
   * 统计用户饼状图
   */
  initUsers: function(data){
      new Charts({
          animation: true,
          canvasId: 'userCanvas',
          type: 'ring',
          extra: {
              ringWidth: 25,
              pie: {
                  offsetAngle: -45
              }
          },
          title: {
              name: '累计用户（人）',
              color: '#999999',
              fontSize: 15
          },
          subtitle: {
              name: data.users,
              color: '#999999',
              fontSize: 25
          },
          series: [{
              name: '组织会员',
              data: data.org,
              stroke: false
          }, {
              name: '个人会员',
              data: data.person,
              stroke: false
          }],
          disablePieStroke: true,
          width: 350,
          height: 350,
          dataLabel: true,
          legend: true,
          padding: 0
      });
  },
  
  /**
   * 活动柱状图
   */
  initActivitycolumn: function(data){
      new Charts({
          canvasId: 'activityCanvas',
          type: 'column',
          categories: ['线下活动', '文明随手拍', '我有点子', '秀我直播', '邻里圈子'],
          series: [{
              name: '审核成功',
              data: [data.activityAll, data.behavior, data.subjectIdea, data.live, data.circle]
          }],
          yAxis: {
              format: function (val,name) {
                  return val + '次';
              }
          },
          xAxis: {
              disableGrid: true,
              type: 'calibration'
          },
          extra: {
              column: {
                  width: 15
              }
          },
          width: 350,
          height: 300
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
      var that = this;
      that.getConfig();
      that.initSocietyData();
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