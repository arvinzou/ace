var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {

      array: ['公益活动', '普及活动', '创意活动', '党建活动'],
      objectArray: [
          {
              id: 1,
              name: '公益活动'
          },
          {
              id: 2,
              name: '普及活动'
          },
          {
              id: 3,
              name: '创意活动'
          },
          {
              id: 4,
              name: '党建活动'
          }
      ],
      index: 0,
      fileUrl: '../../image/addFile.png',
      isUpload: false,
      category: '1',
      solutionNum: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      if (!util.is_login()) {
          wx.switchTab({ url: "../userinfo/index?url=../releaseIdea/index" });
      }
  },
  formSubmit: function(e){
      var that = this;
      var title = e.detail.value.title;
      var solution = e.detail.value.solution;
      var listSubjectIdeaAnnexVo = [];
      var ideaAnnex = { "fileUrl": that.data.fileUrl}
      if (that.data.isUpload){
          listSubjectIdeaAnnexVo.push(ideaAnnex);
      }
      if(title == '' || title == undefined || title == null){
          wx.showModal({
              title: '提示',
              content: '标题不能为空！',
              success: function (res) { }
          });
          return;
      }
      if (solution == '' || solution == undefined || solution == null) {
          wx.showModal({
              title: '提示',
              content: '具体想法不能为空！',
              success: function (res) { }
          });
          return;
      }
      util.request(cfg.releaseIdea, { "params": JSON.stringify({ "title": title, "category": that.data.category, "solution": solution, "listSubjectIdeaAnnexVo": listSubjectIdeaAnnexVo })} ,
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  wx.showModal({
                      title: '提示',
                      content: ret.info,
                      success: function (res) { }
                  });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },
    addFile: function () {
        var that = this;
        wx.showActionSheet({
            itemList: ['打开照相', '选取现有的'],
            itemColor: '#007aff',
            success(res) {
                if (res.tapIndex === 0) {
                    wx.chooseImage({
                        success(res) {
                            that.uploadFileFun(res.tempFilePaths[0]);
                        }
                    })
                } else if (res.tapIndex === 1) {
                    wx.chooseImage({
                        //count: 3, // 设置最多三个文件
                        //sizeType: ['original', 'compressed'],
                        //sourceType: ['album', 'camera'],
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
                that.setData({ fileUrl: url });
                that.setData({ isUpload: true});
            },
            fail: function (res) {
                return null
            },
        })
    },

    bindPickerChange: function (e) {
        var that = this;
        console.log('picker发送选择改变，携带值为', e.detail.value)
        that.setData({
            index: e.detail.value,
            category: that.data.objectArray[e.detail.value].id
        });
    },
    checkFontNum: function(e){
        var name = e.currentTarget.dataset.name;
        let temp = {};
        temp[name] = e.detail.cursor,
        this.setData(temp);
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