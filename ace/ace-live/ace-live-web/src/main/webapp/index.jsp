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
    <title>${cfg.sys_name}</title>

</head>


<jsp:include page="/dynamic/common/header.jsp" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="page-content-inner">

</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<script id="tpl-portal-1" type="text/template">

    <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->
    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">咨询师</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-user"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计已审核</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.counselor}">\${data.counselor}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">工作室</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red  icon-screen-desktop"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计已审核</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.studio}">\${data.studio}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">课程</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-layers"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计已审核</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.course}">\${data.course}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">心理评测</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue  icon-graduation"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.evaluat_tpl}">\${data.evaluat_tpl}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
    </div>

    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">评论</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-note"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.courseCmt}">\${data.courseCmt}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">咨询订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red  icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计有效</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.counselorOrder}">\${data.counselorOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">课程订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计有效</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.courseOrder}">\${data.courseOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">评测订单</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue icon-basket-loaded"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计有效</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.evaluatOrder}">\${data.evaluatOrder}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
    </div>


    </div>
</script>



<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/index/act.js?version=${cfg.version}"></script>
</body>
</html>