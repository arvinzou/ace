<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>群英考勤数据</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">
    <div class="portlet-body">
        <div class="row custom-toolbar">
            <form action="#" id="fm-search">
                <div class="col-md-9 toolbar">

                </div>
                <div class="col-md-3">


                    <div class="input-group">
                        <input type="text"
                               name="realname"
                               class="form-control"
                               placeholder="请输入姓名">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/qyAttRecord/findQyAttRecordList">
									搜索
							</button>
						</span>
                    </div>

                </div>

            </form>
        </div>

        <table id="grid-table"></table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>

<%--查看详情--%>
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 75%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm-preview">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--详情juicer模板--%>
<script id="tpl-preview" type="text/template">
    <div class="form-group">
        <label class="col-md-2 view-label">姓名</label>
        <div class="col-md-10">
            {@if data.o.headimgurl!='' && data.o.headimgurl!=null && data.o.headimgurl!=undefined}
            <img src="\${data.o.photoUrl}" class="cover"/>
            {@else}
            <img src="${pageContext.request.contextPath}/content/common/img/default_header.png" class="cover"/>
            {@/if}
            <a>\${data.o.name}</a>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">手机号码</label>
        <div class="col-md-10">
            \${data.o.mobile}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">身份证号码</label>
        <div class="col-md-10">
            \${data.o.idCard}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">介绍</label>
        <div class="col-md-10">
            \${data.o.introduce}
        </div>
    </div>
</script>


<script id="tpl-check-group" type="text/template">

    {@each data.list as item, index}
    {@if item.CODE}
    <button type="button" authority="false" class="btn btn-default"
            onclick="setParams('\${data.key}','\${item.CODE}');">\${item.NAME}
    </button>
    {@else}
    <button type="button" authority="false" class="btn btn-default" onclick="setParams('\${data.key}','');">全部</button>
    {@/if}

    {@/each}

</script>

<div class="modal fade" role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Excel导入</h4>
            </div>
            <div class="modal-body">
                <div id="uploader">
                </div>
                <div style="margin:5px">
                    <a href="roadSection.xls" style="color:red">下载模板</a>.<br>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<%--easyui--%>
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
<%--导出--%>
<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/qyAttRecord/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/qyAttRecord/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/qyAttRecord/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/qyAttRecord/view.js?version=${cfg.version}"></script>

<%--权限管理--%>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

</body>
<style>
    /* css code area*/
</style>
</html>