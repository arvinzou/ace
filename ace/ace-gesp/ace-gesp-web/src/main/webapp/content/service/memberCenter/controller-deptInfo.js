
function loadMemberInfo(deptId,memberRemark) {
	$.ajax({
		type : "post",
		url : contextPath + "/memberInfo/loadMemberBaseInfo.do",
		data : {
			id : deptId,parentDepartmentId:memberRemark
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			loadMemberContact(deptId);
			var memberLevelName="行业用户";
			$('#level').html(rst.value.departmentName);
			$.each(rst.value, function(key, value) {
				if (key == 'legalPersonIdType') {
					value = rsd(value, '70');
				}
				if (key == 'nature') {
					value = rsd(value, '03');
				}
				if (key == 'transBussScope') {
					var transBussScope = value.split(",");
					if(transBussScope!=undefined){
						if(transBussScope.length>1){
							value ="";
							for (var i = 0; i < transBussScope.length; i++) {
								if(i!=0){
									value = value+"、";
								}
								value = value+rsd(transBussScope[i], '81');
							}
						}else{
							value = rsd(value, '81');
						}
					}
				}
				if (key == 'simage'&&value) {
					$('#avatar').attr('src',(fastdfs_server+value)); 
					value = '<a href="'+(fastdfs_server+value)+'" target="_blank"><i class="fa fa-file-image-o" aria-hidden="true"></i></a>';
				}
				if (key == 'category') {
					
					if (value == '3') {
						if (rst.value.endDate) {
							value = '会员';
							//value = '<span class="badge badge-success">会员</span> 至 <span class="badge badge-danger">'+rst.value.endDate.substring(0,10)+"</span>";
							memberLevelName = rst.value.memberLevel;
							$('#edit_memberNos').val(rst.value.memberNo);
							$('#edit_status').val('20');
							$('#edit_id').val(rst.value.memberId);
							$('#edit_meberLevels').combobox('setValue',rst.value.memberLevelName);
							//getPayInfo(deptId, '');
						} else {
							value = '企业';
						}
					} else {
						value = rsd(value, '66');
					}
				}
				if (key == 'type') {
					value = rsd(value, '04');
				}
				if(key=="regDate"||key=="transBussLicenseValidDate"){
					value = value.substring(0, 10);
				}
				$('#' + key).html(value);
			});
			$('#look-info').html("");
			var len = memberRemark.length;
			if(len==8){
				$('#look-info').append('<div  align="center"><span style="margin-right:10px;"><i class="ace-icon fa fa-graduation-cap bigger-120 pink"></i> 资质未认证</span>\
						<span><i class="ace-icon fa  fa-user-circle bigger-120 green"></i> '+memberLevelName+' </span></div>');
			}else{
				$('#look-info').append('<div  align="center"><a href="javascript:memberBaseInfo()" class="btn btn-link"> \
						<i class="ace-icon fa fa-graduation-cap bigger-120 pink"></i> 资质认证\
						</a><a href="javascript:applyMember('+memberCode+')" class="btn btn-link">\
						<i class="ace-icon fa fa-plus-circle bigger-120 green"></i> 申请为会员 </a></div>');
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}

function loadQualifications(memberCode,pdeptId) {
	$.ajax({
		type : "get",
		url : contextPath + "/qualifications/selectQualificationsList.do",
		data : {
			deptId:memberCode,pdeptId:pdeptId,flag:""
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				butiQualificationsTable(rst.value)
			} else {
				bootbox.dialog({
					title : '系统提示',
					message : rst.errorMessage,
					detail : rst.detail,
					buttons : {
						"success" : {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback" : function() {
								$(this).dialog("close");
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
var dttable;
function butiQualificationsTable(data) {
	if ($('#table4').hasClass('dataTable')) {
		dttable = $('#table4').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	var html = new Array();
	$(data).each(function(index, o) {
						html.push('<tr>');
						html.push('<td  width="40px" class="center"><span class="badge badge-success">'+ (index + 1) + '</span></td>');
						html.push('<td width="150px">' + o.name + '</td>');
						html.push('<td width="200px">');
						if(o.fileSrc){
							html.push('<a target="_blank" href="');
							html.push((fastdfs_server+(o.fileSrc==null?'':o.fileSrc)));
							html.push('"');
							html.push('><span class="badge badge-info">查看</span></a>');
							if(o.status=='4'){
								html.push('<span class="badge badge-success">已认证</span>');
							}else if(o.status=='3'){
								html.push('<span class="badge badge-danger">认证失败【');
								html.push(o.auditDiscribtion==null?'':o.auditDiscribtion);
								html.push('】</span>');
							}
						}else{
							html.push('<span class="badge badge-danger">待上传</span>');
						}
						html.push('</td>');
						html.push('</tr>');
					});
	$('#table4').find('tbody').empty();
	$('#table4').find('tbody').append(html.join(''));
	
}

/*加载联系人信息*/
function loadMemberContact(depatId){
	if ($('#tableContactDe').hasClass('dataTable')) {
		dttable = $('#tableContactDe').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	$.ajax({
		type : "post",
		url : portalPath + "/users/findDeIdByUsersList.do",
		data : {
			departmentId : depatId
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			var html = new Array();
			$(rst.rows).each(function(index, o) {
				var tel = o.telphone;
				if(!tel){
					tel = "";
				}
				var qq = o.qq;
				if(!qq){
					qq = "";
				}
				html.push('<tr>');
				html.push('<td  width="50px" class="center">'+ o.name + '</td>');
				html.push('<td width="50px">' + rsd(o.userLevel, "05") + '</td>');
				html.push('<td width="50px">' + o.mobile + '</td>');
				html.push('<td width="50px">' + o.email + '</td>');
				html.push('<td width="50px">' + tel + '</td>');
				html.push('<td width="50px">' + qq);
				/*html.push('<td width="100px">');
				if(o.userLevel){
					html.push('  <a href="javascript:updateContact("'+o+'");" target="_blank"><span class="badge badge-info">修改</span></a>');
				}*/
				html.push('</td></tr>');
			});
			$('#tableContactDe').find('tbody').empty();
			$('#tableContactDe').find('tbody').append(html.join(''));
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			alert("加载错误！");
		}
	});
}
