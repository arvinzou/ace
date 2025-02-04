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
    <title>测评结果管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/test.css">
</head>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<div class="portlet light">

    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-6">
                <a href="add/index.jsp?id=${param.id}" class="btn green">创建</a>
            </div>

            <div class="col-md-6">

                <form id="fm-search">
                    <div class="input-group">
                        <input type="text"
                               name="keyword"
                               class="form-control"
                               placeholder="请输入题目内容">
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
                    <th width="20%"> 测试名称</th>
                    <th width="30%"> 测试介绍</th>
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
        <td>\${item.name}</td>
        <td>\${item.introduce}</td>
        <td>
            ﻿<a href="edit/index.jsp?id=${param.id}&did=\${item.id}">编辑</a>
            ﻿<a href="addTopic/index.jsp?id=${param.id}&did=\${item.id}">添加试题</a>
            <a href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}"
               data-target="#modal-preview">查看</a>
        </td>
    </tr>
    {@/each}
</script>
﻿

<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title">详细</h4>
            </div>
            <div class="modal-body" style="background-color: #eee">
                <div class="form-horizontal" role="form">
                    <div class="form-body" style="height: 600px;overflow: auto;" id="fm-preview">

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

    {@each data as item, index}
    {@if item.type==1||item.type==3}
    <div class="testItem items testStyle2">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
        </div>
        <div class="testScore">
            {@each item.topicOptList as itm, idx}
            <div class="option">
                <input data-answer="\${itm.answer}" type="radio" name="test_\${index}" id="option_\${index}_\${idx}"/>
                <label for="option_\${index}_\${idx}">\${itm.content}</label>
            </div>
            {@/each}
        </div>
    </div>

    {@else if item.type == 2}
    <div class="testItem items testStyle2">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
        </div>
        <div class="testScore">
            {@each item.topicOptList as itm, idx}
            <div class="option">
                <input data-answer="\${itm.answer}" type="checkbox" name="test_\${index}"
                       id="option_\${index}_\${idx}"/>
                <label for="option_\${index}_\${idx}">\${itm.content}</label>
            </div>
            {@/each}
        </div>
    </div>
    {@else if item.type == 4}
    <div class="testItem">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
        </div>
        <div class="input_text">
            <textarea maxlength="200" class="message" placeholder="请在此输入您要填写的内容~200字以内"></textarea>
        </div>
    </div>

    {@else if item.type == 5}
    <div class="testItem items" data-name="\${item.name}" data-introduce="\${item.introduce}">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} \${formatScore(item.tscore)}</span>
        </div>
        <div class="testScore">
            <div class="core">
                <div class="button subBtn">
                </div>
                <div class="score">
                    <p data-total="\${item.tscore}" class="number">\${item.tscore|parseIntF}</p>
                </div>
                <div class="button addBtn"></div>
            </div>
        </div>
    </div>
    {@else}
    <div>111</div>
    {@/if}
    {@/each}

</script>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>