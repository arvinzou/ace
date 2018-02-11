/*变量*/
var imgHost = "http://zx.huacainfo.com/";
var start;

var status;
var id;
var rid;
$(function () {
    initWeb();

    $('.sortLive').click(sortLiveByTimeDo);

    $('.sceneList').on('click', '.reportNum', viewReportListByIdDo);
    $('#reportCan').on('click', '.cancelSort', cancelSortDo);

    $('#htmlLoad').on('focus', '.form-control',promptDo);
    $('#htmlLoad').on('click',hideModifyWebDo);
    $('.report-list').sortable({
        cursor: "move",
        items: "li",                        //只是li可以拖动
        opacity: 0.3,                       //拖动时，透明度为0.6
        revert: true,                       //释放时，增加动画
        stop: function (event, ui) {        //更新排序
            updateSequence($(this).sortable("toArray"));
        }
    });
});

function promptDo() {
    $('#htmlLoad .prompt').text('');
}



/*取消排序*/
function cancelSortDo() {
    $('.report-list').empty();
    $('#reportCan').hide();
}

/*查看报道列表*/
function viewReportListByIdDo() {
    rid = $(this).parents('li').data('Liveid');
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    var data = {
        'rid': rid,
        'start': start,
        'limit': 1000,
        'status': 2,
        'orderBy': 'sort',
        'sord': 'asc'

    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            $('.report-temp').show();
            viewReportList(result.rows);
        }
    })
    return false;
}


/*渲染直播列表*/
function viewReportList(data) {
    console.log('viewList');
    console.log(data);
    $('#reportCan').show();
    var publication = '';
    $('.report-list').empty();
    for (var i = 0; i < data.length; i++) {
        var liReport = '';
        if (1 == data[i].mediaType) {
            liReport = reportVideoTempla;
        } else if (2 == data[i].mediaType) {
            liReport = reportImgTemplate;
            if (data[i].mediaContent == null || data[i].mediaContent.trim() == '') {
                liReport = reportTextTemplate;
            }
        } else {
            liReport = reportAudioTempla;
        }
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[content]', data[i].content);
        liReport = liReport.replace('[createTime]', data[i].createTime.substring(0, 16));
        liReport = liReport.replace('[publication]', publication);
        liReport = liReport.replace('[id]', data[i].id);
        liReport = liReport.replace('[name]', data[i].nickName);
        var $liReport = $(liReport);
        $('.report-list').append($liReport);
    }
}


















function updateSequence(arr) {
    var data = [];
    for (var i = 0; i < arr.length; i++) {
        data.push({id: arr[i], index: i});
    }
    console.log(JSON.stringify(data));

    $.ajax({
        type: "post",
        url: "/live/liveRpt/updateSequence.do",
        data: {
            data: JSON.stringify(data),
            'rid': rid,
            'message': JSON.stringify(
                {
                    "header": {
                        "type": 3
                    }
                }
            )
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            if (!rst.state) {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.errorMessage,
                    detail: rst.detail,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                            }
                        }
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        error: function () {

        }
    });
}