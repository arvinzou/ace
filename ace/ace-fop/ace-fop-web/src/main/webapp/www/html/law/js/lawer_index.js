var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    $scope.openLawDoc = function($event,docType){
        $event.target.href='lawer_doc.html?docType='+docType;
    }
});