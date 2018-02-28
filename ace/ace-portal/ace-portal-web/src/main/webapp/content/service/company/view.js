jQuery(function($) {
    renderForm();
    renderUploader1();
});
var uploader1;
function renderForm() {
    var e = new cake.tplform("j-editform", formModel);
    e.render($("#j-editform .row-content"),function(e) {
        console.log(e);
    },function(e) {
        console.log(e);
    });
}

function renderUploader1() {
    uploader1 = new plupload.Uploader({
        runtimes: "html5,flash,silverlight,html4",
        browse_button: "j-cover",
        multi_selection: false,
        container: document.getElementById("j-uploader-cnt"),
        flash_swf_url: portalPath+"/content/common/plupload/Moxie.swf",
        silverlight_xap_url: portalPath+"/content/common/plupload/Moxie.xap",
        url: portalPath+"/department/upload.do",
        filters: {
             mime_types: [{
                 title: "Image files",
                 extensions: "jpg,jpeg,gif,png,bmp"
             }],
             max_file_size: "20480kb",
             prevent_duplicates: !1
         },
        multipart_params:{},
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        init: {
            PostInit: function() {},
            FilesAdded: function(t, a) {
                $("#j-cover").addClass("fn-hide"),
                $("#j-row-img .j-uploader-tip").removeClass("fn-hide"),
                $("#j-row-img .j-uploader-tip p em").html("开始上传"),
                t.start()
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $("#j-row-img .j-uploader-tip p em").html("正在上传（" + r + "%）");
            },
            FileUploaded: function(t, i, a) {
                if (200 == a.status) {
                    var rst = JSON.parse(a.response);
                    var o = fastdfs_server+rst.file_path;
                    console.log(o);
                    var l = new Image;
                    l.src = o,
                    l.onload = l.onerror = function() {
                        var e = l.width;
                        t = l.height;
                        $("#j-row-img .j-uploader-tip p em").html("（100%）");
                        var i = $('<div class="xcy-cutimg fn-left fn-mr10 fn-mb10"><div class="imgbar"><span class="close"><i class="pz-icon icon-close"></i></span><img src="' + o + '"></div></div>');
                        i.data({
                            fileurl: o,
                            width: e,
                            height: t
                        });
                        i.find(".close").click(function() {
                            i.remove();
                            $("#j-cover").removeClass("fn-hide");
                        });
                        $("#j-uploader-rst").html(i);
                        $("#j-row-img .j-uploader-tip p em").html("");
                        $("#j-row-img .j-uploader-tip").addClass("fn-hide");
                        $("#j-cover").addClass("fn-hide");
                    }
                } else {
                    $("#j-row-img .j-uploader-tip p em").html(a.response)
                }
            },
            Error: function(e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : alert("文件上传失败：" + t.message)
            }
        }
    });
    uploader1.init();
}
