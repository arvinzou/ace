var status;

$(function () {
    userInfo();
    initweb();
    $('body').on('click', '.file_p', downloadFile);
})

// function downloadFile() {
//     if (status != 2) {
//         alert("审核通过才能下载。")
//     }
// }

function downloadFile() {
    var $that = $(this);
    var resId = $that.parent().data('resId');
    console.log(resId);
    if (!resId) {
        alert("审核通过才能下载。");
    }
    var url = "../../download.html?id=" + resId;
    window.location.href = url;
}

function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.ajaxSettings.async = false;
    $.getJSON(url, function (result) {
        status = result.data.vipStatus;
    });
    $.ajaxSettings.async = true;
}

function initweb() {
    var nodeId = localStorage["nodeId"];
    var url = "/fundtown/www/process/getNodeResList";
    var data = {
        nodeId: nodeId,
    };
    $.getJSON(url, data, function (result) {
        console.log(result);
        viewFileList(result.data['0'], '.fileList');
        viewFileList(result.data['1'], '.fileListTwo');
    });
}


// function viewFileList(data, className) {
//     if (!data) {
//         return;
//     }
//     $(className).empty();
//     for (var i = 0; i < data.length; i++) {
//         var p = fileTemplate;
//         p = p.replace('[resName]', data[i].resName)
//             .replace('[id]', status == 2 ? '/fundtown/www/download/file?id=' + data[i].id : '#');
//         $(className).append($(p));
//     }
// }
//
// var fileTemplate = '<p><a class="file_p" href="[id]">[resName]</a></p>';



function viewFileList(data, className) {
    if (!data) {
        return;
    }
    $(className).empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName);
        var $p = $(p);
        if (status == 2) {
            $p.data('resId', data[i].id);
        }
        $(className).append($p);
    }
}

var fileTemplate = '<p><a class="file_p" href="javascript:void(0)">[resName]</a></p>';
