jQuery(function($) {
	loadDepartInfos();
	$('#regPro').combotree({    
		url:portalPath +'/system/selectProvinceTreeList.do?id=00&&level=5',
		method:'get',
		animate: true,
		lines:false,
		required:true,
		onSelect:function(node){
			$('#regCity').combotree('setValue', '');
			reload('4',node.id,"#regCity");
		}
	});  

	$('#regCity').combotree({    
		url:"",
		method:'get',
		animate: true,
		lines:false,
		required:true,
		onSelect:function(node){
			$('#regAreaCode').combotree('setValue', '');
			reload('3',node.id,"#regAreaCode");
		}
	});  

	$('#regAreaCode').combotree({    
		url:"",
		method:'get',
		animate: true,
		lines:false,
		required:true
	});  	
	$('#bussPro').combotree({    
		url:portalPath +'/system/selectProvinceTreeList.do?id=00&&level=5',
		method:'get',
		animate: true,
		lines:false,
		required:true,
		onSelect:function(node){
			$('#bussCity').combotree('setValue', '');
			reload('4',node.id,"#bussCity");
		}
	});   
	
	$('#bussCity').combotree({    
		url:"",
		method:'get',
		animate: true,
		lines:false,
		required:true,
		onSelect:function(node){
			$('#bussAreaCode').combotree('setValue', '');
			reload('3',node.id,"#bussAreaCode");
		}
	});  

	$('#bussAreaCode').combotree({    
		url:"",
		method:'get',
		animate: true,
		lines:false,
		required:true
	}); 
	
	$('#mainPost').combobox({
		//url:portalPath+'/dict/findListByCategoryId.do?categoryId=05&selected=false',
        method:'get',
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:200
	});

	$('#contactPost').combobox({
		//url:portalPath+'/dict/findListByCategoryId.do?categoryId=05&selected=false',
        method:'get',
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:200
	});
	
	
	loadCategory();
	
});

function reload(level,node,id){
	$.ajax({
		type : "post",
		url : portalPath + "/system/selectProvinceTreeList.do",
		data:{level:level,id:node},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			if(rst){
				$(id).combotree('loadData', rst);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}

function loadDepartInfos(){
	$.ajax({
		type : "post",
		url : contextPath + "/www/reg/selectInfoByPrimary.do",
		data:{},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			var r = rst.value;
			$('#update_company').form("load", r);
			reload('4',r.regPro,"#regCity");
			$('#regCity').combotree('setValue', r.regCity);
			reload('3',r.regCity,"#regAreaCode");
			$('#regAreaCode').combotree('setValue', r.regAreaCode);
			reload('4',r.bussPro,"#bussCity");
			$('#bussCity').combotree('setValue', r.bussCity);
			reload('3',r.bussCity,"#bussAreaCode");
			$('#bussAreaCode').combotree('setValue', r.bussAreaCode);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}

function loadCategory(){
	$.ajax({
		type : "post",
		url:portalPath+'/dict/findListByCategoryId.do?categoryId=05&',
		data:{},
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(rst, textStatus) {
			var name = [];
			var a =0;
			if (rst) {
				for (var i = 0; i < rst.length; i++) {
					if (rst[i].code!="2") {
						name[a] = rst[i];
						a++;
					}
				}
			}
			$('#contactPost').combobox('loadData',name);
			$('#mainPost').combobox('loadData',name);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
			
		}
	});
}