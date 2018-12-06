var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope) {

    console.log(window.location.search);
    console.log(url);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    $.ajax({
        url: "/fop/fopCompany/www/selectFopCompanyByPrimaryKey",
        type:"post",
        async:false,
        data:{"id": primaryId},
        success:function(result){
            if(result.status == 0) {
                $scope.companyData = result.value;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error:function(){
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $.ajax({
        url: "/fop/pm/www/findPmList",
        type:"post",
        async:false,
        data:{"companyId": primaryId, "start": 0, "limie": 999},
        success:function(result){
            if(result.status == 0) {
                $scope.partyMember = result.rows;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error:function(){
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });
});