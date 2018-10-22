var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();

var wxuser = {
  headimgurl: "",
  nickname: "",
  openid: ""
};

Page({
  data: {
    serverfile: cfg.serverfile,
    autopush: false,
    enablecamera: true,
    autofocus: true,
    muted: false,
    pusherStatus: 'stop',
    playimg: "../../image/play_on.png",
    cameraimg: "../../image/camera_on.png",
    mutedimg: "../../image/muted_on.png",
    rtmpurl: cfg.rtmpserver + "13922861673?id=249134995",
    orientation: "horizontal",
    orientationimg: "../../image/screen_horizontal.png",
    aspectimg: "../../image/aspect_916.png",
    aspect: "9:16",
    message: [],
    toView: 0,
    contentText: '',
    hiddenmodalput: true,
    hiddenmodalcmt: true,
    display: 'show',
    currentTab: 0,
    navbar: ['简介', '直播','互动'],
    id: null,
    list: [],
    pusherSizeH: 35,
    paddingtop: 0,
    nameDisplay:false,
    scoll:'live-top-box-noscoll',
    sort:'0'
  },
  onReady: function (res) {
    var that = this;
   // this.data.videoContext = wx.createLivePlayerContext("video-livePusher");
  },
  statechange(e) {
    console.log('live-pusher code:', e.detail.code)
  },

  netstatus(e) {
    this.setData(
      {
        info: e.detail.info
      }
    );
  },
  renderChartBox: function (data) {
    console.log("===============接收信息===================");
    console.log(data);
    var that = this;
    var message = [];
    if (data.header.cmd == 'reload.rpt') {
      that.loadRpt();
      return;
    }
    if (data.header.cmd == 'reload.msg') {
      message = [];
      that.loadMsg();
      return;
    }
    if (data.header.cmd == 'content') {
      data.id = "c" +util.uuid();
      message = that.data.message;
      message.push(data);
      console.log(data);
      that.setData({ message: message, scrollintoview: data.id });
    }
    
  },
  onLoad: function (params) {
    var that = this;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../preview/index?id=" + params.id });
    }
    that.setData({
      userinfo: wx.getStorageSync('userinfo')
    });
    that.setData({
      id:params.id
    });
    that.initData(that.data.id);
    var url = "wss://" + cfg.websocketurl + "/live/www/websocket/" + that.data.id + "/" + that.data.userinfo.unionId + "/chat";
    console.log(url);
    openSocket.connect(function (data) { // WebSocket初始化连接
      if (data) {
        that.renderChartBox(data);
      }
    }, url);
    setInterval(() => {
      console.log("心跳检查websocket");
      if (app.globalData.socketConnectFail) { // WebSocket断线重连
        console.log("websocket -> " + app.globalData.socketConnectFail);
        console.log("重新连接websocket->" + url);
        openSocket.connect(function (data) {
          if (data) {
            that.renderChartBox(data);
          }
        }, url);
      }
    }, 5000)

  },
  error(e) {
    console.error('live-player error:', e.detail.errMsg)
  },
  play(e) {
    var that = this;
    var pusherStatus = that.data.pusherStatus;
    var userinfo = wx.getStorageSync('userinfo');
    that.setData({
      rtmpurl: cfg.rtmpserver + userinfo.mobile + "?id=" + util.uuid() + "&appid=" + cfg.appid
    });
    if (pusherStatus == 'stop') {
      wx.showModal({
        title: '系统提示',
        content: "确定开始录制吗？",
        success: function (res) {
          if (res.confirm) {
            wx.setNavigationBarTitle({
              title: '录制中'
            });
            that.setData({
              playimg: "../../image/play_off.png",
              pusherStatus: 'start',
              display: 'hide'
            });
            that.data.videoContext.start();

          } else if (res.cancel) {
          }
        }
      })
    }
    if (pusherStatus == 'start') {
      wx.showModal({
        title: '系统提示',
        content: "确定停止录制吗？",
        success: function (res) {
          if (res.confirm) {
            wx.setNavigationBarTitle({
              title: '暂停'
            });
            that.setData({
              playimg: "../../image/play_on.png",
              pusherStatus: 'stop',
              display: 'show'
            });
            that.data.videoContext.stop();
          } else if (res.cancel) {
          }
        }
      })
    }
  },
  camera(e) {
    var that = this;
    var enablecamera = that.data.enablecamera;
    if (enablecamera) {
      that.setData({
        enablecamera: !enablecamera,
        cameraimg: "../../image/camera_off.png"
      });
    } else {
      that.setData({
        enablecamera: !enablecamera,
        cameraimg: "../../image/camera_on.png",
      });
    }
  },
  muted(e) {
    var that = this;
    var muted = that.data.muted;
    if (!muted) {
      that.setData({
        muted: !muted,
        mutedimg: "../../image/muted_off.png"
      });
    } else {
      that.setData({
        muted: !muted,
        mutedimg: "../../image/muted_on.png",
      });
    }
  },
  orientation(e) {
    var that = this;
    var orientation = that.data.orientation;
    if (orientation == 'horizontal') {
      that.setData({
        orientation: "vertical",
        orientationimg: "../../image/screen_vertical.png"

      });
    } else {
      that.setData({
        orientation: "horizontal",
        orientationimg: "../../image/screen_horizontal.png"
      });
    }
  },
  aspect(e) {
    var that = this;
    var aspect = that.data.aspect;
    if (aspect == '9:16') {
      that.setData({
        aspect: "3:4",
        aspectimg: "../../image/aspect_34.png"
      });
    } else {
      that.setData({
        aspect: "9:16",
        aspectimg: "../../image/aspect_916.png"
      });
    }
  }
  ,
  formSubmit: function () {
    var that = this;
    var message = {};
    message.header = {
      cmd: 'content',
      wxuser: {
        headimgurl: that.data.userinfo.avatarUrl,
        nickname: that.data.userinfo.nickName,
        openid: that.data.userinfo.openId,
        unionid: that.data.userinfo.unionId
      }
    };

    message.createTime = util.formatTime(new Date(), 'h:m:s');
    message.content = that.data.contentText;
    wx.sendSocketMessage({
      data: JSON.stringify(message),
    });
    console.log(message);
    that.setData({ contentText: '' });
  },
  submitChat: function (e) {
    console.log('form发生了submit事件，携带数据为：', e);
    var that = this;
    if (!that.data.contentText) {
      wx.showToast({
        title: '发送内容不为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    that.formSubmit();
  },
  contentInput: function (e) {
    this.setData({
      contentText: e.detail.value
    })
  },
  //滑动切换
  swiperTab: function (e) {
    var that = this;
    that.setData({
      currentTab: e.detail.current
    });
    that.loadRpt();
  },
  rpt: function () {
    var that = this;
    console.log('../rpt/index?id=' + that.data.id);
    wx.navigateTo({
      url: '../rpt/index?id=' + that.data.id,
    })

  },
  loadMsg: function () {
    var that = this;
    that.data.message = [];

    util.request(cfg.getLiveMsgList, { rid: that.data.id },
      function (data) {
        var scrollintoview="";
        for (var i = 0; i < data.length; i++) {
          var o = JSON.parse(data[i].content);
          o.id = "c"+data[i].id;
          scrollintoview=o.id;
          console.log(o);
          that.renderChartBox(o);
        }
        that.setData({ scrollintoview: scrollintoview });
      }
    );
  },
  loadRpt: function () {
    var that = this;
    that.data.list = [];
    that.setData({ list: [] });
    util.request(cfg.getLiveRptList, { rid: that.data.id, page: 1, sort:that.data.sort },
      function (data) {
        that.setData({ list: data.data });
      }
    );
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: [e.currentTarget.id] // 需要预览的图片http链接列表
    })
  },
  onPageScroll: function (res) {
    let that = this;
    console.log(res);
    if (that.data.currentTab == 0 || that.data.currentTab == 2){
        return ;
    }
    if (res.scrollTop >= 30) {
      if (!that.data.nameDisplay) {
        that.setData({
          pusherSizeH: 17.5,
          nameDisplay: true,
          paddingtop: 17.5,
          scoll: 'live-top-box-scoll'
        });
      }
    } else {
      if (that.data.nameDisplay){
        that.setData({
          pusherSizeH: 35,
          paddingtop: 0,
          nameDisplay: false,
          scoll: 'live-top-box-noscoll'
        });
      }
     
    }
  },
  onReachBottom: function () {
    console.log("onReachBottom");
  },
  onPullDownRefresh: function () {
    console.log("onPullDownRefresh");
  },
  /**
  * 点击选项卡
  */
  navbarTap: function (e) {
    let that = this;
    if (that.data.currentTab === e.target.dataset.idx) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.idx
      })
    }
  },
  onPullDownRefresh: function () {
    let that = this;
    wx.stopPullDownRefresh();
    that.loadRpt();
    that.loadMsg();

  },
  addlike: function (e) {
    console.log(e.currentTarget.dataset.id);
    util.request(cfg.addLike, { type: "2", id: e.currentTarget.dataset.id },
      function (data) {

      }
    );
  },
  addcmt: function (e) {
    console.log(e.currentTarget.dataset.id);
    var that = this;
    that.setData({
      hiddenmodalcmt: false,
      rptId: e.currentTarget.dataset.id
    })
  },
  //确认  
  confirmCmt: function (e) {
    console.log('form发生了submit事件，携带数据为：', e);
    var that = this;
    if (!that.data.contentCmtText) {
      wx.showToast({
        title: '发送内容不为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    that.cmtFormSubmit();
    that.setData({
      hiddenmodalcmt: true
    })
  },
  contentCmtInput: function (e) {
    this.setData({
      contentCmtText: e.detail.value
    })
  },
  cmtFormSubmit: function () {
    var that = this;
    var data = {};
    data.rptId = that.data.rptId;
    data.uid = that.data.userinfo.unionId;
    data.content = that.data.contentCmtText;
    util.request(cfg.insertLiveCmt, { jsons: JSON.stringify(data) },
      function (data) {
        that.setData({ hiddenmodalcmt: true });
        that.setData({ contentCmtText: '' });
      }
    );

  },
  initData(id) {
    var that = this;
    that.loadMsg();
    that.loadRpt();
    that.loadLive(id);
    that.loadTotalNumAndOrgInfo(id);
  },
  //点击按钮痰喘指定的hiddenmodalput弹出框  
  modalinputCmt: function () {
    this.setData({
      hiddenmodalcmt: !this.data.hiddenmodalcmt
    })
  },
  //取消按钮  
  cancelCmt: function () {
    this.setData({
      hiddenmodalcmt: true
    });
  },
  loadLive: function (id) {
    var that = this;
    util.request(cfg.loadLive, { id: id },
      function (data) {
        console.log(data);
        that.setData({ rtmpurl: data.rtmpUrl, live: data,startDate:data.startTime.substring(0,10) });
        wx.setNavigationBarTitle({
          title: data.name
        })
      }
    );
  },
  loadTotalNumAndOrgInfo: function (id) {
    var that = this;
    util.request(cfg.server + "/live/www/live/getTotalNumAndOrgInfo", { id: id, companyId: cfg.companyId},
      function (data) {
        console.log(data);
        that.setData({org:data.data});
      }
    );
  },
  sort:function(){
    var that=this;
    if (that.data.sort=='0'){
      that.setData({ sort: '1' });
    }else{
      that.setData({ sort: '0' });
    }
    that.loadRpt();
  }
});