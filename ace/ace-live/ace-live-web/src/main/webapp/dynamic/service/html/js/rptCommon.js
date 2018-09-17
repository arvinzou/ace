$(function () {
    init();
    $('.reportImageUploadList').on('click', '.removeImage', removeImgDo);
    $('.reportVideoUploadList').on('click', '.removeVideo', removeVdoDo);
    $('.formRow').on('keyup', 'textarea', computedNumDo);
    $(":radio").on('click', chooseRptTypeDo);
    $('.html-margin').on('click','.release',releaseDo);
});

/*直播名字字数的计算*/
function computedNumDo() {
    promptDo('');
    var that = $(this);
    var nowWordNumber = that.val().length;
    var $span = that.parents('.formRow').find('span');
    if (!$span.length) {
        return;
    }
    var spanText=$span.text();
    var num=spanText.substring(spanText.indexOf('/')+1,spanText.length);
    if (nowWordNumber > num) {
        $span.css('color', '#f00');
    } else {
        $span.css('color', '#666');
    }
    $span.text(nowWordNumber + '/' + num);
}


/*选择报道类型*/
function chooseRptTypeDo() {
    mediaType = $(this).val();
    console.log(mediaType);
    switch (parseInt(mediaType)) {
        case 1:
            chooseVideoRptDo();
            break;
        case 2:
            chooseImageRptDo();
            break;
        case 3:
            chooseAudioRptDo();
    }
}

function init() {
    mediaType = 2;
    chooseImageRptDo();
}

/*删除图片*/
function removeImgDo() {
    $(this).parents('li').remove();
    $('.Iupbtn').show();
}

/*删除图片*/
function removeVdoDo() {
    $('.removeVideo').hide();
    $('.reportVideoUploadList video').prop('src', '');
    $('.reportVideoUploadList').data('fileurl', null);
    $('.reportVideoUploadList .uploadText').show();
    return false;
}



function chooseVideoRptDo() {
    $('.formRowVdo').show();
    $('.formRowImg').hide();
    if (uploaderV) {
        uploaderV.destroy();
    }
    /*文件上传*/
    uploaderV = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'Vupbtn',
        url: '/live/www/live/upload',
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
                promptDo('');
                up.start();
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $(".uploadVideoPloadprogress").html("点击上传视频");
                if (rst.success) {
                    var videourl = imgHost + rst.file_path;
                    viewVideo(videourl);
                }
            },
            UploadProgress: function (e, t) {
                var r = t.percent;
                $(".uploadVideoPloadprogress").html("开始上传（" + r + "%）");
            },
            Error: function (e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
            },
            destroy : function() {
                // this.trigger('Destroy');
                // settings = total = null; // purge these exclusively
                this.unbindAll();
            }
        }
    });
    uploaderV.init();
}

function chooseImageRptDo() {
    $('.formRowVdo').hide();
    $('.formRowImg').show();
    if (uploaderI) {
        uploaderI.destroy();
    }
    /*文件上传*/
    uploaderI = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'Iupbtn',
        url: '/live/www/live/upload',
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
                promptDo('');
                up.start();
            },
            UploadProgress: function (e, t) {
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
            },
            destroy : function() {
                // this.trigger('Destroy');
                // settings = total = null; // purge these exclusively
                this.unbindAll();
            }
        }
    });
    uploaderI.init();
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

/*处理图片*/
function handleImage(imageUrl) {
    var o = imgHost + imageUrl, l = new Image;
    l.src = o,
        l.onload = l.onerror = function () {
            var e = l.width, t = l.height;
            viewImagePage(o, e, t);
        }
}

function chooseAudioRptDo() {
    $('.formRowVdo').hide();
    $('.formRowImg').hide();
}

var reportUploadImgTemplate = '<li>' +
    '                        <div>' +
    '                            <img src="[imgPath]">' +
    '                        </div>' +
    '                        <span class="removeIcon removeImage"><i class="iconfont">&#xe628;</i></span>' +
    '                    </li>';


/*显示视频*/
function viewVideo(videoUrl) {
    $('.removeVideo').show();
    $('.reportVideoUploadList video').prop('src', videoUrl);
    $('.reportVideoUploadList').data('fileurl', videoUrl);
    $('.reportVideoUploadList .uploadText').hide();
}

function viewAudio(videoUrl) {

}

/*时间控件*/
$("#createTime").datetimepicker({
    format: "yyyy-mm-dd hh:ii:ss",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
});

var reportUploadImgTemplate='<li>'+
    '                        <div>'+
    '                            <img src="[imgPath]">'+
    '                        </div>'+
    '                        <span class="removeIcon removeImage"><i class="iconfont">&#xe628;</i></span>'+
    '                    </li>';