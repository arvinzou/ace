var fileHost = 'http://zx.huacainfo.com/';
var start;
var limit = 1000;
var orderByStr = null;
var id;
var mediaType = 2;
var uploaderI;
var uploaderV;
var userProp;
var marktext;
$(function () {
    initweb();
    $(":radio").click(chooseTypeDo);
    $('.cancel').click(hideSelectReportDo);
    $('#liveListTable').on('click', 'a', actionSelectReportDo);
    $('.search').click(searchByNameDo);
    $('.release').click(releaseDo);
    $('.reportImageUploadList').on('click', '.removeImage', removeImgDo);
    $('.reportVideoUploadList').on('click', '.removeVideo', removeVdoDo);
    $('.select_report').on('focus', '.form-control',promptDo);
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

function promptDo() {
    $('.prompt').text('');
}

/*确认发布报道*/
function releaseDo() {
    var flag=false;
    var imgs = [];
    var datas = {};
    var content=$('.content').val();
    var rpt = {
        'mediaType': mediaType,
        'content':content,
        'rid': id,
        'uid': userProp.openId,
        'mediaContent': ""
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
    var url = '/live/liveRpt/insertLiveRpt.do';
    $.post(url, {jsons: JSON.stringify(datas)}, function (result) {
        if(0==result.status){
            hideSelectReportDo();
            return;
        }
        alert("创建失败！");
    });
}

/*按名字搜索直播*/
function searchByNameDo() {
    var inputName = $('.searchByName').val();
    console.log(inputName);
    loadLiveList(inputName);
}

/*点击添加直播报道*/
function actionSelectReportDo() {
    console.log('添加报道');
    id = null;
    id = $(this).parent().parent().data('Liveid');
    if (!id) {
        return;
    }
    ;
    var url = '/live/live/selectLiveByPrimaryKey.do';
    var data = {
        id: id
    }
    $.getJSON(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            $('.formRow-content .name').val(result.value.name);
            $('.select_report').show();
            chooseImageDo();
        }
    });
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


/*显示视频*/
function viewVideo(videoUrl) {
    $('.removeVideo').show();
    $('.reportVideoUploadList video').prop('src',videoUrl);
    $('.reportVideoUploadList').data('fileurl',videoUrl);
    $('.reportVideoUploadList .uploadText').hide();
}

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

/*网页初始化*/
function initweb() {
    url = '/portal/system/getUserProp.do';
    $.get(url, function (result) {
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
        var $tr = $(trLive).data("Liveid", data[i].id);
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


var reportUploadImgTemplate='<li>'+
    '                        <div>'+
    '                            <img src="[imgPath]">'+
    '                        </div>'+
    '                        <span class="removeIcon removeImage"><i class="iconfont">&#xe628;</i></span>'+
    '                    </li>';



