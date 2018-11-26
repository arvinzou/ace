var util = require("../../util/util.js");
var cfg = require("../../config.js");

var countdown = 30;
var stop = true;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        unionId: null,
        array: ['社会组织', '党组织'],
        stepNum: 1,
        regType: 2,
        orgType:1,
        regName: "社会组织",
        phoneNum: null,
        lastNum: 0,
        stop: true,
        imageCover: null,
        orgId: null,
        userinfo: null
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        that.orgList();

    },

    radioChange: function(e) {
        console.log('radio发生change事件，携带value值为：', e.detail.value);
        var that = this;
        that.setData({ orgType: e.detail.value});
    },
    nextOne: function() {
        var that = this;

        //党组织必须在添加照片的情况下才能进行后续的操作
        if (that.data.imageCover == null) {
            wx.showModal({
                title: '提示',
                content: '请上传组织照片！',
                success: function(res) {}
            });
            return;
        }
        this.setData({
            stepNum: ++that.data.stepNum,
            topNum: 0,
        });
    },

    previousOne: function() {
        var that = this;
        this.setData({
            stepNum: --that.data.stepNum,
            topNum: 0,
        });
    },
    emailInput: function(e){
        var email = e.detail.value;
        var reg = new RegExp('^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$')
        if(!reg.test(email)){
            wx.showModal({
                title: '提示',
                content: '邮箱格式不正确！',
                success: function (res) { }
            });
            return;
        }
       
    },
    /**
     * 组织注册
     */
    orgFormSubmit: function(e) {
        var that = this;
        var orgName = e.detail.value.orgName;
        var orgAddr = e.detail.value.orgAddr;
        var contactPerson = e.detail.value.contactPerson;
        var contactPhone = e.detail.value.contactPhone;
        var email = e.detail.value.email;
        var jsonData = {
            "orgName": orgName,
            "orgAddr": orgAddr,
            "contactPerson": contactPerson,
            "contactPhone": contactPhone,
            "orgType": that.data.orgType,
            "orgCover": that.data.imageCover,
            "validPoints": 0,
            "accPoints": 0,
            "email": email
        }
        if (orgName == undefined || orgName == null || orgName == '') {
            wx.showModal({
                title: '提示',
                content: '组织名称不能为空！',
                success: function(res) {}
            });
            return;
        }
        if (contactPerson == undefined || contactPerson == null || contactPerson == '') {
            wx.showModal({
                title: '提示',
                content: '联系人不能为空！',
                success: function(res) {}
            });
            return;
        }
        var reg = new RegExp('^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$');  
      if (email){
          if (!reg.test(email)) {
            wx.showModal({
              title: '提示',
              content: '邮箱格式不正确！',
              success: function (res) { }
            });
            return;
          }
        }
        
        util.request(cfg.server + '/society/www/user/newOrgInfo', {
                "unionId": wx.getStorageSync("WX-SESSION-ID"),
                "jsonData": JSON.stringify(jsonData)
            },
            function(ret) {
                if (ret.status == 0) {
                    that.setData({
                        lastNum: 3
                    });
                    that.setData({
                        stepNum: ++that.data.stepNum,
                        topNum: 0,
                    });
                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.info,
                        success: function(res) {}
                    });
                }

            }
        );
    },

    addImage: function() {
        var that = this;
        wx.showActionSheet({
            itemList: ['打开照相', '选取现有的'],
            itemColor: '#007aff',
            success: function(res) {
                if (res.tapIndex === 0) {
                    wx.chooseImage({
                        sourceType: ['camera'],
                        success: function(res) {
                            that.uploadFileFun(res.tempFilePaths[0]);
                        }
                    });
                } else if (res.tapIndex === 1) {
                    wx.chooseImage({
                        count: 1, // 设置最多三张
                        sizeType: ['original', 'compressed'],
                        sourceType: ['album', 'camera'],
                        success: function(res) {
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

    uploadFileFun: function(tempFilePaths) {
        var that = this;
        wx.uploadFile({
            url: cfg.uploadUrl,
            filePath: tempFilePaths,
            name: 'file',
            success: function(res) {
                var data = JSON.parse(res.data);
                var url = data.file_path;
                that.setData({
                    imageCover: url
                });
            },
            fail: function(res) {
                return null
            },
        })
    },


    orgList: function() {
        var that = this;
        var groupName = [];
        var groupObj = [];
        util.request(cfg.orgList, {
                "unionId": "0"
            },
            function(ret) {
                if (ret.status == 0) {
                    groupObj.push({
                        id: 0,
                        value: null,
                        name: '无组织'
                    });
                    var len = ret.data.length;
                    var datas = ret.data;
                    for (var i = 0; i < len; i++) {
                        var arrTemp = {
                            id: i + 1,
                            value: datas[i].id,
                            name: datas[i].orgName
                        }
                        groupName.push(datas[i].orgName);
                        groupObj.push(arrTemp);
                    }
                    that.setData({
                        objectGroupArray: groupObj
                    });
                    console.log(that.data.groupArray);
                    console.log(that.data.objectGroupArray);
                } else {
                    wx.showModal({
                        title: '提示',
                        content: ret.info,
                        success: function(res) {}
                    });
                }

            }
        );

    },
    close: function() {
        wx.switchTab({
            url: '../me/index'
        });
    },
    inputFocus: function(e){
        var value = e.detail.value;
        var tips = e.currentTarget.dataset.tips;
        if(value == ""){

        }
    },
    inputBlur: function(e){

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