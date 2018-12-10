window.onload = function () {
    var url =   window.location.href.substring(1);
    category = getParam('id');
    new Mdate("dateSelectorOne");
    var date = new Date();
    $('#dateSelectorOne').val(date.getFullYear() + "年" + (date.getMonth() + 1) + '月').attr('data-year', date.getFullYear()).attr("data-month", date.getMonth() + 1);
    $('.month').on('change', 'input', getList);
    getList();
}

var category;

function getList() {
    getOrderList($('.month input').val());
}

function getOrderList(date) {
    var year = date.substring(0, 4);
    var momth = date.substring(date.indexOf('年') + 1, date.indexOf('月'));
    var url = contextPath + "/www/order/findList";
    if(category==3){
        var url = contextPath + "/www/order/profitFindList";
    }
    var data = {
        findType: '2',
        cpuTag:'1',
        // year: year,
        // month: momth,
        category:category==3?'':category,
        statusArray:'2,6,7',
    };
    $.getJSON(url, data, function (rst) {
        if(category==3){
            var mylist = document.getElementById('tmp-list_3').innerHTML;
        }else{
            var mylist = document.getElementById('tmp-list').innerHTML;
        }
        var html = juicer(mylist, {
            data: rst.data.rows
        });
        $("#orderList").append(html);
    })
}

var getParam = function (name) {
    var search = document.location.search;
    //alert(search);
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
};