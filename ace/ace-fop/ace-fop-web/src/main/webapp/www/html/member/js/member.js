var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var userStatus = null;

var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    var iframe = $("#iframe");
    if (iframe.readyState == "complete"){
        setIframeHeight(iframe);
    }

    /**
     * 查询统计信息
     */
    $.ajax({
        url: "/fop/www/publishStatistics",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                $scope.statisData = result.data;
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
                $scope.companyType = result.data.data.companyType;
                userStatus = result.data.data.status;
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



    $scope.writeInfo = function(){
        var dataType = "";
        $.ajax({
            url: "/fop/www/organizationType",
            type:"post",
            async:false,
            data:{},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    dataType = result.data;       //dataType:1表示团体会员，2表示企业会员
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    if(dataType == "1"){
                        window.open("pinformation/group.html");
                    }else if(dataType == "2"){
                        window.open("pinformation/company.html");
                    }else{
                        layer.alert("对不起，您没有登录！", {
                            icon: 5,
                            skin: 'myskin'
                        });
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

    $scope.checkUserUrl = function($event, url){
        if(userStatus != '2'){
            layer.alert("对不起，您还不是会员，请先完善资料！", {
                icon: 5,
                skin: 'myskin'
            });
        }else{
            $event.target.href()
        }
    }
});

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text != ''){
            return text.substring(0,10);
        }else{
            return text;
        }
    }
});

function setIframeHeight(iframe) {
    if(iframe) {
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if(iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
};

$(function () {
    $('.member_menu_ul').on('click', 'li', actionClick);
})

function actionClick() {
    $(this).siblings().removeClass("hover_li");
    $(this).addClass("hover_li");
}

