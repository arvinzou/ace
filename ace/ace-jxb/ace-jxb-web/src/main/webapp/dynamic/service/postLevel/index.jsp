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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/reservation/css/style.css">
    <script src="${pageContext.request.contextPath}/content/service/postLevel/js/act.js?v=${cfg.version}"></script>
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
                                                                <span class="caption-subject font-green bold uppercase"><font
                                                                        style="vertical-align: inherit;"><font
                                                                        style="vertical-align: inherit;">老师等级配置</font></font></span>
                                                            </div>
                                                            <div class="actions">
                                                                <a onclick="javascript:createLevel()"
                                                                   class="btn btn-circle btn-success btn-sm">
                                                                    <i class="fa fa-plus"></i>
                                                                    添加配置
                                                                </a>
                                                            </div>

                                                        </div>
                                                        <div class="portlet-body">
                                                            <div class="mt-element-card mt-element-overlay">
                                                                <div class="row">

                                                                    <%--content--%>


                                                                    <div class="table-scrollable">
                                                                        <table class="table table-hover">
                                                                            <thead>
                                                                            <tr class="uppercase">

                                                                                <%--<th> 序号</th>--%>
                                                                                <th> 名称</th>
                                                                                <th> 营业额要求</th>
                                                                                <th> 发展人数要求</th>
                                                                                <th>分成</th>
                                                                                <th colspan="2">操作</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody class="level_list">

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
</div>
</body>
<script id="temp_level_list" type="text/template">
    {@each data as item}
    <tr data-id="\${item.id}">
        <%--<td><input type="text" class="postName" value="\${item.postIndex}"></td>--%>
        <td><input type="text" class="postName" value="\${item.postName}"></td>
        <td><input type="text" class="turnover" value="\${item.turnover}"></td>
        <td><input type="text" class="counselorNum" value="\${item.counselorNum}"></td>
        <td><input type="text" class="ratio" value="\${item.ratio}"></td>
        <td colspan="2"><span class="delectBtn primary-link" width="15%">删除</span></td>
    </tr>
    {@/each}
</script>

<style>
    input {
        height: 30px;
        border: 0px;
    }

    .delectBtn, .insertBtn, .cancelBtn {
        cursor: pointer;
    }
</style>


</html>

