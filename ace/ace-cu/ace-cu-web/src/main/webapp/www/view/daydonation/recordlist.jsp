<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>使用记录</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layui/css/layui.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="css/daydonation.css?v=<%=System.currentTimeMillis() %>"/>
</head>

<body>
<div class="record_box">
    <div class="record_more">
        <div class="project_title">使用记录</div>
        <div class="project_content" id="recordList">

        </div>
    </div>
</div>

<script id="list-tpl" type="text/template">
    <ul class="layui-timeline">
        {@each data as item, index}
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">
                    <div class="project_record_title">
                        <div class="project_record_title01">\${item.useToProjectName}</div>
                        <div class="project_record_title02">使用资金：<span class="money_default">\${item.useAmount}</span></div>
                    </div>
                    <div class="project_record_info">
                        \$${item.useToProjectDesc}
                    </div>
                </div>
            </div>
        </li>
        {@/each}
    </ul>
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/recordlist.js?v=<%=System.currentTimeMillis() %>"></script>
</body>

</html>