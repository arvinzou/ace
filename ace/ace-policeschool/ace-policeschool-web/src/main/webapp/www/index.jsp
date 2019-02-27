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
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="../dynamic/common/base.jsp"/>
    <script src="${portalPath}/content/common/js/loader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/portal/js/main/portal4.js?v=${cfg.version}"></script>
</head>

<body>
<div class="page-wrapper">

    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="${pageContext.request.contextPath}/www/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <%--<li>--%>
                                <%--<span>仪表盘</span>--%>
                                <%--</li>--%>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <h1>湖南华彩伟业网络科技有限公司</h1>
                                <!--=======================================-->

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>

</div>

</body>
</html>