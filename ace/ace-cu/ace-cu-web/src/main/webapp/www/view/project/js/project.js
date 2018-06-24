var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var hostPath = "http://127.0.0.1";
var page = 0;
var pageSize = 9999;
var app =angular.module(ngAppName, []);
var defaultProjectId = null;

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
                defaultProjectId = result.data.rows[0].id;
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

    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: defaultProjectId, start: 0, limit: 9999},
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

    $.ajax({
        url:"/cu/www/project/findDonateList",
        type:"post",
        async:false,
        data:{projectId: defaultProjectId, start: 0, limit: 9999},
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

    $scope.showInfo = function(id){
        defaultProjectId = id;
        console.log("defaultProjectId",defaultProjectId);
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

    $scope.doSomething = function(){
        var th_width1 = $(".news-module1 li").eq(0).width();
        var th_left1 = $(".news-module1 li").eq(0).offset().left;
        var slider_width1 = $(".news-slider1").width();
        var slider_left1 = th_left1 + (th_width1/2) - slider_width1/2;
        $(".news-slider1").css("left",slider_left1);
        $(".news-module1 li").on("click",function(){
            var n = $(this).index();
            var th_width = $(this).width();
            var th_left = $(this).offset().left;
            var slider_width = $(".news-slider1").width();
            var slider_left = th_left + (th_width/2) - slider_width/2;
            $(".news-slider1").css("left",slider_left);
            $(this).addClass("active").siblings().removeClass("active");
        });
    }

    $scope.donate = function(){
        window.location.href = '/cu/www/view/order/order.html?projectId='+defaultProjectId;
    }
});
app.filter(
    'to_trusted', ['$sce', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }]
);
app.directive('repeatDone', function() {
    return {
        link: function(scope, element, attrs) {
            if (scope.$last) {                   // 这个判断意味着最后一个 OK
                scope.$eval(attrs.repeatDone)    // 执行绑定的表达式
            }
        }
    }
})
function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}
