var pageSize=10;
var noticeType = "";
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    // 初始化查询所有
    $.ajax({
        url: "/fop/www/findCompanyList",
        type:"post",
        async:false,
        data:{page:currentPage, limit: pageSize},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                $scope.totalCount = result.data.total;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                var totalPage;
                if(totalSize % pageSize == 0){
                    totalPage = totalSize / pageSize;
                }else{
                    totalPage = totalSize / pageSize +1;
                }
                laypage({
                    cont: $("#paganation"),   //容器名
                    pages: totalPage,           //总页数
                    curr: currentPage,         //当前页
                    skip: true,
                    skin: '#1A56A8',
                    groups: 5,                  //连续显示分页数
                    jump: function(obj, first){ //触发分页后的回调
                        if(!first){
                            currentPage = obj.curr;
                            $scope.searchList(currentPage, pageSize);
                        }
                    }

                });
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findCompanyList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, title: key_word },
            success:$scope.responseHandle,
            error:function(){
                alert("内部服务异常");
            }
        });
    }

    $scope.responseHandle = function(result){
        if(result.status == 0) {
            $scope.items = result.data.list;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        }else {
            alert(result.info);
        }
    }
});