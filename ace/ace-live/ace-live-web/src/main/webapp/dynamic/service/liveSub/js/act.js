var loading = {};
var params = {
    limit: 10,
    'deptId': userProp.corpId
};
window.onload = function () {
    initPage();
    initEvents();
}

function App() {
    console.log("=============================App Start==============================");
}
/*直播初始化分页*/
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
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}
/*直播条件查询*/
function t_query() {
    getPageList();
    return false;
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}
/*直播加载表格数据*/
function getPageList() {
    var url = contextPath + "/live/findLiveList";
    params['name'] = $("input[name=keyword]").val();
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
/*直播添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*直播编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/subject/selectSubjectByPrimaryKey";
    $.getJSON(url, {
        id: id
    }, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {
                data: result.value
            });
            $("#detail-info").html(html);
            $("#modal-detail").modal("show");
        }
    });
}

function initEvents() {
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        $('.audit-title').html(title);
        console.log(relatedTarget);
        modal.find('.modal-body input[name=id]').val(id);
    })
    $('#modal-audit .audit').on('click', function () {
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


    $('#modal-status').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        $('.status-title').html(title);
        console.log(relatedTarget);
        modal.find('.modal-body input[name=id]').val(id);
    })
    $('#modal-status .status').on('click', function () {
        $('#modal-status form').submit();
    });
    $('#modal-status form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            updateStatus(params);
            return false;
        }
    });

    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
	
}
/*直播审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/live/updateLiveAuditByLiveId",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                $("#modal-audit").modal('hide');
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function updateStatus(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/live/updateStatus",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                $("#modal-status").modal('hide');
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
