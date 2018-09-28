var dateTimePicker = require('../../util/dateTimePicker.js');

Page({
    data: {
        topNum: 0,
        dateTimeArray: null,
        dateTime: null,
        startYear: 2000,
        endYear: 2050,
        stepNum:1,
    },
    onLoad() {
        var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
        // 精确到分的处理，将数组的秒去掉
        var lastArray = obj.dateTimeArray.pop();
        var lastTime = obj.dateTime.pop();

        this.setData({
            dateTimeArray: obj.dateTimeArray,
            dateTime: obj.dateTime
        });
    },
    changeDateTime(e) {
        this.setData({ dateTime1: e.detail.value });
    },
    changeDateTimeColumn(e) {
        var arr = this.data.dateTime, dateArr = this.data.dateTimeArray;

        arr[e.detail.column] = e.detail.value;
        dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

        this.setData({
            dateTimeArray: dateArr
        });
    },

    nextOne:function(){
        var that=this;
        this.setData({ stepNum: ++that.data.stepNum,
            topNum:0,});
    },

    previousOne:function() {
        var that = this;
        this.setData({ stepNum: --that.data.stepNum, topNum:  0,});
    },
})