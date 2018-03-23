var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        requestAdd: cfg.selectPovertyAlleviationById,
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
        util.request(
            that.data.requestAdd,
            { id: options.id },
            function (data) {
                //修改时间格式
                let date = data.value.time;
                if (date) {
                    data.value.time = date.substring(0, 10);
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
    navbarTap_tel: function (e) {
        console.log("tel");
        console.log(e);
        wx.makePhoneCall({
            phoneNumber: e.currentTarget.dataset.telnumber
        });
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