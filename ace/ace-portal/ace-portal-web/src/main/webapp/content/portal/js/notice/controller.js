jQuery(function($) {
	$('#cc').combobox({
		onLoadSuccess: function(){
			if(edit){
				loadForm(noticeId);
				loadAttach(noticeId);
			}
		}
	});
	$('#btn-view-top').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext)
					return;
		}
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr)
		updateForTopByPrimaryKey(gd.noticeId);
	});
	$('#btn-view-push').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
				'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap,
					$.jgrid.nav.alerttext)
					return;
		}
		var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr)
		if (gd.status=='1') {
			alert("已发布过的公告不能重复发布!");
					return;
		}
		var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
			modal: true,
			width:550,
			title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> "+gd.title+"</h4></div>",
			title_html: true,
			buttons: [ 
				
				{
					html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
					"class" : "btn btn-info btn-xs",
					id:'btn-dialog-push',
					click: function() {
						updateForStatusByPrimaryKey(gd.noticeId);
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
	});
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

	$('#btn-view-add').on(
			'click',
			function() {
				parent.addPanel('公告编辑',contextPath+'/dynamic/portal/notice/add.jsp?id='+urlid,true);
			});
	$('#btn-view-edit').on(
			'click',
			function() {
				var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
						'selrow');
				if (!gr) {
					$.jgrid.info_dialog($.jgrid.nav.alertcap,
							$.jgrid.nav.alerttext)
							return;
				}
				var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr)
				parent.addPanel('公告编辑',contextPath+'/dynamic/portal/notice/add.jsp?id='+urlid+'&noticeId='+gd.noticeId,true);
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
	$('#btn-notice-submit').on('click',function() {
		$('#fm-notice').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				if(params.content==''){
					params.content=CKEDITOR.instances.content.getData();
				}
				var url=contextPath +"/notice/insertNotice.do"
				if(edit){
					url=contextPath +"/notice/updateNotice.do"
				}
				console.log(params);
				$.ajax({
					type : "post",
					url : url,
					data:{jsons:JSON.stringify(params)},
					beforeSend : function(XMLHttpRequest) {
						style_ajax_button('btn-notice-submit',true);
					},
					success : function(rst, textStatus) {
						style_ajax_button('btn-notice-submit',false);
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
											//关闭窗口
											parent.reloadGrid();
											parent.removePanel();
										}
									}
								}
							});
					
						}
					},
					complete : function(XMLHttpRequest, textStatus) {
						style_ajax_button('btn-notice-submit',false);
					},
					error : function() {
						style_ajax_button('btn-notice-submit',true);
					}
				});
				return false;
			}
		});	
	
	});
	
});
function style_ajax_button(btnId,status){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}
function loadForm(id){
	$.ajax({
		type : "get",
		url : contextPath + "/notice/selectNoticeByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$('#fm-notice').form('load',rst.value);
				CKEDITOR.instances.content.setData(rst.value.content);
			}else{
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
								//$( this ).dialog( "close" );
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
function loadAttach(noticeId){
	$.ajax({
		type : "get",
		url : contextPath + "/attach/findAttachList.do",
		data:{noticeId:noticeId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.value, function(n, file) {
					
					html.push('<div id="' + file.fileUrl + '"><a href="'+fastdfs_server+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.attachId+'\')"></a><b></b></div>');
				});
				//document.getElementById('filelist').innerHTML=html.join('');
				$('#filelist-history').html(html.join(''));
			}else{
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
								//$( this ).dialog( "close" );
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
function deleteAttach(fileName){
	$.ajax({
		type : "get",
		url : contextPath + "/attach/deleteAttachByFileName.do",
		data:{fileName:fileName},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				loadAttach(noticeId);
			}else{
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
								//$( this ).dialog( "close" );
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
function updateForTopByPrimaryKey(noticeId){
	$.ajax({
		type : "get",
		url : contextPath + "/notice/updateForTopByPrimaryKey.do",
		data:{noticeId:noticeId},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
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
							jQuery(cfg.grid_selector).jqGrid('setGridParam', {
							}).trigger("reloadGrid");
						}
					}
				}
			});
			
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
		}
	});
}
function updateForStatusByPrimaryKey(noticeId){
	var groupId=$('input[name=groupId]').val();
	var departmentId=$('input[name=departmentId]').val();
	$.ajax({
		type : "get",
		url : contextPath + "/notice/updateForStatusByPrimaryKey.do",
		data:{noticeId:noticeId,status:'1',groupId:groupId,departmentId:departmentId},
		beforeSend : function(XMLHttpRequest) {
			style_ajax_button('btn-dialog-push',true);
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$("#dialog-message" ).dialog( "close" );
				style_ajax_button('btn-dialog-push',false);
				alert(rst.errorMessage);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
				}).trigger("reloadGrid");
			}else{
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
								//$( this ).dialog( "close" );
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
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}