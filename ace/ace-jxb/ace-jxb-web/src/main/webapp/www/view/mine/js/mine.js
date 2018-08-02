function App() {
    console.log("=============================App Start==============================");
    initData();
};
var consulorId = "";
function initData(){
    $.ajax({
        url: contextPath+"/www/reg/findInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
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
                alert("普通会员的个人中心待建设中！");

            } else {
                if(result.data == 'unregister'){   //跳转到注册页面
                    window.location.href = '/jxb/www/view/regist/regist.html';
                }
                return;
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
    $.ajax({
        url: contextPath+"/www/counselor/signIn",
        type:"post",
        async:false,
        data:{counselorId: consulorId},
        success:function(result){
            if(result.status == 0) {
                alert("签到成功！");
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