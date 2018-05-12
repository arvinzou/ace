function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:"page",start:0,limit:999},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				$('#' + o.id).html(o.value);
			});
		}
	});
}

function App() {
    console.log("=============================App Start chart3==============================");
    chart3() ;
}