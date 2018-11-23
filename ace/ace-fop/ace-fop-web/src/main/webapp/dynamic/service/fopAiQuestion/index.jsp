<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>诉求服务</title>
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

                <button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/fopAiQuestion/insertFopAiQuestion"></button>




            </div>
            <div class="col-md-7">

                <div class="btn-group" role="group"  style="float:right;padding-right:5px">

                </div>


            </div>

            <div class="col-md-3">
                    <div class="input-group">
                        <input type="text"
                               name="question"
                               class="form-control"
                               placeholder="请输入问题">
                        <span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
                                    authority="${pageContext.request.contextPath}/fopAiQuestion/findFopAiQuestionList">
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
                          <h5 class="header-title">问题</h5>
                          <div class="row" style="padding:10px" id="question">
                          \${data.question}
                          </div>
                          <h5 class="header-title">答案</h5>
                          <div class="row" style="padding:10px" id="answer">
                          \${data.answer}
                          </div>
                          <h5 class="header-title">基本信息</h5>
                          <div class="row" style="padding:10px">
                              <div class="labelItem"><span class="labelItemHeader">
                      所含关键字</span>
                                  <br>
                                  <span id="keyWord">\${data.keyWord}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      累计提问次数</span>
                                  <br>
                                  <span id="accCount">\${data.accCount}
                      </span>
                              </div>
                                   <br>
                              <div class="labelItem">
                                  <span class="labelItemHeader">备注</span>
                                  <br>
                                  <span id="remark">\${data.remark}</span>
                              </div>
                                  <br>
                              <div class="labelItem hide">
                                  <span class="labelItemHeader">状态</span>
                                  <br>
                                  <span id="status">\${data.status}</span>
                              </div>
                                  <br>
                          </div>
                          <h5 class="header-title">操作信息</h5>
                          <div class="row" style="padding:10px">
                              <div class="labelItem"><span class="labelItemHeader">
                      创建人编号</span>
                                  <br>
                                  <span id="createUserId">\${data.createUserId}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      创建人姓名</span>
                                  <br>
                                  <span id="createUserName">\${data.createUserName}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      入库日期</span>
                                  <br>
                                  <span id="createDate">\${data.createDate}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新人编号</span>
                                  <br>
                                  <span id="lastModifyUserId">\${data.lastModifyUserId}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新人姓名</span>
                                  <br>
                                  <span id="lastModifyUserName">\${data.lastModifyUserName}
                      </span>
                              </div>
                                  <br>
                              <div class="labelItem"><span class="labelItemHeader">
                      最后更新时间</span>
                                  <br>
                                  <span id="lastModifyDate">\${data.lastModifyDate}
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


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
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
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


<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>


<script
        src="${pageContext.request.contextPath}/content/service/fopAiQuestion/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopAiQuestion/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopAiQuestion/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/fopAiQuestion/view.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



<script src="${portalPath}/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/FileSaver/FileSaver.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/html2canvas/html2canvas.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/tableExport.min.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/tableExport/export.js?version=${cfg.version}"></script>
</body>
</html>