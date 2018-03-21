jQuery(function($) {
	listTaskUser();
	$('#btn-print').on(
			'click',
			function() {
				var url = contextPath
						+ '/dynamic/service/evTask/print-task-user.jsp?id='
						+ urlid + '&evTaskId=' + gd.evTaskId + "&time="
						+ new Date();
				window.open(url);
			});
	$(document).on('click', 'th input:checkbox' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});
		$('#btn-del').on('click', function() {
		var pk=new Array();
		$("input[name='pk']").each(function() {
			if ($(this).get(0).checked) {
				pk.push($(this).val());
			}
		});
		if(pk[0]==null){
			alert("请选择要删除的用户！");
			return;
		}
		deleteEvTaskUsersByEvTaskUsersId(pk.join(','));
	});
});
window.setInterval(listTaskUser, 10000);
function listTaskUser() {
	$
			.ajax({
				type : "post",
				url : contextPath + "/evTaskUsers/findEvTaskUsersList.do",
				data : {
					evTaskId : evTaskId,
					limit : 99999
				},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
					var html = new Array();
					var i = 0;
					$
							.each(
									$(rst.list),
									function(i, o) {
										html.push('<tr>');
										html.push('<td align="center">');
										html
												.push('<label class="position-relative">');
										html
												.push('<input type="checkbox" name="pk" class="ace" value="'
														+ o.id + '" />');
										html.push('<span class="lbl"></span>');
										html.push('</label>');
										html.push('</td>');
										html.push('<td>' + (i + 1) + '</td>');
										html.push('<td>' + o.account + '</td>');
										html
												.push('<td>' + o.userName
														+ '</td>');
										html
												.push('<td>'
														+ (o.status == "1" ? "未投票"
																: "<span class='badge badge-info'>已投票</span>")
														+ '</td>');
										html
												.push('<td>'
														+ (o.taskTime == null ? ""
																: o.taskTime)
														+ '</td>');
										html.push('<td>'
												+ (o.evTime == null ? ""
														: o.evTime) + '</td>');
										if (o.status == "1") {
											html.push('<td><a href="javascript:deleteEvTaskUsersByEvTaskUsersId(\''
													+ o.id
													+ '\')">[删除]</a></td>');
										} else {
											html
													.push('<td><a href="javascript:updateForReset(\''
															+ o.id
															+ '\')">重新投票</a></td>');
										}

										html.push('</tr>');
										i++;
									});
					$('#task-content').html(html.join(''));
					$('#time').html(new Date());

				}
			});
}

function updateForReset(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/evTaskUsers/updateForReset.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			listTaskUser();
		}
	});
}
function deleteEvTaskUsersByEvTaskUsersId(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/evTaskUsers/deleteEvTaskUsersByEvTaskUsersId.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			listTaskUser();
		}
	});
}