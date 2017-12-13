<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>运营分析</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
var meetingId='${param.meetingId}';
var topicId='${param.topicId}';
var normId='${param.normId}';



</script>
<body>
<div class="page-content">
    <div class="row">
        <div class="col-md-6">
            <div style="padding-left:30px">
                <a class="blue" href="javascript:setViewType('1')" data-rel="tooltip" data-placement="top" title="默认视图">默认</a>
                <a class="blue" href="javascript:setViewType('2')" data-rel="tooltip" data-placement="top" title="月视图">月视图</a>
                <a class="blue" href="javascript:setViewType('3')" data-rel="tooltip" data-placement="top" title="周视图">周视图</a>
            </div>
            <div id="ct1" class="row" style="height:400px; overflow:scroll;"></div>


        </div>
        <div class="col-md-6">

            <div style="padding-left:30px">
                <a class="blue" href="javascript:" data-rel="tooltip" data-placement="top" title="默认视图">默认</a>
            </div>
            <div id="ct2" class="row" style="height:400px; overflow:scroll;">


            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
           <!-- <div class="row">
                <table id="grid1" class="table table-bordered" style="margin-left:20px;text-align:left">


                </table>
            </div>-->
            <div class="row" id="ct3">
                <div style="padding-left:30px">
                    <div class="div-left header-title-custom">不良现象明细</div>
                    <div class="div-right header-title-custom">
                        <div style="text-align:right"><a class="blue" href="javascript:add2()" data-rel="tooltip" data-placement="top"
                                                         title="不良现象明细"><i class="ace-icon fa fa-plus-square"></i></a>
                            <a class="blue" href="javascript:reload2()" data-rel="tooltip" data-placement="top" title="刷新"><i
                                    class="ace-icon glyphicon glyphicon-refresh"></i></a>
                        </div>
                    </div>
                </div>
                <div class="action-buttons">
                    <a>
                    </a>
                </div>
                <table id="grid2" class="table table-bordered table-hover" style="margin-left:20px;text-align:left">


                </table>

            </div>

        </div>
        <div class="col-md-6" id="ct4">
            <div style="padding-left:30px">
                <div class="div-left header-title-custom">TOP10问题分析</div>
                <div class="div-right header-title-custom">
                    <div style="text-align:right"><a class="blue" href="javascript:add()" data-rel="tooltip" data-placement="top"
                                                     title="任务分配"><i class="ace-icon fa fa-plus-square"></i></a>
                        <a class="blue" href="javascript:reload()" data-rel="tooltip" data-placement="top" title="刷新"><i
                                class="ace-icon glyphicon glyphicon-refresh"></i></a>
                    </div>
                </div>
            </div>
            <div class="action-buttons">
                <a>
                </a>
            </div>
            <table id="grid3" class="table table-bordered table-hover" style="margin-left:20px;text-align:left">


            </table>
        </div>
    </div>
</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/config-1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/config-2.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/view.js?version=${cfg.version}&t=1"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<style>
.div-left{ float:left;width:90%;}
.div-right{ float:right;width:10%;}
thead {
           display:block
}
tbody {
           height:300px;
           overflow-y:scroll;
           display:block;
           width:100%;
 }
      .wt60{
          width:45px;
          min-width:45px;
          max-width:45px;
      }
      .wt80{
          width:105px;
          min-width:105px;
          max-width:105px;
      }
      .wt600{
          width:140px;
          min-width:140px;
          max-width:140px;
      }
</style>

</body>
</html>