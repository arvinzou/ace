var host = 'http://192.168.2.153/live';
var fileHost = 'http://zx.huacainfo.com/';
var start;
var limit = 25;
var orderByStr = null;
var id;
var mediaType = 2;
var uploaderI;
var uploaderV;
$(function () {
    initweb();
    $(":radio").click(chooseTypeDo);
    $('.cancel').click(hideSelectReportDo);
    $('.search').click(searchByNameDo);
    $('.release').click(releaseDo);
    $('.reportList').on('click', '.picbar', actionModifyDo);
    $('.previewPage').click(hidePreviewPageDo);
    $('.msgbar').on('click', 'a', startPreviewDo);
    $('#imageView').on('click', '.removeImg', removeImgDo);
    $('#videoView').on('click', '.removeImg', removeVdoDo);
});

/*处理图片*/
function handleImage(imageUrl) {
    var o = fileHost + imageUrl, l = new Image;
    l.src = o,
        l.onload = l.onerror = function () {
            var e = l.width, t = l.height;
            viewImagePage(o, e, t);
        }
}

/*删除图片*/
function removeImgDo() {
    $(this).parent().parent().remove();
    $('#j-cover').show();
}

/*删除视频*/
function removeVdoDo() {
    $(this).parent().remove();
    $('#v-cover').show();
}

/*点击预览按键*/
function startPreviewDo() {
    var id = $(this).parent().parent().data('Liveid');
    var data = findCover(id);
    console.log(data);
}

/*取消预览*/
function hidePreviewPageDo(event) {
    if (event.eventPhase == '2') {
        $('.previewWeb-content').empty();
        $(this).hide();
    }
    return false;
}

/*点击修改报道*/
function actionModifyDo() {
    console.log('修改报道');
    id = $(this).parent().data('Liveid');
    console.log(id);
    var url = host + '/liveRpt/selectLiveRptByPrimaryKey.do';
    var data = {
        'id': id
    };
    $.getJSON(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            showModifyWeb(result.value);
        }
    });
}

/*显示修改报道页*/
function showModifyWeb(data) {
    console.log(data);
    $('.select_report').show();
    for (var item in data) {
        if (item == 'rid') {
            viewLiveName(data[item]);
        } else {
            $('.' + item + 'Rpt').val(data[item]);
        }
        if (item == 'mediaType') {
            if (data[item] == '2') {
                $('.formRowVdo').hide();
                $('.formRowImg').show();
                mediaType=2;
                chooseImageDo();
                $(":radio[name='category'][value='2']").prop("checked", "checked");
                findCover(data['id']);
            } else if (data[item] == '1') {
                $('.formRowVdo').show();
                $('.formRowImg').hide();
                chooseVideoDo();
                mediaType=1;
                $(":radio[name='category'][value='1']").prop("checked", "checked");
                viewVideo(data['mediaContent'])
            } else {
                $(":radio[name='category'][value='3']").prop("checked", "checked");
                mediaType=3;
            }
        }
    }
}

function viewImage(data) {
    console.log(data);
    for (var i = 0; i < data.length; i++) {
        viewImagePage(data[i].url, data[i].w, data[i].h);
    }
}

/*根据id查找直播*/
function viewLiveName(id) {
    var url = host + '/live/selectLiveByPrimaryKey.do';
    var data = {
        'id': id
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            var data = result.value;
            for (var item in data) {
                $('.' + item).val(data[item]);
            }
        }
    });
}

/*根据id查找图片*/
function findCover(id) {
    var url = host + '/liveImg/findLiveImgList.do';
    var data = {
        'rptId': id
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewImage(result.rows);
        }
    });
}


/*确认发布报道*/
function releaseDo() {
    var url =host+ '/liveRpt/deleteLiveRptAndImgLiveByRptId.do';
    var data = {
        'id': id,
    }
    $.getJSON(url, data, function (result) {
        if (0 == result.status) {
            actionUpdataRpt();
        }
    });
}

/*开始修改报道*/

function actionUpdataRpt() {
    var imgs = [];
    var datas = {};
    var rpt = {
        'mediaType': mediaType,
        'content': $('.contentRpt').val(),
        'rid': id,
        'uid': 'oFvIjw8x1--0lQkUhO1Ta3L59o3c',
        'mediaContent': '',
        'createTime': $('.createTimeRpt').val() + ':00',
    };
    if (2 == mediaType) {
        $("#imageView .xcy-cutimg").each(function () {
            var e = $(this).data("fileurl");
            var r = $(this).data("width");
            var i = $(this).data("height");
            if (e) {
                imgs.push({
                    url: e,
                    w: r,
                    h: i
                })
            }
        });

    } else {
        var e = $("#videoView .xcy-cutimg").data("fileurl");
        rpt.mediaContent = e;
    }
    datas.imgs = imgs;
    datas.rpt = rpt;
    var url = host + '/liveRpt/insertLiveRpt.do';
    $.post(url, {jsons: JSON.stringify(datas)}, function (result) {
        console.log(result);
    });
}

/*按名字搜索报道*/
function searchByNameDo() {
    var inputName = $('.searchByName').val();
    console.log(inputName);
    loadLiveList(inputName);
}


/*点击隐藏发布直播页*/
function hideSelectReportDo() {
    $('.xcy-cutimg').remove();
    $('.select_report').hide();
}

/*选择报道类型*/
function chooseTypeDo() {
    mediaType = $(this).val();
    console.log(mediaType);
    switch (parseInt(mediaType)) {
        case 1:
            chooseVideoDo();
            break;
        case 2:
            chooseImageDo();
            break;
        case 3:
            chooseAudioDo();
    }
}

function chooseVideoDo() {
    console.log('视频');
    $('.formRowVdo').show();
    $('.formRowImg').hide();
    if (uploaderV) {
        return;
    }
    /*文件上传*/
    uploaderV = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'Vupbtn',
        url: 'http://192.168.2.153/live/www/live/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '20mb',
            mime_types: [
                {
                    title: "video files",
                    extensions: "3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv,mp3,acc,wav",
                    prevent_duplicates: true,
                }
            ]
        },
        init: {
            FilesAdded: function (up, files) {
                console.log('uploadFile');
                up.start();

            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                var videourl = fileHost + rst.value[0];
                viewVideo(videourl);
            },
            Error: function (e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
            }
        }
    });
    uploaderV.init();
}

function chooseImageDo() {
    console.log('图片');
    $('.formRowVdo').hide();
    $('.formRowImg').show();
    if (uploaderI) {
        return;
    }
    /*文件上传*/
    uploaderI = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'Iupbtn',
        url: 'http://192.168.2.153/live/www/live/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '20mb',
            mime_types: [
                {
                    title: "Image files",
                    extensions: "jpg,gif,png",
                    prevent_duplicates: true,
                }
            ]
        },

        init: {
            FilesAdded: function (up, files) {
                console.log('uploadFile');
                up.start();
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                if (0 == rst.status) {
                    handleImage(rst.value[0]);
                }
            },
            Error: function (e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
            }
        }
    });
    uploaderI.init();
}


function chooseAudioDo() {

}

/*网页初始化*/
function initweb() {
    /*下载报道列表*/
    loadReportList();
    $('.previewPage').hide();
}

/*下载直播数据*/
function loadReportList(name) {
    console.log('loadReportList');
    var url = host + '/liveRpt/findLiveRptList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewReportList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewReportList(data) {
    console.log('viewList');
    console.log(data);
    $('#liveReportTable').empty();
    for (var i = 0; i < data.length; i++) {
        var liReport = '';
        if (data[i].mediaContent) {
            liReport = reportImgTemplate;
        }
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
        var $li = $(liReport).data("Liveid", data[i].id);
        $('#liveReportTable').append($li);
    }
}

/*直播列表模板*/
var reportTextTemplate = '<li>' +
    '            <div class="picbar">' +
    '                <div class="textContent">[content]</div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '            	<span class="omission msgbar-common creater"> ' +
    '            		<i class=""></i>[名字]' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="icon-clock"></i>[createTime]' +
    '            	</span><a class="">预览</a>' +
    '            </div>' +
    '        </li>';

var reportImgTemplate = '<li>' +
    '            <div class="picbar">' +
    '                <div class="title omission">[content]</div>' +
    '                <div class="pic">' +
    '                    <img src="[mediaContent]">' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar"> ' +
    '            	<span class="omission msgbar-common creater"> ' +
    '            		<i class=""></i> [名字] ' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="icon-clock"></i> [createTime] ' +
    '            	</span> <a>预览</a>' +
    '            </div>' +
    '        </li>';

var reportVideoTempla = '<li>' +
    '            <div class="picbar">' +
    '                <div class="title omission">[content]</div>' +
    '                <div class="pic">' +
    '                    <video src="[mediaContent]"></video>' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '                <span class="omission msgbar-common creater">' +
    '                    <i class=""></i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="icon-clock"></i>[createTime]' +
    '                </span><a>发布</a>' +
    '            </div>' +
    '        </li>';


var reportAudioTempla = '<li>' +
    '            <div class="picbar">' +
    '                <div class="title omission">[content]</div>' +
    '                <div class="pic">' +
    '                 <audio controls="controls">' +
    '                           <source src="[mediaContent]" type="audio/mpeg">' +
    '                 </audio>' +
    '                </div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '                <span class="omission msgbar-common creater">' +
    '                    <i class=""></i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="icon-clock"></i>[createTime]' +
    '                </span><a>发布</a>' +
    '            </div>' +
    '        </li>';


$("#createTime").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
});


var videoTempLate = '<div class="xcy-cutimg">' +
    '                        <label class="upbtn">' +
    '                            <video src="[videoUrl]"></video>' +
    '                        </label>' +
    '                        <span class="removeImg">X</span>' +
    '                    </div>';


var imgTemplate = '<div class="xcy-cutimg">' +
    '             <label  class="upbtn">' +
    '                  <div class="imgbar">' +
    '                          <img src="[imgPath]">' +
    '                  </div>' +
    '                   <span class="removeImg">X</span>' +
    '              </label>' +
    '</div>';

/*显示图片*/
function viewImagePage(o, e, t) {
    var imgDiv = imgTemplate.replace('[imgPath]', o);
    var $imgDiv = $(imgDiv).data({
        fileurl: o,
        width: e,
        height: t
    });
    $('#j-cover').before($imgDiv);
    if ($('#imageView').children().length >= 5) {
        $('#j-cover').hide();
    }
}


/*显示视频*/
function viewVideo(videoUrl) {
    var vdoDiv = videoTempLate.replace('[videoUrl]', videoUrl);
    var $vdoDiv = $(vdoDiv).data({
        fileurl: videoUrl,
    });
    $('#v-cover').before($vdoDiv);
    if ($('#videoView').children().length >= 2) {
        $('#v-cover').hide();
    }
}


