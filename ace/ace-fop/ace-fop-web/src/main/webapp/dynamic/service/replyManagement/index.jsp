<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>回复管理</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}"/>
<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet" type="text/css"/>

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light ">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-2 toolbar">
            </div>

            <div class="col-md-10">
                <form action="#" id="fm-search">
                    <div class="input-group" style="width: 250px;float: right">
                        <input type="text"
                               name="title"
                               class="form-control"
                               placeholder="请输入诉求标题">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn" id="btn-search"
                                    authority="${pageContext.request.contextPath}/fopQuestion/findFopQuestionList">
									搜索
							</button>
						</span>
                    </div>
                </form>
            </div>
        </div>

        <table id="grid-table">

        </table>

        <div class="paginationbar">
            <ul id="grid-pager" class="pagination"></ul>
        </div>
    </div>
</div>

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
                          <h5 class="header-title">发起人ID</h5>
                          <div class="row" style="padding:10px" id="question">
                          </div>
                          <h5 class="header-title">标题</h5>
                          <div class="row" style="padding:10px" id="answer">
                          </div>
                          <h5 class="header-title">内容</h5>
                          <div class="row" style="padding:10px" id="content">
                          </div>
                          <h5 class="header-title">来源ID</h5>
                          <div class="row" style="padding:10px" id="sourceId">
                          </div>
                          <h5 class="header-title">来源类型</h5>
                          <div class="row" style="padding:10px" id="sourceType">
                          </div>
                          <h5 class="header-title">子类型</h5>
                          <div class="row" style="padding:10px" id="subType">
                          </div>
                          <h5 class="header-title">父节点ID</h5>
                          <div class="row" style="padding:10px" id="parentId">
                          </div>
                          <h5 class="header-title">发布时间</h5>
                          <div class="row" style="padding:10px" id="releaseDate">
                          </div>
                          <h5 class="header-title">回复内容</h5>
                          <div class="row" style="padding:10px" id="reply">
                          </div>
                          <h5 class="header-title">回复人</h5>
                          <div class="row" style="padding:10px" id="displayName">
                          </div>
                          <h5 class="header-title">基本信息</h5>
                          <div class="row" style="padding:10px">
                              <div class="labelItem"><span class="labelItemHeader">
                      所含关键字</span>
                                  <br>
                                  <span id="keyWord">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      累计提问次数</span>
                                  <br>
                                  <span id="accCount">
                      </span>
                              </div>
                                   <br>
                              <div class="labelItem">
                                  <span class="labelItemHeader">备注</span>
                                  <br>
                                  <span id="remark"></span>
                              </div>
                                  <br>
                              <div class="labelItem hide">
                                  <span class="labelItemHeader">状态</span>
                                  <br>
                                  <span id="status"></span>
                              </div>
                                  <br>
                          </div>
                          <h5 class="header-title">操作信息</h5>
                          <div class="row" style="padding:10px">
                              <div class="labelItem"><span class="labelItemHeader">
                      创建人编号</span>
                                  <br>
                                  <span id="createUserId">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      创建人姓名</span>
                                  <br>
                                  <span id="createUserName">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      入库日期</span>
                                  <br>
                                  <span id="createDate">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新人编号</span>
                                  <br>
                                  <span id="lastModifyUserId">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新人姓名</span>
                                  <br>
                                  <span id="lastModifyUserName">
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新时间</span>
                                  <br>
                                  <span id="lastModifyDate">
                      </span>
                              </div>
                                  <br>
                          </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
            </div>
        </div>
    </div>
</div>


<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">发起人ID</label>
            <div class="col-md-10">
                \${data.relationId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">标题</label>
            <div class="col-md-10">
                \${data.title}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">内容</label>
            <div class="col-md-10">
                \$\${data.content}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">来源ID</label>
            <div class="col-md-10">
                \${data.sourceId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">来源类型</label>
            <div class="col-md-10">
                \${data.sourceType}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">子类型</label>
            <div class="col-md-10">
                \${data.subType}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">父节点ID</label>
            <div class="col-md-10">
                \${data.parentId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">发布时间</label>
            <div class="col-md-10">
                \${data.releaseDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回复内容</label>
            <div class="col-md-10">
                \${data.reply}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">回复人</label>
            <div class="col-md-10">
                \${data.displayName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">所含关键字</label>
            <div class="col-md-10">
                \${data.keyWord}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">累计提问次数</label>
            <div class="col-md-10">
                \${data.accCount}
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
        </div>
         <div class="form-group">
             <label class="col-md-2 view-label">最后更新人姓名</label>
             <div class="col-md-10">
                 \${data.lastModifyUserName}
             </div>
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
                <form class="form-horizontal" action="/fopQuestion/audit" id="fm-audit" role="form">
                    <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核选项</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input type="radio" name="rst" value="0"><span style="padding:10px">通过</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="rst" value="1"><span style="padding:10px">驳回</span>
                                    </label>
                                    <input type="text" class="hide" name="id" value="1"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">审核说明</label>
                            <div class="col-md-10">
                                <input type="hidden" name="id" value="\${data.id}">
                                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
                <button type="button" class="btn btn-primary" authority="false">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<jsp:include page="/dynamic/common/footer.jsp"/>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>

<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

<script src="${pageContext.request.contextPath}/content/service/replyManagement/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/replyManagement/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/replyManagement/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/replyManagement/view.js?version=${cfg.version}"></script>


<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

</body>
</html>