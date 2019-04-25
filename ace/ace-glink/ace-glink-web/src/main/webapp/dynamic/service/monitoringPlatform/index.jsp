<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>监控平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<style>

</style>
   <div class="jkpt-container-wrap">
       <div class="jkpt-header-wrap"></div>
       <div class="jkpt-main-wrap">
           <div class="left">
               <ul class="shebei-ul">
                   <li>
                       <img src="./img/icon-jzwzs@2x.png">
                       <div class="right">
                           <i>2,346</i>
                           <span>建筑物总数</span>
                       </div>
                   </li>
                   <li>
                       <img src="./img/icon-router@2x.png">
                       <div class="right">
                           <i>1,056</i>
                           <span>路由器离线数</span><span>总设备数&nbsp;6,846</span>
                       </div>
                   </li>
                   <li>
                       <img src="./img/icon-wgsl@2x.png">
                       <div class="right">
                           <i>106</i>
                           <span>网关离线数</span><span>总设备数&nbsp;6,846</span>
                       </div>
                   </li>
                   <li>
                       <img src="./img/icon-qdlxs@2x.png">
                       <div class="right">
                           <i>8</i>
                           <span>强电离线数</span><span>总设备数&nbsp;6,846</span>
                       </div>
                   </li>
               </ul>
               <div class="area-wrap">
                   <div class="title">江汉区</div>
                   <div class="time-wrap">
                       <div class="shijian">
                           <%--<span>开始时间</span>--%>
                           <%--<input id="p-startDt" type="text" size="16" name="startDate" readonly="" class="form-control">--%>
                       </div>
                       <div class="dianliang">
                           <span>总耗电量（kwh）</span>
                           <ul class="num-ul">
                               <li>0</li>
                               <li>0</li>
                               <li>0</li>
                               <li class="active">6</li>
                               <li class="active">8</li>
                               <li class="active">0</li>
                               <li class="active">0</li>
                               <li class="active">0</li>
                           </ul>
                       </div>
                       <div class="dianfei">
                           <div class="left">
                               <span>电费单价</span>
                               <div class="input-wrap">
                                   <i>¥</i>
                                   <input value="0.0">
                                   <span>/kwh</span>
                               </div>
                           </div>
                           <div class="empty-wrap"></div>
                           <div class="right">
                               <span>电费</span>
                               <div class="input-wrap">
                                   <i>¥</i>
                                   <input value="0.0" readonly>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
           <div class="right">
               <div class="up">
                   <div class="dianya-wrap">
                       <span class="title">万达广场节点信息</span>
                       <div id="jiedianhaodl" ></div>
                   </div>
                   <div class="wendu-wrap">
                       <div class="canvas-tu"  id="wendu"></div>
                       <div class="canvas-tu"  id="shidu"></div>
                   </div>
                   <div class="shebei-status-wrap">
                       <select class="form-control">
                           <option value="0">万达广场</option>
                           <option value="1">水星楼</option>
                       </select>
                       <ul class="shebei-status-ul">
                           <li>
                               <img src="./img/shebei-router@2x.png">
                               <div class="right">
                                   <i>路由器状态</i>
                                   <div class="line zaixian">
                                       <span>在线</span><img src="./img/signal-4.png">
                                   </div>
                               </div>
                           </li>
                           <li>
                               <img src="./img/shebei-wg@2x.png">
                               <div class="right">
                                   <i>网关状态</i>
                                   <div class="line lixian"><span>离线</span></div>
                               </div>
                           </li>
                       </ul>
                   </div>
               </div>
               <div class="down">
                   <div class="guzhangjiankong-wrap" >
                       <div class="up">
                           <span>故障监控</span>
                           <select  id="year" onchange="ErrorChart();">
                               <option value="2019">2019</option>
                               <option value="2018">2018</option>
                           </select>
                           <select  id="month" onchange="ErrorChart();">
                               <option value="04">04</option>
                               <option value="01">01</option>
                               <option value="02">02</option>
                               <option value="03">03</option>
                               <option value="05">05</option>
                               <option value="06">06</option>
                               <option value="07">07</option>
                               <option value="08">08</option>
                               <option value="09">09</option>
                               <option value="10">10</option>
                               <option value="11">11</option>
                               <option value="12">12</option>
                           </select>
                       </div>
                       <div id="guzhangjiankong"></div>
                   </div>
                   <div class="baojingqianshi-wrap">
                       <div class="baojingqianshi-title">
                           <span class="left">故障报警TOP10<i>（故障时间倒叙）</i></span>
                           <a href="javacript:;" class="right">More <img src="./img/icon-more@2x.png"></a>
                       </div>
                       <table class="table   gzjb-table">
                           <thead >
                               <tr>
                                   <th width="11.6%" style="text-align: center;">故障等级</th>
                                   <th width="18.2%" style="text-align: center;">设备名称</th>
                                   <th width="20.2%" style="text-align: center;">故障位置</th>
                                   <th width="20.2%" style="text-align: center;">故障类型</th>
                                   <th width="12.6%" style="text-align: center;">故障数量</th>
                                   <th width="16.6%" style="text-align: center;">故障时间</th>
                               </tr>
                           </thead>
                           <tbody id="top10-list">
                               <tr >
                                   <td><i class="circle grade-red"></i></td>
                                   <td>设备名称1</td>
                                   <td>江汉区</td>
                                   <td>故障类型</td>
                                   <td>10</td>
                                   <td>03.01-15:30:04</td>
                               </tr>
                               <tr >
                                   <td><i class="circle grade-orange"></i></td>
                                   <td>设备名称1</td>
                                   <td>江汉区</td>
                                   <td>故障类型</td>
                                   <td>10</td>
                                   <td>03.01-15:30:04</td>
                               </tr>
                               <tr >
                                   <td><i class="circle grade-yellow"></i></td>
                                   <td>设备名称1</td>
                                   <td>江汉区</td>
                                   <td>故障类型</td>
                                   <td>10</td>
                                   <td>03.01-15:30:04</td>
                               </tr>
                           </tbody>
                       </table>
                   </div>
               </div>
           </div>
       </div>
   </div>
<script id="top10-tpl" type="text/template">
    {@each data as item, index}
    <tr>
        <td >
            {@if item.errLevel == '03'}
            <div class="serious"></div>
            {@else if item.errLevel == '02'}
            <div class="more-serious"></div>
            {@else if item.errLevel == '01'}
            <div class="less-serious"></div>
            {@/if}
        </td>
        <td >\${item.deviceName}</td>
        <td >\${item.subareaName}</td>
        <td >\${item.errContent}</td>
        <td>\${item.errLoopNum}</td>
        <td >\${item.errDate}</td>
    </tr>
    {@/each}
</script>

<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/echarts.js"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

<script>
    $(function () {
        var wendu = new canvasPanel();
        wendu.bgColor = '#FF7B57';
        wendu.MaxNum = 120;
        wendu.MinNum = -10;
        wendu.current = 40;
        wendu.title = '温度值';
        wendu.init('wendu');
        var shidu = new canvasPanel();
        shidu.bgColor = '#00FFFF';
        shidu.danwei = "%";
        shidu.MaxNum = 100;
        shidu.MinNum = 0;
        shidu.current = 100;
        shidu.title = '湿度值';
        shidu.init('shidu');
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('jiedianhaodl')); //节点耗电chart图
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '节点耗电量', //标题文本内容
            x:'center',
            y:'bottom',
            textStyle: {//主标题文本样式{"fontSize": 18,"fontWeight": "bolder","color": "#333"}
                fontFamily: 'Arial',
                fontSize: 18,
                color:'#00D6D9',
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
        toolbox: { //可视化的工具箱
            show: true,
            feature: {
                restore: { //重置
                    show: false
                },
                saveAsImage: {//保存图片
                    show: false
                }
            }
        },
        tooltip: { //弹窗组件
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [{
            name: '节点耗电量',
            type: 'gauge',
            center: ["50%", "54%"], // 仪表位置
            radius: '92%', //仪表盘半径
            startAngle:200,  //仪表盘起始角度。圆心 正右手侧为0度，正上方为90度，正左手侧为180度。
            endAngle: -20,
            min:0,
            max:10000,
            axisLine: {
                lineStyle: {
                    width: 14 // 这个是修改宽度的属性
                }
            },
            splitLine:{  //分隔线样式
                show:false
            },
            axisTick:{  //刻度样式
                splitNumber: 2,   //分隔线之间分割的刻度数
                length:26,     //刻度线长。支持相对半径的百分比。
                lineStyle:{   //线的设置。
                    width:5,
                    color:'auto'
                }
            },
            axisLabel:{  //刻度标签
                distance: 0, //标签与刻度线的距离。
                color:'#ffffff',
                fontSize: 9

            },
            pointer: { //仪表盘指针
                length:'46%', //指针长度
                width: 5  //指针宽度。
            },
            itemStyle:{  //指针样式
                color: '#FFF100'
            },
            title: {//仪表盘内标题(即data下value值的单位"km/h")
                offsetCenter:['52%', '50%'],//相对于仪表盘中心的偏移位置
                textStyle: {
                    fontWeight: 'bolder',
                    fontSize: 16,
                    fontStyle: 'normal',
                    color: '#FFF100',
                    shadowColor: '#fff', //默认透明
                    shadowBlur: 10
                }
            },
            detail: {  //仪表盘详情，用于显示数据。
                offsetCenter: ['-10%', '50%'], //相对于仪表盘中心的偏移位置
                formatter:'{value}',
                fontSize: 30,
                fontWeight: 'bold',
                color: '#FFF100'

            },
            data: [
                {value: 1000, name: '/kmh'}
            ]
        }]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    /**
     * 故障监控统计图表
     * @constructor
     */
    var lineColor = ['#00D6D9','#f8d347','#8174c7','#67b245','#04177a','#d41b30','#078f91','#f49e24','#007aff','#38474f','#aed39d'];
    var echart = echarts.init(document.getElementById("guzhangjiankong"));
    var option = {
        title: {                             //标题
            text: ''
        },
        tooltip : {                          //提示框
            trigger: 'axis'                   //坐标轴类型
        },
        legend: {                            //图例
            show:false
        },
        calculable : true,
        grid: {                             //直角坐标系内绘图网格,top,left.. 离容器的距离设置
            top:'20%',
            left: '1%',
            right: '4%',
            bottom: '0%',
            containLabel: true               //是否显示刻度标签
        },
        xAxis : [
            {
                name:'',
                type : 'category',            //类目轴，必须设置data
                boundaryGap : false,          //是否显示在刻度中间
                splitLine:{ show:false },        //是否显示网格
                data: ['0301','0302','0303'],
                axisLabel:{
                    //X轴刻度配置 0：表示全部显示不间隔
                    interval:0
                },
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#00D6D9',//左边线的颜色
                        // width:'2'//坐标线的宽度
                    }
                },

                axisLabel: {
                    textStyle: {
                        color: '#00D6D9 '//坐标值得具体的颜色
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',               //数值轴
                name:'',
                splitLine:{show: false},//去除网格线
                splitArea : {show : false},//保留网格区域
                axisLabel : {
                    formatter: '{value}'
                },
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#00D6D9',//左边线的颜色
                        // width:'2'//坐标线的宽度
                    }
                },

                axisLabel: {
                    textStyle: {
                        color: '#00D6D9 ',//坐标值得具体的颜色

                    }
                }
            }
        ],
        series : [
            {
                name:'大户',
                type:'line',
                smooth:true,                                    //曲线是否平滑显示
                areaStyle: { normal: {opacity:0.3} },           //曲线面积透明度
                data:[0, 22,30]    //数据
            }
        ],
        color:lineColor                   //配色
    };
    echart.setOption(option);
    // function ErrorChart() {
    //     var dayList = [];
    //     var dayCountList = [];
    //     startLoad();
    //     var year = $("#year option:selected").val();
    //     var month = $("#month option:selected").val();
    //     $.ajax({
    //         url: contextPath + "/errFeedback/getDayErrCountList",
    //         type: "post",
    //         async: false,
    //         data: {
    //             year: year,
    //             month: month,
    //         },
    //         success: function (rst) {
    //             stopLoad();
    //             if(rst.length > 0){
    //                 for(var i=0; i<rst.length; i++){
    //                     dayList.push(rst[i].errday);
    //                     dayCountList.push(rst[i].totalErrNum);
    //                 }
    //             }
    //         },
    //         error: function () {
    //             stopLoad();
    //             alert("对不起出错了！");
    //         }
    //     });
    //
    //     var myChart = echarts.init(document.getElementById('guzhangjiankong'));
    //     option = {
    //         tooltip: {
    //             trigger: 'axis'
    //         },
    //         legend: {
    //             data: ['故障数目']
    //         },
    //         toolbox: {
    //             show: false,
    //             feature: {
    //                 mark: {show: true},
    //                 dataView: {show: true, readOnly: false},
    //                 magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
    //                 restore: {show: true},
    //                 saveAsImage: {show: true}
    //             }
    //         },
    //         calculable: true,
    //         xAxis: [
    //             {
    //                 type: 'category',
    //                 boundaryGap: false,
    //                 data: dayList
    //             }
    //         ],
    //         yAxis: [
    //             {
    //                 type: 'value'
    //             }
    //         ],
    //         series: [
    //             {
    //                 name: '故障数目',
    //                 type: 'line',
    //                 stack: '总量',
    //                 itemStyle: {normal: {areaStyle: {type: 'default'}}},
    //                 data: dayCountList
    //             }
    //         ]
    //     };
    //     myChart.setOption(option);
    // }
</script>
</body>
</html>
