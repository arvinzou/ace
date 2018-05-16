var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope) {
    // 初始化查询所有
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    $.ajax({
        url: "/fop/www/selectGeHelpByPrimaryKey",
        type: "post",
        async: false,
        data: {id : primaryId},
        success: function (result) {
            if (result.status == 0) {
                $scope.apealData = result.data;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            } else {
                alert(result.info);
            }
        },
        error: function () {
            alert("内部服务异常");
        }
    });
});