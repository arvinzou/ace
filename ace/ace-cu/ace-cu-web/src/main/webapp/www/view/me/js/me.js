var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    /**
     * 查询用户信息
     */
    $.ajax({
        url: "/cu/www/user/findByOpenId",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                $scope.userInfo = result.data;
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
    /**
     * 爱心记录/爱心积分
     */
    $.ajax({
        url: "/cu/www/user/findDonateList",
        type:"post",
        async:false,
        data:{start:0, limit:9999},
        success:function(result){
            if(result.status == 0) {
                $scope.donateList = result.data.rows;
                $scope.times = result.data.total;
                var len = result.data.rows.length;
                var arr = result.data.rows;
                var total=0;
                var points = 0;
                for(var i=0; i<len; i++){
                    if(arr[i].donateAmount !='' && arr[i].donateAmount!=undefined){
                        total += parseFloat(arr[i].donateAmount);
                    }
                    if(arr[i].points !='' && arr[i].points!=undefined){
                        points += parseInt(arr[i].points);
                    }
                }
                $scope.totalAmount = total;
                $scope.totalPoint = points;
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

    /**
     * 我的求助
     */
    $.ajax({
        url: "/cu/www/user/findMyProject",
        type:"post",
        async:false,
        data:{start:0, limit:9999},
        success:function(result){
            if(result.status == 0) {
                $scope.projectList = result.data.rows;
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

    $scope.aboutus = function(){
        window.location.href = '/cu/www/view/about/about.html';
    }

    $scope.apply = function(){
        window.location.href = '/cu/www/view/apply/apply.html';
    }

    $scope.showProgress = function(id){
        window.location.href = '/cu/www/view/me/apply_progress.html?projectId='+id;
    }
});

function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}
