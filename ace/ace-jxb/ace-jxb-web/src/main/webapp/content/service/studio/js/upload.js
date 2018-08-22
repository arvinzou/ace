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
    cover, againadd;


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
    '                        <img src="${pageContext.request.contextPath}/content/service/information/img/left_pic_two.jpg"' +
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
    preImg('/jxb/content/service/information/img/left_pic_two.jpg');
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

var uploader;


function initEvents() {
    $('#img-uploader').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        console.log(button);
        xsize = button.data('xsize');
        ysize = button.data('ysize');
        cover = button.data('cover');
        againadd = button.data('againadd');
        console.log(xsize + "/" + ysize);
        console.log(cover);
        console.log(button["0"].src);
        preImg(button["0"].src);
    });
}


function initUpload() {
    initEvents();
    console.log("initUpload");
    //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
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
            extensions: "jpg,gif,png"
        }] //图片限制
    });
    //在实例对象上调用init()方法进行初始化
    uploader.init();
    //图片选择完毕触发
    uploader.bind('filesAdded', function (uploader, files) {
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
    uploader.bind('FileUploaded', function (uploader, files, res) {
        var data = JSON.parse(res.response);
        if (againadd) {
            var html = '<div class="imgSrc">' +
                '           <div class="idCardBox">' +
                '                <img class="select_img form_idCardImgUrl" src="' + data.file_path + '">' +
                '               <div class="deleteBtn">X</div>' +
                '           </div>' +
                '     </div>';
            $('#indexImg').before($(html));
            var index = $('#indexImg').siblings().length;
            if (index == 4) {
                $('#indexImg').hide();
            }
        } else {
            var img = "#" + cover;
            $(img).attr("src", data.file_path);
            $(img).css("display", "block");
            $(img).css("max-width", xsize);
            $(img).css("max-height", ysize);
        }
        $('#img-uploader').modal('hide');
    });
    //会在文件上传过程中不断触发，可以用此事件来显示上传进度监听（比如说上传进度）
    uploader.bind('UploadProgress', function (uploader, file) {
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
    uploader.bind('Error', function (up, err) {
        alert("文件上传失败,错误信息: " + err.message);
    });
    $('#img-uploader .btn-success').on('click', function () {
        uploader.setOption({
            multipart_params: {
                x: parseInt(global_api.x),
                y: parseInt(global_api.y),
                desWidth: parseInt(global_api.w),
                desHeight: parseInt(global_api.h),
                srcWidth: parseInt(boundx),
                srcHeight: parseInt(boundy)
            }
        });
        uploader.start();
    });
    initEvents();
}