jQuery(function($) {
	$( "#tab-group-free" ).on('click', function(e) {
		$('#tree-free').tree(
				{
					checkbox : false,
					url : contextPath+'/group/selectFreeGroupTreeRoot.do',
					onBeforeExpand : function(node, param) {
						$('#tree-free').tree('options').url = contextPath+'/group/selectFreeGroupTreeByPid.do?pid='
								+ node.id;
					},
					onClick : function(node) {

					}
				});
	});
	
});