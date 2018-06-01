var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var index = 0;

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope) {
    /**
     * 查询公告栏信息
     */
    $scope.shares = shareJson.slice(0,10);
    $.ajax({
        url: "/fop/www/homepageNoticeList",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            if (result.status == 0) {
                $scope.notice_slices = result.data.top1;
                $scope.notice_list = result.data.top0
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
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    /**
     *  查询品牌推广信息
     */
    $.ajax({
        url: "/fop/www/findInformationServiceListDo",
        type: "post",
        async: false,
        data: {modules : "6", page: 1, limit: 4},  //首页只展示4条信息
        success: function (result) {
            if (result.status == 0) {
                $scope.bands = result.data.list;
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
            layer.alert("系统服务内部异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    $scope.next = function(){
        index = index + 10;
        if(index <30){
            $scope.shares = shareJson.slice(index, index+10);
        }else{
            index = index - 10;
        }
    }
    $scope.pre = function(){
        index = index -10;
        if(index >= 0){
            $scope.shares = shareJson.slice(index, index+10);
        }else{
            index = index +10;
        }
    }

    $scope.showBrand = function(index){
        var primaryId = $scope.bands[index].id;
        console.log(primaryId);
        window.open('html/band/band_info.html?id='+primaryId);
    }

    $scope.showInfo = function(index){
        var primaryId = $scope.notice_list[index].id;
        console.log(primaryId);
        window.open('html/information/information_info.html?id='+primaryId);
    }
    $scope.showBannerInfo = function(index){
        var primaryId = $scope.notice_slices[index].id;
        console.log(primaryId);
        window.open('html/information/information_info.html?id='+primaryId);
    }
});

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        return text.substring(0,10);
    }
});