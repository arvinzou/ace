$(function () {
    initWeb();
    $('.btn_list').on('click', 'li', linkurl);
})

function initWeb() {
    var url = '/fundtown/www/process/getNodeList';
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewNodeList(result.data);
            return;
        }
        alert("网络异常~刷新重试。")
    })
}

function viewNodeList(datas) {
    $('#node_list').empty();
    for (var i = 0; i < datas.length; i++) {
        var temp = nodeTemplate;
        var data = datas[i];
        temp = temp.replace('[sequence]', data.sequence)
            .replace('[curNodeName]', data.curNodeName);
        var obj = {
            id: data.id,
            linkUrl: data.linkUrl
        }
        $('#node_list').append($(temp).data(obj));
    }
}

function linkurl() {
    var $that = $(this);
    var obj = $that.data();
    console.log(obj);
    if (window.localStorage) {
        //存储变量的值
        localStorage.nodeId = obj['id'];
        location.href = '../approved/' + obj['linkUrl'];
    } else {
        alert("NOT SUPPORT");
    }
}

var nodeTemplate = '<li>' +
    '            <div class="left">' +
    '                <span class="num">0[sequence]</span>' +
    '                <span class="name">[curNodeName]</span>' +
    '            </div>' +
    '            <div class="right">查看</div>' +
    '        </li>';
