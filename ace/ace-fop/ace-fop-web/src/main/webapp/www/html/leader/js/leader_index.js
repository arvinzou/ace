var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

});

function setIframeHeight(iframe) {
    if(iframe) {
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if(iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
};