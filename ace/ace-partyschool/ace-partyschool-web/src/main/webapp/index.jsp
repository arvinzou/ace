<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>${cfg.sys_name}</title>

</head>


<jsp:include page="/dynamic/common/header.jsp"/>


<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<div class="page-content-inner">

</div>

<div class="row">
    <div class="col-xs-12 col-sm-6">
        <!-- #section:custom/widget-box -->
        <div class="widget-box transparent background-white padding1">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">
                    <i class="ace-icon glyphicon glyphicon-th-large green"></i>系统公告
                </h4>

                <div class="widget-toolbar no-border">

                </div>
            </div>

            <div class="widget-body" style="min-height:250px">
                <div class="widget-main padding-4">
                    <table width="100%">


                        <tbody id="notice-list-grid">

                        </tbody>
                    </table>
                </div>
                <!-- /.widget-main -->
            </div>
            <!-- /.widget-body -->
        </div>
        <!-- /.widget-box -->

        <!-- /section:custom/widget-box -->
    </div>

    <div class="col-xs-12 col-sm-6">
        <!-- #section:custom/widget-box -->
        <div class="widget-box transparent background-white padding1">
            <div class="widget-header">
                <h4 class="widget-title lighter smaller">
                    <i class="ace-icon glyphicon glyphicon-th-large green"></i>业务公告
                </h4>

                <div class="widget-toolbar no-border">

                </div>
            </div>

            <div class="widget-body" style="min-height:250px">
                <div class="widget-main padding-4">
                    <table width="100%;">


                        <tbody id="notice-list-grid-area">

                        </tbody>
                    </table>
                </div>
                <!-- /.widget-main -->
            </div>
            <!-- /.widget-body -->
        </div>
        <!-- /.widget-box -->

        <!-- /section:custom/widget-box -->
    </div>
    <!-- /.span -->
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

<script id="tpl-portal-1" type="text/template">

    <div class="row widget-row">
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">讲师</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-graduation-cap"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.teacher}">\${data.teacher}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">学员</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green fa fa-group"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.student}">\${data.student}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">评测</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red  fa fa-line-chart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计次数</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup"
                              data-value="\${data.eva}">\${data.eva}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>
        <div class="col-md-3">
            <!-- BEGIN WIDGET THUMB -->
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">通知</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-male"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.notice}">\${data.notice}</span>
                    </div>
                </div>
            </div>
            <!-- END WIDGET THUMB -->
        </div>

    </div>

    </div>


</script>


<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script>

    function App() {


    }

    Array.prototype.contains = function (needle) {
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

    window.onload = function () {
        var data = {reportId: "portal", userId: userProp.userId};
        var tplId = "tpl-portal-1";

        $.ajax({
            type: "post",
            url: contextPath + '/anslysis/query',
            data: data,
            success: function (rst) {
                var data = {};
                $.each(rst.value, function (i, o) {
                    data[o.id] = o.VALUE;
                });

                render($(".page-content-inner"), data, tplId)
            }
        });
        initNoticeTopList1();
        initNoticeTopList2();
    }

function initNoticeTopList1() {

			$.ajax({
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
									html.push('<td align="left">');




									html.push('<a href="'+o.url+'" target="_blank">');

									html.push(o.title);


									html.push('</a>');

									if(o.top=='1'){
										html.push('  <span class="badge badge-yellow">置顶</span> ');

									}
									html.push('</td>');
									html.push('<td width="150px" align="right">');
									html.push(o.createTime);
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

	$.ajax({
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
                        									html.push('<td align="left">');




                        									html.push('<a href="'+o.url+'" target="_blank">');

                        									html.push(o.title);


                        									html.push('</a>');

                        									if(o.top=='1'){
                        										html.push('  <span class="badge badge-yellow">置顶</span> ');

                        									}
                        									html.push('</td>');
                        									html.push('<td width="150px" align="right">');
                        									html.push(o.createTime);
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
</script>


</body>
</html>