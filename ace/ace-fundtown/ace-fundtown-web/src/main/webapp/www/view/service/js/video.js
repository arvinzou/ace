$(function () {

})


/*文件上传*/
var uploader = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'upbtn',
    url: '/portal/files/uploadFile.do',
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
            {title: "Video files", extensions: "mp4"}
        ]
    },
    init: {
        FileFiltered: function (up, files) {
            showUploadText();
            up.start();
            return false;
        },
        UploadProgress: function (e, t) {
            var r = t.percent;
            $(".uploadPloadprogress").html("开始上传（" + r + "%）")
        },
        FileUploaded: function (uploader, file, responseObject) {
            var rst = JSON.parse(responseObject.response);
            viewCover(rst.value[0]);
        }
    }
});