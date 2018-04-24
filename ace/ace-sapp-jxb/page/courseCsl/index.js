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
    checkImageUrl: cfg.checkImageUrl,
    userinfo:wx.getStorageSync('userinfo'),
    WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
    displayVideo: 'hide',
    id: null,
    mediTypeItems: [
      { name: '音频', value: '1', checked: true},
      { name: '视频', value: '2' }
    ],
    costTypeItems: [
      { name: '系列课程', value: '1', checked: true },
      { name: '免费课程', value: '2' }
    ],
    categoryCodes: ["01", "02", "03", "04"],
    categorys: ["亲子关系", "婚姻家庭", "职场压力",'其他'],
    currentTab: 0,
    navbar: ['地址', '上传']
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
  initData:function(res){
    console.log('initData');
    var that = this;
    if (that.data.param.act == 'add') {
      that.initAdd();
    } else {
      that.initEdit();
    }
  },
  initAdd: function (param) {
    var that = this;
    var id = util.uuid();
    that.setData({
      formData: {
        id: id,
        files: [],
        categoryCodeIndex:0,
        mediUrl: cfg.serverfile + that.data.userinfo.mobile + "?id=" + id
      }
    });
  },
  initEdit: function (param) {
    var that = this;
    util.request(cfg.getCoursById, { id: that.data.param.id },
      function (data) {
        console.log(data.data);
        var formData = data.data;
        formData.files = [formData.cover];
        formData.categoryCodeIndex = util.indexOf(that.data.categoryCodes, formData.category);
        that.setData({
          mediTypeItems: util.initRadioGroupData(that.data.mediTypeItems, formData.mediType),
          costTypeItems: util.initRadioGroupData(that.data.costTypeItems, formData.costType),
          formData
        });
      }
    );
  },
  onLoad: function (param) {
    var that = this;
    console.log('index.js.onLoad');
    console.log(param);
    this.setData({ param: param });
    if (wx.getStorageSync('userinfo')){
      console.log('logined');
      that.initData(param);
    }else{
      console.log('logining');
      util.login(function (res) {
        that.initData(param);
      });
    }
  },
  formSubmit: function (e) {
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    var now = new Date();
    now.setDate(now.getDate() + 10);
    var data = util.extend(that.data.formData, e.detail.value);
    data.cover = that.data.formData.files[0];
    var url = '';
    if (that.data.param.act == 'add') {
      url = cfg.insertCourseSapp;
      data.duration = 0;
      data.demandNum = 0;
      data.likeNum = 0;
    } else {
      url = cfg.updateCourseSapp;
    }
  
    console.log(data);
    util.request(url, { jsons: JSON.stringify(data) },
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          showCancel: false,
          success: function (res) {
            if (res.confirm&&data.status==0) {
              that.initData(that.data.param);
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
              that.data.formData.files.push(cfg.serverfile + obj.file_path);
              that.setData({
                formData: that.data.formData
              });
            },
            fail: function (res) {
              wx.hideLoading();
              wx.showModal({ title: "提示", content: "上传失败", showCancel: false })
            }
          })
        }


      }
    })
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.fromData.files // 需要预览的图片http链接列表
    })
  },
  chooseImage: function () {
    let that = this;
    var files = that.data.formData.files;
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
    var files = that.data.formData.files;
    if (files.length > 0) {
      files.remove(idx);
    }
    that.setData({ formData: that.data.formData });
  },
  formReset: function (e) {

  },
  mediTypeChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    var mediTypeItems = this.data.mediTypeItems;
    for (var i = 0, len = mediTypeItems.length; i < len; ++i) {
      mediTypeItems[i].checked = mediTypeItems[i].value == e.detail.value;
    }
    this.setData({
      mediTypeItems: mediTypeItems
    });
  },
  costTypeChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    var costTypeItems = this.data.costTypeItems;
    for (var i = 0, len = costTypeItems.length; i < len; ++i) {
      costTypeItems[i].checked = costTypeItems[i].value == e.detail.value;
    }
    this.setData({
      costTypeItems: costTypeItems
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
  bindCategroyChange: function (e) {
    console.log('picker type code 发生选择改变，携带值为', e.detail.value);
    var that=this;
    that.data.formData.categoryCodeIndex= e.detail.value;
    this.setData({
      formData: that.data.formData
    })
  },
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
  delVideo: function () {
    console.log("delVideo");
    let that = this;
    that.setData({ displayVideo: 'hide', mediUrl: null });
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
            var formData = that.data.formData;
            formData.mediUrl = cfg.serverfile + obj.file_path;
            that.setData({
              formData: formData,
              displayVideo: 'show'
            });
          },
          fail: function (res) {
            wx.hideLoading();
            wx.showModal({ title: "提示", content: "上传失败", showCancel: false})
          }
        })
      }
    });
  }
});

