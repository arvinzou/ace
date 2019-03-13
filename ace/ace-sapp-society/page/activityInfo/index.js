var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    id: '',
    activityInfo: {},
    btnFlag: 1,
    nowDate: new Date().Format("yyyy-MM-dd HH:mm:ss"),
    method: false,
    hiddenBtn: true,
    showBtn: false,
    accessToken: '', // 接口调用凭证
    sameWidth: 0, //宽度
    sameHeight: 0, //高度
    qrCodeImgUrl: '', //生成二维码图片地址
    coverImg: '', //封面
    coverWidth: 0, //封面宽
    coverHeight: 0, //封面高
    showModel: false,
    isCreatedImg: false //是否生成图片标志
  },
  /**
   * 页面加载，加载一次
   */
  onLoad: function(options) {
    var that = this;
    var id = options.id;
    if (options.scene) {
      id = decodeURIComponent(options.scene);
    }
    that.setData({
      id: id
    });
    that.getToken();
    //判断有没有鉴权
    if (!util.is_login()) {
      var u = '../activityInfo/index?id=' + id;
      wx.navigateTo({
        url: '../userinfo/index?url=' + encodeURIComponent(u) + '&type=navigateTo'
      });
      return;
    }
  },
  //隐藏窗口
  hideModel: function() {
    this.setData({
      showModel: false
    });
  },
  /**
   * 分享朋友圈
   */
  shareFriends: function(e) {
    var that = this;
    var postCover = '../../image/share-bg.png'; //封面图，底图
    var activeId = e.currentTarget.dataset.id; //活动页面id
    var isCreatedImg = that.data.isCreatedImg;
    if (!isCreatedImg) { //没有生成图片
      util.request(cfg.getCodeUrl, {
        sysId: 'societyMiniApp', //系统id
        page: 'page/activityInfo/index', //分享的哪一个页面
        scene: activeId //页面参数
        },
        //获取二维码图片路径成功
        function(res) {
          var filePath = res.file_path;
          if (filePath !== undefined && filePath !== null) {
            //  下载文件（二维码路径）资源到本地
            wx.downloadFile({
              url: filePath, // 仅为示例，并非真实的资源
              success: function(res) {
                // 只要服务器有响应数据，就会把响应内容写入文件并进入 success 回调，业务需要自行判断是否下载到了想要的内容
                if (res.statusCode === 200) {
                  wx.showLoading({
                    title: '图片生成中',
                  });
                  that.setData({
                    qrCodeImgUrl: res.tempFilePath
                  });
                  that.generateImage(); //根据以上信息开始画图，合成一张图片
                  //canvas画图需要时间而且还是异步的，所以加了个定时器
                  setTimeout(function() {
                    //将生成的canvas图片，转为真实图片
                    wx.canvasToTempFilePath({
                      x: 0,
                      y: 0,
                      canvasId: 'shareCanvas',
                      success: function(res) {
                        var shareImg = res.tempFilePath;
                        that.setData({
                          shareImg: shareImg,
                          showModel: true,
                          showShareModal: false,
                          isCreatedImg: true
                        });
                        wx.hideLoading();
                      },
                      fail: function(res) {
                        wx.hideLoading();
                      }
                    });
                  }, 500);
                }
              }
            });
          }
        },
        //获取二维码失败
        function(res) {
        }
      );
    } else { // 生成图片
      that.setData({
        showModel: true
      });
    }


  },
  /**
   * 生成图片
   */
  generateImage: function() {
    var that = this;
    const ctx = wx.createCanvasContext('shareCanvas'); //绘图上下文
    const activityInfo = that.data.activityInfo; //获取活动信息
    const title = activityInfo.title; //标题 
    const startDate = activityInfo.startDate; // 开始时间
    const endDate = activityInfo.endDate; // 结束时间
    const location = activityInfo.location; //活动地点
    var initCanvasWidth = 270;
    var initCanvasHeight = 360;
    //屏幕高度
    // var screenWidth = wx.getSystemInfoSync().screenWidth;
    // var screenHeight = wx.getSystemInfoSync().screenHeight;
    that.setData({
      sameWidth: initCanvasWidth,
      sameHeight: initCanvasHeight
    })
    ctx.save(); //保存绘图上下文。
    // canvas 背景颜色设置不成功，只好用default背景图
    // 绘制 封面图并裁剪（这里图片确定是按100%宽度，同时高度按比例截取，否则图片将会变形）
    // 裁剪位置  图片上的坐标  x:0 ,y: (coverHeight - 129 * coverWidth / 252) / 2
    // 图片比例 255:129   图片宽度按原图宽度即coverWidth  图片高度按129*coverWidth/252
    // 开始绘图的位置  16, 94
    // 裁剪框的大小，即需要图片的大小 252, 129 (300,600)
    ctx.drawImage('../../image/share-bg.png', 0, 0, initCanvasWidth, initCanvasHeight);
    // 标题
    ctx.setFillStyle('#ffffff'); // 文字颜色：白色
    ctx.setFontSize(13); // 文字字号：18px
    ctx.setTextAlign('center'); // 文字居中
    ctx.font = 'normal bold sans-serif';
    // 在画布上绘制被填充的文本
    ctx.fillText(title, initCanvasWidth / 2, 35);

    //活动地点
    ctx.setFontSize(11);
    ctx.fillText('活动地点：' + location, initCanvasWidth / 2, 55);

    //开始时间
    ctx.setFontSize(10);
    ctx.fillText('开始时间：' + startDate, initCanvasWidth / 2, 75);

    //结束时间
    ctx.setFontSize(10);
    ctx.fillText('结束时间：' + endDate, initCanvasWidth / 2, 95);
    // 二维码绘制图像到画布
    ctx.drawImage(that.data.qrCodeImgUrl, initCanvasWidth / 2 - (65), 170, 130, 130);

    ctx.draw(); //将之前在绘图上下文中的描述（路径、变形、样式）画到 canvas 中。
  },
  // 长按保存事件
  saveImg: function() {
    var that = this;
    // 获取用户是否开启用户授权相册
    wx.getSetting({
      success: function(res) {
        // 如果没有则获取授权
        if (!res.authSetting['scope.writePhotosAlbum']) {
          wx.authorize({
            scope: 'scope.writePhotosAlbum',
            success: function() {
              wx.saveImageToPhotosAlbum({
                filePath: that.data.shareImg,
                success: function() {
                  wx.showToast({
                    title: '保存成功'
                  })
                },
                fail: function() {
                  wx.showToast({
                    title: '保存失败',
                    icon: 'none'
                  })
                }
              })
            },
            fail: function() {
              // 如果用户拒绝过或没有授权，则再次打开授权窗口
              wx.showModal({
                title: '提示',
                content: '是否打开相机授权设置？',
                success: function(res) {
                  if (res.confirm) {
                    wx.openSetting({
                      success: function(res) {
                      }
                    });
                  }
                }
              })
            }
          })
        } else {
          // 有则直接保存
          wx.saveImageToPhotosAlbum({
            filePath: that.data.shareImg,
            success: function() {
              wx.showToast({
                title: '保存成功'
              })
            },
            fail: function() {
              wx.showToast({
                title: '保存失败',
                icon: 'none'
              })
            }
          })
        }
      }
    })
  },
  /**
   * 获取token值
   */
  getToken: function(e) {
    var that = this;
    util.request(cfg.token, {
        sysId: 'societyMiniApp'
      },
      function(res) {
      },
      function(res) {
      }
    );
  },

  /**
   * 按钮判断
   */
  ifCreatBtn: function() {
    var that = this;
    // 已经登陆过了。获取用户信息
    var sysUserInfo = util.getSysUser();
    // 如果没有用户信息
    if (sysUserInfo) {
      if ((that.data.activityInfo.category == 4 && sysUserInfo.person.politicalStatus == 1) || (that.data.activityInfo.initiatorId == sysUserInfo.person.id)) {
        return;
      }
    }
    that.setData({
      hiddenBtn: false,
    });
    that.btnControl();
  },

  // 获取列表
  initdata: function() {
    var that = this;
    util.request(cfg.findActivity, {
        id: that.data.id,
      },
      function(rst) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
        that.data.activityInfo = rst.data;

        that.setBarTitleText(rst.data.title);
        that.ifCreatBtn();
        that.setData({
          activityInfo: rst.data
        });
      }
    );
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var id = this.data.id;
    var that = this;
    if (!id) {
      wx.navigateBack({})
      return;
    }
    this.initdata(); //初始化数据
  },


  btnControl: function() {
    var that = this;
    if (that.data.activityInfo.dendline > that.data.nowDate) {
      util.request(cfg.applyStatus, {
          activityId: that.data.activityInfo.id,
        },
        function(rst) {
          //        code:1、未注册和鉴权2、账户类型为组织，3、未报名，4、已报名
          var code = rst.data.code;
          that.setData({
            btnFlag: code,
          })
        }
      );
    } else {
      that.setData({
        btnFlag: 0,
      })
    }
  },

  setBarTitleText: function(tit) {
    wx.setNavigationBarTitle({
      title: tit
    })
  },
  // 查看更多参与人员
  viewParticipants: function(e) {
    var p = e.currentTarget.dataset.id;
    var that = this;
    var flag = false;
    var sysUserInfo = util.getSysUser();
    //如果没有注册，尝试重新申请获取一次。
    if (sysUserInfo) {
        flag = that.data.activityInfo.sId == sysUserInfo.societyOrg.id;
    }
    wx.navigateTo({
      url: '../participants/index?id=' + p + "&flag=" + flag,
    })
  },
  apply: function() {
    var that = this;
    var coin = that.data.activityInfo.participant;
    if (coin < 0) {
      wx.showModal({
        title: '提示',
        content: "参加活动需要" + coin + "爱心币",
        success: function(res) {
          if (res.confirm) {
            var user = util.getSysUser();
            if (user.person.validPoints > -coin) {
              wx.navigateTo({
                url: '../enterActivity/index?id=' + that.data.activityInfo.id + '&category=' + that.data.activityInfo.category
              })
            } else {
              wx.showToast({
                title: '爱心币不足',
                icon: 'success',
                duration: 2000,
                complete: function() {
                  return;
                }
              });
            }
          } else {
            return;
          }
        }
      })
    } else {
      wx.navigateTo({
        url: '../enterActivity/index?id=' + that.data.activityInfo.id + '&category=' + that.data.activityInfo.category
      })
    }
  },
  onPageScroll: function(e) {
    if (this.data.hiddenBtn) {
      return;
    }
    if (e.scrollTop <= 0) {
      // 滚动到最顶部
      e.scrollTop = 0;
    } else if (e.scrollTop > this.data.scrollHeight) {
      // 滚动到最底部
      e.scrollTop = this.data.scrollHeight;
    }
    if (e.scrollTop > this.data.scrollTop || e.scrollTop >= this.data.scrollHeight) {
      this.setData({
        showBtn: true,
      });
    } else {
      this.setData({
        showBtn: false,
      });
    }
    //给scrollTop重新赋值 
    this.setData({
      scrollTop: e.scrollTop
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    wx.stopPullDownRefresh();
    return;
  },
  signIn: function() {
    wx.navigateTo({
      url: '../regist/index',
    })
  },
  onShareAppMessage: function(res) {
    var that = this;
    if (res.from === 'button') {
      // 来自页面内转发按钮
    }
    var id = that.data.id;
    return {
      title: that.data.tit,
      path: '/page/activityInfo/index?id=' + id,
      success: function(res) {
        // 转发成功
      },
      fail: function(res) {
        // 转发失败
      }
    }
  }
})