var util = require("../../util/util.js");
var cfg = require("../../config.js");

Page({
    data: {
        serverfile: cfg.serverfile,
        list: [
            {
                id: "1",
                name: "统战档案",
                menu: [
                    { id: "1", opentype: "navigate", name: "统战单位", icon: "tongzhan_dept.png", page: "../dept/index", color: "#ee4b47" },
                    { id: "2", opentype: "navigate", name: "统战人士", icon: "tongzhan_rs.png", page: "../personage/index", color: "#fcae14" },
                    { id: "3", opentype: "navigate", name: "统战文件", icon: "tongzhan_wj.png", page: "../files/index", color: "#3fbc54" },
                    { id: "4", opentype: "navigate", name: "统战GIS", icon: "tongzhan_GIS.png", page: "../service/index", color: "#3285ff" },
                ]
            },

            {
                id: "2",
                name: "统战活动",
                menu: [
                    { id: "5", opentype: "navigate", name: "统战管理", icon: "tongzhan_gl.png", page: "../activitySec/index?category=1", color: "#3285ff" },
                    { id: "6", opentype: "navigate", name: "同心行动", icon: "tongxin_xd.png", page: "../activitySec/index?category=2", color: "#ff5c61" },
                    { id: "7", opentype: "navigate", name: "建言献策", icon: "jianyan.png", page: "../feedback/index", color: "#83cc2b" },
                    { id: "8", opentype: "navigate", name: "统战飞讯", icon: "tongzhan_fx.png", page: "../notice/index", color: "#1bc5bb" }
                ]
            },

            {
                id: "3",
                name: "日常工作",
                menu: [
                    { id: "9", opentype: "navigate", name: "同心工程", icon: "tongxin_gc.png", page: "../concentric/index", color: "#ee4b47" },
                    { id: "10", opentype: "navigate", name: "统战宣传", icon: "tongzhan_xc.png", page: "../propaganda/index", color: "#754aff" },
                    { id: "11", opentype: "navigate", name: "统战调研", icon: "tongzhan_dy.png", page: "../research/index",  color: "#ffc010" },
                    { id: "12", opentype: "navigate", name: "统战信息", icon: "tongzhan_xx.png", page: "../message/index", color: "#69cffa" },
                    { id: "13", opentype: "navigate", name: "培训教育", icon: "jypx.png", page: "../education/index", color: "#3fbc54" },
                    { id: "14", opentype: "navigate", name: "精准扶贫", icon: "tongzhan_jzfp.png", page: "../povertyAlleviation/index", color: "#ff5c61" }
                ]
            },

            // {
            //     id: "4",
            //     name: "统战看板",
            //     menu: [
            //         { id: "15", opentype: "navigate", name: "统战数据", icon: "tongzhan_sj.png", page: "#", color: "#3285ff" }
            //     ]
            // },
        ]

    },

    onReady: function (res) {
        console.log('index.js.onReady')
        wx.setNavigationBarColor({
            frontColor: cfg.frontColor,
            backgroundColor: cfg.backgroundColor,
            animation: {
                duration: 400,
                timingFunc: 'easeIn'
            }
        });
    },

    navigator: function (url) {
        wx.switchTab({
            url: url
        });
    },

    onLoad: function () {
        console.log('index.js.onLoad');
    },

    next: function () {
        this.navigator('../dept/index');
    }
});