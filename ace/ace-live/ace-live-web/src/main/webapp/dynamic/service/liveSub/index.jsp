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
</head>
<body>

<!--隐藏存放ID-->
<input type="text" hidden value="" id="auditId"/>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

                                        <div class="portlet light ">

                                            <div class="portlet-body">

                                                <div class="row custom-toolbar">
                                                    <div class="col-md-3" id="createCourse">
                                                        <a href="javascript:void(0);" onclick="add('1');" style="font-size: 14px !important;" class="btn green commonCourse">创建直播</a>
                                                        <a href="javascript:void(0);" onclick="add('2');" style="font-size: 14px !important; display: none;" class="btn green specialCourse">创建直播</a>
                                                    </div>

                                                    <div class="col-md-9">

                                                        <form onsubmit="return t_query()">
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="setParams('status','');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="setParams('status','1');">预播放</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('status','2');">直播中</button>
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

                                                            <th width="20%">开始&结束时间</th>
                                                            <th width="15%">参与人数/点击量</th>
                                                            <th width="10%">审核状态</th>
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
                <div class="col-md-4"><img src="\${item.imageSrc}" class="cover"/></div>
                <div class="col-md-8">
                    <div class="describtion">\${item.name}</div>
                </div>
            </div>
        </td>
        <td >
            {@if item.status==1}
            <span class="label label-lg label-success">预播</span>
            {@else if item.status==2}
            <span class="label label-lg label-danger">直播中</span>
            {@else}
            <span class="label label-lg label-info">历史</span>
            {@/if}
        </td>

        <td>
            \${item.startTime}<br>
            \${item.endTime}

        </td>
        <td  >\${item.nop}/\${item.pop}</td>
        <td >
            {@if item.auditStatus==1}
            <span class="label label-lg label-info">待审</span>
            {@else if item.auditStatus==2}
            <span class="label label-lg label-success">通过</span>
            <div style="padding-top:10px">\${item.statement}</div>
            {@else}
            <span class="label label-lg label-danger">驳回</span>
            <div style="padding-top:10px">\${item.statement}</div>
            {@/if}
        </td>
        <td >
            <a  href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            {@if item.auditStatus==1}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
            {@/if}
        </td>
    </tr>
    {@/each}
</script>
</body>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="editCourse"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div id="courseBasic"></div>
        </div>
    </div>
</div>


<!--审核弹框-->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="modal-audit"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel3">审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                      <div class="form-body">
                          <div class="form-group">
                              <label class="col-md-2 control-label">对象</label>
                              <div class="col-md-10 audit-title">

                              </div>
                          </div>
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
                                <input type="hidden" name="id">
                                <textarea name="text" style="width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green audit">确定</button>
            </div>
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