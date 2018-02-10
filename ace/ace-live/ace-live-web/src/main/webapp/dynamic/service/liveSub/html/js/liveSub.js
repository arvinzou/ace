$(function () {
    initWeb();
    /*修改直播状态*/
    $('.sceneList').on('click', '.changeLiveStatus', changeLiveStatusDo);
    /*根据直播名查找*/
    $('.search').click(searchByNameDo);
    /*按照状态分类*/
    $('.topToolBtn').on('change', '.liveStatus', searchByStatusDo);
    /*点击修改直播内容*/
    $('.sceneList').on('click', '.picbar', modifyLiveDo);
})

/*初始化页面*/
function initWeb() {
    var url = '/portal/system/getUserProp.do';
    $.get(url, function () {
        loadLiveList();
    });
}

/*下载直播数据*/
function loadLiveList(name, liveStatus, orderByStr) {
    var url = '/live/live/findLiveList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'status': liveStatus,
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
        liLive = liLive.replace('[imageSrc]', imgHost + data[i].imageSrc);
        liLive = liLive.replace('[name]', data[i].name);
        liLive = liLive.replace('[createUserName]', data[i].createUserName);
        liLive = liLive.replace('[startTime]', data[i].startTime.substring(0, 16));
        liLive = liLive.replace('[reportNum]', data[i].reportCount);
        var status = data[i].status == 1 ? '开始直播' : (data[i].status == 2 ? '结束直播' : (data[i].status == 3 ? '恢复直播' : ''));
        liLive = liLive.replace('[status]', status);
        var $li = $(liLive).data("Liveid", data[i].id);
        $('.sceneList').append($li);
    }
}

/*切换直播状态*/
function changeLiveStatusDo() {
    console.log('切换直播');
    var id = $(this).parents('li').data('Liveid');
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
            if ("1" == status) {
                dataLive[item] = "2";
            } else if ("2" == status) {
                dataLive[item] = "3";

            } else if ("3" == status) {
                dataLive[item] = "1";
            }
        }
        dataLive['endTime'] = new Date();
    }
    var url = "/live/live/updateLive.do";
    var data = {
        'jsons': JSON.stringify(dataLive)
    };
    $.post(url, data, function (result) {
        if (result.status == 0) {
            searchByNameDo();
        } else {
            alert("操作失败，请重试。");
            searchByNameDo();
        }
    });
}

/*按名字搜索直播*/
function searchByNameDo(orderByStr) {
    var liveStatus = $('.liveStatus').val();
    var inputName = $('.searchByName').val();
    loadLiveList(inputName, liveStatus, orderByStr);
}

/*根据状态查找*/
function searchByStatusDo() {
    searchByNameDo();
}

/*修改直播信息*/
function modifyLiveDo(event) {
    var id = $(this).parents('li').data('Liveid');
    var url = '/live/live/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    }
    $('#htmlLoad').data('liveId', id);
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            $('#htmlLoad').load('./../html/createLive.html', function () {
                showModifyWeb(result.value);
            });
        }
    });
}
