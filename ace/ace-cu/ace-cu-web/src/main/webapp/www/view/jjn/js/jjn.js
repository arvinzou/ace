var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var hostPath = "http://127.0.0.1";
var page = 0;
var pageSize = 9999;
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
	$.ajax({
        url: "/cu/www/project/findList",
        type:"post",
        async:false,
        data:{start: page, limit: pageSize, type: "0"},
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
    
   /**
    * 查看救急难项目详情
    */
   $scope.showProjectInfo = function(id){
   		window.location.href = '../donation/donation.html?projectId='+id;
   }
   
   /**
    * 发起筹款
    */
   $scope.raiseMoney = function(){
   		window.location.href = '../apply/apply.html';
   }
});

app.filter(
    'to_trusted', ['$sce', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }]
);