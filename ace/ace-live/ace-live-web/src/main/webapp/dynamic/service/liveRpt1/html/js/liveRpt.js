var host = 'http://192.168.2.153/live';
var start;
var limit = 25;
var orderByStr = null;
var id;
var mediaType = 2;
var imgs = [];
$(function () {
    initweb();
    $(":radio").click(chooseTypeDo);
    $('.cancel').click(hideSelectReportDo);
    $('#liveListTable').on('click', 'a', actionSelectReportDo);
    $('.search').click(searchByNameDo);
    $('.release').click(releaseDo);
});

/*确认发布报道*/
function releaseDo() {
    $('#file-3').fileinput('upload');
}

/*按名字搜索直播*/
function searchByNameDo() {
    var inputName = $('.searchByName').val();
    console.log(inputName);
    loadLiveList(inputName);
}

/*点击添加直播报道*/
function actionSelectReportDo() {
    console.log('添加报道');
    id = null;
    id = $(this).parent().parent().data('Liveid');
    if (!id) {
        return;
    }
    ;
    $('.select_report').show();
}

/*点击隐藏发布直播页*/
function hideSelectReportDo() {
    $('.select_report').hide();
}

/*选择报道类型*/
function chooseTypeDo() {
    mediaType = $(this).val();
    switch (parseInt(mediaType)) {
        case 1:
            chooseVideoDo();
            break;
        case 2:
            chooseImageDo();
            break;
        case 3:
            chooseAudioDo();
    }
}

function chooseVideoDo() {
    $('#file-3').fileinput('clear');
    $('#file-3').fileinput('refresh', {maxFileCount: 1, allowedPreviewTypes: ['video'], allowedFileTypes: ['video']});
}

function chooseImageDo() {
    $('#file-3').fileinput('clear');
    $('#file-3').fileinput('refresh', {maxFileCount: 4, allowedPreviewTypes: ['image'], allowedFileTypes: ['image']});
}

function chooseAudioDo() {
    $('#file-3').fileinput('clear');
    $('#file-3').fileinput('refresh', {maxFileCount: 1, allowedPreviewTypes: ['audio'], allowedFileTypes: ['audio']});
}

/*网页初始化*/
function initweb() {
    initFileUpload();
    chooseImageDo();
    loadLiveList();
}

/*下载直播数据*/
function loadLiveList(name) {
    var url = host + '/live/findLiveList.do';
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'deptId': '00010001'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewLiveList(result.rows);
        }
    })
}

/*渲染直播列表*/
function viewLiveList(data) {
    $('#liveListTable').empty();
    for (var i = 0; i < data.length; i++) {
        var trLive = liveTemplate;
        trLive = trLive.replace('[name]', data[i].name);
        trLive = trLive.replace('[createUserName]', data[i].createUserName);
        trLive = trLive.replace('[startTime]', data[i].startTime.substring(0, 16));
        var $tr = $(trLive).data("Liveid", data[i].id);
        $('#liveListTable').append($tr);
    }
}

/*直播列表模板*/
var liveTemplate = '<tr>' +
    '                <td>[name]</td>' +
    '                <td>[createUserName]</td>' +
    '                <td>[startTime]</td>' +
    '                <td><a>发布报道</a></td>' +
    '</tr>';

/*文件上传组件初始化*/
function initFileUpload() {
    $("#file-3").fileinput({
        theme: 'fa',
        language: 'zh',
        uploadUrl: 'http://192.168.2.153/portal/files/uploadFile.do',
        uploadAsync: false,
        showUpload: false,
        maxFileCount: 4,
        showCaption: false,
        browseClass: "btn btn-primary btn-lg",
        fileType: "any",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        overwriteInitial: false,
        initialPreviewAsData: true,
        layoutTemplates: {
            actionUpload: '',
        }
    });
}


// $('#file-3').on('filebatchpreupload', function(event, data) {
//      console.log(event);
//      console.log(data);
// });

// $('#file-3').on('fileloaded', function(event, file, previewId, index, reader) {
//     console.log(event);
//     console.log(file);
//     console.log(previewId);
//     console.log(index);
//     console.log(reader);
// });

$('#file-3').on('filebatchuploadsuccess', function (event, data) {
    var imgs = new Array();
    var j = 0;
    var datas = {};
    var files = data.files;

    for (var i = 0; i < files.length; i++) {
        console.log("哈哈");
        var file = files[i];
        var reader = new FileReader();
        reader.onload = function (e) {
            var dataImage = e.target.result;
            var image = new Image();
            image.onload = function () {
                var url = 'http://zx.huacainfo.com/' + data.response.value[j];
                var width = image.width;
                var height = image.height;
                imgs.push({
                    url: url,
                    w: width,
                    h: height
                });
                console.log(imgs);
                j++;
                if(i>=files.length){
                    var rpt = {
                        mediaType: mediaType,
                        content: $('.content').val(),
                        rid: id,
                        uid: 'oFvIjw8x1--0lQkUhO1Ta3L59o3c',
                        mediaContent: "",
                    };
                    console.log("**********"+JSON.stringify(imgs));
                    console.log("imgs=");
                    console.log(imgs);
                    datas.rpt = rpt;
                    datas.imgs = imgs;
                    console.log(JSON.stringify(datas));
                    var url = host + '/liveRpt/insertLiveRpt.do';
                    $.post(url, {jsons: JSON.stringify(datas)}, function (result) {
                        console.log(result);
                    });
                }
            };
            image.src = dataImage;
        };
        reader.readAsDataURL(file);
    }
    console.log("*--------------"+imgs.length);


});