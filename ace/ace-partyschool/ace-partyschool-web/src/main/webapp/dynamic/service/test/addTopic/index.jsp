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
    <title>试题管理</title>
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
                <a href="javascript:addTopic();" class="btn green">添加试题</a>
            </div>
            <div class="col-md-9">

            </div>
        </div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="30%"> 题目内容</th>
                    <th width="20%"> 题目类型</th>
                    <th width="10%"> 分值</th>
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

<div class="modal fade bs-example-modal-lg" id="modal-topics">
    <div class="modal-dialog  modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加试题</h4>
            </div>
            <div class="modal-body">


                <div class="row custom-toolbar">
                    <div class="col-md-3">
                    </div>

                    <div class="col-md-9">

                        <form id="fm-search-model">
                            <div class="btn-group" role="group" style="float:left;padding-right:5px">
                                <button type="button" class="btn btn-default active" onclick="setParams('type','1');">
                                    单选题
                                </button>
                                <button type="button" class="btn btn-default" onclick="setParams('type','2');">多选题
                                </button>
                                <button type="button" class="btn btn-default" onclick="setParams('type','3');">判断题
                                </button>
                                <button type="button" class="btn btn-default" onclick="setParams('type','4');">问答题
                                </button>
                                <button type="button" class="btn btn-default" onclick="setParams('type','5');">打分题
                                </button>
                            </div>
                            <div class="input-group">
                                <input type="text"
                                       name="keyword"
                                       class="form-control"
                                       placeholder="请输入题目名称">
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
                            <th width="10%">选择</th>
                            <th width="90%"> 题目内容</th>
                        </tr>
                        </thead>
                        <tbody id="page-list-model">

                        </tbody>
                    </table>
                </div>
                <div class="paginationbar">
                    <ul class="pagination" id="pagination-model"></ul>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="submitTopics()" class="btn green status">确定并继续添加</button>
            </div>
        </div>
    </div>
</div>


<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>


<script id="tpl-list-model" type="text/template">
    {@each data as item, index}
    <tr>
        <td><input name="topic" value="\${item.id}" type="checkbox"></td>
        <td> \${item.content}</td>
    </tr>
    {@/each}
</script>

<%--列表juicer模板--%>
<script id="tpl-list" type="text/template">
    {@each data as item, index}
    <tr>
        <td> \${item.content}</td>
        <td> \${parseType(item.type)}</td>
        <td><input type="text"  onblur="setScore(this.value,'\${item.tid}')" value="\${item.tscore}"></td>
        <td>
            <a href="javascript:moveIndex('\${testTopicId(index,-1)}','\${item.tid}');">上移</a>
            <a href="javascript:moveIndex('\${testTopicId(index,1)}','\${item.tid}');">下移</a>
            <a href="javascript:delTestTopic('\${item.tid}');">删除</a>
        </td>
    </tr>
    {@/each}
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
