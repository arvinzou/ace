
window.onload = function(){
    initInfo();
    $('.rules').on('click','.text',showModal);
    $('.my_model').on('click','.rules_text',stopPropagation);
    $('.my_model').click(hideModal);
    $('.form').on('keyup',".applyAmount",vMoney);
    $('.form').on('keydown',".applyAmount",gMoney);
    $('.money_card').on('click',".action_btn",actionMoney);
    $('.rst_model').on('click','.backBtn',backWeb);
    $('.rst_model').on('click','.withdrawHistroy',withdrawHistroy);
    $('.realname').on('blur','.realName',vRealName);
    $('.realname').on('focus','.realName',initRealName);
};

var imoney='';
var amount;

function initRealName() {
    $('.realname .realName').css('color','#A0A7B6');
}

/**
 * 取款记录*/
function withdrawHistroy() {
    window.location.href = contextPath + '/www/view/mine/earnings.jsp';
}

/**
 * 验证名字*/
function vRealName() {
    var str=$('.realname .realName').val();
    var han = /^[\u4e00-\u9fa5]+$/;
    if (!han.test(str)) {
        $(".form .applyAmount").prop("readonly",true);
        $('.realname .realName').css('color','#FF3366');
    }else{
        $(".form .applyAmount").prop("readonly",false);
        $('.realname .realName').css('color','#A0A7B6');
    };
}


/**返回*/
function  backWeb() {
    window.history.back();
}


/*初始化信息*/
function initInfo() {
    // var day=new Date().getDay();
    // if(day>7){
    //     $('.rst_model1').show();
    //     return;
    // }
    //当月有没有提现
    // $.getJSON();
    var url=contextPath+"/www/reg/findInfo";
    var data={};
    $.getJSON(url,data,function (rst) {
        if(rst.status == 0 && rst.data.memberType == '1'){
            amount=rst.data.counselor.amount;
            amount=amount?amount:0.00;
            $('.money_card .info.stutas').html("可提现金额"+amount+"元");
            // if(amount<50){
            //     $('.rst_model1').show();
            // }
        }
    })
}

function showModal() {
    $('.my_model').addClass('my_model_a');
}

function hideModal() {
    $('.my_model').removeClass('my_model_a');
}

function stopPropagation() {
    return false;
}

function vMoney() {
    var that= $('.form .applyAmount');
    var money=that.val();
    var regu = /^0\.\d+$|^0\.?$|^[1-9]\d*$|^[1-9]\d*(\.\d+)?$/;
    if(regu.test(money)){
        var index=money.indexOf(".");
        if(index!=-1){
            that.val(money.substring(0,index+3));
        }
        if(money>49.99&&money<5000.01&&money<(amount+0.01)){
            $('.money_card .clickBtn').addClass('action_btn');
            $('.money_card .info').hide();
            $('.money_card .stutas').show();
        }else{
            $('.money_card .clickBtn').removeClass('action_btn');
            $('.money_card .info').hide();
            $('.money_card .stutas1').show();
        }
    }else{
        if(money==''){
            $('.money_card .info').hide();
            $('.money_card .stutas').show();
            that.val('');
        }else{
            that.val(imoney);
        }

    }
}


function gMoney() {
    var that= $('.form .applyAmount');
    imoney=that.val();
}


function actionMoney() {
    var applyAmount= $('.form .applyAmount').val();
    var realName=$('.realname .realName').val();
    if(!(applyAmount&&realName)){
        alert("姓名和金额不能为空");
        return;
    }
    var url=contextPath+"/www/counselor/withdraw";
    // var url="http://localhost/jxb/www/counselor/withdraw";
    var data={applyAmount:applyAmount,realName:realName,withdrawType:1};
    $.post(url,data,function (rst) {
        if(rst.status==0){
            $('.rst_model').show();
            return;
        }
        alert(rst.info);
    })
}