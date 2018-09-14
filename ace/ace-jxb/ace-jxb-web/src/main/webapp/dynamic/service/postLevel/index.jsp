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

    <jsp:include page="../../common/header.jsp"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/reservation/css/style.css">
    <script src="${pageContext.request.contextPath}/content/service/postLevel/js/act.js?v=${cfg.version}"></script>

</head>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
                <div class="portlet light">

                    <div class="portlet-body">


                        <div class="row custom-toolbar">
                            <div class="col-md-1">
                                <a onclick="javascript:createLevel()"
                                   class="btn  green">
                                    <i class="fa fa-plus"></i>
                                    添加配置
                                </a>
                            </div>
                            <div class="col-md-1">

                            </div>
                            <div class="col-md-10">

                            </div>
                        </div>


                                <div class="table-scrollable">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr class="uppercase">

                                            <th> 岗位序号</th>
                                            <th> 岗位名称</th>
                                            <th> 营业额要求</th>
                                            <th> 发展人数要求</th>
                                            <th> 咨询师分成比例</th>
                                            <th colspan="2">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="level_list">

                                        </tbody>
                                    </table>


                        </div>
                    </div>
                </div>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

</body>
<script id="temp_level_list" type="text/template">
    {@each data as item}
    <tr data-id="\${item.id}">
        <td><input type="text" class="postIndex" value="\${item.postIndex}"></td>
        <td><input type="text" class="postName" value="\${item.postName}"></td>
        <td><input type="text" class="turnover" value="\${item.turnover}"></td>
        <td><input type="text" class="counselorNum" value="\${item.counselorNum}"></td>
        <td><input type="text" class="ratio" value="\${item.ratio}"></td>
        <td colspan="2"><a class="delectBtn">删除</a></td>
    </tr>
    {@/each}
</script>

<style>
    input {
        height: 30px;
        border: 0px;
    }

    .delectBtn, .insertBtn, .cancelBtn {
        cursor: pointer;
    }
</style>

<jsp:include page="/dynamic/common/footer.jsp" />
</html>

