window.onload = function (){
    initPage();    //初始化显示单品课程
}
var payType = "";
var videoUrl = "";
var params={limit:2,type:'1'};
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start']=(num-1)*params.limit;
            params['initType']=type;
            getPageList();
        }
    });
}
function t_query(){
    getPageList();
    return false;
}
function getPageList() {
    var url = contextPath+ "/course/findCourseList";
     params['name']=$("input[name=keyword]").val();
     startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total
                });
            }
            renderPage("courseList", result.rows, "list");
        }
    })
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function makecourse(id){
    if(params.type=='1'){
        window.location.href = contextPath + '/dynamic/service/course/make.jsp?courseId='+id;
    }else{
        window.location.href = contextPath + '/dynamic/service/course/makeSeries.jsp?id='+id;
    }
}


function deleteCourse(id){
    $.ajax({
        url: contextPath + "/course/deleteCourseByCourseId",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
               id: id
            })
        },
        success:function(result){
            if(result.status == 0) {
               getPageList();
               alert("删除成功！");
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function changeCourseType(type){
    if(type == '1'){
        $(".commonCourse").show();
        $(".specialCourse").hide();
    }else if(type == '2'){
        $(".commonCourse").hide();
        $(".specialCourse").show();
    }
    params['type']=type;
    initPage();
}


function edit(id){
    /**
     * 查询课程基本信息
     */
    $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: id
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('courseBasic', result.value, 'editCourseTemp');
                initEditor();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function initEditor(){
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

function confirmEdit(id, type){
    var courseName = $("input[name = 'courseName']").val();
    var courseCover = $("#courseCover").attr("src");
    var introduction = $("textarea[name = 'introduction']").val();
    var price = $("input[name ='price']").val();
    payType = $(".payType .cactive").text()=="免费"?"0":"1";
    var free = $('input[name="tried"]:checked').val();
    var duration = $("input[name = 'duation']").val();
    videoUrl = $("#vedioSource").attr('src');
    $.ajax({
        url: contextPath + "/course/updateCourse",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
                id: id,
                type: type,
                category: '1',
                mediType: '1',
                name: courseName,
                cover: courseCover,
                duration: duration,
                costType: payType,
                cost: price,
                introduce: introduction,
                "courseSource":{
                    mediUrl: videoUrl,
                    free: free,
                    duration: duration
                }
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
function payTypeCheck(dom) {
    if (dom == 'noPay') {
        payType = '0';
        $('#' + dom).removeClass('uncactive').addClass('cactive');
        $('#pay').removeClass('cactive').addClass('uncactive');
    } else {
        payType = '1';
        $('#' + dom).removeClass('uncactive').addClass('cactive');
        $('#noPay').removeClass('cactive').addClass('uncactive');
    }
}

/**
 * 课程审核
 * @param id
 */
var courseId = null;
function openAudit(id, index){
    if(userProp.account != 'jxb'){
       alert("您不是近心帮的管理员，没有权限审核该课程");
       return;
    }
    courseId = id;
    $("#auditOpt"+index).attr("data-toggle","modal");
    $("#auditOpt"+index).attr("data-target","#audit");
}

function add(type){
    window.location.href = contextPath+ '/dynamic/service/course/add/index.jsp?type='+type;
}
function audit(){
    var statement = $("textarea[name = 'message']").val();
    var rst = $("input[name='auditState']:checked").val();
    var auditor = $("input[name='auditor']").val();
    $.ajax({
        url: contextPath + "/course/audit",
        type:"post",
        async:false,
        data:{
            data:JSON.stringify({
                courseId: courseId,
                statement: statement,
                rst: rst,
                auditor:auditor
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("操作成功！");
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
function online(id){
    if(confirm("确定要上线吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/course/updateLineState",
            type:"post",
            async:false,
            data:{
               courseId :id,
               state:'1'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
function outline(id){
    if(confirm("确定要下线吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/course/updateLineState",
            type:"post",
            async:false,
            data:{
               courseId :id,
               state:'0'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}