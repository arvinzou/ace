var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    console.log(window.location.href);
    var url = window.location.href.substring(1);
    var deptId = url.substring(url.indexOf('=')+1);
    console.log(deptId);

    if(deptId){
        $.ajax({
            url: "/fundtown/www/info/vipInfo",
            type:"post",
            async:false,
            data:{
                deptId: deptId
            },
            success:function(result){
                if(result.status == 0) {
                    $scope.vipInfo = result.data;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    if(result.errorMessage){
                        alert(result.errorMessage);
                    }else{
                        alert(result.info);
                    }
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }else {
        alert("系统服务内部异常！");
    }
});