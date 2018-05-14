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
        url: "/fop/www/findGeHelpList",
        type:"post",
        async:false,
        data:{page:currentPage, limit: pageSize},
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
                alert(result.info);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });


    /**
     * 按关键字搜索
     */
    $scope.search = function(){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findGeHelpList",
            type:"post",
            async:false,
            data:{page:1, limit: pageSize, title: key_word},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("内部服务异常");
            }
        });
    }

    /**
     * 列表排序
     */
    $scope.sortList = function(name){
        var flag = name;
        var orderParam = "";
        if(flag == 'asc'){
            orderParam = 'asc';
            $("#"+flag).hide();
            $("#desc").show();
        }else{
            orderParam = 'desc';
            $("#"+flag).hide();
            $("#asc").show();
        }
        $.ajax({
            url: "/fop/www/findGeHelpList",
            type:"post",
            async:false,
            data:{page:1, limit: pageSize, sord: orderParam},
            success:function(result){
                if(result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("内部服务异常");
            }
        });
    }

    /**
     * 发布诉求
     */
    $scope.release = function(){
        var flag= true;
        var name = $("input[name='title']").val();
        var content = $("textarea[name='content']").val();
        console.log(name);
        if(name == '' && name == undefined){
            flag = false;
            alert("请输入诉求标题");
        }
        if(content == '' && content == undefined){
            flag = false;
            alert("请输入诉求内容");
        }
        if(flag){
            $.ajax({
                url: "/fop/www/insertGeHelp",
                type:"post",
                async:false,
                data:{title:name, content:content},
                success:function(result){
                    if(result.status == 0) {
                       alert("发布成功");
                        if (!$scope.$$phase) {
                            $scope.$apply();
                        }
                    }else {
                        alert(result.info);
                    }
                },
                error:function(){
                    alert("内部服务异常");
                }
            });
        }
    }

    /**
     * 查看诉求详情
     * @param obj
     */
    $scope.showInfo = function(obj){
        window.open('appeal_info.html?id=c6a5092b37094cc38a7c793912bc2543');
    }

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findGeHelpList",
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
