var util = require("../../util/util.js");
var cfg = require("../../config.js");
var fileList = [];
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
      solutionNum: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      if (!util.is_login()) {
          wx.navigateTo({ url: "../userinfo/index?url=../releaseIdea/index&type=navigateTo" });
      }
  },
  formSubmit: function(e){
      console.log("fileList=================================="+fileList);
      var that = this;
      var title = e.detail.value.title;
      var solution = e.detail.value.solution;
      var listSubjectIdeaAnnexVo = [];
      for(var i=0; i<fileList.length; i++){
          var ideaAnnex = { "fileUrl": fileList[i] };
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
      util.request(cfg.releaseIdea, { "params": JSON.stringify({ "title": title, "category": that.data.category, "solution": solution, "listSubjectIdeaAnnexVo": listSubjectIdeaAnnexVo }), "unionId": wx.getStorageSync('WX-SESSION-ID')} ,
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
                        formData: { id: that.data.id, collectionName: "live", companyId: cfg.companyId },
                        success: function (resp) {
                            console.log(resp);
                            wx.hideLoading();
                            var obj = JSON.parse(resp.data);
                            console.log(obj);
                            fileList.push(obj.file_path);
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