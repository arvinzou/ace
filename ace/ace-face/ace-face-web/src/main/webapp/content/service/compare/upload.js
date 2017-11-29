jQuery(function($) {
	init_uploader({
		extensions : "jpg,gif,png,bmp",
		url : portalPath + '/person/uploadFile.do',
		target : "url",
		multipart_params : {}
	});
});

function init_uploader(config) {
	$("#uploader").pluploadQueue(
			{
				runtimes : 'html5,flash,silverlight,html4',
				chunk_size : '1mb',
				unique_names : true,
				multipart_params : config.multipart_params,
				filters : {
					max_file_size : '10mb',
					mime_types : [{
						title : "Image files",
						extensions : config.extensions
					}]
				},

				// Resize images on clientside if we can
				resize : {
					width : 600,
					height : 600,
					quality : 90
				},

				url : config.url,
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
							$("#dialog-message").dialog("close");
						}
					}
				}
			});

		} else {

			$("#dialog-message").dialog("close");
			initData();
		}
	});
}

function reset_uploader(config) {
	var uploader = $('#uploader').pluploadQueue();
    console.log("==================$('#uploader')"+$('#uploader').html());
    console.log("uploader="+uploader);
	uploader.splice();
	uploader.refresh();
	init_uploader(config);
}


