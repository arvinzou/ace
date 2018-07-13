var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    $.ajax({
        url: "/fundtown/www/info/vipList",
        type:"post",
        async:false,
        data:{
            type: "0",
            start: 0,
            limit: 9999
        },
        success:function(result){
            if(result.status == 0) {
                $scope.vipList = result.data.rows;
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

    $scope.changeTab = function(type, clazz){
        $(clazz).addClass("active");
        $(clazz).siblings().removeClass("active");
        if(type == '0'){
            $(".list_title").html("私募股权投资基金管理机构公示");
        }else if(type == '1'){
            $(".list_title").html("私募股权投资基金公示");
        }
        $.ajax({
            url: "/fundtown/www/info/vipList",
            type:"post",
            async:false,
            data:{
                type: type,
                start: 0,
                limit: 9999
            },
            success:function(result){
                if(result.status == 0) {
                    $scope.vipList = result.data.rows;
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
    }

    $scope.showVipInfo = function(deptId){
        window.location.href = '/fundtown/www/publicity/pinfo.html?deptId='+deptId;
    }
});