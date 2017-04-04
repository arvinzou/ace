function addWorkflow(key, name, proxy) {
	$.ajax({
		type : "post",
		url : "/workflow/workflow/startProcessInstanceByKey.do",
		data : {
			key : key,
			proxy : proxy
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			if (rst.state) {
				initWorkflowList();
				var id = rst.response.id;
				console.log(rst);
				addPanel(name, rst.response.formResourceName + '?taskId=' + id,
						true);
			} else {
				alert(rst.errorMessage);
			}

		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {

		}
	});

}


function initWorkflowList() {
	$
			.ajax({
				type : "post",
				url : "/workflow/workflow/findPersonalTasks.do",
				data : {},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {

					if (rst) {
						var html = new Array();
						html.push('<li class="dropdown-header">');
						html.push('<i class="ace-icon fa fa-check"></i>');
						html.push(rst.value.length);
						html.push(' 任务待完成');
						html.push('</li>');
						for ( var i in rst.value) {
							var o = rst.value[i];

							html.push('<li>');
							html.push('<a href="javascript:addPanel(\''+o.name+'\',\'/workflow/dynamic/service/workflow/form-view-task.jsp?processInstanceId='+o.processInstanceId+'&taskId='+o.id+'&formId='+o.formKey+'\',true)">');
							html.push('<div class="clearfix">');
							html.push('<span class="pull-left">'+o.name+'</span>');
							html.push('<span class="pull-right">'+o.createTime+'</span>');
							html.push('</div>');
							html.push('<div class="progress progress-mini">');
							html.push('<div style="width:100%" class="progress-bar"></div>');
							html.push('</div>');
							html.push('</a>');
							html.push('</li>');

							console.log(rst.value[i]);
						}
						html.push('<li class="dropdown-footer">');
						html.push('<a href="#">');
						html.push('');
						//html.push('<i class="ace-icon fa fa-arrow-right"></i>');
						html.push('</a>');
						html.push('</li>');

						 $('#workflow-task-list').html(html.join(''));
						 $('#workflow-task-list-point').html(rst.value.length);
						 
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
}
