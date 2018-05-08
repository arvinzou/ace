<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
    <jsp:include page="../../common/base.jsp" />
    <script src="${portalPath}/content/service/pages/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?version=${cfg.version}"></script>
</head>
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
                                    <a href="index4.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>我的页面</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <section style="width:1200px;margin:0 auto;text-align: center;box-sizing:border-box;">
                                </section>
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
<script id="tpl-box" type="text/template">
    <div>
        {@each data.data as item}
        <div class="list_box" id="div\${item.id}">
            <div class="sys" data-itid="\${item.id}">
                <img src="${pageContext.request.contextPath}/www/img/sys.png">
                <img src="http://www.xmypage.com//backend/qrcode.php?data=http://www.xmypage.com/model1_63635.html" style="display: none;">
            </div>
            <div class="switch" style="display:block" data-id="\${item.id}">
                <img src="${pageContext.request.contextPath}/www/img/switch.png" title="页面转移给好友"></div>
            <div class="domain" style="display:block" data-id="\${item.id}"><img src="${pageContext.request.contextPath}/www/img/domain.png" title="域名绑定设置"></div>
            <h2 class="page_name">\${item.name}</h2>
            <p class="update_time">更新于\${item.lastModifyDate}</p>
            <div class="list">
                <div class="simulator_hd">
                    <h4 class="title">\${item.name}</h4>
                </div>
                <iframe src="${pageContext.request.contextPath}/www/page/\${item.tplId}/index.jsp" class="list_frame" scrolling="no" onload="sort_overflow_hidden(this)"></iframe>
                <input type="text" id="pageurlinput_0" value="http://www.xmypage.com/model1_63635.html" class="pageurlinput">
            </div>
            <div class="list_menu">
                <img src="${pageContext.request.contextPath}/www/img/BaiduShurufa_2016-9-14_1-55-40.png" onclick="" data-toggle="tooltip" data-placement="top" title="编辑">
                <span></span>
                <img src="${pageContext.request.contextPath}/www/img/BaiduShurufa_2016-9-14_1-55-53.png" data-toggle="tooltip" data-placement="top" title="复制链接" >
                <span></span>
                <img src="${pageContext.request.contextPath}/www/img/BaiduShurufa_2016-9-14_1-56-4.png" onclick="delete_page('\${item.id}','\${item.name}')" data-toggle="tooltip" data-placement="top" title="删除">
            </div>

        </div>
        {@/each}
    </div>
</script>
</body>
</html>