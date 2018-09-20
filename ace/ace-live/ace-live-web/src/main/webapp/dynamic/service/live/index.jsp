<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
	<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
	<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
	<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
	<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>
</head>
<body>

<!--隐藏存放ID-->
<input type="text" hidden value="" id="auditId"/>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

                                        <div class="portlet light ">

                                            <div class="portlet-body">

                                                <div class="row custom-toolbar">
                                                    <div class="col-md-3">
                                                        <a href="add/index.jsp?id=${param.id}"  class="btn green">创建</a>
                                                    </div>

                                                    <div class="col-md-9">

                                                        <form onsubmit="return t_query()">
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="setParams('status','1');">预播</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('status','2');">直播</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('status','3');">历史</button>
                                                            </div>
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="setParams('auditStatus','');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="setParams('auditStatus','1');">待审</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('auditStatus','2');">通过</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('auditStatus','3');">驳回</button>
                                                            </div>
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="setParams('category','');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="setParams('category','1');">图文</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('category','2');">视频</button>
                                                            </div>
                                                            <div class="input-group">
                                                                <input type="text"
                                                                       name="keyword"
                                                                       class="form-control"
                                                                       placeholder="请输入直播名称">
                                                                <span class="input-group-btn">
                                                                <button class="btn  btn-default search_btn"
                                                                        type="submit">
                                                                        搜索
                                                                </button>
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>

                                                </div>

                                                <div class="table-scrollable">
                                                    <table class="table table-hover">
                                                        <thead>
                                                        <tr>

                                                            <th width="35%">名称 </th>
                                                            <th width="10%">状态</th>

                                                            <th width="20%">开始时间</th>
                                                            <th width="10%">参与人数/点击量</th>
                                                            <th width="15%">审核状态</th>
                                                            <th width="10%">操作</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="page-list">

                                                        </tbody>
                                                    </table>
                                                </div>


                                                <div class="paginationbar">
                                                    <ul class="pagination" id="pagination1"></ul>
                                                </div>
                                            </div>
                                        </div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>

        <td >
            <div class="row">
                <div class="col-md-4 my-gallery"><img src="\${item.imageSrc}" class="cover"/></div>
                <div class="col-md-8">
                    <div class="describtion">\${item.name}</div>
                </div>
            </div>
        </td>
        <td >
            {@if item.status==1}
            <span class="label label-lg label-success">预播</span>
            {@else if item.status==2}
            <span class="label label-lg label-danger">直播</span>
            {@else}
            <span class="label label-lg label-info">历史</span>
            {@/if}
        </td>

        <td>
            \${item.startTime}

        </td>
        <td  >\${item.nop}/\${item.pop}</td>
        <td >
            {@if item.auditStatus==1}
            <span class="label label-lg label-info">待审</span>
            {@else if item.auditStatus==2}
            <span class="label label-lg label-success">通过</span>
            <div style="padding-top:5px">\${item.auditDate}</div>
            <div style="padding-top:5px">\${item.statement}</div>
            {@else}
            <span class="label label-lg label-danger">驳回</span>
            <div style="padding-top:5px">\${item.auditDate}</div>
            <div style="padding-top:5px">\${item.statement}</div>
            {@/if}
        </td>
        <td >
            <a  href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-status">设置状态</a>
            {@if item.auditStatus==1}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
            {@/if}
            {@if item.auditStatus==2}
            <a href="../liveRpt/add/index.jsp?id=${param.id}&did=\${item.id}">发布报道</a>
            {@/if}
			<a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-preview">查看</a>
        </td>
    </tr>
    {@/each}
</script>
</body>

<div class="modal fade bs-example-modal-lg" id="modal-status">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 view-label">对象</label>
                            <div class="col-md-10 status-title">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <input type="hidden" name="id">
                                    <label>
                                        <input type="radio" name="status" value="1"><span style="padding:10px">预播</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="2"><span style="padding:10px">直播中</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="status" value="3"><span style="padding:10px">历史</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>


<!--审核弹框-->
<div class="modal fade"  role="dialog" id="modal-audit">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                      
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green audit">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade"  role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-preview" role="form">
                      
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script id="tpl-fm" type="text/template">
	<div class="form-body">
	                          <div class="form-group">
	                              <label class="col-md-2 view-label">名称</label>
	                              <div class="col-md-10">
										\${data.o.name}
	                              </div>
	                          </div>
							<div class="form-group">
								<label class="col-md-2 view-label">类型</label>
								<div class="col-md-10">
									\${data.o.category==1?'图文':'视频'}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 view-label">开始时间</label>
								<div class="col-md-10">
									\${data.o.startTime}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 view-label">摘要</label>
								<div class="col-md-10">
									\${data.o.remark}
								</div>
							</div>
							  
							  <div class="form-group">
							  	<label class="col-md-2 view-label">介绍</label>
							  	<div class="col-md-10">
							  		\$\${data.o.content}
							  	</div>
							  </div>
							  <div class="form-group">
							  <label class="col-md-2 view-label">封面</label>
							  <div class="col-md-10">
							  	<img  src="\${data.o.imageSrc}" style="max-width:480px;" />
							  </div>
							  </div>
							  <h4>结果</h4>
							  <hr>
	                        <div class="form-group " id="operation">
	                            <label class="col-md-2 control-label">结果</label>
	                            <div class="col-md-10">
	                                <div class="radio-group-container">
	                                    <label>
	                                        <input type="radio" name="rst" value="2"><span style="padding:10px">通过</span>
	                                    </label>
	                                    <label>
	                                        <input type="radio" name="rst" value="3"><span style="padding:10px">退回</span>
	                                    </label>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-md-2 control-label">说明</label>
	                            <div class="col-md-10">
	                                <input type="hidden" name="id" value="\${data.o.id}">
	                                <textarea name="text" style="width: 100%;height: 100px;"></textarea>
	                            </div>
	                        </div>
	                    </div>
	
</script>

<script id="tpl-preview" type="text/template">
	<div class="form-body">
	                          <div class="form-group">
	                              <label class="col-md-2 view-label">名称</label>
	                              <div class="col-md-10">
										\${data.o.name}
	                              </div>
	                          </div>
							<div class="form-group">
								<label class="col-md-2 view-label">类型</label>
								<div class="col-md-10">
									\${data.o.category==1?'图文':'视频'}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 view-label">开始时间</label>
								<div class="col-md-10">
									\${data.o.startTime}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 view-label">摘要</label>
								<div class="col-md-10">
									\${data.o.remark}
								</div>
							</div>
							  
							  <div class="form-group">
							  	<label class="col-md-2 view-label">介绍</label>
							  	<div class="col-md-10">
							  		\$\${data.o.content}
							  	</div>
							  </div>
							  <div class="form-group">
							  <label class="col-md-2 view-label">封面</label>
							  <div class="col-md-10">
							  	<img  src="\${data.o.imageSrc}" style="max-width:480px;" />
							  </div>
							  </div>
							  
	                    </div>
	
</script>
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
	
<style>
    .cover{
        width: 140px;
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
<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>