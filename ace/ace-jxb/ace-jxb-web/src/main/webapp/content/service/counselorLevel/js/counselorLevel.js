window.onload = function () {
    initPage();
    $('.modify_btn').on('click', activeModify);
    $('.calculation_btn').on('click', activeCalculation);
};

var params = {limit: 20, type: '1'};

/*确定计算岗位*/
function activeCalculation() {
    var quarter = $('input[name="quarter"]:checked').val();
    var url = '/jxb/www/counselor/examine';
    var data = {
        quarter: quarter
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {

        } else {
            alert(result.errorMessage);
        }
        $('#calculationLevel').modal('hide');

        //
        initPage();
    })
}

//分页组件
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
            getDataList();
        }
    });
}

function getDataList() {
    var url = "/jxb/postLevel/findCounselorLevelList";
    params['teacherName'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();

        if (result.status == 0) {
            if (params.type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            var navitem = document.getElementById('tmpl-data-list').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });

            $("#data-list").html(html);
        }
    })
}

function modifyLevel(counselorId, postId) {
    $('#counselorLevelModal').modal('show');
    var url = '/jxb/postLevel/findPostLevelList';
    var data = {
        star: 0,
        page: 100
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('levelListModel').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            $("#levelList").html(html);
            $("#levelList").data('counselorId', counselorId);
            $("#levelList").data('postId', postId);
            $('#postId').val(postId)
            $('#counselorLevelModal').modal('show');

            //
            initPage();
        }
    });
}

function calculationLevel() {
    $('#calculationLevel').modal('show');
    // var url='';
}


function activeModify() {
    var counselorId = $("#levelList").data('counselorId');
    var pId = $("#levelList").data('postId');
    var postId = $('#postId').val();
    if (postId != pId) {
        var url = '/jxb/postLevel/modifyCounselorLevel';
        var data = {
            counselorId: counselorId,
            postId: postId,
        }
        $.post(url, data, function (result) {
            if (result.status == 0) {
                getDataList($('#pagination1 .active').text());
            } else {
                alert(result.errorMessage);
            }
            $('#counselorLevelModal').modal('hide');
        })
    }
}


function t_query() {
    initPage();
    return false;
}

function setParams(key, value) {
    params[key] = value;
    getDataList();
}