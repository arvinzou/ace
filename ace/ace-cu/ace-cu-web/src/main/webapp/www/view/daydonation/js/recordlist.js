var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var primaryId = null;
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "projectId"){
                primaryId = value;
            }
        }
    }
    /**
     * 使用记录列表
     */
    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 99999},
        success:function(result){
            if(result.status == 0) {
                $scope.useRecords = result.data.rows;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
});

app.filter('to_trusted', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }
);