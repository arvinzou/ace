$(function() {
	initCrop();
	$("#cropModal").on("show.bs.modal",readyUpload);   
	$("#cropModal").on("hide.bs.modal",destroyUpload);  
})

var jcrop_api, //截屏工具的对象
	boundx, //图片真实长
	boundy, //图片真实宽
	// Grab some information about the preview pane
	$preview,
	$pcnt,
	$pimg, //预览框对象。
	global_api,
	xsize, //div长宽
	uploaderCropimg,
	ysize;

var defaultPic = 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535620109816&di=221d2fb7c66f9b6fc2500b90baa9cdb6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F902397dda144ad3436cc41afdba20cf431ad85a3.jpg';

var cropModal = '<style>.jc-demo-box input[type="radio"] {visibility: hidden;}</style>'+
	'    <div class="modal fade bs-example-modal-lg" id="cropModal" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myLargeModalLabel">' +
	'			<div class="modal-dialog modal-lg" role="document" style="max-width: 820px !important;">' +
	'				<div class="modal-content">' +
	'					<div class="modal-header">' +
	'						<h4 class="modal-title" id="myModalLabel">裁切图片</h4>' +
	'						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
	'						' +
	'					</div>' +
	'					<div class="modal-body">' +
	'						<div class="jc-demo-box">' +
	'							<img src="' + defaultPic + '" id="target" alt="[Jcrop Example]" />' +
	'							<div id="preview-pane" style="display: block;' +
	'position: absolute;z-index: 2000;top: 10px;' +
	'right: -280px;padding: 6px;border: 1px rgba(0, 0, 0, .4) solid;' +
	'background-color: white;' +
	'-webkit-border-radius: 6px;' +
	'-moz-border-radius: 6px;' +
	'border-radius: 6px;' +
	'-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);">' +
	'								<div class="preview-container" style="width: 250px;height: 500px;overflow: hidden;">' +
	'									<img src="' + defaultPic + '" />' +
	'								</div>' +
	'							</div>' +
	'							' +
	'						</div>' +
	'					</div>' +
	'                   <div class="progress crop-progress" style="height: 4px; border-radius:0;">' +
	'  						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%">' +
  	'						</div>' +
	'					</div>' +
	'					<div class="modal-footer">' +
	'						<button id="selectCropImg" type="button" class="btn btn-success">选择文件</button>' +
	'						<button type="button" class="btn btn-Crop-primary btn-primary">确定</button>' +
	'					</div>' +
	'				</div>' +
	'			</div>' +
	'		</div>';

function initCrop() {
	$('body').append($(cropModal));
    $pcnt.height(500);
    $pcnt.width(250);
	cropObject();
}

function destroyUpload(){
	uploaderCropimg.destroy();
}

function readyUpload(event){
	uploadObject();
	var button = $(event.relatedTarget);
    xs = button.data('xsize');
    ys = button.data('ysize');
    cover = button.data('cover');
    var pcntW=$pcnt.width();
    var pcntH=$pcnt.height();
    ysize=(pcntW/xs)*ys;
    if(ysize>pcntH){
		ysize=pcntH;
		xsize=(pcntW/ys)*xs;
	}else {
    	xsize=pcntW
	}
    $pcnt.height(ysize);
    $pcnt.width(xsize);
    jcrop_api.setOptions({
    	aspectRatio: xsize / ysize
    });
}

function uploadObject() {
	uploaderCropimg = new plupload.Uploader({
		browse_button: 'selectCropImg', //触发文件选择对话框的按钮，为那个元素id
		url: portalPath + '/files/uploadImage.do',//服务器端的上传页面地址
		max_file_size: '2mb', //限制为2MB
		multi_selection: false,
		resize: {
			width: 1024,
			height: 1024,
			crop: true,
			quality: 90,
			preserve_headers: false
		},
		filters: [{
			title: "Image files",
			extensions: "jpeg,jpg,gif,png"
		}] //图片限制
	});
	//在实例对象上调用init()方法进行初始化
	uploaderCropimg.init();
	uploaderCropimg.bind('filesAdded', function(uploader, files) {
		previewImage(files[0], function(imgsrc) {
			setCropImg(imgsrc);
		})
	});
	
	 //图片上传成功触发，ps:data是返回值（第三个参数是返回值）
    uploaderCropimg.bind('FileUploaded', function (uploaderCropimg, files, res) {
        var data = JSON.parse(res.response);
        var img = "#" + cover;
        $(img).attr("src", data.file_path);
        $(img).css("display", "block");
        $(img).css("max-width", xsize);
        $(img).css("max-height", ysize);
        $('#img-uploader').modal('hide');
    });
    //会在文件上传过程中不断触发，可以用此事件来显示上传进度监听（比如说上传进度）
    uploaderCropimg.bind('UploadProgress', function (uploaderCropimg, file) {
    	var percent = file.percent;
        $('.crop-progress .progress-bar').width(percent+'%');
    });
	/*上传完成之后*/
    uploaderCropimg.bind('UploadComplete', function (uploaderCropimg, file) {
        $('.crop-progress .progress-bar').width('0%');
    	$('#cropModal').modal('hide');
    });


    
    /*错误提示*/
    uploaderCropimg.bind('Error', function (up, err) {
        switch (err.code) {
            case -200:
                alert("文件上传失败,错误信息: 网络错误");
                break;
            case -300:
                alert("文件上传失败,错误信息: 本地上文件不可读");
                break;
            case -400:
                alert("文件上传失败,错误信息: 存在安全问题");
                break;
            case -500:
                alert("文件上传失败,错误信息: 初始化时发生错误");
                break;
            case -600:
                alert("文件上传失败,错误信息: 上传文件太大(2M以内)");
                break;
            case -601:
                alert("文件上传失败,错误信息: 文件类型不符合要求");
                break;
            case -602:
                alert("文件上传失败,错误信息: 选取了重复的文件");
                break;
            case -700:
                alert("文件上传失败,错误信息: 图片格式错误");
                break;
            case -702:
                alert("文件上传失败,错误信息: 文件太大小，无法处理");
                break;
        }
    });
    $('#cropModal .btn-Crop-primary').on('click', function () {
        uploaderCropimg.setOption({
            multipart_params: {
                x: parseInt(global_api.x),
                y: parseInt(global_api.y),
                desWidth: parseInt(global_api.w),
                desHeight: parseInt(global_api.h),
                srcWidth: parseInt(boundx),
                srcHeight: parseInt(boundy)
            }
        });
        uploaderCropimg.start();
    });
}

function previewImage(file, callback) {
	if(!file || !/image\//.test(file.type)) return;
	if(file.type == 'image/gif') {
		var fr = new mOxie.FileReader();
		fr.onload = function() {
			callback(fr.result);
			fr.destroy();
			fr = null;
		}
		fr.readAsDataURL(file.getSource());
	} else {
		var preloader = new mOxie.Image();
		preloader.onload = function() {
			preloader.downsize(1024, 1024);
			var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 1024) : preloader.getAsDataURL();
			callback && callback(imgsrc);
			preloader.destroy();
			preloader = null;
		};
		preloader.load(file.getSource());
	}
}

function cropObject() {
	$preview = $('#preview-pane');
	$pcnt = $('#preview-pane .preview-container');
	$pimg = $('#preview-pane .preview-container img');
//	xsize = $pcnt.width(); //div长宽
//	ysize = $pcnt.height();
	$('#target').Jcrop({
		onChange: updatePreview, //改变
		onSelect: updatePreview, //选择框选时
		boxWidth: 500, //画布宽度
		aspectRatio: xsize / ysize, //选框高宽比
        setSelect: [0, 0, 200, 200]
	}, function() {
		var bounds = this.getBounds();
		boundx = bounds[0];
		boundy = bounds[1];
		// Store the API in the jcrop_api variable
		jcrop_api = this;
		$preview.appendTo(jcrop_api.ui.holder);
		// Move the preview into the jcrop container for css positioning
	});
}

function setCropImg(imgUrl) {
	$pimg.attr('src',imgUrl)
	jcrop_api.setImage(imgUrl, function() {
		var bounds = jcrop_api.getBounds(); //图片的真实大小
		boundx = bounds[0];
		boundy = bounds[1];
		$preview.appendTo(jcrop_api.ui.holder);
	});
}

function updatePreview(c) {
	global_api=c;
	/*
	{x: 0, y: 0, x2: 619.8529411764706, y2: 421.5, w: 619.8529411764706, …}
	h:421.5
	w:619.8529411764706 选框右下角定位坐标（宽度）
	x:0   选框左上角定位坐标
	x2:619.8529411764706 选框右下角定位坐标
	y:0   选框左上角定位坐标
	y2:421.5 选框右下角定位坐标
	*/
	if(parseInt(c.w) > 0) {
		var rx = xsize / c.w;
		var ry = ysize / c.h;

		$pimg.css({
			width: Math.round(rx * boundx) + 'px',
			height: Math.round(ry * boundy) + 'px',
			marginLeft: '-' + Math.round(rx * c.x) + 'px',
			marginTop: '-' + Math.round(ry * c.y) + 'px'
		});
	}
};