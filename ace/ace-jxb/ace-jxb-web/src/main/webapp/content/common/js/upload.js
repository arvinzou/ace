var jcrop_api, //jcrop对象
    boundx, //图片实际显示宽度
    boundy, //图片实际显示高度
    realWidth, // 真实图片宽度
    realHeight, //真实图片高度
    // 使用的jquery对象
    $target,
    $pimg,
    global_api,
    xsize,
    ysize,
    cover;

var modelTemplate = '<div class="modal fade" id="img-uploader" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
    '        <div class="modal-dialog" style="width: 1100px;" role="document">' +
    '            <div class="modal-content">' +
    '                <div class="modal-header">' +
    '                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
    '                        <span aria-hidden="true">&times;</span>' +
    '                    </button>' +
    '                    <h4 class="modal-title">图片上传</h4>' +
    '                </div>' +
    '                <div class="modal-body row jcrop center">' +
    '                    <!-- 原图 -->' +
    '                    <div class="col-md-6 original-pane">' +
    '                        <img src=""' +
    '                             id="target" alt="原图"/>' +
    // '                        <div class="btn-group" style="padding-top:10px">'+
    // '                            <button class="btn btn-default" type="button" onclick="resetSize(100,100)">16:16</button>'+
    // '                        </div>'+
    '                    </div>' +
    '                    <!-- 预览盒子 -->' +
    '                    <div class="col-md-6">' +
    '                        <div class="row">' +
    '                            <div class="preview-pane" style="overflow: hidden;">' +
    '                                <img src="${pageContext.request.contextPath}/content/service/information/img/left_pic_two.jpg"' +
    '                                     class="preview" alt="Preview" id="Preview"/>' +
    '                            </div>' +
    '                        </div>' +
    '                        <div class="row" id="proc" style="margin-top: 30px">' +
    '                        </div>' +
    '                        <div class="row" style="padding-top: 50px;">' +
    '                            <button type="button" class="btn btn-success btn-lg" id="browse">本地上传</button>' +
    '                        </div>' +
    '                    </div>' +
    '                </div>' +
    '                <div class="modal-footer">' +
    '                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>' +
    '                    <button type="button" class="btn btn-success">确认</button>' +
    '                </div>' +
    '            </div>' +
    '        </div>' +
    '    </div>';


jQuery(function ($) {
    $('body').append($(modelTemplate));
    $target = $('#target');
    $pimg = $('.preview-pane img');
    xsize = 300;
    ysize = 300;
    preImg(portalPath+'/content/common/image/upload-default.jpg');
});

//3、将本地图片 显示到浏览器上
function preImg(url) {
    console.log('url===' + url);
    //图片裁剪逻辑
    if (jcrop_api) //判断jcrop_api是否被初始化过
    {
        jcrop_api.destroy();
    }

    //初始化预览div内容
    initPreview();
    var p = document.getElementById('Preview');
    p.src = url;
    //初始化图片
    initTarget();
    var image = document.getElementById('target');
    image.onload = function () { //图片加载是一个异步的过程
        //获取图片文件真实宽度和大小
        var img = new Image();
        img.onload = function () {
            realWidth = img.width;
            realHeight = img.height;
            //获取图片真实高度之后
            initJcrop(); //初始化Jcrop插件
        };
        img.src = url;
    };
    image.src = url;
}

//初始化Jcrop插件
function initJcrop() {
    console.log('init', [xsize, ysize]);
    $target.removeAttr("style"); //清空上一次初始化设置的样式
    $target.Jcrop({
        onChange: updatePreview,
        onSelect: updatePreview,
        boxWidth: 500,
        aspectRatio: xsize / ysize,
        setSelect: [20, 20, 400, 400],
    }, function () {
        //初始化后回调函数
        // 获取图片实际显示的大小
        var bounds = this.getBounds();
        boundx = bounds[0]; //图片实际显示宽度
        boundy = bounds[1]; //图片实际显示高度
        // 保存jcrop_api变量
        jcrop_api = this;
    });
}


//更新显示预览内容
function updatePreview(c) {
    global_api = c;
    // console.log(c);
    // console.log(boundx + "," + boundy);
    if (parseInt(c.w) > 0) {
        var rx = xsize / c.w;
        var ry = ysize / c.h;
        $pimg.css({
            maxWidth: Math.round(rx * boundx) + 'px',
            maxHeight: Math.round(ry * boundy) + 'px',
            width: Math.round(rx * boundx) + 'px',
            height: Math.round(ry * boundy) + 'px',
            marginLeft: '-' + Math.round(rx * c.x) + 'px',
            marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
        $(".preview-pane").css("width", xsize + 'px');
        $(".preview-pane").css("height", ysize + 'px');
    }
}

//初始化预览div内容
function initTarget() {
    $target.removeAttr("style"); //清空上一次初始化设置的样式
    $target.css({
        maxWidth: '100%',
        maxHeight: '100%'
    });
}

//初始化预览div内容
function initPreview() {
    $pimg.removeAttr("style"); //清空上一次初始化设置的样式
    $pimg.css({
        maxWidth: xsize + 'px',
        maxHeight: ysize + 'px'
    });
}

function previewImage(file, callback) {
    if (!file || !/image\//.test(file.type)) return;
    if (file.type == 'image/gif') {
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
            preloader.downsize(1024, 1024);
            var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 1024) : preloader.getAsDataURL();
            callback && callback(imgsrc);
            preloader.destroy();
            preloader = null;
        };
        preloader.load(file.getSource());
    }
}



function initUploadEvents() {
    $('#img-uploader').on('show.bs.modal', function (event) {
        $('.progress-bar-success').css('width', '0%');
        var button = $(event.relatedTarget);
        xsize = button.data('xsize');
        ysize = button.data('ysize');
        cover = button.data('cover');
        preImg(portalPath+'/content/common/image/upload-default.jpg');
    });
}


function initUpload() {
    initUploadEvents();
    console.log("initUpload");
    //实例化一个plupload上传对象
    var uploaderHeadimg = new plupload.Uploader({
        browse_button: 'browse', //触发文件选择对话框的按钮，为那个元素id
        url: portalPath + '/files/uploadImage.do', //服务器端的上传页面地址
        max_file_size: '2mb', //限制为2MB
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 90,
            preserve_headers: false
        },
        filters: [{
            title: "Image files",
            extensions: "jpg,jpeg,gif,png"
        }] //图片限制
    });
    //在实例对象上调用init()方法进行初始化
    uploaderHeadimg.init();
    //图片选择完毕触发
    uploaderHeadimg.bind('filesAdded', function (uploaderHeadimg, files) {
        for (var i = 0, len = files.length; i < len; i++) {
            !function (i) {
                previewImage(files[i], function (imgsrc) {
                    //console.log(imgsrc);
                    preImg(imgsrc);
                })
            }(i);
        }
    });
    //图片上传成功触发，ps:data是返回值（第三个参数是返回值）
    uploaderHeadimg.bind('FileUploaded', function (uploaderHeadimg, files, res) {
        var data = JSON.parse(res.response);
        var img = "#" + cover;
        $(img).attr("src", data.file_path);
        $(img).css("display", "block");
        $(img).css("max-width", xsize);
        $(img).css("max-height", ysize);
        $("input[name="+cover+"]").val(data.file_path);
        $('#img-uploader').modal('hide');


    });
    //会在文件上传过程中不断触发，可以用此事件来显示上传进度监听（比如说上传进度）
    uploaderHeadimg.bind('UploadProgress', function (uploaderHeadimg, file) {
        var percent = file.percent;
        console.log(percent);
        var html = [];
        html.push('<div class="progress">');
        html.push(
            '<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"'
        );
        html.push('style="width: ' + percent + '%">');
        html.push('<span class="sr-only">' + percent + '% Complete (success)</span>');
        html.push('</div>');
        html.push('</div>');
        $("#proc").html(html.join(''));
    });
    uploaderHeadimg.bind('Error', function (up, err) {
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
    $('#img-uploader .btn-success').on('click', function () {
        uploaderHeadimg.setOption({
            multipart_params: {
                x: parseInt(global_api.x),
                y: parseInt(global_api.y),
                desWidth: parseInt(global_api.w),
                desHeight: parseInt(global_api.h),
                srcWidth: parseInt(boundx),
                srcHeight: parseInt(boundy)
            }
        });
        uploaderHeadimg.start();
    });
    // initEvents();
}

function resetSize(x, y) {
    xsize = x;
    ysize = y;
    console.log(xsize + "/" + ysize);
    preImg(portalPath+'/content/common/image/upload-default.jpg');
}
