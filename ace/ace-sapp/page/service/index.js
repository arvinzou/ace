var app = getApp();
var util = require("../../util/util.js");
var cfg = require("../../config.js");
//引入百度地图api
var bmap = require('../../util/bmap-wx.js');
var BMap = {};
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
  onReady: function (e) {    // 使用 wx.createMapContext 获取 map 上下文 
    this.mapCtx = wx.createMapContext('myMap');
    
  },
  data: {
    showSearchRsPanel: false,
    hasLocation: false,
    circlesStatus: false,
    scale: 16,
    includePointsStatus:true,
    showModalStatus: false,
    showBarStatus: false,
    showOrgBarStatus: false,
    showFloatBoxStatus: true,
    showMapStatus: true,
    activeTarget:'dept',
    deptIcon: 'tongzhandw_selected.png',
    orgIcon: 'bangzhu.png',
    personageIcon: 'tongzhanrs.png',
    category: '01',
    serverfile: cfg.serverfile,
    lastChange: new Date().getTime(),
    view: {
      height: '93vh'
    },
    list: [
    ],
    listCategory: [],
    latitude: 29.031673,
    longitude: 111.698497,
    includePoints: [],
    markers: [{
      iconPath: "../../image/jigou.png",
      id: '977577',
      title: '久久鸭脖中心店',
      types: '1',
      latitude: 29.031673,
      longitude: 111.698497,
      width: 35,
      height: 45,
      callout: { content: "久久鸭脖中心店", color: "#000000", fontSize: 14, borderRadius: 5, bgColor: "", padding: 10, display: 'ALWAYS' }
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
    controls: []
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
  regionchange: function (e) {
    var that = this;
   
    var now = new Date();
    var lastChange = that.data.lastChange;
    console.log(lastChange);
    var av = now.getTime() - lastChange  //时间差的毫秒数
    //移动结束，找到视野中心点重新加载
    console.log(av);
    if(av>1000){
      that.getCenterLocation();
    }
    that.setData({
      lastChange: new Date().getTime()
    })
  },
  getCenterLocation: function () {
    var that = this;
    this.mapCtx.getCenterLocation({
      success: function (res) {
        console.log(res.longitude);
        console.log(res.latitude);
        if (res.latitude != that.data.latitude){
          that.setData({
            latitude: res.latitude,
            longitude: res.longitude,
            includePointsStatus:false
          })
          that.reloadData();
         
        }
        
      }
    })
  },
  reloadData:function(){
    var that=this;
    if (that.data.activeTarget == 'dept') {
      that.initDeptData('');
    }
    if (that.data.activeTarget == 'org') {
      that.initOrgData('');
    }
    if (that.data.activeTarget == 'personage') {
      that.initPersonageData('');
    }
  }
  ,
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
  tabdept: function (e) {
    console.log(e);
    this.setData({
      deptIcon: 'tongzhandw_selected.png',
      orgIcon: 'bangzhu.png',
      personageIcon: 'tongzhanrs.png',
      showOrgBarStatus: false,
      activeTarget: 'dept',
      includePointsStatus:true,
      view: {
        height: '93vh'
      }
    });
    this.initDeptData('');
  },
  taborg: function (e) {
    console.log(e);
    this.setData({
      deptIcon: 'tongzhandw.png',
      orgIcon: 'bangzhu_selected.png',
      personageIcon: 'tongzhanrs.png',
      activeTarget: 'org',
      showOrgBarStatus: true,
      includePointsStatus: true,
      view: {
        height: '82vh'
      }
    });
    this.initOrgData('');
  },
  tabpersonage: function (e) {
    console.log(e);
    this.setData({
      deptIcon: 'tongzhandw.png',
      orgIcon: 'bangzhu.png',
      personageIcon: 'tongzhanrs_selected.png',
      showOrgBarStatus: false,
      includePointsStatus: true,
      activeTarget: 'personage',
      view: {
        height: '93vh'
      }
    });
    this.initPersonageData('');
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
        that.reloadData();
      }
    })
  },
  getCircles: function (e) {
    console.log(e)
    var that = this
    wx.getLocation({
      success: function (res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        var speed = res.speed;
        var accuracy = res.accuracy;
        var cir = [{
          latitude: res.latitude,
          longitude: res.longitude,
          color: '#FF0000DD',
          fillColor: '#7cb5ec88',
          radius: 500,
          strokeWidth: 1
        }];
        if (!that.data.circlesStatus) {
          cir = [];
        }
        that.setData({
          latitude: latitude,
          longitude: longitude,
          scale: 16,
          circles: cir,
          circlesStatus: !that.data.circlesStatus
        })

      }
    })
  },
  //根据经纬度在地图上显示  
  openLocation: function (e) {
    console.log(e.currentTarget.dataset)
    var o = e.currentTarget.dataset;
    wx.openLocation({
      longitude: Number(o.longitude),
      latitude: Number(o.latitude),
      name: o.name,
      address: o.address
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
      },
      complete: function () {
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
    that.getLocation();
    that.initDeptData('');
    util.request(cfg.selectOrganizationCategoryList, {},
      function (data) {
        that.setData({
          listCategory: data
        });
      }
    );
    //构造百度地图api实例
    BMap = new bmap.BMapWX({
      ak: 'cPY4B8MAYgPQYOuDKPTNvUin31DBPDCB'
    })
  },
  initOrgData: function (text) {
    var that = this;
    util.request(cfg.selectOrganizationByCategory, { category: that.data.category, longitude: that.data.longitude, latitude: that.data.latitude,q:text },
      function (data) {
        var markers = [];
        var includePoints = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          o.iconPath = "../../image/location_96px_1175814_easyicon.net.png";
          o.width = 45;
          o.title = o.name;
          o.height = 45;
          o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5, display: 'ALWAYS' };
          //o.label = { content: o.name, color: "#696969", fontSize:14 };
          markers.push(o);
          includePoints.push({
            longitude: o.longitude,
            latitude: o.latitude
          });
          console.log(o);
        }
        if (!that.data.includePointsStatus){
          includePoints.length = 0;
        }
        that.setData({
          markers: that.data.markers.concat(markers),
          includePoints: includePoints
        });
      }
    );
  },
  
  initDeptData: function (text) {
    var that = this;
    var includePoints = [];
    util.request(cfg.selectDeptListMap, { longitude: that.data.longitude, latitude: that.data.latitude,q:text },
      function (data) {
        var markers = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          o.iconPath = "../../image/location_96px_1175814_easyicon.net.png";
          o.width = 45;
          o.title = o.name;
          o.height = 45;
          o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5, display: 'ALWAYS' };
          //o.label = { content: o.name };
          markers.push(o);
          includePoints.push({
            longitude: o.longitude,
            latitude: o.latitude
          });
          console.log({
            longitude: o.longitude,
            latitude: o.latitude
          });
        }
        if (!that.data.includePointsStatus) {
          includePoints.length = 0;
        }
        that.setData({
          markers: markers,
          includePoints: includePoints
        });
        if (that.data.showSearchRsPanel) {
          that.showBar();
        }
      }
    );
  },
  initPersonageData: function (text) {
    var that = this;
    var includePoints = [];
    util.request(cfg.selectPersonAgetListMap, { longitude: that.data.longitude, latitude: that.data.latitude,q:text},
      function (data) {
        var markers = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          o.iconPath = "../../image/person.png";
          o.width = 45;
          o.title = o.name;
          o.height = 45;
          o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5, display: 'ALWAYS' };
          //o.label = { content: o.name };
          markers.push(o);
          includePoints.push({
            longitude: o.longitude,
            latitude: o.latitude
          });
          console.log(o);
        }
        if (!that.data.includePointsStatus) {
          includePoints.length = 0;
        }
        that.setData({
          markers: markers,
          includePoints: includePoints
        });
        if (that.data.showSearchRsPanel) {
          that.showBar();
        }
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
        height: '72vh'
      }
    })
  },
  hideModal: function () {
    // 隐藏遮罩层
    var that = this;
    this.setData({
      showModalStatus: false,
      showOrgBarStatus: true,
      view: {
        height: '93vh'
      }
    });
    //that.showBar();
  },
  navigator: function () {
    var that = this;
    console.log('../' + that.data.o.types + '/index?id=' + that.data.o.id);
    wx.navigateTo({
      url: '../' + that.data.o.types + '/index?id=' + that.data.o.id
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
  hideBar: function () {
    // 隐藏遮罩层
    this.setData({
      showBarStatus: false,
      showFloatBoxStatus: true,
      showOrgBarStatus: true,
      showMapStatus: true,
      view: {
        height: '93vh'
      }
    });
    this.setData({
      showSearchRsPanel: false
    });
  },
  bindregionchange: function (e) {
    console.log(e);
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
    console.log("showInput");
  },
  hideInput: function () {
    console.log("hideInput");
    this.setData({
      inputVal: "",
      inputShowed: false
    });
    this.hideBar();
  },
  clearInput: function () {
    console.log("clearInput");
    this.setData({
      inputVal: ""
    });
    
  },
  inputTyping: function (e) {
    var that = this;
    console.log(e.detail.value);
    console.log(that.data.activeTarget);
    if (e.detail.value.length<2){
      return;
    }
    that.setData({
      showSearchRsPanel:true
    });
    if (that.data.activeTarget=='org'){
      that.searchService(e.detail.value);
    }
    if (that.data.activeTarget == 'dept') {
      that.initDeptData(e.detail.value);
    }
    if (that.data.activeTarget == 'personage') {
      that.initPersonageData(e.detail.value);
    }
    
  },
  bindtapType: function (e) {
    var that = this;
    var o = e.currentTarget.dataset;
    if (o.name.length < 2) {
      return;
    }
    that.setData({
      showSearchRsPanel: true
    });
    that.searchService(o.name);
  },
  searchService:function(q){
    var that = this;
    BMap.search({
      query: q,
      success: function (res) {
        var data = res.wxMarkerData;
        console.log(data);
        var markers = [];
        var includePoints = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          o.name = data[i].title;
          o.types = "www";
          o.addr = data[i].address;
          o.tel = data[i].telephone;

         // o.iconPath = "../../image/location_96px_1175814_easyicon.net.png";
          //o.width = 30;
          o.title = o.name;
         // o.height = 30;
          //o.callout = { content: o.name, color: "#FFFFFF", fontSize: 14, borderRadius: 5, bgColor: "#d81e06", padding: 5, display: 'ALWAYS' };
          markers.push(o);
          includePoints.push({
            longitude: o.longitude,
            latitude: o.latitude
          });
          console.log(o);
        }
        if (markers.length>0){
          that.setData({
            markers: markers,
            includePoints: includePoints
          });
        }
        if (that.data.showSearchRsPanel){
          that.showBar();
        }
       
        
      }
    })
  }
})


