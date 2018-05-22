var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var config = {
    server,
    serverfile,
    appid: 'wxcf3d50130f9844f5',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    frontColor: '#1A56A8',
    backgroundColor: "blue",
    loginUrl: `${server}/portal/www/authority.do`,
    bindUrl: `${server}/portal/www/bind.do`,
    checkImageUrl: `${server}/portal/captcha/image.do`,
    uploadUrl: `${server}/portal/www/upload.do`,
    getAppealById: `${server}/portal/www/appealCase/getAppealById.do`,
    insertAppealCase: `${server}/portal/www/appealCase/insertAppealCase.do`,
    getList: `${server}/portal/www/appealCase/getList.do`,
    selectAppealCaseByPrimaryKey: `${server}/portal/www/appealCase/selectAppealCaseByPrimaryKey.do`,
    sendCmccByMobile: `${server}/portal/www/sendCmccByMobile.do`,
    updateAccept: `${server}/portal/www/appealCase/updateAccept.do`,
    updateDetailsOfProgress: `${server}/portal/www/appealCase/updateDetailsOfProgress.do`,
    updateAppealCase: `${server}/portal/www/appealCase/updateAppealCase.do`
};
module.exports = config
