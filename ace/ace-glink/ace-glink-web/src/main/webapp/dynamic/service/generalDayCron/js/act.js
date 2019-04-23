var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
}


/*分区管理初始化分页*/
function initPage() {
    //分页组件
    /*$.jqPaginator('#pagination1', {
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
    });*/
    getPageList();
}


function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*分区管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/generalDayCron/syncData";
    params['name'] = $("input[name=keyword]").val();
    startLoad();

    $.getJSON(url, params, function (rst) {
        stopLoad();
        console.log(rst);
        var data = rst;
        render($("#page-list"), data, "tpl-list");


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
    $('#modal-option').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        topBuildingCode = id;
        var modal = $(this);
        initForm(id);
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

function update() {

    var WorkMode = $('#WorkMode option:selected').val();//选中的值
    $.ajax({
        url: contextPath + "/generalDayCron/updateDayCron",
        type: "post",
        async: false,
        data: {
            WorkMode: WorkMode
        },
        success: function (result) {
            console.log(result);
            stopLoad();
            if (result.Status == "ok") {
                alert("更新成功");
                getPageList();
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



