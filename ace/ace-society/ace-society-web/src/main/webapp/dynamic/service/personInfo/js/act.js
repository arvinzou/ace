var loading = {};
var params = {limit: 10,politicalStatus:1};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();

}
function App() {
    console.log("=============================App Start==============================");
    loadCustom();
}
/*加载资源*/
function loadCustom() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/js/jquery.form.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
    console.log("===============");
    console.log(urlParams);
}
/*个人信息初始化分页*/
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
/*个人信息条件查询*/
function t_query() {
    getPageList();
    return false;
}
/*个人信息加载表格数据*/
function getPageList() {
    var url = contextPath + "/personInfo/findPersonInfoList";
    params['realName'] = $("input[name=keyword]").val();
    params['orgName'] = $("input[name=orgName]").val();
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
    }, function error(e) {

        console.log(e);
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
/*个人信息添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*个人信息编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/personInfo/selectPersonInfoByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#fm-detail").html(html);
            $("#modal-detail").modal("show");
        }
    });
}

function initEvents() {
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
        initAuditForm(id);
    })
    $('#modal-audit .btn-primary').on('click', function () {
        $('#modal-audit form').submit();
    });

    $('#modal-audit form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var rstVal = $('input[name="rst"]:checked').val();
            if (rstVal == undefined) {
                alert("请选择审核结果!");
                return;
            }

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
/*个人信息审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/personInfo/audit",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            $("#modal-audit").modal('hide');
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
/*个人信息上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/personInfo/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '1'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
/*个人信息下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/personInfo/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '0'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('parsePStatus', parsePStatus);
}

function parsePStatus(val) {
    switch (val) {
        case '1':
            return "个人";
        case '2':
            return "党员";
        default:
            return "个人";
    }
}

/**
 * 状态
 * 0-删除
 * 1-暂存
 * 2-提交审核
 * 3-审核通过
 * 4-审核驳回
 */
function parseStatus(val) {
    switch (val) {
        case '0':
            return "已删除";
        case '1':
            return "暂存";
        case '2':
            return "待审核";
        case '3':
            return "已通过";
        case '4':
            return "被驳回";
        default:
            return "暂存";
    }
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

function initAuditForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/personInfo/selectPersonInfoByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                render('#fm-audit', result.value, 'tpl-fm-audit');
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