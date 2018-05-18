var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
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
        data:{limit:pageSize, page: currentPage, modules: "4"},  //4代表招商信息
        success:function(result){
            if(result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                $scope.totalCount = totalSize;
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
            data:{limit:pageSize, page: currentPage, modules: "4"},
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
     * 发布招商信息
     */
    $scope.releaseInfo = function(){
        var userProp = parent.parent.userProp;
        if(userProp == null || userProp == '' ) {
            location.href = '/portal/dynamic/portal/security/login.jsp';
        }else{
            var title = $("input[name='name']").val();
            var content = $("textarea[name='content']").val();
            if(title == '' || title == undefined){
                layer.alert("标题不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' || content == undefined){
                layer.alert("招商具体内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            $.ajax({
                url: "/fop/www/insertInformationServiceDo",
                type:"post",
                async:false,
                data:{title: title, content: content, modules: "4"},
                success:function(result){
                    if(result.status == 0) {
                        console.log(result);
                        $scope.searchList(currentPage, pageSize);
                        layer.alert("发布成功！", {
                            icon: 1,
                            skin: 'myskin'
                        });
                        $("input[name='postname']").val("");
                        $("textarea[name='content']").val("");
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
                    layer.alert("系统内部服务异常！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            });
        }
    }
});