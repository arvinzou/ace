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
        url: "/fop/www/selectProjectByPrimaryKey",
        type:"post",
        async:false,
        data:{id: primaryId},
        success:function(result){
            if(result.status == 0) {
                $scope.projectInfo = result.data;
                //$scope.comments = result.data.comments;
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
                alert(result.info);
            }
        },
        error:function(){
            alert("内部服务异常");
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
});