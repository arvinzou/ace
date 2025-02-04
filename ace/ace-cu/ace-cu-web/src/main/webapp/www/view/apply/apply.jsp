<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>申请</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/need/layer.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="css/apply.css?v=<%=System.currentTimeMillis() %>"/>
</head>

<body>
<div class="applybox">
    <div class="plate_01">
        <div class="title01">申请求助</div>
        <div class="form_01">
            <div class="title02">
                <span class="imagebox"><img src="img/identity.png" width="100%" height="100%"/></span>
                <span class="titlebox">身份认证</span>
            </div>
            <div class="form">
                <p>
                    <span class="form_title">真实姓名</span>
                    <input class="form_text" name="realName" maxlength="10" type="text" placeholder="请输入真实姓名"
                           onfocus="this.placeholder=''" onblur="this.placeholder='请输入真实姓名'"/>
                </p>
            </div>
            <div class="form">
                <p>
                    <span class="form_title">身份证号</span>
                    <input class="form_text" name="idCard" maxlength="18" type="text" placeholder="请输入受助人身份证号码"
                           onfocus="this.placeholder=''" onblur="this.placeholder='请输入受助人身份证号码'"
                           onchange="if(!/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(this.value)){alert('身份证号码格式不正确！');this.value='';}"/>
                </p>
            </div>
            <div class="form">
                <p>
                    <span class="form_title">联系方式</span>
                    <input class="form_text" name="phoneNum" maxlength="11" type="text" placeholder="请输入手机号码"
                           onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号码'"
                           onchange="if(/\D/.test(this.value)){alert('联系电话格式不正确！');this.value='';}"/>
                </p>
            </div>
            <div class="form">
                <p>
                    <span style="width: 100%;font-size: 0.4rem;">相关证明材料上传</span>
                </p>
            </div>
            <div class="identify">
                <!--身份证正面-->
                <div class="positive">
                    <div class="pictureContainer container_01" id="positive">
                        <div class="viewPicture picture_01">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_01">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem;"/>
                            <p class="uploadPloadprogress progress_01">身份证正面</p>
                        </div>
                    </div>
                </div>
                <!--身份证反面-->
                <div class="unpositive">
                    <div class="pictureContainer container_02" id="unpositive">
                        <div class="viewPicture picture_02">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_02">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem"/>
                            <p class="uploadPloadprogress progress_02">低保证或扶贫手册</p>
                        </div>
                    </div>
                </div>
                <!--手持身份证照-->
                <div class="handIdentity">
                    <div class="pictureContainer container_05" id="handIdentity">
                        <div class="viewPicture picture_05">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_05">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem"/>
                            <p class="uploadPloadprogress progress_05">疾病诊断证明或意外事故</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="plate_02">
        <div class="form_01">
            <div class="title02">
                <span class="imagebox"><img src="img/project.png" width="100%" height="100%"/></span>
                <span class="titlebox">项目设置</span>
            </div>
            <div class="form">
                <p>
                    <span class="form_title">目标金额</span>
                    <input class="form_text" name="flagAmount" type="text" placeholder="请填写整数"
                           onfocus="this.placeholder=''" onblur="this.placeholder='请填写整数'"
                           onchange="if(/\D/.test(this.value)){alert('金额填写不正确！');this.value='';}"/>
                </p>
            </div>
            <div class="form">
                <p>
                    <span class="form_title">筹款标题</span>
                    <input class="form_text" maxlength="50" name="raiseTitle" type="text" />
                </p>
            </div>
            <div class="form_cmt">
                <p>
                    <span class="form_title">筹款说明</span>
                </p>
                <p>
                    <textarea class="project_cmt" minlength="10"  name="raiseDes" type="text"
                              placeholder="请详细填写筹款原因、筹款目的、自身状况和善款用途等信息（10个字以上）" onfocus="this.placeholder=''"
                              onblur="this.placeholder='请详细填写筹款原因、筹款目的、自身状况和善款用途等信息（10个字以上）'"
                              onchange="if((this.value).length<10){alert('筹款说明需在10字以上！');this.value='';}"></textarea></textarea>
                </p>
            </div>
            <div class="form">
                <p>
                    <span class="form_title01">其他证明(最多上传3张)</span>
                </p>
            </div>
            <div class="identify">
                <!--证明-->
                <div class="positive" id="datum01">
                    <div class="pictureContainer container_03" id="prove1">
                        <div class="viewPicture picture_03">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_03">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem;"/>
                            <p class="uploadPloadprogress progress_03">上传证明</p>
                        </div>
                    </div>
                </div>
                <!--证明-->
                <div class="unpositive" id="datum02" style="display: none;">
                    <div class="pictureContainer container_04" id="prove2">
                        <div class="viewPicture picture_04">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_04">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem"/>
                            <p class="uploadPloadprogress progress_04">上传证明</p>
                        </div>
                    </div>
                </div>

                <div class="handIdentity" id="datum03" style="display: none;">
                    <div class="pictureContainer container_06" id="prove3">
                        <div class="viewPicture picture_06">
                            <img class="undis" src="" style="width: 100%;height: 100%;object-fit: cover;">
                        </div>
                        <div class="uploadText text_06">
                            <img src="img/add.png" style="width:0.5rem;height:0.5rem"/>
                            <p class="uploadPloadprogress progress_06">上传证明</p>
                        </div>
                    </div>
                </div>
                <div style="clear: both;"></div>
            </div>
            <div class="warn">
						<span class="warn_img">
							<img src="img/warn.png" style="width: 100%;height: 100%;"/>
						</span>
                <span class="warn_info">
							求助者介绍不得出现任何联系方式和银行卡等收款信息，包括但不限于手机号码、座机号码、微信号、支付宝账号、银行卡等私人信息，否则项目验证均不予以通过。
						</span>
            </div>
        </div>
    </div>
    <div class="operation">
        <button onclick="applyProject();">提交申请</button>
    </div>
</div>

<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/plupload/plupload.full.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/layer.mobile-v2.0/layer_mobile/layer.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/apply.js"></script>
</body>

<!--申请成功提示框-->
<div id="success_box" class="success_box" style="display: none;">
    <div class="sbanner">
        <img src="img/success_banner.png"/>
    </div>
    <div class="sinfo">
        <p>申请提交成功~</p>
    </div>
    <div class="sdetail">
        <p>资料审核中，请耐心等待</p>
        <p>可前往<a href="javascript:void(0)" onclick="showProjectProgress();">个人中心</a>查看审核状态</p>
    </div>
</div>
</html>