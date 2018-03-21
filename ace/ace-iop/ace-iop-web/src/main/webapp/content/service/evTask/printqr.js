var gd={};
jQuery(function($) {
	loadView(evTaskId);
	$('#btn-print').on('click', function() {
		window.print();
	});
});
function listClasses() {
	$.ajax({
		type : "post",
		url : contextPath + "/evTask/selectVoteClassesListByDeptId.do",
		data : {
			deptId : deptId
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html = new Array();
			$.each($(rst.list), function(i, o) {
				html.push('<tr>');
				html.push('<td colspan="2" align="center" ><h1>No.'+(i+1)+"   "+o.grade_name+' '+o.classes_name+'<br>投票截止时间：'+gd.evDeadline+'</h1></td>');
				html.push('</tr>');
				html.push('<tr>');
				html.push('<td colspan="2" align="center"> ');
				html.push('<img src="'+contextPath+'/www/qr.do?url='+encodeURIComponent('http://zx.huacainfo.com/ev/dynamic/www/vote/index.jsp?deptId='+deptId+'&classId='+o.classes_id+'&gradeId='+o.grade_id+'&evTaskId='+evTaskId)+'&time='+new Date()+'" /></td>');
				html.push('</tr>');


				html.push('<tr>');
				html.push('<td colspan="2"><hr style="height:1px;border:none;border-top:1px dashed #000000;" /></td>');
				html.push('</tr>');
				var u='http://zx.huacainfo.com/ev/dynamic/www/vote/index.jsp?deptId='+deptId+'&classId='+o.classes_id+'&gradeId='+o.grade_id+'&evTaskId='+evTaskId;
				console.log(u);

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
			listClasses();
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function(XMLHttpRequest, textStatus) {
			alert(textStatus);
		}
	})
}