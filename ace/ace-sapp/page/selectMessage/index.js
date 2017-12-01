var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
    data: {
        requestAdd: '',
        name:'',
        list:[],
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
                that.setData({
                   list:data.value,
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
        if (module == "培训教育") {
            that.setData({
                requestAdd: cfg.selectEducationById,
                name:'培训',
            });
        } else if (module == "统战文件") {
            that.setData({
                requestAdd: cfg.selectFilesById,
                name: '文件',
            });
        } else if (module == "同心工程") {
            that.setData({
                requestAdd: cfg.selectConcentricById,
                name: '同心',
            });
        } else if (module == "统战宣传") {
            that.setData({
                requestAdd: cfg.selectPropagandaById,
                name: '宣传',
            });
        } else if (module == "统战调研") {
            that.setData({
                requestAdd: cfg.selectResearchById,
                name: '调研',
            });
        } else if (module == "统战信息") {
            that.setData({
                requestAdd: cfg.selectMessageById,
                name: '信息',
            });
        } else if (module == "精准扶贫") {
            that.setData({
                requestAdd: cfg.selectPovertyAlleviationById,
                name: '扶贫',
            });
        }
    },

    navbarTap_map: function (e) {
        console.log("map");
        console.log(e.currentTarget.dataset.latitude);
        if(!e.currentTarget.dataset.latitude){
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
    }
})

