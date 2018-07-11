$(function () {
    userInfo();
    initweb();
})
var status;

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
    }
    $.getJSON(url, data, function (result) {
        console.log(result);
        viewFileListFree(result.data['0']);
        viewFileList(result.data['0'], '.fileList');
        viewFileList(result.data['1'], '.fileListTwo');
    });
}


function viewFileList(data, className) {
    if (!className) {
        return;
    }
    $(className).empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName).replace('[resName]', data[i].resName);
        console.log(status);
        if (status == 0) {
            p = p.replace('[resUrl]', data[i].resUrl);
        } else if (status == 1) {
            p = p.replace('[resUrl]', "");
        }
        $(className).append($(p));
    }
}

function viewFileListFree(data, className) {
    if (className) {
        return;
    }
    $('.fileList_free').empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName)
            .replace('[resName1]', data[i].resName)
            .replace('[resUrl]', data[i].resUrl);
        $('.fileList_free').append($(p));
    }
}


var fileTemplate = '<p><a class="downFile" href="[resUrl]" download="[resName1]">[resName]</a></p>';
