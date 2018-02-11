// JavaScript Document
var userProp;
$(function () {
    webInit();
});
function webInit() {
    var url = '/portal/system/getUserProp.do';
    $.get(url, function () {
    });

    $('#htmlLoad').load('./../html/liveForm.html',function () {
        $('#JSLoad').load('./../html/createLiveJS.html',function () {

        });
    });
}