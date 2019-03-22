var server = "https://api.huacainfo.com";
var serverfile = "https://api.huacainfo.com/";
var config = {
    server,
    serverfile,
    appid: 'wx70ba7c5dca85e4da',
    appsecret: '96214b4a01153935995cc027ddf26d75',
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/society/www/captcha/image.do`,
    uploadUrl: `${server}/portal/www/upload.do`,
    companyId:'0002'
};
module.exports = config
