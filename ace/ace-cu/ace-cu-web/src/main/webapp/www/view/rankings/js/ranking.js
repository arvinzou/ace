var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    $.ajax({
        url: "/cu/www/report/donateRank",
        type:"post",
        async:false,
        data:{start:0, limit: 999999, needOpenId: "2"},
        success:function(result){
            if(result.status == 0) {
                $scope.rankList = result.data;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
});