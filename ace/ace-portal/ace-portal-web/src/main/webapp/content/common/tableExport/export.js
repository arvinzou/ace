var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'MS-Excel'
        //powerpoint: 'MS-Powerpoint'
       // pdf: 'PDF'
};
var $menu = $('<span class="widget-toolbar no-border"><button class="btn btn-xs bigger btn-white dropdown-toggle" data-toggle="dropdown" authority="false">导出<i class="ace-icon fa fa-chevron-down icon-on-right"></i></button><ul class="dropdown-menu dropdown-default dropdown-menu-right dropdown-caret dropdown-close" role="menu" aria-labelledby="dropdownMenu"></ul></span>');
//var exportTypes = ['txt','excel','json','xml','png','csv','sql','doc','pdf','powerpoint'];
var exportTypes = ['txt','excel','json','xml','png','csv','sql','doc'];
var tempStr = "";
for(var i = 0;i < exportTypes.length;i++){
    tempStr += '<li data-type="'+exportTypes[i]+'"><a href="javascript:void(0)">'+TYPE_NAME[exportTypes[i]]+'</a></li>';
}
$menu.find("ul.dropdown-menu ").html(tempStr);
$menu.find("li").click(function () {
    var type = $(this).data('type');
    var doExport = function () {
            if(!cfg.fileName){
                cfg.fileName="表格数据";
            }
            var dd = $('.ui-jqgrid-htable thead').clone();//找到<thead>
            var ee = $(cfg.grid_selector).clone();
            ee.find('.jqgfirstrow').remove();//干掉多余的无效行
            ee.find('tbody').before(dd);//合并表头和表数据
            ee.find('tr.ui-search-toolbar').remove();//干掉搜索框
            $(ee.find('th')).each(function(i,obj){
                if($(obj).css("display")=="none"){
                    $(obj).remove();
                }
            });
            $(ee.find('td')).each(function(i,obj){
                if($(obj).css("display")=="none"){
                    $(obj).remove();
                }
            });
            //console.log($(ee).html());
           console.log({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            //Regex reg = new Regex(@"(?i)<(/?(?:table|tr|td))\b[^>]*>");
            var domstr=$(ee).html();
            var html=domstr.replace(/<(?!(table|tbody|th|thead|tr|td)[ >])[^>/]*>/gi,"");
            html = html.replace(/[\r\n]/g, "");
            html=html.replace(/<\/?span[^>]*>/gi,'');
            html=html.replace(/<\/?div[^>]*>/gi,'');
            html=html.replace(/style=".*?"/gi,'');
            html=html.replace(/class=".*?"/gi,'');
            html=html.replace(/role=".*?"/gi,'');
            html=html.replace(/id=".*?"/gi,'');
            html=html.replace(/aria-describedby=".*?"/gi,'');
            html=html.replace(/title=".*?"/gi,'');
            html=html.replace(/tabindex=".*?"/gi,'');
            html=html.replace(/^\s+|\s+$/ig, '');
            html=html.replace(/&nbsp;/ig, '');
            html=html.replace(/ /ig, '');
            html="<table id='exportHandle'><caption>"+cfg.fileName+"</caption>"+html+"</table>";
            console.log(html);
            $("#tableExport").html(html);
            $("#tableExport").removeClass("hide");
            $("#exportHandle").tableExport({type:type,fileName: cfg.fileName+'v'+getNowFormatDate()});
            $("#tableExport").addClass("hide");
        };
   doExport();
});
//导出按钮的构建
$(".toolbar").append($menu);
$("body").append('<div id="tableExport"></div>');
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