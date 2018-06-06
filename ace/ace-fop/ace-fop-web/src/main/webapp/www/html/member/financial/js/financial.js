var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var status = null;

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){
    //初始化文本框

    var editor = new Simditor({
        textarea: $('#editor_release')
    });
    $.ajax({
        url: "/fop/www/findFinanceProjectList",
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
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error:function(){
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize, status){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findFinanceProjectList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, status: status},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统服务内部异常！", {
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

    $scope.search = function(status){
        $.ajax({
            url: "/fop/www/findFinanceProjectList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: 1, status: status},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
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
                                $scope.searchList(currentPage, pageSize, status);
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
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.update_click = function(index){
        var info = $scope.items[index];
        $scope.infoData = info;
        var editor = new Simditor({
            textarea: $('#editor_update')
        });
        editor.setValue($scope.infoData.financeContent);
    }
    $scope.update = function(id){
        var content = $("textarea[name='content']").val();
        $.ajax({
            url: "/fop/www/updateFinanceProject",
            type:"post",
            async:false,
            data:{id:id,financeTitle:$scope.infoData.financeTitle, financeAmount: $scope.infoData.financeAmount, financeYear:$scope.infoData.financeYear, financeContent: content, yearYield: $scope.infoData.yearYield},
            success:function(result){
                if(result.status == 0) {
                    $scope.search();
                    console.log(result);

                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    layer.alert("编辑成功！", {
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
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }
    $scope.delete = function(index){
        var id = $scope.items[index].id;
        $.ajax({
            url: "/fop/www/deleteFinanceProjectByFopFinanceProjectId",
            type:"post",
            async:false,
            data:{id: id},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    layer.alert("删除成功！", {
                        icon: 1,
                        skin: 'myskin'
                    });
                    $scope.items.splice(index,1);
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }

                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.release = function(){
        var financeName = $("input[name = 'financeName']").val();
        var financeMoney = $("input[name = 'financeMoney']").val();
        var financeYear = $("input[name = 'financeYear']").val();
        var rate = $("input[name = 'rate']").val();
        var content = $("textarea[name = 'pcontent']").val();
        if(financeName == '' || financeName == undefined){
            layer.alert("名称不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(financeMoney == '' || financeMoney == undefined){
            layer.alert("金额不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(financeYear == '' || financeYear == undefined){
            layer.alert("年限不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(rate == '' || rate == undefined){
            layer.alert("年收益不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(content == '' || content == undefined){
            layer.alert("内容不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertFinanceProject",
            type:"post",
            async:false,
            data:{financeTitle:financeName, financeAmount: financeMoney, financeYear:financeYear, financeContent: content, yearYield: rate},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    $scope.search();
                    layer.alert("发布成功！", {
                        icon: 1,
                        skin: 'myskin'
                    });

                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }

                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }
});