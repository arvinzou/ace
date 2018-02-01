var host = 'http://192.168.2.153/live';
var start;
var limit = 25;
var orderByStr = null;
var id;
var mediaType = 2;
var imgs = [];
var uploaderV;
var uploaderI = new plupload.Uploader({
    runtimes: 'html5,flash,silverlight,html4',
    browse_button: 'upbtn',
    url: 'http://192.168.2.153/portal/files/uploadFile.do',
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
            uploaderI.start();
            return false;
        },
        FileUploaded: function (uploader, file, responseObject) {
            var rst = JSON.parse(responseObject.response);
            viewCover(rst.value);
        }
    }
});
uploaderI.init();

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
    //chooseImageDo();
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
    if (uploaderI) {
        uploaderI.destroy()
    };
    /*文件上传*/
    uploaderV = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upbtn',
        url: 'http://192.168.2.153/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '10mb',
            mime_types: [
                {title: "video files", extensions: "3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv,mp3,acc,wav"}
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                console.log('uploadFile');
                uploaderV.start();
                return false;
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover(rst.value);
            }
        }
    });
}


function chooseImageDo() {
    console.log('image');
    if (uploaderV) {
        uploaderV.destroy()
    };
    /*文件上传*/
    uploaderI = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upbtn',
        url: 'http://192.168.2.153/portal/files/uploadFile.do',
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
                uploaderI.start();
                return false;
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover(rst.value);
            }
        }
    });
}

function chooseAudioDo() {

}

/*网页初始化*/
function initweb() {
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




