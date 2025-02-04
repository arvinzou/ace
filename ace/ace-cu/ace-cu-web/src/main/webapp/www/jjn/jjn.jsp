<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>救急难</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/need/layer.css?v=<%=System.currentTimeMillis()%>"/>
    <link rel="stylesheet" type="text/css" href="css/jjn.css?v=<%=System.currentTimeMillis()%>"/>
</head>
<body>
<div class="mainbox">
    <div class="banner">
        <img src="img/default.png" style="width: 100%;height: 100%;"/>
    </div>
    <div class="raisebox" id="project">

    </div>
</div>
<div class="raise_btnbox">
    <button class="raise_btn" onclick="raiseMoney();">申请筹款</button>
</div>

<script id="project-tpl" type="text/template">
    {@each data as item, index}
    <div class="raise_item" onclick="showProjectInfo('\${item.id}')">
        <div class="raise_item_image">
            {@if item.coverUrl != '' && item.coverUrl != undefined && item.coverUrl != null}
            <img src="\${item.coverUrl}"/>
            {@else}
            <img src="img/project_default.png"/>
            {@/if}
        </div>
        <div class="raise_item_cnt">
            <div class="raise_item_title">\${item.projectName}</div>
            <div class="raise_item_info">\${item.title}</div>
            <div class="raise_item_detail">
                <div class="detail01">已募集<span>\${item.collectAmount}</span>元</div>
                {@if item.balanceDays != '0'&&item.status=='2'}
                <div class="detail02">剩余<span>\${item.balanceDays}</span>天</div>
                {@else if item.balanceDays == '0'&&item.status=='2'}
                <div class="detail02">已结束</div>
                {@else if item.status=='1'}
                <div class="detail02">待审核</div>
                {@/if}
                <div style="clear: both;"></div>
            </div>
        </div>
        <div style="clear: both;"></div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/layer.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="js/jjn.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
<!--弹框提示层-->
<div id="warning" style="display: none;width: 9rem;">
    <div class="warn_content">
        <h3 class="warn_title">平台声明</h3>
        <p>
            1.申请人在仔细阅读《实施办法》后，在本平台填写申请信息；
        </p>
        <p>
            2.项目发起人为常德市慈善总会，对项目真实性负责；
        </p>
        <p>
            3.郑重承诺：本平台不收取任何费用。
        </p>
    </div>
</div>
</html>
