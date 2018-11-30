
function init_uploader(params,url) {
	$("#uploader").pluploadQueue(
			{
				runtimes : 'html5',
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
				resize : {
					width : 320,
					height : 240,
					quality : 90
				},
				url : url
			});

			var uploader = $('#uploader').pluploadQueue();
            	uploader.bind("UploadComplete", function() {

            	});
	uploader.bind("FileUploaded", function(uploader, file, responseObject) {
		console.log(responseObject.response);
		var rst = JSON.parse(responseObject.response);
		if (!rst.state) {
			alert(rst.errorMessage);
		} else {
		     alert(rst.errorMessage);
        	$('#modal-upload').modal('hide');
			viewFiles(meetingId);

		}
	});
}





function reset_uploader(params,url) {
	init_uploader(params,url);
}
function upload(params,url) {
    init_uploader(params,url);
    $('#modal-upload').modal('show');
}
