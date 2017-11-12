$(function(){
    $('.header-title-menu span:first').css({'background-color':'black','color':'white'});
    $('.header-title-menu').on('click','span',showperson);//点击选项卡
    $('.header-title-menu').on('mouseover','span',hoverspan);//点击选项卡
});

function hoverspan(){
    console.log('鼠标');
    $('.header-title-menu span').css({'background-color':'white','color':'black'});
    $(this).css({'background-color':'black','color':'white'});
}

/**
 * 点击显示不同的选项
 */
function showperson(){
    console.log('进入');
    var index=$(this).index();
    console.log(index);
    if(index==0){
        showxinxi();//基本信息
    }else if(index==1){
        showleixing();//人士类型
    }else if(index==2){
        showzhiwu();//人士职务
    }else if(index==3){
        showjiangcheng();//社会奖惩
    }
}

/**
 * 展示基本信息
 */
function showxinxi(){
    console.log('显示信息');
    $('#dialog-message-view .window-xinxi').hide();
    $('#dialog-message-view .xinxi-show').show();
}

/**
 * 展示人士类型
 */
function showleixing(){

}

/**
 * 展示职务
 */
function showzhiwu(){
}

/**
 * 展示奖惩
 */
function showjiangcheng(){
    var flag=$('#jiangcheng-show').html();
    if(!flag){
        return;
    }
    var url='personage/getJcByUserId.do';
    var data={'userId':userId};
}