var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    $("#establishDate").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
    });

    var assId = null;
    $.ajax({
        url: "/fop/www/selectAssociationInfo",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                $scope.information = result.data;
                $scope.group = result.data.list[0];
                assId = result.data.id;
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

    $scope.insertInformation = function(){
        var fullName = $("input[name='fullName']").val();
        var establishDate = $("input[name='establishDate']").val();
        var formatDate = new Date(establishDate.replace(/-/,"/"))
        var phoneNumber = $("input[name='phoneNumber']").val();
        var address = $("input[name='address']").val();
        var directorNum = $("input[name='directorNum']").val();
        var viceNum = $("input[name='viceNum']").val();
        var pname = $("input[name='pname']").val();
        var assPost = $("input[name='assPost']").val();
        var phoneNum = $("input[name='phoneNum']").val();
        var companyName = $("input[name='companyName']").val();
        var latitude = $("#latitude").val();
        var longitude = $("#longitude").val();

        $.ajax({
            url: "/fop/www/insertAssociationInfo",
            type:"post",
            async:false,
            data:{assId: assId,fullName:fullName,establishDate: formatDate, phoneNumber:phoneNumber, address: address, directorNum: directorNum,
                    viceNum :viceNum, pname: pname, assPost: assPost, phoneNum: phoneNum, companyName: companyName, latitude: latitude, longitude: longitude},
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    layer.alert(result.errorMessage, {
                        icon: 1,
                        skin: 'myskin'
                    });
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
    }
});

/**
 * 2、自动填写地址
 * @param latitude
 */
function latitude(latitude) {
    $("#latitude").val(latitude);
}
function longitude(longitude) {
    $("#longitude").val(longitude);
}
function addr(addr) {
    $("#address").val(addr);
}
/** 2、 end */

