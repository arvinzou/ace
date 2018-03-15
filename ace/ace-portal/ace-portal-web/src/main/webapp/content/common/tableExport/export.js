var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'MS-Excel',
        powerpoint: 'MS-Powerpoint',
        pdf: 'PDF'
};
var $menu = $('<div class="top-menu"><ul class="nav navbar-nav pull-left"><li class="dropdown dropdown-user"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"><span class="username username-hide-on-mobile">导出</span> <i class="fa fa-angle-down"></i></a><ul class="dropdown-menu dropdown-menu-default"></ul></li></ul></div>');
var exportTypes = ['txt','excel','json','xml','png','csv','sql','doc','pdf','powerpoint'];
var tempStr = "";
for(var i = 0;i < exportTypes.length;i++){
    tempStr += '<li data-type="'+exportTypes[i]+'"><a href="javascript:void(0)">'+TYPE_NAME[exportTypes[i]]+'</a></li>';
}
$menu.find("ul.dropdown-menu ").html(tempStr);
$menu.find(".dropdown-menu-default li").click(function () {
    var type = $(this).data('type');
    var doExport = function () {
            var dd = $('.ui-jqgrid-htable thead').clone();//找到<thead>
            var ee = $(cfg.grid_selector).clone();
            ee.find('.jqgfirstrow').remove();//干掉多余的无效行
            ee.find('tbody').before(dd);//合并表头和表数据
            ee.find('tr.ui-search-toolbar').remove();//干掉搜索框
            console.log({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            //Regex reg = new Regex(@"(?i)<(/?(?:table|tr|td))\b[^>]*>");
            var domstr=$(ee).html();
            $("#tableExport").html("<table id='exportHandle'><caption>"+cfg.fileName+"</caption>"+domstr.replace(/<(?!(table|tbody|th|thead|tr|td)[ >])[^>/]*>/img,"")+"</table>");
            $("#tableExport").removeClass("hide");
            $("#exportHandle").tableExport({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            $("#tableExport").addClass("hide");
        };
   doExport();
});
//导出按钮的构建
$("body").find('.jqgrid-export').replaceWith($menu);

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "";
    var seperator2 = "";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + "" + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}