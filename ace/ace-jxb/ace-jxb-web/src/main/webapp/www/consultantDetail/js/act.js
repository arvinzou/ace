function App() {

    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    findDetail(primaryId);
}

function findDetail(id){
    $.ajax({
        url: contextPath+ "/www/consult/getCounselorDetail",
        type:"post",
        async:false,
        data:{
            counselorId: id
        },
        success:function(result){
            if(result.status == 0) {
               console.log(result);
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