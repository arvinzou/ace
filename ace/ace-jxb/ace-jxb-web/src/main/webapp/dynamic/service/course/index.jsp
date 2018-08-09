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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/course/css/style.css">
    <script src="${pageContext.request.contextPath}/dynamic/service/course/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
</head>

<body>

<!--隐藏存放ID-->
<input type="text" hidden value="" id="auditId"/>
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

                                <div class="portlet box yellow">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i>课程管理 </div>
                                        <div class="tools">
                                            <a href="javascript:;" class="collapse" data-original-title="" title=""> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <div class="tabbable-line">
                                            <ul class="nav nav-tabs ">
                                                <li class="active" onclick="changeCourseType('1');">
                                                    <a href="#tab_15_1" data-toggle="tab" aria-expanded="true"> 单节课程 </a>
                                                </li>
                                                <li class="" onclick="changeCourseType('2');">
                                                    <a href="#tab_15_1" data-toggle="tab" aria-expanded="false"> 系列课程 </a>
                                                </li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab_15_1">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                            <div class="portlet light ">
                                                                <div class="portlet-title">
                                                                    <div class="caption">
                                                                        <i class="icon-social-dribbble font-green"></i>
                                                                        <span class="caption-subject font-green bold uppercase"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">课程管理</font></font></span>
                                                                    </div>
                                                                    <div class="make_course" id="makeCourse"></div>
                                                                    <div class="create_course" id="createCourse"></div>
                                                                </div>
                                                                <div class="portlet-body">
                                                                    <div class="table-scrollable">
                                                                        <table class="table table-hover">
                                                                            <thead>
                                                                            <tr>
                                                                                <th width="5%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> ＃ </font></font></th>
                                                                                <th width="30%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 课程名称 </font></font></th>
                                                                                <th width="10%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 状态 </font></font></th>
                                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 上架时间 </font></font></th>
                                                                                <th width="10%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 购买数 </font></font></th>
                                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 审核状态 </font></font></th>
                                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 操作 </font></font></th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody id="courseList">

                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- END SAMPLE TABLE PORTLET-->
                                                        </div>
                                                        <ul class="pagination" id="pagination1" style="padding-left: 15px;"></ul>
                                                    </div>
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

<script id="list" type="text/template">
    {@each data as item, index}
    <tr>
        <td width="5%"><font style="vertical-align: inherit;">
            <input type="radio" name="course" value="\${item.id}"/>
            <font style="vertical-align: inherit;">\${parseInt(index)+1} </font></font>
        </td>
        <td width="30%">
            <img src="\${item.cover}" style="width: 80px;height: 60px;"/>
            <span>\${item.name}</span>
        </td>
        <td width="10%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">  </font></font></td>
        <td width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${item.createDate} </font></font></td>
        <td width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${item.duration} </font></font></td>
        <td width="10%">
            {@if item.status==1}
            <span class="label label-sm label-info"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 待审核 </font></font></span>
            {@else if item.status==2}
            <span class="label label-sm label-warning"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 审核通过 </font></font></span>
            {@else}
            <span class="label label-sm label-danger"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 审核不通过 </font></font></span>
            {@/if}
        </td>
        <td width="15%">
            <a class="operation" href="">编辑</a>
            <a class="operation" href="">下架</a>
            <a class="operation" href="javascript:void(0);" onclick="deleteCourse('\${item.id}');">删除</a>
            <a class="operation" href="">查看评论</a>
            <a class="operation" href="">购买明细</a>
        </td>
    </tr>
    {@/each}
</script>

<script id="makeTemp" type="text/template">
    {@if data.type == '1'}
    <a href="javascript:;" class="btn red" onclick="makecourse();">制作课程<i class="fa fa-edit"></i></a>
    {@else if data.type == '2'}
    <a href="javascript:;" class="btn red" onclick="makeSeriesCourse();">制作课程<i class="fa fa-edit"></i></a>
    {@/if}
</script>

<script id="createTemp" type="text/template">
    <a href="/jxb/dynamic/service/course/create.jsp" class="btn green">创建课程<i class="fa fa-plus"></i></a>
</script>
</body>

<style>
    .modal .headbox {
        width: 150px !important;
        height: 150px !important;
        border-radius: 50% !important;
        overflow: hidden;
        margin: 0 auto;
    }

    .modal-body {
        font-size: 16px;
        line-height: 24px;
        text-align: justify
    }

    .modal img {
        width: 100%;
        height: 100%;
    }
</style>
</html>