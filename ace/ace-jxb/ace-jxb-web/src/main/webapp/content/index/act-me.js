function App(){
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:"portalMe",userId:userProp.userId},
		success : function(rst) {
		    var data={};
			$.each(rst.value, function(i,o) {
				data[o.id]=o.value;
			});
			console.log(data);
			render($(".page-content-inner"), data, "tpl-portal")
		}
	});
}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}