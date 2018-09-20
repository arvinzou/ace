<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>芙蓉街道智慧平台</title>

</head>


<jsp:include page="/dynamic/common/common.jsp"/>

<style>
    .padding1 {
        padding: 15px 15px 15px 15px;
    }

    .page-content {
        background-color: #eff3f8;
        position: relative;
        margin: 0;
        padding: 10px 10px 0px 10px;
    }



    .background {
        background-color: #eff3f8;
    }

    .background-white {

    }

    .stamper {
        padding-top: 10px;
        height: 100px;
    }

    .stamper span {
        float: right;
        display: inline-block;
        height: 100%;
        width: 200px;
    }
    .margin-bottom-40 {
     margin-bottom: 0px!important;
}

</style>


<style>
    .charts-portal-ct1 {
        width: 500px;
        height: 300px
    }

    .charts-portal-ct2 {
        width: 500px;
        height: 300px
    }
    /***
Dashboard Stats 2
***/
.dashboard-stat2 {
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  -ms-border-radius: 4px;
  -o-border-radius: 4px;
  border-radius: 4px;
  background: #fff;
  padding: 15px 15px 30px 15px;
  margin-bottom: 20px; }
  .dashboard-stat2.bordered {
    border: 1px solid #e7ecf1; }
  .dashboard-stat2 .display {
    margin-bottom: 20px; }
    .dashboard-stat2 .display:before, .dashboard-stat2 .display:after {
      content: " ";
      display: table; }
    .dashboard-stat2 .display:after {
      clear: both; }
    .dashboard-stat2 .display .number {
      float: left;
      display: inline-block; }
      .dashboard-stat2 .display .number h3 {
        margin: 0 0 2px 0;
        padding: 0;
        font-size: 30px;
        font-weight: 400; }
        .dashboard-stat2 .display .number h3 > small {
          font-size: 23px; }
      .dashboard-stat2 .display .number small {
        font-size: 14px;
        color: #AAB5BC;
        font-weight: 600;
        text-transform: uppercase; }
    .dashboard-stat2 .display .icon {
      display: inline-block;
      float: right;
      padding: 7px 0 0 0; }
      .dashboard-stat2 .display .icon > i {
        color: #cbd4e0;
        font-size: 26px; }
  .dashboard-stat2 .progress-info {
    clear: both; }
    .dashboard-stat2 .progress-info .progress {
      margin: 0;
      height: 4px;
      clear: both;
      display: block; }
    .dashboard-stat2 .progress-info .status {
      margin-top: 5px;
      font-size: 11px;
      color: #AAB5BC;
      font-weight: 600;
      text-transform: uppercase; }
      .dashboard-stat2 .progress-info .status .status-title {
        float: left;
        display: inline-block; }
      .dashboard-stat2 .progress-info .status .status-number {
        float: right;
        display: inline-block; }



/* Custom colors */
.progress-bar.white {
  background: #ffffff !important;
  color: #666 !important; }

.progress-bar.default {
  background: #e1e5ec !important;
  color: #666 !important; }

.progress-bar.dark {
  background: #2f353b !important;
  color: #FFFFFF !important; }

.progress-bar.blue {
  background: #3598dc !important;
  color: #FFFFFF !important; }

.progress-bar.blue-madison {
  background: #578ebe !important;
  color: #FFFFFF !important; }

.progress-bar.blue-chambray {
  background: #2C3E50 !important;
  color: #FFFFFF !important; }

.progress-bar.blue-ebonyclay {
  background: #22313F !important;
  color: #FFFFFF !important; }

.progress-bar.blue-hoki {
  background: #67809F !important;
  color: #FFFFFF !important; }

.progress-bar.blue-steel {
  background: #4B77BE !important;
  color: #FFFFFF !important; }

.progress-bar.blue-soft {
  background: #4c87b9 !important;
  color: #FFFFFF !important; }

.progress-bar.blue-dark {
  background: #5e738b !important;
  color: #FFFFFF !important; }

.progress-bar.blue-sharp {
  background: #5C9BD1 !important;
  color: #FFFFFF !important; }

.progress-bar.blue-oleo {
  background: #94A0B2 !important;
  color: #FFFFFF !important; }

.progress-bar.green {
  background: #32c5d2 !important;
  color: #FFFFFF !important; }

.progress-bar.green-meadow {
  background: #1BBC9B !important;
  color: #FFFFFF !important; }

.progress-bar.green-seagreen {
  background: #1BA39C !important;
  color: #FFFFFF !important; }

.progress-bar.green-turquoise {
  background: #36D7B7 !important;
  color: #FFFFFF !important; }

.progress-bar.green-haze {
  background: #44b6ae !important;
  color: #FFFFFF !important; }

.progress-bar.green-jungle {
  background: #26C281 !important;
  color: #FFFFFF !important; }

.progress-bar.green-soft {
  background: #3faba4 !important;
  color: #FFFFFF !important; }

.progress-bar.green-dark {
  background: #4DB3A2 !important;
  color: #FFFFFF !important; }

.progress-bar.green-sharp {
  background: #2ab4c0 !important;
  color: #FFFFFF !important; }

.progress-bar.green-steel {
  background: #29b4b6 !important;
  color: #FFFFFF !important; }

.progress-bar.grey {
  background: #E5E5E5 !important;
  color: #333333 !important; }

.progress-bar.grey-steel {
  background: #e9edef !important;
  color: #80898e !important; }

.progress-bar.grey-cararra {
  background: #fafafa !important;
  color: #333333 !important; }

.progress-bar.grey-gallery {
  background: #555555 !important;
  color: #ffffff !important; }

.progress-bar.grey-cascade {
  background: #95A5A6 !important;
  color: #FFFFFF !important; }

.progress-bar.grey-silver {
  background: #BFBFBF !important;
  color: #FAFCFB !important; }

.progress-bar.grey-salsa {
  background: #ACB5C3 !important;
  color: #FAFCFB !important; }

.progress-bar.grey-salt {
  background: #bfcad1 !important;
  color: #FAFCFB !important; }

.progress-bar.grey-mint {
  background: #525e64 !important;
  color: #FFFFFF !important; }

.progress-bar.red {
  background: #e7505a !important;
  color: #ffffff !important; }

.progress-bar.red-pink {
  background: #E08283 !important;
  color: #ffffff !important; }

.progress-bar.red-sunglo {
  background: #E26A6A !important;
  color: #ffffff !important; }

.progress-bar.red-intense {
  background: #e35b5a !important;
  color: #ffffff !important; }

.progress-bar.red-thunderbird {
  background: #D91E18 !important;
  color: #ffffff !important; }

.progress-bar.red-flamingo {
  background: #EF4836 !important;
  color: #ffffff !important; }

.progress-bar.red-soft {
  background: #d05454 !important;
  color: #ffffff !important; }

.progress-bar.red-haze {
  background: #f36a5a !important;
  color: #ffffff !important; }

.progress-bar.red-mint {
  background: #e43a45 !important;
  color: #ffffff !important; }

.progress-bar.yellow {
  background: #c49f47 !important;
  color: #ffffff !important; }

.progress-bar.yellow-gold {
  background: #E87E04 !important;
  color: #ffffff !important; }

.progress-bar.yellow-casablanca {
  background: #f2784b !important;
  color: #ffffff !important; }

.progress-bar.yellow-crusta {
  background: #f3c200 !important;
  color: #ffffff !important; }

.progress-bar.yellow-lemon {
  background: #F7CA18 !important;
  color: #ffffff !important; }

.progress-bar.yellow-saffron {
  background: #F4D03F !important;
  color: #ffffff !important; }

.progress-bar.yellow-soft {
  background: #c8d046 !important;
  color: #ffffff !important; }

.progress-bar.yellow-haze {
  background: #c5bf66 !important;
  color: #ffffff !important; }

.progress-bar.yellow-mint {
  background: #c5b96b !important;
  color: #ffffff !important; }

.progress-bar.purple {
  background: #8E44AD !important;
  color: #ffffff !important; }

.progress-bar.purple-plum {
  background: #8775a7 !important;
  color: #ffffff !important; }

.progress-bar.purple-medium {
  background: #BF55EC !important;
  color: #ffffff !important; }

.progress-bar.purple-studio {
  background: #8E44AD !important;
  color: #ffffff !important; }

.progress-bar.purple-wisteria {
  background: #9B59B6 !important;
  color: #ffffff !important; }

.progress-bar.purple-seance {
  background: #9A12B3 !important;
  color: #ffffff !important; }

.progress-bar.purple-intense {
  background: #8775a7 !important;
  color: #ffffff !important; }

.progress-bar.purple-sharp {
  background: #796799 !important;
  color: #ffffff !important; }

.progress-bar.purple-soft {
  background: #8877a9 !important;
  color: #ffffff !important; }

  .img-circle {
    border-radius: 50%!important;
}
</style>

<body>
<div style="height:10px;" class="background-white"></div>
<div class="page-content">

    <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->
    <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-red-haze">
                            <span data-counter="counterup" data-value="0" id="jxb">0</span>
                            <small class="font-red-haze">位</small>
                        </h3>
                        <small>入驻咨询师</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-user-circle-o"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                                            <span style="width: 100%;"
                                                  class="progress-bar progress-bar-success red-haze">
                                                <span class="sr-only"></span>
                                            </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-green-sharp">
                            <span data-counter="counterup" data-value="" id="rpt">0</span>
                            <small class="font-green-sharp">个</small>
                        </h3>
                        <small>入驻工作室</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-bank"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                                            <span style="width: 100%;"
                                                  class="progress-bar progress-bar-success green-sharp">
                                                <span class="sr-only"></span>
                                            </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-blue-sharp">
                            <span data-counter="counterup" data-value="0" id="cmt">0</span>
                            <small class="font-blue-sharp">节</small>
                        </h3>
                        <small>课程资源</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-comment-o"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                                            <span style="width: 100%;"
                                                  class="progress-bar progress-bar-success blue-sharp">
                                                <span class="sr-only"></span>
                                            </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            <div class="dashboard-stat2 ">
                <div class="display">
                    <div class="number">
                        <h3 class="font-purple-soft">
                            <span data-counter="counterup" data-value="0" id="msg">0</span>
                            <small class="font-purple-sharp">条</small>
                        </h3>
                        <small>心里评测</small>
                    </div>
                    <div class="icon">
                        <i class="fa fa-flag"></i>
                    </div>
                </div>
                <div class="progress-info">
                    <div class="progress">
                                            <span style="width: 100%;"
                                                  class="progress-bar progress-bar-success purple-soft">
                                                <span class="sr-only"></span>
                                            </span>
                    </div>
                    <div class="status">
                        <div class="status-title"></div>
                        <div class="status-number"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- /.page-content -->


<jsp:include page="/dynamic/common/footer-1.jsp"/>

<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

<script
        src="${pageContext.request.contextPath}/content/index/config-1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/config-2.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/view.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/jquery.stamper.js?version=${cfg.version}"></script>

<jsp:include page="/dynamic/common/footer-2.jsp"/>

<script
        src="${pageContext.request.contextPath}/content/index/index.js?version=${cfg.version}"></script>

</body>
</html>