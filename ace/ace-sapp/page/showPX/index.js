var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        requestAdd: cfg.selectEducationById,
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
        util.request(
            that.data.requestAdd,
            { id: options.id },
            function (data) {
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

    navbarTap_map: function (e) {
        console.log("map");
        console.log(e.currentTarget.dataset.latitude);
        if (!e.currentTarget.dataset.latitude) {
            return;
        }
        wx.openLocation({
            latitude: parseFloat(e.currentTarget.dataset.latitude),
            longitude: parseFloat(e.currentTarget.dataset.longitude),
            scale: 28,
            address: e.currentTarget.dataset.address,
        })
    },

    navbarTap_file: function (e) {
        let that = this;
        console.log(e);
        let urls = that.data.serverfile + e.currentTarget.dataset.fileaddr;
        const downloadTask = wx.downloadFile({
            url: urls, //开启tomcat后的本机ip地址
            success: function (res) {
                console.log("res.tempFilePath");
                console.log(res.tempFilePath);
                var filePath = res.tempFilePath
                wx.openDocument({
                    filePath: filePath,
                    success: function (res) {
                        console.log('打开文档成功')
                        console.log(res)
                    },
                    fail: function (res) {
                        console.log('fail')
                        console.log(res)
                    },
                    complete: function (res) {
                        console.log('complete')
                        console.log(res)
                    }
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