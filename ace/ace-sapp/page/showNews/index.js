var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    requestAdd: '',
    name: '',
    list: [],
    serverfile: cfg.serverfile,
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
  onLoad: function (options) {
    var that = this;
    let module = options.module;
    that.chooseRequest(module);
    util.request(
      that.data.requestAdd,
      { id: options.id },
      function (data) {
          let date = data.value.published;
          if (date) {
            data.value.published = date.substring(0, 10);
          }
        that.setData({
          list: data.value,
        });
      }
    );
    /**判断有没有权限？？？？？ */
    var o = wx.getStorageSync('userInfo');
    console.log(o)
    if (!o) {
      that.setData({
        userLogin: false
      });
      // that.reg();
    } else {

      if (o.role && (o.role == 'admin')) {
        that.setData({
          userLogin: true
        });
      } else {

        if (o.deptId && o.deptId == options.id) {
          that.setData({
            userLogin: true
          });
        } else {
          //that.reg();
        }
      }
    }
  },

  chooseRequest: function (module) {
    let that = this;
    wx.setNavigationBarTitle({
      title: module,
    });
    if (module == "统战宣传") {
      that.setData({
        requestAdd: cfg.selectPropagandaById,
        name: '宣传',
      });
    } else if (module == "理论调研") {
      that.setData({
        requestAdd: cfg.selectResearchById,
        name: '调研',
      });
    } else if (module == "信息管理") {
      that.setData({
        requestAdd: cfg.selectMessageById,
        name: '信息',
      });
    }     
  },

  copyUrl: function (e) {
    console.log("map");
    let url = e.currentTarget.dataset.url;
    wx.setClipboardData({
      data: url,
      success: function (res) {
        wx.showToast({
          title: '复制成功',
          icon: 'success',
          duration: 2000
        })
      }
    })
  },
})






// wx.openDocument({
//     filePath: res.tempFilePath,
//     success: function (res) {
//         that.setData({
//             content: filePath
//         })
//     }
// })




// wx.saveFile({
//     tempFilePath: tempFilePath,
//     success: function (res) {
//         console.log("success");
//         var savedFilePath = res.savedFilePath;
//         wx.getSavedFileList({
//             success: function (res) {
//                 console.log(res.fileList)
//             }
//         })
//     },
//     fail: function (res) {
//         console.log('fail');
//         console.log(res);
//         showTip();
//     },
//     complete: function (res) {
//         console.log('complete');
//         console.log(res);
//     }
// })