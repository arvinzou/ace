var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var status = null;
var userStatus = null;
var userId = null;

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    //初始化文本框
    var editor = new Simditor({
        textarea: $('#editor_release'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: '/portal/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });

    $("#city").click(function (e) {
        SelCity(this, e);
    });
    $("#city_edit").click(function (e) {
        SelCity(this, e);
    });
    $("s").click(function (e) {
        SelCity(document.getElementById("city"), e);
    });

    /**
     * 查询企业信息
     */
    $.ajax({
        url: "/fop/www/getUserInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                $scope.companyInfo = result.data.data;
                userStatus = result.data.data.status;
                userId = result.data.data.id;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                if(result.errorMessage != '' && result.errorMessage != undefined){
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }else{
                    layer.alert(result.info, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            }
        },
        error:function(){
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });


    $.ajax({
        url: "/fop/www/findProjectList",
        type:"post",
        async:false,
        data: {limit: pageSize, page: currentPage, iself: true},
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
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize, status){
        $.ajax({
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data: {limit: pageSize, page: currentPage, status: status, iself: true},
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
            url: "/fop/www/findProjectList",
            type:"post",
            async:false,
            data: {limit: pageSize, page: 1, status: status, iself: true},
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

    var rowIndex = 0;
    $scope.update_click = function(index){
        rowIndex = index;
        var info = $scope.items[index];
        $scope.infoData = info;
        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
            upload: {
                url: '/portal/files/uploadImage.do', //文件上传的接口地址
                params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                fileKey: 'file', //服务器端获取文件数据的参数名
                connectionCount: 3,
                leaveConfirm: '正在上传文件'
            }
        });
        editor.setValue($scope.infoData.coopDesc);
    }
    $scope.update = function(id){
        var content = $("#editor").val();
        $scope.items[rowIndex].coopDesc = content;
        $.ajax({
            url: "/fop/www/updateProject",
            type:"post",
            async:false,
            data: {
                id: id,
                projectName: $scope.infoData.projectName,
                coopType: $scope.infoData.coopType,
                areaCode: areaCode,
                projectType: $scope.infoData.projectType,
                coopDesc: content,
            },
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
                    $("#myModal").modal('hide');
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
            url: "/fop/www/deleteProjectByFopProjectId",
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
        var title = $("input[name='projectName']").val();
        var cooType = $("#cooType option:checked").val();
        var area = areaCode;
        var projectType = $("input[name='projectType']").val();
        var content = $("textarea[name='pcontent']").val();
        if(title == '' || title == undefined){
            layer.alert("项目名称不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(cooType == '' || cooType == undefined){
            layer.alert("合作方式不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(area == '' || cooType == undefined){
            layer.alert("所属区域不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(content == '' || content == undefined){
            layer.alert("项目内容不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertProject",
            type:"post",
            async:false,
            data:{projectName: title, coopType: cooType, areaCode: area, projectType: projectType, coopDesc: content},
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

                    editor.setValue("");
                    $("#myModal_release").modal('hide');

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
