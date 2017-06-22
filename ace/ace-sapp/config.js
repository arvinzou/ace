/**
 * 小程序配置文件
 */

// 此处主机域名是腾讯云解决方案分配的域名
// 小程序后台服务解决方案：https://www.qcloud.com/solution/la

var host = "14592619.qcloud.la";
var server = "https://demo.huacainfo.com"
var serverfile = "http://zx.huacainfo.com/"
var config = {
    // 下面的地址配合云端 Server 工作
    host,
    server,
    serverfile,
    // 登录地址，用于建立会话
    loginUrl: `https://${host}/login`,
    selectOrganizationList: `${server}/uf/www/selectOrganizationList.do`,
    selectOrganizationListMap: `${server}/uf/www/selectOrganizationListMap.do`,
    selectOrganization: `${server}/uf/www/selectOrganization.do`
};

module.exports = config
