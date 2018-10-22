
var server = "https://zx.huacainfo.com";
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
    uploadUrl: `${server}/portal/www/upload.do`,
    mallList: `${server}/society/www/commodity/getList`,
    siteDetail: `${server}/society/www/commodity/getDetail`,
    sendCode: `${server}/society/www/reg/sendCode`,
    regist: `${server}/society/www/reg/register`,
    orgList: `${server}/society/www/reg/findOrgList`,
    ideaList: `${server}/society/www/idea/findList`,
    releaseIdea: `${server}/society/www/idea/submit`,
    ideaDetail: `${server}/society/www/idea/getIdeaDetail`,
    findUserInfo: `${server}/society/www/reg/findByUserId`,
    createOrder: `${server}/society/www/order/create`,
    behaviorList: `${server}/society/www/behavior/findList`,
    findActivityReport:`${server}/society/www/activity/selectActivityReportByPrimaryKey`,
    findSocietyOrgInfos:`${server}/society/www/activity/findSocietyOrgInfoList`,
    findActivitys:`${server}/society/www/activity/findActivityList`,
    findActivity:`${server}/society/www/activity/selectActivityByPrimaryKey`,
    publicActivityReports:`${server}/society/www/activity/findPublicActivityReportList`,
    applyStatus:`${server}/society/www/activity/getApplyStatus`,
    insertActivity:`${server}/society/www/activity/insertActivity`,
    participants:`${server}/society/www/activity/findActivityParticipants`,
    selectActivityReport:`${server}/society/www/activity/selectActivityReportByActivityId`,
    updateActivityReport:`${server}/society/www/activity/updateActivityReport`,
    pointRank: `${server}/society/www/user/pointsRank`,
    findOrderList: `${server}/society/www/user/findOrderList`,
    findPointsRecord: `${server}/society/www/user/findPointsRecord`,
    findActivitying: `${server}/society/www/activity/findActivitying`,
    findPublicActivityReportList: `${server}/society/www/activity/findPublicActivityReportList`
};
module.exports = config

