var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var coverImg = null;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}

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
    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type:"post",
        async:false,
        data:{limit:pageSize, page: currentPage, modules: "2", status: "2"},  //2代表企业产品
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
        $.ajax({
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, modules: "2",  status: "2"},
            success:$scope.responseHandle,
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.search = function(){
        var keyword = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findInformationServiceListDo",
            type:"post",
            async:false,
            data: {limit: pageSize, page: currentPage, modules: "2", status: "2", title: keyword},  //3人才信息
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
    $scope.before_release = function ($event) {
        var userProp = parent.parent.userProp;
        console.log("userStyatus",userStatus);
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
     * 发布产品信息
     */
    $scope.releaseInfo = function() {
        console.log(coverImg);
        var title = $("input[name='name']").val();
        var content = $("textarea[name='content']").val();
        var remark = $("textarea[name='remark']").val();
        if (title == '' || title == undefined) {
            layer.alert("标题不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if (coverImg == null || coverImg == undefined) {
            layer.alert("产品封面不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if (content == '' || content == undefined) {
            layer.alert("产品具体内容不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(remark == '' || remark == undefined){
            layer.alert("产品简介不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertInformationServiceDo",
            type: "post",
            async: false,
            data: {title: title, content: content, modules: "2", fileUrl: coverImg, remark: remark},
            success: function (result) {
                if (result.status == 0) {
                    console.log(result);
                    $scope.searchList(currentPage, pageSize);
                    layer.alert("发布成功！", {
                        icon: 1,
                        skin: 'myskin'
                    });
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                } else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            },
            error: function () {
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    /**
     * 查看企业产品详情
     * @param index
     */
    $scope.showInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('product_info.html?id='+primaryId);
    }
});

/*图片上传成功后*/
function viewCover(img) {
    $('.pictureContainer').data('imgSrc',img);
    var imagePath=img;
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

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text.length >10){
            return text.substring(0,10);
        }else{
            return text;
        }
    }
});