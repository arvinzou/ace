$(function () {
    userInfo();
})

var deptID;

function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.ajaxSettings.async = false;
    $.getJSON(url, function (result) {
        uploader.init();
        deptID = result.data.deptId;
        // if(result.data.vipStatus==2){
        //     uploader.init();
        //     deptID=result.data.deptId;
        // }else {
        //     alert('注册并审核通过之后才能上传视频','注册并审核通过之后才能上传视频');
        // }
    });
    $.ajaxSettings.async = true;
}


/*文件上传*/
var uploader = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'upbtn',
    url: '/fundtown/www/process/uploadFile',
    file_data_name: 'file',
    multi_selection: false,
    resize: {
        width: 1024,
        height: 1024,
        crop: true,
        quality: 60,
        preserve_headers: false
    },
    multipart_params: {
        deptId: deptID
    },
    filters: {
        max_file_size: '204800mb',
        mime_types: [
            {title: "Video files", extensions: "mp4"}
        ]
    },
    init: {
        FileFiltered: function (up, files) {
            up.start();
            return false;
        },
        UploadProgress: function (e, t) {
            var r = t.percent;
            $(".uploadPloadprogress").html("开始上传（" + r + "%）")
        },
        FileUploaded: function (uploader, file, responseObject) {
            var rst = JSON.parse(responseObject.response);
            console.log(rst);
        }
    }
});