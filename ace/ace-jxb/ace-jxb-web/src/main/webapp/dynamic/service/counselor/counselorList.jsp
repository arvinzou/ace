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
    <jsp:include page="../../common/base.jsp"/>
    <script src="${portalPath}/content/common/js/loader.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/service/counselor/counselor.js?v=${cfg.version}"></script>
</head>
<style>
    .counselor-list {

    }

    .counselor-box {
        float: left;
        margin-right: 20px;
    }

    .uc-recomend-counselor-img {
        width: 40px;
        height: 40px;
    }
</style>

<body>
<div class="page-wrapper">

    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="${pageContext.request.contextPath}/www/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <a>咨询师管理</a>
                                </li>
                            </ul>
                            <div class="page-content-inner">
                                <!---==============================================-->
                                <%--操作框--%>
                                <div class="widget-body">
                                    <div class="widget-main padding-6">
                                        <form action="#" id="fm-search">
                                            名称： <input name="fullName" type="text" style="width: 200px;"/>
                                            <button class="btn btn-info" id="btn-search"
                                                    authority="${pageContext.request.contextPath}/counselor/findCounselorList">
                                                <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                                            </button>
                                            <button class="btn btn-info" id="btn-view-edit"
                                                    authority="${pageContext.request.contextPath}/counselor/audit">
                                                <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <%--老师列表--%>
                                <div class="counselor-list" style="margin-top: 10px;">
                                    <%--<div class="counselor-box" data-log-act="click" data-log-id="expertCard"--%>
                                    <%--data-log-data="{&quot;logType&quot;:&quot;instructor&quot;,&quot;itemId&quot;:1028863767,&quot;itemName&quot;:&quot;魏巍老师&quot;,&quot;itemUrl&quot;:&quot;//study.163.com/topics/masters_weiwei&quot;}">--%>
                                    <%--<a target="_blank" class="j-href" href="//study.163.com/topics/masters_weiwei">--%>
                                    <%--<div class="uc-recomend-expert-img">--%>
                                    <%--<img src="//edu-image.nosdn.127.net/fb9279b2-e81d-4a35-9803-5a753e61b187.jpg?imageView&amp;quality=100&amp;thumbnail=225y225&amp;&amp;crop=0_0_225_185&amp;type=webp"--%>
                                    <%--alt="行家图片" data-src="" id="auto-id-1532136583725">--%>
                                    <%--</div>--%>
                                    <%--<div class="uc-recomend-expert-context">--%>
                                    <%--<p class="uc-recomend-expert-name">魏巍老师</p>--%>
                                    <%--<p class="uc-recomend-expert-title">浙派名师，网易明狮工作室负责人</p>--%>
                                    <%--</div>--%>
                                    <%--</a>--%>
                                    <%--</div>--%>
                                </div>
                                <!--=======================================-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>

</div>

<script id="tpl-counselor-box" type="text/template">
    {@each rows as item}
    <div class="counselor-box" data-log-act="click" data-log-id="expertCard"
         data-log-data="">
        <a target="_blank" class="j-href" href="">
            <div class="uc-recomend-counselor-img">
                <img src="\${item.imagePhotoUrl}" alt="咨询师形象" data-src="">
            </div>
            <div class="uc-recomend-expert-context">
                <p class="uc-recomend-counselor-name">\${item.name}</p>
                <p class="uc-recomend-counselor-certification">\${item.certification}</p>
            </div>
        </a>
    </div>
    {@/each}
</script>
</body>
</html>