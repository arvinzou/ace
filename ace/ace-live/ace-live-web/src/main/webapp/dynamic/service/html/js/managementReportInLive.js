$(function () {
    init();
    /*根据状态进行查询*/
    $('.rptManageBtnList').on('change', '.rptType', searchByStatusDo);
    /*按名字搜索*/
    $('.rptManageBtnList').on('click', '.search', searchByNameDo);
    $('.rptManageBtnList').on('click', '.startSort', sortActionDo);
    $('.rptManageBtnList').on('click', '.select-all-Btn', selectAllDo);
    $('.rptManageBtnList').on('click', '.download-file', downloadFileDo);
});

function downloadFileDo() {
    console.log(1);
    $('#htmlLoad .liveInRptList .Topcheckbox input').each(function () {
        if ($(this).prop('checked')) {
            var id = $(this).parents('li').data('id');
            if (!id) {
                return;
            }
            var url = '/live/liveRpt/selectLiveRptByPrimaryKey.do';
            var data = {
                'id': id
            };
            $.getJSON(url, data, function (result) {
                actiondownload(result.value);
            });
        }
    });
}

function actiondownload(data) {
    console.log(2);
    if ('2' == data.mediaType) {
        console.log(3);
        if (data.mediaContent) {
            var url = '/live/liveImg/findLiveImgList.do';
            var datas = {
                'rptId': data.id
            };
            $.getJSON(url, datas, function (result) {
                if (result.status == 0) {
                    var imgdata = result.rows;
                    $('.preview-img img').show();
                    for (var i = 0; i < imgdata.length; i++) {
                        toUpload(imgdata[i].url);
                    }
                }
            });
        }
    } else if ('1' == data.mediaType) {
        console.log(4);
        toUpload(data.mediaContent);
    } else if ('3' == data.mediaType) {
        console.log(5);
        toUpload(data.mediaContent);
    }
}

function toUpload(fileUrl) {
    console.log(fileUrl);
    window.open(fileUrl, "navTab");
}


function selectAllDo() {
    console.log('selectAllDo');
    var text = $(this).text();
    if ('全选' == text) {
        $(this).text('取消全选');
        $('.liveInRptList .Topcheckbox input').prop({checked: true});
    } else if ('取消全选' == text) {
        $(this).text('全选');
        $('.liveInRptList .Topcheckbox input').prop({checked: false});
    }

}

function init() {
    loadReportList();
}

/*排序开始*/
function sortActionDo() {
    var text = $(this).text();
    if ('开始排序' == text) {
        $(this).text('取消排序');
        loadReportList();
        $('.rptManageBtnList .form-control').attr('disabled', true);
        $('.rptManageBtnList .search').attr('disabled', true);
        $('.liveInRptList').sortable({
            cursor: "move",
            items: "li",                        //只是li可以拖动
            opacity: 0.6,                       //拖动时，透明度为0.6
            revert: true,                       //释放时，增加动画
            stop: function (event, ui) {        //更新排序
                updateSequence($(this).sortable("toArray"));
            }
        });
    } else if ('取消排序' == text) {
        $(this).text('开始排序');
        cancelSort();
    }
}


function cancelSort() {
    $('.rptManageBtnList .form-control').attr('disabled', false);
    $('.rptManageBtnList .search').attr('disabled', false);
    $(".liveInRptList").sortable("destroy");
}


/*根据状态查找*/
function searchByStatusDo() {
    searchByNameDo();
}

/*按名字搜索报道*/
function searchByNameDo() {
    var mediaType = $('.rptManageBtnList .rptType').val();
    var inputName = $('.rptManageBtnList .searchRptByName').val();
    loadReportList(inputName, mediaType);
}

/*下载直播数据*/
function loadReportList(content, mediaType) {
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    var data = {
        'rid': $('#htmlLoad').data('rid'),
        'content': content,
        'mediaType': mediaType,
        'start': start,
        'limit': limit,
        'orderBy': 'sort',
        'status': 2,
        'sord': 'asc'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewReportList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewReportList(data) {
    $('.liveInRptList').empty();
    for (var i = 0; i < data.length; i++) {
        var btnSpace = '';
        var liReport = '';
        if (1 == data[i].mediaType) {
            liReport = reportVideoTemplate;
        } else if (2 == data[i].mediaType) {
            liReport = reportImgTemplate;
            if (data[i].mediaContent == null || data[i].mediaContent.trim() == '') {
                liReport = reportTextTemplate;
            }
        } else {
            liReport = reportAudioTemplate;
        }
        btnSpace = '<a class="download-file">下载</a>';
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[content]', data[i].content);
        liReport = liReport.replace('[createTime]', data[i].createTime.substring(0, 16));
        liReport = liReport.replace('[btnSpace]', btnSpace);
        liReport = liReport.replace('[id]', data[i].id);
        liReport = liReport.replace('[name]', data[i].nickName);
        liReport = liReport.replace('[checkbox]', checkboxTemplate);
        var $liReport = $(liReport);
        $liReport.data("id", data[i].id);
        $liReport.data("rid", data[i].rid);
        $('.liveInRptList').append($liReport);
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
            'rid': $('#htmlLoad').data('rid'),
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