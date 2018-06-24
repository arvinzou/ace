var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var recordList = [];
app.controller(ngControllerName,function($scope){
	console.log(window.location.href);
    var url = window.location.href.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    
    /**
     * 项目详情
     */
    $.ajax({
        url: "/cu/www/project/findDetail",
        type:"post",
        async:false,
        data:{projectId: primaryId},
        success:function(result){
            if(result.status == 0) {
                $scope.projectInfo = result.data;
                $("#projectDetail").html(result.data.description);
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
    var flag = false;
    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                $scope.useRecords = result.data.rows;
                recordList = result.data.rows;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                flag = true;
            }else {
                alert(errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

 /*  $scope.callMethod = function(){
        for(var i=0; i< $scope.useRecords.length; i++){
            $("#record"+i).html($scope.useRecords[i].useToProjectDesc);
        }
    }*/
    
    /**
     * 捐赠列表
     */
    $.ajax({
        url: "/cu/www/project/findDonateList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                $scope.donationList = result.data.rows;
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

    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        for(var i=0; i< recordList.length; i++){
            console.log("record"+i);
            $("#record"+i).html(recordList[i].useToProjectDesc);
        }
    });

    $scope.donate = function(){
        window.location.href = '/cu/www/view/order/order.html?projectId='+primaryId;
    }
});
app.filter('to_trusted', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }
);
//自定义指令repeatFinish
app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});
function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}
