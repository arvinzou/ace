var loading = {};
var videoUrl = '';
var tryvideoUrl = '';
var primaryId = null;
var partId = null;
var sourceId = null;
function loadlocal() {
    var urls = [];
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/module.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/hotkeys.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/uploader.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/simditor.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/plupload/plupload.full.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}

window.onload = function(){

    console.log(window.location.href);
    var url =   window.location.href.substring(window.location.href.indexOf("?")+1, window.location.href.length+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "courseId"){
                primaryId = value;
            }
            if(name == "partId"){
                partId = value;
            }
            if(name == "sourceId"){
                sourceId = value;
            }
        }
    }
    console.log("courseId:"+primaryId+"\n"+"partId:"+partId+"\n");
    findSourceByCourseId();
    var editor = new Simditor({
        textarea: $('#coursedoc'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    var video = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'video',
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
                {title: "files", extensions: "mp3"}
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                showUploadText('.viewPicture p', '.uploadText');
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）")
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover('http://zx.huacainfo.com/'+rst.value[0], '.pictureContainer','.viewPicture p','.uploadText');
                videoUrl = 'http://zx.huacainfo.com/'+rst.value[0];
                console.log(rst.value[0]);
            }
        }
    });
    video.init();
}


/*文件上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).text(img);
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

function save(sourceId){
    //如果sourceId不为空的话，操作就是更新
    var coursedoc = $("textarea[name = 'coursedoc']").val();
    var duation = $("input[name = 'duation']").val();
    var free = $('input[name="tried"]:checked').val();
    var courseName = findCourseById();
    if(partId == null || partId == undefined){
        partId = '0';
    }

    if(sourceId == null || sourceId == undefined || sourceId == ''){  //插入操作
        $.ajax({
            url: contextPath + "/courseSource/insertCourseSource",
            type:"post",
            async:false,
            data:{jsons:JSON.stringify({
                    courseId: primaryId,
                    name: courseName,
                    partId: partId,
                    free: free,
                    mediUrl: videoUrl,
                    introduction: coursedoc
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
    }else{   //更新操作
        $.ajax({
            url: contextPath + "/courseSource/updateCourseSource",
            type:"post",
            async:false,
            data:{jsons:JSON.stringify({
                    id: sourceId,
                    courseId: primaryId,
                    name: courseName,
                    partId: partId,
                    free: free,
                    mediUrl: videoUrl,
                    introduction: coursedoc
                })
            },
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    alert("更新成功！");
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

}

function findCourseById(){
    var courseName = '';
    $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                courseName = result.value.name;
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    return courseName;
}

function findSourceByCourseId(){
    $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                var dataSource = {
                    "courseId":"",
                    "createDate":"",
                    "duration":"",
                    "free":"",
                    "id":"",
                    "introduction":"",
                    "mediUrl":"",
                    "name":"",
                    "partId":""
                }
                if(result.value.courseSource == undefined || result.value.courseSource == null){
                    viewHtml('courseSource', dataSource, 'courseSourceTemp');
                }else{
                    viewHtml('courseSource', result.value.courseSource, 'courseSourceTemp');
                }
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}