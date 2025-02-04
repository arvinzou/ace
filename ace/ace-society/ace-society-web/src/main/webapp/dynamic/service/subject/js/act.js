var loading = {};
var params = {limit: 5};
window.onload = function (){
    initPage();
    initEvents();
    initJuicerMethod();
}
function App() {
    console.log("=============================App Start==============================");
}
/*方案提议初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 10,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start']=(num-1)*params.limit;
            params['initType']=type;
            getPageList();
        }
    });
}
/*方案提议条件查询*/
function t_query(){
    getPageList();
    return false;
}
/*方案提议加载表格数据*/
function getPageList() {
    var url = contextPath+ "/subject/findSubjectList";
    params['title']=$("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total
                });
            }
            render($("#page-list"), rst.rows, "tpl-list");
        }else{
            alert("系统服务内部异常！");
            stopLoad();
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
/*方案提议添加*/
function add(type){
window.location.href = 'add/index.jsp?id='+ urlParams.id;
}
/*方案提议编辑*/
function edit(did){
window.location.href = 'edit/index.jsp?id='+ urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/subject/selectSubjectByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
        var navitem = document.getElementById('tpl-detail').innerHTML;
        var html = juicer(navitem, {data: result.value});
        $("#detail-info").html(html);
        $("#modal-detail").modal("show");
    }
    });
}

function initEvents() {
$('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        initForm(id);
    })
$('#modal-audit .btn-primary').on('click', function () {
$('#modal-audit form').submit();
    });
$('#modal-audit form').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                console.log(params);
                audit(params);
                return false;
            }
        });


}
/*方案提议审核*/
function audit(params){
    startLoad();
    $.ajax({
        url: contextPath + "/subject/audit",
        type:"post",
        async:false,
        data:params,
        success:function(rst){
            stopLoad();
            $("#modal-audit").modal('hide');
            alert(rst.errorMessage);
            if(rst.status == 0) {
                getPageList();
            }
        },
        error:function(){
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
        return "已删除";
        case '1':
        return "暂存";
        case '2':
        return "提交审核";
        case '3':
        return "审核通过";
        case '4':
        return "审核驳回";
        default:
        return "";
    }
}

function showIdea(id){
    window.location.href = contextPath + '/dynamic/service/subjectIdea/index.jsp?id='+urlParams.id+ '&did='+id;
}

/*查看详情*/
function initForm(id) {
    var url = contextPath + "/subject/selectSubjectByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-fm').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#auditContent").html(html);
        }
    });
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}