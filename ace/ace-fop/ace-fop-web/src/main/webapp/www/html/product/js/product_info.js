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
        url: "/fop/www/selectInformationServiceByPrimaryKeyoDo",
        type:"post",
        async:false,
        data:{id: primaryId, modules: "2"},
        success:function(result){
            if(result.status == 0) {
                $scope.info = result.data;
                $(".content_info").html(result.data.content);
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