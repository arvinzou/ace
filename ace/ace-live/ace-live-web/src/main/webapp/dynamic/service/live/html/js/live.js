// JavaScript Document
var userProp;
$(function () {
    webInit();
});
function webInit() {
    $.get(userUrl, function () {
    });

    $('#htmlLoad').load('./../html/liveForm.html',function () {
        $('#JSLoad').load('./../html/createLiveJS.html',function () {

        });
    });
}