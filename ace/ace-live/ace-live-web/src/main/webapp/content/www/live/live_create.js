//封面上传
var imageUploader;
function uploadCover() {
    // var e = {};
    // e.key = "";
    // var fileFilter = {
    //     mime_types: [{
    //         title: "Image files",
    //         extensions: "jpg,jpeg,gif,png,bmp"
    //     }],
    //     max_file_size: "20480kb",//20MB
    //     prevent_duplicates: false
    // };
    // var r = "";
    // var i = "";
    // imageUploader = new plupload.Uploader({
    //     runtimes: "html5,flash,silverlight,html4",
    //     browse_button: "upbtn",
    //     multi_selection: false,
    //     container: document.getElementById("j-uploader-selectimg"),
    //     flash_swf_url: "/live/content/www/lib/plupload-2.1.2/Moxie.swf",
    //     silverlight_xap_url: "/live/content/www/lib/plupload-2.1.2/Moxie.xap",
    //     url: "/live/www/live/upload.do?companyId=" + lvsCmd.urlParams.companyId,
    //     filters: fileFilter,
    //     resize: {
    //         width: 1024,
    //         height: 1024,
    //         crop: true,
    //         quality: 60,
    //         preserve_headers: false
    //     },
    //     init: {
    //         PostInit: function () {
    //         },
    //         FilesAdded: function (t, a) {
    //             var o = a[0],
    //                 l = o.name,
    //                 n = l.substr(l.lastIndexOf(".")),
    //                 d = (1e4 * (new Date).getTime() + Math.floor(1e4 * Math.random())).toString(32),
    //                 s = d + n;
    //             Math.ceil(o.size / 1024 / 1024 * 100) / 100;
    //             $("#j-uploader-selectimg").addClass("fn-hide"),
    //                 $("#j-row-img .j-uploader-tip").removeClass("fn-hide"),
    //                 $("#j-row-img .j-uploader-tip p").html("开始上传<em>...</em>"),
    //                 e.key = i + "/" + s,
    //                 t.setOption({
    //                     // url: r,
    //                     multipart_params: e
    //                 }),
    //                 showUploadText();
    //                 t.start()
    //         },
    //         UploadProgress: function (e, t) {
    //             var r = t.percent;
    //             $(".uploadPloadprogress").html("开始上传（" + r + "%）")
    //         },
    //         FileUploaded: function (t, i, a) {
    //             // console.log("eeeeeeeeeeeeeeeeeee");
    //             // console.log($("#j-row-img").width());
    //             // console.log($("#j-row-img").height());
    //             // //文件上传成功后
    //             // if (200 == a.status) {
    //             //     var rst = JSON.parse(a.response);
    //             //     var o = cfg.fastdfs_server + rst.file_path;
    //             //     var r = o;
    //             //     r && r.indexOf(".gif") == -1 && (r += "?x-oss-process=image/resize,w_240");
    //             //
    //             //     $(".imgbar img").prop('src', r);
    //             //     $(".imgbar img").removeClass("fn-hide");
    //             //     $("#j-selectimg").addClass("fn-hide");
    //             //     $(".j-uploader-tip").addClass("fn-hide");
    //             //
    //             // } else $("#j-row-img .j-uploader-tip p").html(a.response)
    //             var rst = JSON.parse(a.response);
    //             viewCover(rst.value[0]);
    //
    //         },
    //         Error: function (e, t) {
    //             t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601
    //                 ? alert("不支持该格式！") : t.code == -602
    //                     ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
    //         }
    //     }
    // });
    //
    // //在实例对象上调用init()方法进行初始化
    // imageUploader.init();

    /*文件上传*/
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upbtn',
        url: "/live/www/live/upload.do?companyId=" + lvsCmd.urlParams.companyId,
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
                {title: "Image files", extensions: "jpg,gif,png"}
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
            FileUploaded: function (t, i, a) {
                var rst = JSON.parse(a.response);
                var o = cfg.fastdfs_server + rst.file_path;
                viewCover(o);

                // console.log($(".pictureContainer").data("imageSrc"))
            }
        }
    });

    uploader.init();
}
//
var formFieldDict = {
    type: {
        name: "type",
        type: "radio",
        option: [
            {
                text: "视频直播",
                value: "1"
            }
            , {
                text: "图文直播",
                value: "2"
            }
        ],
        value: 1
    },
    name: {
        name: "title",
        class: "j-content fn-text fn-h30",
        type: "text",
        maxlength: 50
    },
    startTime: {
        id: "startTime",
        name: "startTime",
        class: "j-content fn-text fn-h30 start-time",
        type: "text"
    },
    endTime: {
        name: "endTime",
        class: "j-content fn-text fn-h30 end-time",
        type: "text"
    },
    remark: {
        class: "j-content fn-h180",
        name: "summary",
        type: "textarea",
        maxlength: 550
    },
    content: {
        class: "j-content fn-h180",
        name: "summary",
        type: "textarea",
        maxlength: 300
    },
    addr: {
        name: "addr",
        class: "j-content fn-text fn-h30",
        type: "text",
        maxlength: 300
    },
    rtmpUrl: {
        name: "rtmpUrl",
        class: "j-content fn-text fn-h30",
        type: "text",
        maxlength: 300
    },
    mp4Url: {
        name: "mp4Url",
        class: "j-content fn-text fn-h30",
        type: "text",
        maxlength: 300
    },
    nop: {
        name: "nop",
        class: "j-content fn-text fn-h30",
        type: "text"
    },
    pop: {
        name: "pop",
        class: "j-content fn-text fn-h30",
        type: "text"
    }
};

//execute
uploadCover();

//romance 自动渲染
var newTplform = new cake.tplform("j-liveform", formFieldDict);
//标题
newTplform.render($("#j-liveform .row-content"),
    //动态字数长度显示
    function (e) {
        function t() {
            //标题
            var titleLength = $("#j-title .j-content").val().length;
            $("#j-title .j-remark-length").html(titleLength + "/30"),
                titleLength > 30 ? $("#j-title .j-remark-length").addClass("fn-color-red") :
                    $("#j-title .j-remark-length").removeClass("fn-color-red");
            //摘要
            var summaryLength = $("#j-summary .j-content").val().length;
            $("#j-summary .j-remark-length").html(summaryLength + "/500"),
                summaryLength > 500 ? $("#j-summary .j-remark-length").addClass("fn-color-red") :
                    $("#j-summary .j-remark-length").removeClass("fn-color-red")
            //地点
            var addrLength = $("#j-addr .j-content").val().length;
            $("#j-addr .j-remark-length").html(addrLength + "/200"),
                addrLength > 200 ? $("#j-addr .j-remark-length").addClass("fn-color-red") :
                    $("#j-addr .j-remark-length").removeClass("fn-color-red");
            //直播流地址
            var liveAddrLength = $("#j-live-addr .j-content").val().length;
            $("#j-live-addr .j-remark-length").html(liveAddrLength + "/200"),
                liveAddrLength > 200 ? $("#j-live-addr .j-remark-length").addClass("fn-color-red") :
                    $("#j-live-addr .j-remark-length").removeClass("fn-color-red");
            //回放地址
            var replayAddrLength = $("#j-replay-addr .j-content").val().length;
            $("#j-replay-addr .j-remark-length").html(replayAddrLength + "/200"),
                replayAddrLength > 200 ? $("#j-replay-addr .j-remark-length").addClass("fn-color-red") :
                    $("#j-replay-addr .j-remark-length").removeClass("fn-color-red");


            // alert($(".start-time").val());
        }

        //事件绑定
        $("#j-title-content").on("input", t),
            $("#j-summary-content").on("input", t),
            $("#j-addr-content").on("input", t),
            $("#j-live-addr-content").on("input", t),
            $("#j-replay-addr-content").on("input", t),
            t()

    },
    //表单提交
    function (e) {
        var name = e.data.name;
        var startTime = e.data.startTime;
        var endTime = e.data.endTime;
        var remark = e.data.remark;
        var content = e.data.content;
        var addr = addr;
        var rtmpUrl = e.data.rtmpUrl;
        var mp4Url = e.data.mp4Url;
        var type = e.data.type;
        var imageSrc = $(".pictureContainer").data("imageSrc");
        var nop = e.data.nop;
        var pop = e.data.pop;
        if (!(name && imageSrc && startTime && remark && content && addr && rtmpUrl && mp4Url && nop && pop)) {
            alert('带“ * ”为必填项');
            return false;
        }

        var data = {
            name: name,
            areaCode: "001",
            startTime: startTime,
            endTime: endTime,
            remark: remark,
            content: content,
            deptId: "002",
            addr: addr,
            rtmpUrl: rtmpUrl,
            mp4Url: mp4Url,
            category: type,
            imageSrc: imageSrc,
            nop: nop,
            pop: pop,
            openid: wxuser.openid
        }

        console.log(JSON.stringify(data));

        var url = apiServer + "/www/live/insertLive.do";
        lvsCmd.ajax(l, {jsons: JSON.stringify(data)},
            function (e, t) {
                $(".j-content").val("");
                location.reload();
                e ? "0" == t.status ? (alert("数据保存成功！"), parent.pizzaCmd.history.pop()) : alert(t.errorMessage) : alert("接口请求失败，请检查网络连接！")
            });
        return false;
    }
);

function initTimePicker() {
    var startTimeDatePicker = new datePicker();
    var startTime;
    var endTime;
    startTimeDatePicker.init({
        'trigger': '.start-time', /*按钮选择器，用于触发弹出插件*/
        'type': 'datetime', /*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate': '1900-1-1', /*最小日期*/
        'maxDate': '2100-12-31', /*最大日期*/
        'onSubmit': function () {/*确认时触发事件*/
            startTime = startTimeDatePicker.value;
        },
        'onClose': function () {/*取消时触发事件*/
        }
    });

    var endTimeDatePicker = new datePicker();
    endTimeDatePicker.init({
        'trigger': '.end-time', /*按钮选择器，用于触发弹出插件*/
        'type': 'datetime', /*模式：date日期；datetime日期时间；time时间；ym年月；*/
        'minDate': '1900-1-1', /*最小日期*/
        'maxDate': '2100-12-31', /*最大日期*/
        'onSubmit': function () {/*确认时触发事件*/
            endTime = endTimeDatePicker.value;
        },
        'onClose': function () {/*取消时触发事件*/
        }
    });
}

initTimePicker();


/*显示上传文字*/
function showUploadText() {
    $('.viewPicture img').prop('src', '');
    $('.viewPicture').hide();
    $('.uploadText').show();
}

/*显示上传图片*/
function showUploadImg(imgpath) {
    $('.viewPicture').show();
    $('.viewPicture img').prop('src', imgpath);
    $('.uploadText').hide();
}


/*图片上传成功后*/
function viewCover(imagePath) {
    $('.pictureContainer').data("imageSrc", imagePath);

    showUploadImg(imagePath);
}
