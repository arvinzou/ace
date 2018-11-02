<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>管理平台</title>

</head>
<jsp:include page="/dynamic/common/header.jsp" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<div class="page-content-inner">

</div>




<div class="row">
    <div class="col-xs-12 col-sm-6">
        <!-- #section:custom/widget-box -->
        <div class="widget-box transparent background-white padding1" id="recent-box">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">
                    <i class="ace-icon glyphicon glyphicon-th-large green"></i>系统公告
                </h4>

                <div class="widget-toolbar no-border">

                </div>
            </div>

            <div class="widget-body" style="min-height:250px">
                <div class="widget-main padding-4">
                    <table width="100%">


                        <tbody id="notice-list-grid">

                        </tbody>
                    </table>
                </div>
                <!-- /.widget-main -->
            </div>
            <!-- /.widget-body -->
        </div>
        <!-- /.widget-box -->

        <!-- /section:custom/widget-box -->
    </div>

    <div class="col-xs-12 col-sm-6">
        <!-- #section:custom/widget-box -->
        <div class="widget-box transparent background-white padding1" id="recent-box">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">
                    <i class="ace-icon glyphicon glyphicon-th-large green"></i>业务公告
                </h4>

                <div class="widget-toolbar no-border">

                </div>
            </div>

            <div class="widget-body" style="min-height:250px">
                <div class="widget-main padding-4">
                    <table width="100%;">


                        <tbody id="notice-list-grid-area">

                        </tbody>
                    </table>
                </div>
                <!-- /.widget-main -->
            </div>
            <!-- /.widget-body -->
        </div>
        <!-- /.widget-box -->

        <!-- /section:custom/widget-box -->
    </div>
    <!-- /.span -->
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<jsp:include page="/dynamic/common/footer.jsp" />

<script src="${pageContext.request.contextPath}/content/index/act.js?version=${cfg.version}"></script>


<script id="tpl-portal" type="text/template">

    <!-- PAGE CONTENT BEGINS -->
    <!-- Row starts -->


    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">企业</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple glyphicon glyphicon-star-empty"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.company}">\${data.company}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">会员</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green glyphicon glyphicon-star"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.member}">\${data.member}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">发布信息</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red glyphicon glyphicon-th"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.info}">\${data.info}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">微信用户</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-mobile"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.userinfo}">\${data.userinfo}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>

    </div>




    </div>


</script>
</body>
</html>