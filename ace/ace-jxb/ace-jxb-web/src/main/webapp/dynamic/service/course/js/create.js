var loading = {};
var payType = "0";   //付费类型，初始化为免费
var courseType = null;
function loadlocal() {
    var urls = [];
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/module.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/hotkeys.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/uploader.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/simditor.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/plupload/plupload.full.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js', type: 'js',callback:function(){
        $('input[name=courseName]').maxlength({alwaysShow: true});
    }});
    urls.push({path: portalPath, url: '/content/common/assets/pages/scripts/components-bootstrap-maxlength.min.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
    loader({
        path: portalPath,
        url: '/content/common/plupload/plupload.full.min.js',
        type: 'js',
        callback: function () {
            loader({
                path: contextPath,
                url: '/content/common/js/upload.js',
                type: 'js',
                callback: function () {
                    initUpload();
                }
            });
        }
    });
}


window.onload = function (){

    console.log(window.location.href);
    var url =   window.location.href.substring(1);
    courseType = url.substring(url.indexOf('=')+1);

    var editor = new Simditor({
        textarea: $('#courseIntro'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });

    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upbtn',
        url: portalPath+ '/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: {
            max_file_size: '2048mb',
            mime_types: [
                {title: "Image files", extensions: "jpg,gif,png"}
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                showUploadText('.viewPicture img', '.uploadText');
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）")
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover(rst.value[0], '.pictureContainer','.viewPicture img','.uploadText');
                /*coverImg = rst.value[0];*/
                console.log(rst.value[0]);
            }
        }
    });
    uploader.init();
}

/*图片上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).data('imgSrc',img);
    var imagePath=img;
    showUploadImg(imagePath, imgClazz, textClazz);
}

/*显示上传文字*/
function showUploadText(imgClazz, textClazz) {
    $(imgClazz).prop('src','');
    $(textClazz).show();
}

/*显示上传图片*/
function showUploadImg(imgpath, imgClazz, textClazz) {
    $(imgClazz).removeClass('hidder').addClass('displayer');
    $(imgClazz).prop('src',imgpath);
    $(textClazz).hide();
}

function payTypeCheck(dom){
    if(dom == 'noPay'){
        payType = '0';
        $('.price-panel').hide();
    }else{
        payType = '1';
        $('.price-panel').show();
    }
}

function save(){
    var courseName = $("input[name='courseName']").val();
    var courseCover = $("#courseCover").attr('src');
    var introduction = $("textarea[name='introduction']").val();
    var duration = 0;
    var cost = $("input[name='price']").val();
    var type = courseType;
    var category = "1";
    var mediType = "1";
    $.ajax({
        url: contextPath + "/course/insertCourse",
        type:"post",
        async:false,
        data:{jsons:JSON.stringify({
                type: type,
                category: category,
                mediType: mediType,
                name: courseName,
                introduce: introduction,
                cover: courseCover,
                duration: duration,
                costType: payType,
                cost: cost,
                demandNum: 0,
                likeNum: 0
            })
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                alert("创建成功！");
                window.location.href = contextPath + '/dynamic/service/course/index.jsp';
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}