jQuery(function($) {
	//init_uploader();

	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'pickfiles', // you can pass in id...
		container: document.getElementById('container'), // ... or DOM Element itself
		url : contextPath+'/attach/uploadFile.do?noticeId='+noticeId+'&collectionName=notice',
    		flash_swf_url : contextPath+'/content/plupload-2.1.2/js/Moxie.swf',
    		silverlight_xap_url : contextPath+'/content/plupload-2.1.2/js/Moxie.xap',
		filters : {
			max_file_size : '100mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
	            {title : "Office files", extensions : "xls,xlsx,doc,docx,ppt,pdf"},
	            {title : "Artive files", extensions : "zip,rar,gzip"}
			]
		},

		init: {
			PostInit: function() {
				document.getElementById('filelist').innerHTML = '';

				document.getElementById('uploadfiles').onclick = function() {
					uploader.start();
					return false;
				};
			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					//alert(file.id);
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},

			UploadProgress: function(up, file) {
				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},

			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});

	uploader.init();
	uploader.bind("FileUploaded", function (uploader,file,responseObject) {
			console.log(file.id);
			var id=file.id;
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
							 
						}
					}
				}
			});
	
		}else{
			var html=[];
			$.each(rst.value, function(n, file) {
				
				html.push('<div id="' + file.fileUrl + '"> <a href="'+fastdfs_server+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.attachId+'\')"></a><b></b></div>');
			});
			document.getElementById('filelist-history').innerHTML+=html.join('');
			$('#'+id).html('');
			
		}
    });
});
