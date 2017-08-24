jQuery(function($) {
	$( "#tab-group-discribline" ).on('click', function(e) {
		$('#tree-discribline').tree(
				{
					checkbox : false,
					url : contextPath+'/group/selectGroupDiscriblineTreeByPid.do?pid=0',
					onBeforeExpand : function(node, param) {
						$('#tree-discribline').tree('options').url = contextPath+'/group/selectGroupDiscriblineTreeByPid.do?pid='
								+ node.id;
					},
					onClick : function(node) {

					}
				});
	});
	
});