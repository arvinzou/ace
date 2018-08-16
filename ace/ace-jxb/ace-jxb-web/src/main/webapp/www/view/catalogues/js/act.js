function App() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
}

function initData(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCoursePartInfo",
        type:"post",
        async:false,
        data:{
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('chapterList', result.data, 'chapterTemp');
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
