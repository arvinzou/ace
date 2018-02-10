
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
    $('.formRow').on('keyup', 'input', computedNumDo);
    $('.formRow').on('keyup', 'textarea', computedNumDo);
    $('.release').click(releaseDo);
});

/*点击发布直播*/
function releaseDo() {
    console.log('releaseDo');
    var name=$('.name').val().trim();
    var startTime=$('.startTime').val().trim();
    var endTime=$('.endTime').val().trim();
    if(endTime){
        endTime+=':00';
    }
    var remark=$('.remark').val().trim();
    var content=$('.content').val().trim();
    var addr=$('.addr').val().trim();
    var rtmpUrl=$('.rtmpUrl').val().trim();
    var mp4Url=$('.mp4Url').val().trim();
    var category=$("input[type='radio']:checked").val().trim();
    var nop=$('.nop').val().trim();
    var pop=$('.pop').val().trim();
    var imageSrc=$('.pictureContainer').data('imgSrc');
    if(!(name&&imageSrc&&startTime&&remark&&content&&category&&addr&&rtmpUrl&&mp4Url&&nop&&pop)){
        $('.prompt').text('带“ * ”为必填项');
        return;
    }
    var keyVal={
         'id':id,
        'name':name,
        'startTime':startTime+':00',
        'endTime':endTime,
        'remark':remark,
        'content':content,
        'addr':addr,
        'rtmpUrl':rtmpUrl,
        'mp4Url':mp4Url,
        'category':category,
        'imageSrc':imageSrc,
        'nop':nop,
        'pop':pop,
        'status':status
    }
    var url="/live/live/updateLiveSelective.do";
    var data={
        'jsons':JSON.stringify(keyVal)
    };
    $.post(url,data, function (result) {
        console.log(result);
        if(result.status==0){
            alert("修改直播成功");
            $(".form-control").val('');
            $('.imgbar').empty();
            $('.modify').hide();
            loadLiveList();
        }else{
            $('.prompt').text(result.errorMessage);
        }
    });
}

/*直播名字字数的计算*/
function computedNumDo() {
    $('.prompt').text('');
    var that = $(this);
    var nowWordNumber = that.val().length;
    var $span = that.parent().parent().find('span');
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

/*文件上传*/
var uploader = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'upbtn', // you can pass an id...
    /*container: document.getElementById('container'),*/ // ... or DOM Element itself
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
        max_file_size: '1000mb',
        mime_types: [
            {title: "Image files", extensions: "jpg,gif,png"}
        ]
    },

    init: {
        FileFiltered: function (up, files) {
            $('.viewPicture img').prop('src','');
            $('.uploadText').show();
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