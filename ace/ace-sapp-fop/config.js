var server = "https://mp.cdsgsl.org.cn";
var serverfile = "https://mp.cdsgsl.org.cn/";
var config = {
    server,
    serverfile,
    appid: 'wxaa3c6d0676f4d11a',
    appsecret: 'c275f7ed74795ed580c23a77d97b7457',
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
