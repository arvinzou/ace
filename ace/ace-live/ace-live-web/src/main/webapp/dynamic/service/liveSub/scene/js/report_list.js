/*变量*/
var host = 'http://192.168.2.153';
var imghost = "http://zx.huacainfo.com/";
var start = 0;
var limit = 10;
var orderByStr = 'createTime';


//windows.load
$(function () {
    //
    initData();

    //
    $(".sortable").sortable({
        cursor: "move",
        items: "li",                        //只是li可以拖动
        opacity: 0.3,                       //拖动时，透明度为0.6
        revert: true,                       //释放时，增加动画
        stop: function (event, ui) {        //更新排序
            updateSequence($(this).sortable("toArray"));
        }
    });
});

//getData
function initData() {
    var url = host + '/liveRpt/findLiveRptList.do';
    var data = {
        'rid': 'c15f484b-bd30-4111-904d-123ca617180e',
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
    }
    $.getJSON(url, data, function (result) {
        console.log(JSON.stringify(result))
        if (result.status == 0) {
            initSortableDiv(result.rows);
        }
    })
}

//draw page
function initSortableDiv(data) {
    $('.sortable').empty();
    console.log(JSON.stringify(data));
    for (var i = 0; i < data.length; i++) {
        var liLiveRpt = sortLiTemplate;
        liLiveRpt = liLiveRpt.replace("[objId]", data[i].id)
            .replace("[title]", data[i].content);

        var $li = $(liLive).data("rptId", data[i].id);
        $('.sortable').append($li);
    }
}

var sortLiTemplate = '<li id="[objId]" class="ui-state-default">[title]</li>';

//
function updateSequence(arr) {
    var data = [];
    for (var i = 0; i < arr.length; i++) {
        data.push({id: arr[i], index: i});
    }
    console.log(JSON.stringify(data));

    $.ajax({
        type: "post",
        url: host + "/updateSequence.do",
        data: {jsons: JSON.stringify(data)},
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

