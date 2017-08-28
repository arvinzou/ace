jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	/*$(".stamper span.t1").stamper({
		image : portalPath+"/content/portal/images/stamper.png",
		scale : 3,
		speed : 300,
		complete : function() {
		
		}
	});*/
	loadMemberInfo(deptId,memberCode);
	loadQualifications(deptId,memberCode);
	/*$("#btn-view-applyMember").on('click', function(e) {
		e.preventDefault();
		insertMemberInfo(deptId)
	});*/
	

});
function applyMember(memberCode){
	if(confirm("确定要申请为会员吗？")){
		insertMemberInfo(memberCode) ;
	}
}

function insertMemberInfo(memberCode) {
	var params = {
		memberCode : memberCode
	};
	$.ajax({
		type : "post",
		url : "/kernel/memberInfo/insertMemberInfo.do",
		data : {
			jsons : JSON.stringify(params)
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst) {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"cancel" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {

							}
						}
					}
				});

			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
		}
	});
}
function getPayInfo(memberCode, payLevel) {
	$.ajax({
		type : "post",
		url : contextPath + "/payCfg/getPayInfo.do",
		data : {
			memberCode : memberCode,
			payLevel : payLevel
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			$('#level').html("会员级别："+rst.value.name);

		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {

		}
	});
}
function memberBaseInfo(){
	parent.addPanel('资质认证',contextPath+'/dynamic/service/memberBaseInfo/index.jsp',true);
}
