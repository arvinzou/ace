var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var project = [];
var projectId = null;

app.controller(ngControllerName,function($scope){

    /**
	 * 查询资金总额
     */
    $.ajax({
        url: "/cu/www/report/financeStatistics",
        type:"post",
        async:false,
        data:{},
        success:function(result){
            if(result.status == 0) {
                $scope.staticsTotal = result.data.totalAmout;
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
	 * 支出资金
     */
    $.ajax({
        url: "/cu/www/report/findUsedRecord",
        type:"post",
        async:false,
        data:{start:0, limit: 999999},
        success:function(result){
            if(result.status == 0) {
                $scope.recordList = result.data.rows;
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
	 * 募集资金
     */
    $.ajax({
        url: "/cu/www/report/findDonateList",
        type:"post",
        async:false,
        data:{start:0, limit: 999999},
        success:function(result){
            if(result.status == 0) {
                $scope.donateList = result.data.rows;
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
     * 项目列表
     * @type {string[]}
     */
    $.ajax({
        url: "/cu/www/report/projectList",
        type:"post",
        async:false,
        data:{start:0, limit: 999999},
        success:function(result){
            if(result.status == 0) {
                var datas = result.data;
                var item = {id : "", value: ""};
                for(var i=0; i<datas.length; i++){
                    item.id = datas[i].id;
                    item.value = datas[i].projectName;
                    project.push(item);
                    item = {id : "", value: ""};
                }
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

    var mobileSelect1 = new MobileSelect({
        trigger: '#trigger1',
        title: '选择项目',
        wheels: [
            {data: project}
        ],
        position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd:function(indexArr, data){
            //console.log(data);
        },
        callback:function(indexArr, data){
            console.log(data[0].value);
            projectId = data[0].id;
            $.ajax({
                url: "/cu/www/report/financeStatistics",
                type:"post",
                async:false,
                data:{projectId: projectId},
                success:function(result){
                    if(result.status == 0) {
                        $scope.staticsTotal = result.data.totalAmout;
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
             * 支出资金
             */
            $.ajax({
                url: "/cu/www/report/findUsedRecord",
                type:"post",
                async:false,
                data:{start:0, limit: 999999, projectId: projectId},
                success:function(result){
                    if(result.status == 0) {
                        $scope.recordList = result.data.rows;
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
             * 募集资金
             */
            $.ajax({
                url: "/cu/www/report/findDonateList",
                type:"post",
                async:false,
                data:{start:0, limit: 999999, projectId: projectId},
                success:function(result){
                    if(result.status == 0) {
                        $scope.donateList = result.data.rows;
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

        }
    });
});
app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
    	if(text!=null && text.length > 10){
            return text.substring(0,10);
		}
        return text;
    }
});
function hoverli(divId){
	$("#"+divId).removeClass('undis').addClass('dis');
	$("#"+divId).siblings().removeClass('dis').addClass('undis');
}
