<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>企业会员</title>
</head>

<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <form action="#" id="fm-search" >
                <div class="col-md-1 toolbar">


                </div>

                <div class="col-md-5">

                </div>


                <div class="col-md-6">

                    <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" authority="false"  onclick="setParams('status','');">全部</button>
                        <button type="button" class="btn btn-default" authority="false"  onclick="setParams('status','1');">待审</button>
                        <button type="button" class="btn btn-default" authority="false" onclick="setParams('status','2');">通过</button>
                        <button type="button" class="btn btn-default" authority="false" onclick="setParams('status','3');">驳回</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="name"
                               class="form-control"
                               placeholder="请输入企业名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
                                    authority="${pageContext.request.contextPath}/fopFlowRecord/findFopFlowRecordList">
									搜索
							</button>
						</span>
                    </div>

                </div>

            </form>
        </div>

        <table id="grid-table"></table>

        <div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<jsp:include page="/dynamic/common/footer.jsp" />

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<script
        src="${pageContext.request.contextPath}/content/service/quitRecord/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/quitRecord/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/quitRecord/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/quitRecord/view.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
</body>
</html>