var courseId = null;
var sourceId = null;
function App() {
    loader({
        path: contextPath,
        url: '/www/view/common/js/star-rating.js',
        type: 'js',
        callback: function () {
            jQuery(document).ready(function() {
                var $inp = $('#rating-input');
                $inp.rating({
                    min: 0,
                    max: 5,
                    step: 1,
                    size: 'xs',
                    showClear: false
                });
            });
        }
    });

    console.log(window.location.href);
    var url =   window.location.href.substring(window.location.href.indexOf("?")+1, window.location.href.length+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "courseId"){
                courseId = value;
            }
            if(name == "sourceId"){
                sourceId = value;
            }
        }
    }
    console.log("courseId:"+courseId+"\n"+"sourceId:"+sourceId+"\n");
    initCourseInfo(courseId);
}

function initCourseInfo(courseId){
    $.ajax({
        url: contextPath+ "/www/course/findCourseDetail",
        type:"post",
        async:false,
        data:{
            courseId: courseId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('banner', result.data, 'bannerTemp');
                viewHtml('teacherName', result.data, 'teacherTemp');
                var type = result.data.type;
                if(type == '1'){
                    //单节课程
                    viewHtml('sourceTitle', result.data.courseSource, 'sourceNameTemp');
                    viewHtml('courseAudio', result.data.courseSource, 'audioTemp');
                }else if(type == '2'){
                    //系列课程
                    findSourceInfo(sourceId);
                }
            }else {
                alert(result.info);
                return;
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

function showCatalog(courseId, partId){
    if(partId == '0'){   //0代表是单节课程，没有目录
        alert("改课程为单节课程，没有目录信息！");
    }else{
        window.location.href = contextPath + '/www/view/catalogues/index.jsp?id='+courseId;
    }
}

function showScripts(sourceId){
    window.location.href = contextPath + '/www/view/courseDetail/index.jsp?id='+sourceId;
}

function findSourceInfo(sourceId){
    $.ajax({
        url: contextPath+ "/www/course/findCourseSource",
        type:"post",
        async:false,
        data:{
            srcId: sourceId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('sourceTitle', result.data, 'sourceNameTemp');
                viewHtml('courseAudio', result.data, 'audioTemp');

            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

