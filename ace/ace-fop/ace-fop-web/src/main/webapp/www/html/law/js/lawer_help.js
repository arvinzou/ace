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

    $.ajax({
        url: "/fop/www/findLawQuestionList",
        type:"post",
        async:false,
        data:{"limit":pageSize, page: currentPage,  status: "2"},
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
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findLawQuestionList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, title: key_word , status: "2"},
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
            data:{limit:pageSize, page: currentPage, title : key_word,  status: "2"},
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
            data:{limit:pageSize, page: currentPage, sord : orderParam,  status: "2"},
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
        }else if(userStatus != '2'){
            //非会员也不能发布
            layer.alert("对不起，您还不是会员，请先完善信息！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userTypes == '3'){ //3代表银行，不能发表
            layer.alert("对不起，您属于银行企业类型，不能发表！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else{
            $event.target.dataset.target='#myModal';
        }
    }
    /**
     * 发布法律帮助
     */
    $scope.insertLawHelp = function(){
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

    /**
     * 查看法律帮助详情
     * @param index
     */
    $scope.showInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('help_info.html?id='+primaryId);
    }
});