var loading = {};
var primaryId = null;
var videoUrl = null;
function loadlocal() {
    var urls = [];
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/module.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/hotkeys.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/uploader.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/simditor.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/plupload/plupload.full.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
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
    var url =   window.location.href.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);

    initPartList();
}

function initpage(partId) {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            findPartCourseList(num, type, partId);
        }
    });
}

function addSeries(){
    var partName = $("input[name='partName']").val();
    if(partName == '' || partName == undefined){
        alert("章节名称不能为空！");
        return;
    }
    $.ajax({
        url: contextPath + "/coursePart/insertCoursePart",
        type:"post",
        async:false,
        data:{jsons:JSON.stringify({
                courseId: primaryId,
                name:partName
            })
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                alert("创建成功！");
                window.location.reload();
                initPartList();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function initPartList(){
    var data = findPartList();
    try{
        viewHtml('chapters', data.rows, 'chapterTemp');
        initpage(data.rows[0].id);
    }catch(e){}
}
function findPartList(){
    var resultData = null;
    $.ajax({
        url: contextPath + "/coursePart/findCoursePartList",
        type:"post",
        async:false,
        data:{
            page: 1,
            limit: 999,
            courseId: primaryId,
            orderBy: "displaySeq",
            sord: "asc"
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                resultData = result;
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    return resultData;
}

function findPartCourseList(num, type, partId){
    var url = contextPath + "/courseSource/findCourseSourceList";
    var data = {
        page: num,
        limit: 10,
        partId: partId
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("courseList", result.rows, "courseTemp");
        }
    })
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function changeChapter(id){
    initpage(id);
}

function add(){
    var partId = $("#chapters  .active").attr("datattr");
    window.location.href = contextPath+ '/dynamic/service/course/list/add/index.jsp?courseId='+primaryId+'&partId='+partId;
}

function deletePartCourse(id){

    $.ajax({
        url: contextPath + "/courseSource/deleteCourseSourceByCourseSourceId",
        type:"post",
        async:false,
        data:{jsons:JSON.stringify({
                id: id
            })
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                alert("删除成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function editCourseSource(id){
    $.ajax({
        url: contextPath + "/courseSource/selectCourseSourceByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: id
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                viewHtml('sourceBasic', result.value, 'courseSourceTemp');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
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
                {title: "files", extensions: "mp4,mp3"}
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                showUploadText('.viewPicture video', '.uploadText');
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）")
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                viewCover('http://zx.huacainfo.com/'+rst.value[0], '.pictureContainer','.viewPicture video','.uploadText');
                videoUrl = 'http://zx.huacainfo.com/'+rst.value[0];
                console.log(rst.value[0]);
            }
        }
    });
    video.init();

}

function updateCourseSource(sourceId, partId){
    var coursedoc = $("textarea[name = 'coursedoc']").val();
    var free = $('input[name="tried"]:checked').val();
    var duation = $("input[name = 'duation']").val();
    $.ajax({
        url: contextPath + "/courseSource/updateCourseSource",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
                id: sourceId,
                courseId: primaryId,
                partId: partId,
                mediUrl: videoUrl,
                duration: duation,
                free: free
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("修改成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

/*文件上传成功后*/
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

function initPartListOnModal(){
    var data = findPartList();
    try{
        viewHtml('chapter', data.rows, 'editChapterTemp');
    }catch(e){}
}

function updateChapter(partId){
    var name = $("input[name='chapterName']").val();
    var displaySeq = $("input[name='displaySeq']").val();
    if(name == '' || name == undefined){
        alert("章节名称不能为空！");
        return;
    }
    if(displaySeq == '' || displaySeq == undefined){
        alert("章节的显示顺序不能为空！");
        return;
    }
    $.ajax({
        url: contextPath + "/coursePart/updateCoursePart",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
                id: partId,
                courseId: primaryId,
                name: name,
                displaySeq: displaySeq
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("修改成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function deleteChapter(partId){
    $.ajax({
        url: contextPath + "/coursePart/deleteCoursePartByCoursePartId",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
                id: partId
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("删除成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}