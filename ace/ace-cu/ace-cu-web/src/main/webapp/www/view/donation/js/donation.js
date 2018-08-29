var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var recordList = [];
var type = "";
var status = null;
app.controller(ngControllerName,function($scope,$sce){
	var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var primaryId = null;
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "type"){
                type = value;
                $scope.type = type;
            }
            if(name == "projectId"){
                primaryId = value;
            }
        }
    }

    /**
     * 项目详情
     */
    $.ajax({
        url: "/cu/www/project/findDetail",
        type:"post",
        async:false,
        data:{projectId: primaryId},
        success:function(result){
            if(result.status == 0) {
                $scope.projectInfo = result.data;
                $("#projectDetail").html(result.data.description);
                var htmlTemp = '<div class="swiper-slide">\n' +
                                        '<video src="http://zx.huacainfo.com/group1/M00/00/3A/i-AA41tzpyaAN4zfABnwZ2Vdppo235.mp4?filename=5a447a704d316aaea58a4a313c16cc40.mp4" poster="img/cover.png" style="width: 100%;height: 100%;object-fit:fill"  autoplay="autoplay" x-webkit-airplay="true" webkit-playsinline="true" airplay="allow" playsinline="allow"  x5-video-player-type="h5" preload="auto" controls>\n' +
                                             '您的设备不支持video。\n' +
                                        '</video>\n' +
                                '</div>\n' +
                                '<div class="swiper-slide">\n' +
                                    '<img class="slideImg" src="'+result.data.carousel[1].resUrl+'" style="width: 100%;height: 100%;">\n' +
                                '</div>\n' +
                                '<div class="swiper-slide">\n' +
                                    '<img class="slideImg" src="'+result.data.carousel[2].resUrl+'" style="width: 100%;height: 100%;">\n' +
                                '</div>\n' +
                                '<div class="swiper-slide">\n' +
                                    '<img class="slideImg" src="'+result.data.carousel[3].resUrl+'" style="width: 100%;height: 100%;">\n' +
                                '</div>';
                $("#swiperBox").html(htmlTemp);
                var mySwiper = new Swiper('.swiper-container',{
                    pagination : '.swiper-pagination',
                    paginationClickable: true,
                    longSwipesRatio: 0.3,
                    touchRatio:1,
                    observer:true,//修改swiper自己或子元素时，自动初始化swiper
                    observeParents:true,//修改swiper的父元素时，自动初始化swiper
                });

                status = result.data.status;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    
    /**
     * 使用记录列表
     */
    var flag = false;
    $.ajax({
        url: "/cu/www/project/findUsedRecordList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                $scope.useRecords = result.data.rows;
                recordList = result.data.rows;
                $scope.totalProject = result.data.total;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                flag = true;
            }else {
                alert(errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });


   /**
     * 捐赠列表
     */
    $.ajax({
        url: "/cu/www/project/findDonateList",
        type:"post",
        async:false,
        data:{projectId: primaryId, start: 0, limit: 9999},
        success:function(result){
            if(result.status == 0) {
                $scope.donationList = result.data.rows;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
            }else {
                alert(result.info);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        for(var i=0; i< recordList.length; i++){
            console.log("record"+i);
            $("#record"+i).html(recordList[i].useToProjectDesc);
        }
    });

    $scope.donate = function(){
        if(status != '2'){
            alert("该项目未审核通过！")
        }else{
            window.location.href = '/cu/www/view/order/order.html?projectId='+primaryId;
        }
    }

    $scope.videoUrlFun = function(url){
        //$sce.trustAsResourceUrl方法把普通路径处理加工成一个angular环境可识别，并认为是安全的路径来使用
        var urlFun = $sce.trustAsResourceUrl(url);
        return urlFun;
    };
});
app.filter('to_trusted', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }
);
//自定义指令repeatFinish
app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});
app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        return text.substring(0,10);
    }
});

function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}

function troggle(obj){
    if($(obj).attr("name") == "down"){   //down展开，up收起
        $(obj).parent().siblings(".project_record_info").removeClass("troggle");
        $(obj).html("<span class=\"opt\">收起</span><img src=\"img/up.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "up");
    }else{
        $(obj).parent().siblings(".project_record_info").addClass("troggle");
        $(obj).html("<span class=\"opt\">展开</span><img  src=\"img/down.png\" style=\"width: 0.3rem;height: 0.2rem;\">");
        $(obj).attr("name", "down");
    }
}
