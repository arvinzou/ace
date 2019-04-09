var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*建筑物管理初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class = "prev"> <a href = "javascript:;" > 上一页 </a></li>',
        next: '<li class = "next"> <a href = "javascript:;" > 下一页 </a></li>',
        page: '<li class = "page"> <a href = "javascript:;" > {{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}

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


    function setParams(key, value) {
        params[key] = value;
        getPageList();
    }

    /*建筑物管理加载表格数据*/
    function getPageList() {
        var url = contextPath + "/topBuilding/findTopBuildingList";
        params['name'] = $('input[name="name"]').val();
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

    /*建筑物管理添加*/
    function add(type) {
        window.location.href = 'add/index.jsp?id=' + urlParams.id;
    }

    /*建筑物管理编辑*/
    function edit(did) {
        window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
    }

    function del(did){
        startLoad();
        var params = {};
        params.id = did;
        if (confirm("确定要删除吗？")) {
            $.ajax({
                url: contextPath + "/topBuilding/deleteTopBuildingByTopBuildingId",
                type: "post",
                async: false,
                data: {
                    jsons: JSON.stringify(params)
                },
                success: function (rst) {
                    stopLoad();
                    if (rst.status == 0) {
                        alert("删除成功！");
                        getPageList();
                    }
                },
                error: function () {
                    stopLoad();
                    alert("对不起出错了！");
                }
            });
        }
    }


    /*查看详情*/
    function detail(id) {
        var url = contextPath + "/topBuilding/selectTopBuildingByPrimaryKey";
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
﻿   $('#modal-preview').on('show.bs.modal', function (event) {
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
}


//juicer自定义函数
    function initJuicerMethod() {
        juicer.register('parseType', parseType);
    }

    function parseType(type){
        var typeList = staticDictObject['177'];
        for(var i=0; i<typeList.length; i++){
            if(type == typeList[i].CODE){
                return typeList[i].NAME;
            }
        }
    }
﻿   function initPreview(id) {
        startLoad();
        $.ajax({
            url: contextPath + "/topBuilding/selectTopBuildingByPrimaryKey",
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

function initClassList(ctrlId) {
    startLoad();
    $.ajax({
        url: contextPath + "/topBuilding/getClassList",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = result.value;
                var all = {id: "", name: "全部"};
                data.unshift(all);
                render('#' + ctrlId, data, 'tpl-cls-option');
                params.category = '2';
                initGrid();
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