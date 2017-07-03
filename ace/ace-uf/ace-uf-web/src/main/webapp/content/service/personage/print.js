jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));

	$('#btn-print').on('click', function() {
		window.print();
	});

	loadView(params);

});

function loadView(params) {
	$.ajax({
		type : "post",
		url : contextPath + '/personage/findPersonageList.do',
		data : params,
		beforeSend : function(XMLHttpRequest) {
		    $("#content").html("<tr><td colspan='12'><h2>加载中......</h2></td></tr>");
		},
		success : function(rst, textStatus) {
			var html = [];
			$.each(rst.rows, function(i, o) {
				html.push("<tr>");

				html.push("<td>");
				html.push(o.name);
				html.push("</td>");
				html.push("<td>");
				html.push(rsd(o.sex, '01'));
				html.push("</td>");
				html.push("<td>");
				html.push(rsd(o.nation, '09'));
				html.push("</td>");
				html.push("<td>");
				html.push(o.placeOfOriginName);
				html.push("</td>");
				html.push("<td>");
				html.push(new Date(o.birthday).pattern("yyyy-MM"));
				html.push("</td>");
				html.push("<td>");
				html.push(new Date(o.joinDate).pattern("yyyy-MM"));
				html.push("</td>");
				html.push("<td>");
				html.push(rsd(o.degreee, '10'));
				html.push("</td>");
				html.push("<td>");
				html.push(o.deptName + o.administrativeDuty
						+ rsd(o.academicTitle, '99'));
				html.push("</td>");
				html.push("<td>");
				html.push(new Date(o.currentPostDate).pattern("yyyy-MM-dd"));
				html.push("</td>");

				html.push("<td>");
				html.push(o.administrativeDuty);
				html.push("</td>");
				html.push("<td>");
				html.push(o.mobile);
				html.push("</td>");
				html.push("<td>");
				html.push("");
				html.push("</td>");
				html.push("</tr>");

			});
			$("#content").html(html.join(""));
			$("h1").html(area[params.areaCode]+rsd(params.category, '98')+"统战人士花名册");
			$("#deptName").html(userProp.corpName);
			$("#date").html(new Date().pattern("yyyy-MM-dd"));


		},
		error : function() {
			alert("加载错误！");
		}
	});
}
