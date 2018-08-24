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
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="description" />

        <jsp:include page="../../common/base.jsp" />
        <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.css">
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/reservation/css/style.css">--%>
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
                                            <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
                                            <i class="fa fa-circle"></i>
                                        </li>
                                        <li>
                                            <span>预约设置</span>
                                        </li>
                                    </ul>
                                    <div class="page-content-inner">

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="portlet light portlet-fit ">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            预约设置
                                                        </div>
                                                        <div class="actions">
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="portlet-body">
                                                        <div class="mt-element-card mt-element-overlay">
                                                            <div class="row" id="reservtionInfo">

                                                                <%--content--%>
                                                                    <div class="form-horizontal" novalidate="novalidate">
                                                                        <div class="form-body">
                                                                            <div class="form-group form-md-checkboxes">
                                                                                <label class="col-md-3 control-label"><span
                                                                                        class="required"
                                                                                        aria-required="true">*</span>咨询对象

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="md-checkbox-inline" id="objects">


                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                            <div class="form-group form-md-checkboxes">
                                                                                <label class="col-md-3 control-label"><span
                                                                                        class="required"
                                                                                        aria-required="true">*</span>擅长领域

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="md-checkbox-inline" id="field">


                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">电话咨询

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="input-group">
                                                                                        <div class="input-group-control">
                                                                                            <input type="text" class="form-control" name="type1" id="money_null">
                                                                                            <span class="error_message"></span>
                                                                                        </div>
                                                                                        <span class="input-group-btn btn-right">
                                                                                            <span type="button" class="btn green-haze dropdown-toggle" data-toggle="dropdown" aria-expanded="false">

                                                                                                    元/次(每次30分钟)
                                                                                                    </font>
                                                                                                </font>
                                                                                            </span>
                                                                                        </span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">微信咨询

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="input-group">
                                                                                        <div class="input-group-control">
                                                                                            <input type="text" class="form-control" name="type2" id="money1_null">
                                                                                            <span class="error_message"></span>
                                                                                        </div>
                                                                                        <span class="input-group-btn btn-right">
                                                                                            <span type="button" class="btn green-haze dropdown-toggle" data-toggle="dropdown" aria-expanded="false">

                                                                                                    元/次(每次30分钟)
                                                                                                    </font>
                                                                                                </font>
                                                                                            </span>
                                                                                        </span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">面对面咨询

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="input-group">
                                                                                        <div class="input-group-control">
                                                                                            <input type="text" class="form-control" name="type3" id="money2_null">
                                                                                            <span class="error_message"></span>
                                                                                        </div>
                                                                                        <span class="input-group-btn btn-right">
                                                                                            <span type="button" class="btn green-haze dropdown-toggle" data-toggle="dropdown" aria-expanded="false">

                                                                                                    元/次(每次30分钟)
                                                                                                </font>
                                                                                            </span>
                                                                                        </span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                    面对面咨询地址
                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <textarea class="form-control" id="city" name="form_city" rows="3"></textarea>
                                                                                    <span class="error_message"></span>
                                                                                    <div class="form-control-focus"></div>

                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label"><span
                                                                                        class="required"
                                                                                        aria-required="true">*</span>预约须知

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <textarea class="form-control" id="notNull1" name="form_info" rows="5"></textarea>
                                                                                    <span class="error_message"></span>
                                                                                    <div class="form-control-focus"></div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label"><span
                                                                                        class="required"
                                                                                        aria-required="true">*</span>是否上线

                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="switch"
                                                                                         data-on-label="<i class='icon-ok icon-white'></i>"
                                                                                         data-off-label="<i class='icon-remove'></i>">
                                                                                        <input id="onlineStatus"
                                                                                               type="checkbox"
                                                                                               name="onlineStatus"/>
                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                        </div>
                                                                        <div class="form-actions">
                                                                            <div class="row">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn green submit_btn">
                                                                                        提交
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                    </div>

                                                                    <%--content--%>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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

    <style>
        .hc-checkbox {
            width: 11em;
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

        .studiologo+p {
            line-height: 1.05rem;
        }

        .input_style {
            border-bottom: 1px solid #c2cad8 !important;
        }

        .portlet-body {
            background-color: #fff !important;
            padding: 14px 98px 45px 20px !important;
        }
    </style>


    <script id="temp_field" type="text/template">
        {@each data as item,index}
        <div class="md-checkbox hc-checkbox">
            <input type="checkbox" id="checkbox_\${index}"
                   name="tags" value="34"
                   class="md-check">
            <label for="checkbox_\${index}">
                <span class="inc"></span>
                <span class="check"></span>
                <span class="box"></span>\${item.name}
            </label>
        </div>
        {@/each}
    </script>

    <script id="temp_objects" type="text/template">
        {@each data as item,index}
        <div class="md-checkbox hc-checkbox">
            <input type="checkbox" id="checkbox0_\${index}"
                   name="tags" value="34"
                   class="md-check">
            <label for="checkbox0_\${index}">
                <span class="inc"></span>
                <span class="check"></span>
                <span class="box"></span>\${item.name}
            </label>
        </div>
        {@/each}
    </script>

    </html>
