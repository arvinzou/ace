jQuery(function($) {
	$('#tree-dept').tree(
					{
						checkbox : false,
						url : contextPath+'/group/selectGroupDepTreeByPid.do?pid=0',
						onBeforeExpand : function(node, param) {
							$('#tree-dept').tree('options').url = contextPath+'/group/selectGroupDepTreeByPid.do?pid='
									+ node.id;
						},
						onClick : function(node) {

						}
					});
});