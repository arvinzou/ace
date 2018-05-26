var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope) {
    // 初始化查询所有
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    $.ajax({
        url: "/fop/www/selectGeHelpByPrimaryKey",
        type: "post",
        async: false,
        data: {id : primaryId},
        success: function (result) {
            if (result.status == 0) {
                $scope.apealData = result.data;
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

    $scope.releaseMsg = function(){
        var reply = $("#release_msg").val();
        $.ajax({
            url: "/fop/www/insertQuestion",
            type:"post",
            async:false,
            data:{parentId: primaryId, sourceType: "1", reply: reply},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    $("#release_msg").val("");
                    layer.alert("发表成功！", {
                        icon: 1,
                        skin: 'myskin'
                    });
                    $scope.init();
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