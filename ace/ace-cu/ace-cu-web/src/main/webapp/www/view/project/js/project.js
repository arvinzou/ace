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
        data:{start: page, limit: pageSize, type: "1"},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.rows;
                $scope.projectInfo = result.data.rows[0];
    			$("#projectDetail").html(result.data.rows[0].description);
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
    
    $scope.showInfo = function(id){
    	var datas = $scope.items;
    	for(var i=0; i<datas.length; i++){
    		if(id == datas[i].id){
    			$scope.projectInfo = datas[i];
    			$("#projectDetail").html(datas[i].description);
    			
    			/**
    			 * 查询使用记录
    			 */
    			 $.ajax({
			        url: "/cu/www/project/findUsedRecordList",
			        type:"post",
			        async:false,
			        data:{projectId: datas[i].id, start: 0, limit: 9999},
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
			        url:"/cu/www/project/findDonateList",
			        type:"post",
			        async:false,
			        data:{projectId: datas[i].id, start: 0, limit: 9999},
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
    		}
    	}
    }
});

function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}
