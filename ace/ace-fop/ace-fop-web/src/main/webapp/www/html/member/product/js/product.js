var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var status = null;
var imgHost = "http://zx.huacainfo.com/";
var coverImg = null;

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, modules: "2"},
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
            data:{limit:pageSize, page: currentPage, status: status,modules: "2"},
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
            data:{limit:pageSize, page: 1, status: status, modules: "2"},
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
            data:{modules: "2", id: id, title: $scope.infoData.title, content: $scope.infoData.content},
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
            data:{modules: "2", id: id},
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

    $scope.init_release = function(){
        var uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'upbtn',
            url: '/portal/files/uploadFile.do',
            file_data_name: 'file',
            multi_selection: false,
            resize: {
                width: 1024,
                height: 1024,
                crop: true,
                quality: 60,
                preserve_headers: false
            },
            filters: {
                max_file_size: '2048mb',
                mime_types: [
                    {title: "Image files", extensions: "jpg,gif,png"}
                ]
            },
            init: {
                FileFiltered: function (up, files) {
                    showUploadText();
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".uploadPloadprogress").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.value[0]);
                    coverImg = rst.value[0];
                }
            }
        });
        uploader.init();
    }
    $scope.release = function(){
        var title = $("input[name='name']").val();
        var content = $("textarea[name='pcontent']").val();
        if(title == '' || title == undefined){
            layer.alert("标题不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(coverImg == null || coverImg == undefined){
            layer.alert("产品封面不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(content == '' || content == undefined){
            layer.alert("产品具体内容不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertInformationServiceDo",
            type:"post",
            async:false,
            data:{title: title, content: content, fileUrl: coverImg, modules: "2"},
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
/*图片上传成功后*/
function viewCover(img) {
    $('.pictureContainer').data('imgSrc',img);
    var imagePath=imgHost+img;
    showUploadImg(imagePath);
}

/*显示上传文字*/
function showUploadText() {
    $('.viewPicture img').prop('src','');
    $('.uploadText').show();
}

/*显示上传图片*/
function showUploadImg(imgpath) {
    $('.viewPicture img').prop('src',imgpath);
    $('.uploadText').hide();
}