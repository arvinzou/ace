var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*节目管理初始化分页*/
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

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*节目管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/animaRes/findAnimaResList";
    params['name'] = $("input[name=keyword]").val();
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

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

/*节目管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*节目管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/animaRes/selectAnimaResByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#detail-info").html(html);
            $("#modal-detail").modal("show");
        }
    })
}

function initEvents() {
//初始化按钮组件
initBtnEvents();
$('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    $('#modal-show').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var preUrl = relatedTarget.data('url');
        var type = relatedTarget.data('type');
        var modal = $(this);
        if(type == '01'){//01是音频文件
            $("#sourcePreview").html("<audio src='"+preUrl+"' style='width: 100%;height: 500px;' controls=\"controls\"></audio>");
        }else if(type == '02'){//02是视频文件
            $("#sourcePreview").html("<video src='"+preUrl+"' style='width: 100%;height: 100%' controls=\"controls\"></video>");
        }else{
            $("#sourcePreview").html("<p>没有可预览的资源文件</p>")
        }
    })
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
}

//juicer自定义函数
function initJuicerMethod(){
    juicer.register('parseSourseType', parseSourseType);
}

function parseSourseType(type){
    var typeList = staticDictObject['179'];
    for(var i=0; i<typeList.length; i++){
        if(type && type == typeList[i].CODE){
            return typeList[i].NAME;
        }
    }
}

﻿function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/animaRes/selectAnimaResByPrimaryKey",
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

function del(did) {
    startLoad();
    var params = {};
    params.id = did;
    if (confirm("确定要删除吗？")) {
        $.ajax({
            url: contextPath + "/animaRes/deleteAnimaResByAnimaResId",
            type: "post",
            async: false,
            data: {
                jsons: JSON.stringify(params)
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    alert("删除成功！");
                    getPageList();
                }
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}
