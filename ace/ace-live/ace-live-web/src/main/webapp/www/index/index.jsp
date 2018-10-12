<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>常德融媒</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="data-spm" content="zy-spot-web.v3">
    <link rel="stylesheet" href="css/style.css"/>
    <script src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
    <!--用戶信息-->
    <script src="${pageContext.request.contextPath}/www/oauth2/cfg.do"></script>
</head>
<body>
<div class="fn-contain">
    <header data-spm="header" class="xcy-head">
        <span id="j-logo" class="rowimg"></span>
        <span id="j-title" class="title"></span>
        <span id="j-livenum" class="num"><em>_</em>个现场</span>
    </header>
    <div class="xcy-head-place"></div>
    <section id="j-livelist" data-spm="list" class="xcy-list">
        <script type="text/template">
            <ul>
                {@each data as item}
                <li class="news-item" data-id="\${item.id}"
                    data-spm-click="category=list;action=click;itemId=\${item.id}">
                    {@if item.cover}
                    <img src="\${item.cover}">
                    {@else}

                    {@/if}
                    <div class="summary">
                        <h2>\${item.topic}</h2>
                        <div class="fn-clear">
            <span class="state box state-\${item.state} type-\${item.type}">
             {@if item.state == 1}预告{@/if} {@if item.state == 2}直播中{@/if} {@if item.state == 3}已结束{@/if}
              <em class="icon"></em>
            </span>
                            {@if item.state != 1}
                            <span class="join">\${item.numOfPartakeString}参与</span>
                            {@/if}
                        </div>
                    </div>
                </li>
                {@/each}
            </ul>
        </script>
    </section>


</div>
<script src="${pageContext.request.contextPath}/www/common/js/zepto-1.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/juicer-min.js"></script>
<script src="${pageContext.request.contextPath}/www/common/js/common.js"></script>
<script src="js/act.js"></script>
</body>
</html>