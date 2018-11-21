function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:activeSyId,start:0,limit:999},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				$('#' + o.id).html(o.value);
			});
		}
	});
}

function tpl() {
	$.ajax({
		type : "post",
		url : contextPath + '/dynamic/portal/tpl/tpl-'+activeSyId+'.jsp',
        timeout : 30000,
        dataType : 'text',
		success : function(data) {
			 $("body .page-content-inner").html(data);
			  chart3() ;
		}
	});
}

function App() {
    console.log("=============================App Start chart3==============================");
    tpl();

}

