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
                                                                                <label class="col-md-3 control-label">咨询对象
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="md-checkbox-inline" id="objects">

                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_00" name="tags" value="34" class="md-check">
                                                                                            <label for="checkbox_00">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>幼儿</label>
                                                                                        </div>

                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_01" name="tags" value="34" class="md-check">
                                                                                            <label for="checkbox_01">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>小学</label>
                                                                                        </div>

                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_02" name="tags" value="34" class="md-check">
                                                                                            <label for="checkbox_02">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>初中</label>
                                                                                        </div>

                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_03" name="tags" value="34" class="md-check">
                                                                                            <label for="checkbox_03">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>高中</label>
                                                                                        </div>

                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                            <div class="form-group form-md-checkboxes">
                                                                                <label class="col-md-3 control-label">擅长领域
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <div class="md-checkbox-inline" id="field">
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_0" name="tags" value="1" class="md-check">
                                                                                            <label for="checkbox_0">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>
                                                                                                二孩
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_1" name="tags" value="2" class="md-check">
                                                                                            <label for="checkbox_1">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>儿童发展</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_2" name="tags" value="3" class="md-check">
                                                                                            <label for="checkbox_2">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>父母成长</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_3" name="tags" value="4" class="md-check">
                                                                                            <label for="checkbox_3">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>行为纠正</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_4" name="tags" value="5" class="md-check">
                                                                                            <label for="checkbox_4">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>习惯培养</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_5" name="tags" value="6" class="md-check">
                                                                                            <label for="checkbox_5">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>育儿困惑</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_6" name="tags" value="7" class="md-check">
                                                                                            <label for="checkbox_6">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>亲子冲突</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_7" name="tags" value="8" class="md-check">
                                                                                            <label for="checkbox_7">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>厌学</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_8" name="tags" value="9" class="md-check">
                                                                                            <label for="checkbox_8">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>自闭症</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_9" name="tags" value="10" class="md-check">
                                                                                            <label for="checkbox_9">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>多动症</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_10" name="tags" value="11" class="md-check">
                                                                                            <label for="checkbox_10">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>不合群</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_11" name="tags" value="12" class="md-check">
                                                                                            <label for="checkbox_11">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>敏感自卑</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_12" name="tags" value="13" class="md-check">
                                                                                            <label for="checkbox_12">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>怕生</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_13" name="tags" value="14" class="md-check">
                                                                                            <label for="checkbox_13">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>注意力不集中</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_14" name="tags" value="15" class="md-check">
                                                                                            <label for="checkbox_14">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>阅读障碍</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_15" name="tags" value="16" class="md-check">
                                                                                            <label for="checkbox_15">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>顽皮淘气</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_16" name="tags" value="17" class="md-check">
                                                                                            <label for="checkbox_16">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>顽皮淘气</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_17" name="tags" value="18" class="md-check">
                                                                                            <label for="checkbox_17">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>孩子叛逆
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_18" name="tags" value="19" class="md-check">
                                                                                            <label for="checkbox_18">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>小动作</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_19" name="tags" value="20" class="md-check">
                                                                                            <label for="checkbox_19">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>网瘾</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_20" name="tags" value="21" class="md-check">
                                                                                            <label for="checkbox_20">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>不爱学习</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_21" name="tags" value="22" class="md-check">
                                                                                            <label for="checkbox_21">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>攀比心强</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_22" name="tags" value="23" class="md-check">
                                                                                            <label for="checkbox_22">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>自控力差</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_23" name="tags" value="24" class="md-check">
                                                                                            <label for="checkbox_23">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>早恋</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_24" name="tags" value="25" class="md-check">
                                                                                            <label for="checkbox_24">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>沟通问题</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_25" name="tags" value="26" class="md-check">
                                                                                            <label for="checkbox_25">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>依赖性强</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_26" name="tags" value="27" class="md-check">
                                                                                            <label for="checkbox_26">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>考试焦虑</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_27" name="tags" value="28" class="md-check">
                                                                                            <label for="checkbox_27">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>胆小懦弱</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_28" name="tags" value="29" class="md-check">
                                                                                            <label for="checkbox_28">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>青春期</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_29" name="tags" value="30" class="md-check">
                                                                                            <label for="checkbox_29">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>内向</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_30" name="tags" value="31" class="md-check">
                                                                                            <label for="checkbox_30">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>不听话</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_31" name="tags" value="32" class="md-check">
                                                                                            <label for="checkbox_31">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>做事拖拉</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_32" name="tags" value="33" class="md-check">
                                                                                            <label for="checkbox_32">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>坏习惯</label>
                                                                                        </div>
                                                                                        <div class="md-checkbox hc-checkbox">
                                                                                            <input type="checkbox" id="checkbox_33" name="tags" value="34" class="md-check">
                                                                                            <label for="checkbox_33">
                                                                                                <span class="inc"></span>
                                                                                                <span class="check"></span>
                                                                                                <span class="box"></span>考前紧张</label>
                                                                                        </div>
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
                                                                                                <font style="vertical-align: inherit;">
                                                                                                    <font style="vertical-align: inherit;">元/次(每次30分钟)
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
                                                                                                <font style="vertical-align: inherit;">
                                                                                                    <font style="vertical-align: inherit;">元/次(每次30分钟)
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
                                                                                                <font style="vertical-align: inherit;">
                                                                                                    <font style="vertical-align: inherit;">元/次(每次30分钟)
                                                                                                    </font>
                                                                                                </font>
                                                                                            </span>
                                                                                        </span>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">面对面咨询地址
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <textarea class="form-control" id="city" name="form_city" rows="3"></textarea>
                                                                                    <span class="error_message"></span>
                                                                                    <div class="form-control-focus"></div>

                                                                                </div>
                                                                            </div>
                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">预约须知
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                </label>
                                                                                <div class="col-md-9">
                                                                                    <textarea class="form-control" id="notNull1" name="form_info" rows="5"></textarea>
                                                                                    <span class="error_message"></span>
                                                                                    <div class="form-control-focus"></div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="form-group ">
                                                                                <label class="col-md-3 control-label">是否上线
                                                                                    <span class="required" aria-required="true">*</span>
                                                                                </label>

                                                                                <div class="switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
                                                                                    <input id="onlineStatus" type="checkbox" name="onlineStatus" />
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


    </html>
