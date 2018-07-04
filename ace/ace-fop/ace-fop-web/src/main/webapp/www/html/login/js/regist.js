var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var flag = null;      //用来标注是企业用户还是团体用户
var countdown = 50;

app.controller(ngControllerName,function($scope) {

    $scope.changeType = function($event, param){
        var typeDiv = $(".companyType");
        for(var i=0; i<typeDiv.length; i++){
            typeDiv[i].checked  = false;
            $(typeDiv[i]).children("div[name='check']").addClass("default");
        }
        ($event.target).checked = true;
        $($event.target).children("div[name='check']").removeClass("default");
        console.log(param);
        if(param){
            $("#name").text("企业名称");
        }else{
            $("#name").text("团体名称");
        }
        flag = param;
    }

    $scope.resigt = function(){
        var regist_flag = true;
        var regist_name = $("input[name='regist_name']").val();
        var regist_num = $("input[name='regist_num']").val();
        if(flag == null){
            regist_flag = false;
            layer.alert("会员类型未选择！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(regist_name == '' || regist_name == undefined){
            regist_flag = false;
            layer.alert("注册名称不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(regist_num == '' || regist_num == undefined){
            regist_flag = false;
            layer.alert("账号设置不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        if(regist_flag){
            $.ajax({
                url: "/fop/www/sign",
                type: "post",
                async: false,
                data: {name: regist_name, phoneNumber: regist_num, isCompany: flag},
                success: function (result) {
                    if (result.status == 0) {
                       console.log(result);
                        layer.alert("注册成功！", {
                            icon: 1,
                            skin: 'myskin'
                        },function(){
                            location.href = "/portal/dynamic/portal/security/login_fop.jsp";
                        });
                        if (!$scope.$$phase) {
                            $scope.$apply();
                        }
                    } else {
                        if(result.detail){
                            layer.alert(result.detail, {
                                icon: 5,
                                skin: 'myskin'
                            });
                        }else{

                            layer.alert(result.errorMessage, {
                                icon: 5,
                                skin: 'myskin'
                            });
                        }

                    }
                },
                error: function () {
                    layer.alert("系统内部服务异常！", {
                        icon: 5,
                        skin: 'myskin'
                    });
                }
            });
        }
    }

    $scope.getNumFun = function(){
        var mobile = $("input[name = 'regist_num']").val();
        if(mobile == '' || mobile == undefined){
            layer.alert("请输入手机号码！", {
                icon: 5,
                skin: 'myskin'
            });
        }else{
            $.post("/fop/www/sms/sendCode",{mobile:mobile},function(result){
                console.log(result);
                layer.alert(result.info, {
                    icon: 1,
                    skin: 'myskin'
                });
            });
            settime();
        }
    }
});


function settime(){
    var btnName = "获取验证码";
    if (countdown == 0) {
        btnName = "获取验证码";
        countdown = 50;
        stop = true;
    } else {
        stop = false;
        btnName = "重新发送 " + countdown + "";
        countdown--;
    }

    stop = stop;
    countdown = countdown;
    $('#getCode').text(btnName);
    $('#getCode').attr("disabled", true);
    $('#getCode').css({'background-color':'#E1E5ED'},{'color':'#666666'});
    if (!stop) {
        setTimeout(function () {
            this.settime()
        }, 1000)
    }else{
        $('#getCode').attr("disabled", false);
        $('#getCode').css({'background-color':'#1A56A8'},{'color':'#FFFFFF'});
    }
}
