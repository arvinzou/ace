
var syid = 'pwl';

$(function () {
    loadTestList();
    $('.test_list').on('click', '.buttom', enterTest);
})

function enterTest() {
    var $that = $(this);
    var id = $that.parent().data("id");
        //存储变量的值
    location.href = 'test.html?id=' + id;

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
            vue1.testlist = result.data;
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

var vue1 = new Vue({
    el: "#vue_test",
    data: {
        testlist: ""
    }
})

