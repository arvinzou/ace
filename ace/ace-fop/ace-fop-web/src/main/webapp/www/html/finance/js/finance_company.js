var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

var rateRange = null;
var yearRange = null;
var data1 = [{
    "id": "10,20",
    "text": "10%-20%"
},
    {
        "id": "21,30",
        "text": "21%-30%"
    },
    {
        "id": "31,40",
        "text": "31-40%"
    }
];

//融资年限
var data2 = [{
    "id": "0",
    "text": "半年"
},
    {
        "id": "1",
        "text": "一年"
    },
    {
        "id": "2",
        "text": "两年"
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
    },
];

app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}

    $scope.money = "";
	//初始化预期年收益
    $('#demo1').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data1,
        onChange: function(newValue) {
            rateRange = newValue;
            console.log(rateRange);
        }
    });

    //初始化融资年限
    $('#demo2').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data2,
        onChange: function(newValue) {
            yearRange = newValue;
            console.log(yearRange);
        }
    });

    $.ajax({
        url: "/fop/www/findFinanceProjectList",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage,  status: "2"},
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
                    //skip: true,
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
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error:function(){
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        $.ajax({
            url: "/fop/www/findFinanceProjectList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize,  status: "2"},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
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
            layer.alert(result.errorMessage, {
                icon: 5,
                skin: 'myskin'
            });
        }
    }

    /**
     * 查看融资详情
     * @param index
     */
    $scope.showFinanceInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('finance_info.html?id='+primaryId);
    }

    /**
     * 发布之前判断是否已经登录
     */
    $scope.before_release = function ($event) {
        var userProp = parent.parent.userProp;
        if (userProp == null || userProp == ''){
            layer.alert("请先登录后再发布！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userStatus !='2'){
            //非会员也不能发布
            layer.alert("对不起，您还不是会员，请先完善信息！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else{
            $event.target.dataset.target='#myModal1';
        }
    }
    /**
     * 发布融资项目
     */
    $scope.relaseFinance = function(){
            var flag = true;
            var projectName = $("input[name='projectName']").val();
            var projectMoney = $("input[name='projectMoney']").val();
            var financeYear = $("#financeYear option:checked").val();
            // var projectType = $("input[name='projectType']").val();
            var rate = $("input[name='rate']").val();
            var content = $("textarea[name='content']").val();
            if(projectName == '' || projectName == undefined){
                flag = false;
                layer.alert("融资名称不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(projectMoney == '' || projectMoney == undefined){
                flag = false;
                layer.alert("融资金额不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(financeYear == '' || financeYear == undefined){
                flag = false;
                layer.alert("融资年限不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            // if(projectType == '' || projectType == undefined){
            //     flag = false;
            //     alert("请输入项目类型！");
            // }
            if(rate == '' || rate == undefined){
                flag = false;
                layer.alert("预期年收益不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' || content == undefined){
                flag = false;
                layer.alert("内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(flag){
                $.ajax({
                    url: "/fop/www/insertFinanceProject",
                    type:"post",
                    async:false,
                    data:{financeTitle:projectName, financeAmount: projectMoney, financeYear:financeYear, financeContent: content, yearYield: rate},
                    success:function(result){
                        if(result.status == 0) {
                            $scope.searchByParam();
                            layer.alert("发布成功！", {
                                icon: 1,
                                skin: 'myskin'
                            });
                        }else {
                            layer.alert(result.errorMessage, {
                                icon: 5,
                                skin: 'myskin'
                            });
                        }
                    },
                    error:function(){
                        layer.alert("系统内部服务异常！", {
                            icon: 5,
                            skin: 'myskin'
                        });
                    }
                });
        }

    }

    /**
     * 根据条件进行检索
     */
    $scope.searchByParam = function(){
        var btmYield = null;
        var topYield = null;
        if(rateRange != null && rateRange !='' && rateRange != undefined){
            btmYield = rateRange.split(",")[0];     //起始收益率
            topYield = rateRange.split(",")[1];     //截止收益率
        }
        var financeTitle = $("#financeTitle").val();
        $.ajax({
            url: "/fop/www/findFinanceProjectList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage, btmYield: btmYield, topYield: topYield, financeTitle: financeTitle,financeYear: yearRange,  status: "2"},
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
                        //skip: true,
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
     *
     * @param val
     */
    $scope.isNumber = function(){
        var temp = /^[0-9]*(\.[0-9]{1,2})?$/;
        if(!temp.test($scope.money)){
            layer.alert("输入金额格式不正确！", {
                icon: 5,
                skin: 'myskin'
            });
            $scope.money = "";
        }
    }
});

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text.length >10){
            return text.substring(0,10);
        }else{
            return text;
        }
    }
});
