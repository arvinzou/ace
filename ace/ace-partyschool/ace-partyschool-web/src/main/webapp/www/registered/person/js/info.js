var account = 0;
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
                console.log("===================================="+result);
                renderPage('userInfo', result.data, 'info-tpl');
                renderPage('option', result.data, 'option-tpl');
                if(result.data.regType == 'student'){
                    account = result.data.student.mobile;
                }else{
                    account = result.data.teacher.mobile;
                }
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

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function exit(){
    $.ajax({
        url: contextPath+ "/www/sign/logout",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0) {
               window.location.href = contextPath + '/www/login/index.jsp';
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

function editPassword(){
    window.location.href = contextPath + '/www/reset/index.jsp';
}

function bindWx(){
    var o={};
    o.account=account;
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));
    $("#bindForm").submit();
}