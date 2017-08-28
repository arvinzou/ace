var list=[];
jQuery(function($) {

	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$('#fm1').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			console.log(params);
			$.each(formData, function(n, obj) {
			});
			var ids = [],status=[],auditDiscribtion=[];
			var inputs = $("#fm1").find("input[name='id']");
			inputs.each(function(){
				var o = $(this);
				ids.push(o.val());
			});
			var inputs = $("#fm1").find("input[name='status']");
			inputs.each(function(){
				if($(this).is(':checked')){
					status.push("4");
				}else{
					status.push("3");
				}
			});
			var inputs = $("#fm1").find("input[name='auditDiscribtion']");
			inputs.each(function(){
				var o = $(this);
				auditDiscribtion.push(o.val());
			});
			for(var i=0;i<status.length;i++){
				var o={};
				o.id=ids[i];
				o.status=status[i];
				o.auditDiscribtion=auditDiscribtion[i];
				if(ids[i]!='undefined'){
					list.push(o);
				}
				
				console.log(o);
			}
			audit(list);
			return false;
		}
	});
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
});

function lookMemberInfo(id,status,departmentName,memberCode){
	parent.addPanel(departmentName,contextPath+'/dynamic/service/memberCenter/index.jsp?deptId='+memberCode,true);
}
function beforeSubmit(){
	return false;
}
var dialog={};
var params={};
function showAuditDia(departmentName,departmentId,pdeptId){
	dialog = $("#dialog-message").removeClass('hide').dialog({
		modal: true,
		width:800,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+departmentName+"</div></div>",
		title_html: true,
		buttons: [ 
			
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				id:'btn-audit',
				click: function() {
					$('#fm1').submit(); 
					jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					}).trigger("reloadGrid");
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
				} 
			}
		]
	});
	loadQualifications(departmentId, pdeptId);
}
function audit(list){
	console.log(list);
	$.ajax({
		type : "post",
		url : contextPath + "/qualifications/updateAudit.do",
		data:{jsons:JSON.stringify(list)},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-audit').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-audit').removeAttr("disabled");
			bootbox.dialog({
				title:'系统提示',
				message:rst.errorMessage,
				detail:rst.detail,
				buttons: 			
				{
					"success" :
					 {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback": function() {
							if(rst.status==0){
								$("#dialog-message").dialog("close");
							} 
						}
					}
				}
			});
		
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-audit').removeAttr("disabled");
		},
		error : function() {
			$('#btn-audit').removeAttr("disabled");
		}
	});
}


function loadQualifications(memberCode, pdeptId) {
	$.ajax({
		type : "get",
		url : contextPath + "/qualifications/selectQualificationsList.do",
		data : {
			deptId : memberCode, pdeptId:pdeptId, flag:2
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				butiQualificationsTable(rst.value)
			} else {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								$(this).dialog("close");
							}
						}
					}
				});
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {
		}
	});
}
var dttable;
function butiQualificationsTable(data) {
	if ($('#table1').hasClass('dataTable')) {
		dttable = $('#table1').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var html = new Array();
	$(data).each(function(index, o) {
						html.push('<tr>');
						html.push('<td  width="40px" class="center"><span class="badge badge-success">'+ (index + 1) + '</span></td>');
						html.push('<td width="250px">' + o.name + '<input type="hidden" name="id" value="'+o.id+'"/></td>');
						html.push('<td width="100px">');
						if(o.fileSrc){
							html.push('<a target="_blank" href="');
							html.push(fastdfs_server+(o.fileSrc==null?'':o.fileSrc));
							html.push('"');
							html.push('><span class="badge badge-info">查看</span></a>');
							if(o.status=='4'){
								html.push('<span class="badge badge-success">已认证</span>');
							}else if(o.status=='3'){
								html.push('<span class="badge badge-danger">认证失败【');
								html.push(o.auditDiscribtion==null?'':o.auditDiscribtion);
								html.push('】</span>');
							}
							
						}else{
							html.push('<span class="badge badge-danger">待上传</span>');
							
						}
						html.push('</td>');
						if(o.fileSrc){
							if(o.status=='4'){
								html.push('<td width="100px"><input class="ace ace-switch ace-switch-5" type="checkbox" name="status" checked="checked"/><span class="lbl"></span></td>');
							}else{
								html.push('<td width="100px"><input class="ace ace-switch ace-switch-5" type="checkbox" name="status"/><span class="lbl"></span></td>');
							}
						}else{
							html.push('<td width="100px"></td>');
						}
						if(o.fileSrc){
							html.push('<td width="150px"><input type="text" name="auditDiscribtion" value="');
							if(o.auditDiscribtion){
								html.push(o.auditDiscribtion);
							}
							
							html.push('"/></td>');
						}else{
							html.push('<td width="100px"></td>');
						}
						
						html.push('</tr>');
					});
	$('#table1').find('tbody').empty();
	$('#table1').find('tbody').append(html.join(''));
}