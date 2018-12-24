<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>公告管理</title>
</head>
<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <form action="#" id="fm-search" >
            <div class="col-md-2 toolbar">

                <button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/processNode/insertProcessNode"></button>

            </div>
            <div class="col-md-7">

                <div class="btn-group" role="group"  style="float:right;padding-right:5px">
                     类别：<input name="category" class="easyui-combobox" style="width: 200px"
                            data-options="
                            url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                            method:'get',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'" />
                </div>


            </div>

            <div class="col-md-3">
                    <div class="input-group">
                        <input type="text"
                               name="name"
                               class="form-control"
                               placeholder="请输入名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
                                    authority="${pageContext.request.contextPath}/processNode/findProcessNodeList">
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

<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">节点名称</label>
            <div class="col-md-10">
                \${data.curNodeName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">节点图标</label>
            <div class="col-md-10">
                \${data.nodeIcon}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">节点序号</label>
            <div class="col-md-10">
                \${data.sequence}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">节点详情封面图片</label>
            <div class="col-md-10">
                \${data.coverImage}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">上一节点id</label>
            <div class="col-md-10">
                \${data.priorNodeId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">下一节点id</label>
            <div class="col-md-10">
                \${data.nextNodeId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${data.remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态</label>
            <div class="col-md-10">
                \${data.status}
            </div>
        </div>
         <div class="form-group">
             <label class="col-md-2 view-label">创建人编号</label>
             <div class="col-md-10">
                 \${data.createUserId}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">创建人姓名</label>
             <div class="col-md-10">
                 \${data.createUserName}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">入库日期</label>
             <div class="col-md-10">
                 \${data.createDate}
             </div>
         </div>

         <div class="form-group">
             <label class="col-md-2 view-label">最后更新人编号</label>
             <div class="col-md-10">
                 \${data.lastModifyUserId}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">最后更新人姓名</label>
             <div class="col-md-10">
                 \${data.lastModifyUserName}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">最后更新时间</label>
             <div class="col-md-10">
                 \${data.lastModifyDate}
             </div>
         </div>
    </div>
</script>



<div class="modal fade" role="dialog" id="modal-detail">
    <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详情</h4>
            </div>

            <div class="modal-body">

<form class="form-horizontal"  id="fm-detail" role="form">
<div class="form-body">
<h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem">
            <span class="labelItemHeader">节点名称</span>
            <br>
            <span id="curNodeName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">节点图标</span>
            <br>
            <span id="nodeIcon"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">节点序号</span>
            <br>
            <span id="sequence"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">节点详情封面图片</span>
            <br>
            <span id="coverImage"></span>
        </div>
        <div class="labelItem  hide">
            <span class="labelItemHeader">上一节点id</span>
            <br>
            <span id="priorNodeId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">上一节点名称</span>
            <br>
            <span id="priorNodeName"></span>
        </div>
        <div class="labelItem  hide">
            <span class="labelItemHeader">下一节点id</span>
            <br>
            <span id="nextNodeId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">下一节点名称</span>
            <br>
            <span id="nextNodeName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">备注</span>
            <br>
            <span id="remark"></span>
        </div>
        <div class="labelItem  hide">
            <span class="labelItemHeader">状态</span>
            <br>
            <span id="status"></span>
        </div>
    </div>
    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">创建人编号</span>
            <br>
            <span id="createUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">创建人姓名</span>
            <br>
            <span id="createUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">入库日期</span>
            <br>
            <span id="createDate"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后更新人编号</span>
            <br>
            <span id="lastModifyUserId"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后更新时间</span>
            <br>
            <span id="lastModifyDate"></span>
        </div>
    </div>
</form>
</div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<div class="modal fade"  role="dialog" id="modal-upload">
    <div class="modal-dialog" role="document" style="width: 830px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片上传</h4>
            </div>
            <div class="modal-body">

                <div id="uploader">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade"  role="dialog" id="modal-file">
    <div class="modal-dialog" role="document" style="width: 830px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">图片</h4>
            </div>
            <div class="modal-body">

                <div id="load" class="loading"></div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


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

<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>




<script
        src="${pageContext.request.contextPath}/content/service/processNode/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/processNode/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/processNode/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/processNode/view.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
</body>
</html>