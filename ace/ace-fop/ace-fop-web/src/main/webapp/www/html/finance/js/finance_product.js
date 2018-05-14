var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

var suretyType = null;
var rateRange = null;
var amountRange = null;
var loanYear = null;
var data_rate = [
	{
    "id": "10,20",
    "text": "10%-20%"
	},
    {
        "id": "21,30",
        "text": "21%-30%"
    },
    {
        "id": "31,40",
        "text": "31%-40%"
    }
];
var data_year = [{
    "id": "0",
    "text": "半年"
	},
    {
        "id": "1",
        "text": "一年"
    },
    {
        "id": "2",
        "text": "二年"
    },
    {
        "id": "3",
        "text": "三年"
    },
    {
        "id": "4",
        "text": "四年"
    },
    {
        "id": "5",
        "text": "五年以上"
    }
];
var data_limit = [{
    "id": "0,50",
    "text": "小于50万"
},
    {
        "id": "50-100",
        "text": "50-100万"
    },
    {
        "id": "100,200",
        "text": "100-200万"
    },
    {
        "id": "200,",
        "text": "200万以上"
    }
];
var data_method = [{
    "id": 1,
    "text": "信用贷"
},
    {
        "id": 2,
        "text": "抵用贷"
    }
];

app.controller(ngControllerName,function($scope){
    $('#product_rate').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_rate,
        onChange: function(newValue) {
            data_rate = newValue;
        }
    });
    $('#product_year').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_year,
        onChange: function(newValue) {
            loanYear = newValue;
        }
    });
    $('#product_limit').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_limit,
        onChange: function(newValue) {
            amountRange = newValue;
        }
    });
    $('#guarantee_method').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_method,
        onChange: function(newValue) {
            suretyType = newValue;
        }
    });

    $.ajax({
        url: "/fop/www/findLoanProductList",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage},
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                $scope.totalSize = totalSize;
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
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        $.ajax({
            url: "/fop/www/findLoanProductList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize},
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
            alert(result.errorMessage);
        }
    }

    /**
     * 根据条件进行检索
     */
    $scope.searchByParam = function(){
        var btmRate = null;     //最低利率
        var topRate = null;     //最高利率
		var btmAmount = null    //最低金额
		var topAmount = null    //最高金额
        if(rateRange != null && rateRange !='' && rateRange != undefined){
            btmRate = rateRange.split(",")[0];     //起始收益率
            topRate = rateRange.split(",")[1];     //截止收益率
        }
        if(amountRange != null && amountRange != '' && amountRange != undefined){
            btmAmount = amountRange.split(",")[0];
            btmAmount = amountRange.split(",")[1];
        }
        var productName = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findLoanProductList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage,btmRate: btmRate, topRate: topRate,btmAmount: btmAmount, topAmount: topAmount, productName: productName, suretyType: suretyType },
            success: function (result) {
                if (result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    var totalSize = result.data.total;
                    $scope.totalSize = totalSize;
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
                    alert(result.errorMessage);
                }
            },
            error: function () {
                alert("内部服务异常");
            }
        });
    }

    /**
     * 查看产品详情
     * @param index
     */
    $scope.showFinanceInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('product_info.html?id='+primaryId);
    }
});

	