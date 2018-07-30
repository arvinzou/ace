<%@page import="com.huacainfo.ace.common.tools.CommonUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    String taskId = request.getParameter("taskId");
    request.setAttribute("edit", "true");
    if (CommonUtils.isBlank(taskId)) {

        taskId = String.valueOf(new java.util.Date().getTime());
        request.setAttribute("edit", "false");
    }
    request.setAttribute("taskId", taskId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>基本信息</title>
</head>
<script type="text/javascript">
    var taskId = '${taskId}';
    var edit = ${edit};
</script>
<style>
    .layout-user {
        width: 248px;
        height: 20px;
        float: left;
        margin: 1px 1px 1px;
    }
</style>
<jsp:include page="../../common/common.jsp"/>
<body>
<div class="page-content">
    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">普通短信发送</h5>

            <div class="widget-toolbar"></div>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-6">

                <input type="hidden" name="taskId" value="${taskId }">
                <div class="profile-info-row hide">
                    <div class="profile-info-name">任务名称：</div>

                    <div class="profile-info-value">
                        <input style="width: 600px; height: 25px; line-height: 25px;" id="taskName" maxLength="20"/>
                        <span style='color:red;font-size:16px;font-weight:800'>*</span>
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">发送给：</div>

                    <div class="profile-info-value">
                        <div id="task-content" class="easyui-panel"
                             style="padding: 5px; width: 600px; height: 100px"></div>
                        <span style='color:red;font-size:16px;font-weight:800'>*</span>
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name"></div>

                    <div class="profile-info-value">
                        <button class="btn btn-purple" id="btn-view-select"
                                authority="false">
                            添加<i class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
                        </button>
                        <button class="btn btn-purple" id="btn-view-remove"
                                authority="false">
                            全部清除<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
                        </button>
                        <button class="btn btn-purple" id="btn-view-remove-last"
                                authority="false">
                            删除最后<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
                        </button>
                    </div>
                </div>
                <div class="profile-info-row hide">
                    <div class="profile-info-name">发送给：</div>

                    <div class="profile-info-value">
							<textarea style="width: 600px; height: 50px; line-height: 30px;"
                                      id="telext"></textarea>
                        <div>多个手机号用;隔开</div>
                    </div>
                </div>
                <div class="profile-info-row">
                    <div class="profile-info-name">短信内容：</div>

                    <div class="profile-info-value">
							<textarea style="width: 600px; height: 80px; line-height: 30px;"
                                      id="msg" maxLength="500" onkeyup="msgOnChange(this)"></textarea>
                        <div id="msg-des">普通短信长度为70个字，超过后部分手机将会以多条方式接收</div>
                    </div>
                </div>


                <div class="profile-info-row">
                    <div class="profile-info-name"></div>
                    <div class="profile-info-value">
                        <button class="btn btn-info" id="btn-view-submit"
                                authority="false">
                            <i class="ace-icon fa fa-check bigger-110"></i> 发送
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<!-- /section:elements.tab.option -->


<div id="dialog-message" class="hide">
    <div class="tabbable">
        <ul class="nav nav-tabs padding-16" id="myTab4">
            <li class="active"><a data-toggle="tab" href="#dept"> <i
                    class="green ace-icon glyphicon glyphicon-user bigger-125"></i>
                批量选择
            </a></li>
            <li><a data-toggle="tab" id="tab-group-tmp" href="#tmp">
                <i class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
                条件选择
            </a></li>
        </ul>
        <div class="tab-content">

            <div id="dept" class="tab-pane in active">
                <div id="tree-dept-panel" class="easyui-panel" style="padding: 5px; width: 550px; height: 300px">
                    <ul id="tree-dept"></ul>
                </div>
            </div>
            <div id="tmp" class="tab-pane">
                <select class="easyui-combogrid"
                        style="width: 560px; height: 25px; line-height: 25px;"
                        id="combogrid-tmp">
                </select>
                <div style="height:5px"></div>
                <div>
                    <button class="btn btn-purple" id="btn-view-select-tmp" authority="false">
                        添加<i class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-purple" id="btn-view-remove-tmp" authority="false">
                        全部清除<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-purple" id="btn-view-remove-tmp-last" authority="false">
                        删除最后<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
                    </button>
                </div>
                <div style="height:5px"></div>
                <div id="task-content-tmp" class="easyui-panel" style="padding: 5px; width: 560px; height: 200px">

                </div>
            </div>

        </div>
    </div>
</div>


<jsp:include page="../../common/footer-1.jsp"/>

<script
        src="${pageContext.request.contextPath}/content/service/taskCmcc/controller.js"></script>

<jsp:include page="../../common/footer-2.jsp"/>

<script type="text/javascript">
    function msgOnChange(field) {
        /*var msg=$('#msg').val();
         $('#msg-des').html('普通短信长度为70个字,还可输入<span class="badge badge-warning">'+(70-msg.length)+"</span>字符");
         */
    }
</script>
</body>
</html>