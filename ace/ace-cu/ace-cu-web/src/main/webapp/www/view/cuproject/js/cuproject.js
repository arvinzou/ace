var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var page = 0;
var pageSize = 9999;
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
	
	$.ajax({
        url: "/cu/www/project/findList",
        type:"post",
        async:false,
        data:{start: page, limit: pageSize, type: "0", orderBy: "sequence"},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.rows;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    
    $scope.help = function(projectId){
    	window.location.href = '../donation/donation.html?projectId='+projectId;
    }
});