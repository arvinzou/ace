$(function () {
    initweb();
    $('.top_card').on('click', '.btn', zhuce);
    $('body').on('click', '.file_p', downloadFile);
})


function zhuce() {
    window.location.href = '../service/form.html';
}

function downloadFile() {
    var $that = $(this);
    var resId = $that.parent().data('resId');
    var url = "../../download.html?id=" + resId;
    window.location.href = url;
}



function initweb() {
    var nodeId = localStorage["nodeId"];
    var url = "/fundtown/www/process/getNodeResList";
    var data = {
        nodeId: nodeId,
    };

    $.getJSON(url, data, function (result) {
        console.log(result);
        viewFileList(result.data['0'],'.fileList');
    });
}


function viewFileList(data, className) {
    if (!data) {
        return;
    }
    $(className).empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName);
        var $p = $(p);
        $p.data('resId', data[i].id);
        $(className).append($p);
    }
}

var fileTemplate = '<p><a class="file_p" href="javascript:void(0)">[resName]</a></p>';
