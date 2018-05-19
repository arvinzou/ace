var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var status = null;

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){
    //初始化文本框
     var editor = new Simditor({
         textarea: $('#editor')

     });
    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, modules: "3"},
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
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, modules: "3", status: status},
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
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data:{limit:pageSize, page: 1, modules: "3", status: status},
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
    }
    $scope.update = function(id){
        $.ajax({
            url: "/fop/www/updateInformationServiceDo",
            type:"post",
            async:false,
            data:{modules: "3", id: id, title: $scope.infoData.title, content: $scope.infoData.content},
            success:function(result){
                if(result.status == 0) {
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
            url: "/fop/www/deleteInformationServiceByInformationServiceIdDo",
            type:"post",
            async:false,
            data:{modules: "3", id: id},
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
});
app.filter('convertText',function(){
    return function(content){
        content = content.replace(/(\n)/g, "");
        content = content.replace(/(\t)/g, "");
        content = content.replace(/(\r)/g, "");
        content = content.replace(/<\/?[^>]*>/g, "");
        content = content.replace(/\s*/g, "");
        content = content.replace("&nbsp", "");
        return content;
    }
});