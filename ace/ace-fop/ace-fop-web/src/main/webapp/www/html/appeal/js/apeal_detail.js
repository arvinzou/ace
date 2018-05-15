var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    console.log(window.location.search);
    console.log(url);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    $.ajax({
        url: "/fop/www/selectAppealHelpByPrimaryKey",
        type:"post",
        async:false,
        data:{id: primaryId},
        success:function(result){
            if(result.status == 0) {
                $scope.apealInfo = result.data;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });
});