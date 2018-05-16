var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
var sordParam = null;
var filePath = null;    //上传文件路径
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    /**
	 * 文件上传
     * @type {plupload.Uploader}
     */
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'pickfiles', // you can pass an id...
        container: document.getElementById('container'), // ... or DOM Element itself
        url: '/portal/files/uploadFile.do',
        flash_swf_url: '../js/Moxie.swf',
        silverlight_xap_url: '../js/Moxie.xap',

       /* filters: {
            max_file_size: '10mb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            },
                {
                    title: "Zip files",
                    extensions: "zip"
                }
            ]
        },*/

        init: {
            PostInit: function() {
                document.getElementById('filelist').innerHTML = '';

            },

            FilesAdded: function(up, files) {
                plupload.each(files, function(file) {
                    up.start();
                    document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
                });
            },
            FileUploaded: function(up, file, info) {
                // Called when file has finished uploading
                console.log('[FileUploaded] File:', file, "Info:", info);
                filePath = JSON.parse(info.response).value[0];
                console.log("filepath", filePath);
            },
            UploadProgress: function(up, file) {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            },

            Error: function(up, err) {
                document.getElementById('console').appendChild(document.createTextNode("\nError #" + err.code + ": " + err.message));
            }
        }
    });

    uploader.init();

    /**
	 * 获取诉求列表
     */
    $.ajax({
        url: "/fop/www/findAppealHelpList",
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
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("内部服务异常");
        }
    });

    $scope.searchList = function(currentPage, pageSize){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findAppealHelpList",
            type:"post",
            async:false,
            data:{page:currentPage, limit: pageSize, title: key_word , sord: sordParam},
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
            alert(result.errorMessage);
        }
    }
    $scope.search = function(){
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findAppealHelpList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, requestTitle: key_word},
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
            url: "/fop/www/findAppealHelpList",
            type:"post",
            async:false,
            data:{limit:pageSize, page: currentPage, sord : orderParam},
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
                    alert(result.errorMessage);
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
    $scope.insertApeal = function(){
        var userProp = parent.parent.userProp;
        if(userProp == null || userProp == '' ){
            location.href='/portal/dynamic/portal/security/login.jsp';
        }else {
            var flag = true;
            var apealTitle = $("input[name = 'apealTitle']").val();
            var content = $("textarea[name = 'content']").val();
            if (apealTitle == '' || apealTitle == undefined) {
                flag = false;
                alert("诉求标题不能为空！");
            }
            if (content == '' || content == undefined) {
                flag = false;
                alert("诉求内容不能为空！");
            }
            if (flag) {
                $.ajax({
                    url: "/fop/www/insertAppealHelp",
                    type: "post",
                    async: false,
                    data: {requestTitle: apealTitle, requestDesc: content, annexUrl: filePath},
                    success: function (result) {
                        if (result.status == 0) {
                            $scope.search();
                            alert("发布成功！");
                        } else {
                            alert(result.errorMessage);
                        }
                    },
                    error: function () {
                        alert("内部服务异常");
                    }
                });
            }
        }
	}

	$scope.showApealInfo = function(index){
        var primaryId = $scope.items[index].id;
        console.log(primaryId);
        window.open('apeal_detail.html?id='+primaryId);
	}
});


