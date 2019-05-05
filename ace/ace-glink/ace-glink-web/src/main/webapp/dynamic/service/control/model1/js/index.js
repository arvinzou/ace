var timer;
$(window).resize(function () {   //当浏览器大小变化时
    clearTimeout(timer);
    timer = setTimeout(function () {
        window.location.reload();
    }, 500)
});
var params = {
    start: 0,
    limit: 100
}

$(function () {
    initPage();
    initsceneControl();
    initJuicerMethod();
    $('.setTime-modal .unit').on('click', '.piece', changePattern);
});


var clearData = ['isWeek', 'isMonth', 'weeks', 'months', 'startDate', 'stopDate', 'specialDate'];


/**juicer自定义函数*/
function initJuicerMethod() {
    juicer.register('isChecked', isChecked);
    juicer.register('formatObject', formatObject);
}

/**
 * 状态解析
 */
function isChecked(val, idex) {
    if (!val) {
        return '';
    }
    var i = val.split(',');
    for (var x = 0; x < i.length; x++) {
        if (idex == i[x]) {
            return 'checked'
        }
    }
    return '';
}

function formatObject(data) {
    for (var item in data) {
        if (item.indexOf('Date') > -1 || item.indexOf('Time') > -1) {
            data[item] = data[item].substring(0, 10).split('-').join('');
        }
    }
    data.strategy = data.code;
    data.area = data.areaCode;
    return JSON.stringify(data);
}

/*++++++++++++++++++++++++++++++++++++++++++initPage Start++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
var nowpagetype = 'sceneControl';

function initPage() {
    /*菜单显示*/
    $('.userInfo>.up').on("click", showMenu);
    $('.btns').on('click', '.btn', changePart);
    $(' .modal .modal-content').on('click', '.modal-close', closeModal);
    initInputDate();
    $('#strategyInfo').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                if (params[obj.name]) {
                    params[obj.name] = params[obj.name] + ',' + obj.value;
                } else {
                    params[obj.name] = obj.value;
                }
            });
            postParam(params);
            return false;
        }
    });
    $('#setS').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                if (params[obj.name]) {
                    params[obj.name] = params[obj.name] + ',' + obj.value;
                } else {
                    params[obj.name] = obj.value;
                }
            });
            postTimer(params);
            return false;
        }
    });
}


function changePart() {
    var that = $(this);
    var type = that.data('type');
    if (type == nowpagetype) {
        return;
    }
    nowpagetype = type;
    $('.content').hide();
    $('.content.' + type).show();
    window['init' + type]();
}

/*菜单显示*/
function showMenu() {
    if ($('.menu-wrap').css("display") == "none") {
        $('.menu-wrap').show();
    } else {
        $('.menu-wrap').hide();
    }
}

function closeModal() {
    $(this).parents().find('.modal').hide();
}

function initInputDate() {
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
    var checkin = $('input[name=startTime]').fdatepicker({
        format: 'yyyy-mm-dd',
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
            var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate() + 1);
            checkout.update(newDate);
        }
        checkin.hide();
        $('input[name=stopTime]')[0].focus();
    }).data('datepicker');
    var checkout = $('input[name=stopTime]').fdatepicker({
        format: 'yyyy-mm-dd',
        onRender: function (date) {
            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
        }
    }).on('changeDate', function (ev) {
        checkout.hide();
    }).data('datepicker');
}

/*++++++++++++++++++++++++++++++++++++++++++initPage end++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


/*++++++++++++++++++++++++++++++++++++++++++initsceneControl Start++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

var html='<div class="default">\n' +
    '                    <img src="img/default.png" alt="">\n' +
    '                    <p>当前暂无场景，请先选择站点</p>\n' +
    '                </div>'

function initsceneControl() {
    getStations();
    requestSenceStatusData();
    $('#check').on('click', 'li', checkStation);
    $('#checked').on('click', 'li', removeStation);
    $('.sceneControl .btns').on('click', '.colorful img', controllPlay);
    $('#s6').change(switchNow);
}

function switchNow() {
    var that = $(this);
    if (that.is(':checked')) {
        submitSenceStatusData('sceneControlState', 1);
    } else {
        submitSenceStatusData('sceneControlState', 2);
    }
}

function controllPlay() {
    var that = $(this);
    var state = that.data('state');
    submitSenceStatusData('playbackStatus', state);
    requestSenceStatusData();
}


/**
 * 提交状态
 * */
function submitSenceStatusData(str, num) {
    var url = contextPath + "/pagePortal/updatePagePortalData";
    var data = {
        key: str,
        val: num
    };
    $.ajaxSettings.async = false;
    $.post(url, data, function (rst) {

    })
    $.ajaxSettings.async = true;
}


/**
 * 请求场景状态数据
 */
function requestSenceStatusData() {
    $.ajax({
        url: contextPath + "/pagePortal/findList",
        type: "post",
        async: false,
        success: function (res) {
            console.log(res);
            if (res.length > 0) {
                initSenceStatusData(res);   //初始化场景状态数据
            } else {
                alert('暂无数据')
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}


/**
 * 初始化场景状态数据
 */
function initSenceStatusData(arr) {
    $.each(arr, function (i, item) {
        console.log(item);
        if (item.itemKey == "sceneControlState") {
            if (item.itemValue == 1) { // 1开，2关
                $('#s6').attr('checked', true);
            } else {
                $('#s6').attr('checked', false);
            }
        }
        if (item.itemKey == "playbackStatus") {  // 1播放， 2暂停
            if (item.itemValue == 1) {
                playNow();
            } else {
                pauseNow();
            }

        }
    });
}


function playNow() {
    $('#pause').addClass('colorful');
    $('#play').removeClass('colorful');
}

function pauseNow() {
    $('#play').addClass('colorful');
    $('#pause').removeClass('colorful');
}


/*获取所有站点*/
function getStations() {
    var url = contextPath + "/topStation/findTopStationList";
    $.get(url, params, function (rst) {
        if (rst.status == 0) {
            var data = rst.rows;
            render('#check', data, 'tpl-check')
        }
    });
}

/*点击选择站点*/
function checkStation() {
    var that = $(this);
    var code = that.data('code');
    var name = that.data('name');
    if (!code) {
        return
    }
    startLoad();
    var url = contextPath + "/animaLnk/findAnimaLnkList";
    var data = {
        stationCode: code
    }
    $.getJSON(url, data, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            that.remove();
            renderadd('#checked', [{code: code, name: name}], 'tpl-checked');
            var data = rst.rows;
            renderadd('#videos', data, 'tpl-videos');
            hasLi();
        }
    })
}

function hasLi() {
    if($('#videos li').length>0){
        $('#videos .default').remove();
    }
}

function nohasLi() {
    if($('#videos li').length<1&&$('#videos .default').length<1){
        $('#videos').append(html);
    }
}


/*删除选择站点*/
function removeStation() {
    var that = $(this);
    var code = that.data('code');
    var name = that.data('name');
    $('#videos .video' + code).remove();
    nohasLi()
    renderadd('#check', [{code: code, name: name}], 'tpl-check');
    that.remove();
}

/*++++++++++++++++++++++++++++++++++++++++++initsceneControl end++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/


/*++++++++++++++++++++++++++++++++++++++++++initstrategyPart end++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
var strategyParams = {
    start: 0,
    limit: 14
}

/*任务初始化管理*/
function initstrategyPart() {
    initPageStrategy();
    $('#strategyList').on('click', 'button', selectPreset);
    $('.strategyPart').on('click', '.addStrategy', addStrategy);
    $('.strategyPart').on('click', '.searchBtn', searchByName);
    $('.scenario-modal #presets').on('click', 'li', setStrategy);

}

function searchByName() {
    strategyParams.start = 0;
    strategyParams.initType = 'init';
    getStrategyList();
}

function addStrategy() {
    showAddStrategyModal({});
}

function editStrategy(id) {
    var url = contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey";
    var data = {
        id: id,
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            showAddStrategyModal(rst.value);
        }
        else {
            alert("失败");
        }
    });
}

function showAddStrategyModal(data) {
    render('#strategyInfo', data, 'tpl-strategyInfo');
    $('.addStrategy-modal').show();
}

/*初始化分页器*/
function initPageStrategy() {
    $.jqPaginator('#pagination3', {
        totalCounts: 1,
        pageSize: strategyParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            strategyParams['start'] = (num - 1) * strategyParams.limit;
            strategyParams['initType'] = type;
            getStrategyList();
        }
    });
}

/*ajax获取数据列表*/
function getStrategyList() {
    var value = $('.strategyPart .strategyName').val();
    strategyParams.name = value;
    var url = contextPath + "/ltStrategy/findLtStrategyList";
    $.getJSON(url, strategyParams, function (rst) {
        if (rst.status == 0) {
            if (strategyParams.initType == "init") {
                $('#pagination3').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#strategyList"), rst.rows, "tpl-strategyList");
        }
    });
}


function postParam(params) {
    var url = '';
    if (params.id) {
        url = contextPath + "/ltStrategy/updateLtStrategy";
    } else {
        url = contextPath + "/ltStrategy/insertLtStrategy";
    }
    var data = {
        jsons: JSON.stringify(params)
    }
    $.post(url, data, function (rst) {
        if (rst.status == 0) {
            getStrategyList();
            $('.modal').hide();
        }
    })
}


var mateData;


function setTimer(id) {
    var url = contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey";
    var data = {
        id: id,
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            mateData = rst.value;
            if (!mateData.pattern) {
                mateData.pattern = '1';
            }
            viewSetTime(mateData);
        }
        else {
            alert("失败");
        }
    });
}


function viewSetTime(data) {
    $('.setTime-modal .piece').removeClass('active');
    $('input[name=startTime]').val('');
    $('input[name=stopTime]').val('');
    switch (data.pattern) {
        case "1":
            if (data.isWeek) {
                var weeks = data.weeks;
                setTimerWeek(weeks);
                $('.setTime-modal .piece.week').addClass('active');
            } else if (data.isMonth) {
                var months = data.months;
                setTimerMonth(months);
                $('.setTime-modal .piece.month').addClass('active');
            } else {
                setTimerWeek();
                $('.setTime-modal .piece.week').addClass('active');
            }
            break;
        case "2":
            $('#inputList').empty();
            $('.setTime-modal .piece.holiday').addClass('active');
            break;
        case "3":
            $('#inputList').empty();
            $('.setTime-modal .piece.event').addClass('active');
            break;
    }
    if (mateData.startTime) {
        $('input[name=startTime]').val(mateData.startTime.substring(0, 10));
        $('input[name=stopTime]').val(mateData.stopTime.substring(0, 10));
    }
    $('.setTime-modal').show();
}

function changePattern() {
    var that = $(this);
    var pattern = that.data('type');
    switch (pattern) {
        case 'week':
            setTimerWeek();
            mateData.pattern = '1';
            break;
        case 'month':
            setTimerMonth();
            mateData.pattern = '1';
            break;
        case 'holiday':
            $('#inputList').empty();
            mateData.pattern = '2';
            break;
        case 'event':
            $('#inputList').empty();
            mateData.pattern = '3';
            break;
    }
    that.siblings().removeClass("active");
    that.addClass("active");
}

function setTimerWeek(weeks) {
    var daysShort = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];
    var data = {};
    data.daysShort = daysShort;
    data.weeks = weeks
    render('#inputList', data, 'tpl-weeks');
}

function setTimerMonth(mons) {
    var months = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];
    var data = {};
    data.months = months;
    data.mons = mons;
    render('#inputList', data, 'tpl-months');
}

function postTimer(param) {
    clearParam();
    for (var item in param) {
        if (item == 'weeks') {
            mateData.isWeek = 1;
        }
        if (item == 'months') {
            mateData.isMonth = 1;
        }
        mateData[item] = param[item];
    }

    if (mateData.pattern == '2') {
        mateData.startDate = mateData.startTime;
        mateData.stopDate = mateData.stopTime;
    }
    if (mateData.pattern == '3') {
        mateData.specialDate = mateData.startTime;
    }
    var url = contextPath + "/ltStrategy/updateLtStrategyVo";
    var data = {
        jsons: JSON.stringify(mateData)
    }
    $.post(url, data, function (rst) {
        if (rst.status == 0) {
            getStrategyList();
            $('.modal').hide();
        }
    })
}

function clearParam() {
    for (var item in clearData) {
        mateData[clearData[item]] = '';
    }
}


var jsonData = '';

function selectPreset(data) {
    jsonData = data;
    $('.scenario-modal').show();
    var url = contextPath + "/ltStrategy/strategysDetail";
    $.getJSON(url, function (rst) {
        if (rst.status == 0) {
            render($("#presets"), rst.value.data, "tpl-presets");
        }
    });
}

function setStrategy() {
    var that = $(this);
    var strategyNum = that.data('strategynum');
    if (!strategyNum) {
        return false;
    }
    var i = JSON.parse(jsonData);
    i.strategy = strategyNum;
    var data = {
        jsons: JSON.stringify(i)
    }
    var url = contextPath + "/ltStrategy/lightStrategy";
    $.post(url, data, function (rst) {
        var r = JSON.parse(rst.value)
        if (r.code == '200') {
            $('.modal').hide();
            alert("下发成功");
        }
        else{
            alert("出现错误");
        }
    })
}


/*++++++++++++++++++++++++++++++++++++++++++initstrategyPart end++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

function renderadd(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).append(html);
}


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}


/*************************************************************************************************************/





