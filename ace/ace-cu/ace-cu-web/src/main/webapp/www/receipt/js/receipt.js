var orderId = null;
window.onload = function(){
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "ORDERID"){
                orderId = value;
            }
        }
        console.log("orderId================================="+orderId);
    }
    orderId = '137f6a8d61164a388338460e19623124';
    if(orderId != null){
        $.ajax({
            url: "/cu/www/report/getShareData",
            type:"post",
            async:false,
            data:{"orderId": orderId},
            success:function(result){
                if(result.status == 0) {
                    renderPage('share_box', result.data, 'share-tpl');
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }
    getConfig();
}

function getConfig(){
    $.ajax({
        url: "/portal/www/wx/jsapi/getConfig",
        type:"post",
        async:false,
        data:{"sysId": "hcwy", "url": window.location.href},
        success:function(result){
            if(result.status == 0) {
                console.log("**************************"+result);
                zdyShare(result.data);
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function zdyShare(data){
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，
        timestamp: data.timestamp, // 必填，
        nonceStr: data.nonceStr, // 必填，
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: ['updateAppMessageShareData','updateTimelineShareData'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {   //需在用户可能点击分享按钮前就先调用
        wx.updateAppMessageShareData({
            title: '携手慈善，传递爱心', // 分享标题
            desc: '我已经捐款了，希望一起大家传递爱心哦~', // 分享描述
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'https://zx.huacainfo.com/cu/www/share/img/logo.jpg' // 分享图标
        }, function(res) {
            //这里是回调函数
            alert("分享成功！");
        });
    });

    wx.ready(function () {      //需在用户可能点击分享按钮前就先调用
        wx.updateTimelineShareData({
            title: '常德慈善总会，慈善一日捐', // 分享标题
            desc: '我已经捐款了，希望一起大家传递爱心哦~', // 分享描述
            link: window.location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'https://zx.huacainfo.com/cu/www/share/img/logo.jpg' // 分享图标
        }, function(res) {
            //这里是回调函数
            alert("分享成功！");
        });
    });
}
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}