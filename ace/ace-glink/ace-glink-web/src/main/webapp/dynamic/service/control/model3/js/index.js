var timer;
$(window).resize(function () {   //当浏览器大小变化时
	clearTimeout(timer);
	timer=setTimeout(function(){
		window.location.reload();
	},500)
});

$(function () {
    initPage();
    initTask();
    initControl();
    initTimeing();
    initJuicerMethod();
    $('.btns').on('click','.btn',changePage);
});

/*切换页面*/
function changePage() {
    var that=$(this);
    var type=that.data('type');
    $('.modals').hide();
    $('.modals.'+type).show();
}


/*****************************************总控设置Start***********************************************/
var map = {};
function  initControl() {
    getYearCronList();
    $('#months').on('click','li',changeMonth);
    $('.Control .right .heads').on('click','button',checkType);
}


function  getYearCronList(){
    var date = new Date;
    var month = date.getMonth();
    var m = month + 1;
    var url = contextPath + "/generalYearCron/syncData";
    $.getJSON(url, params, function (rst) {
        console.log(rst.value);
        if (rst.status == 0) {
            var data = rst.value;
            map=data;
            $('#months li:eq('+month+')').addClass('active');
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
    var that=$(this);
    var type=that.data('type');
    $("input:radio[value='"+type+"']").prop("checked", "checked");
    stroyMap();
}
/*储存map*/
function stroyMap(){
    var mid ='m'+$("#months li.active").data("id");
    var checkValue = '';
    $('input:radio:checked').each(function () {
        checkValue += $(this).val();
    });
    map[mid]=checkValue;
}

function renderMonths(m) {
    var datas={};
    datas.m=map['m'+m];
    datas.d=parseInt(mGetDate(m));
    render($("#page-YearCronlist"), datas, "tpl-YearCronlist");
    var date = new Date;
    var month = date.getMonth();
    var mo=m-1;
    if(month==mo){
        var day = date.getDate();
        $('#s' + day).addClass('dayactive');
    }
}

//提交总控数据
function postList() {
    stroyMap();
    var url=contextPath + "/generalYearCron/updateGeneralCtrlCron";
    var data={
        jsons: JSON.stringify(map)
    }
    $.post(url,data,function(result){
        if (result.Status == "ok") {
            alert("设置成功");
            getYearCronList();
        } else {
            alert(result.errorMessage);
        }
    })
}



/*****************************************总控设置End***********************************************/

/*****************************************任务管理Start***********************************************/
var taskParams={
    start:0,
    limit:21
}

/*任务初始化管理*/
function initTask() {
    $("#areaNodeID").combotree({
        onChange: function (newValue, oldValue) {
            getTaskList("areaNodeID", newValue);
        }
    });
    initPageTask();
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
            getTaskList();
        }
    });
}

/*ajax获取数据列表*/
function getTaskList(key, value) {
    if(!key){
        taskParams[key] = value;
    }
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
    var url=contextPath + "/seAreaTask/exeTask";
    var data={
        areaNodeID: areaNodeID,
        taskNo: taskNo
    }
    $.post(url,data,function(rst){
        if (rst.status == 0) {
            alert(rst.errorMessage);
            getTaskList();
        }
    })
}
/*****************************************任务管理End***********************************************/


/*****************************************定时设置Start***********************************************/
var params={
    start:0,
    limit:15
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
                render('#page-update', data, 'tpl-monthslist');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
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
    Timermap['id'] = $('input[name="id"]').val();
    Timermap['timerID'] = $('input[name="timerID"]').val();
    Timermap['timerName'] = $('input[name="timerNames"]').val();
    Timermap['timerEnable'] = $('input[name="timerEnable"]').val();
    Timermap['startTime'] = $('input[name="startTime"]').val();
    Timermap['taskNo'] = $('input[name="taskNo"]').val();
    Timermap['MonthEnable'] = MonthEnable;
    Timermap['WeekEnable'] = WeekEnable;
    Timermap['DayEnable'] = DayEnable;
    console.log(Timermap);
}

function TimerUpdate() {
    checkData();
    $.ajax({
        url: contextPath + "/seTimerData/updateTimer",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(Timermap)
        },
        success: function (result) {
            if (result.Status == "ok") {
                alert("设置成功");
                getPageList();
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });

}
/*****************************************定时设置End***********************************************/


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




function mGetDate(m){
    var date = new Date();
    var year = date.getFullYear();
    var d = new Date(year, m, 0);
    return d.getDate();
}





