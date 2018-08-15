function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/www/view/common/js/nav.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
var primaryId = null;
function App() {
    loadlocal();

    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
}

function initData(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCourseDetail",
        type:"post",
        async:false,
        data:{
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('banner', result.data, 'bannerTemp');
                viewHtml('courseDetail', result.data, 'courseDetailTemp');
                var courseContent = result.data.introduce;
                var costType = result.data.costType;
                var type = result.data.type;
                var cost = "";
                if(costType == '0'){
                    cost = "免费";
                }else if(costType == '1'){
                    cost =  result.data.cost;
                }
                $("#courseContent").html(courseContent);
                $("#totalCost").text("¥"+cost);

                if(type == '1'){
                    //单节课程
                    viewHtml('chapterContent', result.data.courseSource, 'djchapterTemp');
                }else if(type == '2'){
                    //系列课程
                    findPartInfo(primaryId);
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
function findPartInfo(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCoursePartInfo",
        type:"post",
        async:false,
        data:{
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('chapterContent', result.data, 'chapterTemp');
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
function hoverLi(domId){
    $("#"+domId).removeClass('menuHide').addClass('menuShow');
    $("#"+domId).siblings().removeClass('menuShow').addClass('menuHide');
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);

}

function playSource(sourceId){
   window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId+'&sourceId='+sourceId;
}