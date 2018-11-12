var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
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
    WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
    userinfo: wx.getStorageSync('userinfo'),
    checkImageUrl: cfg.checkImageUrl,
    max: 0,
    maxRemark: 0,
    loading: false,
    disabled: false,
    categoryItems: [
      { name: '视频直播', value: '2', checked: true},
      { name: '图文直播', value: '1' }
    ],
    typeCodes: ["01", "02", "03", "04"],
    types: ["亲子关系", "婚姻家庭", "职场压力",'其他'],
    files:[],
    isRegist: false,
    userinfoData: null,
    wxUser: null
  },
  onReady: function (res) {
  },
  initData:function(param){
    console.log('initData');

    var that = this;
    if (param.act=='add'){
        that.initAdd();
    }else{
      that.initEdit();
    }
  },
  initAdd: function (param){
    var that = this;
    var id = util.uuid();
    that.setData({
      formData: {
        id: id,
        files: [],
        date: util.formatDate(new Date()),
        time: util.formatTime(new Date(), "h:m:s"),
        type: 0,
        rtmpUrl: cfg.hlsserver + that.data.userinfo.userProp.mobile+".m3u8"
      },
      date: util.formatDate(new Date()),
      time: util.formatTime(new Date(), "h:m:s")
    });
  },
  initEdit: function (param) {
    var that=this;
    util.request(cfg.selectLiveByPrimaryKey, {id: that.data.param.id},
      function (data) {
        console.log(data.data);
        var formData = data.value;
        formData.date = formData.startTime.substring(0, 10);
        formData.time = formData.startTime.substring(11, 20);
        formData.typeIndex = util.indexOf(that.data.typeCodes, formData.type);
        that.setData({
          categoryItems: util.initRadioGroupData(that.data.categoryItems, formData.category),
          formData,
          files: [formData.imageSrc],
          date : formData.startTime.substring(0, 10),
          time : formData.startTime.substring(11, 20)
        });
      }
    );
  },
  onLoad: function (param) {
    var that = this;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../liveCls/index?id=" + params.id });
    }else{
        if (wx.getStorageSync('userinfo')) {
            that.initUserData();
            that.initWxUserData();
            that.setData({
                userinfo: wx.getStorageSync('userinfo'),
                WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
            });
            console.log(param);
            that.setData({ param: param });
            that.initData(param);
        }
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
    data.imageSrc = that.data.files[0];
    data.startTime = that.data.formData.date + " " + that.data.formData.time
    data.endTime = util.formatTime(now,"Y-M-D h:m:s");
    console.log(data);
    var url = '';
    if (that.data.param.act=='add'){
      url = cfg.insertLive;
      data.nop = 0;
      data.pop = 0;
    }else{
      url = cfg.updateLive;
    }
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
            if (res.tempFiles[i].size <= 2000000){
                wx.showLoading({ title: "正在上传" });
                console.log(res.tempFilePaths[i]);
                wx.uploadFile({
                    url: cfg.uploadUrl,
                    filePath: res.tempFilePaths[i],
                    name: 'file',
                    header: {
                    'content-type': 'multipart/form-data'
                    },
                    formData: {collectionName: "live", companyId: cfg.companyId },
                    success: function (resp) {
                    console.log(resp);
                    wx.hideLoading();
                    var obj = JSON.parse(resp.data);
                    console.log(obj);
                    var files = that.data.files;
                    files.push(obj.file_path);
                    that.setData({
                        files: files
                    });
                    },
                    fail: function (res) {
                    wx.hideLoading();
                    wx.showModal({ title: "提示", content: "上传失败" });
                    }
                });
            }else{
                wx.showModal({ title: "提示", content: "上传图片大小不能超过2M！" });
            }
        }


      }
    })
  },
  previewImage: function (e) {
    console.log(e);
    wx.previewImage({
      current: e.currentTarget.dataset.id, // 当前显示图片的http链接
      urls: [cfg.serverfile + e.currentTarget.dataset.id] // 需要预览的图片http链接列表
    })
  },
  chooseImage: function () {
    let that = this;
    var files = that.data.files;
    if (files&&files.length > 0) {
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
    }
    that.setData({ files: that.data.files});
  },
  formReset: function (e) {
    console.log("clear");
    that.data.files = [];
    this.setData({
      formData: that.data.formData
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
    var that=this;
    that.data.formData.date = e.detail.value;
    that.setData({
      date: that.data.formData.date
    });

  },
  bindTimeChange: function (e) {
    var that = this;
    that.data.formData.time = e.detail.value + ":00";
    that.setData({
      time: that.data.formData.time
    });
  },
  bindTypeChange: function (e) {
    console.log('picker type code 发生选择改变，携带值为', e.detail.value);
    var that=this;
    that.data.formData.typeIndex = e.detail.value;
  },
    initUserData: function () {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    util.setSysUser(ret.data);
                    that.setData({
                        userinfoData: ret.data,
                        isRegist: true,
                    });
                } else {
                    that.setData({
                        isRegist: false
                    });
                    wx.navigateTo({ url: "../regist/index" });
                }
            }
        );
    },
    initWxUserData: function () {
        var that = this;
        util.request(cfg.server + '/portal/www/selectWxUserByPrimaryKey.do', { "id": wx.getStorageSync('WX-SESSION-ID') },
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    that.setData({ wxUser: ret.value });
                }
            }
        );
    },
});