
var editor;
jQuery(function($) {

	if(edit){
		loadForm(evTaskId);
	}
	
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title: function(title) {
			var $title = this.options.title || '&nbsp;'
			if( ("title_html" in this.options) && this.options.title_html == true )
				title.html($title);
			else title.text($title);
		}
	}));


$('#btn-evTask-submit').on('click',function() {
	$('#fm-evTask').ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			var params = {};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
				
			});
			$.extend(params, {
				time : new Date()
			});
			params.evContent=editor.getValue();
			var url=contextPath +"/evTask/updateByPrimaryKeySelective.do"
			if(edit){
				url=contextPath +"/evTask/updateByPrimaryKeySelective.do"
			}
			
			console.log(params);
			$.ajax({
				type : "post",
				url : url,
				data:{jsons:JSON.stringify(params)},
				beforeSend : function(XMLHttpRequest) {
					style_ajax_button('btn-evTask-submit',true);
				},
				success : function(rst, textStatus) {
					style_ajax_button('btn-evTask-submit',false);
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
					style_ajax_button('btn-evTask-submit',false);
				},
				error : function() {
					style_ajax_button('btn-evTask-submit',true);
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
		url : contextPath + "/evTask/selectEvTaskByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				$('#fm-evTask').form('load',rst.response);
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
					initSimditor($("textarea[name=evContent]"),rst.response.evContent);
				});
				$.each(rst.response, function(key, value) {
					$('#' + key).html(value);
				});

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

function initSimditor(textarea,text){
            editor = new Simditor({
                 textarea:textarea,
                 params :{},
                 toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough','fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
                             upload: {
                                 url: '/files/uploadImage.do', //文件上传的接口地址
                                 params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                                 fileKey: 'file', //服务器端获取文件数据的参数名
                                 connectionCount: 3,
                                 leaveConfirm: '正在上传文件'
                             }
             });
             if(text){
                   editor.setValue(text);
             }
}