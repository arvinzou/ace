<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>学员注册</title>
    <jsp:include page="../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/common/css/mobileSelect.css"/>
    <link rel="stylesheet" type="text/css" href="js/loading/loading.css"/>
    <link rel="stylesheet" type="text/css" href="css/cityselect.css"/>

</head>
<body>
<div class="index">
    <div class="form-title">基本信息填写</div>
    <div class="form-box basicinfo">
        <div class="form-content form-border">
            <label class="form-label">姓名</label>
            <input class="form-input" type="text" name="name" placeholder="识别/输入真实姓名" onblur="searchByName();"/>
        </div>
        <div class="form-content">
            <label class="form-label">身份证号</label>
            <input class="form-input" maxlength="18" type="text" name="idCard" placeholder="识别/输入身份证号(选填)"/>
        </div>
        <div class="camera-box">
            <label><img id="upload" src="img/camera.png" class="camera" onclick="upload();"/></label>
            <%--                    <input type="file" id="file" name="file" accept="image/*" capture="camera" onchange="imgChange();" style="display: none;width: 100%;height: 100%;"/>--%>
        </div>
    </div>

    <div class="form-box">
        <div class="form-content form-border">
            <label class="form-label">性别</label>
            <div class="radio-box">
                <div class="sex-box"><img id="nan" src="img/sex_unselect.png" class="sex-radio"
                                          onclick="selectSex(this,'1');"/><span class="radio-title">男</span></div>
                <div class="sex-box"><img id="nv" src="img/sex_unselect.png" class="sex-radio"
                                          onclick="selectSex(this,'2');"/><span class="radio-title">女</span></div>
            </div>
        </div>
        <div class="form-content form-border">
            <label class="form-label">籍贯</label>
            <input type="hidden" name="nativePlace" id="nativePlace">
            <div class="form-select" type="text" id="nativePlacePicker"><span class="unselect">请选择籍贯</span></div>
        </div>
        <div class="form-content form-border">
            <label class="form-label">政治面貌</label>
            <div class="form-select" type="text" name="political" id="political"><span class="unselect">请选择政治面貌</span>
            </div>
            <img src="img/icon_select.png" class="redirect"/>
        </div>
        <div class="form-content form-border">
            <label class="form-label">单位</label>
            <input class="form-input" minlength="3" type="text" name="workUnitName" placeholder="请输入单位名称"/>
        </div>
        <div class="form-content form-border">
            <label class="form-label">职务</label>
            <input class="form-input" minlength="3" type="text" name="postName" placeholder="请输入职务名称"/>
        </div>
        <div class="form-content form-border">
            <label class="form-label">班次</label>
            <div class="form-select" type="text" id="classes"><span class="unselect">请选择班次</span></div>
            <img src="img/icon_select.png" class="redirect"/>
        </div>
        <div class="form-content form-border">
            <label class="form-label">毕业院校</label>
            <input class="form-input"  type="text" name="college" placeholder="请输入毕业院校"/>
        </div>
        <div class="form-content form-border">
            <label class="form-label">警号</label>
            <input class="form-input"  type="text" name="badgeNum" placeholder="请输入警号"/>
        </div>
        <div class="form-content">
            <label class="form-label">手机号码</label>
            <input class="form-input"  type="text" name="mobile" placeholder="请输入手机号码"/>
        </div>
    </div>
    <div class="footer">
        <img src="img/regist.png" onclick="regist();"/>
    </div>
</div>


<!--绑定微信栏目-->
<div class="modal" id="bindModal" style="display: none;">
    <form action="${pageContext.request.contextPath}/www/oauth2/auth" id="bindForm" method="post" onsubmit="bindWx();">
        <input type="hidden" name="jsonData"/>
        <input type="hidden" value="WX_BIND" name="action"/>
        <input type="hidden" value="/policeschool/www/registered/person/index.jsp" id="respUri" name="respUri"/>


        <div class="modal-main">
            <div class="modal-banner"><img src="img/bind.png" class='banner'/></div>
            <div class="modal-title">是否绑定微信</div>
            <div class="modal-sectitle">绑定微信快速登录</div>
            <div class="modal-opt">
                <div class="cancel"><span onclick="cancel();">取消</span></div>
                <div class="bind">
                    <button type="submit" class="submit">立即绑定</button>
                </div>
            </div>
        </div>
    </form>
</div>


<!--报名学员选择栏目-->
<div class="modal" id="nameModal" style="display: none;">
    <div class="modal-main user-main">
        <div class="modal-title" style="padding-top: 0.4rem;">选择报名信息</div>
        <div class="modal-content" id="userInfo">

        </div>
        <div class="modal-opt user-opt">
            <div class="cancel">
                <button class="close" id="close">取消</button>
            </div>
            <div class="bind">
                <button class="submit" onclick="confirm();">确定</button>
            </div>
        </div>
    </div>
    </form>
</div>
</body>

<script id="user-tpl" type="text/template">
    {@each data as item, index}
    {@if index == 0}
    <div class="user-item user-active"
         onclick="select(this, '\${item.id}');">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="25%">姓名：</td>
                <td width="75%">\${item.name}</td>
            </tr>
            <tr>
                <td width="25%">单位：</td>
                <td width="75%">\${item.workUnitName}</td>
            </tr>
            <tr>
                <td width="25%">职务：</td>
                <td width="75%">\${item.postName}</td>
            </tr>
        </table>
    </div>
    {@else}
    <div class="user-item user-unactive"
         onclick="select(this,'\${item.name}','\${item.workUnitName}','\${item.postName}');">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="25%">姓名：</td>
                <td width="75%">\${item.name}</td>
            </tr>
            <tr>
                <td width="25%">单位：</td>
                <td width="75%">\${item.workUnitName}</td>
            </tr>
            <tr>
                <td width="25%">职务：</td>
                <td width="75%">\${item.postName}</td>
            </tr>
        </table>
    </div>
    {@/if}
    {@/each}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/mobileSelect.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script type="text/javascript" src="js/loading/loading.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="js/city.js"></script>
<script type="text/javascript" src="js/student.js"></script>
</html>
