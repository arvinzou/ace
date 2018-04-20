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
    max: 0,
    maxRemark: 0,
    loading: false,
    disabled: false,
    files: [],
    checkImageUrl: cfg.checkImageUrl,
    id: null,
    categoryItems: [
      { name: '视频直播', value: '2', checked: true},
      { name: '图文直播', value: '1' }
    ],
    date: util.formatDate(new Date()),
    time: util.formatTime(new Date(),"h m:s"),
    typeCodes: ["01", "02", "03", "04"],
    typeCodeIndex: 0,
    types: ["亲子关系", "婚姻家庭", "职场压力",'其他']
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
    data.rpt.uid = 'oFvIjw7bU8IN-GYgxYCwwf_fOKZY';
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
    util.request(cfg.insertLiveRptSapp, { jsons: JSON.stringify(data) },
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
            formData: { id: that.data.id, collectionName: "jxb", companyId: cfg.companyId },
            success: function (resp) {
              console.log(resp);
              wx.hideLoading();
              var obj = JSON.parse(resp.data);
              console.log(obj);
              fileList.push(cfg.serverfile + obj.file_path);
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
    var files = that.data.files;
    if (files.length > 0) {
      wx.showModal({ title: "提示", showCancel:false,content: "封面限制为单个图片" });
      return ;
    }
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
  categoryChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    var categoryItems = this.data.categoryItems;
    for (var i = 0, len = categoryItems.length; i < len; ++i) {
      categoryItems[i].checked = categoryItems[i].value == e.detail.value;
    }
    this.setData({
      categoryItems: categoryItems
    });
  },
  remarkChange: function (e) {
    console.log(e);
    if (e.detail && e.detail.value.length > 0) {
      this.setData({
        maxRemark: e.detail.value.length
      });
    }
  },
  bindDateChange: function (e) {
    this.setData({
      date: e.detail.value
    })
  },
  bindTimeChange: function (e) {
    this.setData({
      time: e.detail.value
    })
  },
  bindTypeChange: function (e) {
    console.log('picker type code 发生选择改变，携带值为', e.detail.value);
    this.setData({
      typeCodeIndex: e.detail.value
    })
  },
});