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
    appid: 'wxcf3d50130f9844f5',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    companyId: '0010007',
    ak: 'cPY4B8MAYgPQYOuDKPTNvUin31DBPDCB',
    frontColor: '#ffffff',
    backgroundColor: "#3C3C3C",
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/uf/www/captcha/image.do`,
    uploadUrl: `${server}/jxb/www/jxb/upload`,
    getLiveMsgList: `${server}/jxb/www/jxb/getLiveMsgList`,
    insertLiveRptSapp: `${server}/jxb/www/jxb/insertLiveRptSapp`,
    getLiveRptList: `${server}/jxb/www/jxb/getLiveRptList`,
    insertLiveSapp: `${server}/jxb/www/jxb/insertLiveSapp`,
    insertCourseSapp: `${server}/jxb/www/jxb/insertCourseSapp`,
    updateLiveSapp: `${server}/jxb/www/jxb/updateLiveSapp`,
    updateCourseSapp: `${server}/jxb/www/jxb/updateCourseSapp`,
    getCoursById: `${server}/jxb/www/jxb/getCoursById`,
    getLiveById: `${server}/jxb/www/jxb/getLiveById`,
    deleteCourseById: `${server}/jxb/www/jxb/deleteCoursById`,
    deleteLiveById: `${server}/jxb/www/jxb/deleteLiveById`,
    proxyService: `${server}/jxb/www/jxb/proxyService`,
    getLiveListByUserId: `${server}/jxb/www/jxb/getLiveListByUserId`,
    getCourseListByUserId: `${server}/jxb/www/jxb/getCourseListByUserId`
};
module.exports = config
