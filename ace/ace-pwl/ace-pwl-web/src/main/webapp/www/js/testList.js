var serviceUrl = "http://localhost/portal";
var serviceImg = "http://zx.huacainfo.com/";
var syid = 'pwl';

$(function () {
    loadTestList();
    $('.test_list').on('click', 'li', enterTest);
});

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
    var url = serviceUrl + "/www/test/getEvaluatTplList.do";
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
    itmp = itmp.replace('#imgUrl#', data.cover);
    itmp = itmp.replace('#name#', data.name);
    itmp = itmp.replace('#introduce#', data.introduce);
    return $(itmp).data('id', data.id);
}

var listTemplate = '<li>' +
    '					<div class="img">' +
    '						<img src="' + serviceImg + '#imgUrl#"/>' +
    '					</div>' +
    '					<div class="text"  >' +
    '						<p class="title">#name#</p>' +
    '						<p class="remark">#introduce#</p>' +
    '					</div>' +
    '				</li>';