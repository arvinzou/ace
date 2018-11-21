var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
var fileList = [];
Page({
  data: {
    serverfile: cfg.serverfile,
    max: 0,
    loading: false,
    disabled: true,
    files: [],
    attachments: [],
    checkImageUrl: cfg.checkImageUrl + "?id="+wx.getStorageSync('WX-SESSION-ID'),
    id: null,
    mediUrl: null
  },
  onReady: function (res) {

  },

  onLoad: function (param) {
    var that = this;
    console.log('index.js.onLoad');
    console.log(param);
    if(!util.isLogin()){
      wx.navigateTo({ url: "../bind/index?url=../answer/index?id=" + that.data.id });
    }
    that.setData(param);
    that.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      checkImageUrl: cfg.checkImageUrl,
      fileList: [],
      userProp: wx.getStorageSync('userProp')
    });
    that.initData(that.data.id);

  },
  formSubmit: function (e) {
    //console.log(e.detail.value);
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    that.setData({
      loading: false,
      disabled: false
    })
    var data = {};
    data.list = [];
    data.o = e.detail.value;
    data.captcha = e.detail.value.captcha;
    data.o.id = that.data.id;
    var files = that.data.files;
    for (var i = 0; i < files.length; i++) {
      data.list.push({ name:'附件'+(i+1), mediUrl: files[i], mediType:'img' });
    }
    data.o.mediType = '1';
    console.log(data);
    that.updateAppealCase(data);
  },
  valueChange: function (e) {
    console.log(e);
    if (e.detail && e.detail.value.length > 0) {
      this.setData({
        max: e.detail.value.length,
        disabled: false
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
            formData: { id: that.data.id, collectionName: "portal" },
            success: function (resp) {
              console.log(resp);
              wx.hideLoading();
              var obj = JSON.parse(resp.data);
              console.log(obj);
              fileList.push( obj.file_path);
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
  },
  chooseAttachments: function () {
    let that = this;
   
  },
  updateAppealCase: function (data){
    var that=this;
    util.request(cfg.updateAppealCase, { jsons: JSON.stringify(data),client:"miniApp" },
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: data.errorMessage,
          success: function (res) {
            that.setData({
              checkImageUrl: cfg.checkImageUrl + "?id=" + wx.getStorageSync('WX-SESSION-ID') + "&uuid=" + util.uuid(),
            });
            if (res.confirm && data.status==0) {
              fileList = [];
              that.setData({
                files: fileList,
                content: ''
              });
            }
          }
        })
      }
    );
  },
  bindCategroyChange: function (e) {
    console.log('picker bindCategroyChange 发生选择改变，携带值为', e.detail.value);

    this.setData({
      categoryIndex: e.detail.value
    })
  },
  initData: function (id) {
    var that = this;
    util.request(cfg.selectAppealCaseByPrimaryKey, { id: id },
      function (data) {
        that.setData({
          appeal: data.value
        });
        console.log(data.value);
        if (data.status != 0) {
          wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        } else {
          wx.setNavigationBarTitle({ title: that.data.appeal.title });
        }
        wx.stopPullDownRefresh();
      }
    );
  }
});