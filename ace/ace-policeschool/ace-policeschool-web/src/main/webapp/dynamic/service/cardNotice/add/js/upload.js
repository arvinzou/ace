var uploader;
function initUploader(){
	uploader = new plupload.Uploader({
		runtimes : 'html5',
		browse_button : 'pickfiles',
        multiple:false,
        container: document.getElementById('container'),
		url : portalPath+'/attach/uploadFile.do?noticeId='+id+'&collectionName=notice',
		filters : {
			max_file_size : '100mb',
			mime_types: [
	            {title : "Office files", extensions : "pdf"}
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
                var node=document.getElementById('filelist');
                if(node.firstChild){
                    node.removeChild(node.firstChild)
				}
                $.each(up.files, function (i, file) {
                    if (up.files.length <= 1) {
                        return;
                    }
                    up.removeFile(file);
                });
				plupload.each(files, function(file) {
                    node.innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},
			UploadProgress: function(up, file) {
                document.getElementById('filelist').getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},
	
			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});
	
	
	uploader.bind("FileUploaded", function (uploader,file,responseObject) {
			console.log(file.id);
			var id=file.id;
			var rst=JSON.parse(responseObject.response);
		if (!rst.state) {
			alert(rst.errorMessage);
		}else{
            $('#filelist-history').empty();
			var html=[];
			$.each(rst.value, function(n, file) {
				html.push('<div id="' + file.fileUrl + '"> <a href="'+fastdfs_server+"/"+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.attachId+'\')"></a><b></b></div>');
			});
			document.getElementById('filelist-history').innerHTML+=html.join('');
			$('#'+id).html('');
			
		}
	});
	uploader.init();
}