var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    serverfile: cfg.serverfile,
    searched:false,
    errord:false,
    list:[],
    winWidth: 0,
    winHeight: 0,
    currentTab: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
    that.getUserInfo();
  },
  initData:function(){
    var that=this;
    util.request(cfg.selectWxUserByPrimaryKey, { id: that.data.id },
      function (data) {
        that.setData({
          o: data.value
        });
      }
    );
    util.request(cfg.selectWxUser, { faceToken: "yes" },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  },
  onShareAppMessage: function (res) {
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/face/index'
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },

  /**  
     * 滑动切换tab  
     */
  bindChange: function (e) {
    var that = this;
    that.setData({ currentTab: e.detail.current });
  },
  /**  
   * 点击tab切换  
   */
  swichNav: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },
  getUserInfo: function () {
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    wx.getLocation({
      type: 'gcj02',
      success: function (data) {
        console.log(data);
        wx.login({
          success: function (o) {
            wx.getUserInfo({
              success: function (res) {

                wx.request({
                  url: cfg.loginUrl,
                  data: {
                    appid: cfg.appid,
                    appsecret: cfg.appsecret,
                    code: o.code,
                    encryptedData: res.encryptedData,
                    iv: res.iv,
                    latitude: data.latitude,
                    longitude: data.longitude
                  },
                  success: function (r) {
                    wx.setStorageSync('WX-SESSION-ID', r.data.value['3rd_session']);


                    that.setData({
                      loading: false,
                      disabled: false,
                      userInfo: res.userInfo,
                      id: r.data.value['3rd_session'],
                      WXSESSIONID: r.data.value['3rd_session']
                    });
                    that.initData();
                    console.log(res);
                    console.log(r.data.value['3rd_session']);
                  },
                  fail: function ({ errMsg }) {
                    console.log('request fail', errMsg)
                  }
                })
              }
            })
          }
        })
      }
    });
  },
  previewPhoto: function () {
    var o = this.data.o;
    var that = this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [o.photo] // 需要预览的图片http链接列表
    })
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
            url: cfg.updateFaceToken,
            filePath: res.tempFilePaths[i],
            name: 'file',
            dataType: "json",
            header: {
              'content-type': 'multipart/form-data',
              'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID')
            },
            formData: { id: that.data.id, collectionName: "app" },
            success: function (resp) {
              console.log(resp);
              wx.hideLoading();
              var obj = JSON.parse(resp.data);
              that.initData();
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
  chooseImageSearch: function () {
    let that = this;
    wx.showActionSheet({
      itemList: ['从相册中选择', '拍照'],
      itemColor: "#f7982a",
      success: function (res) {
        if (!res.cancel) {
          if (res.tapIndex == 0) {
            that.chooseWxImageSearch('album')
          } else if (res.tapIndex == 1) {
            that.chooseWxImageSearch('camera')
          }
        }
      }
    })
  },
  chooseWxImageSearch: function (type) {
    var that = this;
    wx.chooseImage({
      sizeType: ['original', 'compressed'],
      sourceType: [type],
      success: function (res) {

        for (var i = 0; i < res.tempFilePaths.length; i++) {
          wx.showLoading({ title: "正在上传" });
          console.log(res.tempFilePaths[i]);
          wx.uploadFile({
            url: cfg.searchFace,
            filePath: res.tempFilePaths[i],
            name: 'file',
            dataType: "json",
            header: {
              'content-type': 'multipart/form-data',
              'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID')
            },
            formData: { id: that.data.id, collectionName: "app" },
            success: function (resp) {
              var data = JSON.parse(resp.data);
              console.log(data);
              wx.hideLoading();
              if (!data.value){
                errord:true,
                wx.showModal({ title: "提示", content: "查找失败，没有找到。" })
              }
              that.setData({
                m:data.value,
                photo:data.other.photo,
                searched: data.state
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
})