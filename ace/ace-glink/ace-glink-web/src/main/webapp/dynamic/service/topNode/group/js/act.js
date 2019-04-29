var loading = {};
var params = {limit: 20};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*节点管理初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev" > <a href="javascript:;" > 上一页 </a></li >',
        next: '<li class = "next" > <a href = "javascript:;" > 下一页 </a></li>',
        page: '<li class = "page" > <a href = "javascript:;" > {{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}

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


$('#getCheckNode').ajaxForm({
    beforeSubmit: function (formData, jqForm, options) {
        if(!formData.length){
           alert("没有选择数据");
           return;
        }
        getStationList(formData);
        console.log(params);
        return false;
    }
});


function getStationList(formData) {
    var url=contextPath + "/topStation/findTopStationList";
    var data={
        start:0,
        limit:1000,
    };
    $.getJSON(url,data,function (rst) {
        if(rst.status==0){
            data['o'] = formData;
            data['s'] = rst.rows;
            render('#fm-preview', data, 'tpl-preview');
            $('#modal-preview').modal("show");
            initform();
        }
        else{
            alert("失败")
        }
    })
}
function initform() {
    $('#upateStation').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                if (params[obj.name]) {
                    params[obj.name] = params[obj.name] + ',' + obj.value;
                } else {
                    params[obj.name] = obj.value;
                }
            });
            if(!params.ids&&params.stationId){
                alert("没有选择节点或站点");
            }
            params.ids=params.ids.slice();
            upateStation(params);
            return false;
        }
    });
}

function upateStation(data) {
    var url=contextPath + "/topNode/updateStation";
    $.post(url,data,function (rst) {
        if(rst.status==0){
            getPageList();
            $('#modal-preview').modal('hide');
        }
        else{
            alert("分配失败")
        }
    })
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*节点管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/topNode/findTopNodeList";
    params['keyword'] = $("input[name=keyword]").val();
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

function initEvents() {
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态
 * 0-删除
 * 1-暂存
 * 2-提交审核
 * 3-审核通过
 * 4-审核驳回
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "删除";
        case '1':
            return "暂存";
        case '2':
            return "待审";
        case '3':
            return "通过";
        case '4':
            return "驳回";
        default:
            return "";
    }
}

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/topNode/selectTopNodeByPrimaryKey",
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
                render('#fm-audit', data, 'tpl-fm');
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
