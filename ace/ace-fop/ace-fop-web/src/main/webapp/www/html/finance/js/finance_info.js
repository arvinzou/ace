var pageSize=5;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){
    console.log(window.location.search);
    console.log(url);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
        $.ajax({
            url: "/fop/www/selectFinanceProjectByPrimaryKey",
            type:"post",
            async:false,
            data:{id: primaryId},
            success:function(result){
                if(result.status == 0) {
                    $scope.financeInfo = result.data;
                    $scope.comments = result.data.comments;
                    $(".content_info").html(result.data.financeContent);
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    if($(".custom_layper_page_info").length>0){
                        setTimeout(function(){
                            var itemSize=$(".msg_list table").length;//直接从html取出已经渲染的tr
                            var pages = Math.ceil(itemSize/pageSize); //得到总页数
                            $(".custom_layper_page_info").html(pageSize+"条/页，共"+itemSize+"条信息");
                            laypage({
                                cont: 'layper_page_box',
                                pages: pages,
                                first: false,
                                last: false,
                                skip:false,
                                groups: 8, //连续显示分页数
                                jump: function(obj){
                                    staticPages(obj.curr);
                                }
                            });
                            staticPages(1);//静态分页
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
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    function staticPages(curr){
        $(".msg_list table").each(function(index){
                var startNo=parseInt(pageSize)*(curr-1)+1;//5
                var endNo=parseInt(pageSize)*curr;//8
                if((index+1)>=parseInt(startNo) && (index+1) <=parseInt(endNo)){
                    $(this).show();
                }else{
                    $(this).hide();
                }
            });
    }

    $scope.init = function(){
        $.ajax({
            url: "/fop/www/selectFinanceProjectByPrimaryKey",
            type:"post",
            async:false,
            data:{id: primaryId},
            success:function(result){
                if(result.status == 0) {
                    $scope.financeInfo = result.data;
                    $scope.comments = result.data.comments;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    if($(".custom_layper_page_info").length>0){
                        setTimeout(function(){
                            var itemSize=$(".msg_list table").length;//直接从html取出已经渲染的tr
                            var pages = Math.ceil(itemSize/pageSize); //得到总页数
                            $(".custom_layper_page_info").html(pageSize+"条/页，共"+itemSize+"条信息");
                            laypage({
                                cont: 'layper_page_box',
                                pages: pages,
                                first: false,
                                last: false,
                                skip:false,
                                groups: 8, //连续显示分页数
                                jump: function(obj){
                                    staticPages(obj.curr);
                                }
                            });
                            staticPages(1);//静态分页
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
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.releaseMsg = function(){
        var userProp = parent.parent.userProp;
        if (userProp == null || userProp == ''){
            layer.alert("请先登录后再发布！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userStatus !='2'){
            //非会员也不能发布
            layer.alert("对不起，您还不是会员，请先完善信息！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        var reply = $("#release_msg").val();
        $.ajax({
            url: "/fop/www/insertQuestion",
            type:"post",
            async:false,
            data:{parentId: primaryId, sourceType: "2", reply: reply},
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

    $('#release_msg').on('keyup',function(){
        var txtval = $('#release_msg').val().length;
        console.log(txtval);
        var str = parseInt(300-txtval);
        console.log(str);
        if(str > 0 ){
            $('#num_txt1').html(str);
        }else{
            $('#num_txt1').html('0');
            $('#release_msg').val($('#release_msg').val().substring(0,300)); //这里意思是当里面的文字小于等于0的时候，那么字数不能再增加，只能是600个字
        }
    });
});