jQuery(function($) {
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date(),
					departmentId : '',
				});
				//console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				
				return false;
			}
		});
	});

	$('#btn-view-add').on(
			'click',
			function() {
				
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						'new',
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : false,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
				if (gr) {
					var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
					$('#parentDepartmentId').val(r.departmentId);
					$('#departmentLevel').val(parseInt(r.departmentLevel)+1);
				}
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
				}
				jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						gr,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	
	$('#btn-view-del').on(
			'click',
			function() {
				
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext);
					return;
				}
				jQuery(cfg.grid_selector).jqGrid(
						'delGridRow',
						gr,
						{
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />')
								style_edit_form(form);
							}
						})
			});
	
	
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));
	
	$('#tt').tree({
		onClick: function(node){
			autotreeq(node);
		}
	});
	$( "#btn-view-da" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
		'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		var departmentId=r.departmentId;
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:610,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>机构人员</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-view-submit',
					click: function() {
						$( this ).dialog( "close" ); 
						//batchUpdateUserAndTeacherByUserIds(departmentId);
						
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
		selectUsersListByDepartmentId(departmentId);
		
	});
	
	$( "#btn-view-ins" ).on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
		'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext);
			return;
		}
		var r=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
		var departmentId=r.departmentId;
		if(confirm("确定要申请入会吗？")){
			insertMemberInfo(departmentId);
		}
	});
});

function selectUsersListByDepartmentId(departmentId){
	$.ajax({
		type : "post",
		url : contextPath + "/department/selectUsersListByDepartmentId.do",
		data:{departmentId:departmentId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html=new Array();
			$.each($(rst.value),function(i,o){
				html.push('<div class="layout-user" >');
				html.push('<user id="'+o.user_id+'" class="badge badge-'+cssColor9[0]+'">');
				html.push(o.name);
				html.push('</user>');
				html.push('</div>');
			});
			$('#task-content-tmp').html(html.join(''));
			//alert(html.join(''));
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}
function autotreeq(node){
	//$('#fm-search').find(":input[name='departmentId']").val(node.id);
	//console.log(params);
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {departmentId:node.id,parentDepartmentId:''}
	}).trigger("reloadGrid");
	
}
function treeAutoSelect(){
	var node = $('#tt').tree('getSelected');
	if(node){
		$(cfg.grid_selector).setSelection(node.id);
	}
	
}
function treeappend(){
	if(!authorConfig.hasOwnProperty(cfg.grid_add_data_url)){
		alert('受限的权限！');
		return;
	}
    var t = $('#tt');
    var node = t.tree('getSelected');
    jQuery(cfg.grid_selector).jqGrid(
			'editGridRow',
			'new',
			{
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : false,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find(
							'.ui-jqdialog-titlebar').wrapInner(
							'<div class="widget-header" />')
					style_edit_form(form);
				}
			})
	$('#parentDepartmentId').val(node.id);
	$('#departmentLevel').val(parseInt(node.src)+1);
}
function treeedit(){
	if(!authorConfig.hasOwnProperty(cfg.grid_edit_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	
	jQuery(cfg.grid_selector).jqGrid(
			'editGridRow',
			gr,
			{
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find(
							'.ui-jqdialog-titlebar').wrapInner(
							'<div class="widget-header" />')
					style_edit_form(form);
				}
			})
}
function treeremove(){
	if(!authorConfig.hasOwnProperty(cfg.grid_delete_data_url)){
		alert('受限的权限！');
		return;
	}
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	if(!gr){
		var node = $('#tt').tree('getSelected');
		$(cfg.grid_selector).setSelection(node.id);
	}
	gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
	'selrow');
	jQuery(cfg.grid_selector).jqGrid(
			'delGridRow',
			gr,
			{
				closeAfterAdd : true,
				recreateForm : true,
				viewPagerButtons : true,
				beforeShowForm : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find(
							'.ui-jqdialog-titlebar').wrapInner(
							'<div class="widget-header" />')
					style_edit_form(form);
				}
			})
}
/*
function append(){
    var t = $('#tt');
    var node = t.tree('getSelected');
    t.tree('append', {
        parent: (node?node.target:null),
        data: [{
            text: 'new item1'
        },{
            text: 'new item2'
        }]
    });
}
function removeit(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('remove', node.target);
}*/
function collapse(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('collapse',node.target);
}
function expand(){
    var node = $('#tt').tree('getSelected');
    $('#tt').tree('expand',node.target);
}
function treereload(){
	$('#tt').tree('reload');
}

function clearQparams(){
	$('#cc1').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {parentDepartmentId:'',departmentId:''}
	}).trigger("reloadGrid");
}
function clearAreaCode(){
	$('#cc2').combotree('setValue', '');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		page : 1,
		postData : {areaCode:'',parentDepartmentId:'',departmentId:''}
	}).trigger("reloadGrid");
}
function insertMemberInfo(departmentId){
	var params={memberCode:departmentId};
	$.ajax({
		type : "post",
		url : "/kernel/memberInfo/insertMemberInfo.do",
		data:{jsons : JSON.stringify(params)},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst) {
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