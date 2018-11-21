jQuery(function ($) {
    //init_uploader();

});

function initUploader(browseBtn, id) {
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: browseBtn,//'pickfiles', // you can pass in id...
        container: document.getElementById('container'), // ... or DOM Element itself
        url: contextPath + '/vipDepartment/uploadVipRes?deptId=' + id + '&domain=' + fastdfs_server,
        flash_swf_url: contextPath + '/content/plupload-2.1.2/js/Moxie.swf',
        silverlight_xap_url: contextPath + '/content/plupload-2.1.2/js/Moxie.xap',
        filters: {
            max_file_size: '100mb',
            mime_types: [
                {title: "Image files", extensions: "jpg,gif,png"},
                {title: "Office files", extensions: "txt,xls,xlsx,doc,docx,ppt,pptx,pdf"},
                {title: "Artive files", extensions: "zip,rar,gzip"},
                {title: "Media files", extensions: "mp4,avi,wmv,wav"}

            ]
        },
        init: {
            PostInit: function () {
                document.getElementById('filelist').innerHTML = '';
                document.getElementById('uploadfiles').onclick = function () {
                    uploader.start();
                    return false;
                };
            },
            FilesAdded: function (up, files) {
                plupload.each(files, function (file) {
                    document.getElementById('filelist').innerHTML +=
                        '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
                });
            },
            UploadProgress: function (up, file) {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML =
                    '<span>' + file.percent + "%</span>";
            },
            Error: function (up, err) {
                document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
            }
        }
    });

    uploader.init();
    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        var id = file.id;
        var rst = JSON.parse(responseObject.response);
        if (!rst.state) {
            bootbox.dialog({
                title: '系统提示',
                message: rst.errorMessage,
                detail: rst.detail,
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i>确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {

                        }
                    }
                }
            });
        } else {
            var html = [];
            $.each(rst.value, function (n, obj) {
                html.push('<div id="' + obj.id + '"> ' +
                    '<a href="' + obj.mediUrl + '" target="_blank">' + obj.resName
                    + '</a> (' + parseInt(file.size / 1024) + 'kb)' +
                    ' <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' ' +
                    ' href="javascript:deleteAttach(\'' + obj.id + '\',\'' + obj.deptId + '\')"></a><b></b></div>');
            });
            document.getElementById('filelist-history').innerHTML += html.join('');
            $('#' + id).html('');

        }
    });

}