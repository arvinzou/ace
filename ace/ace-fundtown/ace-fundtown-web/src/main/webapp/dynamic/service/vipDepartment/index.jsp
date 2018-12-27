<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>入驻成员列表</title>
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

                <button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/vipDepartment/insertVipDepartment"></button>

            </div>
            <div class="col-md-7">

                <div class="btn-group" role="group"  style="float:right;padding-right:5px">
                      企业类型：<input name="type" class="easyui-combobox" style="width: 200px"
                                 data-options="
                                 url:'${portalPath}/dict/findListByCategoryId.do?categoryId=147&selected=false',
                                 method:'get',
                                 valueField:'code',
                                 textField:'name',
                                 panelHeight:'auto'">
                </div>


            </div>

            <div class="col-md-3">
                    <div class="input-group">
                        <input type="text"
                               name="departmentName"
                               class="form-control"
                               placeholder="请输入企业名称">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
                                    authority="${pageContext.request.contextPath}/vipDepartment/findVipDepartmentList">
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
            <label class="col-md-2 view-label">企业名称</label>
            <div class="col-md-10">
                \${data.departmentName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">企业类型</label>
            <div class="col-md-10">
                \${parseType(data.type)}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">所属机构名称</label>
            <div class="col-md-10">
                \${data.parentDepartmentName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">联系人手机号</label>
            <div class="col-md-10">
                \${data.contactMobile}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">法人代表</label>
            <div class="col-md-10">
                \${data.legalPersonName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册时间</label>
            <div class="col-md-10">
                \${data.regDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">注册资本(万元)</label>
            <div class="col-md-10">
                \${data.regCapital}
            </div>
        </div>
         <div class="form-group">
             <label class="col-md-2 view-label">入驻状态</label>
             <div class="col-md-10">
                 \${parseStatus(data.status)}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">系统标示</label>
             <div class="col-md-10">
                 \${data.syid}
             </div>
         </div>
         <div class="form-group">
             <label class="col-md-2 view-label">注册地址</label>
             <div class="col-md-10">
                 \${data.regAddr}
             </div>
         </div>

         <div class="form-group">
             <label class="col-md-2 view-label">附件资源</label>
             <div class="col-md-10">
                 <div id="attachment-list" class="row" style="padding:10px">
                     <div id='filelist-history'></div>
                     <div id='filelist'></div>
                     <div id='container'>
                         附件：<a id='pickfiles' href='javascript:;'>[添加附件]</a>
                         <a id='uploadfiles' href='javascript:;'>[上传]</a>
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
            <span class="labelItemHeader">企业名称</span>
            <br>
            <span id="departmentName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">企业类型</span>
            <br>
            <span id="type"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">所属机构名称</span>
            <br>
            <span id="parentDepartmentName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">联系人手机号</span>
            <br>
            <span id="contactMobile"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">法人代表</span>
            <br>
            <span id="legalPersonName"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">注册时间</span>
            <br>
            <span id="regDate"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">注册资本(万元)</span>
            <br>
            <span id="regCapital"></span>
        </div>
        <div class="labelItem ">
            <span class="labelItemHeader">入驻状态</span>
            <br>
            <%--1=入驻中、2=vip--%>
            <span id="status"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">系统标示</span>
            <br>
            <span id="syid"></span>
        </div>
    </div>
    <h5 class="header-title">注册地址</h5>
    <div id="regAddr" class="row" style="padding:10px">
    </div>

    <h5 class="header-title">附件资源</h5>
    <div id="attachment-list" class="row" style="padding:10px">
        <div id='filelist-history'></div>
        <div id='filelist'></div>
        <div id='container'>
            附件：<a id='pickfiles' href='javascript:;'>[添加附件]</a>
            <a id='uploadfiles' href='javascript:;'>[上传]</a>
        </div>
    </div>

    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader"></span>
            <br>
            <span id="createUserId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">创建的用户名</span>
            <br>
            <span id="createUserName"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader"></span>
            <br>
            <span id="createTime"></span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">最后修改时间</span>
            <br>
            <span id="lastModifyTime"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后修改用户ID</span>
            <br>
            <span id="lastModifyUserId"></span>
        </div>
        <div class="labelItem hide">
            <span class="labelItemHeader">最后修改用户名</span>
            <br>
            <span id="lastModifyUserName"></span>
        </div>
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

<div class="modal fade" role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">信息审核</h4>
            </div>

            <div class="modal-body">
                <form action="/vipDepartment/audit" id="fm-audit">
                        入驻节点： <input name="nodeId" class="easyui-combobox" style="width: 200px"
                                     data-options="
                            url:'${portalPath}/dict/findListByCategoryId.do?categoryId=148&selected=false',
                            method:'get',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'">
                    <div class="space-8"></div>
                        审核结果：
                        <input id="audit-pass" name="rst" type="radio" value="1"/> 通过
                        &nbsp&nbsp&nbsp&nbsp
                        <input id="audit-unpass" name="rst" type="radio" value="2"/> 拒绝
                    <div class="space-8"></div>
                        <input type="hidden" name="id" value="\${data.id}">
                        审核备注： <input type="text" name="message" style="width: 200px"/>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn btn-primary" authority="false">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
        src="${pageContext.request.contextPath}/content/service/vipDepartment/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/vipDepartment/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/vipDepartment/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/vipDepartment/view.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/vipDepartment/upload.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
</body>
</html>