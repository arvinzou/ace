var serviceImg = "http://zx.huacainfo.com/";
var syid = 'pwl';

$(function () {
    loadTestList();
    $('.test_list').on('click', '.test', enterTest);
})

function enterTest() {
    var $this = $(this);
    if (window.localStorage) {
        //存储变量的值
        localStorage.id = $this.data('id');
        location.href = 'test.html';
    } else {
        alert("NOT SUPPORT");
    }
}

function loadTestList() {
    var url = "/portal/www/test/getEvaluatTplList.do";
    var data = {
        page: 1,
        limit: 1000,
        syid: syid
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewList(result.data);
        }
    });
}

function viewList(data) {
    $('.test_list').empty();
    for (var i = 0; i < data.length; i++) {
        $('.test_list').append(replaceStr(data[i]));
    }
}

function replaceStr(data) {
    var itmp = listTemplate;
    itmp = itmp.replace('#name#', data.name);
    //itmp = itmp.replace('#introduce#', data.introduce);
    return $(itmp).data('id', data.id);
}

var listTemplate = '<div class="test">' +
    '						<div class="title">' +
    '							<p>#name#</p>' +
    '							<p class="number">1次</p>' +
    '						</div>' +
    '						<div class="pen_icon">' +
    '							<i class="iconfont">&#xe63c;</i>' +
    '						</div>' +
    '			     </div>';
