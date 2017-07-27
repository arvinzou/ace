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
    selectOrganizationCategoryList: `${server}/uf/www/selectOrganizationCategoryList.do`,
    selectOrganizationByCategory: `${server}/uf/www/selectOrganizationByCategory.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    appid: 'wxa09a5be5fd228680',
    appsecret: 'd520d29f8c26c7e3885d80b1812a8d91',
    areaCode:'430702',
    uploadUrl: `${server}/portal/www/uploadFile.do`,
    regUrl: `${server}/portal/www/reg.do`,
    selectDeptList: `${server}/uf/www/selectDeptList.do`,
    selectDeptListMap: `${server}/uf/www/selectDeptListMap.do`,
    selectPersonAgetListMap: `${server}/uf/www/selectPersonageListMap.do`,
    selectDept: `${server}/uf/www/selectDept.do`,
    selectDeptCategoryList: `${server}/uf/www/selectDeptCategoryList.do`,
    selectDeptByCategory: `${server}/uf/www/selectDeptByCategory.do`,
    selectPersonage: `${server}/uf/www/selectPersonage.do`,
    selectPersonageList: `${server}/uf/www/selectPersonageList.do`,
    selectDeptByCategory: `${server}/uf/www/selectDeptByCategory.do`,
    selectActivityPageList: `${server}/uf/www/selectActivityPageList.do`,
    selectPhotoListById: `${server}/uf/www/selectPhotoListById.do`,
    selectActivityById: `${server}/uf/www/selectActivityById.do`,
    selectUserListByActivityId: `${server}/uf/www/selectUserListByActivityId.do`,
    selectCommentListByActivityId: `${server}/uf/www/selectCommentListByActivityId.do`,
    insertActivityComment: `${server}/uf/www/insertActivityComment.do`

};
module.exports = config
