var verificatRet = {
    // 身份证
    identityCard: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
    //identityCard : /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}\d|[X]{1}$/,
    // 手机号
    mobilePhone: /^((1[3|4|5|7|8][0-9]{1})+\d{8})$/,
    // 电子邮箱
    email: /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/,
    // 座机
    phone: /^0\d{2,3}-?\d{7,8}$/,
    // 数字
    intNumber: /^[0-9]*$/,
    // 非零正整数
    NZ_number: /^\+?[1-9][0-9]*$/,
    // 非零负整数
    _NZ_number: /^\-[1-9][0-9]*$/,
    // 非负整数（正整数 + 0）
    _noIntNumber: /^\d+$/,
    // 非正整数（负整数 + 0）
    noIntNumber: /^((-\d+)|(0+))$/,
    // 整数
    intNumber: /^-?\d+$/,
    // 正浮点小数
    doubleNumber: /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,
    // 非正浮点小数（负浮点小数 + 0）
    _noDoubleNum: /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/,
    // 负浮点小数
    _doubleNumber: /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/,
    // 浮点数
    double: /^(-?\d+)(\.\d+)?$/,

    companyName: /^[\u4e00-\u9fa5|（|）|(|)]{2,50}$/
};


function validateform(idName) {
    if (idName.indexOf('companyName') == 0) {
        return validateCompanyName(idName);
    } else if (idName.indexOf('mobilePhone') == 0) {
        return validateMobilePhone(idName);

    } else if (idName.indexOf('email') == 0) {
        return validateEmail(idName);
    }
    else if (idName.indexOf('intNumber') == 0) {
        return validateIntNumber(idName);
    }
}


/*邮箱号码验证*/
function validateIntNumber(idName) {
    var result = {};//答案容器。
    var value = isNotNullTrim(idName);
    if (value) {
        if (verificatRet.intNumber.test(value)) {
            result.status = 0;
            result.message = value;
            return result;
        }
        else {
            result.status = 1;
            result.message = "数字格式。";
            return result;
        }
    } else {
        if (idName.indexOf('null') != -1) {
            result.status = 0;
            result.message = value;
            return result;
        } else {
            result.status = 1;
            result.message = "不能为空！";
            return result;
        }
    }
}


/*邮箱号码验证*/
function validateEmail(idName) {
    var result = {};//答案容器。
    var value = isNotNullTrim(idName);
    if (value) {
        if (verificatRet.email.test(value)) {
            result.status = 0;
            result.message = value;
            return result;
        }
        else {
            result.status = 1;
            result.message = "邮箱号码格式不对。";
            return result;
        }
    } else {
        if (idName.indexOf('null') != -1) {
            result.status = 0;
            result.message = value;
            return result;
        } else {
            result.status = 1;
            result.message = "不能为空！";
            return result;
        }
    }
}

/*手机号码验证*/
function validateMobilePhone(idName) {
    var result = {};//答案容器。
    var value = isNotNullTrim(idName);
    if (value) {
        if (verificatRet.mobilePhone.test(value)) {
            result.status = 0;
            result.message = value;
            return result;
        }
        else {
            result.status = 1;
            result.message = "手机号码格式不对。";
            return result;
        }
    } else {
        if (idName.indexOf('null') != -1) {
            result.status = 0;
            result.message = value;
            return result;
        } else {
            result.status = 1;
            result.message = "不能为空！";
            return result;
        }
    }
}

/*公司名称验证*/
function validateCompanyName(idName) {
    var result = {};//答案容器。
    var value = isNotNullTrim(idName);
    if (value) {
        if (verificatRet.companyName.test(value)) {
            result.status = 0;
            result.message = value;
            return result;
        }
        else {
            result.status = 1;
            result.message = "这不是公司名称。";
            return result;
        }
    } else {
        if (idName.indexOf('null') != -1) {
            result.status = 0;
            result.message = value;
            return result;
        } else {
            result.status = 1;
            result.message = "不能为空！";
            return result;
        }
    }
}


function isNotNullTrim(idName) {
    var value = $('#' + idName).val();
    if (value != null && value != undefined && value != 'undefined' && $.trim(value) != "") {
        return value;
    }
    return false;
}


