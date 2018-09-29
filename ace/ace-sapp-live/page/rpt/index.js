var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
var fileList = [];
Array.prototype.indexOf = function (val) {
  for (var i = 0; i < this.length; i++) {
    if (this[i] == val) return i;
  }
  return -1;
};

Array.prototype.remove = function (val) {
  var index = this.indexOf(val);
  if (index > -1) {
    this.splice(index, 1);
  }
};
Page({
  data: {
    serverfile: cfg.serverfile,
    currentTab: 0,
    navbar: ['图片上传', '视频上传', '音频录制'],
    max: 0,
    loading: false,
    disabled: false,
    files: [],
    checkImageUrl: cfg.checkImageUrl,
    id: null,
    mediUrl: null,
    docText: '',
    displayVideo: 'hide',
    displayAudio: 'hide',
    playimg: "../../image/record_on.png",
    recorderStatus: false
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
  },

  onLoad: function (param) {
    var that = this;
    console.log('index.js.onLoad');
    console.log(param);
    this.setData(param);
    this.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      checkImageUrl: cfg.checkImageUrl,
      userinfo: wx.getStorageSync('userinfo'),
      fileList: []
    });
  },
  formSubmit: function (e) {
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    var data = {};
    data.imgs = [];
    data.rpt = {};
    data.captcha = e.detail.value.captcha;
    data.rpt.rid = that.data.id;
    data.rpt.uid = that.data.userinfo.unionId;
    data.rpt.mediaContent = that.data.mediUrl;
    data.rpt.content = e.detail.value.docText;

    var files = that.data.files;
    for (var i = 0; i < files.length; i++) {
      data.imgs.push({ url: files[i], w: 0, h: 0 });
    }
    if (that.data.currentTab == 0) {
      data.rpt.mediaType = '2';
    }
    if (that.data.currentTab == 1) {
      data.rpt.mediaType = '1';
    }
    if (that.data.currentTab == 2) {
      data.rpt.mediaType = '3';
    }
    console.log(data);
    util.request(cfg.insertLiveRpt, { jsons: JSON.stringify(data) },
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          success: function (res) {
            if (res.confirm) {
              fileList = [];
              that.setData({
                files: fileList,
                checkImageUrl: cfg.checkImageUrl + "?id=" + util.uuid(),
                docText: ''
              });
            }
          }
        })
      }
    );
  },
  valueChange: function (e) {
    console.log(e);
    if (e.detail && e.detail.value.length > 0) {
      this.setData({
        max: e.detail.value.length
      });
    }
  },
  chooseWxImage: function (type) {
    var that = this;
    wx.chooseImage({
      sizeType: ['original', 'compressed'],
      sourceType: [type],
      success: function (res) {

        for (var i = 0; i < res.tempFilePaths.length; i++) {
          wx.showLoading({ title: "正在上传" });
          console.log(res.tempFilePaths[i]);
          wx.uploadFile({
            url: cfg.uploadUrl,
            filePath: res.tempFilePaths[i],
            name: 'file',
            header: {
              'content-type': 'multipart/form-data'
            },
            formData: { id: that.data.id, collectionName: "live", companyId: cfg.companyId },
            success: function (resp) {
              console.log(resp);
              wx.hideLoading();
              var obj = JSON.parse(resp.data);
              console.log(obj);
              fileList.push(obj.file_path);
              that.setData({
                files: fileList
              });
            },
            fail: function (res) {
              wx.hideLoading();
              wx.showModal({ title: "提示", content: "上传失败" })
            }
          })
        }


      }
    })
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  chooseImage: function () {
    let that = this;
    wx.showActionSheet({
      itemList: ['从相册中选择', '拍照'],
      success: function (res) {
        if (!res.cancel) {
          if (res.tapIndex == 0) {
            that.chooseWxImage('album')
          } else if (res.tapIndex == 1) {
            that.chooseWxImage('camera')
          }
        }
      }
    })
  },
  delImage: function (e) {
    var idx = e.target.dataset.index;
    console.log(idx);
    let that = this;
    var files = that.data.files;
    if (files.length > 0) {
      files.remove(idx);
      fileList.remove(idx);
    }
    that.setData({ files: files });
  },
  formReset: function (e) {
    console.log("clear");
    fileList = [];
    this.setData({
      files: fileList
    });
  },
  //滑动切换
  swiperTab: function (e) {
    var that = this;
    that.setData({
      currentTab: e.detail.current
    });
    if (this.data.currentTab == 0 || this.data.currentTab == 2) {
      that.delVideo();
    }
  },
  //点击切换
  clickTab: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
      if (this.data.currentTab == 0 || this.data.currentTab == 2) {
        that.delVideo();
      }
    }
  },
  chooseVideo: function () {
    let that = this;
    wx.chooseVideo({
      sourceType: ['album', 'camera'],
      success: function (res) {
        wx.showLoading({ title: "正在上传" });
        var file = res.tempFilePath;
        wx.uploadFile({
          url: cfg.uploadUrl,
          filePath: file,
          name: 'file',
          header: {
            'content-type': 'multipart/form-data'
          },
          formData: { id: that.data.id, collectionName: "jxb", companyId: cfg.companyId },
          success: function (resp) {
            console.log(resp);
            wx.hideLoading();
            var obj = JSON.parse(resp.data);
            console.log(obj);
            that.setData({
              mediUrl: cfg.serverfile + obj.file_path,
              displayVideo: 'show'
            });
          },
          fail: function (res) {
            wx.hideLoading();
            wx.showModal({ title: "提示", content: "上传失败" })
          }
        })
      }
    });
  },
  startRecorder: function () {
    let that = this;
    var recorderManager = wx.getRecorderManager();
    const options = {
      duration: 600000,
      sampleRate: 44100,
      numberOfChannels: 2,
      encodeBitRate: 192000,
      format: 'mp3',
      frameSize: 50
    }
    if (that.data.recorderStatus) {
      recorderManager.stop();
      that.setData({
        playimg: "../../image/record_on.png",
        recorderStatus: false

      });
    } else {
      recorderManager.start(options);
      that.setData({
        playimg: "../../image/record_off.png",
        recorderStatus: true,
        displayAudio: 'none'
      });
    }
    recorderManager.onStop((res) => {
      console.log('recorder stop', res);
      wx.showLoading({ title: "正在上传" });
      var file = res.tempFilePath;
      wx.uploadFile({
        url: cfg.uploadUrl,
        filePath: file,
        name: 'file',
        header: {
          'content-type': 'multipart/form-data'
        },
        formData: { id: that.data.id, collectionName: "jxb", companyId: cfg.companyId },
        success: function (resp) {
          console.log(resp);
          wx.hideLoading();
          var obj = JSON.parse(resp.data);
          console.log(obj);
          that.setData({
            mediUrl: cfg.serverfile + obj.file_path,
            displayAudio: ' '
          });
        },
        fail: function (res) {
          wx.hideLoading();
          wx.showModal({ title: "提示", content: "上传失败" })
        }
      })
    })
  },
  stopRecorder: function () {
    let that = this;
    var recorderManager = wx.getRecorderManager();
    const options = {
      duration: 600000,
      sampleRate: 44100,
      numberOfChannels: 2,
      encodeBitRate: 192000,
      format: 'mp3',
      frameSize: 50
    }

    recorderManager.start(options);
  },
  delVideo: function () {
    console.log("delVideo");
    let that = this;
    that.setData({ displayVideo: 'hide', mediUrl: null });
  },
  delAideo: function () {
    let that = this;
    that.setData({ displayAudio: 'hide', mediUrl: null });
  },
  /**
   * 点击选项卡
   */
  navbarTap: function (e) {
    console.log(e);
    let that = this;
    if (that.data.currentTab == e.target.dataset.idx) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.idx
      })
    }
  }
});