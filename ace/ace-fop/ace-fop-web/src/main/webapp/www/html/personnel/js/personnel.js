var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    try{
        $scope.userProp = userProp;
    }catch(e){}
    //初始化文本框
    var editor = new Simditor({
        textarea: $('#editor')

    });

    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, modules: "3",  status: "2"},  //3代表人才信息
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
            data:{limit:pageSize, page: currentPage, modules: "3", status: "2"},
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
     * 发布之前判断是否已经登录
     */
    $scope.before_release = function () {
        var userProp = parent.parent.userProp;
        if (userProp == null || userProp == ''){
            layer.alert("请先登录后再发布！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
    }
    /**
     * 发布人才信息
     */
    $scope.releaseInfo = function(){
            var title = $("input[name='postname']").val();
            var content = $("textarea[name='content']").val();
            if(title == '' || title == undefined){
                layer.alert("岗位名称不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' || content == undefined){
                layer.alert("岗位具体内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            $.ajax({
                url: "/fop/www/insertInformationServiceDo",
                type:"post",
                async:false,
                data:{title: title, content: content, modules: "3"},
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

    $scope.showInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('recruit.html?id='+primaryId);
    }
});