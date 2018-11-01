<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>诉求</title>
</head>
<link rel="stylesheet" href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />

<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">

				
				
				
				




			</div>

			<div class="col-md-8">
				<form action="#" id="fm-search" >
					<div class="btn-group" role="group"  style="float:left;padding-right:5px">
						<button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
						<button type="button" class="btn btn-default"  onclick="setParams('status','1');">待受理</button>
						<button type="button" class="btn btn-default" onclick="setParams('status','2');">已受理</button>
						<button type="button" class="btn btn-default" onclick="setParams('status','3');">办结</button>
					</div>
					<div class="input-group">
						<input type="text"
							   name="title"
							   class="form-control"
							   placeholder="请输入标题">
						<span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
									authority="${pageContext.request.contextPath}/appealCase/findAppealCaseList.do">
									搜索
							</button>
						</span>
					</div>
				</form>
			</div>

		</div>

		<table id="grid-table"></table>

		<div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
	</div>
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

<div class="modal fade"  role="dialog" id="modal-accept">
	<div class="modal-dialog" role="document" style="width:60%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">诉求受理</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="fm-accept" role="form">
					<div class="form-group">
						<label class="col-md-2 view-label">
							诉求标题
						</label>
						<div class="col-md-6 title">

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">
							受理部门
							<span class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="answerDept" maxlength="50" placeholder="请输入受理部门">
							<div class="error-answerDept"></div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">
							受理人员
							<span class="required" aria-required="true"> * </span>

						</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="operator" maxlength="50" placeholder="请输入受理人员">
							<div class="error-operator"></div>
							<input type="hidden" name="id">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn green accept">确定</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade"  role="dialog" id="modal-proc">
	<div class="modal-dialog" role="document" style="width:80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">处理进度</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="fm-proc" role="form">
					<div class="form-group">
						<label class="col-md-2 view-label">
							诉求标题
						</label>
						<div class="col-md-6 title">

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">
							处理进度
							<span class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-6">
							<textarea rows="10" cols="100" class="form-control" name="detailsOfProgress" maxlength="500" placeholder="请输入处理进度（建议字数在300个字以内，不超过500个字)"></textarea>
							<div class="error-detailsOfProgress"></div>
							<input type="hidden" name="id">
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn green proc">确定</button>
			</div>
		</div>
	</div>
</div>




<div class="modal fade"  role="dialog" id="modal-answer">
	<div class="modal-dialog" role="document" style="width:80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">诉求答复</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="fm-answer" role="form">
					<div class="form-group">
						<label class="col-md-2 view-label">
							诉求标题
						</label>
						<div class="col-md-6 title">

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">
							答复内容
							<span class="required" aria-required="true"> * </span>
						</label>
						<div class="col-md-6">
							<textarea rows="10" cols="100" class="form-control" name="answerContent" maxlength="500" placeholder="请输入答复内容（建议字数在300个字以内，不超过500个字)"></textarea>
							<div class="error-answerContent"></div>
							<input type="hidden" name="id">
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn green answer">确定</button>
			</div>
		</div>
	</div>
</div>
<script id="tpl-preview" type="text/template">
	<div class="form-body">

		<div class="form-group">
			<label class="col-md-2 view-label">公司/单位</label>
			<div class="col-md-10">
				\${data.o.companyName}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 view-label">联系人</label>
			<div class="col-md-10">
				\${data.o.submitName}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 view-label">联系电话</label>
			<div class="col-md-10">
				\${data.o.tel}
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 view-label">提交时间</label>
			<div class="col-md-10">
				\$\${data.o.submitTime}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 view-label">标题</label>
			<div class="col-md-10">
				\${data.o.title}
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 view-label">诉求内容</label>
			<div class="col-md-8">
				<div class="row">\${data.o.content}</div>
				{@if data.o.mediUrl.indexOf('.mp3')!=-1}
				<div class="row"><audio src="\${data.o.mediUrl}" controls ></audio></div>
				{@/if}
				{@if data.o.mediUrl.indexOf('.mp4')!=-1}
				<div class="row"><video src="\${ data.o.mediUrl}" controls style="width:360px;height:90px"></video></div>
				{@/if}

				{@each data.o.list as img, idx}
				<div class="my-gallery" style="float:left;padding:5px"><img src="\${img.mediUrl}" class="cover"/></div>
				{@/each}

			</div>
		</div>
		{@if data.o.answerDept}
		<div class="form-group">
			<label class="col-md-2 view-label">处理科室/人</label>
			<div class="col-md-8">
				\${data.o.answerDept}<br>
				\${data.o.operator}<br>
				\${data.o.answerTime}<br>
			</div>
		</div>
		{@/if}
		{@if data.o.detailsOfProgress}
		<div class="form-group">
			<label class="col-md-2 view-label">处理进度</label>
			<div class="col-md-8">
				\${data.o.detailsOfProgress}
			</div>
		</div>
		{@/if}
		{@if data.o.answerContent}
		<div class="form-group">
			<label class="col-md-2 view-label">答复内容</label>
			<div class="col-md-8">
				<div class="row">
					\${data.o.answerTime}
				</div>
				<div class="row">
				\${data.o.answerContent}
				</div>
			</div>
		</div>
		{@/if}


	</div>

</script>

<div class="modal fade"  role="dialog" id="modal-preview">
	<div class="modal-dialog" role="document" style="width: 90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" authority="false" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">详细</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="fm-preview" role="form">

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
			</div>
		</div>
	</div>
</div>
<div id="j-pswp" class="pswp"role="dialog" aria-hidden="true">
	<div class="pswp__bg"></div>
	<div class="pswp__scroll-wrap">
		<div class="pswp__container">
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
			<div class="pswp__item"></div>
		</div>
		<div class="pswp__ui pswp__ui--hidden">
			<div class="pswp__top-bar">
				<div class="pswp__counter"></div>
				<button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
				<button class="pswp__button pswp__button--share" title="Share"></button>
				<button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
				<button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
				<div class="pswp__preloader">
					<div class="pswp__preloader__icn">
						<div class="pswp__preloader__cut">
							<div class="pswp__preloader__donut"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
				<div class="pswp__share-tooltip"></div>
			</div>
			<button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
			<button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
			<div class="pswp__caption">
				<div class="pswp__caption__center"></div>
			</div>
		</div>
	</div>
<jsp:include page="/dynamic/common/footer.jsp" />
	<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>

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
		src="${pageContext.request.contextPath}/content/service/appealCase/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/appealCase/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/appealCase/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/appealCase/view.js?version=${cfg.version}"></script>



	<style>
    .cover{
        width: 90px;
        height: 90px;
        object-fit: cover;
    }
    .describtion{
        padding-left:15px;
    }
    .cost{
          padding-top: 5px;
          padding-left:15px;
          color:#FE6500;
    }
</style>
</body>
</html>