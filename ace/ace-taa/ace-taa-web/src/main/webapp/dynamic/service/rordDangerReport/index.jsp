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
    <title>路况隐患</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
    <link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
    <script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
    <script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>

</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <%--  <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>--%>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <div class="input-group" style="float:left;padding-right:10px">
                        行政区划 <input class="easyui-combotree" name="areaCode"
                                    data-options="url:﻿'${portalPath}/system/selectProvinceTreeList.do',method:'get',label:'',labelPosition:'top'"
                                    style="width:200px;﻿line-height: 30px;height: 30px;">
                    </div>
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('status','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','1');">待处理
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','2');">登记
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','3');">销号
                        </button>
                        <button type="button" class="btn btn-default" onclick="setParams('status','4');">退回
                        </button>
                    </div>

                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入上报单位/上报人/归属路段/归属路长">
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
                    <th width="15%">路况隐患地点</th>
                    <th width="8%">归属路长</th>
                    <th width="8%">隐患类型</th>
                    <th width="10%">上报人</th>
                    <th width="15%">上报时间</th>
                    <th width="15%">上报单位</th>
                    <th width="8%">处理状态</th>
                    <th width="15%">操作</th>
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


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>

        <td>\${item.address}</td>
        <td>\${item.roadManName}</td>
        <td> \${rsd(item.type,'175')}</td>
        <td>\${item.uname}</td>
        <td>\${item.reportDate}</td>
        <td>\${item.departmentName}</td>
        {@if item.status==1}

        <td width="15%" style="color:#2638ff;">待处理</td>
        {@/if}
        {@if item.status==2}

        <td width="15%" style="color:#ff392f">登记</td>
        {@/if}
        {@if item.status==3}

        <td width="15%" style="color:#81ff28">销号</td>
        {@/if}
        {@if item.status==4}

        <td width="15%">退回</td>
        {@/if}
        <td>
            ﻿
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            {@if item.status==1 || item.status==2}
            <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">登记</a>
            <a href="javascript:outline('\${item.id}');">销号</a>
            <a href="javascript:withdraw('\${item.id}');">退回</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade" role="dialog" id="withdraw-modal">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">退回</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal" role="form">
                    <div class="form-body" id="fm">
                        <div class="form-group">
                            <label class="col-md-2 control-label">
                                退回原因
                            </label>
                            <div class="col-md-6">
                                <input name="reasonId" type="hidden" id="reasonId"/>
                                <div class="radio-group-container">
                                    <textarea name="reason" cols="80" rows="3" id="reason"> </textarea>
                                </div>
                                <div class="error-reason" id="error-reason" style="color: red" hidden>退回原因不能为空</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="" id="saveReason" onclick="saveReason();">确定</button>


                <button type="button" class="btn " data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl-preview" type="text/template">
    <div class="form-group hidden">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.o.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">事故发生地点</label>
        <div class="col-md-10">
            <a href="${pageContext.request.contextPath}/dynamic/service/roadSection/previewMap.jsp?latitude=\${data.o.latitude}&longitude=\${data.o.longitude}"
               target="_blank">\${data.o.address}</a>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">归属路段</label>
        <div class="col-md-10">
            \${data.o.roadSectionName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">归属路长</label>
        <div class="col-md-10">
            \${data.o.roadManName}
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2 view-label">行政区划</label>
        <div class="col-md-10">
            \${data.o.areaName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">隐患类型</label>
        <div class="col-md-10">
            \${rsd(data.o.type,'175')}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">上报时间</label>
        <div class="col-md-10">
            \${data.o.reportDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">上报人</label>
        <div class="col-md-10">
            \${data.o.uname}
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-2 view-label">上报单位</label>
        <div class="col-md-10">
            \${data.o.departmentName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">路患描述</label>
        <div class="col-md-10">
            \${data.o.remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">现场照片</label>
        {@each data.o.picList as pic, index}
        <div class="my-gallery" style="float:left;padding:5px;">
            <img src="\${pic.fileUrl}" class="cover"/>
        </div>
        {@/each}

    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">整改措施</label>
        <div class="col-md-10">
            \${data.o.takeSteps}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">整改照片</label>
        {@each data.o.changedList as pic1, index}
        <div class="my-gallery" style="float:left;padding:5px">
            <img src="\${pic1.fileUrl}" class="cover"/>
        </div>
        {@/each}
    </div>
    {@if data.o.reason !=null && data.o.status==4}
    <div class="form-group">
        <label class="col-md-2 view-label">退回原因</label>
        <div class="col-md-10">
            \${data.o.reason}
        </div>
    </div>

    {@/if}
    </div>

</script>

<div id="j-pswp" class="pswp" role="dialog" aria-hidden="true">
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
    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .describtion {
        padding-left: 15px;
        height: 50px;
    }

    .cost {
        padding-top: 5px;
        padding-left: 15px;
        color: #FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>

<script src="js/act.js?v=${cfg.version}"></script>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
</html>