var host = "14592619.qcloud.la";
var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var rtmpserver ="rtmp://zx.huacainfo.com/live/";
var config = {
    host,
    rtmpserver,
    websocketurl:'zx.huacainfo.com',
    server,
    serverfile,
    appid: 'wxcf3d50130f9844f5',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    companyId: '0010007',
    frontColor: '#ffffff',
    backgroundColor: "#3C3C3C",
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    uploadUrl: `${server}/jxb/www/jxb/upload.do`,
    getLiveMsgList: `${server}/jxb/www/jxb/getLiveMsgList.do`,
    insertLiveRptSapp: `${server}/jxb/www/jxb/insertLiveRptSapp.do`
};


module.exports = config
