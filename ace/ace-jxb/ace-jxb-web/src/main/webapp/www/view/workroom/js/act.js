function App() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
};

function initData(primaryId){

    $.ajax({
        url: contextPath+ "/www/studio/getStudioList",
        type:"post",
        async:false,
        data:{
            counselorId: primaryId,
            status : '1'
        },
        success:function(result){
            if(result.status == 0) {
                var myroomTemp = document.getElementById('myroomTemp').innerHTML;
                var html = juicer(myroomTemp, {
                    data: result.data
                });
                $("#itemList").append(html);
                var joinroomTemp = document.getElementById('joinroomTemp').innerHTML;
                var joinhtml = juicer(joinroomTemp, {
                    joinData: result.data.join
                });
                $("#itemList").append(joinhtml);
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

function showDetail(id){
    window.location.href = contextPath + '/www/view/myworkroom/index.jsp?id='+id;
}

function showjoinDetail(id){
    window.location.href = contextPath + '/www/view/joinedRoom/index.jsp?id='+id;
}

function invate(id){
    window.location.href = contextPath + '/www/view/invate/index.jsp?id='+id;
}