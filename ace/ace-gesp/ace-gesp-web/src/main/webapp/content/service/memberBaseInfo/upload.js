var upload=[{},{},{},{},{}];
var uplaods={};
jQuery(function($) {
	buidUploader(uplaods,'simage',setInputValue,null,'filelist','pickfiles','container','console','uploadfiles','1','')
});

function buidUploader(uploader,targetInput,callBack,ids,filelist,browse_button,container,console,uploadfiles,flag,id){
	 uploader = new plupload.Uploader(
			{
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : browse_button, // you can pass in id...
				container : document.getElementById(container), // ... or
				url : portalPath + '/files/uploadFile.do?types=1',
				flash_swf_url : contextPath
						+ '/content/plupload-2.1.2/js/Moxie.swf',
				silverlight_xap_url : contextPath
						+ '/content/plupload-2.1.2/js/Moxie.xap',
				filters : {
					max_file_size : '100mb',
					mime_types : [ {
						title : "Image files",
						extensions : "jpg,gif,png,pdf"
					} ]
				},
				init : {
					PostInit : function() {
						document.getElementById(filelist).innerHTML = '';
						if(flag=="2"){
						}else{
							document.getElementById(uploadfiles).onclick = function() {
								uploader.start();
								return false;
							};
						}
					},
					FilesAdded : function(up, files) {
						if(flag=="2"){
							previewImage(files[0], function (imgsrc) {
									if(imgsrc){
										var name = files[0].name;
										var extStart=name.lastIndexOf(".");
										var ext=name.substring(extStart,name.length).toUpperCase();
										if(ext!=".JPG"&&ext!=".GIF"&&ext!=".PNG"){
											alert("只支持图片格式为jpg,gif,png");
										}else{
											$('#update_img').attr('src', imgsrc);
											$('#update_img').attr('name', name);
											uploader.start();
										}
									}
								});
						}else {
							plupload.each(files,
									function(file) {
										// alert(file.id);
										var name = files[0].name;
										var extStart=name.lastIndexOf(".");
										var ext=name.substring(extStart,name.length).toUpperCase();
										if(ext!=".JPG"&&ext!=".GIF"&&ext!=".PNG"&&ext!=".PDF"){
											alert("只支持格式为jpg,gif,png,pdf的图片和文件");
										}else{
											var fileId = "'"+file.id+"'";
											document.getElementById(filelist).innerHTML += '<sapn id="'+ file.id+ '">'+ file.name+ ' ('+ plupload.formatSize(file.size)+ ') <b><a href="javascript:deleteFile('+fileId+');">删除</a></b></span>';
										}
									});
						}
					},
					UploadProgress : function(up, file) {
						if(flag=="2"){
						}else{document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent+ "%</span>";}
					},
					Error : function(up, err) {
						document.getElementById(console).innerHTML += "\nError #"+ err.code + ": " + err.message;
					}
				}
			});

	uploader.init();
	uploader.bind("FileUploaded", function(uploader, file, responseObject) {
		
		var id = file.id;
		var rst = JSON.parse(responseObject.response);
		if (!rst.state) {
			alert(rst.errorMessage);
		} else {
			$.each(rst.value, function(n, o) {
				callBack(targetInput,o,ids,id);
			});
		}
	});
}
function setInputValue(targetInput,o){
	$('input[name='+targetInput+']').val(o);
}

function previewImage(file, callback) {//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
    if (!file || !/image\//.test(file.type)) return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
        var fr = new mOxie.FileReader();
        fr.onload = function () {
            callback(fr.result);
            fr.destroy();
            fr = null;
        }
        fr.readAsDataURL(file.getSource());
    } else {
        var preloader = new mOxie.Image();
        preloader.onload = function () {
            preloader.downsize(300, 300);//先压缩一下要预览的图片,宽300，高300
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
            callback && callback(imgsrc); //callback传入的参数为预览图片的url
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}

function deleteFile(id){
	$('#'+id).remove();
}
