window.onload = function() {
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
                var member = document.getElementById('memberTemp').innerHTML;
                var html = juicer(member, {
                    data: result.data.memberList
                });
                $(".main").append(html);
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
