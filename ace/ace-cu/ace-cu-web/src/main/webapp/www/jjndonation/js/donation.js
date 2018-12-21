var recordList = [];
var type = "";
var status = null;
var primaryId = null;
var projectTitle = "";
var coverUrl = "https://cswx.changde.gov.cn/cu/www/share/img/logo.png";
var content = "";
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
                status = result.data.status;
                projectTitle = result.data.projectName;
                coverUrl = result.data.coverUrl;
                content = result.data.title;
                console.log("======================================================"+coverUrl);
                result.data.endDate = result.data.endDate.substring(0,10);
                renderPage('projectInfo', result.data, 'project-tpl');
                renderPage('projectDetail', result.data, 'detail-tpl');
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
                renderPage('donateList', result.data.rows, 'donate-tpl');
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

    getConfig(projectTitle, coverUrl, content);
};

function donate(){
    window.location.href = '/cu/www/view/order/order.jsp?projectId='+primaryId;
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

function getConfig(projectTitle, coverUrl, content){
    $.ajax({
        url: "/portal/www/wx/jsapi/getConfig",
        type:"post",
        async:false,
        data:{"sysId": "cu", "url": window.location.href},
        success:function(result){
            if(result.status == 0) {
                console.log("**************************"+result);
                zdyShare(result.data, projectTitle, coverUrl, content);
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function zdyShare(data,projectTitle, coverUrl, content){
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，
        timestamp: data.timestamp, // 必填，
        nonceStr: data.nonceStr, // 必填，
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','updateAppMessageShareData','updateTimelineShareData'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        wx.onMenuShareTimeline({
            title: projectTitle, // 分享标题
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: coverUrl, // 分享图标
            success: function () {
                // 用户点击了分享后执行的回调函数
                alert("分享成功！");
            }
        });
    });

    wx.ready(function () {
        wx.onMenuShareAppMessage({
            title: projectTitle, // 分享标题
            desc: content, // 分享描述
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: coverUrl, // 分享图标
            type: '', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户点击了分享后执行的回调函数
                alert("分享成功！");
            }
        });
    });

    wx.ready(function () {   //需在用户可能点击分享按钮前就先调用
        wx.updateAppMessageShareData({
            title: projectTitle, // 分享标题
            desc: content, // 分享描述
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: coverUrl // 分享图标
        }, function(res) {
            //这里是回调函数
            alert("分享成功！");
        });
    });

    wx.ready(function () {      //需在用户可能点击分享按钮前就先调用
        wx.updateTimelineShareData({
            title: projectTitle, // 分享标题
            desc: content, // 分享描述
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: coverUrl // 分享图标
        }, function(res) {
            //这里是回调函数
            alert("分享成功！");
        });
    });
}
