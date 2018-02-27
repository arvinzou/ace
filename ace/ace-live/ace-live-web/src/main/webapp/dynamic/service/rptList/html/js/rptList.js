var marktext;
var mediaType;
$(function () {
    /*初始化页面*/
    initweb();

    $('.reportList').on('click', '.picbar', actionModifyDo);

    /*点击发布报道*/
    $('#liveReportTable').on('click', '.publication', startPublicationDo);

    $('#liveReportTable').on('click', '.preview-report', startPreviewDo);
    /*根据状态进行查询*/
    $('.topToolBtn').on('change', '#chooseStatus',searchByStatusDo);
    /*按名字搜索*/
    $('.search').on('click', searchByNameDo);
});

/*根据id查找图片*/
function findCover(id) {
    var url = '/live/liveImg/findLiveImgList.do';
    var data = {
        'rptId': id
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewImage(result.rows);
        }
    });
}

/*渲染图片*/
function viewImage(data) {
    for (var i = 0; i < data.length; i++) {
        viewImagePage(data[i].url, data[i].w, data[i].h);
    }
}

/*根据id查找直播*/
function viewLiveName(rid) {
    $('#htmlLoad').data('rid',rid);
    var url ='/live/live/selectLiveByPrimaryKey.do';
    var data = {
        'id': rid
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            var data = result.value;
            for (var item in data) {
                $('.live' + item).val(data[item]);
            }
        }
    });
}


/*显示修改报道页*/
function inputRptForm(data) {
    console.log(data);
    for (var item in data) {
        if (item == 'rid') {
            viewLiveName(data[item]);
        } else {
            $('.' + item).val(data[item]);
        }
        if (item == 'mediaType') {
            if (data[item] == '2') {
                $('.formRowVdo').hide();
                $('.formRowImg').show();
                mediaType = 2;
                chooseImageRptDo();
                findCover(data['id']);
            } else if (data[item] == '1') {
                $('.formRowVdo').show();
                $('.formRowImg').hide();
                chooseVideoRptDo();
                mediaType = 1;
                viewVideo(data['mediaContent']);
            } else {
                $('.formRowVdo').hide();
                $('.formRowImg').hide();
                mediaType = 3;
                viewAudio(data['mediaContent']);
            }
        }
        $(":radio[name='category'][value='"+mediaType+"']").prop("checked", "checked");
    }
}

/*点击修改报道*/
function actionModifyDo() {
    console.log('修改报道');
    var id = $(this).parents('li').data('id');
    console.log(id);
    if(!id){
        return;
    }
    $('#htmlLoad').data('id',id);
    $('#htmlLoad').load('./../html/floatTable.html', function () {
        $('#floatTable').load('./../html/modifyRpt.html', function () {
            $('#JSLoad').load('./../html/modifyRptJS.html', function () {
                var url =  '/live/liveRpt/selectLiveRptByPrimaryKey.do';
                var data = {
                    id: id
                };
                $.getJSON(url, data, function (result) {
                    console.log(result);
                    if (result.status == 0) {
                        inputRptForm(result.value);
                    }
                });
            });
        });
    });
}


/*根据状态查找*/
function searchByStatusDo() {
    searchByNameDo();
}

/*发布报道*/
function startPublicationDo() {
    console.log('发布报道');
    var $this = $(this);
    var id = $this.parents('li').data('id');
    var rid =$this.parents('li').data('rid');
    var url = '/live/liveRpt/updateLiveRptStatus.do';
    var data = {
        'id': id,
        'rid': rid,
        'status': 2,
        'message': JSON.stringify(
            {
                "header": {
                    "type": 3
                }
            }
        )
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            searchByNameDo();
        }
    });
}

/*按名字搜索报道*/
function searchByNameDo() {
    var status = $('#chooseStatus').val();
    var inputName = $('.searchByName').val();
    loadReportList(inputName, status);
}

/*渲染直播列表*/
function viewReportList(data) {
    $('#liveReportTable').empty();
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
        if (1 == data[i].status) {
            btnSpace = '<a class="publication">发布</a><a class="preview-report">预览</a>';
        }else{
            btnSpace = '<a class="preview-report">预览</a>';
        }
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[content]', data[i].content);
        liReport = liReport.replace('[createTime]', data[i].createTime.substring(0, 16));
        liReport = liReport.replace('[btnSpace]', btnSpace);
        liReport = liReport.replace('[id]', data[i].id);
        liReport = liReport.replace('[name]', data[i].nickName);
        var $liReport = $(liReport);
        $liReport.data("id", data[i].id);
        $liReport.data("rid", data[i].rid);
        $('#liveReportTable').append($liReport);
    }
}

/*下载直播数据*/
function loadReportList(content, status,orderByStr) {
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    var data = {
        'content': content,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'status': status,
        'sord': 'asc'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewReportList(result.rows);
        }
    })
}

/*网页初始化*/
function initweb() {
    /*下载报道列表*/
    $.get(userUrl, function () {
        loadReportList();
    })
}


/*点击预览按键*/
function startPreviewDo() {
    var id = $(this).parents('li').data('id');
    if (!id) {
        return;
    }
    var url =  '/live/liveRpt/selectLiveRptByPrimaryKey.do';
    var data = {
        'id': id
    };
    $.getJSON(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            $('#htmlLoad').load('./../html/floatTable.html', function () {
                $('#floatTable').load('./../html/previewRptWeb.html', function () {
                    viewPreviewReport(result.value);
                });
            });
        }
    });
}

function viewPreviewReport(data) {
    if('2'==data.mediaType){
        if(data.mediaContent){
            var url = '/live/liveImg/findLiveImgList.do';
            var datas = {
                'rptId': data.id
            };
            $.getJSON(url,datas,function (result) {
                if(result.status==0){
                    var imgdata=result.rows;
                    $('.preview-img img').show();
                    for(var i=0;i<imgdata.length;i++){
                        $('.preview .preview-img-'+i).prop('src',imgdata[i].url);
                    }
                }
            });
        }else{
            $('.preview .preview-left').remove();
            $('.preview .preview-right').css('width','100%');
        }
    }else if('1'==data.mediaType){
        $('.preview-left .li-video').show();
        $('.preview video').prop('src',data.mediaContent);
    }else if('3'==data.mediaType){
        $('.preview-img audio').show();
        $('.preview audio').prop('src',data.mediaContent);
    }
    $('.preview .preview-right span').text(data.content);
}



