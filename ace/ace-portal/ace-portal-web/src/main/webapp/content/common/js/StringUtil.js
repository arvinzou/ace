function testisNum(object) {

	var s = document.getElementById(object.id).value;

	if (s != "") {
		if (isNaN(s)) {
			alert("请输入数字");
			object.value = "";
			object.focus();
		}
	}
}
// 2、电话号码，传真

// Code
// 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
function isTel(object) {
	// 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"

	var s = document.getElementById(object.id).value;
	var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	// var pattern
	// =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
	if (s != "") {
		if (!pattern.exec(s)) {
			alert('请输入正确的电话号码:电话号码格式为国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"');
			object.value = "";
			object.focus();
		}
	}
}
// 3、邮箱


// Code
function Check(object) {
	var s = document.getElementById(object.id).value;
	var pattern = /^[a-zA-Z0-9_\-]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/;
	if (s != "") {
		if (!pattern.exec(s)) {
			alert('请输入正确的邮箱地址');
			object.value = "";
			object.focus();
		}
	}

}
// 4、手机号码

// Code
// 校验手机号码：必须以数字开头，除数字外，可含有“-”
function isMobile(s) {

	var reg0 = /^13\d{5,9}$/;
	var reg1 = /^153\d{4,8}$/;
	var reg2 = /^159\d{4,8}$/;
	var reg3 = /^0\d{10,11}$/;
	var my = false;
	if (reg0.test(s))
		my = true;
	if (reg1.test(s))
		my = true;
	if (reg2.test(s))
		my = true;
	if (reg3.test(s))
		my = true;
	return my;
}
// Code
// 校验日期
function isdate(object) {
	var s = document.getElementById(object.id).value;
	var pattern = /^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/;
	if (s != "") {
		if (!pattern.exec(s)) {
			alert('请输入正确的日期');
			object.value = "";
			object.focus();
		}
	}
}
// 5、邮编

// Code
// 校验(国内)邮政编码
function isPostalCode(object) {
	var s = document.getElementById(object.id).value;
	var pattern = /^[0-9]{6}$/;
	if (s != "") {
		if (!pattern.exec(s)) {
			alert('请输入正确的邮政编码');
			object.value = "";
			object.focus();
		}
	}
}
// 6、日期

// Code
// 校验日期
function isdate(object) {
	var s = document.getElementById(object.id).value;
	var pattern = /^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/;
	if (s != "") {
		if (!pattern.exec(s)) {
			alert('请输入正确的日期');
			object.value = "";
			object.focus();
		}
	}
}

// 6、正数数值格式

// 校验正数数值格式
function isPositiveDecimal(value) {
	var pattern = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	if (value != "") {
		if (!pattern.exec(value)) {
			return [false,"必须为正数数值格式!",""];
		}else{
			return [true,"",""];
		}
	}
}