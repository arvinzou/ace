jQuery(function($) {
	$( "#tab-group-grade" ).on('click', function(e) {
		$('#tree-grade').tree(
				{
					checkbox : false,
					url : contextPath+'/group/selectGroupGradeTreeByPid.do?pid=0',
					onBeforeExpand : function(node, param) {
						$('#tree-grade').tree('options').url = contextPath+'/group/selectGroupGradeTreeByPid.do?pid='
								+ node.id;
					},
					onClick : function(node) {

					}
				});
	});
	
});