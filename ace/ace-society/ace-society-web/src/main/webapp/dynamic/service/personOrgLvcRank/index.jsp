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
    <title>社会组织信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
              rel="stylesheet" type="text/css"/>
    <%--custom css--%>
</head>
<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<div class="portlet light">
    <div class="portlet-body">

        <div class="row custom-toolbar">
            <div class="col-md-4">

        </div>

         <div class="col-sm-7" style="float:right">
    <form onsubmit="return t_query()">
         <div class="input-group">
              <div style="float:right;margin-right:15px" >
              时间：<input autocomplete="off" class="easyui-datebox" name="startDate"
                     style="width: 200px; height: 30px; line-height: 25px;"> 至
                    <input autocomplete="off" class="easyui-datebox" name="endDate"
                     style="width: 200px; height: 30px; line-height: 25px;">
              </div>
         <div class="input-group-btn">
              <button class="btn  btn-default search_btn" type="submit">搜索</button>
         </div>
         </div>
     </form>
</div>


        <div class="table-scrollable">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="30%"> 排名</th>
                    <th width="40%"> 用户</th>
                    <th width="30%"> 累计获得爱心币</th>
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
        <td> \${item.i}</td>
        <td> \${item.nickName}</td>
        <td> \${item.accPoints}</td>
    </tr>
    {@/each}
</script>


<%--详情juicer模板--%>
<script id="tpl-detail" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 view-label">排名</label>
            <div class="col-md-10">
                \${data.i}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 view-label">用户</label>
            <div class="col-md-10">
                \${data.nickName}
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 view-label">累计获得爱心币</label>
            <div class="col-md-10">
                \${data.accPoints}
            </div>
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
<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>

<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>