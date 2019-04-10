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
    <title>策略管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-3">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-9">

                <form id="fm-search">
                    <div class="btn-group" role="group" style="float:left;padding-right:5px">
                        <button type="button" class="btn btn-default" onclick="setParams('category','');">全部</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','1');">图文</button>
                        <button type="button" class="btn btn-default" onclick="setParams('category','2');">视频</button>
                    </div>
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入策略名称或编号">
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
                    <%--<th width="10%"> 主键</th>--%>
                    <th width="10%"> 策略编号</th>
                    <th width="10%"> 策略名称</th>
                    <%--<th width="10%"> 策略描述</th>--%>
                    <th width="10%"> 策略状态</th>
                    <th width="10%"> 行政区划</th>
                    <th width="10%"> 所属站点</th>
                    <%--<th width="10%"> 模式</th>--%>
                    <%--<th width="10%"> 按周执行</th>--%>
                    <%--<th width="10%"> 星期数组</th>--%>
                    <%--<th width="10%"> 按月执行</th>--%>
                    <%--<th width="10%"> 月份数组</th>--%>
                    <%--<th width="10%"> 开始日期</th>--%>
                    <%--<th width="10%"> 技术日期</th>--%>
                    <%--<th width="10%"> 具体的特殊日期</th>--%>
                    <%--<th width="10%"> 策略的开始时间</th>--%>
                    <%--<th width="10%"> 策略的结束时间</th>--%>
                    <%--<th width="10%"> 分区已编辑完毕的策略编号（或名称）</th>--%>
                    <%--<th width="10%"> 备注</th>--%>
                    <th width="10%"> 状态</th>
                    <%--<th width="10%"> 创建人编号</th>--%>
                    <%--<th width="10%"> 创建人姓名</th>--%>
                    <%--<th width="10%"> 创建日期</th>--%>
                    <%--<th width="10%"> 更新人编号</th>--%>
                    <%--<th width="10%"> 更新人名称</th>--%>
                    <%--<th width="10%"> 更新日期</th>--%>
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
        <%--<td> \${item.id}</td>--%>
        <td> \${item.code}</td>
        <td> \${item.name}</td>
        <%--<td> \${item.depict}</td>--%>
        <td> \${item.state}</td>
        <td> \${item.district}</td>
        <td> \${item.stationCode}</td>
        <%--<td> \${item.pattern}</td>--%>
        <%--<td> \${item.isWeek}</td>--%>
        <%--<td> \${item.weeks}</td>--%>
        <%--<td> \${item.isMonth}</td>--%>
        <%--<td> \${item.months}</td>--%>
        <%--<td> \${item.startDate}</td>--%>
        <%--<td> \${item.stopDate}</td>--%>
        <%--<td> \${item.specialDate}</td>--%>
        <%--<td> \${item.startTime}</td>--%>
        <%--<td> \${item.stopTime}</td>--%>
        <%--<td> \${item.strategy}</td>--%>
        <%--<td> \${item.remark}</td>--%>
        <td> \${item.status}</td>
        <%--<td> \${item.createUserId}</td>--%>
        <%--<td> \${item.createUserName}</td>--%>
        <%--<td> \${item.createDate}</td>--%>
        <%--<td> \${item.lastModifyUserId}</td>--%>
        <%--<td> \${item.lastModifyUserName}</td>--%>
        <%--<td> \${item.lastModifyDate}</td>--%>
        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-info">暂存</span>
            {@else if item.status==2}
            <span class="label label-lg label-warning">待审</span>
            {@else if item.status==3}
            <span class="label label-lg label-info">通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else if item.status==4}
            <span class="label label-lg label-info">驳回</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-danger">暂存</span>
            {@/if}
        </td>
        <td>
            ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-status">设置状态</a>
            {@if item.auditStatus==1}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-audit">审核</a>
            {@/if}
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>

            <a href="javascript:del('\${item.id}');">删除</a>

        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-status">
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
                                        <input type="radio" name="status" value="2"><span
                                            style="padding:10px">直播中</span>
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
<div class="modal fade" role="dialog" id="modal-audit">
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
<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body" style="height: 600px;overflow: auto">
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
<script id="tpl-fm" type="text/template">
    <div class="form-body">

        <div class="form-group">
            <label class="col-md-2 view-label">主键</label>
            <div class="col-md-10">
                \${id}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略编号</label>
            <div class="col-md-10">
                \${code}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略名称</label>
            <div class="col-md-10">
                \${name}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略描述</label>
            <div class="col-md-10">
                \${depict}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略状态</label>
            <div class="col-md-10">
                \${state}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">行政区划</label>
            <div class="col-md-10">
                \${district}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">所属站点</label>
            <div class="col-md-10">
                \${stationCode}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">模式</label>
            <div class="col-md-10">
                \${pattern}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">按周执行（0否1是）[日程模式]</label>
            <div class="col-md-10">
                \${isWeek}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">星期数组[日程模式]</label>
            <div class="col-md-10">
                \${weeks}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">按月执行(0否1整月)[日程模式]</label>
            <div class="col-md-10">
                \${isMonth}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">月份数组(pattern为1)[日程模式]</label>
            <div class="col-md-10">
                \${months}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">开始日期[假日模式]</label>
            <div class="col-md-10">
                \${startDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">技术日期[假日模式]</label>
            <div class="col-md-10">
                \${stopDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">具体的特殊日期[事件模式]</label>
            <div class="col-md-10">
                \${specialDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略的开始时间</label>
            <div class="col-md-10">
                \${startTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">策略的结束时间</label>
            <div class="col-md-10">
                \${stopTime}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">分区已编辑完毕的策略编号（或名称）</label>
            <div class="col-md-10">
                \${strategy}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">备注</label>
            <div class="col-md-10">
                \${remark}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">状态 </label>
            <div class="col-md-10">
                \${status}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人编号</label>
            <div class="col-md-10">
                \${createUserId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建人姓名</label>
            <div class="col-md-10">
                \${createUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">创建日期</label>
            <div class="col-md-10">
                \${createDate}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人编号</label>
            <div class="col-md-10">
                \${lastModifyUserId}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新人名称</label>
            <div class="col-md-10">
                \${lastModifyUserName}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">更新日期</label>
            <div class="col-md-10">
                \${lastModifyDate}
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
    <div class="form-group">
        <label class="col-md-2 view-label">主键</label>
        <div class="col-md-10">
            \${data.id}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略编号</label>
        <div class="col-md-10">
            \${data.code}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略名称</label>
        <div class="col-md-10">
            \${data.name}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略描述</label>
        <div class="col-md-10">
            \${data.depict}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略状态</label>
        <div class="col-md-10">
            \${data.state}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">行政区划</label>
        <div class="col-md-10">
            \${data.district}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">所属站点</label>
        <div class="col-md-10">
            \${data.stationCode}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">模式</label>
        <div class="col-md-10">
            \${data.pattern}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">按周执行（0否1是）[日程模式]</label>
        <div class="col-md-10">
            \${data.isWeek}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">星期数组[日程模式]</label>
        <div class="col-md-10">
            \${data.weeks}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">按月执行(0否1整月)[日程模式]</label>
        <div class="col-md-10">
            \${data.isMonth}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">月份数组(pattern为1)[日程模式]</label>
        <div class="col-md-10">
            \${data.months}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">开始日期[假日模式]</label>
        <div class="col-md-10">
            \${data.startDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">技术日期[假日模式]</label>
        <div class="col-md-10">
            \${data.stopDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">具体的特殊日期[事件模式]</label>
        <div class="col-md-10">
            \${data.specialDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略的开始时间</label>
        <div class="col-md-10">
            \${data.startTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">策略的结束时间</label>
        <div class="col-md-10">
            \${data.stopTime}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">分区已编辑完毕的策略编号（或名称）</label>
        <div class="col-md-10">
            \${data.strategy}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">备注</label>
        <div class="col-md-10">
            \${data.remark}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">状态 </label>
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
        <label class="col-md-2 view-label">创建日期</label>
        <div class="col-md-10">
            \${data.createDate}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新人编号</label>
        <div class="col-md-10">
            \${data.lastModifyUserId}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新人名称</label>
        <div class="col-md-10">
            \${data.lastModifyUserName}
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 view-label">更新日期</label>
        <div class="col-md-10">
            \${data.lastModifyDate}
        </div>
    </div>
</script>
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
</html>
