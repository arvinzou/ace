var app = getApp();
var util = require("../../util/util.js");
var cfg = require("../../config.js");

Page({
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/service/index',
      success: function (res) {
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  data: {
    hasLocation: false,
    scale:16,
    showModalStatus: false,
    showBarStatus:false,
    showOrgBarStatus: false,
    showFloatBoxStatus: true,
    showMapStatus: true,
    deptColor:'red',
    orgColor:'#000000',
    category:'01',
    serverfile: cfg.serverfile,
    view: {
      height: '100vh'
    },
    list: [
    ],
    listCategory:[],
    latitude: 29.031673,
    longitude: 111.698497,

    markers: [{
      iconPath: "../../image/jigou.png",
      id: '977577',
      title:'久久鸭脖中心店',
      types:'1',
      latitude: 29.031673,
      longitude: 111.698497,
      width: 35,
      height: 45,
      callout: { content: "久久鸭脖中心店", color:"#000000", fontSize: 14, borderRadius: 5, bgColor: "", padding: 10, display:'ALWAYS'}
      //label: { color: "#000000", fontSize: 20, content:"久久鸭脖中心店", x:2, y:3}
    }],
    polyline: [{
      points: [{
        longitude: '116.481451',
        latitude: '40.006822'
      }, {
        longitude: '116.487847',
        latitude: '40.002607'
      }, {
        longitude: '116.496507',
        latitude: '40.006103'
      }],
      color: "#FF0000DD",
      width: 3,
      dottedLine: true
    }],
    circles: [{
      latitude: '40.007153',
      longitude: '116.491081',
      color: '#FF0000DD',
      fillColor: '#7cb5ec88',
      radius: 400,
      strokeWidth: 2
    }],
    controls: [/*{
      id: 1,
      iconPath: '../../image/locationto.png',
      position: {
        top: 10,
        left: 10,
        width: 50,
        height: 50
      },

      clickable: true
    }*/]
  },
  controltap: function (e) {
    console.log(e.controlId);
    this.getLocation();
  },
  bindtap: function (e) {
    console.log("bindtap click");
    var that = this;
    that.hideModal();
  },
  regionchange:function(e){
    console.log("regionchange click");
  },
  callouttap: function (e) {
    console.log("callouttap click");
    console.log(e);
    var that = this;
    var data = that.data.markers;
    for (var i = 0; i < data.length; i++) {
      var o = data[i];
      if (o.id == e.markerId) {
        that.setData({
          o: o
        });
      }
    }

    that.showModal();
  },
  markertap: function (e) {
    console.log("markertap click");
    console.log("callouttap click");
    console.log(e);
    var that = this;
    var data = that.data.markers;
    for (var i = 0; i < data.length; i++) {
      var o = data[i];
      if (o.id == e.markerId) {
        that.setData({
          o: o
        });
      }
    }

    that.showModal();
    
  },
  tabdept:function(e){
    console.log(e);
    this.setData({
      deptColor: 'red',
      orgColor: '#000000',
      showOrgBarStatus: false,
      view: {
        height: '100vh'
      }
    });
    this.initDeptData('');
  },
  taborg: function (e) {
    console.log(e);
    this.setData({
      deptColor: '#000000',
      orgColor: 'red',
      showOrgBarStatus:true,
      view: {
        height: '90vh'
      }
    });
    this.initOrgData('01');
  },
  tabscaleadd: function (e) {
    console.log(e);
    var that = this;
    that.setData({
      scale: that.data.scale + 1
    });
  },
  tabscale: function (e) {
    console.log(e);
    var that = this;
    that.setData({
      scale: that.data.scale - 1
    });
  },
  //获取经纬度  
  getLocation: function (e) {
    console.log(e)
    var that = this
    wx.getLocation({
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy
        that.setData({
          latitude: latitude,
          longitude: longitude,
          scale: 16
        })
      }
    })
  },
  //根据经纬度在地图上显示  
  openLocation: function (e) {
    console.log("openLocation" + e)
    var value = e.detail.value
    wx.openLocation({
      longitude: Number(value.longitude),
      latitude: Number(value.latitude)
    })
  },
  //选择位置位置  
  chooseLocation: function (e) {
    console.log(e)
    var that = this
    wx.chooseLocation({
      success: function (res) {
        // success  
        console.log(res)
        that.setData({
          hasLocation: true,
          location: {
            longitude: res.longitude,
            latitude: res.latitude
          }
        })
      },
      fail: function () {
        // fail  
      },
      complete: function () {
        // complete  
      }
    })
  },
  clear: function () {
    this.setData({
      hasUserInfo: false,
      userInfo: {}
    })
  },
  onLoad: function (options) {
    var that = this;
    console.log('onLoad');
    //that.showBar()
    that.getLocation();
    that.initDeptData('');
    util.request(cfg.selectOrganizationCategoryList, {},
      function (data) {
        that.setData({
          listCategory: data
        });
      }
    );
  },
  query:function(e){
    console.log(e.currentTarget.id);
    this.initOrgData(e.currentTarget.id);
  },
  initOrgData:function(category){
    var that = this;
    util.request(cfg.selectOrganizationByCategory, { category: category},
      function (data) {
        var markers = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
           o.iconPath = "../../image/location_96px_1175814_easyicon.net.png";
          o.width = 45;
          o.title = o.name;
          o.height = 45;
          o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5/*, display: 'ALWAYS' */ };
          o.label = { content: o.name };
          markers.push(o);
          console.log(o);
        }
        that.setData({
          markers: markers
        });
      }
    );
  }, initDeptData: function (category) {
    var that = this;
    util.request(cfg.selectDeptByCategory, { category: category },
      function (data) {
        var markers = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          o.iconPath = "../../image/location_96px_1175814_easyicon.net.png";
          o.width = 45;
          o.title = o.name;
          o.height = 45;
          o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5/*, display: 'ALWAYS' */ };
          o.label = { content: o.name };
          markers.push(o);
          console.log(o);
        }
        that.setData({
          markers: markers
        });
      }
    );
  },
  showModal: function () {
    // 显示遮罩层
    this.setData({
      showModalStatus: true,
      showBarStatus: false,
      showOrgBarStatus: false,
      view: {
        height: '82vh'
      }
    })
  },
  hideModal: function () {
    // 隐藏遮罩层
    var that=this;
    this.setData({
      showModalStatus: false,
      showOrgBarStatus: true,
      view: {
        height: '100vh'
      }
    });
    //that.showBar();
  },
  navigator: function () {
    var that=this;
    console.log('../' + that.data.o.types + '/index?id=' + that.data.o.id);
    wx.navigateTo({
      url: '../' + that.data.o.types+'/index?id=' + that.data.o.id
    });
     this.hideModal();
  },
  showBar: function () {
    // 显示遮罩层
    this.setData({
      showBarStatus: true,
      showFloatBoxStatus: false,
      showOrgBarStatus: false,
      showMapStatus: false,
      view: {
        height: '0vh'
      }
    })
  },
  hideBar: function (e) {
    console.log(e.currentTarget.id);
    this.initOrgData(e.currentTarget.id);
    // 隐藏遮罩层
    this.setData({
      showBarStatus: false,
      showFloatBoxStatus: true,
      showOrgBarStatus: true,
      showMapStatus: true,
      view: {
        height: '100vh'
      }
    });
  }
})


