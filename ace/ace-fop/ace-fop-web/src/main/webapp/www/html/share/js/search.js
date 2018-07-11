var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    try{
        $scope.userProp = userProp;
    }catch(e){}
    var flag = false;
    $("#searchBtn").click(function(){
        var layerIndex = layer.load(1);
        var type = $('input:radio[name="radio"]:checked').val();
        var keyword = $("input[name='keyword']").val();

        if(type == '' || type== undefined){
            layer.alert("请选择搜索类型！", {
                icon: 5,
                skin: 'myskin'
            });
            layer.close(layerIndex);
            return;
        }
        if(keyword == '' || keyword == undefined){
            layer.alert("请输入搜索关键字！", {
                icon: 5,
                skin: 'myskin'
            });
            layer.close(layerIndex);
            return;
        }

        $.ajax({
            url: "/fop/www/dataSwapper/search",
            type:"post",
            async:false,
            data:{keyword: keyword, type: type},
            success:function(result){
                var data = result.data;
                if(data != null){
                    flag=true;
                }else{
                    layer.alert("未查询到数据！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                    layer.close(layerIndex);
                    return;
                }
            },
            error:function(){
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
                layer.close(layerIndex);
                return;
            }
        });
        if(flag){
            layer.close(layerIndex);
            window.open('searchRet.html?type='+type+'&keyword='+keyword);
        }
    });
});
function checkParam(obj){
    if($(obj).val() == '0'){
        $("#keyword").attr("placeholder","请输入主体身份代码查询相关信息");
    }else if($(obj).val() == '1'){
        $("#keyword").attr("placeholder","请输入统一社会信用代码查询相关信息");
    }else if($(obj).val() == '2'){
        $("#keyword").attr("placeholder","请输入纳税人识别号查询相关信息");
    }else if($(obj).val() == '3'){
        $("#keyword").attr("placeholder","请输入组织机构代码查询相关信息");
    }
}