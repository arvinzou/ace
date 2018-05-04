<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
    <jsp:include page="../common/base.jsp" />
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?version=${cfg.version}"></script>
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

                                <!---==============================================-->
                                <div class="row">
                                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                        <div class="dashboard-stat2 ">
                                            <div class="display">
                                                <div class="number">
                                                    <h3 class="font-red-haze">
                                                        <span data-counter="counterup" data-value="0" id="dept">0</span>
                                                        <small class="font-red-haze">个</small>
                                                    </h3>
                                                    <small>模板</small>
                                                </div>
                                                <div class="icon">
                                                    <i class="fa fa-bank"></i>
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
                                                        <span data-counter="counterup" data-value="" id="personage">0</span>
                                                        <small class="font-green-sharp">个</small>
                                                    </h3>
                                                    <small>页面</small>
                                                </div>
                                                <div class="icon">
                                                    <i class="fa fa-user-circle-o"></i>
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
                                                        <span data-counter="counterup" data-value="0" id="feedback">0</span>
                                                        <small class="font-blue-sharp">个</small>
                                                    </h3>
                                                    <small>图片</small>
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
                                                        <span data-counter="counterup" data-value="0" id="activity">0</span>
                                                        <small class="font-purple-sharp">次</small>
                                                    </h3>
                                                    <small>留言</small>
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