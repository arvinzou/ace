var Charts = require('../../util/wxcharts.js');
var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    date: '2019-01',
    region: [],
    regionArray: [], //地区列表数组
    type: 0,
    array: [],
    objectArray: [
    ],
    index: 0,
    rIndex: 0, //区域筛选索引
    aIndex: 0, //路长筛选索引
    totalData: null, //
    areaCode: '4307',
    areaCodeNameArray: [],
    dithArray: [], // 死亡人数数组
    nowData: null,
    pastData: null,
    roadId: '',
    timesArray: [], //事故次数数组
    timesAreaArray: [] // 事故地区数组
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.initRegionList(); // 初始化区域位置
    that.initQueryTime() //初始化查询时间
    that.initTrafficList(); //初始化本月交通事故
    that.initTimesColumnReport(); //初始化事故次数柱状图
    that.initDeathColumnReport(); //初始化死亡人数柱状图
    that.initRoadList(); // 初始化路长对比图
  },
  /**
   * 获取行政区划列表
   */
  initRegionList: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/report/findDistrictList', {},
      function(res) {
        if (res.status == 0) {
          console.log(res);
          var data = res.data;
          var tempArr = [];
          for (var i = 0; i < data.length; i++) {
            tempArr.push(data[i].name);
          }
          that.setData({
            region: tempArr,
            regionArray: data
          });
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },
  /**
   * 选择绑定区域
   */
  bindRegionChange: function(e) {
    var that = this;
    var tempIndex = e.detail.value;
    var areaArr = that.data.regionArray;
    this.setData({
      rIndex: tempIndex,
      areaCode: areaArr[tempIndex].code
    });
    console.log("areaCode===========================" + that.data.areaCode);
    that.initTrafficList(); //初始化本月交通事故
  },

  /**
   * 初始化查询时间 
   */
  initQueryTime: function(e) {
    var that = this;
    var d = new Date;
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    if (month < 10) {
      month = '0' + month;
    }
    var ym = year + '-' + month;
    that.setData({
      date: ym
    });
  },

  /**
   * 选择时间
   */
  bindMonthChange: function(e) {
    console.log(e)
    var that = this;
    that.setData({
      date: e.detail.value,
    });
    that.initTrafficList(); //初始化本月交通事故
    that.initDeathColumnReport(); //初始化死亡人数柱状图
    that.initTimesColumnReport(); //初始化事故次数柱状图
  },

  /**
   * 获取统计
   */
  initTrafficList: function() {
    var that = this;
    console.log(that.data.date)
    util.request(cfg.server + '/taa/www/report/multipleReport', {
        areaCode: that.data.areaCode,
        dateTimeStr: that.data.date
      },
      function(res) {
        if (res.status == 0) {
          var data = res.data;
          var top10 = res.data.top10;
          var templateArr = [];
          //处理返回来数据deathNum没有的情况，没有默认赋值为0
          for (var i = 0; i < top10.length; i++){
            if (top10[i].deathNum != undefined) {
              templateArr.push(top10[i]);
            } else {
              templateArr.push({ deathNum: 0, name: top10[i].name });
            }
          }
          console.log(templateArr);
          data.top10 = templateArr;
          that.setData({
            totalData:data,
          });
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },
  /**
   * 事故次数柱状图
   */
  initTimesColumnReport: function() {
    var that = this;
    console.log(that.data.date)
    util.request(cfg.server + '/taa/www/report/histogramReport', {
        category: 'times',
        dateTimeStr: that.data.date
      },
      function(res) {
        if (res.status == 0) {
          var columnData = res.data;
          var timesAreaArray = [];
          var timesArray = [];
          if (columnData != null && columnData != undefined && columnData.length > 0) { //查询无数据
            for (var i = 0; i < columnData.length; i++) {
              //判断是否有该字段返回
              if (columnData[i].areaCodeName != null && columnData[i].areaCodeName != undefined) {
                timesAreaArray.push(columnData[i].areaCodeName);
              }
              //判断是否有该字段返回
              if (columnData[i].num != null && columnData[i].num != undefined) {
                timesArray.push(columnData[i].num);
              } else {
                timesArray.push(0);
              }
            }
            that.setData({
              timesAreaArray: timesAreaArray,
              timesArray: timesArray
            });
          } else { //查询无数据
            var regionArray = that.data.regionArray
            var index = that.data.rIndex;
            timesArray.push(0);
            timesAreaArray.push(regionArray[index].name);
            that.setData({
              timesAreaArray: timesAreaArray,
              timesArray: timesArray
            });
          }
          console.log(timesAreaArray, timesArray)
          that.columnByAccident(); //事故次数绘图
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },

  /**
   * 死亡人数柱状
   */
  initDeathColumnReport: function() {
    var that = this;
    console.log(that.data.date);
    util.request(cfg.server + '/taa/www/report/histogramReport', {
        category: 'death',
        dateTimeStr: that.data.date
      },
      function(res) {
        if (res.status == 0) {
          var columnData = res.data;
          var areaCodeNameArray = [];
          var dithArray = [];
          if (columnData != null && columnData != undefined && columnData.length > 0) {  //查询有数据
            for (var i = 0; i < columnData.length; i++) {
              //判断是否有该字段返回
              if (columnData[i].areaCodeName != null && columnData[i].areaCodeName != undefined) {
                areaCodeNameArray.push(columnData[i].areaCodeName);
              }
              //判断是否有该字段返回
              if (columnData[i].num != null && columnData[i].num != undefined) {
                dithArray.push(columnData[i].num);
              } else {
                dithArray.push(0);
              }
            }
            that.setData({
              areaCodeNameArray: areaCodeNameArray,
              dithArray: dithArray
            });
            
          } else {   //查询无数据
            var regionArray = that.data.regionArray;
            var index = that.data.rIndex;
            dithArray.push(0);
            areaCodeNameArray.push(regionArray[index].name);
            that.setData({
              areaCodeNameArray: areaCodeNameArray,
              dithArray: dithArray
            });
          }
          console.log(areaCodeNameArray, dithArray)
          that.columnByDiedNum(); //死亡人数柱状绘图
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },

  /**
   * 获取所有路长列表
   */
  initRoadList: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/road/findRoadManList', {
        areaCode: that.data.areaCode
      },
      function(res) {
        if (res.status == 0) {
          var data = res.rows;
          console.log(data);
          var array = ['全部'];
          var arrayObject = [{
            'id': '',
            'name': '全部'
          }];
          for (var i = 0; i < data.length; i++) {
            array.push(data[i].name);
            var o = {};
            o.id = data[i].id;
            o.name = data[i].name;
            arrayObject.push(o);
          }
          that.setData({
            array: array,
            objectArray: arrayObject
          });
          that.initReport(); //初始化路长报表
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },
  /**
   * 初始化报告
   */
  initReport: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/report/contrastiveReport', {
        areaCode: that.data.areaCode,
        roadManId: that.data.roadId
      },
      function(res) {
        if (res.status == 0) {
          var data = res.value;
          console.log(data)
          var nowData = res.value.now;
          var pastData = res.value.past;
          that.setData({
            nowData: nowData,
            pastData: pastData
          });
          that.trendLine(); //绘制路长同期对比图
        } else {
          wx.showModal({
            title: '提示',
            content: res.errorMessage,
            success: function(res) {}
          });
        }
      }
    );
  },
  /**
   * 柱状图切换
   */
  changeChartType: function(e) {
    var that = this;
    console.log(e);
    that.setData({
      type: e.target.dataset.id
    });
  },
  /**
   * 事故次数画图
   */
  columnByAccident: function() {
    var that = this;
    var timesAreaArray = that.data.timesAreaArray;
    var timesArray = that.data.timesArray;
    console.log("================================" + timesAreaArray + "," + timesArray);
    new Charts({
      canvasId: 'accidentColumn',
      type: 'column',
      categories: timesAreaArray,
      series: [{
        name: '次',
        data: timesArray
      }],
      yAxis: {
        format: function(val) {
          return val + '次';
        }
      },
      extra: {
        column: {
          width: 15
        }
      },
      width: 350,
      height: 300,
      dataLabel: false
    });
  },
  /**
   * 死亡人数画图
   */
  columnByDiedNum: function(areaCodeNameArray, dithArray) {
    var that = this;
    console.log("================================" + that.data.areaCodeNameArray + "," + that.data.dithArray);
      new Charts({
        canvasId: 'diedNumColumn',
        type: 'column',
        categories: that.data.areaCodeNameArray,
        series: [{
          name: '人',
          data: that.data.dithArray
        }],
        yAxis: {
          format: function(val) {
            return val + '人';
          }
        },
        extra: {
          column: {
            width: 15
          }
        },
        width: 350,
        height: 300,
        dataLabel: false
      });
  },
  /**
   * 路长同期对比画图
   * 
   */
  trendLine: function() {
    var that = this;
    new Charts({
      canvasId: 'trendLine',
      type: 'line',
      categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      series: [{
        name: '今年',
        data: [that.data.nowData.January, that.data.nowData.February, that.data.nowData.March, that.data.nowData.April, that.data.nowData.May, that.data.nowData.June, that.data.nowData.July, that.data.nowData.August, that.data.nowData.September, that.data.nowData.October, that.data.nowData.November, that.data.nowData.December],
        format: function(val) {
          return val;
        }
      }, {
        name: '去年',
        data: [that.data.pastData.January, that.data.pastData.February, that.data.pastData.March, that.data.pastData.April, that.data.pastData.May, that.data.pastData.June, that.data.pastData.July, that.data.pastData.August, that.data.pastData.September, that.data.pastData.October, that.data.pastData.November, that.data.pastData.December],
        format: function(val) {
          return val;
        }
      }],
      yAxis: {
        title: '次',
        format: function(val) {
          return val;
        },
        min: 0
      },
      width: 350,
      height: 300
    });
  },
  /**
   * 切换路长
   */
  bindRoadChange: function(e) {
    var that = this;
    that.setData({
      index: e.detail.value
    });
    var road = that.data.objectArray[e.detail.value].id;
    that.setData({
      roadId: road
    })
    that.initReport();
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

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})