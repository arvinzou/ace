var util = require("../../util/util.js");
var cfg = require("../../config.js");

var countdown = 30;
var stop = true;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        objectGroupArray: [

        ],
        group_index: 0,
        unionId: null,
        stepNum: 1,
        regType: 1,
        regName: "个人",
        phoneNum: null,
        lastNum: 0,
        stop: true,
        btnName: "获取验证码",
        imageCover: null,
        orgId: null,
        userinfo: null,
        politicalStatus: "1"
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        that.orgList();

    },

    radioChange: function(e) {
        var that = this;
        console.log('radio发生change事件，携带value值为：', e.detail.value);
        that.setData({
            politicalStatus: e.detail.value
        });
    },
    bindPickerChange1: function(e) {
        var that = this;
        console.log('picker发送选择改变，携带值为', e.detail.value)
        that.setData({
            group_index: e.detail.value,
            orgId: that.data.objectGroupArray[e.detail.value].value
        })
    },
    nextOne: function() {
        var that = this;
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

    phoneInput: function(e) {
        var that = this;
        that.setData({
            phoneNum: e.detail.value
        });
        console.log("phoneNum启动！");
    },
    sendCode: function(e) {
        var that = this;
        var phone = that.data.phoneNum;
        console.log("========================================phone" + phone);
        if (phone == null || phone == undefined) {
            wx.showModal({
                title: '提示',
                content: '请输入手机号码！',
                success: function(res) {}
            });
            return;
        }
        util.request(cfg.sendCode, {
                "mobile": phone
            },
            function(ret) {
                console.log(ret);
                wx.showModal({
                    title: '提示',
                    content: ret.info,
                    success: function(res) {}
                })
            }
        );
        that.settime();
    },
    settime: function() {
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
            setTimeout(function() {
                that.settime()
            }, 1000)
        }

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

    /**
     * 个人注册
     */
    personFormSubmit: function(e) {
        var that = this;

        var realName = e.detail.value.realName;
        var mobilePhone = e.detail.value.mobilePhone;
        var code = e.detail.value.code;
        if (realName == undefined || realName == '' || realName == null) {
            wx.showModal({
                title: '提示',
                content: '请输入名称！',
                success: function(res) {}
            });
            return;
        }
        if (mobilePhone == undefined || mobilePhone == '' || mobilePhone == null) {
            wx.showModal({
                title: '提示',
                content: '请输入手机号码！',
                success: function(res) {}
            });
            return;
        }
        if (code == undefined || code == '' || code == null) {
            wx.showModal({
                title: '提示',
                content: '请输入验证码！',
                success: function(res) {}
            });
            return;
        }
        var jsonData = {
            "realName": realName,
            "mobilePhone": mobilePhone,
            "orgId": that.data.orgId,
            "validPoints": 0,
            "accPoints": 0,
            "politicalStatus": that.data.politicalStatus
        }
        util.request(cfg.regist, {
                "unionId": wx.getStorageSync("WX-SESSION-ID"),
                "regType": that.data.regType,
                "mobile": mobilePhone,
                "code": code,
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