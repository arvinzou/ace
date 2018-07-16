var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data: {limit: pageSize, page: currentPage, modules: "5", status: "1"},  //5代表政策文件
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
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data: {limit: pageSize, page: currentPage, modules: "5", status: "1"},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.search = function(){
        var keyword = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data: {limit: pageSize, page: currentPage, modules: "5", status: "1", title: keyword},  //5代表政策文件
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
     * 查看政策文件详情
     * @param index
     */
    $scope.showInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('policy_info.html?id='+primaryId);
    }

    /**
     * 政策文件下载
     */
    $scope.download = function($event, file, title){
        if(file != '' && file != null){
            var downTitle = file.substring(file.indexOf("filename=")+9,file.length);
            $event.target.download = downTitle;
            $event.target.href="https://zx.huacainfo.com/"+file;
        }else{
            layer.alert("对不起，该政策没有可下载的附件！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
    }
});

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text.length > 10){
            return text.substring(0,10);
        }else {
            return text;
        }
    }
});