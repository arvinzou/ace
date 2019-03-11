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
    <title>测试答案管理</title>
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
            <div class="col-md-9">

            </div>

            <div class="col-md-3">
                <form id="fm-search">
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

                    <th width="10%"> 测试名称</th>
                    <th width="10%"> 总分</th>
                    <th width="10%"> 答卷人</th>
                    <th width="20%"> 创建日期</th>
                    <th width="10%"> 状态</th>
                    <th width="20%">操作</th>
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
        <td>\${item.score}</td>
        <td>\${item.createUserName}</td>
        <td>\${item.createDate}</td>

        <td>
            {@if item.status==0}
            <span class="label label-lg label-danger">删除</span>
            {@else if item.status==1}
            <span class="label label-lg label-info">未评分</span>
            {@else if item.status==2}
            <span class="label label-lg label-success">已评分</span>
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
            <a href="#" data-toggle="modal" data-status="\${item.status}" data-id="\${item.id}" data-score="\${item.score}"
               data-target="#modal-preview">查看</a>
        </td>
    </tr>
    {@/each}
</script>
﻿


<div class="modal fade" role="dialog" id="modal-preview">
    <div class="modal-dialog" STYLE="width: 1100px;" role="document">
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
                <div class="rightBar">
                    <h2>总分:<span class="totalScore">10</span></h2>
                    <div>
                        <p><span class="point bluepoint"></span>正确答案</p>
                        <p><span class="point redpoint"></span>你的选择</p>
                    </div>
                    <div class="button">
                        确定
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

    {@each data.o as item, index}
    {@if item.type==1||item.type==3}
    <div class="testItem items testStyle2">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} (得分:\${scored(item)})</span>
        </div>
        <div class="testScore">
            {@each item.topicOptRstList as itm, idx}
            <div class="option">
                <span class="radio \${isRight(itm)}"></span>
                <label>\${itm.content}</label>
            </div>
            {@/each}
        </div>
    </div>

    {@else if item.type == 2}
    <div class="testItem items testStyle2">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} (得分:\${scored(item)})</span>
        </div>
        <div class="testScore">
            {@each item.topicOptRstList as itm, idx}
            <div class="option \${thisIsRight(item.topicOptRstList)}">
                <span class="checkbox \${isRight(itm)}"></span>
                <label>\${itm.content}</label>
            </div>
            {@/each}
        </div>
    </div>
    {@else if item.type == 4}
    <div class="testItem">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} (得分:\${item.youScore?item.youScore:0})</span>
        </div>
        <div class="input_text">
            <div maxlength="200" class="message">\${item.answer}</div>
            {@if data.status==1}
            打分:<input step="0.1" name="inputScore" onblur="checkNumber(this,'\${item.tscore}')" min="0"
                      max="\${item.tscore}" data-id='\${item.id}' type="number"/>
            {@/if}
        </div>
    </div>
    {@else if item.type == 5}
    <div class="testItem items" data-name="\${item.name}" data-introduce="\${item.introduce}">
        <div class="testTitle">
            <span class="text">Q\${formatIndex(index)}、\${item.content} (得分:\${item.youScore?item.youScore:0})</span>
        </div>
        <div class="testScore">
            <div class="core">
                <div class="score">
                    <p data-total="\${item.tscore}" class="number">\${item.answer}</p>
                </div>
            </div>
            {@if data.status==1}
            打分:<input step="0.1" name="inputScore" onblur="checkNumber(this,'\${item.tscore}')" min="0"
                      max="\${item.tscore}" data-id='\${item.id}' type="number"/>
            {@/if}
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
<script src="js/test.js?v=${cfg.version}"></script>
</html>