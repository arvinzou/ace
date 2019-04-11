var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*策略管理初始化分页*/
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

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*策略管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/ltStrategy/findLtStrategyList";
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

/*策略管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*策略管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey";
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
    juicer.register('isChecked', isChecked);
    //模态框事件
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initForm(id);
    });
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        initPreview(id);
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
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        $(event.target).addClass("active");
    });

}

function isChecked(val, idex) {
    var i = val.slice(',');
    console.log(i);
    for (var x = 0; x < i.length; x++) {
        if (idex == i[x]) {
            return 'checked'
        }
    }
    return '';
}

function setModel(id) {
    startLoad();
    var url = contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey";
    var data = {
        id: id
    };
    $.getJSON(url, data, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            data = rst.value;
            render('#setModel', data, 'tpl-setModel');
            initDatetimepicker();
            $('#modal-seting').modal('show');
        } else {
            alert(rst.errorMessage);
        }
    });
}


function initDatetimepicker() {
    $("input[name=startDate]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var startTime = event.date;
        $("input[name=stopDate]").datetimepicker('setStartDate', startTime);
        $("input[name=stopDate]").val("");
    });

    $('input[name=startDate]').focus(function () {
        $(this).blur(); //不可输入状态
    })


    $("input[name=stopDate]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var endTime = event.date;
        $("input[name=startDate]").datetimepicker('setEndDate', endTime);
    });
    $('input[name=stopDate]').focus(function () {
        $(this).blur(); //不可输入状态
    });


    $("input[name=startTime]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var startTime = event.date;
        $("input[name=stopTime]").datetimepicker('setStartDate', startTime);
        $("input[name=stopTime]").val("");
    });

    $('input[name=startTime]').focus(function () {
        $(this).blur(); //不可输入状态
    })


    $("input[name=stopTime]").datetimepicker({
        minView: "hour",
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: true, //显示‘今日’按钮
        clearBtn: true, //清除按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var endTime = event.date;
        $("input[name=startTime]").datetimepicker('setEndDate', endTime);
    });
    $('input[name=stopTime]').focus(function () {
        $(this).blur(); //不可输入状态
    });
    $('#setting').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                if (params[obj.name]) {
                    params[obj.name] = params[obj.name] + ',' + obj.value;
                } else {
                    params[obj.name] = obj.value;
                }
            });
            saveSetting(params);
            return false;
        }
    });

}

function change(idx) {
    $('#modal-seting .tab_leb .tab_f').hide();
    switch(idx){
        case '1':
            $('#modal-seting .tab_leb .tab_1').show();  break;
        case '2':
            $('#modal-seting .tab_leb .tab_2').show();
            break;
        case '3':
            $('#modal-seting .tab_leb .tab_3').show();
            break;
    }
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

    /*监听表单提交*/
}

function saveSetting(params) {
    var url = contextPath + "/ltStrategy/updateLtStrategyVo";
    var data = {
        jsons: JSON.stringify(params)
    }
    $.post(url,data,function (rst) {
        if(rst.status==0){
            alert(1)
        }else{
            alert(2)
        }
    })
}

/*策略管理审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/ltStrategy/audit",
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

/*策略管理上架*/
function updateStatus(id) {
    if (confirm("确定要发布吗？")) {
        startLoad();
        var url=contextPath + "/ltStrategy/updateStatus";
        var data= {
            id: id,
                status: '2'
        };
        $.getJSON(url,data,function(rst){
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
        })
    }
}

/*策略管理下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/ltStrategy/updateStatus",
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

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = result.value;
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
    var url = contextPath + "/ltStrategy/deleteLtStrategyByLtStrategyId";
    var data = {
        jsons: JSON.stringify({
            id: did
        })
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            getPageList();
        }
        else {
            alert("删除失败")
        }

    })
}

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/ltStrategy/selectLtStrategyByPrimaryKey",
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
