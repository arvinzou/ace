//封面上传
function uploadCover() {
    var fileFilters = {
        mime_types: [{
            title: "Image files",
            extensions: "jpg,jpeg,gif,png,bmp"
        }],
        max_file_size: "20480kb",
        prevent_duplicates: !1
    };

    /*文件上传*/
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upbtn',
        url: "/live/www/live/upload?companyId=" + lvsCmd.urlParams.companyId,
        file_data_name: 'file',
        multi_selection: false,
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: fileFilters
        //     {
        //     max_file_size: '2048mb',
        //     mime_types: [
        //         {title: "Image files", extensions: "jpg,gif,png"}
        //     ]
        // },
        ,
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

                $('.pictureContainer').data("imageSrc", rst.file_path);
                viewCover(o);
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
    title: {
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
    name: {
        class: "j-content fn-h180",
        name: "name",
        type: "textarea",
        maxlength: 550
    },
    content: {
        class: "j-content fn-h180",
        name: "content",
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
        var name = e.data.title;
        var startTime = e.data.startTime + ":00";
        var endTime = e.data.endTime + ":00";
        var remark = e.data.name;
        var content = e.data.content;
        var addr = e.data.addr;
        var rtmpUrl = e.data.rtmpUrl;
        var mp4Url = e.data.mp4Url;
        var type = e.data.type;
        var imageSrc = $(".pictureContainer").data("imageSrc");
        var nop = e.data.nop;
        var pop = e.data.pop;

        console.log(" e.data.summary:" + e.data.summary + "\n"
            + "e.data.desc：" + e.data.desc);

        console.log(name + "," + imageSrc + "," + startTime + "," + endTime
            + "," + remark + "," + content + "," + addr + "," + rtmpUrl + "," + mp4Url + "," + nop + "," + pop);
        // 数据合法性校验
        if (!checkData(name, imageSrc, startTime, endTime, remark, content, addr, rtmpUrl, mp4Url, nop, pop)) {
            return false;
        }
        var data = {
            name: name,
            areaCode: "",
            startTime: startTime,
            endTime: endTime,
            remark: remark,
            content: content,
            deptId: "",
            addr: addr,
            rtmpUrl: rtmpUrl,
            mp4Url: mp4Url,
            category: type,
            imageSrc: imageSrc,
            nop: nop,
            pop: pop,
            openid: wxuser.openid
        }

        console.log("*********ajax data: " + JSON.stringify(data));

        var url = apiServer + "/www/live/insertLive";
        lvsCmd.ajax(url, {jsons: JSON.stringify(data)},
            function (e, t) {
                // $(".j-content").val("");
                // location.reload();
                e ? "0" == t.status ? ajaxSuccess() : ajaxFailed(t) : alert("接口请求失败，请检查网络连接！")
            });
        return false;
    });

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
    showUploadImg(imagePath);
}


function checkData(name, imageSrc, startTime, endTime, remark, content, addr, rtmpUrl, mp4Url, nop, pop) {
    if (isEmpty(name) || isEmpty(imageSrc) || isEmpty(startTime)
        || isEmpty(remark) || isEmpty(content) || isEmpty(addr) || isEmpty(rtmpUrl)
        || isEmpty(mp4Url) || isEmpty(nop) || isEmpty(pop)) {
        alert('带“ * ”为必填项');
        return false;
    }
    if (name.length > 30) {
        alert('标题字数过长');
        return false;
    }
    if (remark.length > 500) {
        alert('摘要字数过长');
        return false;
    }
    if (addr.length > 200) {
        alert('地点字数过长');
        return false;
    }
    if (rtmpUrl.length > 200) {
        alert('直播流地址字数过长');
        return false;
    }
    if (mp4Url.length > 200) {
        alert('回放地址字数过长');
        return false;
    }
    if (!isPositiveInteger(nop)) {
        alert('参与人数必须为正整数');
        return false;
    }
    if (!isPositiveInteger(pop)) {
        alert('点赞数必须为正整数');
        return false;
    }
    if (judegTimeOrder(new Date().toLocaleTimeString(), startTime)) {
        alert('开始时间不能早于系统时间');
        return false;
    }
    if (judegTimeOrder(startTime, endTime)) {
        alert('开始时间不能大于结束时间');
        return false;
    }

    return true;
}

function isPositiveInteger(s) {//是否为正整数
    var re = /^[0-9]+$/;
    return re.test(s)
}

function judegTimeOrder(time1, time2) {
    var s = new Date(time1).getTime();
    var e = new Date(time2).getTime();

    return s > e;
}


function ajaxSuccess() {
    // $(".j-content").val("");
    // location.reload();
    location.href = "index.html?companyId=" + lvsCmd.urlParams.companyId;
    (alert("数据保存成功！"), parent.pizzaCmd.history.pop());
}

function ajaxFailed(t) {
    alert(t.errorMessage)
}

function isEmpty(str) {
    if (!str || str.length == 0) {
        return true;
    }
    return false;
}
