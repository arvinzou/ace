var dateTimePicker = require('../../util/dateTimePicker.js');
import WxValidate from '../../util/WxValidate';
var util = require("../../util/util.js");
var cfg = require("../../config.js");


const App = getApp()

Page({
    data: {
        dateTimeArray: null,
        startDate: null,
        endDate: null,
        dendline: null,
        startYear: 2000,
        endYear: 2050,
        stepNum: 1,
        setDendline: true,
        modeNum: 0,
        purposeNum: 0,
        category: '',
        form: {
            name: '',
            title: '',
            location: '',
            mode: '',
            purpose: '',
            dendline: '',
            startDate: '',
            endDate: '',
            parterNum: '',
            coverUrl: '',
        },
        index: 0,
    },

    bindPickerChange: function(e) {
        let that = this;
        that.setData({
            index: e.detail.value,
            category: parseInt(e.detail.value) + 1
        })
    },

    onLoad(e) {
        let that = this;
        let cat = e.category;
        that.initValidate();
        let name = util.getSysUser().societyOrg.orgName;
        var obj = dateTimePicker.dateTimePicker(that.data.startYear, that.data.endYear);
        // 精确到分的处理，将数组的秒去掉
        var lastArray = obj.dateTimeArray.pop();
        var lastTime = obj.dateTime.pop();
        var sysUser = util.getSysUser();
        let array = ['公益活动', '普及活动', '创意活动'];
        let objectArray = [{
                id: 1,
                name: '公益活动'
            },
            {
                id: 2,
                name: '普及活动'
            },
            {
                id: 3,
                name: '创意活动'
            },

        ];
        if (sysUser.societyOrg.orgType == 1) {
            array.push('党建活动');
            objectArray.push({
                id: 4,
                name: '党建活动'
            });
        }
        that.getCoin(cat);
        that.setData({
            dateTimeArray: obj.dateTimeArray,
            startDate: obj.dateTime,
            endDate: obj.dateTime,
            dendline: obj.dateTime,
            category: cat,
            array:array,
            objectArray: objectArray,
            form: {
                name: name
            },
            index: cat - 1
        });
    },
    getCoin: function(cat) {
        let that = this;
        util.request(cfg.getCoin, {
            id: cat
        }, function(rst) {
            if (rst.status == 0) {
                that.setData({
                    host: rst.data.host
                })
            }
        })
    },

    changeDateTime(e) {
        let name = e.currentTarget.dataset.name;
        let temp = {};
        temp[name] = e.detail.value,
            this.setData(temp);
    },
    changeDateTimeColumn(e) {
        console.log(e);
        let name = e.currentTarget.dataset.name;
        var arr = this.data[name],
            dateArr = this.data.dateTimeArray;

        arr[e.detail.column] = e.detail.value;
        dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

        this.setData({
            dateTimeArray: dateArr
        });
    },
    callphone: function(e) {
        console.log(e);
        let data = e.currentTarget.dataset
        let pmobile = data.mobile;
        wx.makePhoneCall({
            phoneNumber: pmobile
        })
    },

    nextOne: function() {
        var that = this;
        this.setData({
            stepNum: ++that.data.stepNum,
        });
        wx.pageScrollTo({
            scrollTop: 0
        })
    },

    previousOne: function() {
        var that = this;
        this.setData({
            stepNum: --that.data.stepNum,
        });
        wx.pageScrollTo({
            scrollTop: 0
        })
    },

    showModal(error) {
        wx.showModal({
            content: error.msg,
            showCancel: false,
        })
    },

    colseThis: function() {
        wx.navigateBack({});
        return;
    },


    formSubmit(e) {
        let that = this;
        const params = e.detail.value
        const startDate = that.formatDT(params.startDate);
        const endDate = that.formatDT(params.endDate);
        const dendline = that.formatDT(params.dendline ? params.dendline : params.startDate);
        const clazz = params.clazz;
        params.coverUrl = that.data.form.coverUrl;
        params.category = that.data.category;
        if (that.data.category == 4 && !clazz) {
            wx.showModal({
                title: '提示',
                content: '请填写活动期数',
            });
            return;
        }
        if (!(startDate < endDate && dendline <= startDate)) {
            wx.showModal({
                title: '提示',
                content: '设置时间错误'
            });
            return;
        }
        params.startDate = startDate;
        params.endDate = endDate;
        params.dendline = dendline;
        console.log(params);
        if (!that.WxValidate.checkForm(params)) {
            const error = that.WxValidate.errorList[0]
            that.showModal(error)
            return false
        }
        util.request(cfg.insertActivity, {
                jsons: JSON.stringify(params)
            },
            function(data) {
                if (data.status == 0) {
                    that.nextOne();
                    return;
                }
                that.showModal({
                    msg: data.errorMessage,
                })
            }
        );
    },

    initValidate() {
        const rules = {
            title: {
                required: true,
                minlength: 1,
                maxlength: 34
            },
            location: {
                required: true,
                minlength: 1,
                maxlength: 20
            },
            mode: {
                required: true,
                minlength: 1,
                maxlength: 300
            },
            purpose: {
                required: true,
                minlength: 1,
                maxlength: 300
            },
            parterNum: {
                required: true,
                digits: true
            },
            clazz: {
                required: false,
                digits: true,

            },
            coverUrl: {
                required: true,
            }
        }
        const messages = {
            title: {
                required: '请填写活动名称',
                minlength: '活动名称最少要5个字',
                maxlength: '输入活动名称太长'
            },
            location: {
                required: '请填写活动地点',
                minlength: '填写活动地点最少5个字',
                maxlength: '填写活动地点最多20个字'
            },
            mode: {
                required: '请填写活动方式',
                minlength: '填写的活动方式最少10个字',
                maxlength: '填写的活动方式字数太多'
            },
            purpose: {
                required: '请填写活动目的',
                minlength: '请填写活动目的至少10个字',
                maxlength: '填写的活动目的太多'
            },
            parterNum: {
                required: '请填写参与人数',
                digits: '参与人数请填写数字'
            },
            clazz: {
                required: '请填写活动期数',
                digits: '活动期数请填写数字'
            },
            coverUrl: {
                required: '没有上传封面',
            },
        }
        this.WxValidate = new WxValidate(rules, messages)
    },
    formatDT(arr) {
        console.log(arr);
        console.log('20' + this.FN(arr[0]) + '-' + this.FN(arr[1] + 1) + '-' + this.FN(arr[2] + 1) + ' ' + this.FN(arr[3]) + ':' + this.FN(arr[4]) + ':00');
        return '20' + this.FN(arr[0]) + '-' + this.FN(arr[1] + 1) + '-' + this.FN(arr[2] + 1) + ' ' + this.FN(arr[3]) + ':' + this.FN(arr[4]) + ':00';
    },

    FN(num) {
        return num >= 10 ? num : '0' + num;
    },

    switchChange(e) {
        this.setData({
            setDendline: e.detail.value
        })
    },
    checkFontNum(e) {
        let name = e.currentTarget.dataset.name;
        let temp = {};
        temp[name] = e.detail.cursor,
            this.setData(temp);
    },
    //添加图爿
    addCover() {
        var that = this;
        wx.showActionSheet({
            itemList: ['打开照相', '选取现有的'],
            itemColor: '#007aff',
            success(res) {
                if (res.tapIndex === 0) {
                    wx.chooseImage({
                        sourceType: ['camera'],
                        success(res) {
                            that.uploadFileFun(res.tempFilePaths[0]);
                        }
                    })
                } else if (res.tapIndex === 1) {
                    wx.chooseImage({
                        count: 1, // 设置最多三张
                        sizeType: ['original', 'compressed'],
                        sourceType: ['album', 'camera'],
                        success(res) {
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
    // 上传文件方法
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
                    ["form.coverUrl"]: url
                })
            },
            fail: function(res) {
                return null
            },
        })
    }
})