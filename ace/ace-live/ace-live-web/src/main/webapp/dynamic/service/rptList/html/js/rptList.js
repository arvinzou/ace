var fileHost = 'http://zx.huacainfo.com/';
var start;
var limit =1000;
var orderByStr = null;
var id;
var rid;
var mediaType = 2;
var uploaderI;
var uploaderV;
var userProp;
var marktext;

$(function () {
    initweb();
    $(":radio").on('click', chooseTypeDo);
    $('.cancel').on('click', hideSelectReportDo);
    $('.search').on('click', searchByNameDo);
    $('.release').on('click', releaseDo);
    $('.reportList').on('click', '.picbar', actionModifyDo);
    $('.previewPage').on('click', hidePreviewPageDo);
    $('.reportImageUploadList').on('click', '.removeImage', removeImgDo);
    $('.reportVideoUploadList').on('click', '.removeVideo', removeVdoDo);
    $('#liveReportTable').on('click', '.publication', startPublicationDo);
    $('#liveReportTable').on('click', '.preview-report', startPreviewDo);
    $('.topToolBtn').on('change', '#chooseStatus',searchByStatusDo);
});


/*删除图片*/
function removeImgDo() {
    $(this).parents('li').remove();
    $('.Iupbtn').show();
}

/*删除图片*/
function removeVdoDo() {
    $('.removeVideo').hide();
    $('.reportVideoUploadList video').prop('src','');
    $('.reportVideoUploadList').data('fileurl',null);
    $('.reportVideoUploadList .uploadText').show();
    return false;
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
            console.log(result);
            $('.previewPage').show()
            $('.previewWeb-content').empty();
            viewPreviewReport(result.value,id);
        }
    });
}

function viewPreviewReport(data,id) {
    var TStr='<div class="previewWeb-text">'+data.content+'</div>';
    $('.previewWeb-content').append($(TStr));
    if('2'==data.mediaType){
        var url = '/live/liveImg/findLiveImgList.do';
        var datas = {
            'rptId': id
        };
        $.getJSON(url,datas,function (result) {
              if(result.status==0){
                  var imgdata=result.rows;
                  for(var i=0;i<imgdata.length;i++){
                      var imgStr='<div class="previewWeb-medio"><img src="'+imgdata[i].url+'"></div>';
                      $('.previewWeb-content').append($(imgStr));
                  }
              }
        });
    }else if('1'==data.mediaType){
        var VStr='<div class="previewWeb-medio"><video controls="controls" src="'+data.mediaContent +'"></video></div>';
        $('.previewWeb-content').append($(VStr));
    }else if('3'==data.mediaType){
        var AStr='<vid class="previewWeb-medio"><audio controls="controls"><source src="'+data.mediaContent+'" type="audio/mpeg"></audio></vid>';
        $('.previewWeb-content').append($(AStr));
    }
}






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
    id = $(this).parents('li').data('id');
    rid = $(this).parents('li').data('rid');
    console.log(id);
    var url =  '/live/liveRpt/selectLiveRptByPrimaryKey.do';
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
            $('.' + item + 'Rpt1').val(data[item]);
        }
        if (item == 'mediaType') {
            if (data[item] == '2') {
                $('.formRowVdo').hide();
                $('.formRowImg').show();
                mediaType = 2;
                chooseImageDo();
                findCover(data['id']);
            } else if (data[item] == '1') {
                $('.formRowVdo').show();
                $('.formRowImg').hide();
                chooseVideoDo();
                mediaType = 1;
                viewVideo(data['mediaContent'])
            } else {
                $('.formRowVdo').hide();
                $('.formRowImg').hide();
                mediaType = 3;
                viewAudio(data['mediaContent'])
            }
        }
        $(":radio[name='category'][value='"+mediaType+"']").prop("checked", "checked");
    }
}

/*根据id查找直播*/
function viewLiveName(id) {
    var url ='/live/live/selectLiveByPrimaryKey.do';
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

/*确认发布报道*/
function releaseDo() {
    var flag=false;
    var imgs = [];
    var datas = {};
    var content=$('.contentRpt1').val();
    var rpt = {
        'id':id,
        'mediaType': mediaType,
        'content': content,
        'rid': rid,
        'uid': userProp.openId,
        'mediaContent': '',
        'createTime': $('.createTimeRpt1').val() + ':00',
    };
    if (2 == mediaType) {
        $(".reportImageUploadList li").each(function () {
            var e = $(this).data("fileurl");
            var r = $(this).data("width");
            var i = $(this).data("height");
            if (e) {
                flag=true;
                imgs.push({
                    url: e,
                    w: r,
                    h: i
                })
            }
        });

    } else {
        var e = $(".reportVideoUploadList").data("fileurl");
        if(!e){
            $('.prompt').text('未上传视频');
            return;
        }
        rpt.mediaContent = e;
    }
    if(!(content||flag)){
        $('.prompt').text('内容和图片不能都为空');
        return;
    }
    datas.imgs = imgs;
    datas.rpt = rpt;
    var url = '/live/liveRpt/updateLiveRpt.do';
    $.post(url, {jsons: JSON.stringify(datas)}, function (result) {
        if(0==result.status){
            hideSelectReportDo();
            return;
        }
        alert("创建失败！");
    });
}

/*按名字搜索报道*/
function searchByNameDo() {
    var status = $('#chooseStatus').val();
    var inputName = $('.searchByName').val();
    loadReportList(inputName, status);
}


/*点击隐藏发布直播页*/
function hideSelectReportDo() {
    window.location.reload();
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
        url: '/live/www/live/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [
                {
                    title: "video files",
                    extensions: "3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv,mp3,acc,wav",
                    prevent_duplicates: true,
                }
            ]
        },
        multipart_params: {
            marktext: marktext,
            companyId: userProp.corpId
        },
        init: {
            FilesAdded: function (up, files) {
                $('.prompt').text('');
                up.start();
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $(".uploadVideoPloadprogress").html("点击上传视频");
                if (rst.success) {
                    var videourl = fileHost + rst.file_path;
                    viewVideo(videourl);
                }
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadVideoPloadprogress").html("开始上传（" + r + "%）");
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
        url: '/live/www/live/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: {
            max_file_size: '2048mb',
            mime_types: [
                {
                    title: "Image files",
                    extensions: "jpg,gif,png",
                    prevent_duplicates: true,
                }
            ]
        },
        multipart_params: {
            marktext: marktext,
            companyId: userProp.corpId
        },
        init: {
            FilesAdded: function (up, files) {
                $('.prompt').text('');
                up.start();
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadImagePloadprogress").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                console.log(rst);
                $(".uploadImagePloadprogress").html("点击上传图片");
                if (rst.success) {
                    handleImage(rst.file_path);
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
    $('.formRowVdo').hide();
    $('.formRowImg').hide();
}

/*网页初始化*/
function initweb() {
    /*下载报道列表*/
    $('.previewPage').hide();
    var url = '/portal/system/getUserProp.do';
    $.get(url, function () {
        loadReportList();
    })
}

/*下载直播数据*/
function loadReportList(content, status) {
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    var data = {
        'content': content,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'status': status,
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewReportList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewReportList(data) {
    $('#liveReportTable').empty();
    for (var i = 0; i < data.length; i++) {
        var publication = '';
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
        if (1 == data[i].status) {
            publication = '<a class="publication">发布</a>';
        }
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[content]', data[i].content);
        liReport = liReport.replace('[createTime]', data[i].createTime.substring(0, 16));
        liReport = liReport.replace('[publication]', publication);
        liReport = liReport.replace('[name]', data[i].nickName);
        var $liReport = $(liReport);
        $liReport.data("id", data[i].id);
        $liReport.data("rid", data[i].rid);
        $('#liveReportTable').append($liReport);
    }
}

/*直播列表模板*/
var reportTextTemplate = '<li>' +
    '            <div class="picbar">' +
    '                <div class="textContent">[content]</div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '            	<span class="omission msgbar-common creater"> ' +
    '            		<i class="iconfont">&#xe61a;</i>[name]' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i>[createTime]' +
    '            	</span>[publication]<a class="preview-report">预览</a>' +
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
    '            		<i class="iconfont">&#xe61a;</i>[name] ' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i> [createTime] ' +
    '            	</span>[publication]<a class="preview-report">预览</a>' +
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
    '                    <i class="iconfont">&#xe61a;</i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="iconfont">&#xe651;</i>[createTime]' +
    '                </span>[publication]<a class="preview-report">预览</a>' +
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
    '                    <i class="iconfont">&#xe61a;</i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="iconfont">&#xe651;</i>[createTime]' +
    '                </span>[publication]<a class="preview-report">预览</a>' +
    '            </div>' +
    '        </li>';


$("#createTime").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
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


function viewImagePage(o, e, t) {
    var imgli = reportUploadImgTemplate.replace('[imgPath]', o);
    var $imgli = $(imgli).data({
        fileurl: o,
        width: e,
        height: t
    });
    $('.Iupbtn').before($imgli);
    if ($('.reportImageUploadList').children().length >= 5) {
        $('.Iupbtn').hide();
    }
}


/*显示视频*/
function viewVideo(videoUrl) {
    $('.removeVideo').show();
    $('.reportVideoUploadList video').prop('src',videoUrl);
    $('.reportVideoUploadList').data('fileurl',videoUrl);
    $('.reportVideoUploadList .uploadText').hide();
}

function viewAudio(videoUrl) {

}

var reportUploadImgTemplate='<li>'+
    '                        <div>'+
    '                            <img src="[imgPath]">'+
    '                        </div>'+
    '                        <span class="removeIcon removeImage"><i class="iconfont">&#xe628;</i></span>'+
    '                    </li>';


