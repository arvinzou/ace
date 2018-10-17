var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
var dict = {};
dict['1001'] = '已经连接推流服务器';
dict['1002'] = '开始推流';
dict['1003'] = '打开摄像头成功';
dict['1004'] = '录屏启动成功';
dict['1005'] = '推流动态调整分辨率';
dict['1006'] = '推流动态调整码率';
dict['1007'] = '首帧画面采集完成';
dict['1008'] = '编码器启动';
dict['-1301'] = '打开摄像头失败';
dict['-1302'] = '打开麦克风失败';
dict['-1303'] = '视频编码失败';
dict['-1304'] = '音频编码失败';
dict['-1305'] = '不支持的视频分辨率';
dict['-1306'] = '不支持的音频采样率';
dict['-1307'] = '网络断连';
dict['-1308'] = '开始录屏失败，可能是被用户拒绝';
dict['-1309'] = '录屏失败需要5.0以上的系统';
dict['-1310'] = '录屏被其他应用打断了';
dict['-1311'] = 'Android录不到音频数据';
dict['-1312'] = '录屏动态切横竖屏失败';
dict['1101'] = '网络状况不佳：上行带宽太小，上传数据受阻';
dict['1102'] = '网络断连, 已启动自动重连';
dict['1103'] = '硬编码启动失败,采用软编码';
dict['1104'] = '视频编码失败';
dict['1105'] = '新美颜软编码启动失败';
dict['1106'] = '新美颜软编码启动失败';
dict['3001'] = 'RTMP -DNS解析失败';
dict['3002'] = 'RTMP服务器连接失败';
dict['3003'] = 'RTMP服务器握手失败';
dict['3004'] = 'RTMP服务器主动断开';
dict['3005'] = 'RTMP 读/写失败';
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
    hiddenmodalcmt:true,
    display: 'show',
    currentTab: 0,
    navbar: ['互动聊天', '图文直播'],
    id: null,
    list: [],
    pusherSizeH: 35,
    pusherSizeW: 100
  },
  onReady: function (res) {
    var that = this;
    this.data.videoContext = wx.createLivePusherContext("video-livePusher");
  },
  statechange(e) {
    console.log('live-pusher code:', e.detail.code)
    wx.setNavigationBarTitle({
      title: dict[e.detail.code]
    })
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
    var message=[];
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
      message = that.data.message;
      message.push(data);
    }
    

    that.setData({ message: message });
    that.setData({ toView: 1000 });
    wx.pageScrollTo({
      scrollTop: 1000
    });
  },
  onLoad: function (params) {
    var that = this;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../live/index?id=" + params.id });
    }
    that.setData({
      userinfo: wx.getStorageSync('userinfo')
    });
    that.setData({
      id: params.id
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
  initData(id) {
    var that = this;
    that.loadMsg();
    that.loadRpt();

  },
  //点击按钮痰喘指定的hiddenmodalput弹出框  
  modalinput: function () {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
  },
  //取消按钮  
  cancel: function () {
    this.setData({
      hiddenmodalput: true
    });
  },
  //确认  
  confirm: function (e) {
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
    that.setData({
      hiddenmodalput: true
    })
  },
  contentInput: function (e) {
    this.setData({
      contentText: e.detail.value
    })
  },
  rpt: function () {
    var that = this;
    console.log('../rpt/index?id=' + that.data.id);
    wx.navigateTo({
      url: '../rpt/index?id=' + that.data.id,
    })

  },
  loadMsg:function(){
    var that=this;
    that.data.message=[];
    util.request(cfg.getLiveMsgList, { rid: that.data.id },
      function (data) {
        for (var i = 0; i < data.length; i++) {
          var o = JSON.parse(data[i].content);
          console.log(o);
          that.renderChartBox(o);
        }
      }
    );
  },
  loadRpt: function () {
    var that = this;
    that.data.list=[];
    that.setData({ list: [] });
    util.request(cfg.getLiveRptList, { rid: that.data.id, page: 1 },
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
    if (res.scrollTop >= 30) {
      if (true) {
        that.setData({ pusherSizeH: 0, pusherSizeW: 0 });
      }
    } else {
      if (true) {
        that.setData({ pusherSizeH: 35, pusherSizeW: 100 });
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

  },
  addlike:function(e){
    console.log(e.currentTarget.dataset.id);
    util.request(cfg.addLike, { type: "2", id: e.currentTarget.dataset.id},
      function (data) {

      }
    );
  },
  addcmt: function (e) {
    console.log(e.currentTarget.dataset.id);
    var that=this;
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
    util.request(cfg.insertLiveCmt, { jsons: JSON.stringify(data)},
      function (data) {
        that.setData({ hiddenmodalcmt:true });
        that.setData({ contentCmtText: '' });
      }
    );
    
  },
  initData(id) {
    var that = this;
    that.loadMsg();
    that.loadRpt();

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
});