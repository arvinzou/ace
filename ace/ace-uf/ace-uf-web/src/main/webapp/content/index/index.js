	
jQuery(function($) {
			initNoticeTopList1();
			initNoticeTopList2();
			launchExample();
			
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title: function(title) {
					var $title = this.options.title || '&nbsp;'
					if( ("title_html" in this.options) && this.options.title_html == true )
						title.html($title);
					else title.text($title);
				}
			}));
			
		});


function initNoticeTopList1() {
			
			$
					.ajax({
						type : "post",
						url : portalPath+"/notice/findListTop.do?category=1",
						data : {},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {

							if (rst) {
								var html = new Array();
								var k=0;
								for ( var i in rst.value) {
									var o=rst.value[i];
									o.url=portalPath+"/dynamic/portal/notice/preview.jsp?noticeId="+o.noticeId+"&taskId=0"
									k++;

									html.push('<tr>');
									html.push('<td>');
									html.push('<span">■</span> ');
									
									html.push('</td>');
									html.push('<td align="left">【');
									html.push(o.departmentName);
									
									html.push('】');
									
									
									html.push('<a href="javascript:parent.addPanel(\''+o.category+'\',\''+o.url+'\',true)">');
									
									html.push(o.title);
									
									
									html.push('</a>');
									
									if(o.top=='1'){
										html.push('  <span class="badge badge-yellow">置顶</span> ');
								
									}
									html.push('</td>');
								
									
									
									html.push('<td width="150px" align="right">');
									html.push(o.publishTime);
									html.push('</td>');
									
									
									html.push('</tr>');
									
									//console.log(rst.list[i]);
								}
								$('#notice-list-grid').html(html.join(''));
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {

						}
					});
		}
function initNoticeTopList2() {
	
	$
			.ajax({
				type : "post",
				url : portalPath+"/notice/findListTop.do?category=2",
				data : {},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {

					if (rst) {
						var html = new Array();
						var k=0;
						for ( var i in rst.value) {
							var o=rst.value[i];
							o.url=portalPath+"/dynamic/portal/notice/preview.jsp?noticeId="+o.noticeId+"&taskId=0"
							k++;

							html.push('<tr>');
							html.push('<td>');
							html.push('<span">■</span> ');
							
							html.push('</td>');
							html.push('<td align="left">【');
							html.push(o.departmentName);
							
							html.push('】');
							
							html.push('<a href="javascript:parent.addPanel(\''+o.category+'\',\''+o.url+'\',true)">');
							
							html.push(o.title);
							
							
							html.push('</a>');
							
							if(o.top=='1'){
								html.push('  <span class="badge badge-yellow">置顶</span> ');
						
							}
							html.push('</td>');
						
							
							
							html.push('<td width="150px" align="right">');
							html.push(o.publishTime);
							html.push('</td>');
							
							
							html.push('</tr>');
							
							//console.log(rst.list[i]);
						}
						$('#notice-list-grid-area').html(html.join(''));
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
}
