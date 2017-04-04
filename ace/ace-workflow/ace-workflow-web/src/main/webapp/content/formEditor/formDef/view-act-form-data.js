jQuery(function($) {
	if(processInstanceId){
		selectListByInstId(processInstanceId);
	}
});

function selectListByInstId(instId) {
	$.ajax({
		type : "get",
		url : contextPath + "/actForm/selectListByInstId.do",
		data : {instId:instId},
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
					html.push("<div class=\"profile-info-row\" id='drow-"+(i+1)+"'>");
					var htmlColum=[];
					for(var e=0;e<maxColum;e++){
						htmlColum.push("<div class=\"profile-info-name\" id='dlr-"+(i+1)+"c-"+(e+1)+"'>");
						htmlColum.push("</div>");
						htmlColum.push("<div class=\"profile-info-value\" id='dvr-"+(i+1)+"c-"+(e+1)+"'>");
						htmlColum.push("</div>");
					}
					html.push(htmlColum.join(""));
				
					html.push("</div>");
				}
				$("#fm-preview-data").find("div").html(html.join(""));
				var tableDom=[];
				var index=0;
				$(rst.value).each(function(i,o){
					if(o.fieldType=="grouptitle"){
						$("#drow-"+o.row).html("<div class=\"profile-group-title\">"+o.label+"</div>");
					}else if(o.fieldType=="filesbox"){
						$("#fm-preview-data").find("table").removeClass("hide");
						 renderFilexboxData(o,tableDom,index);
						 index=index+1;
						
					}else{
						
						$("#dlr-"+o.row+"c-"+o.colum).append(o.label);
						
						var view=o.value;
						if(o.fieldType=="combobox"){
							view=rsd(o.value,o.dictId);
						}
						if(o.fieldType=="datebox"){
							
						}
						$("#dvr-"+o.row+"c-"+o.colum).html(view);
					}
					
				});
				$("#fm-preview-data").find("table").find('tbody').html(tableDom.join(""));
				
				
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

function renderFilexboxData(o,html,index){
	html.push('<tr>');
	html.push('<td  width="30px" class="center"><span class="badge badge-success">'+ (index + 1) + '</span></td>');
	html.push('<td width="150px">' + o.label + '</td>');

	html.push('<td width="30px">');
	html.push('<a href="'+fastdfs_server+o.value + '" target="_blank" "><span class="badge badge-info">查看</span></a>');
	html.push('</td></tr>');
}
