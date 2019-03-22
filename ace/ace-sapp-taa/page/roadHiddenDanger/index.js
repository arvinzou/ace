var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    deptArray: [], // 部门，只包含部门名称
    deptObjectArray: [], // 部门对象数组，包含部门名称以及对应编号
    dIndex: 0, //部门筛选索引
    noChangedImagesUrl: [], // 显示临时图片url列表
    noChangedServerImagesUrl: [], // 上传到服务器返回图片url
    region: [], // 地区列表,部门名称
    regionArray: [], //地区列表，部门名称和编号
    rIndex: 0, //区域筛选索引
    userData: {}, // 用户信息
    yhType: [], //隐患
    yhTypeArray: [], // 隐患类型
    yhIndex: 0, //隐患类型索引  
    isShowFalseTextarea: true, //是否显示假的textarea
    describe: '', //隐患描述 textarea输入内容
    defaultDescribe: '请描述情况', //默认提示描述

    latitude: '',
    longitude: '',
    current: [],
    isEdit: true,
    roadManId: '',
    roadManName: '',
    address: '',
    sectionFlag: false,
    sectionName: '',
    sectionId: '',
    areaCode: '',
    isCollection: false,
    polyline: [{
      points: [

      ],
      color: '#4350FC',
      width: 8,
      dottedLine: false
    }], //路段采集划线
    isAdd: false, //点击添加事故快报按钮添加快报
    showModal: false, //是否显示路段选择模态框
    sectionList: [],
    modalSeclect: 0, //路段模态框选择
    isSrink: false, //表单是否收缩,

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initDict(); // 初始化字典
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    that.getLocation(); // 获取地理位置
    var sectionId = app.globalData.sectionId; //  路段id
    var sectionName = app.globalData.sectionName; //路段名
    var roadManId = app.globalData.roadManId; // 路长id
    var roadManName = app.globalData.roadManName; // 路长姓名

    //判断全局属性是否有值存在
    if (sectionId && sectionName) {
      that.setData({
        sectionFlag: true,
        sectionId: sectionId,
        sectionName: sectionName,
      });
    }
    if (roadManId && roadManName) {
      that.setData({
        roadManId: roadManId,
        roadManName: roadManName
      });
    }
    // 获取数据后清空全局的属性值
    app.globalData.sectionName = null;
    app.globalData.roadManId = null;
    app.globalData.roadManName = null;
    app.globalData.sectionId = null;
  },
  /**
   * 初始化用户信息，图像，名字，警号，权限
   */
  initUserData: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/register/findCustomerVo', {},
      function(res) {
        if (res.status == 0) {
          that.setData({
            userData: res.data
          });
        } else {
          if (res.info == '用户尚未注册') {
            wx.navigateTo({
              url: '../regist/index',
            });
          } else {
            wx.showModal({
              title: '提示',
              content: res.info,
              success: function(res) {}
            });
          }
        }
      }
    );
  },
  /**
   *  初始化字典
   */
  initDict: function() {
    var that = this;
    util.request(cfg.server + '/portal/content/common/js/dict_taa.js', {},
      function(res) {
        var oIndex = res.indexOf("=");
        var retData = res.substring(oIndex + 1, res.length);
        var retObj = JSON.parse(retData);
        var lkyhObj = retObj['175'];
        var yhType = [];
        var yhTypeArray = [];
        if (lkyhObj) {
          // 移除code=='',name==''的那条数据
          for (var i = 0; i < lkyhObj.length; i++) {
            if (lkyhObj[i].CODE == '' && lkyhObj[i].NAME == '') {
              lkyhObj.splice(i, 1);
            }
          }
          for (var i = 0; i < lkyhObj.length; i++) {
            yhType.push(lkyhObj[i].NAME);
            yhTypeArray.push(lkyhObj[i]);
          }
          that.setData({
            yhType: yhType,
            yhTypeArray: yhTypeArray
          });
        }
      },
      function(res) {
        console.log(res);
      }
    );
  },
  /**
   * 得到当前位置信息(经纬度)
   */
  getLocation: function(e) {
    var that = this;
    wx.getLocation({
      type: 'gcj02',
      success: function(res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        var o = {
          iconPath: '../../image/icon-locate.png',
          longitude: res.longitude,
          latitude: res.latitude,
          width: 28,
          height: 28
        }
        that.toAddress(latitude, longitude);
        var arr = [];
        arr.push(o);
        that.setData({
          latitude: latitude,
          longitude: longitude,
          current: arr
        });
      },
    })
  },
  /**
   * 获取地址信息
   */
  toAddress: function(latitude, longitude) {
    var that = this;
    util.request(cfg.server + '/taa/www/mapApi/tx/reversePoint', {
        lat: latitude,
        lon: longitude
      },
      function(res) {
        console.log(res);
        if (res.status == 0) {
          that.setData({
            address: res.result.address,
            areaCode: res.result.ad_info.adcode
          });
          that.getRoadSection(latitude, longitude);
        } else {
          wx.showModal({
            title: '提示',
            content: res.info,
            success: function(res) {}
          });
        }

      }
    );
  },
  /**
   * 获取街道信息
   */
  getRoadSection: function(latitude, longitude) {
    var that = this;
    util.request(cfg.server + '/taa/www/road/getCloseRoadSection', {
        lat: latitude, //latitude   29.014811
        lon: longitude, //longitude   111.722444
        radius: '10000'
      },
      function(res) {
        console.log(res);
        if (res.status == 0) {
          console.log(res);
          var dataList = res.data.rows;
          if (dataList.length > 0) {
            that.setData({
              sectionFlag: true,
              sectionName: dataList[0].sectionName,
              sectionId: dataList[0].roadSectionId,
              roadManName: dataList[0].manName,
              roadManId: dataList[0].roadManId,
              sectionStartName: dataList[0].startName,
              sectionEndName: dataList[0].endName,
              distance: dataList[0].distance,
              sectionList: dataList
            });
          } else {
            wx.showModal({
              title: '提示',
              content: '没有获取到最近位置的路段！',
              success: function(res) {}
            });
          }

        } else {

        }

      },
      function(res) {
        console.log(res);
      }
    );
  },
  /**
   * 点击添加快报按钮
   */
  addKb: function() {
    var that = this;
    that.setData({
      isEdit: true
    });
  },
  /**
   * 弹框缩下去
   */
  closeAndOpen: function() {
    var that = this;
    if (that.data.isSrink == false) {
      //展开表单
      that.setData({
        isSrink: true
      });

    } else {
      that.setData({
        isSrink: false
      });
    }
  },
  /**
   * 快报中，重新获取路段
   */
  resetSection: function() {
    var that = this;
    that.getLocation();
    that.setData({
      showModal: true,
      isEdit: false
    })
  },
  closeModal: function() {
    var that = this;
    that.setData({
      showModal: false,
      isEdit: true
    });
  },
  /**
   * 事故快报选取路长信息
   */
  selectRoadMan: function() {
    wx.navigateTo({
      url: '../roadlist/index?type=lh',
    });
  },
  /**
   * 选择路段信息
   */
  selectRoadSection: function() {
    var that = this;
    that.setData({
      showModal: false,
      isEdit: true
    });
    wx.navigateTo({
      url: '../collection/index?type=lh',
    });
  },
  /**
   * 快报弹框选择路段信息
   */
  selectModalSection: function(e) {
    var that = this;
    console.log(e)
    app.globalData.sectionId = e.currentTarget.dataset.sectionid;
    app.globalData.sectionName = e.currentTarget.dataset.sectionname;
    app.globalData.roadManId = e.currentTarget.dataset.roadmanid;
    app.globalData.roadManName = e.currentTarget.dataset.roadman;
    app.globalData.startName = e.currentTarget.dataset.startname;
    app.globalData.endName = e.currentTarget.dataset.endname;

    that.setData({
      sectionName: app.globalData.sectionName,
      sectionId: app.globalData.sectionId,
      roadManName: app.globalData.roadManName,
      roadManId: app.globalData.roadManId,
      sectionStartName: app.globalData.startName,
      sectionEndName: app.globalData.endName,
      modalSeclect: e.currentTarget.dataset.index,
      distance: e.currentTarget.dataset.distance
    })

  },
  // 选择隐患类型
  bindYHTypeChange: function(e) {
    this.setData({
      yhIndex: e.detail.value
    })
  },
  /**
   * 选择图片来源
   */
  chooseImgResource: function() {
    var that = this;
    wx.showActionSheet({
      itemList: ['从相册中选择', '拍照'],
      success: function(res) {
        if (!res.cancel) {
          if (res.tapIndex == 0) {
            that.chooseWxImage('album')
          } else if (res.tapIndex == 1) {
            that.chooseWxImage('camera');
          }
        }
      }
    })
  },
  /**
   * 上传图片（本地临时图片）
   */
  chooseWxImage: function(type) {
    var that = this;
    var noChangedImagesUrl = that.data.noChangedImagesUrl;
    console.log(noChangedImagesUrl)
    wx.chooseImage({
      sizeType: ['original', 'compressed'], // 原图还是压缩图，默认二者都有
      sourceType: [type], // 可以指定来源是相册还是相机
      count: 5, // 最多可以选择的图片张数
      success: function(res) {
        console.log(res);
        var tempFilePaths = res.tempFilePaths;
        //判断一次性上传图片数量+显示临时图片对的数量小于等于5
        if ((tempFilePaths.length + noChangedImagesUrl.length) <= 5) {
          for (var i = 0; i < tempFilePaths.length; i++) {
            noChangedImagesUrl.push(tempFilePaths[i]);
          }
          that.setData({
            noChangedImagesUrl: noChangedImagesUrl
          });
        } else {
          wx.showModal({
            title: "提示",
            content: "允许上传图片最多为5张！",
            showCancel: false
          });
        }
      }
    })
  },
  /**
   * 移除临时图片显示路径
   */
  removeTempImgUrl: function(e) {
    var that = this;
    var index = e.currentTarget.dataset.index; // 获取索引
    var noChangedImagesUrl = that.data.noChangedImagesUrl;
    wx.showModal({
      title: '提示',
      content: '确认删除该图片？',
      success: function(res) {
        console.log(res);
        if (res.confirm) {
          noChangedImagesUrl.splice(index, 1); //移除选中的图片
          that.setData({
            noChangedImagesUrl: noChangedImagesUrl
          });
        }
      }
    });
  },
  /**
   * 提交确认后
   * 图片上传服务器
   */
  imagesUploadServer: function() {
    var that = this;
    var noChangedImagesUrl = that.data.noChangedImagesUrl;
    var noChangedServerImagesUrl = that.data.noChangedServerImagesUrl;
    console.log(noChangedImagesUrl)
    if (noChangedImagesUrl.length > 0) {
      for (var i = 0; i < noChangedImagesUrl.length; i++) {
        wx.showLoading({
          title: "正在上传"
        });
        console.log(noChangedImagesUrl[i]);
        // 文件上传服务器
        wx.uploadFile({
          url: cfg.uploadUrl,
          filePath: noChangedImagesUrl[i],
          name: 'file',
          header: {
            'content-type': 'multipart/form-data'
          },
          formData: {
            id: that.data.id,
            collectionName: "live",
            companyId: cfg.companyId
          },
          success: function(resp) {
            wx.hideLoading();
            console.log(resp)
            var obj = JSON.parse(resp.data);
            console.log(obj);
            var filePath = obj.file_path;
            filePath = filePath.replace(/^http*/, 'https'); //正则匹配以http开头，s可选的字符串
            noChangedServerImagesUrl.push(filePath);
          },
          fail: function(res) {
            wx.hideLoading();
            wx.showModal({
              title: "提示",
              content: "上传失败"
            });
          }
        });
      }
      that.setData({
        noChangedServerImagesUrl: noChangedServerImagesUrl
      });
    } else {
      wx.showModal({
        title: "提示",
        content: "允许上传图片最多为5张！"
      });
    }
  },

  /**
   * 失去焦点，显示假的文本域 
   */
  showFalseTextarea: function(e) {
    var value = e.detail.value;
    this.setData({
      isShowFalseTextarea: true,
      describe: value
    });
  },
  /**
   * 切换view 与 textarea文本域
   */
  tabTextarea: function(e) {
    console.log(e)
    var value = e.currentTarget.dataset.value;
    this.setData({
      isShowFalseTextarea: false,
      describe: value
    });
  },
  /**
   * 隐患上报提交
   */
  xbSubmit: function(e) {
    var that = this;
    console.log(e);
    var sectionId = that.data.sectionId; //路段名
    var sectionName = that.data.sectionName; //路段名
    var address = that.data.address; //地址
    var roadManName = that.data.roadManName; //路长
    var yhType = e.detail.value.yhType; //隐患类型
    var describe = that.data.describe; //隐患详细情况
    var noChangedImagesUrl = that.data.noChangedImagesUrl; //未整改的图片url
    var noChangedServerImagesUrl = that.data.noChangedServerImagesUrl; //未整改的服务器图片url
    // 验证路段
    if (sectionName == null || sectionName == '') {
      wx.showToast({
        title: '请选择路段',
        icon: 'none',
        mask: true,
        duration: 2000
      });
      return;
    }
    // 验证地址
    if (address == null || address == '') {
      wx.showToast({
        title: '请填写地址',
        icon: 'none',
        mask: true,
        duration: 2000
      });
      return;
    }
    // 验证路长
    if (roadManName == null || roadManName == '') {
      wx.showToast({
        title: '请选择路长',
        icon: 'none',
        mask: true,
        duration: 2000
      });
      return;
    }
    // 验证隐患类型
    if (yhType == null || yhType == '') {
      wx.showToast({
        title: '请选择隐患类型',
        icon: 'none',
        mask: true,
        duration: 2000
      });
      return;
    }
    // 验证隐患详细 和 图片
    if ((describe != '') || (noChangedImagesUrl.length > 0)) {
      console.log(describe, noChangedImagesUrl.length)
      if (noChangedImagesUrl.length > 0) {
        that.imagesUploadServer(); //图片上传服务器
      }
    } else {
      wx.showModal({
        title: '提示',
        content: '请填写隐患描述或者上传图片',
        showCancel: false
      });
      return;
    }
    // 提交数据之前需要上传图片，延时处理
    setTimeout(function(e) {
      console.log(noChangedServerImagesUrl)
      // 上报数据  
      util.request(cfg.server + '/taa/www/roadDangerReport/roadReport', {
          data: JSON.stringify({
            latitude: that.data.latitude, //经度
            longitude: that.data.longitude, // 维度
            roadManId: that.data.roadManId, //路长id
            roadSectionId: sectionId, // 路段id
            address: address, // 地址
            type: yhType, // 隐患类型
            remark: describe, // 隐患描述
            noChangedImagesUrl: noChangedServerImagesUrl // 未整改图片url   
          })
        },
        function(res) {
          console.log(res);
          if (res.status == 0) {
            // 提交成功后上传数据清空
            wx.showModal({
              title: '提示',
              content: res.errorMessage,
              showCancel: false,
              success: function(res) {
                if (res.confirm) {
                  that.setData({
                    sectionFlag: false,
                    sectionId: '',
                    sectionName: '',
                    roadManId: '',
                    roadManName: '',
                    noChangedImagesUrl: [],
                    describe: '',
                  });
                  that.onShow();
                }
              }
            });

          } else {
            wx.showModal({
              title: '提示',
              content: res.errorMessage,
              showCancel: true,
              success: function(res) {}
            });
          }
        },
        function(res) {
          console.log(res)
        }
      );
    }, 500)

  },


  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})