var loading = {};
var params = {limit: 10,category:'1'};
window.onload = function () {
    initPage();
    initEvents();
    $('#preview').on('click', '.btn-audit', audit);
    $('#preview').on('change', '.verify-btn', beforeActive);
    $('#info').on('click', '.personnelInfo', optionPersonner);
    $('#preview').modal('show');
}

/*不同的选项显示不同的按钮*/
function beforeActive() {
    var val = $(this).val();
    if (val == 3) {
        $(this).next().hide();
        $('#coinConfig').show();
    } else if (val == 4) {
        $(this).next().show();
        $('#coinConfig').hide();
    }
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
}

/*线下活动初始化分页*/
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

/*选择参加者*/
function optionPersonner() {
    var $that = $(this);
    if ($that.is('.active_flag')) {
        $that.removeClass('active_flag')
        return;
    }
    $that.addClass('active_flag');
}


/*线下活动条件查询*/
function t_query() {
    getPageList();
    return false;
}

/*线下活动加载表格数据*/
function getPageList() {
    var url = contextPath + "/activity/findActivityList";
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

/*线下活动添加*/
function add(type) {
    window.location.href = add / index.jsp;
}

/*线下活动编辑*/
function edit(id) {
    window.location.href = 'edit/index.jsp?id=' + id;
}

/*个人信息审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/activity/audit",
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



/*线下活动上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/activity/updateStatus",
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

/*线下活动下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/activity/updateStatus",
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

function deleteData(id) {
    if (confirm("确定删除该活动吗，删除后将不能恢复。")) {
        var url = contextPath + "/activity/softDelete";
        var data = {
            jsons: JSON.stringify({
                id: id,
            })
        }
        startLoad();
        $.post(url, data, function (rst) {
            stopLoad();
            if (rst.status == 0) {
                getPageList();
                return;
            }
            alert(rst.errorMessage);
        })
    }
}


// function details(id) {
//     var url = contextPath + "/activity/selectActivityByPrimaryKey";
//     var data = {
//         id: id,
//     }
//     $('#preview').data('id', id);
//     startLoad();
//     $.post(url, data, function (rst) {
//         stopLoad();
//         if (rst.status == 0) {
//             renderPage("info", rst.value, "tpl-info");
//             if (rst.value.status == 2) {
//                 getCoinConfig(rst.value.category);
//             }
//             $('#preview').modal("show");
//             return;
//         }
//         alert(rst.errorMessage);
//     })
// }

/*查看详情*/
function details(id) {
    var url = contextPath + "/activity/selectActivityByPrimaryKey";
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


function initAuditForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/activity/selectActivityByPrimaryKey",
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



function getCoinConfig(category) {
    var url = contextPath + "/coinConfig/findCoinConfigList";
    var data = {
        category: category,
        start: 0,
        limit: 10
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            renderPage('coinConfig', result.rows, 'tpl-coinConfig');
        }
    })
}


function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}