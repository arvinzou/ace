<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html lang="en">


    <head>
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="${cfg.sys_name}" />
        <!--公共部分开始-->
        <jsp:include page="/dynamic/common/base.jsp" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css" />
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css"/>
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"/>
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout3/css/custom.min.css" />
        <!--公共部分结束-->

        <!--私有部分开始-->
        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/course/add/css/style.css">
        <!--私有部分结束-->
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
                                            <span>课程管理</span>
                                        </li>
                                    </ul>
                                    <div class="page-content-inner">

                                        <!---==============================================-->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <!-- BEGIN SAMPLE TABLE PORTLET-->
                                                <div class="portlet light">
                                                    <div class="portlet-title">
                                                        <div class="caption">
                                                            创建课程
                                                        </div>
                                                        <div class="actions">

                                                        </div>
                                                    </div>
                                                    <div class="portlet-body" id="courseSource">
                                                        <div class="form-panel">
                                                            <!--具体界面元素开始-->
                                                            <form class="form-horizontal" id="fm-add" role="form">
                                                                <div class="form-body">
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>课程名称</label>
                                                                        <div class="col-md-10">
                                                                            <input type="text" class="form-control" name="name" maxlength="28" placeholder="请输入课程名称（建议字数在14个字以内，不超过28个字)">
                                                                            <span class="help-block"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>课程封面</label>
                                                                        <div class="col-md-10">
                                                                            <div class="row">
                                                                                <img class=" form_imagePhotoUrl" id="courseCover" style="max-width:100%;cursor: pointer;" data-toggle="modal" data-xsize="375" data-ysize="210" data-cover="courseCover"
                                                                                     data-target="#img-uploader" src="${pageContext.request.contextPath}/dynamic/service/course/add/img/no-img.jpg?v=${cfg.version}">
                                                                            </div>
                                                                            <div class="tips">建议图片尺寸750*420px或16:9，JPG、PNG、GIF格式，大小不超过2M</div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>课程简介</label>
                                                                        <div class="col-md-10">
                                                                            <div style="text-align:left">
                                                                                <textarea name="introduce" id="introduce" class="introduction"></textarea>
                                                                            </div>
                                                                            <span class="help-block"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>课程价格</label>
                                                                        <div class="col-md-10">
                                                                            <div class="radio-group-container">
                                                                                <label class="mt-radio mt-radio-outline">
                                                                                    <input type="radio" name="costType" value="0"  onclick="payTypeCheck('noPay');">免费
                                                                                    <span></span>
                                                                                </label>
                                                                                <label class="mt-radio mt-radio-outline">
                                                                                    <input type="radio" name="costType" value="1" checked onclick="payTypeCheck('pay');">付费
                                                                                    <span></span>
                                                                                </label>
                                                                                <div class="price-panel">
                                                                                    <div class="row">
                                                                                        <label class="col-md-3 control-label">课程售价</label>
                                                                                        <div class="col-md-9">
                                                                                            <input name="primeCost" type="text" style="width:70%" class="form-control" placeholder="请输入课程原价（单位：元）" onchange="if(!/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/.test(this.value) || this.value == 0){alert('请输入合法金额！');this.value='';}"/>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <label class="col-md-3 control-label">划线价格</label>
                                                                                        <div class="col-md-9">
                                                                                            <input name="cost" type="text" style="width:70%" class="form-control" placeholder="请输入划线价格（单位：元）" onchange="if(!/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/.test(this.value) || this.value == 0){alert('请输入合法金额！');this.value='';}"/>
                                                                                            <span class="help-block" style="text-align:left;font-size:12px;padding-top:10px">划线价是一种常见的促销方式，您可以通过设置划线价让您的课程价格看起来更加优惠低廉，吸引更多用户进行购买。</span>
                                                                                        </div>

                                                                                    </div>
                                                                                </div>



                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>课程对象</label>
                                                                        <div class="col-md-10">
                                                                            <div class="radio-group-container" id="dict-149">

                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">
                                                                            <span class="label-red">*</span>针对能力</label>
                                                                        <div class="col-md-10">

                                                                            <div class="radio-group-container" id="dict-150">

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-md-2 control-label">适合谁听</label>
                                                                        <div class="col-md-10">
                                                                            <input type="text" class="form-control" name="applicationObject" maxlength="20" placeholder="请输入适合谁听（您可以输入心理老师、心理学爱好者、父母等）">
                                                                            <span class="help-block"> </span>
                                                                        </div>
                                                                    </div>


                                                                </div>
                                                                <div class="form-actions">
                                                                    <div class="row">
                                                                        <div class="col-md-offset-3 col-md-7">
                                                                            <button class="btn  btn-lg  green" type="submit" style="width:30%">保存</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!--具体界面元素结束-->
                                                    </div>
                                                </div>
                                                <!-- END SAMPLE TABLE PORTLET-->
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
    <!--公共部分开始-->
    <jsp:include page="../../../common/footer.jsp" />
    <!--公共部分结束-->
    <!--私有部分开始-->
    <script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
    <script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/portal/js/main/menu4.js" type="text/javascript"></script>
    <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/plupload/plupload.full.min.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/upload.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/dynamic/service/course/add/js/act.js?v=${cfg.version}"></script>

    <!--私有部分结束-->



    <script id="tpl-dict-149" type="text/template">
        {@each data as item, index}
            {@if item.CODE!=''}
            <label class="mt-radio mt-radio-outline">
                <input type="radio" name="objects" value="\${item.CODE}">\${item.NAME}
                <span></span>
            </label>
            {@/if}
        {@/each}
    </script>

    <script id="tpl-dict-150" type="text/template">
        {@each data as item, index} {@if item.CODE!=''}
        <label class="mt-radio mt-radio-outline">
            <input type="radio" name="purport" value="\${item.CODE}">\${item.NAME}
            <span></span>
        </label>
        {@/if} {@/each}
    </script>

    </html>
