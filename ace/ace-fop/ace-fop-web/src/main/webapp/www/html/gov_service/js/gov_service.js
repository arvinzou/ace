var pageSize=10;
var noticeType = "";
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}

    var editor = new Simditor({
        textarea: $('#content'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: '/portal/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    // 初始化查询所有
    $.ajax({
        url: "/fop/www/findGeHelpList",
        type:"post",
        async:false,
        data:{page:currentPage, limit: pageSize,  status: "2"},
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


    /**
     * 按关键字搜索
     */
    $scope.search = function(){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findGeHelpList",
            type:"post",
            async:false,
            data:{page:1, limit: pageSize, title: key_word,  status: "2"},
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
            data:{page:1, limit: pageSize, sord: orderParam,  status: "2"},
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
            clearForm();
        }
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
                layer.alert("诉求标题不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(content == '' && content == undefined){
                flag = false;
                layer.alert("诉求内容不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(flag){
                $.ajax({
                    url: "/fop/www/insertGeHelp",
                    type:"post",
                    async:false,
                    data:{title:name, content:content},
                    success:function(result){
                        if(result.status == 0) {
                            layer.alert("发布成功！", {
                                icon: 1,
                                skin: 'myskin'
                            });
                            $scope.search();
                            if (!$scope.$$phase) {
                                $scope.$apply();
                            }
                            editor.setValue("");
                            $("#myModal").modal('hide');
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
     * 查看诉求详情
     * @param obj
     */
    $scope.showInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('appeal_info.html?id='+primaryId);
    }

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findGeHelpList",
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
});
app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        return text.substring(0,10);
    }
});

