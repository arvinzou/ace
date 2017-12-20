jQuery(function($) {
	init_uploader(null,null);
});

function init_uploader(params,url) {
	$("#uploader").pluploadQueue(
			{
				runtimes : 'html5,flash,silverlight,html4',
				chunk_size : '50mb',
				unique_names : true,
				multipart_params : params,
				filters : {
					max_file_size : '500mb',
					mime_types : [{
						title : "Excel files",
						extensions : "xls,xlsx,ppt,pptx,doc,docx,zip,rar,jpg,gif,jpeg,png"
					}

					]
				},

				// Resize images on clientside if we can
				resize : {
					width : 320,
					height : 240,
					quality : 90
				},

				url : url,
				flash_swf_url : portalPath
						+ '/content/plupload-2.1.2/js/Moxie.swf',
				silverlight_xap_url : portalPath
						+ '/content/plupload-2.1.2/js/Moxie.xap',
			});
	var uploader = $('#uploader').pluploadQueue();
	uploader.bind("UploadComplete", function() {

	});
	uploader.bind("FileUploaded", function(uploader, file, responseObject) {
		console.log(responseObject.response);
		var rst = JSON.parse(responseObject.response);
		if (!rst.state) {
			bootbox.dialog({
				title : '系统提示',
				message : rst.errorMessage,
				detail : rst.detail,
				buttons : {
					"success" : {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback" : function() {
							$("#dialog-upload").dialog("close");
						}
					}
				}
			});

		} else {
			//$('input[name=photo]').val(rst.value);
			viewFiles(meetingId);
			alert(rst.errorMessage);
			$("#dialog-upload").dialog("close");
		}
	});
}

function reset_uploader(params,url) {
	//var uploader = $('#uploader').pluploadQueue();
	//uploader.splice();
	//uploader.refresh();
	init_uploader(params,url);
}
function upload(params,url) {
	reset_uploader(params,url);
	$("#dialog-upload")
			.removeClass('hide')
			.dialog(
					{
						modal : true,
						width : 750,
						title : "<div class='widget-header widget-header-small'><div class='widget-header-pd' >数据导入</div></div>",
						title_html : true,
						buttons : [
								{
									html : "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
									"class" : "btn btn-info btn-xs",
									click : function() {
										$(this).dialog("close");
									}
								},
								{
									html : "<i class='ace-icon glyphicon glyphicon-refresh bigger-110'></i>&nbsp; 重置",
									"class" : "btn btn-info btn-xs",
									id : 'ajax_button',
									click : function() {
										reset_uploader(params,url);
									}
								}

						]
					});

}
