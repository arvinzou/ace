
function releaseDo() {
    if(checkWordNumber()){
        return;
    }
    var flag=false;
    var imgs = [];
    var datas = {};
    var content=$('.formRow .content').val();
    var rpt = {
        'id':$('#htmlLoad').data('id'),
        'mediaType': mediaType,
        'content': content,
        'rid': $('#htmlLoad').data('rid'),
        'uid': userProp.openId,
        'mediaContent': '',
        'createTime': $('.formRow .createTime').val(),
    };
    if (2 == mediaType) {
        $(".reportImageUploadList li").each(function () {
            var e = $(this).data("fileurl");
            var r = $(this).data("width");
            var i = $(this).data("height");
            if (e) {
                flag=true;
                imgs.push({
                    url: e,
                    w: r,
                    h: i
                })
            }
        });
        if(!(content||flag)){
            promptDo('内容和图片不能都为空');
            return;
        }

    } else {
        var e = $(".reportVideoUploadList").data("fileurl");
        if(!e){
            promptDo('未上传视频');
            return;
        }
        rpt.mediaContent = e;
    }
    datas.imgs = imgs;
    datas.rpt = rpt;
    var url = '/live/liveRpt/updateLiveRpt';
    $.post(url, {jsons: JSON.stringify(datas)}, function (result) {
        if(0==result.status){
            $('#htmlLoad').empty();
            loadReportList();
            return;
        }
        promptDo(result.errorMessage);
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