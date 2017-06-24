/**
 * 小程序配置文件
 */
// 此处主机域名是腾讯云解决方案分配的域名

// 小程序后台服务解决方案：https://www.qcloud.com/solution/la
var host = "14592619.qcloud.la";
var server = "https://zx.huacainfo.com"
var serverfile = "https://zx.huacainfo.com/"
var config = {
    // 下面的地址配合云端 Server 工作
    host,
    server,
    serverfile,
    // 登录地址，用于建立会话
    loginUrl: `${server}/portal/www/authority.do`,
    selectOrganizationList: `${server}/uf/www/selectOrganizationList.do`,
    selectOrganizationListMap: `${server}/uf/www/selectOrganizationListMap.do`,
    selectOrganization: `${server}/uf/www/selectOrganization.do`,
    selectAreaCodeList: `${server}/uf/www/selectAreaCodeList.do`,
    insertFeedback: `${server}/uf/www/insertFeedback.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    appid: 'wxa09a5be5fd228680',
    appsecret: 'd520d29f8c26c7e3885d80b1812a8d91',
    areaCode:'430702'
};
module.exports = config
