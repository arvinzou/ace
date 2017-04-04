jQuery(function($) {
	loadDefFormByFormId(formId);
	$("#fm-preview").ajaxForm({
		beforeSubmit : function(formData, jqForm, options) {
			var params={};
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			if($("#fm-preview").form('validate')){
				 actSubmitForm(params);
			}
			
			return false;
		}
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

function preview(width){
	var dialog = $( "#dialog-message-preview").removeClass('hide').dialog({
		modal: true,
		width:width,
		title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >"+title+"</div></div>",
		title_html: true,
		buttons: [ 
			{
				html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
				"class" : "btn btn-info btn-xs",
				id:"btn-view-act-form-submit",
				click: function() {
					$("#fm-preview").submit();
				} 
			},
			{
				html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
				"class" : "btn btn-xs",
				click: function() {
					parent.removePanel(); 
				} 
			}
		]
	});
}
function loadDefFormByFormId(formId) {
	$.ajax({
		type : "get",
		url : contextPath + "/actForm/selectDefFormByFormId.do",
		data : {formId:formId},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			if (rst && rst.state) {
				//console.log(rst);
				var html=[];
				var maxRow=1,maxColum=1;
				var contentFilesbox=false;
				$(rst.value).each(function(i,o){
					if(o.fieldType!="filesbox"){
						if(o.row>maxRow){
							maxRow=o.row;
						}
						if(o.colum>maxColum){
							maxColum=o.colum;
						}
					}else{
						contentFilesbox=true;
					}
				});
				
				for(var i=0;i<maxRow;i++){
					html.push("<div class=\"profile-info-row\" id='row-"+(i+1)+"'>");
					var htmlColum=[];
					for(var e=0;e<maxColum;e++){
						htmlColum.push("<div class=\"profile-info-name\" id='lr-"+(i+1)+"c-"+(e+1)+"'>");
						htmlColum.push("</div>");
						htmlColum.push("<div class=\"profile-info-value\" id='vr-"+(i+1)+"c-"+(e+1)+"'>");
						htmlColum.push("</div>");
					}
					html.push(htmlColum.join(""));
					html.push("<input  name='processDefinitionId' type='hidden' value=\""+processDefinitionId+"\">");
					html.push("<input  name='taskId' type='hidden' value=\""+taskId+"\">");
					html.push("</div>");
				}
				$("#fm-preview").find("div").html(html.join(""));
				var tableDom=[];
				var index=0;
				$(rst.value).each(function(i,o){
					if(o.fieldType=="grouptitle"){
						$("#row-"+o.row).html("<div class=\"profile-group-title\">"+o.label+"</div>");
					}else if(o.fieldType=="filesbox"){
						$("#fm-preview").find("table").removeClass("hide");
						 renderFilexbox(o,tableDom,index);
						 index=index+1;
						
					}else{
						if(o.cfg.required=="on"){
							$("#lr-"+o.row+"c-"+o.colum).append("<span class=\"red\">∗</span>");
						}
						$("#lr-"+o.row+"c-"+o.colum).append(o.label);
						$("#vr-"+o.row+"c-"+o.colum).html("<input id=\""+o.id+"\" name=\""+o.id+"\">");
						if(o.fieldType=="textbox"){
							renderTextbox(o)
						}
						if(o.fieldType=="combobox"){
							renderCombobox(o)
						}
						if(o.fieldType=="datebox"){
							renderDatebox(o)
						}
					}
					
				});
				$("#fm-preview").find("table").find('tbody').html(tableDom.join(""));
				var width=300*maxColum;
				if(width<600){
					width=600;
				}
				//$(".formPanel").css("width",width);
				 preview(width)
				
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

function renderTextbox(o){
	var properties={};
	var e=o.cfg;
	var field="#"+o.id;
	if(e.placeholder){
		$(field).attr("placeholder",e.placeholder);
	}
	if(e.defaultValue){
		$(field).attr("value",e.defaultValue);
	}
	if(e.required){
		properties.required=true;
	}
	if(e.validatorType){
		if(!e.minlength){
			e.minlength=1;
		}
		if(!e.maxlength){
			e.maxlength=50;
		}
		properties.validType=[e.validatorType,	"length["+e.minlength+","+e.maxlength+"]"]
	}
	if(e.styleWidth){
		$(field).css("width",e.styleWidth);
	}
	if(e.styleHeight){
		$(field).css("height",e.styleHeight);
	}
	if(e.multiline=="true"){
		properties.multiline=true;
	}
	$(field).validatebox(properties);
	//console.log(properties);
}
function renderCombobox(o){
	var properties={};
	var e=o.cfg;
	console.log(e);
	var field="#"+o.id;
	if(e.defaultValue){
		$(field).attr("value",e.defaultValue);
	}
	if(e.required){
		properties.required=true;
	}
	if(e.panelHeight){
		properties.panelHeight=e.panelHeight;
	}
	if(e.multiple=="true"){
		properties.multiple=true;
	}
	if(e.dataUrl){
		properties.url=e.dataUrl;
	}else{
		properties.url=portalPath+"/dict/findListByCategoryId.do?categoryId="+e.dictId;
		if(e.selected=="false"){
			properties.url=properties.url+"&selected="+e.selected
		}
	}
	if(e.valueField){
		properties.valueField=e.valueField;
	}else{
		properties.valueField='code';
	}
	if(e.textField){
		properties.textField=e.textField;
	}else{
		properties.textField='name';
	}
	if(e.styleWidth){
		$(field).css("width",e.styleWidth);
	}
	$(field).combobox(properties);
	
	console.log(properties);
}
function renderDatebox(o){
	var properties={};
	var e=o.cfg;
	console.log(e);
	var field="#"+o.id;
	if(e.defaultValue){
		$(field).attr("value",e.defaultValue);
	}
	if(e.required){
		properties.required=true;
	}
	if(e.styleWidth){
		$(field).css("width",e.styleWidth);
	}
	if(e.type=="date"){
		$(field).datebox(properties);
	}else{
		$(field).datetimebox(properties);
	}
	
	
	console.log(properties);
}
function renderFilexbox(o,html,index){
	html.push('<tr>');
	html.push('<td  width="30px" class="center"><span class="badge badge-success">'+ (index + 1) + '</span></td>');
	html.push('<td width="150px">' + o.label + '</td>');
	html.push('<td width="80px">' + o.cfg.discribtion + '</td>');
	html.push('<td width="30px">');
	if(o.cfg.demoUrl){
		html.push('  <a href="' +fastdfs_server+ o.cfg.demoUrl + '" target="_blank"><span class="badge badge-info">查看</span></a>');
	}
	html.push('</td>');
	html.push('<td width="50px"><input id="'+o.id+'" type="hidden" name="'+o.id+'"><a href="javascript:uploadFile(\''+o.id+'\')">[选择附件]</a></td>');
	html.push('<td width="30px">');
	html.push('<a href="javascript:viewfile(\''+o.id + '\')" target="_blank" class="hide" id="href-'+o.id+'"><span class="badge badge-info">查看</span></a>');
	html.push('</td></tr>');
}

function viewfile(id){
	window.open(fastdfs_server+$("#"+id).val());
}