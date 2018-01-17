function checkType() {
    2 == reportType ? ($("#j-row-img, #j-upcontain .xcy-cutimg").removeClass("fn-hide"), $("#j-row-video").addClass("fn-hide")) : 1 == reportType && ($("#j-row-img, #j-upcontain .xcy-cutimg").addClass("fn-hide"), $("#j-row-video").removeClass("fn-hide"))
}
function createUpImg() {
    var e={}, t = {
        mime_types: [{
            title: "Image files",
            extensions: "jpg,jpeg,gif,png,bmp"
        }],
        max_file_size: "20480kb",
        prevent_duplicates: !1
    },
    r = "",
    i = "";
    e.key="";
    console.log("createUpImg start");
    var a = new plupload.Uploader({
        runtimes: "html5,flash,silverlight,html4",
        browse_button: "j-uploader-selectimg",
        multi_selection: !1,
        container: document.getElementById("j-row-img"),
        flash_swf_url: "/live/content/www/lib/plupload-2.1.2/Moxie.swf",
        silverlight_xap_url: "/live/content/www/lib/plupload-2.1.2/Moxie.xap",
        url: "/live/www/live/upload.do",
        filters: t,
        init: {
            PostInit: function() {},
            FilesAdded: function(t, a) {
                var o = a[0],
                l = o.name,
                n = l.substr(l.lastIndexOf(".")),
                d = (1e4 * (new Date).getTime() + Math.floor(1e4 * Math.random())).toString(32),
                s = d + n;
                Math.ceil(o.size / 1024 / 1024 * 100) / 100;
                $("#j-uploader-selectimg").addClass("fn-hide"),
                $("#j-row-img .j-uploader-tip").removeClass("fn-hide"),
                $("#j-row-img .j-uploader-tip p").html("开始上传<em>...</em>"),
                e.key = i + "/" + s,
                t.setOption({
                   // url: r,
                    multipart_params: e
                }),
                t.start()
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $("#j-row-img .j-uploader-tip p em").html("（" + r + "%）")
            },
            FileUploaded: function(t, i, a) {
                console.log(t);
                console.log(i);

                if (200 == a.status) {
                    var rst = JSON.parse(a.response);
                    var o = cfg.fastdfs_server+rst.file_path,
                    l = new Image;
                    l.src = o,
                    l.onload = l.onerror = function() {
                        var e = l.width,
                        t = l.height;
                        $("#j-row-img .j-uploader-tip p em").html("（100%）");
                        var r = o;
                        r && r.indexOf(".gif") == -1 && (r += "?x-oss-process=image/resize,w_240");
                        var i = $('<div class="xcy-cutimg fn-left fn-mr10 fn-mb10"><div class="imgbar"><span class="close"><i class="pz-icon icon-close"></i></span><img src="' + r + '"></div></div>');
                        i.data({
                            fileurl: o,
                            width: e,
                            height: t
                        }),
                        i.find(".close").click(function() {
                            i.remove(),
                            $("#j-row-img").removeClass("fn-hide")
                        }),
                        $("#j-row-img").before(i),
                        $("#j-row-img .j-uploader-tip p").html(""),
                        $("#j-row-img .j-uploader-tip").addClass("fn-hide"),
                        $("#j-uploader-selectimg").removeClass("fn-hide"),
                        $("#j-upcontain .xcy-cutimg").length >= 4 && $("#j-row-img").addClass("fn-hide")
                    }
                } else $("#j-row-img .j-uploader-tip p").html(a.response)
            },
            Error: function(e, t) {
                t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
            }
        }
    });
     console.log("a.init()");
    a.init();
    createUpVideo();
    console.log("createUpImg end;");
}
function createUpVideo() {
    console.log("createUpVideo start");
     $("#j-row-video .xcy-video .imgbar").hide();
    var  t = {
        mime_types: [{
            title: "video files",
            extensions: "3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv"
        }],
        max_file_size: "20480kb",
        prevent_duplicates: !1
    };
    var a = new plupload.Uploader({
            runtimes: "html5,flash,silverlight,html4",
            browse_button: "j-uploader-selectvideo",
            multi_selection: !1,
            container: document.getElementById("j-row-video"),
            flash_swf_url: "/live/content/www/lib/plupload-2.1.2/Moxie.swf",
            silverlight_xap_url: "/live/content/www/lib/plupload-2.1.2/Moxie.xap",
            url: "/live/www/live/upload.do",
            filters: t,
            init: {
                PostInit: function() {},
                FilesAdded: function(t, a) {
                    var o = a[0],
                    l = o.name,
                    n = l.substr(l.lastIndexOf(".")),
                    d = (1e4 * (new Date).getTime() + Math.floor(1e4 * Math.random())).toString(32),
                    s = d + n;
                    Math.ceil(o.size / 1024 / 1024 * 100) / 100;
                    $("#j-uploader-selectvideo").addClass("fn-hide"),
                    $("#j-row-video .j-uploader-tip").removeClass("fn-hide"),
                    $("#j-row-video .j-uploader-tip p").html("开始上传<em>...</em>"),
                    t.setOption({
                       // url: r,
                        multipart_params: ""
                    }),
                    t.start()
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $("#j-row-video .j-uploader-tip p em").html("（" + r + "%）")
                },
                FileUploaded: function(t, i, a) {
                    if (200 == a.status) {
                        var rst = JSON.parse(a.response);
                        console.log(rst);
                        var o = cfg.fastdfs_server+rst.file_path;
                        reportType=1;
                        checkType();
                      $("#j-row-video").data("fileurl", o);
                      $("#j-row-video").attr("fileurl", o);
                      $("#j-row-video .videobar, #j-videocover").removeClass("fn-hide");
                      $("#j-uploader-selectvideo").addClass("fn-hide");
                      $("#j-row-video .j-uploader-tip").addClass("fn-hide");

                    } else $("#j-row-video .j-uploader-tip p").html(a.response)
                },
                Error: function(e, t) {
                    t.code == -600 ? alert("上传的视频太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-video .j-uploader-tip p").html("文件上传失败：" + t.message)
                }
            }
        });
         console.log("a.init()");
        a.init();
    console.log("createUpVideo end");
}
var urlParams = lvsCmd.urlParams;
var formFieldDict = {
    type: {
        name: "type",
        type: "radio",
        option: [{
            text: "图文报道",
            value: "2"
        },
        {
            text: "视频报道",
            value: "1"
        },{
                      text: "音频报道",
                      value: "3"
            }],
        value: 2
    },
    content: {
        class: "j-content fn-h180",
        name: "content",
        type: "textarea",
        maxlength: 300
    },
    scene: {
        name: "scene",
        type: "text",
        disabled: !0,
        class: "disabled",
        value: lvsCmd.urlParams.title
    }
};
var reportType = 2;
console.log("createUpImg");
createUpImg(),
$("#j-row-video .videobar .icon-video").click(function() {
    var e = $("#j-row-video").data("fileurl");
   new pizzaCmd.overlay($("#j-overlay-view").html(),
    function(t) {
        t.show(),
        t.obj.find(".j-overlay-close").click(function() {
            t.remove()
        })
        videoPlayer("j-viewvideo", "", e)
    })
    $(this).blur();
}),
$("#j-row-video .videobar .close").click(function() {
    $("#j-row-video .videobar, #j-row-video .j-uploader-tip, #j-videocover").addClass("fn-hide"),
    $("#j-uploader-selectvideo").removeClass("fn-hide"),
    $("#j-row-video").data("fileurl", null),
    $("#j-row-video .xcy-video .imgbar").html("").data("fileurl", null).hide()
});
var newTplform = new cake.tplform("j-reportform", formFieldDict);
newTplform.render($("#j-reportform .row-content"),
function(e) {
    function t() {
        var e = $(".j-content").val().length;
        $(".j-remark-length").html(e + "/300"),
        e > 300 ? $(".j-remark-length").addClass("fn-color-red") : $(".j-remark-length").removeClass("fn-color-red")
    }
    new lvsCmd.cutimg($("#j-videocover"), {
        filetype: 8,
        width: 750,
        height: 422,
        scale: .4
    },
    function(e) {
        $("#j-row-video .xcy-video .imgbar").html('<img src="' + e + '">').data("fileurl", e)
    }),
    $(".j-content").on("input", t),
    t()
},
function(e) {
    var data={};
    var rpt = {
        rid: lvsCmd.urlParams.id,
        uid:wxuser.openid,
        mediaType: reportType,
        content: e.data.content,
        mediaContent:""

    };

    var imgs=[];
    if (2 == reportType) {
        $("#j-upcontain .xcy-cutimg").each(function() {
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

         if("" == rpt.content &&imgs.length<1) {
            alert("报道内容和报道图片不能全为空");
            return false;
         }
    } else if (1 == reportType) {
        var r = $("#j-row-video").data("fileurl");
        if (!r)  {alert("请上传报道视频"); return false;};
        rpt.mediaContent=r;

    }
    data.rpt=rpt;
    data.imgs=imgs;
    var l = apiServer + "/www/live/insertLiveRpt.do";
    lvsCmd.ajax(l, {jsons:JSON.stringify(data)},
    function(e, t) {
        e ? "0" == t.status ? (alert("数据保存成功！"), parent.pizzaCmd.history.pop()) : alert(t.errorMessage) : alert("接口请求失败，请检查网络连接！")
    });
    return false;
});

$("#j-reportform input[name=type]").click(function() {
         var e = $(this).val();
         console.log(e);
         reportType != e && (reportType = e, checkType())
});
reportType=2;
checkType();
function videoPlayer(e, n, t) {
	var i = {
		id: "j-viewvideo",
		source: t,
		autoplay: !0,
		width: "480px",
		height: "270px"
	}; (0 == t.indexOf("rtmp:") || t.indexOf(".m3u8") > -1) && (i.useFlashPrism = !0);
	new prismplayer(i)
}