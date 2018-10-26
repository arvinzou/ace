<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>常德慈善总会</title>
    <link rel="stylesheet" type="text/css" href="css/cuproject.css?v=<%=System.currentTimeMillis()%>"/>
</head>
<body>
<div class="cubox" id="projectList">
    <!--<div class="project_title">凝聚 <span class="project_title01">468.060.208 </span>份爱心</div>-->
</div>

<script id="projectTemp" type="text/template">
    {@each data as item, index}
    <div class="projectlist">
        <div class="project_item">
            <div class="project_image">
                <img src="\${item.coverUrl}" onclick="help('\${item.id}');"/>
            </div>
            <div class="slogan">\${item.projectName}</div>
            <div class="slogan01">\${item.title}</div>
            <button class="operation" onclick="help('\${item.id}');">给予帮助</button>
        </div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="js/cuproject.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>
