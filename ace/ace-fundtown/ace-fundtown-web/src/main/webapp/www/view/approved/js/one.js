$(function () {
    initweb();
    $('.top_card').on('click', '.btn', zhuce);

})


function zhuce() {
    window.location.href = '../service/form.html';
}


function initweb() {
    var nodeId = localStorage["nodeId"];
    var url = "/fundtown/www/process/getNodeResList";
    var data = {
        nodeId: nodeId,
    };

    $.getJSON(url, data, function (result) {
        console.log(result);
        viewFileList(result.data['0']);
    });
}


function viewFileList(data, className) {
    $('.fileList').empty();
    for (var i = 0; i < data.length; i++) {
        var p = fileTemplate;
        p = p.replace('[resName]', data[i].resName)
            .replace('[id]', data[i].id);
        $('.fileList').append($(p));
    }
}


var fileTemplate = '<p><a class="" href="/fundtown/www/download/file?id=[id]">[resName]</a></p>';
