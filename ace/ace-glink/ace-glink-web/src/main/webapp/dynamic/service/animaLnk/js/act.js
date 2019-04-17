var loading = {};
var params = {limit: 10};
var topBuildingId = null;
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*节目上传初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class = "prev"> <a href = "javascript:;"> 上一页 </a></li>',
        next: '<li class = "next"> <a href = "javascript:;"> 下一页 </a></li>',
        page: '<li class = "page"> <a href = "javascript:;" >{{page}}</a></li> ',
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

/*节目上传加载表格数据*/
function getPageList() {
    var url = contextPath + "/topBuilding/findTopBuildingList";
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

/*节目上传添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*节目上传编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/animaLnk/selectAnimaLnkByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#detail-info").html(html);
            $("#modal-detail").modal("show");
        }
    })
}

function bindAnimaEvent(obj,animaCode,prePlayUrl){
    var checkStatus = $(obj).find(".checkStatus").attr("src");
    var list = [];
    if(checkStatus.indexOf("un")>0){
        //未勾选
        $(obj).find(".checkStatus").attr("src",'img/icon-checked.png');
        $(obj).find(".checkStatus").addClass("active");
    }else{
        //已勾选
        $(obj).find(".checkStatus").attr("src",'img/icon-unchecked.png');
        $(obj).find(".checkStatus").removeClass("active");
    }
}

function confirmBindAnima(){
    var list = [];
    var checkList = $("#animaList").find(".active");
    for(var i=0; i<checkList.length; i++){
        var animaCode = $(checkList[i]).data('code');
        var prePlayUrl = $(checkList[i]).data('url');
        var o = {
            aiCode:  animaCode,
            lnkCode: topBuildingCode,
            lnkType: "3",
            prePlayUrl: prePlayUrl
        };
        list.push(o);
    }
    startLoad();
    $.ajax({
        url: contextPath + "/animaLnk/insertAnimaLnk",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(list)
        },
        success: function (rst) {
            stopLoad();
            $("#modal-option").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initEvents(){
    initBtnEvents();

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        topBuildingCode = id;
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })
    $('#modal-option').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        topBuildingCode = id;
        var modal = $(this);
        initForm();
    });
    $('#modal-preUrl').on('show.bs.modal', function (event) {
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
}

//juicer自定义函数
function initJuicerMethod() {
   // juicer.register('parseStatus', parseStatus);
}
﻿function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/animaLnk/findAnimaLnkList",
        type: "post",
        async: false,
        data: {
            lnkCode: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                render('#fm-preview', result.rows, 'tpl-preview');
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

function initForm() {
    startLoad();
    var name = $("#animaName").val();
    $.ajax({
        url: contextPath + "/animaRes/findAnimaResList",
        type: "post",
        async: false,
        data: {
            name: name,
            start: 0,
            limit:40
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                render('#animaList', result.rows, 'animaList-tpl');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
    return;
}

function removeAnimaEvent(did){
    if (confirm("确定要移除吗？")) {
        startLoad();
        var params = {};
        params.id = did;
        $.ajax({
            url: contextPath + "/animaLnk/deleteAnimaLnkByAnimaLnkId",
            type: "post",
            async: false,
            data: {
                jsons: JSON.stringify(params)
            },
            success: function (result) {
                stopLoad();
                if (result.status == 0) {
                    alert("操作成功！");
                    initPreview(topBuildingCode);
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
}

function query(){
    initForm();
    $('#modal-option').show();
}