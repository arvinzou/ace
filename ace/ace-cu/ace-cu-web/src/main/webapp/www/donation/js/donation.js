var recordList = [];
var type = "";
var status = null;
var primaryId = null;
window.onload = function(){
	var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "type"){
                type = value;
                $scope.type = type;
            }
            if(name == "projectId"){
                primaryId = value;
            }
        }
    }

    /**
     * 项目详情
     */
    $.ajax({
        url: "/cu/www/project/findDetail",
        type:"post",
        async:false,
        data:{projectId: primaryId},
        success:function(result){
            if(result.status == 0) {
                $("#projectDetail").html(result.data.description);
                if(result.data.carousel == '' || result.data.carousel == undefined || result.data.carousel=='[]'){
                    var htmlTemp =   '<div class="swiper-slide">\n' +
                        '<img class="slideImg" src="'+result.data.coverUrl+'" style="width: 100%;height: 100%;">\n' +
                        '</div>\n' ;
                }else{
                    var htmlTemp = '<div class="swiper-slide">\n' +
                        '<video src="http://zx.huacainfo.com/group1/M00/00/3A/i-AA41tzpyaAN4zfABnwZ2Vdppo235.mp4?filename=5a447a704d316aaea58a4a313c16cc40.mp4" poster="img/cover.png" style="width: 100%;height: 100%;object-fit:fill"  autoplay="autoplay" x-webkit-airplay="true" webkit-playsinline="true" airplay="allow" playsinline="allow"  x5-video-player-type="h5" preload="auto" controls>\n' +
                        '您的设备不支持video。\n' +
                        '</video>\n' +
                        '</div>\n' +
                        '<div class="swiper-slide">\n' +
                        '<img class="slideImg" src="'+result.data.carousel[1].resUrl+'" style="width: 100%;height: 100%;">\n' +
                        '</div>\n' +
                        '<div class="swiper-slide">\n' +
                        '<img class="slideImg" src="'+result.data.carousel[2].resUrl+'" style="width: 100%;height: 100%;">\n' +
                        '</div>\n' +
                        '<div class="swiper-slide">\n' +
                        '<img class="slideImg" src="'+result.data.carousel[3].resUrl+'" style="width: 100%;height: 100%;">\n' +
                        '</div>';
                }

                $("#swiperBox").html(htmlTemp);

                 var swiper = new Swiper('.swiper-container', {
                     navigation: {
                         nextEl: '.swiper-button-next',
                         prevEl: '.swiper-button-prev',
                     },
                 });
                status = result.data.status;
                renderPage('projectInfo', result.data, 'project-tpl');
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    
    /**
     * 使用记录列表
     */
    var flag = false;
    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                flag = true;
                renderPage('record', result.data, 'record-tpl');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });


   /**
     * 捐赠列表
     */
    $.ajax({
        url: "/cu/www/project/findDonateList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                renderPage('donateList',result.data.rows, 'donate-tpl');
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
};

function donate(){
    window.location.href = '/cu/www/view/order/order.html?projectId='+primaryId;
}

function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}

function troggle(obj){
    if($(obj).attr("name") == "down"){   //down展开，up收起
        $(obj).parent().siblings(".project_record_info").removeClass("troggle");
        $(obj).html("<span class=\"opt\">收起</span><img src=\"img/up.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "up");
    }else{
        $(obj).parent().siblings(".project_record_info").addClass("troggle");
        $(obj).html("<span class=\"opt\">展开</span><img  src=\"img/down.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "down");
    }
}
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}
