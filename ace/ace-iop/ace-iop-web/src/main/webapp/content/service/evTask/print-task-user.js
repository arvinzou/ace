var gd={};
jQuery(function($) {
	loadView(evTaskId);
	$('#btn-print').on('click', function() {
		window.print();
	});
});
function listTaskUser() {
	$.ajax({
		type : "post",
		url : contextPath + "/evTaskUsers/selectPrintUserListByDeptId.do",
		data : {
			evTaskId : evTaskId,
			limit : 99999
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html = new Array();
			$.each($(rst.list), function(i, o) {
				html.push('<tr>');
				html.push('<td colspan="2" align="center">No.'+(i+1)+"   "+gd.evTaskName+'</td>');
				html.push('</tr>');
				html.push('<tr>');
				html.push('<td>用户名：'+o.ACCOUNT+' (字母大写)</td>');
				html.push('<td>密 码： '+o.SEAT+'</td>');
				html.push('</tr>');
				html.push('<tr>');
				html.push('<td>投票截止时间：'+gd.evDeadline+'</td>');
				html.push('<td>投票网址：http://218.75.129.10:8080/cas</td>');
				html.push('</tr>');
				html.push('<tr>');
				html.push('<td colspan="2"><hr style="height:1px;border:none;border-top:1px dashed #000000;" /></td>');
				html.push('</tr>');
			});
			$('#task-content').html(html.join(''));
		}
	});
}

function loadView(id) {
	$.ajax({
		type : "get",
		url : contextPath + "/evTask/selectEvTaskByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				$.each(rst.response, function(key, value) {
					if(key=='status'){
						rst.response.status=rsd(value, "STATIC_DATA_54");
					}
					if(key=='evTempleteId'){
						rst.response.evTempleteId=rsd(value, "STATIC_DATA_53");
					}
					if(key=='category'){
						rst.response.category=rsd(value, "STATIC_DATA_20");
					}
				});
				gd = rst.response;
			} else {
				alert(rst.errorMessage);
			}
			listTaskUser();
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function(XMLHttpRequest, textStatus) {
			alert(textStatus);
		}
	})
}