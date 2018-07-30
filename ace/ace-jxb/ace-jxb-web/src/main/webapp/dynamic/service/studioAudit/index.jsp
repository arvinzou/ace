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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/studioAudit/css/style.css">
    <script src="${pageContext.request.contextPath}/dynamic/service/studioAudit/js/act.js?v=${cfg.version}"></script>
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
                                <div class="row">
                                        <div class="col-md-12">
                                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                                            <div class="portlet light ">
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                        <i class="icon-social-dribbble font-green"></i>
                                                        <span class="caption-subject font-green bold uppercase"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">工作室审核</font></font></span>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="table-scrollable">
                                                        <table class="table table-hover">
                                                            <thead>
                                                            <tr>
                                                                <th width="5%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> ＃ </font></font></th>
                                                                <th width="20%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 名称 </font></font></th>
                                                                <th width="30%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 负责人 </font></font></th>
                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 级别 </font></font></th>
                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 状态 </font></font></th>
                                                                <th width="15%"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 操作 </font></font></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="audioList">

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END SAMPLE TABLE PORTLET-->
                                        </div>
                                    <ul class="pagination" id="pagination1" style="padding-left: 15px;"></ul>
                                </div>
                                <script id="list" type="text/template">
                                    {@each data as item,index}
                                    <tr>
                                        <td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${index}</font></font></td>
                                        <td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${item.name} </font></font></td>
                                        <td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${item.dutyName} </font></font></td>
                                        <td><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> \${item.level} </font></font></td>
                                        <td>
                                            {@if item.status==0}
                                                <span class="label label-sm label-info"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 待审核 </font></font></span>
                                            {@else if item.status==1}
                                                <span class="label label-sm label-warning"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 审核通过 </font></font></span>
                                            {@else}
                                                <span class="label label-sm label-danger"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 审核不通过 </font></font></span>
                                            {@/if}
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-outline btn-circle btn-sm purple" data-target="#myModal" data-toggle="modal" onclick="edit('\${item.id}');">
                                                <i class="fa fa-search"></i><font ><font> 查看 </font></font></a>
                                            <a href="javascript:;" class="btn btn-outline btn-circle red btn-sm blue" data-target="#opt" data-toggle="modal" onclick="setval('\${item.id}');">
                                                <i class="fa fa-edit"></i><font ><font> 审核 </font></font></a>
                                        </td>
                                    </tr>
                                    {@/each}
                                </script>
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


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="myModal"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">工作室详情</h4>
            </div>
            <div id="info">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="stdioInfo" type="text/template">
    <table>
        <tr class="str">
            <td width="15%">工作室名称</td>
            <td width="35%">\${info.name}</td>
            <td width="15%">logo</td>
            <td width="35%">
                <img src="\${info.logoImgUrl}" style="width: 80px; height: 80px;"/>
            </td>
        </tr>
        <tr class="commontr">
            <td  width="15%">等级</td>
            <td  width="35%">\${info.level}</td>
            <td  width="15%">负责人</td>
            <td  width="35%">\${info.dutyName}</td>
        </tr>
        <tr class="commontr">
            <td width="15%">状态</td>
            <td colspan="3">
                {@if info.status == 0}
                待审核
                {@else if info.status == 1}
                审核通过
                {@else}
                审核不通过
                {@/if}
            </td>
        </tr>
        <tr class="str">
            <td width="15%">内容</td>
            <td colspan="3">\${info.introduce}</td>
        </tr>
    </table>
</script>

<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="opt"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel1">工作室审核</h4>
            </div>
            <div id="operation">
                <input type="radio" name="radio" value="1"/>审核通过&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="radio" value="2"/>审核拒绝
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="audit();">确定</button>
            </div>
        </div>
    </div>
</div>

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