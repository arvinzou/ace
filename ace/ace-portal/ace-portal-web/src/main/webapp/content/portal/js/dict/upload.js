jQuery(function($) {
	init_uploader();
});

function init_uploader(){
	 $("#uploader").pluploadQueue({
			runtimes : 'html5,flash,silverlight,html4',
	        chunk_size : '1mb',
	        unique_names : true,
	        multipart_params:{id:'EC12395876'}, 
	        filters : {
	            max_file_size : '10mb',
	            mime_types: [
	                //{title : "Image files", extensions : "jpg,gif,png"},
	                {title : "Excel files", extensions : "xls,xlsx"}
	            ]
	        },
	 
	        // Resize images on clientside if we can
	        resize : {width : 320, height : 240, quality : 90},
	 
	        url : contextPath+'/dict/importXls.do',
	    	flash_swf_url : contextPath+'/content/common/js/plupload-2.1.2/js/Moxie.swf',
	    	silverlight_xap_url : contextPath+'/content/common/js/plupload-2.1.2/js/Moxie.xap',
	    });
	 	var uploader = $('#uploader').pluploadQueue();
	    uploader.bind("UploadComplete", function () {

	    });
	    uploader.bind("FileUploaded", function (uploader,file,responseObject) {
   			console.log(responseObject.response);
   			var rst=JSON.parse(responseObject.response);
   			if (!rst.state) {
   				
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
								$( "#dialog-message" ).dialog( "close" );
							}
						}
					}
				});
		
			}else{
				alert(rst.errorMessage);
				$( "#dialog-message" ).dialog( "close" ); 
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
				}).trigger("reloadGrid");
			}
	    });
}

function reset_uploader(){
	var uploader = $('#uploader').pluploadQueue();
	uploader.splice();
	uploader.refresh();
	init_uploader();
}