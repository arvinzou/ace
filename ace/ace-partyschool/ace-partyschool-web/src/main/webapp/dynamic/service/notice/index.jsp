<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>通知公告</title>
	</head>
	<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	 type="text/css" media="screen" />
	<jsp:include page="/dynamic/common/header.jsp" />
	<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />

	<body>
		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<div class="portlet light ">
			<div class="portlet-body">
				<div class="row custom-toolbar">
					<form action="#" id="fm-search">
						<div class="col-md-3 toolbar">

							<button type="button" class="btn  green" id="btn-view-add" authority="false">添加</button>

						</div>
						<div class="col-md-9">

							<div class="btn-group" id="check-group-category" role="group" style="float:left;padding-right:5px">

							</div>

							<div class="btn-group" id="select1" role="group" style="float:left;padding-right:5px">

							</div>


							<div class="input-group">
								<input type="text" name="title" class="form-control" placeholder="请输入文件名称">
								<span class="input-group-btn">
									<button class="btn  btn-default search_btn" id="btn-search" authority="false">
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

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<jsp:include page="/dynamic/common/footer.jsp" />


		<div class="modal fade" role="dialog" id="modal-preview">
			<div class="modal-dialog" role="document" style="width: 100%;">
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
<div class="modal fade" role="dialog" id="modal-push">
			<div class="modal-dialog" role="document" style="width: 70%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" authority="false">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">发布</h4>
					</div>
					<div class="modal-body" id="tt">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green" authority="false" id="btn-view-save">确定</button>
					</div>
				</div>
			</div>
		</div>
		<script id="tpl-preview" type="text/template">


			<div class="form-group">
        <label class="col-md-2 view-label">标题</label>
        <div class="col-md-10">
            \${data.o.title}
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-md-2 view-label">内容</label>
        <div class="col-md-10">
            <div class="row">\$\${data.o.content}</div>
			<div class="row" id="filelist-history"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">发布日期</label>
        <div class="col-md-10">
            \${data.o.pushDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">发布人</label>
        <div class="col-md-10">
            \${data.o.publisher}
        </div>
    </div>
   
</script>




		<script id="tpl-check-group" type="text/template">

			{@each data.list as item, index}
		        {@if item.CODE}
		            <button type="button" authority="false" class="btn btn-default"  onclick="setParams('\${data.key}','\${item.CODE}');">\${item.NAME}</button>
		        {@else}
		            
		        {@/if}
		
		    {@/each}
		
		</script>

		<script id="tpl-select-list" type="text/template">

			<select name="classesId" id="classesId" class="form-control" style="height: 30px;">
		    {@each data as item, index}
		           <option value="\${item.id}">\${item.name}</option>
		    {@/each}
		    </select>
		</script>


		<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>


<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/notice/config.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/notice/model.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/notice/controller.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/notice/view.js?version=${cfg.version}"></script>



		<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>

	</body>
	<style>
		/* css code area*/
	</style>
</html>
