var fileHost = 'http://zx.huacainfo.com/';
var start;
var limit = 25;
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
    $('#imageView').on('click', '.removeImg', removeImgDo);
    $('#videoView').on('click', '.removeImg', removeVdoDo);
});

/*删除图片*/
function removeImgDo() {
    $(this).parent().parent().remove();
    $('#j-cover').show();
}

/*删除图片*/
function removeVdoDo() {
    $(this).parent().remove();
    $('#v-cover').show();
}

/*确认发布报道*/
function releaseDo() {
    var imgs = [];
    var datas = {};
    var rpt = {
        'mediaType': mediaType,
        'content': $('.content').val(),
        'rid': id,
        'uid': 'oFvIjw8x1--0lQkUhO1Ta3L59o3c',
        'mediaContent': ""
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
        url: '/portal/files/uploadFile.do',
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
        multipart_params: {
            marktext: marktext,
            companyId: userProp.corpId
        },
        init: {
            FilesAdded: function (up, files) {
                console.log('uploadFile');
                up.start();

            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewVideo(rst.value[0]);
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
        url: '/portal/files/uploadFile.do',
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
        multipart_params: {
            marktext: marktext,
            companyId: userProp.corpId
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


/*显示视频*/
function viewVideo(videoUrl) {
    var videoUrl = fileHost + videoUrl;
    var vdoDiv = videoTempLate.replace('[videoUrl]', videoUrl);
    var $vdoDiv = $(vdoDiv).data({
        fileurl: videoUrl,
    });
    $('#v-cover').before($vdoDiv);
    if ($('#videoView').children().length >= 2) {
        $('#v-cover').hide();
    }
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



