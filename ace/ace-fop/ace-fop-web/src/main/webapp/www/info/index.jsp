<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <title>法人信息</title>
    <jsp:include page="/dynamic/common/common-www.jsp"/>
</head>
<body>
<form  id="fm-edit">
</form>
<style>
    .label-red{
        color:red
    }

</style>
</body>
<jsp:include page="/dynamic/common/footer-1-www.jsp"/>
<script src="${portalPath}/content/common/js/dict_fop.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="js/act.js" ></script>
</html>

<script id="tpl-fm" type="text/template">

    <div class="weui-cells__title">法人基本信息</div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label"><span class="label-red">*</span>姓名</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="realName" value="\${data.o.realName}" placeholder="请输入姓名">
            </div>
            <div class="error-realName" style="color:red"></div>
        </div>

        <div class="weui-cells__title">性别</div>
        <div class="weui-cells_radio">
            <label class="weui-cell weui-check__label" for="x11">
                <div class="weui-cell__bd">
                    <p>男</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" class="weui-check" name="sex" id="x11" \${data.o.sex==1?'checked="checked"':''}>
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="x12">

                <div class="weui-cell__bd">
                    <p>女</p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" name="sex" class="weui-check" id="x12" \${data.o.sex==2?'checked="checked"':''}>
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">籍贯</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="nativePlace" value="\${data.o.nativePlace}" placeholder="请输入籍贯">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label for="" class="weui-label">出生日期</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" value="\${data.o.birthDate}" name="birthDate">
            </div>
        </div>
        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">民族</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" name="nationality">
                    {@each data.dict['09'] as item, index}
                    <option value="\${item.CODE}" \${item.CODE==data.o.nationality?'selected=selected':''}>\${item.NAME}</option>
                    {@/each}
                </select>
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">政治面貌</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="political" value="\${data.o.political}" placeholder="请输入政治面貌">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label for="" class="weui-label">工作时间</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="date" value="\${data.o.recruitmentDate}" name="recruitmentDate">
            </div>
        </div>

        <div class="weui-cell weui-cell_select weui-cell_select-after">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">文化程度</label>
            </div>
            <div class="weui-cell__bd">
                <select class="weui-select" name="education">
                    {@each data.dict['10'] as item, index}
                    <option value="\${item.CODE}" \${item.CODE==data.o.education?'selected=selected':''}>\${item.NAME}</option>
                    {@/each}
                </select>
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">技术职称</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="skillJobTitle" value="\${data.o.skillJobTitle}" placeholder="请输入技术职称">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">单位职务</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="deptPost" value="\${data.o.deptPost}" placeholder="请输入单位职务">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">身份证号</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="identityCard" value="\${data.o.identityCard}" placeholder="请输入身份证号">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">社会职务</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="societyPost" value="\${data.o.societyPost}" placeholder="请输入主要社会职务">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">传真</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="fax" value="\${data.o.fax}" placeholder="请输入传真">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">邮政编码</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" pattern="[0-9]*"  name="postcode" value="\${data.o.postcode}" placeholder="请输入邮政编码">
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">办公电话</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="phoneNumber" value="\${data.o.phoneNumber}" placeholder="请输入办公电话">
            </div>
        </div>


        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">移动电话</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" pattern="[0-9]*"  name="mobileNumer" value="\${data.o.mobileNumer}" placeholder="请输入移动电话">
            </div>
            <div class="error-mobileNumer" style="color:red"></div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label for="" class="weui-label">电子邮箱</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text"  name="email" value="\${data.o.email}" placeholder="请输入电子邮箱">
            </div>
        </div>
        <div class="weui-cells__title">简历</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入简历" rows="5" name="resume">\${data.o.resume}</textarea>
                    <div class="weui-textarea-counter"><span></span></div>
                </div>
            </div>
        </div>
        <div class="weui-cells__title">特长及成就</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入特长及成就" rows="5" name="achievement">\${data.o.achievement}</textarea>
                    <div class="weui-textarea-counter"><span></span></div>
                </div>
            </div>
        </div>

        <div class="weui-btn-area">
            <button class="weui-btn weui-btn_primary" type="submit">确定</button>
        </div>

    </div>

</script>
