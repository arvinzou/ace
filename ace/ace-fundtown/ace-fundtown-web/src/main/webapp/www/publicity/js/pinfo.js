var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

   var deptId = "";
   var type = "";

    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var primaryId = null;
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "deptId"){
                deptId = value;
            }
            if(name == "type"){
                type = value;
            }
        }
    }

    if(deptId){
        if(type == "1"){
            $(".mechan").show();
        }else{
            $(".mechan").hide();
        }
        $.ajax({
            url: "/fundtown/www/info/vipInfo",
            type:"post",
            async:false,
            data:{
                deptId: deptId
            },
            success:function(result){
                if(result.status == 0) {
                    $scope.vipInfo = result.data;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                }else {
                    if(result.errorMessage){
                        alert(result.errorMessage);
                    }else{
                        alert(result.info);
                    }
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }else {
        alert("系统服务内部异常！");
    }
});