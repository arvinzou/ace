
window.onload = function(){
    $('.rules').on('click','.text',showModal);
    $('.my_model').on('click','.rules_text',stopPropagation);
    $('.my_model').click(hideModal);
    $('.form').on('keyup',"input",vMoney);
    $('.form').on('keydown',"input",gMoney);
    $('.money_card').on('click',".action_btn",actionMoney);
};

var imoney='';

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
    var that= $('.form input');
    var money=that.val();
    var regu = /^0\.\d+$|^0\.?$|^[1-9]\d*$|^[1-9]\d*(\.\d+)?$/;
    if(regu.test(money)){
        var index=money.indexOf(".");
        if(index!=-1){
            that.val(money.substring(0,index+3));
        }
        if(money>49.99&&money<5000.01){
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
    var that= $('.form input');
    imoney=that.val();
}


function actionMoney() {
    var that= $('.form input');
    var money=that.val();
    console.log(money);
}