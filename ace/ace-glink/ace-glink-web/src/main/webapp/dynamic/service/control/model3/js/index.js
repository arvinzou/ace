var timer;
$(window).resize(function () {   //当浏览器大小变化时
	clearTimeout(timer);
	timer=setTimeout(function(){
		window.location.reload();
	},500)
});
var params={
	start:0,
	limit:100
}

$(function () {
    initPage();
    getPageList();
    initEvents();
    initJuicerMethod();
    getYearCronList();
    $('.btns').on('click','.btn',changePage)
    $('.unit').on('click', '.piece', changeList)

});

/*切换页面*/
function changePage() {
    var that=$(this);
    var type=that.data('type');
    activeDo(type);
}

function activeDo(type) {
    console.log(type);
    $('.modals').hide();
    $('.modals.'+type).show();
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
/*分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 100,
        pageSize: 5,
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

    $('#fm-search').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
                console.log(obj);
            });
            params['initType'] = 'init';
            params['start'] = 0;
            getPageList();
            return false;
        }
    });

    $("#months li").click(function () {
        //  monthList();
        $(this).siblings('li').removeClass('active');  // 删除其他兄弟元素的样式
        $(this).addClass('active'); // 添加当前元素的样式
        var m = $(this).data("id");
        setParams(m);
    });
    //全部平日模式
    $("#dayCron").click(function () {
        $("input:radio[value='1']").prop("checked", "checked");
    });
    //全部节日模式
    $("#festivalCron").click(function () {
        $("input:radio[value='2']").prop("checked", "checked");
    });
    //全部重大节日模式
    $("#greatCron").click(function () {
        $("input:radio[value='3']").prop("checked", "checked");
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

function initEvents() {

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);

    })
}

//查询更新定时设置数据
function selectTimerDate(id) {
    $("#Timer").hide();
    $("#updateRecord").show();
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


/*定时任务数据加载表格数据*/
function getPageList() {
    var url = contextPath + "/seTimerData/findSeTimerDataList";
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
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
var map = {};
function getYearCronList() {
    var date = new Date;
    var month = date.getMonth();
    var m = month + 1;
    var day = date.getDate();
    var url = contextPath + "/generalYearCron/syncData";
    $.getJSON(url, params, function (rst) {
        console.log(rst.value);
        if (rst.status == 0) {
            var data = rst.value;
            // var nowmon=data[m];
            //   var a = nowmon.substring(day - 1, day);
            //   console.log(a);
            var id;
            for (var i = 1; i < 13; i++) {
                id = "m" + i;
                map[id] = data[id];
            }
            $('#months li:eq('+month+')').addClass('active');
            var datas={};
            datas.m=data['m'+m];
            datas.d=parseInt(mGetDate(m));
            render($("#page-YearCronlist"), datas, "tpl-YearCronlist");
            $('#s' + day).addClass('dayactive');
        }
    });
}
//加载每月数据
function setParams(m) {
    var url = contextPath + "/generalYearCron/syncData";
    $.getJSON(url, params, function (rst) {
        if (rst.status == 0) {
            var d = rst.value;
            var data={};
            data.m=d['m'+m];
            data.d=parseInt(mGetDate(m));
            render($("#page-YearCronlist"), data, "tpl-YearCronlist");
            var date = new Date;
            var month = date.getMonth();
            var mo=m-1;
            if(month==mo){
                var day = date.getDate();
                $('#s' + day).addClass('dayactive');
            }
        }
    });
}



function mGetDate(m){
    var date = new Date();
    var year = date.getFullYear();
    var d = new Date(year, m, 0);
    return d.getDate();
}


//获取每月修改的数据
function monthList() {
    var mid = $("#months li.active").data("id");
    console.log(mid);
    var checkValue = '';
    $('input:radio:checked').each(function () {
        checkValue += $(this).val();
    });
    console.log(mid + ":" + checkValue);
    if (map.hasOwnProperty(mid)) {
        map[mid] = checkValue;
    }
    console.log(map);
    execute();
}
//执行总控数据修改
function execute() {
    $.ajax({
        url: contextPath + "/generalYearCron/updateGeneralCtrlCron",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(map)
        },
        success: function (result) {
            stopLoad();
            if (result.status == "ok") {
                // getPageList();
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
            stopLoad();
            if (result.status == "ok") {
                // getPageList();
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