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
    }
    i{
        font-style: normal;
    }
    .jkpt-container-wrap{
        width: 100%;
        height: 1080px;
        background: url("./img/monitoring-bg@2x.png") no-repeat center center #FFFFFF;
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
            /*border:1px solid red;*/
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
        background: url("./img/border-zhdl@2x.png") no-repeat center center;
    }
    .area-wrap>.title{
        font-size: 20px;
        color: #FFFFFF;
        text-align: center;
        padding-top:20px;
    }
    .jkpt-main-wrap>.right{
        flex: 1;
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
                       <div class="left">
                           <%--<span>开始时间</span>--%>
                           <%--<input id="p-startDt" type="text" size="16" name="startDate" readonly="" class="form-control">--%>
                       </div>
                       <div class="middle"></div>
                       <div class="right"></div>
                   </div>
               </div>
           </div>
           <div class="right">
               <div class="up">sdf</div>
               <div class="down">dsdf</div>
           </div>
       </div>
   </div>


<%--=============common jsp-suffix===============--%>
<%--<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>--%>
<%--==============common jsp-suffix==============--%>
</body>




<style>

</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<%--<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>--%>
<%--<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>--%>


<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

</html>
