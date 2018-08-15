function App() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
}

function initData(id){
    $.ajax({
        url: contextPath+ "/www/course/findCourseSource",
        type:"post",
        async:false,
        data:{
            srcId: id
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('sourceDetail', result.data, 'sourceDetailTemp');
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