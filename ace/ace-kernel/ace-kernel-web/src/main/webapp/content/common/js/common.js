/*add tab*/
function addPanel(title, src, closable) {
	var screenHeight = window.innerHeight - 76;
	var iframe = '<iframe name="mainFrame" id="ifr" src="' + src
			+ '" width="100%" height="' + screenHeight
			+ 'px" frameborder="0"  scrolling="auto"></iframe>';
	var isExit = $('#tt').tabs('exists',title);
	if(!isExit){
		$('#tt').tabs('add', {
			title : title,
			content : iframe,
			closable : closable
		});
	}else{
		$('#tt').tabs('select',title);
	}
	
}

function alert(title,msg){
	bootbox.dialog({
		title : title,
		message : msg,
		buttons : 			
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