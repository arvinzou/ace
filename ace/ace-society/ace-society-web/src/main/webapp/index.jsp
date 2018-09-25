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


<jsp:include page="/dynamic/common/header.jsp" />


<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="page-content-inner">

</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<script id="tpl-portal-1" type="text/template">


    <div class="row widget-row">
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">会员个人</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-user"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.person}">\${data.person}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">会员组织</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red   fa fa-users"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.org}">\${data.org}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">线下活动</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-heart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.activityAll}">\${data.activityAll}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">线下活动</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green  fa fa-heart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">待审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.activityAudit}">\${data.activityAudit}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="row widget-row">
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">文明随手拍</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green fa fa-camera"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.behavior}">\${data.behavior}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">我有点子</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red fa fa-envelope"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.subjectIdea}">\${data.subjectIdea}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">秀我直播</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-microphone"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.live}">\${data.live}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">朋友圈</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue  fa fa-photo"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.circle}">\${data.circle}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>





</script>



<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script>

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
        var data={reportId:"portal",userId:userProp.userId};
        var tplId="tpl-portal-1";

        $.ajax({
    		type : "post",
    		url : contextPath + '/anslysis/query',
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
    }
</script>
</body>
</html>