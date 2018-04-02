var orderByStr = '';

$(function () {
    initWeb();
    /*修改直播状态*/
    $('.sceneList').on('click', '.changeLiveStatus', changeLiveStatusDo);
    $('.sceneList').on('click', '.reportNum', viewReportListByIdDo);
    /*根据直播名查找*/
    $('.search').click(searchByNameDo);
    /*按照状态分类*/
    $('.topToolBtn').on('change', '.jxbStatus', searchByStatusDo);
    /*点击修改直播内容*/
    $('.sceneList').on('click', '.picbar', modifyLiveDo);
    /*按时间排序*/
    $('.topToolBtn').on('click', '.sortLive', sortLiveByTimeDo);

     /*按照状态分类*/
        $('#type').on('change', searchByStatusDo);
});

/*查看报道列表*/
function viewReportListByIdDo() {
    var rid = $(this).parents('li').data('Liveid');
    if (!rid) {
        return;
    }
    $('#htmlLoad').data('rid', rid);
    $('#htmlLoad').load('./../html/managementReportInLive.html', function () {
        $('#JSLoad').load('./../html/managementReportInLiveJS.html', function () {

        });
    });
    return false;
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
    searchByNameDo();
}


/*初始化页面*/
function initWeb() {
    $.get(userUrl, function () {
        loadLiveList();
    });
}

/*下载直播数据*/
function loadLiveList(name, jxbStatus,type) {
    var url = '/jxb/jxb/findLiveList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'status': jxbStatus,
        'deptId': '0010007',
        'sord': 'asc',
        'type':type
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
    var dataType = {
        "01" : "亲子关系",
        "02" : "婚姻家庭",
        "03" : "情绪调控",
        "04" : "职场压力"
    };
    for (var i = 0; i < data.length; i++) {
        var liLive = jxbTemplate;
        liLive = liLive.replace('[imageSrc]', imgHost + data[i].imageSrc);
        liLive = liLive.replace('[name]', data[i].name);
        liLive = liLive.replace('[type]', dataType[data[i].type]);
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
    var url = '/jxb/jxb/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            modifyStatus(result.value);
        }
    });
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
    var url = "/jxb/jxb/updateLive.do";
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
function searchByNameDo() {
    var jxbStatus = $('.jxbStatus').val();
    var inputName = $('.searchByName').val();
    var type = $('#type').val();
    loadLiveList(inputName, jxbStatus,type);
}

/*根据状态查找*/
function searchByStatusDo() {
    searchByNameDo();
}

/*修改直播信息*/
function modifyLiveDo(event) {
    var id = $(this).parents('li').data('Liveid');
    if (!id) {
        return;
    }
    var url = '/jxb/jxb/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    }
    $('#htmlLoad').data('jxbId', id);
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            $('#htmlLoad').load('./../html/floatTable.html', function () {
                $('#floatTable').load('./../html/liveForm.html', function () {
                    $('#JSLoad').load('./../html/modifyLiveJS.html', function () {
                        showModifyWeb(result.value);
                    })
                });
            });
        }
    });
}

/*进入修改页*/
function showModifyWeb(data) {
    $('.modify').show();
     $(function(){
            var staticDictObjects;
            if (!staticDictObjects) {
                staticDictObjects = parent.staticDictObject;
            }
            var dict=staticDictObjects['121'];
            for (var i = 0; i <dict .length; i++) {


                if(data.type==+dict[i].CODE){
                     $(".formContenRight").find("#type").append("<option value='"+dict[i].CODE+"' selected>"+dict[i].NAME+"</option>");
                }else{
                     $(".formContenRight").find("#type").append("<option value='"+dict[i].CODE+"'>"+dict[i].NAME+"</option>");
                }
            }
        });
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

/*图片上传成功后*/
function viewCover(img) {
    $('.pictureContainer').data('imgSrc', img);
    var imagePath = imgHost + img;
    $('.viewPicture img').prop('src', imagePath);
    $('.uploadText').hide();
}
$(function(){
    var staticDictObjects;
    if (!staticDictObjects) {
        staticDictObjects = parent.staticDictObject;
    }
    var dict=staticDictObjects['121'];
    for (var i = 0; i <dict .length; i++) {
        $("#type").append("<option value='"+dict[i].CODE+"'>"+dict[i].NAME+"</option>");
    }
});
