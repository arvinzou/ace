function App() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
};

function initData(id){
    $.ajax({
        url: contextPath+ "/www/studio/getStudioDetail",
        type:"post",
        async:false,
        data:{
            studioId: id
        },
        success:function(result){
            if(result.status == 0) {
               /* var roomTemp = document.getElementById('roomTemp').innerHTML;
                var html = juicer(roomTemp, {
                    data: result.data
                });
                $("#itemList").append(html);*/
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