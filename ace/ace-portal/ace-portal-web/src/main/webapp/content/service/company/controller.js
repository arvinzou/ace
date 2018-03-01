jQuery(function($) {
    initFormData(userProp.corpId);
});
function initFormData(id) {
	$.ajax({
		type : "post",
		url : contextPath+'/department/selectDepartmentByPrimaryKey.do',
		data : {
			departmentId : id
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
            console.log(rst.value);
            renderForm(rst.value);
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
function update(data) {
	$.ajax({
		type : "post",
		url : contextPath+'/department/updateDepartment.do',
		data :{jsons:JSON.stringify(data)},
		beforeSend : function(XMLHttpRequest) {
            $("#btn-submit").attr('disabled',"true");
		},
		success : function(rst, textStatus) {
           $("#btn-submit").removeAttr('disabled');
           alert(rst.errorMessage);
		},
		error : function() {
			alert("加载错误！");
		}
	});
}