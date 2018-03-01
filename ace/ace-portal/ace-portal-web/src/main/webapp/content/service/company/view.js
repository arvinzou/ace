jQuery(function($) {
    uploader1=renderUploader("logo",{},{ width: 88,height: 88,crop: true,quality: 99,preserve_headers: false });
    uploader2=renderUploader("watermark1",{},{ width: 92,height: 22,crop: true,quality: 99,preserve_headers: false });
    uploader3=renderUploader("watermark2",{},{ width: 92,height: 22,crop: true,quality: 99,preserve_headers: false });
});
var uploader1,uploader2,uploader3;
function renderForm(data) {
    formModel.departmentName.value=data.departmentName;
    formModel.shortName.value=data.shortName;
    renderImg(data.logo,"logo");
    renderImg(data.watermark1,"watermark1");
    renderImg(data.watermark2,"watermark2");

    var e = new cake.tplform("j-editform", formModel);
    e.render($("#j-editform .row-content"),function(e) {
        console.log(e);
    },function(e) {
        e.data.logo=$("#j-uploader-rst-logo .xcy-cutimg").data("fileurl");
        e.data.watermark1=$("#j-uploader-rst-watermark1 .xcy-cutimg").data("fileurl");
        e.data.watermark2=$("#j-uploader-rst-watermark2 .xcy-cutimg").data("fileurl");
        e.data.id=userProp.corpId;
        console.log(e);
        update(e.data);
    });
}

function renderUploader(key,multipart_params,resize) {
    uploader = new plupload.Uploader({
        runtimes: "html5,flash,silverlight,html4",
        browse_button: "j-cover-"+key,
        multi_selection: false,
        container: document.getElementById("j-uploader-cnt-"+key),
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
        multipart_params:multipart_params,
        resize: resize,
        init: {
            PostInit: function() {},
            FilesAdded: function(t, a) {
                $("#j-cover-"+key).addClass("fn-hide"),
                $("#j-row-img-"+key+" .j-uploader-tip").removeClass("fn-hide"),
                $("#j-row-img-"+key+" .j-uploader-tip p em").html("开始上传"),
                t.start()
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $("#j-row-img-"+key+" .j-uploader-tip p em").html("正在上传（" + r + "%）");
            },
            FileUploaded: function(t, i, a) {
                if (200 == a.status) {
                    var rst = JSON.parse(a.response);
                    var o = fastdfs_server+rst.file_path;
                    console.log(o);
                    var l = new Image;
                    l.src = o,
                    l.onload = l.onerror = function() {
                        $("#j-row-img-"+key+" .j-uploader-tip p em").html("（100%）");
                       renderImg(rst.file_path,key);
                    }
                } else {
                    $("#j-row-img-"+key+" .j-uploader-tip p em").html(a.response)
                }
            },
            Error: function(e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : alert("文件上传失败：" + t.message)
            }
        }
    });
    uploader.init();

    return uploader;
}

function renderImg(file_path,key){
    var i = $('<div class="xcy-cutimg"> <label class="upbtn"><div class="imgbar fn-textleft"><span class="pz-close"><i class="pz-icon pz-icon-close"></i></span><span class="logo"><img src="' + fastdfs_server+file_path + '"></span></div></label></div>');
    i.data({
        fileurl: file_path
    });
    i.find(".pz-close").click(function() {
        i.remove();
        $("#j-cover-"+key).removeClass("fn-hide");
    });
    $("#j-uploader-rst-"+key).html(i);
    $("#j-row-img-"+key+" .j-uploader-tip p em").html("");
    $("#j-row-img-"+key+" .j-uploader-tip").addClass("fn-hide");
    $("#j-cover-"+key).addClass("fn-hide");
}