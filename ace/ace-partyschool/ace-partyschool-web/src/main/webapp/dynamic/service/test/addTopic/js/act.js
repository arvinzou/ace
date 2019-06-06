var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {

        juicer.register('parseType', parseType);
        // juicer.register('parseStatus', parseStatus);
        juicer.register('testTopicId', testTopicId);
        $(".breadcrumb").append("<li><span>添加试题</span></li>");
        initEvents();
        initForm();

    });
}

//juicer自定义函数
function initJuicerMethod() {


}

function initEvents() {
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        $(event.target).addClass("active");
    });

    $('#modal-topics').on('hide.bs.modal', function (event) {
        initForm();
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


/*保存表单**/
function save(params) {
    $.extend(params, {});
    params.id = urlParams.did;
    startLoad();
    $.ajax({
        url: contextPath + "/test/updateTest",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id=' + urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

var topiclist;

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/test/findTopicsByTestId",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = result.data.topics;
                topiclist = data;
                render('#page-list', data, 'tpl-list');
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


function addTopic() {
    initPage();
    $('#modal-topics').modal('show');
}


var paramsModel = {limit: 10, testId: urlParams.did, type: '1'};


function initPage() {
    $.jqPaginator('#pagination-model', {
        totalCounts: 1,
        pageSize: paramsModel.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            paramsModel['start'] = (num - 1) * paramsModel.limit;
            paramsModel['initType'] = type;
            getPageList();
        }
    });
}


function getPageList() {
    var url = contextPath + "/topic/findNoThisTopicList";
    paramsModel['name'] = $("input[name=keyword]").val();
    // startLoad();
    $.getJSON(url, paramsModel, function (rst) {
        // stopLoad();
        if (rst.status == 0) {
            if (paramsModel.initType == "init") {
                $('#pagination-model').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#page-list-model"), rst.rows, "tpl-list-model");
        }
    })
}

function setParams(key, value) {
    paramsModel[key] = value;
    getPageList();
}


function submitTopics() {
    var checkeds = $('#page-list-model input[type=checkbox]:checked');
    if (checkeds.length < 1) {
        return;
    }
    var topics = [];
    checkeds.each(function (i) {
        topics.push($(this).val());
    });
    var url = contextPath + "/test/inserTopics";
    var data = {
        jsons: JSON.stringify({
            testId: urlParams.did,
            topics: topics
        })
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            getPageList();
        } else {
            alert("添加失败,刷新浏览器重试。")
        }
    });
};


function testTopicId(index, num) {
    var idx = parseInt(index) + num;
    var len = topiclist.length;
    if (-1 < idx && idx < len) {
        return topiclist[idx].tid;
    }
    return "";
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

function moveIndex(tid1, tid2) {
    if (!tid1) {
        return;
    }
    var url = contextPath + "/test/changeTestTopicIndex";
    var data = {
        tid1: tid1,
        tid2: tid2
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            initForm();
        } else {
            alert("设置失败,刷新浏览器重试。")
        }
    });
}

function delTestTopic(id) {
    if (!id) {
        return;
    }
    var url = contextPath + "/test/delTestTopic";
    var data = {
        id: id
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            initForm();
        } else {
            alert("删除失败,刷新浏览器重试。")
        }
    });
}


function setScore(val, id) {
    var url = contextPath + "/test/setScore";
    var data = {
        score: val,
        id: id
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            initForm();
        } else {
            // alert("删除失败,刷新浏览器重试。")
        }
    });

}


function parseType(val) {
    switch (val) {
        case '1':
            return "单选题";
        case '2':
            return "多选题";
        case '3':
            return "判断题";
        case '4':
            return "问答题";
        case '5':
            return "打分题";
        default:
            return "";
    }
}
