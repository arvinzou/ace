<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>课程详情</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="../common/css/star-rating.css" />
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
			  integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
			  crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>


	<body>
		<div class="container">
			<div class="course_basic">
				<div id="basicInfo">
					<div class="row">
						<div class="col-xs-12 col-md-12">
							<div class="banner" id="banner">

							</div>
						</div>
					</div>
					<div class="row course_title" id="sourceTitle">

					</div>
					<div class="row teacher_name" id="teacherName">

					</div>
				</div>
				<div class="row course_audio" id="courseAudio">

				</div>
			</div>
			<div class="comment_list" id="commentList">

			</div>
		</div>

		<!--评价框模态窗-->
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="container star">
							<form>
								<input value="5" id="rating-input" type="text" title="" />
								<span class="demo" id="kv-caption">推荐</span><br/>
							</form>
						</div>
					</div>
					<textarea class="point" name="content" placeholder="鼓励下老师吧~" maxlength="100"
							  onfocus="this.placeholder=''" onblur="this.placeholder='鼓励下老师吧~(100字以内)'"></textarea><br/>
					<div class="pjbtn">
						<a href="javascript:void(0);" class="commit" onclick="commitComments();">提&nbsp;交</a>
					</div>
				</div>
			</div>
		</div>


	</body>

	<script id="bannerTemp" type="text/template">
		<img src="\${data.cover}" />
	</script>
	<script id="teacherTemp" type="text/template">
		<div class="col-xs-12 col-md-12"><p>\${data.counselor.name}</p></div>
	</script>
	<script id="sourceNameTemp" type="text/template">
		<div class="col-xs-12 col-md-12">
			<h3>\${data.name}</h3>
		</div>
	</script>

	<script id="audioTemp" type="text/template">
		<%--<div class="row">--%>
		<%--<div class="col-xs-6 col-md-6">--%>
		<%--<a href="javascript:void(0);" onclick="showCatalog('\${data.courseId}','\${data.partId}');">--%>
		<%--<p><img src="img/icon_catalog.png" class="icon" style="margin-left: 0.08rem"/></p>--%>
		<%--<p class="audioTitle">目录</p>--%>
		<%--</a>--%>
		<%--</div>--%>
		<%--<div class="col-xs-6 col-md-6" style="text-align: right">--%>
		<%--<a href="javascript:void(0);" onclick="showScripts('\${data.id}');">--%>
		<%--<p><img src="img/icon_script.png" class="icon"/></p>--%>
		<%--<p class="audioTitle">文稿</p>--%>
		<%--</a>--%>
		<%--</div>--%>
		<%--</div>--%>
		<%--<div class="row">--%>
		<%--<div class="col-xs-12 col-md-12">--%>
		<%--<audio src="\${data.mediUrl}" controls="controls" style="width: 100%;" currentTime duration>--%>
		<%--您的设备不支持音频播放--%>
		<%--</audio>--%>
		<%--</div>--%>
		<%--</div>--%>


		<div class="" id="player">
			<div class="p_left">
				<a href="javascript:void(0);" onclick="showCatalog('\${data.courseId}','\${data.partId}');">
					<p><img src="img/icon_catalog.png" class="icon" style="margin-left: 0.08rem"/></p>
					<p class="audioTitle">目录</p>
				</a>
			</div>
			<div class="p_center">


				<div class="audio green-audio-player">
					<div class="loading">
						<div class="spinner"></div>
					</div>
					<div class="play-pause-btn">
						<img src="img/play.png" class="play-pause-icon" id="playPause">
					</div>

					<div class="controls">
						<span class="current-time">0:00</span>
						<div class="slider" data-direction="horizontal">
							<div class="progress" style="width: 0%;">
								<div class="pin" id="progress-pin" data-method="rewind"></div>
							</div>
						</div>
                        <span class="total-time">0:00</span>
					</div>

					<audio crossorigin="" preload="auto">
						<source src="\${data.mediUrl}" type="audio/mpeg">
					</audio>
				</div>


			</div>
			<div class="p_right">
				<a href="javascript:void(0);" onclick="showScripts('\${data.id}');">
					<p><img src="img/icon_script.png" class="icon"/></p>
					<p class="audioTitle">文稿</p>
				</a>
			</div>

		</div>


	</script>

<script id="commentListTemp" type="text/template">
    <div class="row statics">
        <div class="col-xs-6 col-md-6">
        	<span class="evaluate">评价（\${data.length}个评价）</span>
   		 </div>
    	<div class="col-xs-6 col-md-6" style="text-align: right;">
        	<a href="#" data-toggle="modal" data-target="#myModal">
        		<img src="img/icon_comment.png" class="comments_icon"/>
        		<span class="comment">发表评论</span>
        	</a>
        </div>
	</div>
	<div class="row list">
		{@each data as item, index}
        <div class="row list_item">
			<div class="row">
         		<div class="col-xs-2 col-md-2">
        			<img src="\${item.headimgurl}" class="headImg" />
        		</div>
        		<div class="col-xs-6 col-md-6">
        			<p class="commonts_name">\${item.nickname}</p>
        			<p class="commonts_time">\${item.createDate}</p>
        		</div>
        		<div class="col-xs-4 col-md-4" style="text-align: right;">
					{@if item.grade == '1'}
					<img src="img/comment_level.png" class="comment_level" />
					{@else if item.grade == '2'}
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					{@else if item.grade == '3'}
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					{@else if item.grade == '4'}
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					{@else if item.grade == '5'}
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					<img src="img/comment_level.png" class="comment_level" />
					{@/if}
        		</div>
        	</div>
			<div class="row">
        		<div class="col-xs-2 col-md-2"></div>
				<div class="col-xs-10 col-md-10">
        			<p class="comment_content">
						\${item.content}
					</p>
    			</div>
    		</div>
    	</div>
		{@/each}
    </div>
</script>

</html>