// JavaScript Document
var userProp;
$(function () {
    webInit();
});
function webInit() {
    $.get(userUrl, function () {
    });

    $('#htmlLoad').load('./../jsp/liveForm.jsp',function () {
        $('#JSLoad').load('./../jsp/createLiveJS.jsp',function () {

        });
    });
}