var util = require("../../util/util.js");
var cfg = require("../../config.js");

var countdown = 30;
var stop = true;
Page({

  /**
   * 页面的初始数据
   */
  data: {
      array: ['个人', '社会组织', '党员', '党组织'],
      objectArray: [
          {
              id: 0,
              name: '个人'
          },
          {
              id: 1,
              name: '社会组织'
          },
          {
              id: 2,
              name: '党员'
          },
          {
              id: 3,
              name: '党组织'
          }
      ],
      index: 0,

      groupArray: ['心阳光联盟', '华彩伟业'],
      objectGroupArray: [
          {
              id: 0,
              name: '心阳光联盟'
          },
          {
              id: 1,
              name: '华彩伟业'
          }
      ],
      group_index: 0,

      stepNum: 1,
      regType: null,
      regName: null,
      phoneNum: null,
      lastNum: 0,
      stop: true,
      btnName: "获取验证码",
      imageCover: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      if (!util.isLogin()) {
          wx.navigateTo({ url: "../userinfo/index?url=../regist/index" });
      }
      that.setData({
          userinfo: wx.getStorageSync('userinfo')
      });
  },
  bindPickerChange: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        var that = this;
        this.setData({
            index: e.detail.value
        });
      console.log((that.data.array)[that.data.index]);
      var name = (that.data.array)[that.data.index];
      that.setData({ regName: name});
        if (name == '个人' || name == '党员') {
            that.setData({ regType: '1'});
        }else{
            that.setData({ regType: '2' });
        }
    },

    bindPickerChange1: function (e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            group_index: e.detail.value
        })
    },
    nextOne: function () {
        var that = this;
        
        //党组织必须在添加照片的情况下才能进行后续的操作
        if (that.data.regName == '党组织' && that.data.imageCover == null){
            wx.showModal({
                title: '提示',
                content: '类型为党组织时，必须添加党组织照片！',
                success: function (res) { }
            });
            return;
        }
        this.setData({
            stepNum: ++that.data.stepNum,
            topNum: 0,
        });
    },

    previousOne: function () {
        var that = this;
        this.setData({ stepNum: --that.data.stepNum, topNum: 0, });
    },

    /**
     * 组织注册
     */
    orgFormSubmit: function(e){
        var that = this;
        var orgName = e.detail.value.orgName;
        var orgAddr = e.detail.value.orgAddr;
        var contactPerson = e.detail.value.contactPerson;
        var contactPhone = that.data.phoneNum;
        var code = e.detail.value.code;
        var orgType = null;
        if (that.data.regName == '党组织'){
            orgType = '1';
        }else{
            orgType = '2';
        }
        var jsonData = { "orgName": orgName, "orgAddr": orgAddr, "contactPerson": contactPerson, "contactPhone": contactPhone, "orgType": orgType, "orgCover": that.data.imageCover }
        if (orgName == undefined || orgName == null || orgName == ''){
            wx.showModal({
                title: '提示',
                content: '组织名称不能为空！',
                success: function (res) {}
            });
            return;
        }
        if (contactPerson == undefined || contactPerson == null || contactPerson == ''){
            wx.showModal({
                title: '提示',
                content: '联系人不能为空！',
                success: function (res) { }
            });
            return;
        }
        if (contactPhone == undefined || contactPhone == null || contactPhone == ''){
            wx.showModal({
                title: '提示',
                content: '联系电话不能为空！',
                success: function (res) { }
            });
            return;
        }
        if (code == undefined || code == null || code == ''){
            wx.showModal({
                title: '提示',
                content: '验证码不能为空！',
                success: function (res) { }
            });
            return;
        }

        util.request(cfg.regist, { "unionId": "0", "regType": that.data.regType, "mobile": contactPhone, "code": code, "jsonData": JSON.stringify(jsonData)},
            function (ret) {
                if (ret.status == 0){
                    that.setData({ lastNum: 3 });
                    that.setData({
                        stepNum: ++that.data.stepNum,
                        topNum: 0,
                    });
                }else{
                    wx.showModal({
                        title: '提示',
                        content: ret.errorMessage,
                        success: function (res) { }
                    });
                }
                
            }
        );
    },
    phoneInput: function(e){
        var that = this;
        that.setData({ phoneNum: e.detail.value});
    },
    sendCode: function(e){
        var that = this;
        var phone = that.data.phoneNum;
        if(phone == null || phone == undefined){
            wx.showModal({
                title: '提示',
                content: '请输入手机号码！',
                success: function (res) { }
            });
            return;
        }
        util.request(cfg.sendCode, { "mobile": phone },
            function (ret) {
                console.log(ret);
                wx.showModal({
                    title: '提示',
                    content: '短信发送成功！',
                    success: function (res) {}
                })
            }
        );
        that.settime();
    },
    settime: function () {
        var that = this;
        var btnName = "获取验证码";
        if (countdown == 0) {
            btnName = "获取验证码";
            countdown = 30;
            stop = true;
        } else {
            stop = false;
            btnName = "重新发送 " + countdown + "";
            countdown--;
        }
        that.setData({
            countdown: countdown,
            btnName: btnName,
            stop: stop
        })
        if (!stop) {
            setTimeout(function () {
                that.settime()
            }, 1000)
        }

    },

    addImage: function(){
        var that = this;
        wx.showActionSheet({
            itemList: ['打开照相', '选取现有的'],
            itemColor: '#007aff',
            success(res) {
                if (res.tapIndex === 0) {
                    wx.chooseImage({
                        sourceType: ['camera'],
                        success(res) {
                            that.uploadFileFun(res.tempFilePaths[0]);
                        }
                    })
                } else if (res.tapIndex === 1) {
                    wx.chooseImage({
                        count: 1, // 设置最多三张
                        sizeType: ['original', 'compressed'],
                        sourceType: ['album', 'camera'],
                        success(res) {
                            var tempFilePaths = res.tempFilePaths;
                            for (var i = 0; i < tempFilePaths.length; i++) {
                                that.uploadFileFun(tempFilePaths[i]);
                            }
                        }
                    })
                }
            }
        })
    },

    uploadFileFun: function (tempFilePaths) {
        var that = this;
        wx.uploadFile({
            url: 'http://zx.huacainfo.com/portal/www/uploadFile.do',
            filePath: tempFilePaths,
            name: 'file',

            formData: {
                collectionName: 'ceshi',
                id: '111'
            },
            success: function (res) {
                var data = JSON.parse(res.data);
                var url = 'http://zx.huacainfo.com/' + data.value[0].fileUrl;
                that.setData({ imageCover: url});
            },
            fail: function (res) {
                return null
            },
        })
    },

    /**
     * 个人注册
     */
    personFormSubmit: function(e){
        var that = this;
        var politicalStatus = null;
        if (that.data.regName == '个人') {
            politicalStatus = '1';
        } else {
            politicalStatus = '2';
        }

        var realName = e.detail.value.realName;
        var mobilePhone = e.detail.value.mobilePhone;
        var code = e.detail.value.code;
        var jsonData = { "realName": realName, "mobilePhone": mobilePhone}
        util.request(cfg.regist, { "unionId": "0", "regType": that.data.regType, "mobile": mobilePhone, "code": code, "jsonData": JSON.stringify(jsonData) },
            function (ret) {
                if (ret.status == 0) {
                    that.setData({ lastNum: 3 });
                    that.setData({
                        stepNum: ++that.data.stepNum,
                        topNum: 0,
                    });
                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.info,
                        success: function (res) { }
                    });
                }

            }
        );
        
    },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})