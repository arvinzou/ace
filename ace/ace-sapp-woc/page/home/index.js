var cfg = require("../../config.js");
var util = require("../../util/util.js");
var wxCharts = require('../common/lib/wxcharts.js');
const app = getApp();
var columnChart = null;
var lineChart = null;
var chartData = {
    main: {
        title: '站点超载情况',
        data: [15, 20, 45, 37],
        categories: ['站点1', '站点2', '站点3', '站点4']
    }

};
Page({
    data: {
        isMainChartDisplay: true,
        detailData: [
            { "time": "04.11", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.12" },
            { "time": "04.12", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.12" },
            { "time": "04.13", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.2" },
            { "time": "04.14", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.08" },
            { "time": "04.14", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.09" },
            { "time": "04.14", "carNum": "湘J0906LG", "zs": "1", "loadPercent": "0.08" },
        ],
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
                name: '成交量',
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
        util.request(cfg.statisticsUrl, { },
            function () {
                // that.setData({
                //     loading: false,
                //     disabled: false
                // })
                wx.showModal({
                    title: '提示',
                    content: '',
                    success: function (res) {
                        if (res.confirm) {
                           console.log(res);
                        }
                    }
                })
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
            } 
});