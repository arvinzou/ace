var loading = {};
var params = {limit: 15};
window.onload = function () {
    initPage();
    initEvents();
    initSelect();
}




function initSelect() {
/*    $(".js-example-basic-single").select2({
        ajax: {
            url: portalPath + "/dict/findListByCategoryId.do?categoryId=154&selected=false",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var datas = $.map(data.slice(1), function (obj) {
                    obj.text = obj.text || obj.name; // replace name with the property used for the text
                    return obj;
                });
                datas = $.map(datas, function (obj) {
                    obj.id = obj.code; // replace name with the property used for the text
                    return obj;
                });
                return {
                    results: datas,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        placeholder: '选择类型',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
    });

    $(".js-example-basic-single1").select2({
        ajax: {
            url: contextPath + "/evaluating/findEvaluatingList",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var datas = $.map(data.rows, function (obj) {
                    obj.text = obj.text || obj.name; // replace name with the property used for the text
                    return obj;
                });
                datas = $.map(datas, function (obj) {
                    obj.id = obj.id; // replace name with the property used for the text
                    return obj;
                });
                return {
                    results: datas,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    },
                    paginate: {
                        more: true
                    }
                };
            },
            cache: true
        },
        placeholder: '选择评测模板',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
    });*/

    $(".js-example-basic-single2").select2({
        ajax: {
            url: contextPath + '/teacher/findTeacherList',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var datas = $.map(data.rows, function (obj) {
                    obj.text = obj.text || obj.name; // replace name with the property used for the text
                    return obj;
                });
                datas = $.map(datas, function (obj) {
                    obj.id = obj.id; // replace name with the property used for the text
                    return obj;
                });
                return {
                    results: datas,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    },
                    paginate: {
                        more: true
                    }
                };
            },
            cache: true
        },
        placeholder: '选择授课老师',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        templateResult: formatState
    });
}

function formatState (state) {
    if (!state.id) {
        return state.text;
    }
    if(!state.photoUrl){
        state.photoUrl=contextPath+'/content/common/img/default_header.png';
    }
    var $state = $(
        '<div style="height: 50px; margin-bottom: 5px;"><img style="height: 50px;width: 50px;object-fit: cover; overflow: hidden;margin-right: 10px" src="'+state.photoUrl+'" class="img-flag" /> ' + state.text + '</div>'
    );
    return $state;
}






/*课程表管理初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: ' <li class="prev"><a href="javascript:;">上一页</a></li>',
        next: ' <li class="next"><a href="javascript:;">下一页</a></li>',
        page: ' <li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}

/*课程表管理条件查询*/
function t_query() {
    getPageList();
    return false;
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*课程表管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/classSchedule/LearnedCourses";
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

function initEvents() {
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initForm(id);
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
        console.log(event);
        $(event.target).addClass("active");
    });
}


/*课程管理编辑*/
function view(did) {
    window.location.href = 'view/index.jsp?id=' +did;
}



function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/classSchedule/selectClassScheduleByPrimaryKey",
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
        url: contextPath + "/classSchedule/selectClassScheduleByPrimaryKey",
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