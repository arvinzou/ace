var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*分区管理初始化分页*/
function initPage() {
    //分页组件
    /*$.jqPaginator('#pagination1', {
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
    });*/
    getPageList();
}


/*function setParams(key, value) {
    params[key] = value;
    getPageList();
}*/

/*加载表格数据*/
function getPageList() {
    var url = contextPath + "/generalYearCron/syncData";
    params['name'] = $("input[name=keyword]").val();
    startLoad();

    $.getJSON(url, params, function (rst) {
        stopLoad();
        console.log(rst.value);
        if (rst.status == 0) {
            /*  if (params.initType == "init") {
                  $('#pagination1').jqPaginator('option', {
                      totalCounts: rst.total == 0 ? 1 : rst.total,
                      currentPage: 1
                  });
              }*/
            render($("#page-list"), rst.value.m1, "tpl-list");

        }
    });


}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;

    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}


function initEvents() {
    //初始化按钮组件
    initBtnEvents();
    //模态框事件
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })

    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    $('#modal-option').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        topBuildingCode = id;
        var modal = $(this);
        initForm(id);
    });


}


function initBtnEvents() {
    $('#fm-search').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            params['initType'] = 'init';
            params['start'] = 0;
            getPageList();
            return false;
        }

    });
}

function checkonline(obj, len1) {
    //关闭vedio
    if ($(obj).is(":checked")) {
        $(obj).parent().parent().find("video").removeClass("overlay");
        $(obj).parent().parent().find("video").attr("controls", "controls");

        $("#close" + len1).text("当前已开启");
    } else {
        $(obj).parent().parent().find("video").addClass("overlay");
        $(obj).parent().parent().find("video").removeAttr("controls");
        $("#close" + len1).text("当前已关闭");
    }
}

//选中的值
function checkanima() {
    var a = $("input[name='checkanima']:checked").length;
    var chk_value = [];
    if (a == 0) {
        $("#checkCount").text('0');
    } else {
        $('input[name="checkanima"]:checked').each(function () {
            chk_value.push($(this).val());
            var b = chk_value.length;
            $("#checkCount").text(b);
        });
    }


}

function update() {

    $.ajax({
        url: contextPath + "/generalYearCron/updateGeneralCtrlCron",
        type: "post",
        async: false,
        data: {
            // WorkMode:WorkMode
        },
        success: function (result) {
            stopLoad();
            if (result.status == "ok") {
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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case "1":
            return "平日模式";
        case "2":
            return "节日模式";
        case "3":
            return "重大节假日模式";
        default:
            return "0";
    }
}

function setParams(m) {

    var url = contextPath + "/generalYearCron/syncData";
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            var data = rst.value;
            render($("#page-list"), data[m], "tpl-list");

        }
    });
}