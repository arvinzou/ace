$("#startDate").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
}).on('change', function (ev) {
    var startDate = $('#startDate').val();
    $("#endDate").datetimepicker('setStartDate', startDate);
    $("#startDate").datetimepicker('hide');
});
$("#endDate").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
}).on('change', function (ev) {
    var endDate = $("#endDate").val();
    $("#startDate").datetimepicker('setEndDate', endDate);
    $("#endDate").datetimepicker('hide');
});

$(function () {
    /*输入计数操作*/
    $('.formRow').on('keyup', 'input', computedNumDo);
    $('.formRow').on('keyup', 'textarea', computedNumDo);
    /*点击取消提示*/
    $('.mainContent').on('focus', '.form-control',promptToDo);
    /*确认发布报道*/
    $('.release').click(releaseDo);
});

/*取消提示*/
function promptToDo() {
    promptDo('');
}

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

/*点击发布直播*/
function releaseDo() {
    var name=$('.name').val();
    var areaCode=userProp.areaCode;
    var startTime=$('.startTime').val();
    var endTime=$('.endTime').val();
    if(endTime){
        endTime+=':00';
    }
    var remark=$('.remark').val().trim();
    var content=$('.content').val().trim();
    var deptId=userProp.corpId;
    var addr=$('.addr').val().trim();
    var rtmpUrl=$('.rtmpUrl').val().trim();
    var mp4Url=$('.mp4Url').val().trim();
    var category=$("input[type='radio']:checked").val().trim();
    var nop=$('.nop').val().trim();
    var pop=$('.pop').val().trim();
    var imageSrc=$('.pictureContainer').data('imgSrc');
    if(!(name&&imageSrc&&startTime&&remark&&content&&category&&addr&&rtmpUrl&&mp4Url&&nop&&pop)){
        promptDo('带“ * ”为必填项');
        return;
    }
    promptDo('');
    var keyVal={
        'name':name,
        'areaCode':areaCode,
        'startTime':startTime+':00',
        'endTime':endTime,
        'remark':remark,
        'content':content,
        'deptId':deptId,
        'addr':addr,
        'rtmpUrl':rtmpUrl,
        'mp4Url':mp4Url,
        'category':category,
        'imageSrc':imageSrc,
        'nop':nop,
        'pop':pop
    }
    var url="/live/live/insertLive.do";
    var data={
        'jsons':JSON.stringify(keyVal)
    };
    $.post(url,data, function (result) {
        console.log(result);
        if(result.status==0){
            alert("创建直播成功");
            $(".form-control").val('');
            showUploadText();
        }else{
            $('.prompt').text(result.errorMessage);
        }

    });
}

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
            {title: "Image files", extensions: "jpg,gif,png"}
        ]
    },
    init: {
        FileFiltered: function (up, files) {
            showUploadText();
            up.start();
            return false;
        },
        UploadProgress: function(e, t) {
            var r = t.percent;
            $(".uploadPloadprogress").html("开始上传（" + r + "%）")
        },
        FileUploaded: function (uploader, file, responseObject) {
            var rst = JSON.parse(responseObject.response);
            viewCover(rst.value[0]);
        }
    }
});

uploader.init();

/*图片上传成功后*/
function viewCover(img) {
    $('.pictureContainer').data('imgSrc',img);
    var imagePath=imgHost+img;
    showUploadImg(imagePath);
}

/*显示上传文字*/
function showUploadText() {
    $('.viewPicture img').prop('src','');
    $('.uploadText').show();
}

/*显示上传图片*/
function showUploadImg(imgpath) {
    $('.viewPicture img').prop('src',imgpath);
    $('.uploadText').hide();
}
