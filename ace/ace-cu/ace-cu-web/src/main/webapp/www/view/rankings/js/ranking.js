var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);

app.controller(ngControllerName,function($scope){

    var counter = 0;
    // 每页展示4个
    var num = 20;
    var page = 0;

    // dropload
    var datas = [];
    $('.rank_list').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            $.ajax({
                type: 'post',
                url: '/cu/www/report/donateRank',
                data:{start:counter, limit: num, needOpenId: "2"},
                success: function(result){
                    if(result.data.length < 1){
                        me.lock();
                        // 无数据
                        me.noData();
                        me.resetload();
                    }else{
                        page++;
                        counter = page * num;
                        var temp = result.data;
                        for(var i=0; i<temp.length; i++){
                            datas.push(temp[i]);
                        }
                        $scope.rankList = datas;
                        me.resetload();
                    }
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        }
    });
   /* $.ajax({
        url: "/cu/www/report/donateRank",
        type:"post",
        async:false,
        data:{start:0, limit: 999999, needOpenId: "2"},
        success:function(result){
            if(result.status == 0) {
                $scope.rankList = result.data;
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
    });*/
});