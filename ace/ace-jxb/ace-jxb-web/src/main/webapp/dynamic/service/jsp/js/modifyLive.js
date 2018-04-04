/*点击发布直播*/
function releaseDo() {
    if(checkWordNumber()){
        return;
    }
    console.log('releaseDo');
    var name=$('.name').val().trim();
    var startTime=$('.startTime').val().trim();
    var endTime=$('.endTime').val().trim();
    if(endTime){
        endTime+=':00';
    }
    var id=$('#htmlLoad').data('jxbId');
    var remark=$('.remark').val().trim();
    var content=$('.content').val().trim();
    var type= $(".formContenRight").find("#type").val();
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
    var keyVal={
        'id':id,
        'name':name,
        'startTime':startTime+':00',
        'endTime':endTime,
        'remark':remark,
        'content':content,
        'addr':addr,
        'rtmpUrl':rtmpUrl,
        'mp4Url':mp4Url,
        'category':category,
        'imageSrc':imageSrc,
        'nop':nop,
        'pop':pop,
        'status':status,
        'type':type
    }
    var url="/jxb/jxb/updateLiveSelective.do";
    var data={
        'jsons':JSON.stringify(keyVal)
    };
    $.post(url,data, function (result) {
        console.log(result);
        if(result.status==0){
            alert("修改直播成功");
            hideTableDo();
            loadLiveList();
        }else{
            promptDo(result.errorMessage);
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