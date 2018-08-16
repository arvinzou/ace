var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var type = null;
var keyword = null;
var layerIndex = null;
app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}

    layerIndex = layer.load(1);
    console.log(window.location.search);
    var url =   window.location.search.substring(1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "type"){
                type = value;
            }
            if(name == "keyword"){
                keyword = value;
            }
        }
    }
    console.log("type,keyword",type,keyword);

    $.ajax({
        url: "/fop/www/dataSwapper/search",
        type:"post",
        async:false,
        data:{keyword: keyword, type: type},
        success:function(result){
            var object={};
            var contentObj = {};
            var menu = {"name":"", "value":""};
            var list = [];
            var name = "", value = "";
            if(result.status == 0) {
                var data=result.data;
                for(var key in data){
                    console.log(data[key]);
                    name = key;
                    for(var item in data[key]){
                        console.log(item, data[key][item]);
                        object[key]=item;
                        value = item;
                        contentObj[item] = data[key][item];
                        console.log("object",object);
                    }
                    menu.name = name;
                    menu.value = value;
                    list.push(menu);
                    menu = {"name":"", "value":""};
                }
                $scope.listMenu = object;
                $scope.listContent = contentObj;
                $scope.listData = result.data;
                $scope.list = list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                layer.close(layerIndex);
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

    $scope.hoverli = function(index){
       $(".tbc").eq(index).removeClass("undis").addClass("dis");
        $(".tbc").eq(index).siblings().removeClass("dis").addClass("undis");
    }
});
app.directive('onFinishRender',['$timeout', '$parse', function ($timeout, $parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit('ngRepeatFinished'); //事件通知
                    layer.close(layerIndex);
                    $(".tbc").eq(0).removeClass("undis").addClass("dis");
                });
            }
        }
    }
}]);
function hideshow(obj){
    var tables = $(obj).siblings("table");
    for(var i=0; i<tables.length; i++){
        if(tables[i].style.display=='none'){
            tables[i].style.display='block';
        }else{
            tables[i].style.display='none';
        }
    }

  /*  if($(obj).siblings("table")[0].hidden){
        $(obj).siblings("table").show();
    }else{
        $(obj).siblings("table").hide();
    }*/
}