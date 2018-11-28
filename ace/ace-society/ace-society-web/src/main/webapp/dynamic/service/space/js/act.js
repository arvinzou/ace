var loading = {};
var params = {limit: 5};
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
}
/*爱心商品初始化分页*/
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
/*爱心商品条件查询*/
function t_query() {
    getPageList();
    return false;
}
/*爱心商品加载表格数据*/
function getPageList() {
    var url = contextPath + "/commodity/findCommodityList";
    params['commodityName'] = $("input[name=keyword]").val();
    params['commodityType'] = '1';//0-爱心商品 1-爱心场地
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
/*爱心商品添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*爱心商品编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/commodity/selectCommodityByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#fm-detail").html(html);
            $("#modal-detail").modal("show");
        }
    })
}

function getOccupyData(did) {
    $.ajax({
        url: contextPath + "/spaceOccupyInfo/findSpaceOccupyInfoList",
        type: "post",
        async: true,
        data: {spaceId: did},
        success: function (rst) {
            if (rst.status == 0) {
                var data = [];
                //遍历数据，获取已占用数据
                $(rst.rows).each(function (index, item) {
                    //index指下标
                    //item指代对应元素内容
                    //this指代每一个元素对象
                    var itemData = {
                        id: item.id,
                        title: item.createUserName + ' ' + item.reserveInterval,
                        start: item.reserveDate,
                        className: item.orderId == 'admin' ? 'admin-occupy' : 'custom-occupy',
                        orderId: item.orderId
                    };
                    data.push(itemData);
                });
                //默认配置选项
                var options = {
                    header: {

                        left: 'prev,next today',
                        center: 'title',
                        right: 'month'//,agendaWeek,agendaDay'
                    },
                    editable: true,
                    droppable: true,
                    eventLimit: true,
                    drop: function (date, allDay, jsEvent, ui) {//event 移动进入后触发
                        var dateTime = JSON.stringify(date);
                        dateTime = dateTime.substring(1, 11);
                        var interval = $(this).text();
                        interval = interval.substring(3, interval.length);
                        if (confirm("确定锁定此时间么?")) {
                            $.ajax({
                                url: contextPath + "/spaceOccupyInfo/spaceLock",
                                type: "post",
                                async: false,
                                data: {
                                    spaceId: did,
                                    dateTime: dateTime,
                                    interval: interval
                                },
                                success: function (rst) {
                                    stopLoad();
                                    alert(rst.errorMessage);
                                },
                                error: function () {
                                    stopLoad();
                                    alert("对不起出错了！");
                                }
                            });
                        }
                        //重新刷新
                        calendarInit(did);
                    },
                    eventClick: function (event) {
                        //取消预订问题
                        var orderId = event.orderId;
                        if ("admin" != orderId) {
                            alert("不得移除客户占用!");
                            return;
                        }
                        if (confirm("确定移除锁定么?")) {
                            $.ajax({
                                url: contextPath + "/spaceOccupyInfo/removeLock",
                                type: "post",
                                async: false,
                                data: {id: event.id},
                                success: function (rst) {
                                    stopLoad();
                                    alert(rst.errorMessage);
                                    if (rst.status == 0) {
                                        calendarInit(did);
                                    }
                                },
                                error: function () {
                                    stopLoad();
                                    alert("对不起出错了！");
                                }
                            });
                        }
                    }
                };
                //被占用情况数据
                options.events = data;
                //init
                $('#calendar').fullCalendar(options);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}


function calendarInit(did) {
    $('#calendar').fullCalendar('destroy');
    //初始化
    getOccupyData(did);
}

function initEvents() {
    //注册拖动事件
    $('#external-events .fc-event').each(function () {
        // store data so the calendar knows to render an event upon drop
        $(this).data('event',
            {
                title: '管理员 ' + $.trim($(this).text()).substring(3, $(this).text().length), // use the element's text as the event title
                className: 'admin-occupy',
                stick: true // maintain when user navigates (see docs on the renderEvent method)
            }
        );
        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true, // will cause the event to go back to its
            revertDuration: 0 //  original position after the drag
        });
    });
    //锁定弹框

    $('#modal-lock').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
        //日历插件初始化
        setTimeout(function () {
            calendarInit(id);
        }, 200);
    });

    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
    });
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

function saveLock(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/commodity/spaceLock",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            $("#modal-lock").modal('hide');
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

/*爱心商品删除*/
function del(id) {
    var params = {id: id};
    startLoad();
    $.ajax({
        url: contextPath + "/commodity/deleteCommodityByCommodityId",
        type: "post",
        async: false,
        data: {jsons: JSON.stringify(params)},
        success: function (rst) {
            stopLoad();
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
/*爱心商品审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/commodity/audit",
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
/*爱心商品上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {

        startLoad();
        $.ajax({
            url: contextPath + "/commodity/updateState",
            type: "post",
            async: false,
            data: {
                id: id,
                state: '1'
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
/*爱心商品下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/commodity/updateState",
            type: "post",
            async: false,
            data: {
                id: id,
                state: '0'
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
    juicer.register('parseState', parseState);
    juicer.register('parseType', parseType);
}

/**
 * 商品状态0-下架1-在售
 */
function parseState(state) {
    switch (state) {
        case '0':
            return "已下架";
        case '1':
            return "在售";
        default:
            return "已下架";
    }
}

/**
 *  商品类型      0-爱心商品    1-爱心场地
 */
function parseType(commodityType) {
    switch (commodityType) {
        case '0':
            return "爱心商品";
        case '1':
            return "爱心场地";
        default:
            return "爱心商品";
    }
}