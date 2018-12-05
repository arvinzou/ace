window.onload = function () {
    new Mdate("dateSelectorOne");
    var date = new Date();
    $('#dateSelectorOne').val(date.getFullYear() + "年" + (date.getMonth() + 1) + '月').attr('data-year', date.getFullYear()).attr("data-month", date.getMonth() + 1);
    $('.month').on('change', 'input', getList);
    getList();
}

function getList() {
    getOrderList($('.month input').val());
}

function getOrderList(date) {
    var year = date.substring(0, 4);
    var momth = date.substring(date.indexOf('年') + 1, date.indexOf('月'));
    console.log(year + momth);
    var url = contextPath + "/www/order/findList";
    var data = {
        findType: '2',
        year: year,
        month: momth,
        statusArray:'2,6,7'
    };
    $.getJSON(url, data, function (rst) {
        console.log(rst);
    })
}