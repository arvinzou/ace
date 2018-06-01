var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);
var cotype = null;
var costep = null;

//合作方式
var data_cotype = [{
        "id": "1",
        "text": "投资合作"
    },
    {
        "id":"2",
        "text":"合作开发"
    },
    {
        "id":"3",
        "text":"出资+资源合作"
    },
    {
        "id":"4",
        "text":"其他"
    }
];

// //项目阶段
// var data_costep = [
//     {
//         "id": "1",
//         "text": "启动期"
//     },
//     {
//         "id": "2",
//         "text": "计划期"
//     },
//     {
//         "id": "3",
//         "text": "执行期"
//     }
// ];
app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}
    //初始化文本框
    var editor = new Simditor({
        textarea: $('#content')

    });

    $('#coperationType').comboboxfilter({
        url: '',
        scope: 'FilterQuery1',
        data: data_cotype,
        onChange: function(newValue) {
            cotype = newValue;
            console.log(cotype);
        }
    });

    // $('#projectStep').comboboxfilter({
    //     url: '',
    //     scope: 'FilterQuery1',
    //     data: data_costep,
    //     onChange: function(newValue) {
    //         costep = newValue;
    //         console.log(costep);
    //     }
    // });

    $.ajax({
        url: "/fop/www/findProjectList",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, status: "2"},
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
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, status: "2"},
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
     * 查看项目详情
     * @param index
     */
    $scope.showProjectInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('cooperation_info.html?id='+primaryId);
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
     * 发布项目
     */
    $scope.relaseProject = function(){
            var flag = true;
            var projectName = $("input[name='projectName']").val();
            var cooType = $("#cooType option:checked").val();
            var area = $("input[name ='area']").val();
            var projectType = $("input[name='projectType']").val();
            var content = $("textarea[name='content']").val();
            if(projectName == '' || projectName == undefined){
                flag = false;
                layer.alert("项目名称不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(cooType == '' || cooType == undefined){
                flag = false;
                layer.alert("合作方式不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(area == '' || area == undefined){
                flag = false;
                layer.alert("所属区域不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            if(flag){
                $.ajax({
                    url: "/fop/www/insertProject",
                    type:"post",
                    async:false,
                    data:{projectName:projectName, coopType: cooType, areaCode: area, projectType: projectType, coopDesc:content},
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
        var key_word = $("#financeTitle").val();

        $.ajax({
            url: "/fop/www/findProjectList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage, status: "2", projectName: key_word, coopType: cotype, areaCode: areaCode},
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
                        skip: true,
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
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data:{"limit":pageSize,  page: 1, sord : orderParam, status: "2"},
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
});
