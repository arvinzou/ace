
jQuery(function($) {
    loadData();
});
function loadData() {
	$.ajax({
		type : "post",
		url : contextPath+"/www/live/getLiveList.do",
		data : {
		},
		beforeSend : function(XMLHttpRequest) {
             $.showLoading();
		},
		success : function(rst, textStatus) {
            console.log(rst);
            $.hideLoading();
             var html=[];
             var tpl=document.getElementById('pagetmpl').text;
             var pagefn = doT.template(tpl, undefined, undefined);
            $(rst).each(function(i,o){
                html.push(pagefn(o));
            });
            $(".page__bd").append(html.join(" "));
            $(document.body).pullToRefreshDone();
		},
		error : function() {
		    $.hideLoading();
			alert("加载错误！");
		}
	});
}
$(function() {
   FastClick.attach(document.body);
});
var start=0;
  $(document.body).pullToRefresh().on("pull-to-refresh", function() {
    setTimeout(function() {
       loadData();
    }, 100);
});