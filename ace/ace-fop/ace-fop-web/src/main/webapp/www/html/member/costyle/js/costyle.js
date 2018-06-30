var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var status = null;
var imgHost = "http://zx.huacainfo.com/";
var coverImg = null;
var coverImg_edit = null;

var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    var relationId = parent.userId;
    //初始化文本框
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
    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, modules: "1", relationId: relationId},
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
                   // skip: true,
                    skin: '#1A56A8',
                    groups: 5,                  //连续显示分页数
                    jump: function(obj, first){ //触发分页后的回调
                        if(!first){
                            currentPage = obj.curr;
                            $scope.searchList(currentPage, pageSize, relationId);
                        }
                    }

                });
            }else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
                });
            }
        },
        error:function(){
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
        }
    });

    $scope.searchList = function(currentPage, pageSize, status, relationId){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, status: status,modules: "1", relationId: relationId},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
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
                skin: 'myskin',
                offset:'400px'
            });
        }
    }

    $scope.search = function(status){
        $.ajax({
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data:{limit:pageSize, page: 1, status: status, modules: "1", relationId: relationId},
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
                                $scope.searchList(currentPage, pageSize, status, relationId);
                            }
                        }

                    });
                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
                });
            }
        });
    }

    var rowIndex =0;
    $scope.update_click = function(index){
        rowIndex = index;
        var info = $scope.items[index];
        $scope.infoData = info;
        //初始化文本框
        var editor = new Simditor({
            textarea: $('#pcontent'),
            toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
            upload: {
                url: '/portal/files/uploadImage.do', //文件上传的接口地址
                params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
                fileKey: 'file', //服务器端获取文件数据的参数名
                connectionCount: 3,
                leaveConfirm: '正在上传文件'
            }
        });
        editor.setValue($scope.infoData.content);
    }
    $scope.update = function(id){
        var fileUrl = null;
        if(coverImg_edit != null){
            fileUrl = coverImg_edit;
        }else{
            fileUrl = $scope.infoData
        }
        $scope.items[rowIndex].content = $("#pcontent").val();
        $.ajax({
            url: "/fop/www/updateInformationServiceDo",
            type:"post",
            async:false,
            data:{modules: "1", id: id, title: $scope.infoData.title, content: $("#pcontent").val(), fileUrl: fileUrl, category: $scope.infoData.category},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    $scope.search();
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    layer.alert("编辑成功！", {
                        icon: 1,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
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
            data:{modules: "1", id: id},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    layer.alert("删除成功！", {
                        icon: 1,
                        skin: 'myskin',
                        offset:'400px'
                    });
                    $scope.items.splice(index,1);
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }

                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
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
                    showUploadText('.viewPicture2 img', '.uploadText2');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".uploadPloadprogress").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.value[0], '.pictureContainer2','.viewPicture2 img','.uploadText2');
                    coverImg = rst.value[0];
                }
            }
        });
        uploader.init();
    }

    $scope.init_update = function(){
        var uploader = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'upbtn_update',
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
                    showUploadText('.viewPicture1 img', '.uploadText1');
                    up.start();
                    return false;
                },
                UploadProgress: function(e, t) {
                    var r = t.percent;
                    $(".uploadPloadprogress").html("开始上传（" + r + "%）")
                },
                FileUploaded: function (uploader, file, responseObject) {
                    var rst = JSON.parse(responseObject.response);
                    viewCover(rst.value[0], '.pictureContainer1','.viewPicture1 img','.uploadText1');
                    coverImg_edit = rst.value[0];
                }
            }
        });
        uploader.init();
    }
    $scope.release = function(){
        var title = $("input[name='name']").val();
        var content = $("textarea[name='pcontent']").val();
        var categoryType = $("#categoryType option:checked").val();
        if(title == '' || title == undefined){
            layer.alert("标题不能为空！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
            return;
        }
        if(categoryType == '' || categoryType == undefined){
            layer.alert("风采类型不能为空！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
            return;
        }
        if(coverImg == null || coverImg == undefined){
            layer.alert("产品封面不能为空！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
            return;
        }
        if(content == '' || content == undefined){
            layer.alert("产品具体内容不能为空！", {
                icon: 5,
                skin: 'myskin',
                offset:'400px'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertInformationServiceDo",
            type:"post",
            async:false,
            data:{title: title, content: content, fileUrl: coverImg, modules: "1", category: categoryType},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    $scope.search();
                    layer.alert("发布成功！", {
                        icon: 1,
                        skin: 'myskin',
                        offset:'400px'
                    });

                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }

                }else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin',
                        offset:'400px'
                    });
                }
            },
            error:function(){
                layer.alert("系统服务内部异常！", {
                    icon: 5,
                    skin: 'myskin',
                    offset:'400px'
                });
            }
        });
    }
});
/*图片上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).data('imgSrc',img);
    var imagePath=imgHost+img;
    showUploadImg(imagePath, imgClazz, textClazz);
}

/*显示上传文字*/
function showUploadText(imgClazz, textClazz) {
    $(imgClazz).prop('src','');
    $(textClazz).show();
}

/*显示上传图片*/
function showUploadImg(imgpath, imgClazz, textClazz) {
    $(imgClazz).removeClass('hidder').addClass('displayer');
    $(imgClazz).prop('src',imgpath);
    $(textClazz).hide();
}