// JavaScript Document

var host='http://localhost/live';
var imghost = "http://zx.huacainfo.com/";
var imageSrc;

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
    $('.release').click(releaseDo);
});

/*点击发布直播*/
function releaseDo() {
    console.log('releaseDo');
    var name=$('.name').val();
    var areaCode=$('.areaCode').val();
    var startTime=$('.startTime').val();
    var endTime=$('.endTime').val();
    if(endTime){
        endTime+=':00';
    }
    var remark=$('.remark').val();
    var content=$('.content').val();
    var deptId=$('.deptId').val();
    var addr=$('.addr').val();
    var rtmpUrl=$('.rtmpUrl').val();
    var mp4Url=$('.mp4Url').val();
    var category=$("input[type='radio']:checked").val();
    var nop=$('.nop').val();
    var pop=$('.pop').val();
    if(!(name&&imageSrc&&startTime&&remark&&content&&category&&deptId&&addr&&rtmpUrl&&mp4Url&&nop&&pop)){
        $('.prompt').text('带“ * ”为必填项');
        return;
    }
    var keyVal={
         'id':id,
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
        'pop':pop,
        'status':status
    }
    var url=host+"/live//updateLive.do";
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
    var $span = that.parent().siblings().eq(0).children();
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
    url: 'http://localhost/portal/files/uploadFile.do',
    file_data_name: 'file',
    multi_selection: false,
    filters: {
        max_file_size: '10mb',
        mime_types: [
            {title: "Image files", extensions: "jpg,gif,png"}
        ]
    },

    init: {
        FileFiltered: function (up, files) {
            console.log('uploadFile');
            uploader.start();
            return false;
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
    imageSrc=img;
    console.log(img);
    var image=imgBox.replace('[imgPath]',img);
    $('.imgbar').empty().append($(image));
}

var imgBox='<img src="'+imghost+'[imgPath]">';