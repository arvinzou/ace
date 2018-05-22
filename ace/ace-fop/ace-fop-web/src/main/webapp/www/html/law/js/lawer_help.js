var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    $.ajax({
        url: "/fop/www/findLawQuestionList",
        type:"post",
        async:false,
        data:{"limit":pageSize, page: currentPage},
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
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findLawQuestionList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, title: key_word },
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
    $scope.search = function(){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findLawQuestionList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, title : key_word},
            success:function(result){
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
            },
            error:function(){
                layer.alert("系统内部服务异常", {
                    icon: 5,
                    skin: 'myskin'
                });
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
            url: "/fop/www/findLawQuestionList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, sord : orderParam},
            success:function(result){
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
            },
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }
    /**
     * 发布法律帮助
     */
    $scope.insertLawHelp = function(){
        var userProp = parent.parent.userProp;
        if(userProp == null || userProp == ''){
            location.href='/portal/dynamic/portal/security/login.jsp';
        }else{
            var flag = true;
            var title = $("input[name='law_title']").val();
            var type = $("#law_type option:checked").val();
            var content = $("textarea[name = 'law_content']").val();
            if(title == '' || title == undefined){
                flag = false;
                layer.alert("法律帮助标题不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(type == '' || type == undefined){
                flag = false;
                layer.alert("法律帮助类型不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' || content == undefined){
                flag = false;
                layer.alert("法律帮助内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(flag){
                $.ajax({
                    url: "/fop/www/insertLawQuestion",
                    type:"post",
                    async:false,
                    data:{title: title, subType: type, content: content},
                    success:function(result){
                        if(result.status == 0) {
                            layer.alert("发布成功！", {
                                icon: 1,
                                skin: 'myskin'
                            });
                            $scope.search();
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

    }
});