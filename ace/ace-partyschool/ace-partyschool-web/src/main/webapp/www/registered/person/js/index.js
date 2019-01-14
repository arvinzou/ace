var regType = null;
$(function(){

    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                regType = result.data.regType;
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
});

function toMailList(){
    window.location.href = contextPath + '/www/registered/maillist/index.jsp';
}
function toClassFiles(){
    window.location.href = contextPath + '/www/registered/files/clazz/index.jsp';
}
function toConsumption(){
    if(regType == "student"){
        window.location.href = contextPath + '/www/registered/consumption/student.jsp';
    }else{
        window.location.href = contextPath + '/www/registered/consumption/teacher.jsp';
    }
}