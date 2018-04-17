var cfg = require("../../config.js");
var util = require("../../util/util.js");
var wxCharts = require('../common/lib/wxcharts.js');
const app = getApp();
var columnChart = null;

var lineChart = null;
var chartData = {
    main: {
        title: '站点超载情况',
        data: [],
        categories: []
    }
};
Page({
    data: {
        isMainChartDisplay: true,
        currentTab: 0
    },
    onReady: function (e) {
        var windowWidth = 320;
        try {
            var res = wx.getSystemInfoSync();
            windowWidth = res.windowWidth;
        } catch (e) {
            console.error('getSystemInfoSync failed!');
        }

        columnChart = new wxCharts({
            canvasId: 'columnCanvas',
            type: 'column',
            animation: true,
            categories: chartData.main.categories,
            series: [{
                name: '站点超载情况',
                data: chartData.main.data,
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
            height: 200,
        });
    },

    createSimulationData: function () {
        var categories = [];
        var data = [];
        for (var i = 0; i < 10; i++) {
            categories.push('0' + '时');
            data.push(Math.random() * (20 - 10) + 10);
        }
        // data[4] = null;
        return {
            categories: categories,
            data: data
        }
    },

    onLoad: function (e) {
        /**
         * 检测次数
         */
        util.login();
        var that1 = this;
        util.request(cfg.statisticsUrl, { },
            function (data) {
              console.log(data);
              that1.setData({ statics_total : data });
            }
        );

        /**
         * 站点超载查询
         */
        var that2 = this;
        util.request(cfg.siteUrl, {},
            function (data) {
                var retData = data.siteList;
                for (var i = 0; i < retData.length; i++){
                    chartData.main.data.push(retData[i].count);
                    chartData.main.categories.push(retData[i].siteName);
                }
                console.log(data);
                
            }
        );

        /**
         * 超载详情查询
         */
        var that3 = this;
        util.request(cfg.illegalTrafficUrl, {start: 0 , limit : 1000},
            function (data) {
                that3.setData({ detailData: data.rows });
            }
        );

        /**
         * 时段超载情况查询
         */
        var that4 = this;
        util.request(cfg.intervalUrl, { },
            function (data) {
                that4.setData({ intervalData: data });
            }
        );

        var windowWidth = 320;
        try {
            var res = wx.getSystemInfoSync();
            windowWidth = res.windowWidth;
        } catch (e) {
            console.error('getSystemInfoSync failed!');
        }

        var simulationData = this.createSimulationData();
        lineChart = new wxCharts({
            canvasId: 'lineCanvas',
            type: 'line',
            categories: simulationData.categories,
            animation: true,
            series: [{
                name: '时段超载情况',
                data: simulationData.data,
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
            height: 200,
            dataLabel: false,
            dataPointShape: true,
            extra: {
                lineStyle: 'curve'
            }
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
    },

    //查询单条通行记录详情
    clickRow : function(e){
        var that = this;
        
    }
});