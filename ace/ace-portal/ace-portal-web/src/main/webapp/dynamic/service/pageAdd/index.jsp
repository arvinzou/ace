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
    <script src="${portalPath}/content/service/tpl/act.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/js/loader.js?v=${cfg.version}"></script>

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
                                    <span>选择模板</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <div class="listBox clearFloat">


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


<script id="tpl-box" type="text/template">
    <div>
    {@each data.data as item}
        <div class="list">
            <div class="ewmBox">
                <img src="\${item.qrcoteUrl}" class="ewmImg">
                <div>扫一扫</div>
                <div>预览页面效果</div>
            </div>
            <p class="listTitle">\${item.name}</p>
            <p class="listIntro">一个列表组成的页面</p>
            <img src="\${item.cover}" class="img-tpl">
            <a href="../pageAdd/index.jsp?id=\${item.id}" class="btn btn_primary chooseBtn">选择</a>
        </div>
    {@/each}
    </div>
</script>
</body>
</html>