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
                var roombaseTemp = document.getElementById('roomInfoTemp').innerHTML;
                var html = juicer(roombaseTemp, {
                    roombase: result.data
                });
                $(".room_info").append(html);

                var contentTemp = document.getElementById('roomContentTemp').innerHTML;
                var content = juicer(contentTemp, {
                    roomContent: result.data
                });
                $("#roomContent").append(content);
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
