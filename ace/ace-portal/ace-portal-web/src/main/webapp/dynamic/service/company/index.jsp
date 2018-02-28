<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>企业单位基本信息</title>
</head>
<jsp:include page="../../common/header.jsp"/>
<script type="text/javascript">
</script>
<body>
<div class="fn-pl40 fn-pr40 fn-pt30 fn-pb30 fn-clear">
    <div class="pz-form">
        <form id="j-editform" onsubmit="return false">
            <div class="wrap fn-clear fn-pb20">
                <div class="group2">
                    <div class="formtitle fn-mb15">基本信息</div>
                    <div class="rows row100">
                        <div class="row-title">机构全称</div>
                        <div class="row-content" data-field="departmentName"></div>
                    </div>
                    <div class="rows row100">
                        <div class="row-title">机构简称</div>
                        <div class="row-content" data-field="shortName"></div>
                    </div>
                    <div class="rows row100">
                        <div class="row-title">LOGO</div>
                        <div class="row-content">
                            <div id="j-row-img-logo">
                                <div class="fn-hide j-uploader-tip"><p><em></em></p></div>
                                <div id="j-uploader-rst-logo"></div>
                                <div id="j-cover-logo" class="xcy-cutimg">
                                    <label class="upbtn">
                                        <div class="imgbar fn-textleft fn-hide">
                                            <span class="close"><i class="pz-icon icon-close"></i></span>
                                            <span class="logo"></span>
                                        </div>
                                        <div class="fn-pt25">
                                            <i class="pz-icon icon-img"></i>
                                            <p class="fn-textcenter fn-mt5">点击选择LOGO图标</p>
                                        </div>

                                    </label>
                                    <div class="fn-mt5 pz-color-gray">支持88px*88px图片大小</div>
                                </div>

                            </div>
                            <div id="j-uploader-cnt-logo"></div>

                        </div>
                    </div>



                </div>
                <div class="group2 fn-pl40 fn-clear">
                    <div class="formtitle fn-mb15">水印信息</div>
                    <div class="rows row100">
                        <div class="row-title">图片水印1</div>
                        <div class="row-content">
                            <div id="j-videocover1" class="xcy-cutimg">
                                <label class="upbtn">
                                    <div class="imgbar fn-textleft fn-hide">
                                        <span class="close"><i class="pz-icon icon-close"></i></span>
                                        <span class="logo fn-pt30"></span>
                                    </div>
                                    <div class="fn-pt25">
                                        <i class="pz-icon icon-img"></i>
                                        <p class="fn-textcenter fn-mt5">点击选择直播水印</p>
                                    </div>
                                    <div class="j-file-input fn-hide">
                                        <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png">
                                    </div>
                                </label>
                            </div>
                            <div class="fn-mt5 pz-color-gray">图片大小：92px*22px<br>图片格式：png</div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="actions actions-transparent fn-pt20">
                <input type="submit" class="pz-btn btn-success btn-big" value="保存修改">
            </div>
        </form>
    </div>
</div>
<jsp:include page="../../common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/company/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/view.js?version=${cfg.version}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/company/style.css?version=${cfg.version}" />
</body>
</html>