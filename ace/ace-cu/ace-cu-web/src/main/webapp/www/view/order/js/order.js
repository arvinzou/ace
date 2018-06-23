$(document).ready(function() {
	$("#onoffswitch").on('click', function() {
		clickSwitch()
	});

	var clickSwitch = function() {
		if($("#onoffswitch").is(':checked')) {
			$("#bill_information").show();
		} else {
			$("#bill_information").hide();
		}
	};

	var area1 = new LArea();
	area1.init({
		'trigger': '#demo1', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
		'valueTo': '#value1', //选择完毕后id属性输出到该位置
		'keys': {
			id: 'id',
			name: 'name'
		}, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
		'type': 1, //数据源类型
		'data': LAreaData //数据源
	});
	area1.value = [1, 13, 3]; //控制初始位置，注意：该方法并不会影响到input的value
	
	$("#no").click(function(){
		$(this).hide();
		$(this).siblings().show();
	});
	$("#yes").click(function(){
		$(this).hide();
		$(this).siblings().show();
	});
});

function selectMoney(obj) {
	$(obj).addClass("lightborder");
	$(obj).siblings().removeClass("lightborder");
	$(obj).parent().siblings(".money_02").find("span").removeClass("lightborder");
	$("#amountMoney").hide();
}

function inputMoney(obj) {
	$(obj).addClass("lightborder");
	$(obj).parent().siblings(".money_01").find("span").removeClass("lightborder");
	$("#amountMoney").show();
}