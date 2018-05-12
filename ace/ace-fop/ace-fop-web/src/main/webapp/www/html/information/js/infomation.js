var pageSize=10;
var noticeType = "";
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    // 初始化查询所有
    noticeType = $("#noticeType").val();
    $.ajax({
        url: "/fop/www/findNoticeList",
        type:"post",
        async:false,
        data:{"limit":pageSize, noticeType:noticeType, page: 1},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });

    /**
     * 按关键字搜索
     */
    $scope.search = function(){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findNoticeList",
            type:"post",
            async:false,
            data:{"limit":pageSize, noticeType:noticeType, page: 1, title : key_word},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("内部服务异常");
            }
        });
    }

    /**
     * 列表排序
     */
    $scope.sortList = function(name){
        var flag = name;
        var orderParam = "";
        if(flag == 'asc'){
            orderParam = 'asc';
            $("#"+flag).hide();
            $("#desc").show();
        }else{
            orderParam = 'desc';
            $("#"+flag).hide();
            $("#asc").show();
        }
        $.ajax({
            url: "/fop/www/findNoticeList",
            type:"post",
            async:false,
            data:{"limit":pageSize, noticeType:noticeType, page: 1, sord : orderParam},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("内部服务异常");
            }
        });
    }
});
