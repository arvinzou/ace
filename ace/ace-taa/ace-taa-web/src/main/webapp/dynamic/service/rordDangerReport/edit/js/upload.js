jQuery(function ($) {
    init_uploader({
        extensions: "doc,docx,ppt,pptx,pdf,jpg,gif,png,bmp,jpeg",
        url: portalPath + '/files/uploadImage.do',
        target: "url",
        multipart_params: {}
    });
});

function init_uploader(config) {
    $("#uploader").pluploadQueue({
        runtimes: 'html5,flash,silverlight,html4',
        chunk_size: '1mb',
        unique_names: true,
        multipart_params: config.multipart_params,
        filters: {
            max_file_size: '10mb',
            mime_types: [{
                title: "Image files",
                extensions: config.extensions
            }]
        },
        // Resize images on clientside if we can
        resize: {
            width: 600,
            height: 600,
            quality: 90
        },

        url: config.url,
    });
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {

    });
    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        console.log(responseObject.response);
        var rst = JSON.parse(responseObject.response);
        if (!rst.success) {
            alert(rst.errorMessage);
        } else {
            $('#images ul').append("<li>\n" +
                "                            <img src=\"" + rst.file_path + "\" alt=\"\">\n" +
                "                        </li>");
        }
        $('#modal-upload').modal('hide');
    });
}

/*
function reset_uploader(config) {
	var uploader = $('#uploader').pluploadQueue();
	uploader.splice();
	uploader.refresh();
	init_uploader(config);
}*/

/*function appendUploadImageBtn() {
	$("#btn-upload-add").on('click',
			function(e) {
				e.preventDefault();
				var config = {
					extensions: "jpg,gif,png,bmp",
					url: portalPath + '/files/uploadImage.do',
					target: id,
					multipart_params: {}
				};
				reset_uploader(config);
				$('#modal-upload').modal('show');

			});

}*/



