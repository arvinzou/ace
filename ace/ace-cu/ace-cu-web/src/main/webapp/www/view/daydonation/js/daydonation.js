var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    var projectId = null;
    var status = null;
    $.ajax({
        url: "/cu/www/project/findList",
        type:"post",
        async:false,
        data:{start: 0, limit: 9999, type: "1"},
        success:function(result){
            if(result.status == 0) {
                $scope.projectInfo = result.data.rows[0];
                projectId = result.data.rows[0].id;
                status = result.data.rows[0].status;
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
     * 使用记录列表
     */
    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: projectId, start: 0, limit: 99999},
        success:function(result){
            if(result.status == 0) {
                $scope.useRecords = result.data.rows;
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
     * 捐赠列表
     */
    $.ajax({
        url: "/cu/www/project/findDonateList",
        type:"post",
        async:false,
        data:{projectId: projectId, start: 0, limit: 99999},
        success:function(result){
            if(result.status == 0) {
                $scope.donationList = result.data.rows;
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

    $scope.donate = function(){
        if(status != '2'){
            alert("该项目未审核通过！")
        }else{
            window.location.href = '/cu/www/view/order/order.html?projectId='+projectId;
        }
    }

    /**
     * 跳转慈善榜单页面
     */
    $scope.donateRank = function(){
        window.location.href = '/cu/www/view/donatelist/donatelist.html?projectId='+projectId;
    }
});
app.filter('to_trusted', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }
);
app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text != undefined){
            return text.substring(10,text.length+1);
        }else{
            return text;
        }
    }
});