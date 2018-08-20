function App() {
    console.log("=============================App Start==============================");
    initData();
};
var consulorId = "";
var signStatus = "1";     //已经签到状态为2
function initData(){
    $.ajax({
        url: contextPath+"/www/reg/findInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.data == 'unregister'){   //跳转到注册页面
                window.location.href = contextPath + '/www/view/regist/regist.html';
            }else{
                try{
                    signStatus = result.data.signInfo.status;
                }catch(e){}
                if(signStatus == '2'){
                    $("#signIn").text("已签到");
                    $("#signIn").removeClass('sign_btn').addClass('signed');
                }
                if(result.status == 0 && result.data.memberType == '1') {
                    consulorId = result.data.counselor.id;
                    var myInfo = document.getElementById('myInfo').innerHTML;
                    var html = juicer(myInfo, {
                        data: result.data
                    });
                    $("#mine").append(html);
                    var signInfo = document.getElementById('signInfo').innerHTML;
                    var signText = juicer(signInfo, {
                        signCount: result.data.signInfo.signCount
                    });
                    $(".sign_info").html(signText);
                }else if(result.status == 0 && result.data.memberType == '2'){
                    window.location.href = contextPath + '/www/view/member/index.jsp'

                }
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

}

/**
 * 签到
 */
function sign(){
    if(signStatus != '2'){
        $.ajax({
            url: contextPath+"/www/reg/signIn",
            type:"post",
            async:false,
            data:{userId: consulorId},
            success:function(result){
                if(result.status == 0) {
                    alert("签到成功！");
                    window.location.reload();
                }else {
                    alert(result.errorMessage);
                    return;
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }
}

function myStudio(){
    window.location.href = contextPath+'/www/view/workroom/index.jsp?id='+consulorId;
}


function switchOffline(){
    var attrContent = $("#offline").attr('src');
    if(attrContent.indexOf('on') > 0){
        $("#offline").attr('src',contextPath+ '/www/view/mine/img/switch_off.png');
    }else{
        $("#offline").attr('src',contextPath+ '/www/view/mine/img/switch_on.png');
    }
}