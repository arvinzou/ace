
jQuery(function($) {

	if(edit){
		loadForm(evTempleteId);
	}
	
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));


$('#btn-evTemplete-submit').on('click',function() {
	$('#fm-evTemplete').ajaxForm({
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
			var url=contextPath +"/evTemplete/insertEvTemplete.do"
			if(edit){
				url=contextPath +"/evTemplete/updateEvTemplete.do"
			}
			console.log(params);
			$.ajax({
				type : "post",
				url : url,
				data:{jsons:JSON.stringify(params)},
				beforeSend : function(XMLHttpRequest) {
					style_ajax_button('btn-evTemplete-submit',true);
				},
				success : function(rst, textStatus) {
					style_ajax_button('btn-evTemplete-submit',false);
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
					style_ajax_button('btn-evTemplete-submit',false);
				},
				error : function() {
					style_ajax_button('btn-evTemplete-submit',true);
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
		url : contextPath + "/evTemplete/selectEvTempleteByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$('#fm-evTemplete').form('load',rst.response);
				
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