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
    <link href="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-7">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-5">

                <form id="fm-search">
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
                    <th width="10%"> 策略编号</th>
                    <th width="10%"> 策略名称</th>
                    <th width="10%"> 策略状态</th>
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
        <td> \${item.code}</td>
        <td> \${item.name}</td>
        <td> \${item.state}</td>
        <td>
            ﻿ <a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            <a href="javascript:updateStatus('\${item.id}');">发布</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-code="\${item.code}" data-target="#modal-push">下发</a>
            <a href="javascript:setModel('\${item.id}');">定时</a>
            <a href="javascript:del('\${item.id}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>
﻿
<div class="modal fade " id="modal-push">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">下发</h4>
            </div>
            <div class="modal-body" id="push">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button form="fm-status" type="submit" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>


<!--设定定时弹框-->
<div class="modal fade" role="dialog" id="modal-seting">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置定时器</h4>
            </div>
            <div class="modal-body" id="setModel">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" form="setting" class="btn green">确定</button>
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

<script type="text/template" id="tpl-setModel">
    <form id="setting">
        <p class="title">请选择模式</p>
        <div class="tab">
            <div class="changeBtn">
                <input type="text" value="\${data.id}" name="id" style="display:none">
                <input type="radio" id="mo1" value="1" \${data.pattern==1?'checked':''} onchange="change('1')"
                       name="pattern">
                <label for="mo1" class="models model_1">
                    <div class="a">
                        <img src="" alt="">
                    </div>
                    <p class="b">日程模式</p>
                    <p>用于日常策略，按周、按月执行</p>
                </label>
                <input id="mo2" type="radio" value="2" \${data.pattern==2?'checked':''} onchange="change('2')"
                       name="pattern">
                <label for="mo2" class=" models model_2">
                    <div class="a">
                        <img src="" alt="">
                    </div>
                    <p class="b">假日模式</p>
                    <p>自定义开始结束时间</p>
                </label>
                <input id="mo3" type="radio" value="3" \${data.pattern==3?'checked':''} onchange="change('3')"
                       name="pattern">
                <label for="mo3" class="models model_3">
                    <div class="a">
                        <img src="" alt="">
                    </div>
                    <p class="b">事件模式</p>
                    <p>制定的策略直接生效</p></label>
            </div>
        </div>
        <p class="title">请设置时间</p>
        <div class="tab_leb">
            <ul class="c">
                <li \${data.pattern==1?'':'hidden'} class="d tab_1 tab_f">
                    <label>
                        <%--<input type="radio" checked name="1" name="type" class="a-radio">
                        <span class="b-radio"></span>--%>
                        指定月
                    </label>

                    <div class="lists months">
                        <ul>
                            <li>
                                <input id="m1" value="1" \${isChecked(data.months,1)} name="months" type="checkbox">
                                <label for="m1">一月</label>
                            </li>
                            <li>
                                <input id="m2" value="2" \${isChecked(data.months,2)} name="months" type="checkbox">
                                <label for="m2">二月</label>
                            </li>
                            <li>
                                <input id="m3" value="3" \${isChecked(data.months,3)} name="months" type="checkbox">
                                <label for="m3">三月</label>
                            </li>
                            <li>
                                <input id="m4" value="4" \${isChecked(data.months,4)} name="months" type="checkbox">
                                <label for="m4">四月</label>
                            </li>
                            <li>
                                <input id="m5" value="5" \${isChecked(data.months,5)} name="months" type="checkbox">
                                <label for="m5">五月</label>
                            </li>
                            <li>
                                <input id="m6" value="6" \${isChecked(data.months,6)} name="months" type="checkbox">
                                <label for="m6">六月</label>
                            </li>
                            <li>
                                <input id="m7" value="7" \${isChecked(data.months,7)} name="months" type="checkbox">
                                <label for="m7">七月</label>
                            </li>
                            <li>
                                <input id="m8" value="8" \${isChecked(data.months,8)} name="months" type="checkbox">
                                <label for="m8">八月</label>
                            </li>
                            <li>
                                <input id="m9" value="9" \${isChecked(data.months,9)} name="months" type="checkbox">
                                <label for="m9">九月</label>
                            </li>
                            <li>
                                <input id="m10" value="10" \${isChecked(data.months,10)} name="months" type="checkbox">
                                <label for="m10">十月</label>
                            </li>
                            <li>
                                <input id="m11" value="11" \${isChecked(data.months,11)} name="months" type="checkbox">
                                <label for="m11">十一月</label>
                            </li>
                            <li>
                                <input id="m12" value="12" \${isChecked(data.months,12)} name="months" type="checkbox">
                                <label for="m12">十二月</label>
                            </li>
                        </ul>
                    </div>
                    <label>
                        <%--<input type="radio" name="1" name="type" class="a-radio">
                        <span class="b-radio"></span>--%>
                        指定周
                    </label>
                    <div class=" lists weeks">
                        <ul>
                            <li>
                                <input id="w1" value="1" \${isChecked(data.weeks,1)} name="weeks" type="checkbox">
                                <label for="w1">星期一</label>
                            </li>
                            <li>
                                <input id="w2" value="2" \${isChecked(data.weeks,2)} name="weeks" type="checkbox">
                                <label for="w2">星期二</label>
                            </li>
                            <li>
                                <input id="w3" value="3" \${isChecked(data.weeks,3)} name="weeks" type="checkbox">
                                <label for="w3">星期三</label>
                            </li>
                            <li>
                                <input id="w4" value="4" \${isChecked(data.weeks,4)} name="weeks" type="checkbox">
                                <label for="w4">星期四</label>
                            </li>
                            <li>
                                <input id="w5" value="5" \${isChecked(data.weeks,5)} name="weeks" type="checkbox">
                                <label for="w5">星期五</label>
                            </li>
                            <li>
                                <input id="w6" value="6" \${isChecked(data.weeks,6)} name="weeks" type="checkbox">
                                <label for="w6">星期六</label>
                            </li>
                            <li>
                                <input id="w7" value="7" \${isChecked(data.weeks,7)} name="weeks" type="checkbox">
                                <label for="w7">星期七</label>
                            </li>
                        </ul>
                    </div>
                </li>
                <li \${data.pattern==2?'':'hidden'} class="e tab_2 tab_f">
                    <div class="dateTime">
                        <div class="inputGro">
                            <img src="" alt=""> <input name="startDate" value="\${data.startDate}" type="text">
                        </div>
                        <div class="inputGro">
                            <img src="" alt=""> <input name="stopDate" value="\${data.stopDate}" type="text">
                        </div>
                    </div>
                </li>
                <li \${data.pattern==3?'':'hidden'} class="e tab_3 tab_f">
                    <div class="dateTime">
                        <div class="inputGro">
                            <img src="" alt=""> <input name="startTime" value="\${data.startTime}" type="text">
                        </div>
                        <div class="inputGro">
                            <img src="" alt=""> <input name="stopTime" value="\${data.stopTime}" type="text">
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</script>


<script type="text/template" id="tpl-push">
    <form class="form-horizontal" id="fm-status">
        <div class="form-group">
            <input type="text" name="id" value="\${data.id}" hidden>
            <input type="text" name="aiCode" value="\${data.code}" hidden>
            <label class="col-md-2 control-label">
                下发类型
            </label>
            <div class="col-md-10">
                <ul class="objectType">
                    <li>
                        <input name="lnkType" value="1" type="radio" checked id="s1" onchange="changeUrl(1)">
                        <label for="s1">站点</label>
                    </li>
                    <li>
                        <input type="radio" value="3" name="lnkType"  id="s" onchange="changeUrl(3)">
                        <label for="s">建筑物</label>
                    </li>
                    <li>
                        <input name="lnkType" value="2" type="radio" id="s2" onchange="changeUrl(2)">
                        <label for="s2">节点</label>
                    </li>
                </ul>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                策略对象
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="lnkCode" maxlength="200"
                       placeholder="请选择下发对象">
                <span class="help-block"></span>
            </div>
        </div>
    </form>
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>

<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script src="js/act.js?v=${cfg.version}"></script>
</html>
