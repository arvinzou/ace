var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var pageSize = 5;
var currentPage = 1;

var app = angular.module(ngAppName, []);
app.controller(ngControllerName, function ($scope) {
    $.ajax({
        url: "/fop/www/findCompanyList",
        type: "post",
        async: false,
        data: {companyType: 2, limit: pageSize, page: currentPage},   //2代表律师事务所
        success: function (result) {
            if (result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                var totalPage;
                if (totalSize % pageSize == 0) {
                    totalPage = totalSize / pageSize;
                } else {
                    totalPage = totalSize / pageSize + 1;
                }
                laypage({
                    cont: $("#paganation"),   //容器名
                    pages: totalPage,           //总页数
                    curr: currentPage,         //当前页
                    skip: true,
                    skin: '#1A56A8',
                    groups: 5,                  //连续显示分页数
                    jump: function (obj, first) { //触发分页后的回调
                        if (!first) {
                            currentPage = obj.curr;
                            $scope.searchList(currentPage, pageSize);
                        }
                    }

                });
            } else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error: function () {
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function (currentPage, pageSize) {
        $.ajax({
            url: "/fop/www/findCompanyList",
            type: "post",
            async: false,
            data: {page: currentPage, limit: pageSize, companyType: 2},
            success: $scope.responseHandle,
            error: function () {
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.responseHandle = function (result) {
        if (result.status == 0) {
            $scope.items = result.data.list;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        } else {
            layer.alert(result.errorMessage, {
                icon: 5,
                skin: 'myskin'
            });
        }
    }
});