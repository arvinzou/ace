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
});


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
        var m = $(this).attr("id");
        setParams(m);
    });
    $("#dayCron").click(function () {
        $("input:radio[value='1']").prop("checked", "checked");
    });
    $("#jieri").click(function () {
        $("input:radio[value='2']").prop("checked", "checked");
    });
    $("#zhong").click(function () {
        $("input:radio[value='3']").prop("checked", "checked");
    });
}

function initEvents() {
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/seTimerData/selectSeTimerDataByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['monthList'] = result.value.monthList;
                data['weekList'] = result.value.weekList;
                data['dayList'] = result.value.dayList;
                render('#fm-preview', data, 'tpl-preview');
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
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var m = "m" + month;
    var url = contextPath + "/generalYearCron/syncData";
    startLoad();

    $.getJSON(url, params, function (rst) {
        stopLoad();

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

            $('#s' + day).addClass('dayactive');
            $('#' + m).addClass('active');
            render($("#page-YearCronlist"), data[m], "tpl-YearCronlist");

        }
    });
}
//加载每月数据
function setParams(m) {
    var url = contextPath + "/generalYearCron/syncData";
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            var data = rst.value;
            render($("#page-YearCronlist"), data[m], "tpl-YearCronlist");
        }
    });
}
//获取每月修改的数据
function monthList() {
    var mid = $("#months li.active").attr("id");
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
