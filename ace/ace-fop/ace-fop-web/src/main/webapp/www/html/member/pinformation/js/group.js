var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";

var app =angular.module(ngAppName, []);
app.controller(ngControllerName,function($scope){

    $scope.insertInformation = function(){
        var fullName = $("input[name='fullName']").val();
        var establishDate = $("input[name='establishDate']").val();
        var phoneNumber = $("input[name='phoneNumber']").val();
        var address = $("input[name='address']").val();
        var directorNum = $("input[name='directorNum']").val();
        var viceNum = $("input[name='viceNum']").val();
        var pname = $("input[name='pname']").val();
        var assPost = $("input[name='assPost']").val();
        var phoneNum = $("input[name='phoneNum']").val();
        var companyName = $("input[name='companyName']").val();

        $.ajax({
            url: "/fop/www/insertAssociationInfo",
            type:"post",
            async:false,
            data:{fullName:fullName, phoneNumber:phoneNumber, address: address, directorNum: directorNum,
                    viceNum :viceNum, pname: pname, assPost: assPost, phoneNum: phoneNum, companyName: companyName},
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
