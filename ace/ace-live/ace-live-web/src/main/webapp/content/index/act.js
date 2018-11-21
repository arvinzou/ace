function App(){


}
Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

 window.onload=function(){
        var data={reportId:"portal",userId:userProp.userId};
        var tplId="tpl-portal-1";

        $.ajax({
    		type : "post",
    		url : contextPath + '/anslysis/query',
    		data:data,
    		success : function(rst) {
    		    var data={};
    			$.each(rst.value, function(i,o) {
    				data[o.id]=o.value;
    			});
    			console.log(data);
    			render($(".page-content-inner"), data, tplId)
    		}
    	});
    }