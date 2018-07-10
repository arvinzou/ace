$(function () {
    var url = '/portal/www/wx/jsapi/getConfig';
    var data = {
        url: "http://zx.huacainfo.com/fundtown/www/map/map.html",
        sysId: 'fundtown'
    }
    $.getJSON(url, data, function (result) {
        wx.config({
            debug: true,
            appId: result.data.appId,
            timestamp: result.data.timestamp,
            nonceStr: result.data.noncestr,
            signature: result.data.signature,
            jsApiList: ["openLocation"]
        })
    });
})

wx.ready(function () {
    wxReady = !0;
});

wx.error(function (res) {
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    console.log("wx_config_error:" + res);
});

