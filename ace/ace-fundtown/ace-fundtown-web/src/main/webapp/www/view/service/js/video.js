var deptID;

$(function () {
    userInfo();
})


function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.ajaxSettings.async = false;
    $.getJSON(url, function (result) {
        deptID = result.data.deptId;
        initVideo(deptID);
        if (result.data.vipStatus == 2) {
            uploader.settings.multipart_params.deptId = deptID;
            uploader.init();
        } else {
            alert('注册并审核通过之后才能上传视频', '注册并审核通过之后才能上传视频');
        }
    });
    $.ajaxSettings.async = true;
}


function initVideo(deptID) {
    if (!deptID) {
        return;
    }
    var url = '/fundtown/www/process/getMyVideo';
    var data = {
        deptId: deptID
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            addVideo(result.data);
        }
    })
}

function addVideo(data) {
    for (var i = 0; i < data.length; i++) {
        viewVideo(data[i].resUrl);
    }
}


/*文件上传*/
var uploader = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'upbtn',
    url: '/fundtown/www/process/uploadFile',
    file_data_name: 'file',
    max_retries: 3,     //允许重试次数
    chunk_size: '5mb', //分块大小
    multi_selection: false,
    multipart_params: {
        deptId: ''
    },
    filters: {
        max_file_size: '204800mb',
        mime_types: [
            {title: "Video files", extensions: "3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv,mp3,acc,wav"}
        ]
    },
    init: {
        FileFiltered: function (up, files) {
            up.start();
            return false;
        },
        UploadProgress: function (e, t) {
            var r = t.percent;
            r = r == 0 ? r : r - 1;
            $(".uploadPloadprogress").html("开始上传（" + r + "%）")
        },
        FileUploaded: function (uploader, file, responseObject) {
            var rst = JSON.parse(responseObject.response);
            if (rst.status == 0) {
                viewVideo(rst.data.resUrl);
                return;
            }
            $(".uploadPloadprogress").html("上传失败");

        }
    }
});

function viewVideo(videoUrl) {
    $('#upbtn').before($('<li class="video_content"><video  controls="controls" src="' + videoUrl + '"></video></li>'));
    $(".uploadPloadprogress").html("上传视频");
}