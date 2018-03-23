var util = require("../../util/util.js");
var cfg = require("../../config.js");
var fileList=[];
Page({
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/feedback/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  data: {
    countries: [],
    countryIndex: 0,
    max:0,
    loading: false,
    disabled: false,
    id: util.uuid(),
    files: [],
    tabActiveLeft: "tab-active",
    tabActiveRight: "",
  },
  onReady: function (res) {
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  tableft: function (e) {
    this.setData({
      tabActiveLeft: "tab-active",
      tabActiveRight: "",
    })
  },
  tabright: function (e) {
    this.setData({
      tabActiveLeft: "",
      tabActiveRight: "tab-active",
    })
  },
  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      countryIndex: e.detail.value
    })
  },
  formSubmit: function (e) {
    var that=this;
    this.setData({
      loading: true,
      disabled: true
    })
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    e.detail.value["id"]=this.data.id;
    util.request(cfg.insertFeedback, e.detail.value,
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        
        if(data.status=='0'){
          that.navigator('../info/index');
        }else{
          wx.showModal({
            title: '提示',
            content: data.errorMessage,
            success: function (res) {
              if (res.confirm) {
              }
            }
          })
        }
        
      }
    );
  },
  formReset: function (e) {
    console.log('form发生了reset事件，携带数据为：', e.detail.value)
    this.setData({
      chosen: ''
    })
  },
  onLoad: function () {
    this.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      fileList: []
    });
    var that = this;
    util.request(cfg.selectAreaCodeList, {areaCode:cfg.areaCode},
      function (data) {
        var countries = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          countries.push(o.name);
        }
        that.setData({
          countries: countries,
          checkImageUrl: cfg.checkImageUrl
        });
      }
    );
  },
  navigator: function (url) {
    wx.navigateTo({
      url: url
    });
  },
   //事件
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
       
        for (var i= 0; i < res.tempFilePaths.length;i++){
          wx.showLoading({ title: "正在上传" });
          console.log(res.tempFilePaths[i]);
          wx.uploadFile({
            url: cfg.uploadUrl,
            filePath: res.tempFilePaths[i],
            name: 'file',

            header: {
              'content-type': 'multipart/form-data'
            },
            formData: {id: that.data.id, collectionName:"app" },
            success: function (resp) {
              console.log(resp);
              wx.hideLoading();
              var obj = JSON.parse(resp.data);
              console.log(obj);
              var list = obj.value;
              fileList.push(cfg.serverfile + list[0].fileUrl);
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
      itemColor: "#f7982a",
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
  formReset:function(e){
    console.log("clear");
    fileList=[];
    this.setData({
      files: fileList,
      id: util.uuid()
    });
  }

});