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
    <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/uploadImg.css">
    <script src="${pageContext.request.contextPath}/content/service/testCase/js/act.js?v=${cfg.version}"></script>
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
                                                                <span class="caption-subject font-green bold uppercase">心理测试</span>
                                                            </div>

                                                            <div class="actions">
                                                                <a onclick="javascript:createTestCase()"
                                                                   class="btn btn-circle btn-success btn-sm">
                                                                    <i class="fa fa-plus"></i>
                                                                    创建试题
                                                                </a>
                                                                <a onclick="javascript:deleteTestCase()"
                                                                   data-repeater-delete=""
                                                                   class="btn btn-circle btn-danger">
                                                                    <i class="fa fa-close"></i> 删除试题</a>
                                                            </div>
                                                        </div>
                                                        <div class="portlet-body">
                                                            <div class="mt-element-card mt-element-overlay">
                                                                <div class="row">

                                                                    <div class="col-md-12">


                                                                        <div class="portlet-body">
                                                                            <div class="table-scrollable">
                                                                                <table class="table table-striped table-hover">
                                                                                    <thead>
                                                                                    <tr>
                                                                                        <th width="10%">#</th>
                                                                                        <th width="70%">题目</th>
                                                                                        <th width="20%">操作</th>

                                                                                    </tr>
                                                                                    </thead>
                                                                                    <tbody id="evaluatCaseList">
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                            <ul class="pagination"
                                                                                id="pagination1"></ul>
                                                                        </div>

                                                                    </div>

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


<div class="modal bs-example-modal-lg" data-backdrop="static" id="createTest" tabindex="-1" role="dialog"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">创建测试</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="from-body">
                            <div class="form-group">
                                <div class="row">
                                    <label class="col-md-3 control-label">题目
                                        <span class="required" aria-required="true">*</span>
                                    </label>
                                    <div class="col-md-9">
                                            <textarea class="form-control form_title" id="form_title"
                                                      name="form_title" rows="5"></textarea>
                                        <span class="error_message"></span>
                                        <div class="form-control-focus"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="table-scrollable">
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width=50%"> 评价</th>
                                        <th width="30%"> 分值</th>
                                        <th width="20%"> 操作</th>
                                    </tr>
                                    </thead>
                                    <tbody class="case-list">
                                    <tr>
                                        <th width="50%"><textarea rows="1"
                                                                  class="textareaHeight  form_name"></textarea></th>
                                        <th width="30%"><input class="form_optionScore" type="text">分</th>
                                        <th class=" primary-link" width="20%"></th>
                                    </tr>
                                    <tr>
                                        <th width="50%"><textarea rows="1"
                                                                  class="textareaHeight  form_name"></textarea></th>
                                        <th width="30%"><input class="form_optionScore" type="text">分</th>
                                        <th class=" primary-link" width="20%"></th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2 col-md-offset-5">
                                <button type="button" id="add-caseSub" class=" col-md-12 btn btn-circle btn-success">添 加
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-default">取消</button>

                <button type="button" class="btn btn-info submit_btn" flag="true">提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>


<script id="temp_evaluatCaseList" type="text/template">
    {@each data as item,index}
    <tr>
        <td width="10%">
            <input data-id="\${item.id}" type="checkbox" id="checkbox1" class="md-check">
        </td>
        <td width="70%"> \${item.title}</td>
        <td width="20%"><a onclick="javascript:active('\${item.id}')" class="primary-link">修改信息</a></td>
    </tr>
    {@/each}
</script>

<style>
    .textareaHeight {
        overflow-y: hidden;
    }

    tbody th {
        padding: 0px !important;
    }

    tbody input, tbody textarea {
        border: none;
        height: 40px;
        padding: 8px;
    }

    tbody textarea {
        width: 100%;
    }

    .delectBtn {
        text-align: center;
        line-height: 40px !important;
        cursor: pointer;
    }

    .error_message {
        color: red;
    }

    input[type="checkbox"] {
        width: 18px;
        height: 18px;
        cursor: pointer;
        margin: 0;
    }
</style>

</html>

