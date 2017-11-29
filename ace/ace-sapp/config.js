/**
 * 小程序配置文件
 */

// 此处主机域名是腾讯云解决方案分配的域名
// 小程序后台服务解决方案：https://www.qcloud.com/solution/la
var host = "14592619.qcloud.la";

var server = "http://127.0.0.1";
var serverfile = "http://127.0.0.1";
var config = {
    // 下面的地址配合云端 Server 工作
    host,
    server,
    serverfile,
    // 登录地址，用于建立会话
    //手机顶部文字显示颜色
    frontColor: '#ffffff',
    //手机顶部背景颜色
    backgroundColor: "#3C3C3C",
    loginUrl: `${server}/portal/www/authority.do`,
    saveFormId: `${server}/portal/www/saveFormId.do`,
    selectOrganizationList: `${server}/uf/www/selectOrganizationList.do`,
    selectOrganizationListMap: `${server}/uf/www/selectOrganizationListMap.do`,
    selectOrganization: `${server}/uf/www/selectOrganization.do`,
    selectAreaCodeList: `${server}/uf/www/selectAreaCodeList.do`,
    insertFeedback: `${server}/uf/www/insertFeedback.do`,
    selectOrganizationCategoryList: `${server}/uf/www/selectOrganizationCategoryList.do`,
    selectOrganizationByCategory: `${server}/uf/www/selectOrganizationByCategory.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    appid: 'wxa09a5be5fd228680',
    //appid:'wx6b6434c7e0e627f5',
    appsecret: 'd520d29f8c26c7e3885d80b1812a8d91',
    //appsecret: '9f202bd7483d888e220d7ee3cce40d6e',
    areaCode:'430702',
    uploadUrl: `${server}/portal/www/uploadFile.do`,
    regUrl: `${server}/portal/www/reg.do`,
    whiteListUrl: `${server}/portal/www/updateForExperienceUser.do`,
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
    insertActivityComment: `${server}/uf/www/insertActivityComment.do`,
    updateActivity: `${server}/uf/www/updateActivity.do`,
    getPersonageTreeList: `${server}/uf/www/getPersonageTreeList.do`,
    insertTaskCmcc: `${server}/uf/www/insertTaskCmcc.do`,
    findTaskCmccList: `${server}/uf/www/findTaskCmccList.do`,
    selectTaskCmccById: `${server}/uf/www/selectTaskCmccById.do`,
    selectFreeGroupTree: `${server}/uf/www/selectFreeGroupTree.do`,
    selectPersonageTree: `${server}/uf/www/selectPersonageTree.do`,
    sendCmccByMobile: `${server}/uf/www/sendCmccByMobile.do`,
    // 获取关于统战文件路径。
    findFilesList:`${server}/uf/www/findFilesList.do`,
    selectFilesById: `${server}/uf/www/selectContentByPrimaryKey.do`,
    //获取关于同心工程路径。
    findConcentricList: `${server}/uf/www/findTongXinList.do`,
    selectConcentricById: `${server}/uf/www/selectTongXinByPrimaryKey.do`,
    // 获取关于培训教育路径
    findEducationList: `${server}/uf/www/findPeiXunList.do`,
    selectEducationById: `${server}/uf/www/selectPeiXunByPrimaryKey.do`,
    //获取关于精准扶贫路径
    findPovertyAlleviationList: `${server}/uf/www/findFuPinList.do`,
    selectPovertyAlleviationById: `${server}/uf/www/selectFuPinByPrimaryKey.do`,
    //获取关于统战信息路径
    findMessageList: `${server}/uf/www/findXinXiList.do`,
    selectMessageById: `${server}/uf/www/selectXinXiByPrimaryKey.do`,
    //获取关于统战调研路径
    findResearchList: `${server}/uf/www/findDiaoYanList.do`,
    selectResearchById: `${server}/uf/www/selectDiaoYanByPrimaryKey.do`, 
    //获取关于统战宣传路径
    findPropagandaList: `${server}/uf/www/findXuanChuanList.do`,
    selectPropagandaById: `${server}/uf/www/selectXuanChuanByPrimaryKey.do`,
};
module.exports = config

/*
{
  "pagePath": "page/dept/index",
  "iconPath": "image/tongzhandw.png",
  "selectedIconPath": "image/tongzhandw_selected.png",
  "text": "统战单位"
}{
  "pagePath": "page/personage/index",
  "iconPath": "image/tongzhanrs.png",
  "selectedIconPath": "image/tongzhanrs_selected.png",
  "text": "统战人士"
},
      {
        "pagePath": "page/activity/index",
        "iconPath": "image/huodong.png",
        "selectedIconPath": "image/huodong_selected.png",
        "text": "统战活动"
      },
      {
        "pagePath": "page/service/index",
        "iconPath": "image/zhoubian.png",
        "selectedIconPath": "image/zhoubian_selected.png",
        "text": "统战GIS"
      },
      {
        "pagePath": "page/notice/index",
        "iconPath": "image/msg.png",
        "selectedIconPath": "image/msg_selected.png",
        "text": "统战飞讯"
      } */
