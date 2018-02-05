/*变量*/
var imghost = "http://zx.huacainfo.com/";
var start;
var limit = 25;
var userProp;
var status;
var id;
$(function () {
    initWeb();
    $('.sceneList').on('click', '.changeLiveStatus', changeLiveStatusDo);
    $('.search').click(searchByNameDo);
    $('.sortLive').click(sortLiveByTimeDo);
    $('.sceneList').on('click', '.picbar', modifyLiveDo);
    $('.sceneList').on('click', '.reportNum', viewReportListByIdDo);
    $('#reportCan').on('click', '.cancelSort', cancelSortDo);
    $('.cancel').click(hideModifyWebDo);
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

/*取消排序*/
function cancelSortDo() {
    $('#reportCan').empty();
    $('#reportCan').hide();
}

/*查看报道列表*/
function viewReportListByIdDo() {
    var id = $(this).parent().parent().data('Liveid');
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    var data = {
        'rid': id,
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
        var $liReport = $(liReport);
        $('.report-list').append($liReport);
    }
}

/*按开始时间排序*/
function sortLiveByTimeDo() {
    var flag = $(this).data('flag');
    if (flag) {
        orderByStr = null;
        $(this).data('flag', false);
    } else {
        orderByStr = 'startTime';
        $(this).data('flag', true);
    }
    searchByNameDo(orderByStr);
}

/*切换直播状态*/
function changeLiveStatusDo() {
    console.log('切换直播');
    var id = $(this).parent().parent().data('Liveid');
    var url = '/live/live/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            modifyStatus(result.value);
        }
    })
}

/*更改状态*/
function modifyStatus(dataLive) {
    for (var item in dataLive) {
        if (item == 'status') {
            var status = dataLive[item];
            if ("2" == status) {
                dataLive[item] = "1";
            } else {
                dataLive[item] = "2";
                dataLive['endTime'] = new Date();
            }
        }
    }
    var url = "/live/live/updateLive.do";
    var data = {
        'jsons': JSON.stringify(dataLive)
    };
    $.post(url, data, function (result) {
        if (result.status == 0) {
            loadLiveList();
        } else {
            alert("操作失败，请重试。");
            loadLiveList();
        }
    });
}

/*按名字搜索直播*/
function searchByNameDo(orderByStr) {
    var inputName = $('.searchByName').val();
    loadLiveList(inputName, orderByStr);
}

/*下载直播数据*/
function loadLiveList(name, orderByStr) {
    var url = '/live/live/findLiveList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'deptId': userProp.corpId,
        'sord': 'asc'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewLiveList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewLiveList(data) {
    $('.sceneList').empty();
    for (var i = 0; i < data.length; i++) {
        var liLive = liveTemplate;
        liLive = liLive.replace('[imageSrc]', imghost + data[i].imageSrc);
        liLive = liLive.replace('[name]', data[i].name);
        liLive = liLive.replace('[createUserName]', data[i].createUserName);
        liLive = liLive.replace('[startTime]', data[i].startTime.substring(0, 16));
        liLive = liLive.replace('[reportNum]', data[i].reportCount);
        var status = data[i].status == 2 ? '恢复直播' : (data[i].status == 1 ? '开始直播' : '');
        liLive = liLive.replace('[status]', status);
        var $li = $(liLive).data("Liveid", data[i].id);
        $('.sceneList').append($li);
    }
}


/*隐藏修改页面*/
function hideModifyWebDo() {
    $('.modify').hide();
    $(".modify .form-control").val('');
    $('.imgbar').empty();
}

/*修改直播信息*/
function modifyLiveDo(event) {
    console.log(event);
    id = null;
    console.log('修改直播');
    id = $(this).parent().data('Liveid');
    var url = '/live/live/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            showModifyWeb(result.value);
        }
    });
}

/*进入修改页*/
function showModifyWeb(data) {
    $('.modify').show();
    for (var item in data) {
        if (item == 'imageSrc') {
            viewCover(data[item]);
        } else if (item == 'category') {
            $(":radio[name='category'][value='" + data[item] + "']").prop("checked", "checked");
        } else if (item == 'status') {
            status = data[item];
        } else {
            $('.' + item).val(data[item]);
        }
    }
}


/*初始化页面*/
function initWeb() {
    start = 0;
    $('.modify').hide();
    $('.report-temp').hide();
    var url = '/portal/system/getUserProp.do';
    $.get(url, function () {
        console.log(userProp);
        /*下载直播数据*/
        loadLiveList();
    });
}


var liveTemplate = ' <li>' +
    '             <div class="picbar">' +
    '                <div class="pic">' +
    '                    <img src="[imageSrc]">' +
    '                </div>' +
    '                <span class="reportNum">[reportNum]条报道' +
    '                </span>' +
    '                <div class="livetitle omission">[name]</div>' +
    '            </div>' +
    '            <div class="msgbar fn-clear"> ' +
    '            	<span class="omission msgbar-common creater">' +
    '            		<i class="iconfont">&#xe61a;</i>[createUserName]' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i>[startTime]' +
    '            	</span>' +
    '                <a class="changeLiveStatus">[status]</a>' +
    '            </div>' +
    '        </li>';


function updateSequence(arr) {
    var data = [];
    for (var i = 0; i < arr.length; i++) {
        data.push({id: arr[i], index: i});
    }
    console.log(JSON.stringify(data));

    $.ajax({
        type: "post",
        url: "/live/liveRpt/updateSequence.do",
        data: {data: JSON.stringify(data)},
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


/*直播列表模板*/
var reportTextTemplate = '<li id="[id]">' +
    '            <div class="picbar">' +
    '                <div class="textContent">[content]</div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '            	<span class="omission msgbar-common creater"> ' +
    '            		<i class="iconfont">&#xe61a;</i>[名字]' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i>[createTime]' +
    '            	</span>' +
    '            </div>' +
    '        </li>';

var reportImgTemplate = '<li id="[id]">' +
    '            <div class="picbar">' +
    '                <div class="title1 omission">[content]</div>' +
    '                <div class="pic">' +
    '                    <img src="[mediaContent]">' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar"> ' +
    '            	<span class="omission msgbar-common creater"> ' +
    '            		<i class="iconfont">&#xe61a;</i>[名字] ' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i> [createTime] ' +
    '            	</span>' +
    '            </div>' +
    '        </li>';

var reportVideoTempla = '<li id="[id]">' +
    '            <div class="picbar">' +
    '                <div class="title1 omission">[content]</div>' +
    '                <div class="pic">' +
    '                    <video src="[mediaContent]"></video>' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '                <span class="omission msgbar-common creater">' +
    '                    <i class="iconfont">&#xe61a;</i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="iconfont">&#xe651;</i>[createTime]' +
    '                </span>' +
    '            </div>' +
    '        </li>';


var reportAudioTempla = '<li id="[id]">' +
    '            <div class="picbar">' +
    '                <div class="title1 omission">[content]</div>' +
    '                <div class="pic">' +
    '                 <audio controls="controls">' +
    '                           <source src="[mediaContent]" type="audio/mpeg">' +
    '                 </audio>' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '                <span class="omission msgbar-common creater">' +
    '                    <i class="iconfont">&#xe61a;</i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="iconfont">&#xe651;</i>[createTime]' +
    '                </span>' +
    '            </div>' +
    '        </li>';