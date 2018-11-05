function loadView(id) {
    $.ajax({
        type: "post",
        url:contextPath + '/fopCompany/selectFopCompanyByPrimaryKey',
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            //动态渲染
            var tpl = document.getElementById('tpl-view-page').innerHTML;//'tpl-company'
            var renderHtml = juicer(tpl, rst.value);
            $('.main_box').html(renderHtml);



            $.each(rst.value, function (key, value) {
                if (key == "thirdLaborRelation") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "否";
                            break;
                        case '1' :
                            rst = "是";
                            break;
                        default :
                            rst = "";
                    }
                    value = rst;
                }
                //企业类型 "0": "企业会员", "4": "个人会员"},//, "1": "团体企业", "2": "律师事务所", "3": "银行机构"
                if (key == "companyType") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "企业会员";
                            break;
                        case '1' :
                            rst = "团体企业";
                            break;
                        case '2' :
                            rst = "律师事务所";
                            break;
                        case '3' :
                            rst = "银行机构";
                            break;
                        case '4' :
                            rst = "个人会员";
                            break;
                        default :
                            rst = "N/A";
                    }
                    value = rst;
                }
                //status
                if (key == "status") {
                    if ("1" == value) {
                        value = "非会员";
                    }
                    if ("2" == value) {
                        value = "会员";
                    }
                }
                //文化程度
                if (key == "lpEducation") {
                    value = rsd(value, "10");
                }
                //民族
                if (key == "lpNationality") {
                    value = rsd(value, "09");
                }
                //性别
                if (key == "lpSex") {
                    value = rsd(value, "01");
                }
                //企业性质
                if (key == "companyProperty") {
                    value = rsd(value, "134");
                }
                //日期格式化
                if (key.indexOf('Dt') != -1 ||
                    key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }

                // console.log("key=" + key + ",value=" + value);
                $(".form_info").find('span[name=' + key + ']').html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
var Common = {

	// EasyUI用DataGrid用日期格式化
	TimeFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		/* json格式时间转js时间格式 */
		value = value.substr(1, value.length - 2);
		var obj = eval('(' + "{Date: new " + value + "}" + ')');
		var dateValue = obj["Date"];
		if (dateValue.getFullYear() < 1900) {
			return "";
		}
		var val = dateValue.format("yyyy-mm-dd HH:MM");
		return val.substr(11, 5);
	},
	DateTimeFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		/* json格式时间转js时间格式 */
		value = value.substr(1, value.length - 2);
		var obj = eval('(' + "{Date: new " + value + "}" + ')');
		var dateValue = obj["Date"];
		if (dateValue.getFullYear() < 1900) {
			return "";
		}

		return dateValue.format("yyyy-mm-dd HH:MM");
	},

	// EasyUI用DataGrid用日期格式化
	DateFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		return value.substr(0, 10);
	}
};

function rsd(value, kernelKey) {
    try {
        var name = value;
        if ((value + "") && ("" + value).indexOf(',') < 0) {
            if (staticDictObject && kernelKey && staticDictObject[kernelKey]) {
                for (var i = 0; i < staticDictObject[kernelKey].length; i++) {
                    if (staticDictObject[kernelKey][i].CODE == value) {
                        name = staticDictObject[kernelKey][i].NAME;
                        break;
                    }
                }
            }
        } else {
            if (value) {
                var nameArray = [];
                var v = (value + "").split(',');
                for (var j = 0; j < v.length; j++) {
                    for (var i = 0; i < staticDictObject[kernelKey].length; i++) {
                        if (staticDictObject[kernelKey][i].CODE == v[j]) {
                            nameArray.push(staticDictObject[kernelKey][i].NAME);
                            break;
                        }
                    }
                }
                name = nameArray.join(',');
            }
        }
    } catch (err) {
        console.info("渲染错误", value + ":" + kernelKey + ":" + err);
    }
    return name;
}