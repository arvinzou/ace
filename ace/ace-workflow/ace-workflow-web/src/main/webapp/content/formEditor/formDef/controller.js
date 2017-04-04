var activiFiledId="";
jQuery(function($) {
	loadFormFiled();
	
	$('#btn-view-edit').on('click', function(e) {
		edit();
	});
	$('#btn-view-del').on('click', function(e) {
		if(!activiFiledId){
			alert("请先选择一个表单组件！");
		}
		deleteActFormFieldByActFormFieldId($("#"+activiFiledId).find("field name").html(),activiFiledId);
		
	});
	$('#btn-view-save').on('click', function(e) {
		eachTable();
	});
	$('#btn-view-preview').on('click', function(e) {
		
		parent.addPanel('表单预览', contextPath
				+ '/dynamic/service/workflow/form-view-start.jsp?formId='
				+ formId , true);
	});
	
});

function uploadFile(field){
	reset_uploader(field);
	var dialog = $( "#dialog-message" ).removeClass('hide').dialog({
		modal: true,
		width:600,
		title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>文件上传</h4></div>",
		title_html: true,
		buttons: [ 
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 关闭",
				"class" : "btn btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
				} 
			}
		]
	});
}

function downloadFile(field){
	window.open(fastdfs_server+$("input[name="+field+"]").val());
}
function loadFormFiled() {
	$.ajax({
		type : "get",
		url : contextPath + "/actFormFieldType/findActFormFieldTypeList.do",
		data : {},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				var html = [];
				html.push('<tr>');
				$.each(rst.rows, function(n, o) {
					
					html.push('<td>');
					html.push('<div class="item" id="">');
					html.push('<view>');
					html.push(o.name);
					html.push('</view>');
					html.push('<field class="hide">');
					html.push('<name>');
					html.push(o.id);
					html.push('</name>');
					html.push('<cfg>');
					html.push('</cfg>');
					html.push('</field>');
					html.push('</div>');
					html.push('</td>');
					
				});
				html.push('</tr>');
				// alert(html.join(''));
				$('.left table tbody').html(html.join(''));
				selectDefFormByFormId();
				
			} else {
				alert(rst.detail);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {
		}
	});
}
function selectDefFormByFormId() {
	$.ajax({
		type : "get",
		url : contextPath + "/actForm/selectDefFormByFormId.do",
		data : {formId:formId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				console.log(rst);
				$(rst.value).each(function(i,o){
					var html=[];
					html.push('<div class="item assigned" id="'+o.id+'">');
					html.push('<view>');
					html.push(o.label);
					html.push('</view>');
					html.push('<field class="hide">');
					html.push('<name>');
					html.push(o.fieldType);
					html.push('</name>');
					html.push('<cfg>');
					html.push(JSON.stringify(o.cfg));
					html.push('</cfg>');
					html.push('</field>');
					html.push('</div>');
					$(".right table tr:gt(0):eq("+(o.row-1)+") td:eq("+o.colum+")").html(html.join(''));
				});
				initDesign();
			} else {
				alert(rst.detail);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function() {
		}
	});
}
function edit(){
	var filedType= $("#"+activiFiledId).find("field name").html();
	var title=filedType;
	if(!filedType){
		alert("请先选择一个表单组件！");
	}
	var dialog = $( "#dialog-message-"+filedType).removeClass('hide').dialog({
		modal: true,
		width:600,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+title+"</div></div>",
		title_html: true,
		buttons: [ 
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
				"class" : "btn btn-info btn-xs",
				click: function() {
					$('#fm-'+filedType).submit();
					$( this ).dialog( "close" );
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
				} 
			}
		]
	});
	var jsonstr=$("#"+activiFiledId).find("field cfg").html();
	if(jsonstr){
		var fmdata=JSON.parse(jsonstr);
		if(fmdata.hasOwnProperty("fileTypes")){
			fmdata.fileTypes=fmdata.fileTypes.split(",");
		}
		$('#fm-'+filedType).form('load',fmdata);
		$("#"+activiFiledId).find("view").html(fmdata.label);
	}
	$('#fm-'+filedType).ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			var params={};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			if(params.hasOwnProperty("fileTypes")){
				var array=$("#fileTypes").combobox("getValues");
				params.fileTypes=array.join(",");
			}
			console.log(params);
			$("#"+activiFiledId).find("field cfg").html(JSON.stringify(params));
			$("#"+activiFiledId).find("view").html(params.label);
			return false;
		}
	});
}
function eachTable() {
	var list = [];
	var params={};
	var order=1;
	$(".right table").find("tr").each(function(i) {
		var dom = $(this).children();
		for(var e=1;e<=4;e++){
			var data={};
			data.id=dom.eq(e).find('div').attr("id");
			data.fieldType=dom.eq(e).find("field name").html();
			data.row=i;
			data.colum=e;
			var jsonstr=dom.eq(e).find("field cfg").html();
			if(jsonstr){
				data.cfg=JSON.parse(jsonstr);
			}
			if(data.id){
				data.order=order;
				order=order+1;
				list.push(data);
			}
		}
		
	});
	params.formId=formId;
	params.list=list;
	saveOrUpdateActForm(params);
}
function saveOrUpdateActForm(params){
	console.log(params);
	$.ajax({
		type : "post",
		url : contextPath + "/actForm/saveOrUpdateActForm.do",
		data:{jsons:JSON.stringify(params)},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-view-save').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-view-save').removeAttr("disabled");
			bootbox.dialog({
				title:'系统提示',
				message:rst.errorMessage,
				detail:rst.detail,
				buttons: 			
				{
					"success" :
					 {
						"label" : "<i class='ace-icon fa fa-check'></i>确定",
						"className" : "btn-sm btn-success",
						"callback": function() {
							
						}
					}
				}
			});
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-view-save').removeAttr("disabled");
		},
		error : function() {
			$('#btn-view-save').removeAttr("disabled");
		}
	});
}
function deleteActFormFieldByActFormFieldId(fieldType,id){
	$.ajax({
		type : "post",
		url : contextPath + "/actForm/deleteActFormFieldByActFormFieldId.do",
		data:{fieldType:fieldType,id:id},
		beforeSend : function(XMLHttpRequest) {
			$('#btn-view-del').attr('disabled',"true");
		},
		success : function(rst, textStatus) {
			$('#btn-view-del').removeAttr("disabled");
			$("#"+activiFiledId).remove();
			activiFiledId=null;
		},
		complete : function(XMLHttpRequest, textStatus) {
			$('#btn-view-del').removeAttr("disabled");
		},
		error : function() {
			$('#btn-view-del').removeAttr("disabled");
		}
	});
}