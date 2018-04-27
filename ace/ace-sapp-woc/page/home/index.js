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
    onPullDownRefresh: function () {
        this.setData({ 
                        currentTab: 0,
                        objectArray: [

                        ],
                        index: -1,    //默认不显示站点
                        index1: -1,
                        index2: -1,
                        isFromSearch: true, //用来判断返回的超载记录是否是空的数组
                        searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
                        callbackcount: 7,      //返回数据的个数  
                        searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
                        searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏 
                        trafficList: [],
                        winHeight: 500
                        
                    });
        this.onLoad();
        wx.stopPullDownRefresh();
    },
    data: {
        isMainChartDisplay: true,
        currentTab: 0,
        currentTime: util.formatDate(new Date()),
        /**
         * 用来存储各个站点信息
         */
        objectArray: [
           
        ],
        index: -1,    //默认不显示站点
        index1: -1,
        index2: -1,
        isFromSearch: true, //用来判断返回的超载记录是否是空的数组
        searchPageNum: 1,   // 设置加载的第几次，默认是第一次  
        callbackcount: 7,      //返回数据的个数  
        searchLoading: false, //"上拉加载"的变量，默认false，隐藏  
        searchLoadingComplete: false,  //“没有数据”的变量，默认false，隐藏 
        trafficList: [],
        winHeight: 500,
        userinfo: wx.getStorageSync('userinfo'),
        modalFlag: true
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
        var that = this;
        if (wx.getStorageSync('userinfo')){
            console.log('logined');
        }else{
            util.login();
        }
        
        // var userinfo =  wx.getStorageSync('userinfo');
        // var userinfo_role = userinfo.role;
        // if (userinfo_role != 'admin'){
        //     that.setData({ modalFlag: false });
        // }
        wx.getSystemInfo({
            success: (res) => { // 用这种方法调用，this指向Page
                this.setData({
                    winHeight: res.windowHeight
                });
            }
        });

        /**
         * 检测次数
         */
        var that1 = this;
        util.request(cfg.statisticsUrl, {}, 'GET',
            function (data) {
                console.log(data);
                that1.setData({ statics_total: data });
            }
        );

        var that2 = this;
        var startTime = util.formatDate(new Date()) + ' 00:00:00';
        var endTime = util.formatDate(new Date()) + ' 23:59:59';
        that2.siteLoad(startTime, endTime);

        var that4 = this;
        util.request(cfg.intervalUrl, {}, 'GET',
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
        var that = this;
        /**
         * 查询所有站点信息
         */
        util.request(cfg.allSiteUrl, {start: 0, limit: 9999},'GET',
            function (data) {
                var siteRows = data.rows;
                var sites = [];
                for(var i=0; i< siteRows.length; i++){
                    sites.push(siteRows[i].siteName);
                }
                that.setData({ objectArray: data.rows});
                that.setData({ objectArray1: data.rows});
                that.setData({ objectArray2: data.rows});
                console.log(data);
                
            }
        );

        //初始化加载各站点的超载信息
        that.setData({
            searchPageNum: 1,   //第一次加载，设置1  
            trafficList: [],  //放置返回数据的数组,设为空  
            isFromSearch: true,  //第一次加载，设置true  
            searchLoading: true,  //把"上拉加载"的变量设为true，显示  
            searchLoadingComplete: false //把“没有数据”设为false，隐藏  
        });
        that.fetchTrafficList();  
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
            });
        }

        if (e.target.dataset.current == '0'){
            //按照当日的各站点超载情况
            var startTime = util.formatDate(new Date())+' 00:00:00';
            var endTime = util.formatDate(new Date())+' 23:59:59';
            that.siteLoad(startTime, endTime);
        } else if (e.target.dataset.current == '1'){
            //按照本月的各站点超载情况
            var startTime = util.getCurrentMonthFirstDate(new Date());
            var endTime = util.formatDate(new Date());
            that.siteLoad(startTime, endTime);
        } else if (e.target.dataset.current == '2'){
            //按照本季度的各站点超载情况
            var timeSection = util.getCurrentQuarter(new Date());
            var startTime = timeSection[0];
            var endTime = timeSection[1];
            that.siteLoad(startTime, endTime);
        }
    },

    siteLoad: function(startTime, endTime){
        util.request(cfg.siteUrl, { startDt: startTime, endDt: endTime }, 'GET',
            function (data) {
                var retData = data.siteList;
                cfg1.categories.splice(0, cfg1.categories.length);
                cfg1.series[0].data.splice(0, cfg1.series[0].data.length);
                for (var i = 0; i < retData.length; i++) {
                    cfg1.categories.push(retData[i].siteName);
                    cfg1.series[0].data.push(retData[i].count);
                }
                console.log(cfg1);

                columnChart = new wxCharts(cfg1);
            }
        );
    },

    bindPickerChange: function (e) {
        var that = this;
        that.setData({
            index: e.detail.value
        });
        var siteId = that.data.objectArray[e.detail.value].id;
        util.request(cfg.statisticsUrl, {siteId : siteId}, 'GET',
            function (data) {
                console.log(data);
                that.setData({ statics_total: data });
            }
        );
    },
    bindPickerChange1: function (e) {
        var that = this;
        that.setData({
            index1: e.detail.value
        });
        var siteId = that.data.objectArray1[e.detail.value].id;
        util.request(cfg.intervalUrl, { siteId: siteId }, 'GET',
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
    bindPickerChange2: function (e) {
        var that = this;
        that.setData({
            index2: e.detail.value
        });
        var siteId = that.data.objectArray2[e.detail.value].id;
        // util.request(cfg.illegalTrafficUrl, { start: 0, limit: 9999, siteId: siteId },
        //     function (data) {
        //         that.setData({ detailData: data.rows });
        //     }
        // );
        that.fetchTrafficList(siteId);
    },

    /**
     * 查询相关超载情况
     */
    getTraffics: function(pageindex, callbackcount, siteId, callback){
        if(siteId == '' || siteId == undefined){
            util.request(cfg.illegalTrafficUrl, { start: pageindex, limit: callbackcount}, 'GET',
                function (data) {
                    //that3.setData({ detailData: data.rows });
                    callback(data.rows);
                }
            )
        }else{
            util.request(cfg.illegalTrafficUrl, { start: pageindex, limit: callbackcount, siteId }, 'GET',
                function (data) {
                    //that3.setData({ detailData: data.rows });
                    callback(data.rows);
                }
            )
        }
    },

    fetchTrafficList: function(siteId){
        var that = this;
        var pageNum = that.data.searchPageNum;//把第几次加载次数作为参数  
        var count = that.data.callbackcount; //返回数据的个数  
        that.getTraffics(pageNum, count,siteId, function(data){
            console.log("traffic"+data);
            //判断是否有数据
            if(data.length > 0){
                var retList = [];
                //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加 
                that.data.isFromSearch ? retList = data : retList = that.data.trafficList.concat(data);
                that.setData({
                    trafficList: retList, //获取数据数组  
                    searchLoading: true   //把"上拉加载"的变量设为false，显示  
                });    
            }else{
                that.setData({
                    searchLoadingComplete: true, //把“没有数据”设为true，显示  
                    searchLoading: false  //把"上拉加载"的变量设为false，隐藏  
                });  
            }
        });
    },
    //滚动到底部触发事件  
    searchScrollLower: function () {
        var that = this;
        if (that.data.searchLoading && !that.data.searchLoadingComplete) {
            that.setData({
                searchPageNum: that.data.searchPageNum + 1,  //每次触发上拉事件，把searchPageNum+1  
                isFromSearch: false  //触发到上拉事件，把isFromSearch设为为false  
            });
            that.fetchTrafficList();
        }

    }
});

