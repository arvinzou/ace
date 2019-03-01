var server = 'http://192.168.2.100';
$(function(){
    findList();
});

function findList(){
    $.ajax({
        url: server+ "/api/www/api/findBorrowList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            lCardNo: 'ZX112011'
        },
        success:function(result){
            if(result.status == 0) {
               console.log(result);
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}