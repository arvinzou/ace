var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var rtmpserver ="rtmp://zx.huacainfo.com/live/";
var hlsserver ="http://139.224.0.227:9999/hls/";
var websocketurl= 'zx.huacainfo.com';
var config = {
    rtmpserver,
    websocketurl,
    server,
    serverfile,
    hlsserver,
    appid: 'wx70ba7c5dca85e4da',
    appsecret: '96214b4a01153935995cc027ddf26d75',
    companyId: '0010007',
    frontColor: '#ffffff',
    backgroundColor: "#3C3C3C",
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    uploadUrl: `${server}/jxb/www/jxb/upload.do`,
    getLiveMsgList: `${server}/jxb/www/jxb/getLiveMsgList.do`,
    insertLiveRptSapp: `${server}/jxb/www/jxb/insertLiveRptSapp.do`,
    getLiveRptList: `${server}/jxb/www/jxb/getLiveRptList.do`,
    insertLiveSapp: `${server}/jxb/www/jxb/insertLiveSapp.do`,
    insertCourseSapp: `${server}/jxb/www/jxb/insertCourseSapp.do`
};
module.exports = config
