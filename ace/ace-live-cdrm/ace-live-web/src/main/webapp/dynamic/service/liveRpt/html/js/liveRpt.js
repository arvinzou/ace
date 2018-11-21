var orderByStr = null;
var marktext;
var mediaType;
$(function () {
    /*初始化下载直播列表*/
    initweb();

    /*开始创建报道*/
    $('#liveListTable').on('click', 'a', actionCreateReportDo);
    /*按照名字查找*/
    $('.search').click(searchByNameDo);
});

/*按名字搜索直播*/
function searchByNameDo() {
    var inputName = $('.searchByName').val();
    loadLiveList(inputName);
}

/*点击添加直播报道*/
function actionCreateReportDo() {
    console.log('添加报道');
    var id = $(this).parents('tr').data('liveId');
    if (!id) {
        return;
    }
    $('#htmlLoad').data('liveId',id);
    $('#htmlLoad').load('./../html/floatTable.html', function () {
        $('#floatTable').load('./../html/createRpt.html', function () {
            $('#JSLoad').load('./../html/createRptJS.html', function () {
                var url = '/live/live/selectLiveByPrimaryKey.do';
                var data = {
                    'id': id
                };
                $.getJSON(url, data, function (result){
                    $('#htmlLoad .formRow .name').val(result.value.name);
                });
            });
        });
    });
}




/*网页初始化*/
function initweb() {
    $.get(userUrl, function (result) {
        loadLiveList();
    })
}

/*下载直播数据*/
function loadLiveList(name) {
    var url = '/live/live/findLiveList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'deptId': userProp.corpId
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewLiveList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewLiveList(data) {
    $('#liveListTable').empty();
    for (var i = 0; i < data.length; i++) {
        var trLive = liveTemplate;
        trLive = trLive.replace('[name]', data[i].name);
        trLive = trLive.replace('[createUserName]', data[i].createUserName);
        trLive = trLive.replace('[startTime]', data[i].startTime.substring(0, 16));
        var $tr = $(trLive).data("liveId", data[i].id);
        $('#liveListTable').append($tr);
    }
}

/*直播列表模板*/
var liveTemplate = '<tr>' +
    '                <td>[name]</td>' +
    '                <td>[createUserName]</td>' +
    '                <td>[startTime]</td>' +
    '                <td><a>发布报道</a></td>' +
    '</tr>';



