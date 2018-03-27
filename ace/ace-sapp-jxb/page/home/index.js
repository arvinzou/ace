var util = require("../../util/util.js");
var cfg = require("../../config.js");
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
Page({
    data: {
        serverfile: cfg.serverfile,
        autopush:false,
        enablecamera:true,
        muted: false,
        pusherStatus:'stop',
        playimg:"../../image/play_on.png",
        cameraimg:"../../image/camera_on.png",
        mutedimg:"../../image/muted_on.png",
        rtmpurl: cfg.rtmpserver+"13922861673?id=249134995"
    },
    onReady: function (res) {
        console.log('index.js.onReady');
        var that=this;
        wx.setNavigationBarColor({
            frontColor: cfg.frontColor,
            backgroundColor: cfg.backgroundColor,
            animation: {
                duration: 400,
                timingFunc: 'easeIn'
            }
        });
        this.data.videoContext = wx.createLivePusherContext("video-livePusher");
        util.login();
       
    },
    statechange(e) {
      console.log('live-pusher code:', e.detail.code)
      wx.setNavigationBarTitle({
        title:  dict[e.detail.code]
      })
    },
    netstatus(e){
     this.setData(
       {
         info: e.detail.info
       }
     );
    },
    onLoad: function () {
        console.log('index.js.onLoad');
       
    },
    error(e) {
      console.error('live-player error:', e.detail.errMsg)
    },
    play(e) {
      var that=this;
      if (!util.security('admin')){
        return;
      }
      var pusherStatus = that.data.pusherStatus;
      var userinfo = wx.getStorageSync('userinfo');
      that.setData({
        rtmpurl: cfg.rtmpserver + userinfo.mobile + "?id=" + util.uuid() + "&appid=" + cfg.appid
      });
      if (pusherStatus=='stop'){
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
                  pusherStatus:'start'
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
                pusherStatus: 'stop'
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
    }
});
