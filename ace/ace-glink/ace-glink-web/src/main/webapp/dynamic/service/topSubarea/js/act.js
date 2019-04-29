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

/*分区管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/topSubarea/findTopSubareaList";
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

/*分区管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*分区管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/topSubarea/selectTopSubareaByPrimaryKey";
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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('parseThirdTag', parseThirdTag);
}

function parseThirdTag(val) {
    switch (val) {
        case 0:
            return "否";
        case 1:
            return "是";
        default:
            return "";
    }
}


/**
 * 状态
 * 0-删除
 * 1-正常
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "删除";
        case '1':
            return "正常";
        default:
            return "";
    }
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/topSubarea/selectTopSubareaByPrimaryKey",
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
                data['stationList'] = result.value.stationList;

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
        url: contextPath + "/topSubarea/selectTopSubareaByPrimaryKey",
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

/*删除*/
function del(did) {
    if (confirm("确定要删除吗？")) {
        var url = contextPath + "/topSubarea/deleteTopSubareaByTopSubareaId";
        var data = {
            jsons: JSON.stringify({
                id: did
            })
        }
        $.getJSON(url, data, function (rst) {
            if (rst.status == 0) {
                getPageList();
            } else {
                alert("删除失败")
            }
        })
    }
}