function App(){


}
Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

 window.onload=function(){
        var data={reportId:"portal"};
        var tplId="tpl-portal";
        $.ajax({
    		type : "post",
    		url : contextPath + '/anslysis/query.do',
    		data:data,
    		success : function(rst) {
    		    var data={};
    			$.each(rst.value, function(i,o) {
    				data[o.id]=o.value;
    			});
    			console.log(data);
    			render($(".page-content-inner"), data, tplId)
    		}
    	});
    	initNoticeTopList1();
        initNoticeTopList2();
    }




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
								$(rst.value).each(function(i,o){
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
								});
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
						$(rst.value).each(function(i,o){
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
                        								});
						$('#notice-list-grid-area').html(html.join(''));
					}
				},
				complete : function(XMLHttpRequest, textStatus) {

				},
				error : function() {

				}
			});
}