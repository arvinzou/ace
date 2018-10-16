var server = "http://127.0.0.1";
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
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/society/www/captcha/image.do`,
    uploadUrl: `${server}/portal/www/upload.do`
};
module.exports = config

