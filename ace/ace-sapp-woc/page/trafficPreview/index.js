var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();

Page({
  data: {
    serverfile: cfg.serverfile,
    loading: false,
    disabled: false,
    id:null,
    o: { "axleCount": 4, "createDate": "2018-04-12 17:23:13", "createUserId": "1520576862359", "createUserName": "智慧治超", "direction": "由南至北", "id": "307c938dc2f649d497c0280beccda396", "inspectTime": "2018-04-12 17:23:12", "lastModifyDate": "2018-04-16 14:33:41", "lastModifyUserId": "1520576862359", "lastModifyUserName": "智慧治超", "locale": "白云大道水泗村站点", "overMass": 1700.00, "overRate": 0.10, "plateNo": "渝X8C75Q", "remark": "", "siteId": "5e7ce0e0-96c9-42a5-b087-b21601d26c62", "siteName": "白云大道水泗村站点", "status": "0", "totalMass": 17700.00,
      "trafficSubVo": [{ "category": "3", "createDate": "2018-04-17 09:35:00", "createUserId": "88888888", "createUserName": "system", "fileUrl": "group1/M00/00/25/i-AA41rUEPmAemkrAABjWHjM5XM636.jpg?filename=3.jpg", "id": "11bdffdf007e455fa164f21c9bc3b839", "inspectTime": "2018-04-17 09:35:00", "lastModifyDate": "2018-04-17 09:35:00", "plateNo": "赣V4Z2DY", "status": "1", "trafficId": "f776dcc05ccb4d74a98db78afe519fb8" }, { "category": "4", "createDate": "2018-04-17 09:35:00", "createUserId": "88888888", "createUserName": "system", "fileUrl": "group1/M00/00/25/i-AA41rUEQaAbkTSAACHFey8R6g949.jpg?filename=4.jpg", "id": "5d3daa51e1b84638a2bcf27652b0ddcb", "inspectTime": "2018-04-17 09:35:00", "lastModifyDate": "2018-04-17 09:35:00", "plateNo": "赣V4Z2DY", "status": "1", "trafficId": "f776dcc05ccb4d74a98db78afe519fb8" }, { "category": "1", "createDate": "2018-04-17 09:35:00", "createUserId": "88888888", "createUserName": "system", "fileUrl": "group1/M00/00/25/i-AA41rUENaAdLkUAAECSU7ktj4896.jpg?filename=1.jpg", "id": "b12497c5a494453ca76456723f121097", "inspectTime": "2018-04-17 09:35:00", "lastModifyDate": "2018-04-17 09:35:00", "plateNo": "赣V4Z2DY", "status": "1", "trafficId": "f776dcc05ccb4d74a98db78afe519fb8" }, { "category": "2", "createDate": "2018-04-17 09:35:00", "createUserId": "88888888", "createUserName": "system", "fileUrl": "group1/M00/00/25/i-AA41rUEO2AVt7tAABLCiTGGkQ380.jpg?filename=2.jpg", "id": "c11cc45760d443f49b5f433a647f53c8", "inspectTime": "2018-04-17 09:35:00", "lastModifyDate": "2018-04-17 09:35:00", "plateNo": "赣V4Z2DY", "status": "1", "trafficId": "f776dcc05ccb4d74a98db78afe519fb8" }]
  }

  },
  onReady: function (res) {
    console.log('index.js.onReady');
    var that = this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
    var that = this;
    util.request(cfg.illegalTrafficOneUrl, { "trafficId": that.data.id, "start": 0, "limit": 9999 },
        function (data) {
            console.log(data.value);
            that.setData({ o: data.value });
        }
    );
  },
  onLoad: function (param) {
    var that = this;
    console.log('index.js.onLoad'); 
    console.log(param); 
    that.setData(param);

  },
  previewImage: function (e) {
    console.log(e);
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: [e.currentTarget.id] // 需要预览的图片http链接列表
    })
  }
});

