var cfg = require("../../config.js");
var util = require("../../util/util.js");
var wxCharts = require('../common/lib/wxcharts.js');
const app = getApp();
var columnChart = null;
var lineChart = null;

var windowWidth = 320;
try {
    var res = wx.getSystemInfoSync();
    windowWidth = res.windowWidth;
} catch (e) {
    console.error('getSystemInfoSync failed!');
}
var cfg1 = {
    canvasId: 'columnCanvas',
    type: 'column',
    animation: true,
    categories: [],
    series: [{
        name: '站点超载情况',
        data: [],
        format: function (val, name) {
            return val;
        }
    }],
    yAxis: {
        format: function (val) {
            return val;
        },
        title: '次',
        min: 0
    },
    xAxis: {
        disableGrid: false,
        type: 'calibration'
    },
    extra: {
        column: {
            width: 15
        }
    },
    width: windowWidth,
    height: 150
};
var cfg2 = {
    canvasId: 'lineCanvas',
    type: 'line',
    categories: [],
    animation: true,
    series: [{
        name: '时段超载情况',
        data: [],
        format: function (val, name) {
            return val;
        }
    }
    ],
    xAxis: {
        disableGrid: true
    },
    yAxis: {
        title: '次',
        format: function (val) {
            return val;
        },
        min: 0
    },
    width: windowWidth,
    height: 150,
    dataLabel: false,
    dataPointShape: true,
    extra: {
        lineStyle: 'curve'
    }
}

Page({
    data: {
        isMainChartDisplay: true,
        currentTab: 0,
        currentTime: util.formatDate(new Date())
    },
    onReady: function (e) {
        var windowWidth = 320;
        var that = this;
        try {
            var res = wx.getSystemInfoSync();
            windowWidth = res.windowWidth;
        } catch (e) {
            console.error('getSystemInfoSync failed!');
        }
    },
    onLoad: function (e) {
        /**
         * 检测次数
         */
        // util.login();
        var that1 = this;
        util.request(cfg.statisticsUrl, {},
            function (data) {
                console.log(data);
                that1.setData({ statics_total: data });
            }
        );

        /**
         * 站点超载查询
         */
        var that2 = this;
        util.request(cfg.siteUrl, {},
            function (data) {
                var retData = data.siteList;
                for (var i = 0; i < retData.length; i++) {
                    //  console.log(retData[i]);
                    cfg1.categories.push(retData[i].siteName);
                    cfg1.series[0].data.push(retData[i].count);
                }
                console.log(cfg1);

                columnChart = new wxCharts(cfg1);
            }
        );

        var that3 = this;
        util.request(cfg.illegalTrafficUrl, { start: 0, limit: 30 },
            function (data) {
                that3.setData({ detailData: data.rows });
            }
        );


        var that4 = this;
        util.request(cfg.intervalUrl, {},
            function (data) {
                var retData = data.countMap;
                var retKey = data.interval;
                for (var i = 0; i < retKey.length; i++) {
                    cfg2.categories.push(retKey[i]);
                    cfg2.series[0].data.push(retData[retKey[i]]);
                }
                lineChart = new wxCharts(cfg2);
            }
        );
    },
    //滑动切换
    swiperTab: function (e) {
        var that = this;
        that.setData({
            currentTba: e.detail.current
        });
    },
    //点击切换
    clickTab: function (e) {

        var that = this;

        if (this.data.currentTab === e.target.dataset.current) {
            return false;
        } else {
            that.setData({
                currentTab: e.target.dataset.current
            })
        }
    } 
});