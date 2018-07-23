<%--
  Created by IntelliJ IDEA.
  User: HuaCai008
  Date: 2018/7/17
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>juicer demo page</title>
</head>
<jsp:include page="../dynamic/common/base.jsp"/>
<script src='../content/common/assets/js/gz/jquery.min.js'></script>
<script type="text/javascript" src='../content/common/juicer/juicer-min.js'></script>
<script id="tpl" type="text/template">
    {@each LIST as item,index}
    {@if item.NOTICE_READ==0}
    <ul>
        <li>
            <div>
                <ul>
                    <li>
                        <label>\${item.NOTICE_TITLE}</label>
                        <span>\${item.NOTICE_TIME}</span>
                    </li>
                    <li>
                        <label>
                            \${item.NOTICE_CONTENT}
                        </label>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
    {@/if}
    {@if item.NOTICE_READ==1}
    <ul>
        <li>
            <div>
                <ul>
                    <li>
                        <label>\${item.NOTICE_TITLE}</label>
                        <span>\${item.NOTICE_TIME}</span>
                    </li>
                    <li>
                        <label>
                            \${item.NOTICE_CONTENT}
                        </label>
                    </li>
                </ul>
            </div>
        </li>
    </ul>
    {@/if}
    {@/each}
</script>
<script id="tpl2" type="text/template">
    <div class="row" style="padding:10px">
        <div class="labelItem hide">
            <span class="labelItemHeader">portal.department.id</span>
            <br>
            <span name="departmentId">\${departmentId}</span>
        </div>
        <div class="labelItem">
            <span class="labelItemHeader">统一社会信用代码</span>
            <br>
            <span name="creditCode">\${departmentId}</span>
        </div>
    </div>
</script>
<body>
<div id="P040201">
    <div class="page" data-page="noticeMessage" title="公告消息">
    </div>
    <div class="page" data-page="noticeDetail" title="公告详情">
    </div>
</div>
</body>
<script type="text/javascript" src="juicer.js"></script>
<script type="text/javascript">
    me.init();
    console.log("...end");
</script>

</html>
