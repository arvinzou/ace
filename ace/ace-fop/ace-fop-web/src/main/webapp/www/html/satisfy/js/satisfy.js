var pageSize=10;
var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var currentPage = 1;
//angularjs Controller初始化
var app =angular.module(ngAppName, []);
var myChart1, myChart2;


// 使用刚指定的配置项和数据显示图表。


//  var drawCircle = function(canvasId, data_arr, color_arr, text_arr){
//             var drawing = document.getElementById(canvasId);
//             if(drawing.getContext) {
//                 var context = drawing.getContext('2d');
//                 var radius = drawing.height/2 -20,//半径
//                     ox = radius +20, oy = radius +20;//圆心
//                 var width = 15, height = 10, //图例宽高
//                     posX = ox * 2 +20, posY = 30;//图例位置
//                 var textX = posX + width + 5, textY = posY + 10;//文本位置
//                 var startAngle = 0, endAngle = 0;//起始、结束弧度
//                /* context.strokeStyle = 'Purple';
//                 context.lineWidth = 3*/;
// //              context.strokeRect(0, 0, drawing.width, drawing.height);
//                 for(var i=0, len=data_arr.length; i<len; i++) {
//                     //绘制饼图
//                     endAngle += data_arr[i] * 2*Math.PI;
//                     context.fillStyle = color_arr[i];
//                     context.beginPath();
//                     context.moveTo(ox, oy);
//                     context.arc(ox, oy, radius, startAngle, endAngle, false);
//                     context.closePath();
//                     context.fill();
//                     startAngle = endAngle;
//                     //绘制图例
//                     context.fillRect(posX, posY + 20 * i, width, height);
//                     context.moveTo(posX, posY + 20 * i);
//                     context.font = 'bold 12px Arial';
//                     var percent = text_arr[i] + ' : ' + data_arr[i]*100 + '%';
//                     context.fillText(percent, textX, textY + 20 * i);
//                 }
//
//             }
//     };
app.controller(ngControllerName,function($scope) {

    try{
        $scope.userProp = userProp;
    }catch(e){}

    /**
     * 类型列表
     * @type {string}
     */
    $scope.defaultType = "全部";
    $scope.selectType = [{id: "", name: "全部"}, {id: "1", name: "在线诉求"}, {id: "2", name: "平台服务"}];

    //意见列表
    $.ajax({
        url: "/fop/www/findQuestionnaireResultList",
        type: "post",
        async: false,
        data: {"limit": pageSize, page: currentPage},
        success: function (result) {
            if (result.status == 0) {
                $scope.items = result.data.list;
                if (!$scope.$$phase) {
                    $scope.$apply();
                }
                var totalSize = result.data.total;
                var totalPage;
                if (totalSize % pageSize == 0) {
                    totalPage = totalSize / pageSize;
                } else {
                    totalPage = totalSize / pageSize + 1;
                }
                laypage({
                    cont: $("#paganation"),   //容器名
                    pages: totalPage,           //总页数
                    curr: currentPage,         //当前页
                   // skip: true,
                    skin: '#1A56A8',
                    groups: 5,                  //连续显示分页数
                    jump: function (obj, first) { //触发分页后的回调
                        if (!first) {
                            currentPage = obj.curr;
                            $scope.searchList(currentPage, pageSize);
                        }
                    }

                });
            } else {
                layer.alert(result.errorMessage, {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        },
        error: function () {
            layer.alert("系统内部服务异常！", {
                icon: 5,
                skin: 'myskin'
            });
        }
    });

    // //统计意见图表
    // $.ajax({
    //     url: "/fop/www/statisticalData",
    //     type: "post",
    //     async: false,
    //     data: {},
    //     success: function (result) {
    //         if (result.status == 0) {
    //             console.log(result);
    //             var text_suqiu = [];
    //             var data_suqiu = [];
    //             var color_suqiu = ['#1D60BC', '#4167E2', '#5C7DEE'];
    //             var suqiu_list = result.data.suqiu;
    //             var hezuo_list = result.data.hezuo;
    //             var suqiu_sum = 0;
    //             for (var i = 0; i < suqiu_list.length; i++) {
    //                 suqiu_sum += suqiu_list[i].count;
    //                 text_suqiu[i] = suqiu_list[i].result;
    //             }
    //             for (var i = 0; i < suqiu_list.length; i++) {
    //                 if (suqiu_sum > 0) {
    //                     data_suqiu[i] = ((suqiu_list[i].count) / suqiu_sum).toFixed(3);
    //                 }
    //             }
    //             drawCircle('appeal', data_suqiu, color_suqiu, text_suqiu);
    //
    //             var text_hezuo = [];
    //             var data_hezuo = [];
    //             var color_hezuo = ['#FF9F00', '#FA8C35', '#FFBC4C'];
    //             var hezuo_sum = 0;
    //             for (var i = 0; i < hezuo_list.length; i++) {
    //                 hezuo_sum += hezuo_list[i].count;
    //                 text_hezuo[i] = hezuo_list[i].result;
    //             }
    //             for (var i = 0; i < hezuo_list.length; i++) {
    //                 if (hezuo_sum > 0) {
    //                     data_hezuo[i] = ((hezuo_list[i].count) / hezuo_sum).toFixed(2);
    //                 }
    //             }
    //             drawCircle('react', data_hezuo, color_hezuo, text_hezuo);
    //             if (!$scope.$$phase) {
    //                 $scope.$apply();
    //             }
    //         } else {
    //             layer.alert(result.errorMessage, {
    //                 icon: 5,
    //                 skin: 'myskin'
    //             });
    //         }
    //     },
    //     error: function () {
    //         layer.alert("系统内部服务异常！", {
    //             icon: 5,
    //             skin: 'myskin'
    //         });
    //     }
    // });

    // $scope.initChart = function(){
    //     $.ajax({
    //         url: "/fop/www/statisticalData",
    //         type: "post",
    //         async: false,
    //         data: {},
    //         success: function (result) {
    //             if (result.status == 0) {
    //                 console.log(result);
    //                 var text_suqiu = [];
    //                 var data_suqiu = [];
    //                 var color_suqiu = ['#1D60BC', '#4167E2', '#5C7DEE'];
    //                 var suqiu_list = result.data.suqiu;
    //                 var hezuo_list = result.data.hezuo;
    //                 var suqiu_sum = 0;
    //                 for (var i = 0; i < suqiu_list.length; i++) {
    //                     suqiu_sum += suqiu_list[i].count;
    //                     text_suqiu[i] = suqiu_list[i].result;
    //                 }
    //                 for (var i = 0; i < suqiu_list.length; i++) {
    //                     if (suqiu_sum > 0) {
    //                         data_suqiu[i] = ((suqiu_list[i].count) / suqiu_sum).toFixed(3);
    //                     }
    //                 }
    //                 drawCircle('appeal', data_suqiu, color_suqiu, text_suqiu);
    //
    //                 var text_hezuo = [];
    //                 var data_hezuo = [];
    //                 var color_hezuo = ['#FF9F00', '#FA8C35', '#FFBC4C'];
    //                 var hezuo_sum = 0;
    //                 for (var i = 0; i < hezuo_list.length; i++) {
    //                     hezuo_sum += hezuo_list[i].count;
    //                     text_hezuo[i] = hezuo_list[i].result;
    //                 }
    //                 for (var i = 0; i < hezuo_list.length; i++) {
    //                     if (hezuo_sum > 0) {
    //                         data_hezuo[i] = ((hezuo_list[i].count) / hezuo_sum).toFixed(2);
    //                     }
    //                 }
    //                 drawCircle('react', data_hezuo, color_hezuo, text_hezuo);
    //                 if (!$scope.$$phase) {
    //                     $scope.$apply();
    //                 }
    //             } else {
    //                 layer.alert(result.errorMessage, {
    //                     icon: 5,
    //                     skin: 'myskin'
    //                 });
    //             }
    //         },
    //         error: function () {
    //             layer.alert("系统内部服务异常！", {
    //                 icon: 5,
    //                 skin: 'myskin'
    //             });
    //         }
    //     });
    // }

    $scope.searchList = function (currentPage, pageSize) {
        var key_word = $("#key_word").val();
        $.ajax({
            url: "/fop/www/findQuestionnaireResultList",
            type: "post",
            async: false,
            data: {page: currentPage, limit: pageSize},
            success: $scope.responseHandle,
            error: function () {
                layer.alert("系统内部服务异常！", {
                    icon: 5,
                    skin: 'myskin'
                });
            }
        });
    }

    $scope.responseHandle = function (result) {
        if (result.status == 0) {
            $scope.items = result.data.list;
            if (!$scope.$$phase) {
                $scope.$apply();
            }
        } else {
            layer.alert(result.errorMessage, {
                icon: 5,
                skin: 'myskin'
            });
        }
    }

    /**
     * 改变下拉框的值，查询
     */
    $scope.change = function () {
        var opinionType = $("#list_type option:checked").val();
        $.ajax({
            url: "/fop/www/findQuestionnaireResultList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage, opinionType: opinionType},
            success: function (result) {
                if (result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    var totalSize = result.data.total;
                    var totalPage;
                    if (totalSize % pageSize == 0) {
                        totalPage = totalSize / pageSize;
                    } else {
                        totalPage = totalSize / pageSize + 1;
                    }
                    laypage({
                        cont: $("#paganation"),   //容器名
                        pages: totalPage,           //总页数
                        curr: currentPage,         //当前页
                        //skip: true,
                        skin: '#1A56A8',
                        groups: 5,                  //连续显示分页数
                        jump: function (obj, first) { //触发分页后的回调
                            if (!first) {
                                currentPage = obj.curr;
                                $scope.searchList(currentPage, pageSize);
                            }
                        }

                    });
                } else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
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

    $scope.search = function(){
        $.ajax({
            url: "/fop/www/findQuestionnaireResultList",
            type: "post",
            async: false,
            data: {limit: pageSize, page: currentPage},
            success: function (result) {
                if (result.status == 0) {
                    $scope.items = result.data.list;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    var totalSize = result.data.total;
                    var totalPage;
                    if (totalSize % pageSize == 0) {
                        totalPage = totalSize / pageSize;
                    } else {
                        totalPage = totalSize / pageSize + 1;
                    }
                    laypage({
                        cont: $("#paganation"),   //容器名
                        pages: totalPage,           //总页数
                        curr: currentPage,         //当前页
                       // skip: true,
                        skin: '#1A56A8',
                        groups: 5,                  //连续显示分页数
                        jump: function (obj, first) { //触发分页后的回调
                            if (!first) {
                                currentPage = obj.curr;
                                $scope.searchList(currentPage, pageSize);
                            }
                        }

                    });
                } else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
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

    /**
     * 发布之前判断是否已经登录
     */
    $scope.before_release = function ($event, div) {
        var userProp = parent.parent.userProp;
        if (userProp == null || userProp == ''){
            layer.alert("请先登录后再发布！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userStatus != '2'){
            //非会员也不能发布
            layer.alert("对不起，您还不是会员，请先完善信息！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else if(userTypes == '3'){ //3代表银行，不能发表
            layer.alert("对不起，您属于银行企业类型，不能发表！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }else{
            $event.target.dataset.target=div;
        }
    }

    /**
     * 在线诉求评价
     */
    $scope.insertSatify_1 = function(){
            var satifyFlag = $("input[name='radio1']:checked").val();
            var content = $("textarea[name='content1']").val();
            if(satifyFlag == '' || satifyFlag == undefined){
                flag = false;
                layer.alert("诉求服务满意度不能为空！", {
                    icon: 5,
                    skin: 'myskin'
                });
                return;
            }
            $.ajax({
                    url: "/fop/www/insertQuestionnaireResult",
                    type: "post",
                    async: false,
                    data: {opinionType:"1", result: satifyFlag, content: content},
                    success: function (result) {
                        if (result.status == 0) {
                            layer.alert("评价成功！", {
                                icon: 1,
                                skin: 'myskin'
                            });
                            $scope.search();
                            //$scope.initChart();
                            $("#myModal_1").modal('hide');
                            if (!$scope.$$phase) {
                                $scope.$apply();
                            }
                            clearForm();
                        } else {
                            layer.alert(result.errorMessage, {
                                icon: 5,
                                skin: 'myskin'
                            });
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

    /**
     * 平台服务评价
     */
    $scope.insertSatify_2 = function(){
        var satifyFlag = $("input[name='radio2']:checked").val();
        var content = $("textarea[name='content2']").val();
        if(satifyFlag == '' || satifyFlag == undefined){
            flag = false;
            layer.alert("诉求服务满意度不能为空！", {
                icon: 5,
                skin: 'myskin'
            });
            return;
        }
        $.ajax({
            url: "/fop/www/insertQuestionnaireResult",
            type: "post",
            async: false,
            data: {opinionType:"2", result: satifyFlag, content: content},
            success: function (result) {
                if (result.status == 0) {
                    layer.alert("评价成功！", {
                        icon: 1,
                        skin: 'myskin'
                    });
                    $scope.search();
                    //$scope.initChart();
                    $("#myModal_2").modal('hide');
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                    clearForm();
                } else {
                    layer.alert(result.errorMessage, {
                        icon: 5,
                        skin: 'myskin'
                    });
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
});

app.filter('formatDate', function() { //可以注入依赖
    return function(text) {
        if(text.length>10){
            return text.substring(0,10);
        }else{
            return text;
        }
    }
});


$(function () {
    myChart1 = echarts.init(document.getElementById('appeal'));
    myChart2 = echarts.init(document.getElementById('react'));
    myChart1.setOption(option1);
    myChart2.setOption(option2);
    viewChart();
})


var option1 = {
    title: {
        text: '在线诉求满意度',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{b} : ({d}%)"
    },
    legend: {
        // orient: 'vertical',
        // top: 'middle',
        bottom: 10,
        left: 'center',
        data: ['很满意', '一般', '不满意']
    },
    series: [
        {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
            ],
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList = [
                            '#4167E2', '#1D60BC', '#5C7DEE'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            },
        }
    ]
};


var option2 = {
    title: {
        text: '平台服务满意度',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{b} : ({d}%)"
    },
    legend: {
        // orient: 'vertical',
        // top: 'middle',
        bottom: 10,
        left: 'center',
        data: ['很满意', '一般', '不满意']
    },
    series: [
        {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [],
            itemStyle: {
                normal: {
                    color: function (params) {
                        var colorList = [
                            '#FF9F00', '#FA8C35', '#FFBC4C'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            },
        }
    ]
};



function viewChart() {
    var url = "/fop/www/statisticalData";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            option1.series[0].data = result.data.suqiu;
            option2.series[0].data = result.data.hezuo;
            myChart1.setOption(option1);
            myChart2.setOption(option2);
        }
    })
}
