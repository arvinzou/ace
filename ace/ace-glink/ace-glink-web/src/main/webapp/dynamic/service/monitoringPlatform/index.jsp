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
    ul,li{
        margin: 0;
        padding: 0;
        list-style-type: none;
    }
    i{
        font-style: normal;
    }
    .jkpt-container-wrap{
        width: 100%;
        height: 1080px;
        background: url("./img/monitoring-bg@2x.png") no-repeat center center transparent;
    }
    .jkpt-header-wrap{
        width: 100%;
        height: 108px;
        background: url("./img/header-bg@2x.png") no-repeat center center;
    }
    .jkpt-main-wrap{
        width: 100%;
        height: 947px;
        background: url("./img/border-mainbig@2x.png") no-repeat center center ;
    }
    .jkpt-main-wrap{
        display: flex;
    }
    .jkpt-main-wrap>.left{
        flex: 0 0 411px;
        width: 411px;
        padding: 51px 0 0 27px ;
    }
    .shebei-ul>li{
        min-width:375px;
        min-height: 111px;
        background: url("./img/border-sjtj@2x.png") no-repeat center center;
        display: flex;
        align-items: center;
        padding: 26px 0 15px 16px;
    }
    .shebei-ul>li+li{
        margin-top:14px;
        border-top-left-radius: 20px;
        border-top-right-radius: 20px;
    }
    .shebei-ul>li>img{
        flex: 0 0 63px;
        width: 63px;
        height: 70px;
    }
    .shebei-ul>li>.right{
        flex: 1;
        margin-left: 10px;
    }
    .shebei-ul>li>.right>i{
        display: block;
        color: #02FCFF;
        font-size: 36px;
        font-weight: bold;
    }
    .shebei-ul>li>.right>span{
        display: inline-block;
        font-size: 16px;
        color: #ffffff;
        height: 17px;
        line-height: 17px;
    }
    .shebei-ul>li>.right>span:nth-of-type(1){
        padding-right:20px;
    }
    .shebei-ul>li>.right>span:nth-of-type(2){
        border-left: 1px solid #FFFFFF;
        padding-left:12px;
    }
    .area-wrap{
        width: 100%;
        min-width: 374px;
        height: 362px;
        margin-top:17px;
        padding:0 16px;
        background: url("./img/border-zhdl@2x.png") no-repeat center center;
    }
    .area-wrap>.title{
        font-size: 20px;
        color: #FFFFFF;
        text-align: center;
        padding-top:20px;
        margin-bottom: 22px;
    }
    .num-ul{
        display: flex;
        margin-top:14px;
    }
    .num-ul>li{
        flex: 0 0 41px;
        width: 41px;
        height: 54px;
        line-height: 54px;
        font-weight: bold;
        color: #005556;
        font-size: 48px;
        text-align: center;
        background: url("./img/num-bg@2x.png") no-repeat center center;
    }
    .num-ul>li.active{
        color: #00D6D9;
    }
    .num-ul>li+li{
        margin-left:2px;
    }
    .dianliang>span,
    .dianfei>.left>span,
    .dianfei>.right>span
    {
        font-size: 16px;
        color: #fff;
    }
    .dianfei{
        margin-top:30px;
    }
    .dianfei>.left,
    .dianfei>.right
    {
        flex: 1;
    }
    .dianfei .input-wrap{
        margin-top: 14px;
        position: relative;
        color: #ffffff;
    }
    .time-wrap>.dianfei{
        display: flex;
        align-items: center;
    }
    .dianfei>.empty-wrap{
        flex: 0 0 30px;
    }
    .input-wrap>input{
        width: 156px;
        height: 32px;
        color: #ffffff;
        font-size: 24px;
        font-weight: bold;
        padding: 0 40px 0 26px;
        outline: none;
        border:1px solid #00FFFF;
        background-color: transparent;
    }
    .input-wrap>i{
        position: absolute;
        top:5px;
        left: 10px;
        z-index:10;
        color: #fff;
        font-size: 16px;
    }
    .dianfei>.left>.input-wrap>span{
        position: absolute;
        top:5px;
        right: 20px;
        z-index:10;
        color: #fff;
        font-size: 16px;
    }
    .dianfei>.right>.input-wrap>input{
        padding-right:10px;
    }
    .jkpt-main-wrap>.right{
        flex: 1;
        padding: 51px  41px 0 10px;
    }
    .jkpt-main-wrap>.right>.up{
        width: 100%;
        height: 278px;
        display: flex;
        background: url("./img/border-jdxx@2x.png") no-repeat left center/contain ;
    }
    .wendu-wrap{
        padding-top: 45px;
    }
    .wendu-wrap .canvas-tu{
        display: inline-block;
        width: 150px;
        height: 212px;
    }
    .shebei-status-wrap{
        margin-left:20px;
        padding-top: 35px;
    }
    .shebei-status-wrap select{
        width: 110px;
        margin-left: auto;
        border:none;
        color: #fff;
        border-radius: 50px !important;
        background-color:#00D6D9 ;
    }
    .shebei-status-ul>li{
        display: flex;
        align-items: center;
        width: 266px;
        min-height: 82px;
        padding: 20px 0 10px 16px;
        background: url("./img/shebei-bg@2x.png") no-repeat left center/contain;
    }
    .shebei-status-ul>li{
        margin-top: 10px;
    }
    .shebei-status-ul>li>.right{
        margin-left:15px;
    }
    .shebei-status-ul>li>.right>i{
        color: #FFFFFF;
        font-size: 18px;
    }
    .shebei-status-ul>li>.right>.line{
        margin-top:6px;
        font-size: 14px;
    }
    .shebei-status-ul>li>.right>.line>span:before{
        display: inline-block;
        content: '';
        width:8px;
        height: 8px;
        margin-right:5px;
        border-radius: 10px;
    }
    .shebei-status-ul>li>.right>.zaixian{
        color: #0AFD99;
    }
    .zaixian>span:before{
        background-color:#0AFD99;
    }
    .lixian>span:before{
        background-color:red;
    }
    .shebei-status-ul>li>.right>.lixian{
        color: red;
    }


</style>
<%--<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>--%>

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
                                   <div class="line zaixian"><span>在线</span></div>
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
               <div class="down"></div>
           </div>
       </div>
   </div>


<%--=============common jsp-suffix===============--%>
<%--<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>--%>
<%--==============common jsp-suffix==============--%>
</body>




<style>

</style>
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
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
    })
</script>
</html>
