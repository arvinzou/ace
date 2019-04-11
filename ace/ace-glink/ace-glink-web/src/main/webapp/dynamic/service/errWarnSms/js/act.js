var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*故障报警初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: "<li class='prev'><a href='javascript:;'>上一页</a></li>",
        next: "<li class='next'><a href='javascript:;'>下一页</a></li>",
        page: "<li class='page'><a href='javascript:;'>{{page}}</a></li>",
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
    //
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
    //
    $('#fm-search input[name=subareaCode]').combogrid({
        panelWidth: 375,
        idField: 'code',
        textField: 'name',
        url: contextPath + '/topSubarea/findTopSubareaList',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        loadMsg: "正在获取...",
        columns: [[
            {
                field: 'name',
                title: '分区名称',
                width: 100
            }, {
                field: 'code',
                title: '分区编码',
                width: 100
            }
        ]],
        //onChange event
        onChange: function (current, old) {
            setParams('subareaCode', current);
        },

    });
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*故障报警加载表格数据*/
function getPageList() {
    var url = contextPath + "/errWarnSms/findErrWarnSmsList";
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

/*故障报警添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*故障报警编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/errWarnSms/selectErrWarnSmsByPrimaryKey";
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

    //查看
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    //查询btn
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });

    //送报人
    $('#modal-someone').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var subareaCode = relatedTarget.data('id');
        //render窗体元素
        initSomeone(subareaCode);
    });

    //调度规则
    $('#modal-scheduler').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var smsId = relatedTarget.data('id');
        //render窗体元素
        initScheduler(smsId);
    });
}

function initScheduler(smsId) {
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSms/selectErrWarnSmsByPrimaryKey",
        type: "post",
        async: false,
        data: {id: smsId},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-someone', data, 'tpl-someone');
            } else {
                swal(result.errorMessage, "", "error")
            }
        },
        error: function () {
            stopLoad();
            swal("对不起出错了！", "", "error")
        }
    });
}

function initSomeone(subareaCode) {
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSomeone/findErrWarnSomeoneList",
        type: "post",
        async: false,
        data: {subareaCode: subareaCode},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = result.rows;
                render('#fm-someone', data, 'tpl-someone');
                //初始化按钮事件
                initAddSomeoneEvent(subareaCode);
            } else {
                swal(result.errorMessage, "", "error")
            }
        },
        error: function () {
            stopLoad();
            swal("对不起出错了！", "", "error")
        }
    });
}

var someoneState;

function initAddSomeoneEvent(subareaCode) {

    //基本初始化
    $('.sw-content input').bootstrapSwitch();
    //点击触发事件，监听按钮状态
    $('.sw-content input').on('switchChange.bootstrapSwitch', function (event, state) {
        // //内置对象、内置属性
        // console.log(event);
        // //获取状态
        // console.log(state);
        if (state) {
            someoneState = '1';
        } else {
            someoneState = '0';
        }
    });
    //
    $("#btn-add-someone").bind('click', function (event) {
        $('#modal-add-someone').modal('show');
        $('#modal-add-someone input').val('');
        //手动设置按钮状态
        $('.sw-content input').bootstrapSwitch('state', true);
    });
    //审核框 确定按钮
    $('#modal-add-someone .btn-primary').on('click', function () {
        $('#modal-add-someone form').submit();
    });
    //审核框 提交事件
    $('#modal-add-someone form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            params.subareaCode = subareaCode;
            params.status = someoneState;
            addSomeone(params);
            return false;
        }
    });
}

/*故障报警上架*/
function updateStatus(id, state) {
    var title = '0' == state ? '弃用该短信模板?' : '启用该短信模板?';
    var text = '0' == state ? '短信模板即将被弃用!' : '短信模板即将被启用!';
    swal({
            title: title,
            text: text,
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes,do it!",
            closeOnConfirm: false
        },
        function () {
            startLoad();
            $.ajax({
                url: contextPath + "/errWarnSms/updateStatus",
                type: "post",
                async: false,
                data: {
                    id: id,
                    status: state
                },
                success: function (rst) {
                    stopLoad();
                    if (rst.status == 0) {
                        swal("变更成功!", "", "success");
                        getPageList();
                    } else {
                        swal(rst.errorMessage, "", "error");
                    }
                },
                error: function () {
                    stopLoad();
                    alert("对不起，出错了！");
                }
            });
        }
    );
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
    juicer.register('parseSomeoneStatus', parseSomeoneStatus);
}

/**
 * 状态解析
 */
function parseSomeoneStatus(status) {
    switch (status) {
        case '0':
            return "OFF";
        case '1':
            return "ON";
        default:
            return "OFF";
    }
}

/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "已弃用";
        case '1':
            return "已启用";
        default:
            return "已弃用";
    }
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSms/selectErrWarnSmsByPrimaryKey",
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

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSms/selectErrWarnSmsByPrimaryKey",
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

function del(id) {
    var url = contextPath + "/errWarnSms/deleteErrWarnSmsByErrWarnSmsId";
    var data = {
        jsons: JSON.stringify({
            id: id,
        })
    };
    swal({
            title: "确认删除该条数据么",
            text: "短信模板即将被移除!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes,do it!",
            closeOnConfirm: false
        },
        function () {
            startLoad();
            $.post(url, data, function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    swal(rst.errorMessage, "", "success");
                    getPageList();
                } else {
                    //错误提示
                    swal(rst.errorMessage, "", "error");
                }
            })
        }
    );
}

function clearSubareaCode() {
    // $('#fm-search input[name=subareaCode]').combogrid('clear');
    setParams('subareaCode', '');
}


/*新增送报人*/
function addSomeone(params) {
    console.log(params);
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSomeone/insertErrWarnSomeone",
        type: "post",
        async: false,
        data: {jsons: JSON.stringify(params)},
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                $("#modal-add-someone").modal('hide');
                initSomeone(params.subareaCode);
            }
        },
        error: function () {
            stopLoad();
            alert('对不起出错了！');
        }
    });
}

/*编辑送报人*/
function editSomeone(did, subareaCode) {


}

/*删除送报人*/
function delSomeone(did, subareaCode) {
    startLoad();
    $.ajax({
        url: contextPath + "/errWarnSomeone/deleteErrWarnSomeoneByErrWarnSomeoneId",
        type: "post",
        async: false,
        data: {jsons: JSON.stringify({id: did})},
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                initSomeone(subareaCode);
            }
        },
        error: function () {
            stopLoad();
            alert('对不起出错了！');
        }
    });
}

