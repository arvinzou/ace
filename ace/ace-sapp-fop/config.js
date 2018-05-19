var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var config = {
    server,
    serverfile,
    appid: 'wxcf3d50130f9844f5',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    frontColor: '#fff',
    backgroundColor: "blue",
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/portal/captcha/image.do`,
    uploadUrl: `${server}/portal/www/upload.do`,
    getAppealById: `${server}/portal/www/appealCase/getAppealById.do`,
    insertAppealCase: `${server}/portal/www/appealCase/insertAppealCase.do`

};
module.exports = config
