var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var config = {
    server,
    serverfile,
  appid: 'wx7e6a369d8fa12b12',
  appsecret: 'eac1cdaebc47d72133d0872a5900f344',
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/society/www/captcha/image.do`,
    uploadUrl: `${server}/portal/www/upload.do`,
};
module.exports = config