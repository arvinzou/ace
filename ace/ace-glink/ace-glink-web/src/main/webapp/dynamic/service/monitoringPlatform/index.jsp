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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/zTreeStyle.css">
</head>
<body>
   <div class="jkpt-container-wrap">
       <div class="jkpt-header-wrap">
           <a href="/glink/dynamic/service/gis/index.jsp"></a>
       </div>
       <div class="jkpt-main-wrap">
           <div class="left">
               <ul class="shebei-ul">
                   <li class="buildingTotal">
                       <img src="./img/icon-jzwzs@2x.png">
                       <div class="right">
                           <i></i>
                           <span>建筑物总数</span>
                       </div>
                   </li>
                   <li class="routerOffNum">
                       <img src="./img/icon-router@2x.png">
                       <div class="right">
                           <i></i>
                           <span>路由器离线数</span><span>总设备数&nbsp;<i></i></span>
                       </div>
                   </li>
                   <li class="gatewayOffNum">
                       <img src="./img/icon-wgsl@2x.png">
                       <div class="right">
                           <i></i>
                           <span>网关离线数</span><span>总设备数&nbsp;<i></i></span>
                       </div>
                   </li>
                   <li class="nodeDeviceOffNum">
                       <img src="./img/icon-qdlxs@2x.png">
                       <div class="right">
                           <i></i>
                           <span>模块离线数</span><span>模块总数&nbsp;<i></i></span>
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
                               <%--<li>0</li>--%>
                               <%--<li>0</li>--%>
                               <%--<li>0</li>--%>
                               <%--<li class="active">6</li>--%>
                               <%--<li class="active">8</li>--%>
                               <%--<li class="active">0</li>--%>
                               <%--<li class="active">0</li>--%>
                               <%--<li class="active">0</li>--%>
                           </ul>
                       </div>
                       <div class="dianfei">
                           <div class="left">
                               <span>电费单价</span>
                               <div class="input-wrap">
                                   <i>¥</i>
                                   <input value="0.0"  id="input-unitPrice">
                                   <span>/kwh</span>
                               </div>
                           </div>
                           <div class="empty-wrap"></div>
                           <div class="right">
                               <span>电费</span>
                               <div class="input-wrap">
                                   <i>¥</i>
                                   <input value="0.0" id="input-totalPrice" readonly>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
           <div class="right">
               <div class="up ">
                   <div class="ztree-wrap">
                       <input id="node-name" data-id="1" value="请选择" class="form-control" readonly>
                       <div class="node-tree-container">
                           <ul class="ztree" id="node-name-tree"></ul>
                       </div>
                   </div>
                   <div class="up-data up-no-data">暂无数据，请选择配电箱下拉树</div>
                   <div class="up-data up-have-data" >
                       <div class="dianya-wrap">
                           <span class="title"></span>
                           <div id="jiedianhaodl" ></div>
                       </div>
                       <div class="wendu-wrap">
                           <div class="canvas-tu"  id="wendu"></div>
                           <div class="canvas-tu"  id="shidu"></div>
                       </div>
                       <div class="scene-wrap">
                           <div class="scene-content">
                               <span>当前场景</span>
                               <div class="down">
                                   <img src="">
                                   <span></span>
                               </div>
                           </div>
                       </div>
                       <div class="shebei-status-wrap mokuai-huilu-wrap">
                           <ul class="shebei-status-ul mokuai-huilu-ul ">
                               <li class="modular">
                                   <img src="./img/icon-model-num@2x.png">
                                   <div class="right">
                                       <i>0</i>
                                       <span>模块数量</span>
                                   </div>
                               </li>
                               <li class="loop">
                                   <img src="./img/icon-loop@2x.png">
                                   <div class="right">
                                       <i>0</i>
                                       <span>回路数量</span>
                                   </div>
                               </li>
                           </ul>
                       </div>
                       <div class="shebei-status-wrap">
                           <ul class="shebei-status-ul">
                               <li class="router">
                                   <img src="./img/shebei-router@2x.png">
                                   <div class="right">
                                       <i>路由器状态</i>
                                       <div class="line">
                                           <span>在线</span><img src="./img/signal-4.png">
                                       </div>
                                   </div>
                               </li>
                               <li class="gate">
                                   <img src="./img/shebei-wg@2x.png">
                                   <div class="right">
                                       <i>网关状态</i>
                                       <div class="line "><span>离线</span></div>
                                   </div>
                               </li>
                           </ul>
                       </div>
                   </div>


               </div>
               <div class="down">
                   <div class="guzhangjiankong-wrap" >
                       <div class="up">
                           <span>故障监控</span>
                           <select  id="year" onchange="initFaultMonitoringData();">

                           </select>
                           <select  id="month" onchange="initFaultMonitoringData();">
                               <option value="01">01</option>
                               <option value="02">02</option>
                               <option value="03">03</option>
                               <option value="04">04</option>
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
                       <table class="table   gzjb-table">
                           <thead >
                               <tr>
                                   <th width="13.6%" style="text-align: center;">灯组编号</th>
                                   <th width="20.2%" style="text-align: center;">通道编号</th>
                                   <th width="20.2%" style="text-align: center;">控制器名称</th>
                                   <th width="20.2%" style="text-align: center;">故障位置</th>
                                   <th width="20.6%" style="text-align: center;">故障时间</th>
                               </tr>
                           </thead>
                           <tbody id="top10-list">

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
        <td >\${item.lampNo}</td>
        <td >\${item.channelNo}</td>
        <td >\${item.ctrlName}</td>
        <td >\${item.buildingName}</td>
        <td >\${item.checkDate}</td>
    </tr>
    {@/each}
</script>

<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/echarts.js"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script><script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jquery.ztree.core.min.js"></script>
   <script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

<script>
    $(function () {
        initLeftData();   //初始化左边屏幕数据
        initFaultMonitoringTime(); //初始化故障监控年月
        initFaultMonitoringData();   //初始化故障监控
        initWeakCurrentFaultData();   //初始化弱电故障数据
        initZTreeData();   //初始化树数据
        // initSelectTreeData();  //初始化下拉树选中的配电箱数据
    });
    /*
    *  初始化左边屏幕数据
    */
    function initLeftData() {
        $.ajax({
            url: contextPath + "/anslysis/screenData",
            type: "post",
            async: false,
            success: function (res) {
                var buildObj = {
                    buildingTotal:res['LE-BuildingTotal'],  // 建筑物总数
                    routerOffNum:res['SE-RouterOffNum'],  //路由离线
                    gatewayOffNum:res['SE-GatewayOffNum'],   //网关离线
                    nodeTotal:res['SE-NodeTotal'],   //路由，网关总设备数
                    nodeDeviceOffNum:res['SE-NodeDeviceOffNum'],  // 模块离线数
                    nodeDeviceNum:res['SE-NodeDeviceNum']  //模块总数
                };
                var areaObj = {
                    powerTotal:res['SE-PowerTotal'],
                    unitPrice:res['SE-UnitPrice']
                };
                initBuildingData(buildObj);
                initAreaData(areaObj);
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }
    /*
    * 初始化建筑物数据
    * */
    function initBuildingData(obj) {
        var buildingTotal =  parseFloat(formatCurrency(obj.buildingTotal.itemValue)).toFixed(0);
        var routerOffNum = parseFloat(formatCurrency(obj.routerOffNum.itemValue)).toFixed(0);
        var gatewayOffNum = parseFloat(formatCurrency(obj.gatewayOffNum.itemValue)).toFixed(0);
        var nodeTotal = parseFloat(formatCurrency(obj.nodeTotal.itemValue)).toFixed(0);
        var nodeDeviceOffNum = parseFloat(formatCurrency(obj.nodeDeviceOffNum.itemValue)).toFixed(0);
        var nodeDeviceNum = parseFloat(formatCurrency(obj.nodeDeviceNum.itemValue)).toFixed(0);
        $('.shebei-ul>li.buildingTotal>.right i').html(buildingTotal);
        $('.shebei-ul>li.routerOffNum>.right i').html(routerOffNum);
        $('.shebei-ul>li.routerOffNum>.right>span:nth-of-type(2) i').html(nodeTotal);
        $('.shebei-ul>li.gatewayOffNum>.right i').html(gatewayOffNum);
        $('.shebei-ul>li.gatewayOffNum>.right>span:nth-of-type(2) i').html(nodeTotal);
        $('.shebei-ul>li.nodeDeviceOffNum>.right i').html(nodeDeviceOffNum);
        $('.shebei-ul>li.nodeDeviceOffNum>.right>span:nth-of-type(2) i').html(nodeDeviceNum);
    }
    /*
    * 初始化地区数据
    * */
    function initAreaData(obj) {
        var powerTotal = obj.powerTotal.itemValue;
        powerTotal = Math.round(parseFloat(powerTotal));
        var unitPrice = parseFloat(obj.unitPrice.itemValue);
        var powerPriceTotal = (parseFloat(powerTotal) * unitPrice).toFixed(1);
        $('.dianfei>.left>.input-wrap input').val(unitPrice);
        $('.dianfei>.right>.input-wrap input').val(powerPriceTotal);
        var numArr = powerTotal.toString().split('');
        localStorage.setItem('powerTotal',powerTotal);
        if(numArr.length <=8){
            var html = '';
            for(var i=0; i < numArr.length; i++){
                html += '<li class="active">'+numArr[i]+'</li>';
            }
            $(".num-ul").append(html);
            var num = 8 - numArr.length;
            if(num > 0){
                var preHtml = '';
                for(var i=0; i <  num; i++){
                    preHtml += '<li>0</li>';
                }
                $(".num-ul").prepend(preHtml);
            }
        }
    }
    /*
    * 监听输入框单价回车事件,
    * */
    $("#input-unitPrice").on('keypress',function (e) {
        var powerTotal = parseFloat(localStorage.getItem('powerTotal'));
        var price = parseFloat($(this).val());
        var reg = /^[0-9]+(.[0-9]{1})?$/;   //验证有一位小数的正实数
        if (!reg.test(price)) {
            alert('输入的字符有误，请重新输入！')
        }else{
            var totalPrice = powerTotal*price;
            if (e.which == 13) {
                $('#input-totalPrice').val(totalPrice.toFixed(1));
            }
        }
    });
    /*
    * 初始化温度湿度值
    * */
    var wendu,shidu;
    function initTemperatureAndHumidity(temperature,humidity){
        temperature = parseFloat(temperature.replace('℃',''));
        humidity = parseFloat(humidity.replace('%RH',''));
        if(!wendu){
            wendu = new canvasPanel();
            wendu.bgColor = '#FF7B57';
            wendu.MaxNum = 120;
            wendu.MinNum = -10;
            wendu.current = temperature;
            wendu.title = '温度值';
            wendu.init('wendu');
        }else{
            wendu.current = temperature;
            wendu.init('wendu');
            wendu.paintTitle('温度值');
        }
        if(!shidu){
            shidu = new canvasPanel();
            shidu.bgColor = '#00FFFF';
            shidu.danwei = "%RH";
            shidu.MaxNum = 100;
            shidu.MinNum = 0;
            shidu.current = humidity;
            shidu.title = '湿度值';
            shidu.init('shidu');
        }else {
            shidu.current = humidity;
            shidu.init('shidu');
            shidu.paintTitle('湿度值');
        }
    }
    /*
    * 初始化路由网关状态
    * */
    function initSignalValue(routeSignalObj,gateStatus){
        //路由
        if(routeSignalObj.routeStatus == 1){ //1,在线，0离线
            var routeSignal = routeSignalObj.routeSignal;
            routeSignal = parseFloat(routeSignal.replace('dbm',''));
            var html = '';
            if(routeSignal < 0 && routeSignal >= -50){
                html = '<span>在线</span><img src="./img/signal-5.png">';
            }else if(routeSignal < -50 && routeSignal >= -70){
                html = '<span>在线</span><img src="./img/signal-3.png">';
            }else if(routeSignal < -70){
                html = '<span>在线</span><img src="./img/signal-1.png">';
            }
            $('.shebei-status-ul>li.router .line').addClass('zaixian').html(html);

        }else{
            $('.shebei-status-ul>li.router .line').addClass('lixian').html('<span>离线</span>')
        }
        //网关
        if(gateStatus == 1){ //1,在线，0离线
            $('.shebei-status-ul>li.gate .line').addClass(('zaixian')).html('<span>在线</span>');
        }else{
            $('.shebei-status-ul>li.gate .line').addClass(('lixian')).html('<span>离线</span>')
        }
    }
    /*
     * 初始化故障监控年月
     */
    function initFaultMonitoringTime(){
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        if(month < 10){
            month = '0' + month;
        }
        var yearHtml = '<option value="'+ year+'">'+year+'</option>';
        $('select#year').append(yearHtml);
        var monthArr = $('select#month option');
        $.each(monthArr,function (i,item) {
             if(item.value == month){
                 item.selected = true;
             }
        });
    }
    /*
     * 初始化节点耗电数据echarts图
    */
    function initNodeConsumePowerData(number) {
        //处理仪表盘max数值，根据实际值来
        var max = (number*10).toFixed(0);
        var len = max.length -1;
        max = max / (Math.pow(10, len));
        max = max.toFixed(0);
        max = max *(Math.pow(10, len)) / max;
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
                max:max,
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
                    offsetCenter:['0', '-25%'],//相对于仪表盘中心的偏移位置
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
                    offsetCenter: ['0', '50%'], //相对于仪表盘中心的偏移位置
                    formatter:'{value}',
                    fontSize: 30,
                    fontWeight: 'bold',
                    color: '#FFF100'
                },
                data: [
                    {value: number, name: 'kwh'}
                ]
            }]

        };
        myChart.setOption(option);
    }
    /**
     * 故障监控统计图表
     * @constructor
     */
    function initFaultMonitoringData() {
        var dayList = [];
        var dayCountList = [];
        var year = $("#year option:selected").val();
        var month = $("#month option:selected").val();
        $.ajax({
            url: contextPath + "/anslysis/errorChart",
            type: "post",
            async: false,
            data: {
                year: year,
                month: month
            },
            success: function (rst) {
                if(rst.length > 0){
                    for(var i=0; i<rst.length; i++){
                        dayList.push(rst[i].checkMonth + '-' + rst[i].checkDay  );
                        dayCountList.push(rst[i].brokenLampCount);
                    }
                }
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
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
                    data: dayList,
                    axisLabel:{
                        //X轴刻度配置 0：表示全部显示不间隔
                        interval:0
                    },
                    axisLine: {
                        lineStyle: {
                            type: 'solid',
                            color: '#00D6D9'//左边线的颜色
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
                            color: '#00D6D9'//左边线的颜色
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
            series : [
                {
                    name:'故障数量(条)',
                    type:'line',
                    smooth:true,                                    //曲线是否平滑显示
                    areaStyle: { normal: {opacity:0.3} },           //曲线面积透明度
                    data:dayCountList    //数据
                }
            ],
            color:lineColor                   //配色
        };
        echart.setOption(option);
    }
    /**
     * 初始化弱电故障数据
     *
     */
    function initWeakCurrentFaultData(){
        $.ajax({
            url: contextPath + "/anslysis/errorList",
            type: "post",
            async: false,
            data: {
                start: 0,
                limit: 10
            },
            success: function (rst) {
                if (rst.status == 0) {
                    render('#top10-list', rst.rows, 'top10-tpl');
                }
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }
    /*
    * 初始化树数据
    * */
    function initZTreeData() {
        var setting = {
            check: {
                enable: true
            },
            view: {
                dblClickExpand: true,
                showIcon: false,
                fontCss : {"font-weight":"400","font-size":"16px"}
            },
            //节点字段修改
            data: {
                key: {
                    name: "text",
                    children: "children"
                },
                simpleData: {
                    enable: true,
                }
            },
            callback: {    //第一步
                onClick: zTreeOnClick     //获取节点值
            }
        };
        $.ajax({
            url: contextPath + "/seNode/getNodeTreeList",
            type: "post",
            async: false,
            data: {
                start: 0,
                limit: 10
            },
            success: function (rst) {
                if (rst.length > 0) {
                    $.fn.zTree.init($("#node-name-tree"), setting, rst);
                }
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }
    //tree执行选择事件
    function zTreeOnClick(event, treeId, treeNode) {       //第二步
        console.log(treeNode);
        if(!treeNode.isParent){
            if(treeNode.src==1){   //src, 0,不可选 1，可选
                var id = treeNode.id;
                var text = treeNode.text;
                $('#node-name').attr('data-id',id).val(text);
                $('.up-data').hide();
                $('.up-have-data').css('display','flex');
                initSelectTreeData(id,text);


            }else{
                alert('当前节点不可用，请重新选择');
            }
        }else{
            alert('当前节点不是最末端，请重新选择')
        }
    }
    /*
    *  初始化回路模块数量
    * */
    function  initLoopModularScene(modularNum,loopNum,sceneValue){
        $('.mokuai-huilu-ul>li.modular .right i').html(modularNum);
        $('.mokuai-huilu-ul>li.loop .right i').html(loopNum);
        var $scene =  $('.scene-content>.down');
        switch (sceneValue) {
            case '平日模式':
                $scene.find('img').attr('src','./img/icon-weekdays@2x.png');
                $scene.find('span').html(sceneValue);
                break;
            case '全开模式':
                $scene.find('img').attr('src','./img/icon-open@2x.png');
                $scene.find('span').html(sceneValue);
                break;
            case '全关模式':
                $scene.find('img').attr('src','./img/icon-close@2x.png');
                $scene.find('span').html(sceneValue);
                break;
            case '节能模式':
                $scene.find('img').attr('src','./img/icon-model-jieneng@2x.png');
                $scene.find('span').html(sceneValue);
                break;
            case '未知模式':
                $scene.find('img').attr('src','./img/icon-model-xx@2x.png');
                $scene.find('span').html(sceneValue);
                break;
        }
    }
    /*
     *  初始化下拉树选中数据
     */
    function initSelectTreeData(id,text){
        var id = id?id:1;
        var text = text?text:$('#node-name').val();
        $('.dianya-wrap>.title').html(text);
        $.ajax({
            url: contextPath + "/anslysis/screenNodeData?nodeId="+ id +"",
            type: "post",
            async: false,
            data: {
                start: 0,
                limit: 10
            },
            success: function (res) {
                console.log(res);
                var nodeConsumePowerNumber = res.meterValue; //节点耗电量
                var temperature = res.temperature; // 温度
                var humidity = res.humidity; //湿度
                var routeSignalObj = {    //路由
                    routeStatus : res.routeStatus,
                    routeSignal: res.routeSignal
                };
                var gateStatus = res.gateStatus;//网关状态
                var modularNum = res.deviceCount; //模块数量
                var loopNum = res.deviceCHCount;  //回路数量
                var sceneValue = res.presetCaption; //场景
                initNodeConsumePowerData(nodeConsumePowerNumber); //初始化节点耗电量echart图
                initTemperatureAndHumidity(temperature,humidity);  //初始化温度，湿度值
                initSignalValue(routeSignalObj,gateStatus); //初始化路由和网关状态
                initLoopModularScene(modularNum,loopNum,sceneValue); //初始化回路，模块，场景模式
            },
            error: function () {
                alert("对不起出错了！");
            }
        });
    }
    //tree显示与隐藏
    document.onclick = function(e) {
        $('.node-tree-container').hide();
    };
    $('#node-name').on("click", function(e) {
        if($('.node-tree-container').css("display") == "none") {
            $('.node-tree-container').show();
        } else {
            $('.node-tree-container').hide();
        }
        e = e || event;
        stopFunc(e);
    });

    $('.node-tree-container').on("click", function(e) {
        e = e || event;
        stopFunc(e);
    });
    function stopFunc(e) {
        e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
    }
    /*
   * 格式化数字，金钱
   * */
    function formatCurrency(num) {
        if (!num){
            return "0.00";
        }
        num = num.toString().replace(/\$|\,/g, '');//去除$符号
        if (isNaN(num)){
            num = "0.00";
        }
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10){
            cents = "0" + cents;
        }
        for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ','+ num.substring(num.length - (4 * i + 3));
        return (((sign) ? '' : '-') + '' + num + ',' + cents);
    }
    /*页面渲染*/
    function render(obj, data, tplId) {
        var tpl = document.getElementById(tplId).innerHTML;
        var html = juicer(tpl, {
            data: data
        });
        $(obj).html(html);
    }
</script>
</body>
</html>
