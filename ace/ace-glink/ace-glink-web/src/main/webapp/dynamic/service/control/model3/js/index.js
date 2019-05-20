var timer;
$(window).resize(function () {   //当浏览器大小变化时
    clearTimeout(timer);
    timer = setTimeout(function () {
        window.location.reload();
    }, 500)
});

$(function () {
    initPage();
    initTask();
    initControl();
    initTimeing();
    initScenario();
    initJuicerMethod();
    $('.btns').on('click', '.btn', changePage);
    $(' .modal .modal-content').on('click', '.modal-close', closeModal);
});

function closeModal() {
    $(this).parents().find('.modal').hide();
}

/*切换页面*/
function changePage() {
    var that = $(this);
    var type = that.data('type');
    var flag = judgeUpdate();
    if (flag == true) {
        $('.modals').hide();
        $('.modals.' + type).show();
    }
}


/*****************************************总控设置Start***********************************************/
var map = {};
var oldMap = {};

function initControl() {
    getYearCronList();
    $('#months').on('click', 'li', changeMonth);
    $('.Control .right .heads').on('click', 'button', checkType);
}

function getYearCronList() {
    var date = new Date;
    var month = date.getMonth();
    var m = month + 1;
    var url = contextPath + "/generalYearCron/syncData";
    $.getJSON(url, params, function (rst) {
        console.log(rst.value);
        if (rst.status == 0) {
            var data = rst.value;
            oldMap = data;
            map = data;
            $('#months li:eq(' + month + ')').addClass('active');
            renderMonths(m);
        }
    });
}

/*点击切换月份*/
function changeMonth() {
    stroyMap();
    $(this).siblings('li').removeClass('active');  // 删除其他兄弟元素的样式
    $(this).addClass('active'); // 添加当前元素的样式
    var m = $(this).data("id");
    renderMonths(m);
}

/*全部设置类型*/
function checkType() {
    var that = $(this);
    var type = that.data('type');
    $("input:radio[value='" + type + "']").prop("checked", "checked");
    stroyMap();
}

/*储存map*/
function stroyMap() {
    var mid = 'm' + $("#months li.active").data("id");
    var checkValue = '';
    $('input:radio:checked').each(function () {
        checkValue += $(this).val();
    });
    map[mid] = checkValue;
}

function renderMonths(m) {
    var datas = {};
    datas.m = map['m' + m];
    datas.d = parseInt(mGetDate(m));
    render($("#page-YearCronlist"), datas, "tpl-YearCronlist");
    var date = new Date;
    var month = date.getMonth();
    var mo = m - 1;
    if (month == mo) {
        var day = date.getDate();
        $('#s' + day).addClass('dayactive');
    }
}

//提交总控数据
function postList() {
    if (!confirm("确认执行吗？")) {
        return;
    }
    stroyMap();
    var url = contextPath + "/generalYearCron/updateGeneralCtrlCron";
    var data = {
        jsons: JSON.stringify(map)
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            // /alert("设置成功");
            alert(result.errorMessage);
            getYearCronList();
        } else {
            alert(result.errorMessage);
        }
    })
}

//深度克隆
function deepClone(obj) {
    var result = {},
        oClass = isClass(obj);
    for (key in obj) {
        var copy = obj[key];
        if (isClass(copy) == "Object") {
            result[key] = arguments.callee(copy);
        } else if (isClass(copy) == "Array") {
            result[key] = arguments.callee(copy);
        } else {
            result[key] = obj[key];
        }
    }
    return result;
}

function isClass(o) {
    if (o === null) return "Null";
    if (o === undefined) return "Undefined";
    return Object.prototype.toString.call(o).slice(8, -1);
}

//判断两个对象是否相同
function diff(obj1, obj2) {
    var o1 = obj1 instanceof Object;
    var o2 = obj2 instanceof Object;
    if (!o1 || !o2) {/*  判断不是对象  */
        return obj1 === obj2;
    }

    if (Object.keys(obj1).length !== Object.keys(obj2).length) {
        return false;
        //Object.keys() 返回一个由对象的自身可枚举属性(key值)组成的数组
    }

    for (var attr in obj1) {
        var t1 = obj1[attr] instanceof Object;
        var t2 = obj2[attr] instanceof Object;
        if (t1 && t2) {
            return diff(obj1[attr], obj2[attr]);
        } else if (obj1[attr] !== obj2[attr]) {
            return false;
        }
    }
    return true;
}

//判断总控数据是否修改，修改就弹出执行提示框
function judgeUpdate() {
    //克隆一个数组
    var arr = oldMap;
    var oNew = deepClone(arr);
    stroyMap();
    var flag = diff(oNew, map);
    console.log(flag);
    if (flag == false) {
        if (!confirm("当前数据已经修改确认执行吗？")) {
            getYearCronList();
            return true;
        }
        stroyMap();
        var url = contextPath + "/generalYearCron/updateGeneralCtrlCron";
        var data = {
            jsons: JSON.stringify(map)
        }
        $.post(url, data, function (result) {
            if (result.status == 0) {
                // /alert("设置成功");
                alert(result.errorMessage);
                getYearCronList();
            } else {
                alert(result.errorMessage);
            }
        })
        return false;
    } else {
        return true;
    }
    return true;
}


/*****************************************总控设置End***********************************************/

/*****************************************任务管理Start***********************************************/
var taskParams = {
    start: 0,
    limit: 21
}

/*任务初始化管理*/
function initTask() {

    //初始化组件
    initPageTask();
    //加载任务树&默认选择第一节点
    $("#taskAreaNode").combotree({
        url: contextPath + '/seProjectArea/selectTreeList',
        method: 'get',
        animate: true,
        lines: true,
        label: 'Select Node:',
        labelPosition: 'top',
        onChange: function (newValue, oldValue) {
            //select onChange
            getTaskList("areaNodeID", newValue);
        },
        onSelect: function () {
            console.log(" alert('selcet!')");
        },
        onClick: function () {
            console.log(" alert('click')")
        },
        onLoadSuccess: function (node, data) {
            $("#taskAreaNode").combotree('setValue', data[0].id);
        }
    });

}

/*初始化分页器*/
function initPageTask() {
    $.jqPaginator('#pagination2', {
        totalCounts: 1,
        pageSize: taskParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            taskParams['start'] = (num - 1) * taskParams.limit;
            taskParams['initType'] = type;
            //区域筛选后，才能加载数据列表
            if ($.isEmptyObject(taskParams.areaNodeID)) {
                console.log("未加载区域数据！~");
            } else {
                getPageList();
            }
        }
    });
}

/*ajax获取数据列表*/
function getTaskList(key, value) {
    taskParams[key] = value;
    console.log("taskParams=" + JSON.stringify(taskParams));

    var url = contextPath + "/seAreaTask/findSeAreaTaskList";
    $.getJSON(url, taskParams, function (rst) {
        if (rst.status == 0) {
            if (taskParams.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#taskList"), rst.rows, "tpl-taskList");
        }
    });
}

function executeTask(areaNodeID, taskNo) {
    if (!confirm("确认执行该任务么？")) {
        return;
    }
    var url = contextPath + "/seAreaTask/exeTask";
    var data = {
        areaNodeID: areaNodeID,
        taskNo: taskNo
    }
    $.post(url, data, function (rst) {
        if (rst.status == 0) {
            alert(rst.errorMessage);
            getTaskList();
        }
    })
}

/*****************************************任务管理End***********************************************/


/*****************************************定时设置Start***********************************************/
var params = {
    start: 0,
    limit: 15
}

function initTimeing() {
    initPage();
    $('.Timing .submit').click(searchTiming);
    $('.unit').on('click', '.piece', changeList);

}

function searchTiming() {
    params['initType'] = 'init';
    getPageList();
}


/*初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
    //全部有效
    $("#AllEffective").click(function () {
        var type = $('.piece.active').data('type');
        if (type == 'monthData') {
            $("input[name='checkMonth']").prop("checked", true);
            $("input[name='checkMonth']").prop('value', '1');
        } else if (type == 'weekData') {
            $("input[name='checkWeek']").prop("checked", true);
            $("input[name='checkWeek']").prop('value', '1');
        } else if (type == 'dayData') {
            $("input[name='checkDay']").prop("checked", true);
            $("input[name='checkDay']").prop('value', '1');
        }
    });
    //全部无效
    $("#AllInvalid").click(function () {
        var type = $('.piece.active').data('type');
        if (type == 'monthData') {
            $("input:checkbox[name='checkMonth']").prop("checked", false);
            $("input[name='checkMonth']").prop('value', '0');
        } else if (type == 'weekData') {
            $("input:checkbox[name='checkWeek']").prop("checked", false);
            $("input[name='checkWeek']").prop('value', '0');
        } else if (type == 'dayData') {
            $("input:checkbox[name='checkDay']").prop("checked", false);
            $("input[name='checkDay']").prop('value', '0');
        }
    });
}

/*定时任务数据加载表格数据*/
function getPageList() {
    var val = $('.Timing .inputGroup .timerName').val();
    params['timerName'] = val;
    var url = contextPath + "/seTimerData/findSeTimerDataList";
    $.getJSON(url, params, function (rst) {
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#page-list"), rst.rows, "tpl-list");
        }
    })
}

/*切换页面*/
function changeList() {
    var that = $(this);
    $(this).siblings('div').removeClass('active');
    $(this).addClass('active');
    var type = that.data('type');
    changeDo(type);
}

function changeDo(type) {
    console.log(type);
    $('.list').hide();
    $('.list.' + type).show();
}


//查询更新定时设置数据
function selectTimerDate(id) {
    $(".timing-modal").show();
    $.ajax({
        url: contextPath + "/seTimerData/selectSeTimerDataByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            if (result.status == 0) {
                var data = {};
                console.log(result);
                data['o'] = result.value;
                data['monthList'] = result.value.monthList;
                data['weekList'] = result.value.weekList;
                data['dayList'] = result.value.dayList;
                Timermap = result.value;
                render('#page-update', data, 'tpl-monthslist');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            // stopLoad();
            alert("对不起出错了！");
        }
    });
}


var Timermap = {};

//选中的值
function checkData() {

    var MonthEnable = {};
    var WeekEnable = {};
    var DayEnable = {};
    $('input[name="checkMonth"]').each(function (i) {
        i = i + 1;
        var m = "m" + i;
        MonthEnable[m] = $(this).val();
    });
    $('input[name="checkWeek"]').each(function (i) {
        i = i + 1;
        var m = "w" + i;
        WeekEnable[m] = $(this).val();
    });
    $('input[name="checkDay"]').each(function (i) {
        i = i + 1;
        var m = "d" + i;
        DayEnable[m] = $(this).val();
    });
    Timermap['MonthEnable'] = MonthEnable;
    Timermap['WeekEnable'] = WeekEnable;
    Timermap['DayEnable'] = DayEnable;
    console.log(Timermap);
}

function TimerUpdate() {
    if (!confirm("确认执行吗？")) {
        return;
    }
    checkData();
    $.ajax({
        url: contextPath + "/seTimerData/updateTimer",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(Timermap)
        },
        success: function (result) {
            if (result.status == 0) {
                //alert("设置成功");
                alert(result.errorMessage);
                getPageList();
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            //  stopLoad();
            alert("对不起出错了！");
        }
    });

}

/*****************************************定时设置End***********************************************/

/*****************************************场景执行Start***********************************************/

var scenarioParams = {
    start: 0,
    limit: 21
}

/*任务初始化管理*/
function initScenario() {
    $('#scenarioList').on('click', 'button', selectPreset);
    $('.scenario-modal').on('click', '.submit', searchPreset);
    $('.scenario-modal #presets').on('click', 'li', setScenario);
    $("#areaNodeID1").combotree({
        onChange: function (newValue, oldValue) {
            getScenarioList("areaNodeID", newValue);
        }
    });
    initPageScenario();
}

function setScenario() {
    var that = $(this);
    scenarioPostData.presetNo = that.data('presetno');
    var url = contextPath + '/seCustomArea/executePreset';
    $.post(url, scenarioPostData, function (rst) {

        console.log(rst);
    })
}

/*初始化分页器*/
function initPageScenario() {
    $.jqPaginator('#pagination3', {
        totalCounts: 1,
        pageSize: scenarioParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            scenarioParams['start'] = (num - 1) * scenarioParams.limit;
            scenarioParams['initType'] = type;
            getScenarioList();
        }
    });

    $.jqPaginator('#pagination4', {
        totalCounts: 1,
        pageSize: scenarioParams.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            presetMap['start'] = (num - 1) * scenarioParams.limit;
            presetMap['initType'] = type;
            if (type != 'init') {
                selectPreset();
            }
        }
    });
}


/*ajax获取数据列表*/
function getScenarioList(key, value) {
    if (key) {
        scenarioParams[key] = value;
    }
    var url = contextPath + "/seCustomArea/findSeCustomAreaList";
    $.getJSON(url, scenarioParams, function (rst) {
        if (rst.status == 0) {
            if (scenarioParams.initType == "init") {
                $('#pagination3').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#scenarioList"), rst.rows, "tpl-scenarioList");
        }
    });
}

var presetMap = {
    start: 0,
    limit: 10
};
var scenarioPostData = {};

function selectPreset() {
    var that = $(this);
    scenarioPostData.areaNo = that.data('areano');
    scenarioPostData.areaNodeID = that.data('areanodeid');
    var val = $('.scenario-modal  .presetName').val();
    presetMap['presetName'] = val;
    $('.scenario-modal').show();
    var url = contextPath + "/sePresetData/findSePresetDataList";
    $.getJSON(url, presetMap, function (rst) {
        if (rst.status == 0) {
            if (presetMap.initType == "init") {
                $('#pagination4').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#presets"), rst.rows, "tpl-presets");
        }
    });
}

function searchPreset() {
    presetMap.start = 0;
    selectPreset();
}


/*****************************************场景执行End***********************************************/


/*添加渲染*/
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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('parseExeState', parseExeState);
}

/**
 * 状态解析
 */
function parseExeState(val) {
    switch (val) {
        case 'ok':
            return "已执行";
        case 'error':
            return "未执行";
        default:
            return "未执行";
    }
}


/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case 0:
            return "无效";
        case 1:
            return "有效";
        default:
            return "0";
    }
}


// //加载每月数据
// function setParams(m) {
//     var url = contextPath + "/generalYearCron/syncData";
//     $.getJSON(url, params, function (rst) {
//         if (rst.status == 0) {
//             var d = rst.value;
//             var data={};
//             data.m=d['m'+m];
//             data.d=parseInt(mGetDate(m));
//             render($("#page-YearCronlist"), data, "tpl-YearCronlist");
//         }
//     });
// }


function mGetDate(m) {
    var date = new Date();
    var year = date.getFullYear();
    var d = new Date(year, m, 0);
    return d.getDate();
}


