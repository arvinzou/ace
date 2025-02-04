


var server = "https://zx.huacainfo.com";
var serverfile = "https://zx.huacainfo.com/";
var rtmpserver ="rtmp://zx.huacainfo.com/live/";
var hlsserver ="http://139.224.0.227:9999/hls/";
var websocketurl = 'zx.huacainfo.com';

var config = {
    rtmpserver,
    websocketurl,
    server,
    serverfile,
    hlsserver,
    appid: 'wxcf3d50130f9844f5',
    appsecret: '10a109f311c9803c2a8fd17b14bf09dd',
    companyId: '00010001',
    ak: 'cPY4B8MAYgPQYOuDKPTNvUin31DBPDCB',
    frontColor: '#000000',
    backgroundColor: "#ffffff",
    loginUrl: `${server}/portal/www/authority.do`,
    checkImageUrl: `${server}/live/www/captcha/image`,
    uploadUrl: `${server}/portal/www/upload.do`,
    getLiveMsgList: `${server}/live/www/live/getLiveMsgList`,
  insertLiveRpt: `${server}/live/liveRpt/www/insertLiveRpt`,
    getLiveRptList: `${server}/live/www/live/getLiveRptList`,
    insertLive: `${server}/live/live/www/insertLive`,
    updateLive: `${server}/live/live/www/updateLive`,
    selectLiveByPrimaryKey: `${server}/live/live/www/selectLiveByPrimaryKey`,
  deleteLiveByLiveId: `${server}/live/live/www/deleteLiveByLiveId`,
    findLiveList: `${server}/live/live/www/findLiveList`,
  updateAuditStatus: `${server}/live/live/www/updateAuditStatus`,
  insertLiveCmt: `${server}/live/liveCmt/www/insertLiveCmt`,
  addLike: `${server}/live/www/live/addLike`,
  getliveListByCorpId: `${server}/live/live/www/getliveListByCorpId`,
  loadLive: `${server}/live/live/www/loadLive`
};
module.exports = config