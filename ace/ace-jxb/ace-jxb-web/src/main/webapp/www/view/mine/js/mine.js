window.onload = function(){
    initData();
    $('.banner').on('click','.getmoney',doWithdraw);
    $('#mine').on('click','.banner',withdrawHistroy);
};


/**
 * 取款记录*/
function withdrawHistroy() {
    window.location.href = contextPath + '/www/view/mine/earnings.jsp';
}




var consulorId = "";
var signStatus = "1";     //已经签到状态为2
var signDays = 0;
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
                if(result.status == 0 && result.data.memberType == '1') {
                    consulorId = result.data.counselor.id;
                    var amount=result.data.counselor.income;
                    result.data.counselor.income=amount?amount:0.00;
                    amount=result.data.counselor.amount;
                    result.data.counselor.amount=amount?amount:0.00;
                    var myInfo = document.getElementById('myInfo').innerHTML;
                    var html = juicer(myInfo, {
                        data: result.data
                    });
                    $("#mine").append(html);
                    var signInfo = document.getElementById('signInfo').innerHTML;
                    var signText = juicer(signInfo, {
                        signCount: result.data.signInfo.signCount
                    });
                    signDays = result.data.signInfo.signCount;
                    $(".sign_info").html(signText);

                    var onOrOff = document.getElementById('onOffTemp').innerHTML;
                    var onOrOffHtml = juicer(onOrOff, {
                        counselor: result.data.counselor
                    });
                    $("#onOrOff").append(onOrOffHtml);
                }else if(result.status == 0 && result.data.memberType == '2'){
                    window.location.href = contextPath + '/www/view/member/index.jsp'
                }
                try{
                    signStatus = result.data.signInfo.status;
                }catch(e){}
                if(signStatus == '2'){
                    $("#signIn").text("已签到");
                    $("#signIn").removeClass('sign_btn').addClass('signed');
                }
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

}

/**
 * 开始提现*/

function doWithdraw(){
    window.location.href = contextPath + '/www/view/mine/withdraw.jsp';
    return false;
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
                    $("#signIn").text("已签到");
                    $("#signIn").removeClass('sign_btn').addClass('signed');
                    var days = parseInt(signDays)+1;
                    $(".sign_info").html("本月已签到"+days+"天");
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
        //在线状态 0-离线 1-在线
        if(onoffline('0')){
            $("#offline").attr('src',contextPath+ '/www/view/mine/img/switch_off.png');
        }
    }else{
        if(onoffline('1')) {
            $("#offline").attr('src', contextPath + '/www/view/mine/img/switch_on.png');
        }
    }
}

function loadMyInfo(id){
    window.location.href = contextPath + '/www/view/counselor/index.jsp?id='+id;
}

function showMycount(){
    window.location.href = contextPath + '/www/view/mine/mycount.jsp?id='+consulorId;
}

function onoffline(state){
    var flag = false;
    $.ajax({
        url: contextPath+"/www/consult/onOff",
        type:"post",
        async:false,
        data:{counselorId: consulorId, onlineStatus: state},
        success:function(result){
            if(result.status == 0) {
                flag = true;
            }else {
                alert(result.errorMessage);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    return flag;
}

function showMyOrder(){
    window.location.href = contextPath + '/www/view/belongCounselorOrder/index.jsp';
}