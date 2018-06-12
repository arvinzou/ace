var pageSize = 10;
var noticeType = "";
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app = angular.module(ngAppName, []);

var companyProperty = null;
//企业类型
var data = [{
    "id": "1",
    "text": "私营企业"
},
    {
        "id": "2",
        "text": "港澳台商投资企业"
    },
    {
        "id": 3,
        "text": "外商投资企业"
    },
    {
        "id": 4,
        "text": "股份合作企业"
    },
    {
        "id": 5,
        "text": "其他联营企业"
    },
    {
        "id": 6,
        "text": "有限责任公司"
    },
    {
        "id": 7,
        "text": "股份有限公司"
    },
    {
        "id": 8,
        "text": "其他企业"
    }
];

app.controller(ngControllerName, function ($scope) {
    // 初始化查询所有
    $('#demo2').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data,
        onChange: function (newValue) {
            companyProperty = newValue;
        }
    });
    $.ajax({
        url: "/fop/www/findCompanyList",
        type: "post",
        async: false,
        data: {page: currentPage, limit: pageSize},
        success: function (result) {
            if (result.status == 0) {
                $scope.items = result.data.list;
                $scope.totalCount = result.data.total;
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
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findCompanyList",
            type: "post",
            async: false,
            data: {
                page: currentPage,
                limit: pageSize,
                title: key_word,
                areaCode: areaCode,
                companyProperty: companyProperty
            },
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

    /**
     * 根据企业性质，地区，关键字去查询企业公示
     */
    $scope.search = function () {
        var key_word = $("#key_word").val();
        console.log("地区代码：", areaCode);
        $.ajax({
            url: "/fop/www/findCompanyList",
            type: "post",
            async: false,
            data: {
                page: currentPage,
                limit: pageSize,
                title: key_word,
                areaCode: areaCode,
                companyProperty: companyProperty
            },
            success: function (result) {
                if (result.status == 0) {
                    $scope.items = result.data.list;
                    $scope.totalCount = result.data.total;
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
    }
});