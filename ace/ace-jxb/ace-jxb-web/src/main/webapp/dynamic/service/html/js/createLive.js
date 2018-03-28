/*点击发布直播*/
function releaseDo() {
    if(checkWordNumber()){
        return;
    }
    var name=$('.name').val();
    var areaCode=userProp.areaCode;
    var startTime=$('.startTime').val();
    var endTime=$('.endTime').val();
    if(endTime){
        endTime+=':00';
    }
    var remark=$('.remark').val().trim();
    var content=$('.content').val().trim();
    var deptId=userProp.corpId;
    var addr=$('.addr').val().trim();
    var rtmpUrl=$('.rtmpUrl').val().trim();
    var mp4Url=$('.mp4Url').val().trim();
    var category=$("input[type='radio']:checked").val().trim();
    var nop=$('.nop').val().trim();
    var pop=$('.pop').val().trim();
    var imageSrc=$('.pictureContainer').data('imgSrc');
    if(!(name&&imageSrc&&startTime&&remark&&content&&category&&addr&&rtmpUrl&&mp4Url&&nop&&pop)){
        promptDo('带“ * ”为必填项');
        return;
    }
    promptDo('');
    var keyVal={
        'name':name,
        'areaCode':areaCode,
        'startTime':startTime+':00',
        'endTime':endTime,
        'remark':remark,
        'content':content,
        'deptId':deptId,
        'addr':addr,
        'rtmpUrl':rtmpUrl,
        'mp4Url':mp4Url,
        'category':category,
        'imageSrc':imageSrc,
        'nop':nop,
        'pop':pop
    }
    var url="/jxb/jxb/insertLive.do";
    var data={
        'jsons':JSON.stringify(keyVal)
    };
    $.post(url,data, function (result) {
        console.log(result);
        if(result.status==0){
            alert("创建直播成功");
            $(".form-control").val('');

            showUploadText();
        }else{
            $('.prompt').text(result.errorMessage);
        }

    });
}


function checkWordNumber() {
    var flag=false;
    $('#htmlLoad .formRow span').each(function(){
        if('rgb(255, 0, 0)'==$(this).css('color')){
            promptDo('超过限制字数');
            flag=true;
        }
    });
    return flag;
}
