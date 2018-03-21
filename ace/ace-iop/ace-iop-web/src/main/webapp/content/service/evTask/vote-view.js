var gd={};
var evTempleteId;
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
	loadView(evTaskId);
	$('#btn-evTask-submit').on('click',function() {
		$('#fm-evTask').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
					
				});
				$.extend(params, {
					time : new Date()
				});
				if(params.evContent==''){
					params.evContent=CKEDITOR.instances.content.getData();
				}
				var url=contextPath +"/evTaskData/insertEvTaskData.do"
				var btn=$('#btn-evTask-submit');
				
				console.log(params);
				if(params['grade']==null){
					alert("请完成身份选择，谢谢。");
					return false;
				}
				$.each(params,function(k,v){
					if(k.substring(0,1)=='N'){
						if(params[k.replace('N','M')]==null){
							alert("请完成第"+v+"个项目的选择，谢谢。");
							return false;
						}
					}
				});
				var plist=new Array();
				$.each(params,function(k,v){
					if(k.substring(0,1)=='M'){
						var po=new Object();
						po.evTarget=k.replace('M','');
						po.evScoreId=v;
						plist.push(po);
					}
					
				});
				console.log(plist);
				//return false;
				var data={};
				var list=new Array();
				data.grade=params['grade'];
				data.evTaskId=params['evTaskId'];
				data.advise=params['advise'];
				$.each($(plist),function(i,obj){
					var evTaskData=new Object();
					evTaskData.evTaskId=evTaskId;
					evTaskData.evTarget=obj.evTarget;
					evTaskData.evScoreId=obj.evScoreId;
					list.push(evTaskData);
				});
				data.list=list;
				console.log(data);
				$.ajax({
					type : "post",
					url : contextPath +"/evTaskData/insertEvTaskData.do",
					data : {
						jsons :JSON.stringify(data)
					},
					beforeSend : function(XMLHttpRequest) {
						$(btn).attr('disabled',"true");
					},
					success : function(rst, textStatus) {
						$(btn).removeAttr("disabled");
						if (rst) {
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
					
						}
					},
					error : function() {
						$(btn).removeAttr("disabled");
					}
				});
				return false;
			}
		});	

	});
	
});
function loadView(id) {
	$.ajax({
		type : "get",
		url : contextPath + "/evTask/selectEvTaskByPrimaryKey.do",
		data : {
			id : id
		},
		beforeSend : function(XMLHttpRequest) {

		},
		success : function(rst, textStatus) {
			var html1 = new Array();
			var html2 = new Array();
			gd = rst.response;
			evTempleteId=rst.response.evTempleteId;
			if (rst && rst.state) {
				if(rst.response.status=='1'){
					$('#jk').html("<a href='javascript:viewTaskUser()'>监控投票</a>");
				}else{
					var a=(rst.response.allUsers-rst.response.voteUsers);
					var b=parseInt(((rst.response.allUsers-rst.response.voteUsers)/rst.response.allUsers)*100);
					$('#jk').html("测评事务结束，本次测评共分发账号："+rst.response.allUsers+"个，实际参与"+a+"人，参评率 "+b+"%。");
				}
				$.each(rst.response, function(key, value) {
					if(key=='status'){
						rst.response.status=rsd(value, "STATIC_DATA_54");
					}
					if(key=='evTempleteId'){
						rst.response.evTempleteId=rsd(value, "STATIC_DATA_53");
					}
					if(key=='category'){
						rst.response.category=rsd(value, "STATIC_DATA_20");
					}
				});
				$.each(rst.response, function(key, value) {
					$('#' + key).html(value);
				});
				
				
				
				$.each(rst.list, function(i, o) {

				});
			} else {
				alert(rst.errorMessage);
			}
		},
		complete : function(XMLHttpRequest, textStatus) {

		},
		error : function(XMLHttpRequest, textStatus) {
			alert(textStatus);
		}
	})
}
function setOpeter(ra){
	//alert($(ra).val());
}
