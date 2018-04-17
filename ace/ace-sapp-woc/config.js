var server = "http://106.75.69.81";
var serverfile = "http://106.75.69.81";
var config = {
    server,
    serverfile,
    appid: 'wx70ba7c5dca85e4da',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    frontColor: '#ffffff',
    backgroundColor: "#3C3C3C",
    loginUrl: `${server}/portal/www/authority.do`,
    statisticsUrl: `${server}/woc/www/data/statistics`,
    // checkImageUrl: `${server}/uf/www/captcha/image.do`,
    // uploadUrl: `${server}/jxb/www/jxb/upload.do`,
    // getLiveMsgList: `${server}/jxb/www/jxb/getLiveMsgList.do`,
    // insertLiveRptSapp: `${server}/jxb/www/jxb/insertLiveRptSapp.do`,
    // getLiveRptList: `${server}/jxb/www/jxb/getLiveRptList.do`,
    // insertLiveSapp: `${server}/jxb/www/jxb/insertLiveSapp.do`,
    // insertCourseSapp: `${server}/jxb/www/jxb/insertCourseSapp.do`
};
module.exports = config
