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

    <jsp:include page="../../common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/reservation/css/style.css">
    <script src="${pageContext.request.contextPath}/content/service/reservation/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
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
                                    <a href="index4.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>仪表盘</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- BEGIN PROFILE SIDEBAR -->
                                        <!-- END BEGIN PROFILE SIDEBAR -->
                                        <!-- BEGIN PROFILE CONTENT -->
                                        <div class="profile-content">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="portlet light portlet-fit ">
                                                        <div class="portlet-title">
                                                            <div class="caption">
                                                                <i class=" icon-layers font-green"></i>
                                                                <span class="caption-subject font-green bold uppercase">预约设置</span>
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <div class="mt-element-card mt-element-overlay">
                                                                <div class="row">

                                                                    <%--content--%>


                                                                    <div class="table-scrollable table-scrollable-borderless">
                                                                        <table class="table table-hover table-light">
                                                                            <thead>
                                                                            <tr class="uppercase">
                                                                                <th colspan="2"> MEMBER</th>
                                                                                <th> Earnings</th>
                                                                                <th> CASES</th>
                                                                                <th> CLOSED</th>
                                                                                <th> RATE</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <tr>
                                                                                <td class="fit">
                                                                                    <img class="user-pic"
                                                                                         src="../assets/pages/media/users/avatar4.jpg">
                                                                                </td>
                                                                                <td>
                                                                                    <a href="javascript:;"
                                                                                       class="primary-link">Brain</a>
                                                                                </td>
                                                                                <td> $345</td>
                                                                                <td> 45</td>
                                                                                <td> 124</td>
                                                                                <td>
                                                                                    <span class="bold theme-font">80%</span>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class="fit">
                                                                                    <img class="user-pic"
                                                                                         src="../assets/pages/media/users/avatar5.jpg">
                                                                                </td>
                                                                                <td>
                                                                                    <a href="javascript:;"
                                                                                       class="primary-link">Nick</a>
                                                                                </td>
                                                                                <td> $560</td>
                                                                                <td> 12</td>
                                                                                <td> 24</td>
                                                                                <td>
                                                                                    <span class="bold theme-font">67%</span>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class="fit">
                                                                                    <img class="user-pic"
                                                                                         src="../assets/pages/media/users/avatar6.jpg">
                                                                                </td>
                                                                                <td>
                                                                                    <a href="javascript:;"
                                                                                       class="primary-link">Tim</a>
                                                                                </td>
                                                                                <td> $1,345</td>
                                                                                <td> 450</td>
                                                                                <td> 46</td>
                                                                                <td>
                                                                                    <span class="bold theme-font">98%</span>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class="fit">
                                                                                    <img class="user-pic"
                                                                                         src="../assets/pages/media/users/avatar7.jpg">
                                                                                </td>
                                                                                <td>
                                                                                    <a href="javascript:;"
                                                                                       class="primary-link">Tom</a>
                                                                                </td>
                                                                                <td> $645</td>
                                                                                <td> 50</td>
                                                                                <td> 89</td>
                                                                                <td>
                                                                                    <span class="bold theme-font">58%</span>
                                                                                </td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>

                                                                    <%--content--%>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- END PROFILE CONTENT -->
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>

</div>


<style>
    .hc-checkbox {
        width: 10rem;
    }

    .error_message {
        color: red;
    }

    .studiologo {
        width: 100px;
        height: 100px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .studiologo img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .studiologo + p {
        line-height: 1.05rem;
    }

    .input_style {
        border-bottom: 1px solid #c2cad8 !important;
    }

    .portlet-body {
        background-color: #fff !important;
        padding: 41px 98px 45px 20px;
    }
</style>
</div>
</body>


</html>

