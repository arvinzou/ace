$(function () {
    initweb();
})

function initweb() {
    var nodeId = localStorage["nodeId"];
    var url = "/fundtown/www/process/getNodeResList";
    var data = {
        nodeId: nodeId,
    }
    $.getJSON(url, data, function (result) {
        console.log(result);
        viewFileList(result.data['0'], '.fileList');
        viewFileList(result.data['1'], '.fileListTwo');
    });
}

function viewFileList(data, className) {
    $(className).empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName)
            .replace('[resName]', data[i].resName)
            .replace('[resUrl]', data[i].resUrl);
        $(className).append($(p));
    }
}


var fileTemplate = '<p><a class="downFile" href="[resUrl]" download="[resName]">[resName]</a></p>';
