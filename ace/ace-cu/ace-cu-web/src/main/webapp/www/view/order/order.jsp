<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>我要捐款</title>
		<link rel="stylesheet" type="text/css" href="css/order.css?v=<%=System.currentTimeMillis() %>" />
		<link rel="stylesheet" type="text/css" href="css/city.css?v=<%=System.currentTimeMillis() %>"/>
		<link rel="stylesheet" type="text/css" href="css/switch.css?v=<%=System.currentTimeMillis() %>" />
	</head>

	<body>
		<div class="order_box">
			<div class="order_content">
				<div class="order_form">
					<div class="title_01">选择捐款金额</div>
					<div class="money_01">
						<span class="lightborder" onclick="selectMoney(this, 10.00);" >¥ 10.00</span>
						<span onclick="selectMoney(this, 50.00);" >¥ 50.00</span>
						<span onclick="selectMoney(this, 100.00);">¥ 100.00</span>
					</div>
					<div class="money_02">
						<span onclick="selectMoney(this, 200.00);" >¥ 200.00</span>
						<span onclick="selectMoney(this, 500.00);" >¥ 500.00</span>
						<!--<span onclick="inputMoney(this);" class="other_amount">输入其他金额</span>-->
						<input onclick="inputMoney(this);" type='number' id="amountMoney"   class="other_amount" type="number" placeholder="输入金额" onfocus="this.placeholder=''" onblur="this.placeholder='输入金额'"  />
					</div>
					<div id="userInfo">
						<div class="title_01">捐款信息</div>
						<div>
							<p>
								<span class="statement" style="color: #EA4436;font-size: 0.35rem;font-weight: bold;">声明：您的个人信息仅后台可见，不会对外公开！</span>
							</p>
						</div>
						<div class="form">
							<p>
								<span class="form_title">捐款人姓名</span>
								<input id="realName" class="form_text" maxlength="10" type="text"  placeholder="请输入捐款人姓名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入捐款人姓名'" />
							</p>
						</div>
						<div class="form">
							<p>
								<span class="form_title">联系电话</span>
								<input id="phoneNum" maxlength="11" class="form_text" type="number" placeholder="请输入联系电话" onfocus="this.placeholder=''" onblur="this.placeholder='请输入联系电话'"  onchange="if(/\D/.test(this.value)){alert('联系电话格式不正确！');this.value='';}"/>
							</p>
						</div>
						<div class="form">
							<p>
								<span class="form_title">捐款单位名称</span>
								<input id="donatePostName" type="text" name="donatePostName" class="form_text" type="text" placeholder="请输入捐款单位名称" onfocus="this.placeholder=''" onblur="this.placeholder='请输入捐款单位名称'" />
							</p>
						</div>
					</div>
					<div class="form_max">
						<p style="padding-top: 0.3rem;padding-bottom: 0.3rem;">留言</p>
						<p>
							<textarea id="message" placeholder="请填写留言内容"></textarea>
						</p>
					</div>
					<div class="form">
						<div class="left_none">匿名捐赠</div>
						<div class="right_none">
							<span id="no"><img src="img/no.png" style="width: 0.5rem;height: 0.5rem;"/></span>
							<span id="yes" style="display: none;"><img src="img/yes.png" style="width: 0.5rem;height: 0.5rem;"/></span>
						</div>
					</div>
					<div class="form">
						<div class="switch_title">是否需要票据</div>
						<div class="testswitch">
							<input class="testswitch-checkbox" id="onoffswitch" type="checkbox">
							<label class="testswitch-label" for="onoffswitch">  
					                <span class="testswitch-inner"></span>
					                <span class="testswitch-switch switchoff"></span>
					            </label>
						</div>
					</div>
					<div id="bill_information" class="bill_information" style="display: none;">
						<div class="title_01">票据收货信息</div>
						<div class="form">
							<p>
								<span class="form_title">收货人姓名</span>
								<input id="billName" class="form_text" type="text" maxlength="10" type="text" placeholder="请输入姓名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入姓名'" />
							</p>
						</div>
						<div class="form">
							<p>
								<span class="form_title">联系电话</span>
								<input id="billphoneNumber" class="form_text" type="number" maxlength="11" placeholder="请输入收货人联系电话" onfocus="this.placeholder=''" onblur="this.placeholder='请输入收货人联系电话'" onchange="if(/\D/.test(this.value)){alert('联系电话格式不正确！');this.value='';}"/>
							</p>
						</div>
						<div class="form">
							<div class="left_box">省市地区</div>
							<div class="content-block right_box">
								<input class="form_text" id="demo1" type="text" readonly="" placeholder="请选择地区"  value="">
								<input id="value1" type="hidden" value="">
							</div>
						</div>
						<div class="form">
							<p>
								<span class="form_title">详细地址</span>
								<input id="detailAddress" class="form_text" type="text" placeholder="请输入详细地址" onfocus="this.placeholder=''" onblur="this.placeholder='请输入详细地址'" />
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="btnDiv"><button class="donateBtn" onclick="donateMoney1();">立即捐款</button></div>
		</div>

		<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/jweixin-1.2.0.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/LAreaData1.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/LAreaData2.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/LArea.js?v=<%=System.currentTimeMillis() %>"></script>
		<script type="text/javascript" src="js/order.js?v=<%=System.currentTimeMillis() %>"></script>
	</body>
<script type="text/javascript">
    var me = window;
    (function init(){
        me.number = getId('amountMoney');
        me.n = '';
        initEvent();

        function initEvent(){
            me.number.addEventListener('keyup', number, false);
        }
        function getId(id){
            return document.getElementById(id);
        }
        function number(){//金额，
            console.log(this.value);
            if(this.value == ''){//校验，用户输入后，输入框的值是否为数字，当不为数字时，取到的为空
                this.value='';
                this.value = me.n;
                return
            }
            if(this.value.length > 8){
                this.value='';
                this.value = me.n;
                return
			}
            if(!/^\d{0,9}\.{0,1}(\d{1,2})?$/.test(this.value)){//校验不超过两位小数
                this.value = me.n;
            }
            if(window.event.keyCode != 8){
                if(this.value%1==0 && me.n==this.value){//当用户按下非删除键时，新值和旧值相同,旧值为整数
                    return
                }
                if(me.n.indexOf(".")>-1 && me.n==this.value){//当用户按下非删除键时，新值和旧值相同,旧值为小数
                    this.value='';
                    this.value = me.n;
                    return
                }
            }else{
                if(me.n.indexOf(".")>-1 && this.value.indexOf(".")<0){//当按下删除时，删掉小数点时
                    me.n = this.value;
                    return
                }
            }
            /* me.n = this.value*/
            if(me.n.indexOf(".")>0){
                this.value = me.n;
            }
        }
    }())
</script>
</html>