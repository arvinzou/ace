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
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
    //故障类型-查询条件
    render('#errType', staticDictObject, 'tpl-errType');
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

/*故障报警加载表格数据*/
function getPageList() {
    var url = contextPath + "/leBrokenLamp/findLeBrokenLampList";
    params['name'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        console.log(JSON.stringify(rst));
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
    var url = contextPath + "/leBrokenLamp/selectLeBrokenLampByPrimaryKey";
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

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });

    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });

    // initNodeList();
    // initTopStationList();
}


/*故障报警上架*/
function updateStatus(id, state) {
    var title = '0' == state ? '标记为未读?' : '标记为已读?';
    swal({
            title: title,
            text: "故障状态将发生变更!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function () {
            startLoad();
            $.ajax({
                url: contextPath + "/errFeedback/updateStatus",
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
    juicer.register('parseStatus', parseStatus);
    juicer.register('rsd', rsd);
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
            return "未读";
        case '1':
            return "已读";
        default:
            return "未读";
    }
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/leBrokenLamp/selectLeBrokenLampByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            console.log(JSON.stringify(result));
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
                //调用初始化函数地图
                var lat = data['o'].latitude,
                    lng = data['o'].longitude;
                console.log("lat=" + lat + ",lng=" + lng);
                if (lat != null) {
                    initCustomMapMarker(lat, lng, 'map-container'
                        , 'https://lbs.qq.com/javascript_v2/img/center.gif');
                }
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
        url: contextPath + "/errFeedback/selectErrFeedbackByPrimaryKey",
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

//初始化自定义地图标记
function initCustomMapMarker(lat, lng, container, img) {
    var center = new qq.maps.LatLng(lat, lng);
    var map = new qq.maps.Map(document.getElementById(container), {
        center: center,
        zoom: 17
    });
    var anchor = new qq.maps.Point(6, 6),
        size = new qq.maps.Size(300, 300),
        origin = new qq.maps.Point(0, 0),
        icon = new qq.maps.MarkerImage(img, size, origin, anchor);
    var marker = new qq.maps.Marker({
        icon: icon,
        map: map,
        position: map.getCenter()
    });
}

/**
 * 初始化下拉节点列表
 */
function initNodeList() {
    startLoad();
    $.ajax({
        url: contextPath + "/topNode/findTopNodeList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 999
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var dataList = result.rows;
                render('#nodeCode', dataList, 'nodeCode-tpl');
                console.log(dataList);
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

/**
 * 初始化下拉站点列表
 */
function initTopStationList() {
    startLoad();
    $.ajax({
        url: contextPath + "/topStation/findTopStationList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 999
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var dataList = result.rows;
                render('#subStation', dataList, 'station-list');
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

function syncData() {
    var url = contextPath + '/leBrokenLamp/syncData?date=2019-04-16';
    if (confirm("即将更新当日故障数据，是否继续？")) {
        $.getJSON(url, function (rst) {
            alert(rst.errorMessage);
            if (rst.status == 0) {
                getPageList();
            }
        });
    }

}
